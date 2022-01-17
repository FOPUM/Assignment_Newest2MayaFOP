/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import javafxModel.registeredStuentDetailsPopupTextModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Ming
 */
public class registeredStudentDetailsPopupController implements Initializable{
    
    MiscFunc misc = new MiscFunc();
    
    @FXML
    private Label courseCapacityLabel;

    @FXML
    private Label courseCodeLabel;

    @FXML
    private Label courseLocationLabel;

    @FXML
    private Label courseModeLabel;

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label courseOccLabel;

    @FXML
    private Label courseTimeLabel;
    
    @FXML
    private Label courseDayLabel;

    @FXML
    private VBox vContainerRegisteredStudent;
    
    private String occ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        registeredStudentController control = new registeredStudentController();
        occ = control.getOcc();
        System.out.println("This is " +occ);
        try {
            getCourseDetailsStudent();
        } catch (SQLException ex) {
            Logger.getLogger(registeredStudentDetailsPopupController.class.getName()).log(Level.SEVERE, null, ex);
        }
        insertCourseDetails();
        
    }
    
    public void setContentInfo(String coursecode, String coursename, String courseocc, String coursecapacity, String coursemode, String courseday, String coursetime, String courselocation, String occur){
        courseCodeLabel.setText(coursecode);
        courseNameLabel.setText(misc.upperLetter(coursename));
        courseOccLabel.setText("Occurence: " + courseocc.substring(3));
        courseCapacityLabel.setText(coursecapacity);
        courseDayLabel.setText(misc.formatDay(courseday));
        courseTimeLabel.setText(coursetime);
        courseLocationLabel.setText(misc.upperLetter(courselocation));
        courseModeLabel.setText(coursemode.toUpperCase());  
        this.occ = occur;
        System.out.println("Occ is " + occ);
    }

    
    login_controller loginControl = new login_controller();
    
    databaseConnection connectNow = new databaseConnection();
    Connection connectDB = connectNow.getConnection();

    String matric_num = loginControl.getUsername();
    char accStatus = loginControl.getAccStatus();
    
    private static ArrayList<String> matricID = new ArrayList<String>();
    private static ArrayList<String> studentName = new ArrayList<String>();
    private static ArrayList<Integer> numberOfStudents = new ArrayList<Integer>();
    
    List<registeredStuentDetailsPopupTextModel> studentDetails = new ArrayList<>();
    
    registeredStudentController previousController = new registeredStudentController();
    private ArrayList<String> occID = previousController.getOccIDStaff();
    
    public void getCourseDetailsStudent() throws SQLException{
        matricID.clear();
        studentName.clear();
        
//        System.out.println(occID.size());
//        if(occID.size() != 0){
//            try {
//                for (int i = 0; i < occID.size(); i++) {
//                    for (int j = 0; j < occID.size(); j++) {
//                        if (occID.get(i).equals(occID.get(j)) && i != j) {
//                            System.out.println("occID is removed this: " + occID.get(j));
//                            occID.remove(j);
//                        }
//                    }
//                }
//                System.out.println("OccIDSize is : " + occID.size());
//                for (int i = 0; i < occID.size(); i++) {
//                    numberOfStudents.add(-1);
//                    System.out.println("occID got: " + occID.get(i));
//                }
//                for (int i = 0; i < occID.size(); i++) {
                    boolean gotStudents = false;
//                    System.out.println(i);
//                    System.out.println(occID.get(i));
                    String courseDetailss="SELECT student_take_course.matric_num AS matricID, student.student_name AS studentName\n" +
                                    "FROM student_take_course\n" +
                                    "INNER JOIN student ON student.matric_num=student_take_course.matric_num\n" +
                                    "WHERE student_take_course.course_status='y' AND student_take_course.occ_id='"+occ+"'";
                    ResultSet courseIDQuery = connectDB.createStatement().executeQuery(courseDetailss);
                    while(courseIDQuery.next()) {
                        matricID.add(courseIDQuery.getString("matricID"));
                        studentName.add(courseIDQuery.getString("studentName"));
//                        numberOfStudents.set(i, matricID.size()-1);
                        gotStudents = true;
                    } 
                    if (!gotStudents) {
                            gotStudents = true;
                            matricID.add("No student arh");
                            studentName.add("");
                            System.out.println("Matric Id and Student Name is empty");
//                            System.out.println("Matric id = " + matricID.get(i));
//                            System.out.println("StudentName id = " + studentName.get(i));
//                            numberOfStudents.set(i, matricID.size()-1);
                        }

//                }



//            } catch (SQLException e) {
//                Logger.getLogger(userAccount.class.getName()).log(Level.SEVERE, null, e);
//                e.printStackTrace();
//            }
        }
    

    public void insertCourseDetails(){
        
        try {
            studentDetails.clear();
            for (int j = 0; j < matricID.size(); j++) {
                System.out.println("Matric id here= " + matricID.get(j));
                System.out.println("StudentName id here= " + studentName.get(j));
                studentDetails.add(new registeredStuentDetailsPopupTextModel(matricID.get(j),studentName.get(j)));
            }
            
            Node[] nodes = new Node[studentDetails.size()];
            
            for (int j = 0; j < nodes.length; j++) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Assignment_MayaFOP/registeredStuentDetailsPopupText.fxml"));
                nodes[j] = loader.load();
                
                final int h = j;
                
                registeredStuentDetailsPopupTextController studentDetailsPopupTextController = loader.getController();
                //customise content
                studentDetailsPopupTextController.setContentInfo(studentDetails.get(j).getMatricIDLabel(),studentDetails.get(j).getStudentNameLabel());               
                    
//                nodes[h].setOnMouseEntered(evt -> {
//                    //add effect
//                    nodes[h].setStyle("-fx-background-color: #b4baca");
//                });
//                nodes[h].setOnMouseExited(evt -> {
//                    //add effect
//                    nodes[h].setStyle("-fx-background-color: transparent");
//                });
//                nodes[h].setOnMousePressed(evt -> {
//                    //add effect
//                });

                vContainerRegisteredStudent.getChildren().add(nodes[j]);
            }

        } catch (Exception e) {
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(moduleConfirmationMessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void resetMemory(){
        matricID.clear();
        studentName.clear();
    }

}
