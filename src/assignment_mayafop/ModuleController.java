/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment_mayafop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Ming
 */
public class ModuleController implements Initializable, ControlledScreen{
    ScreenController myController = new ScreenController();
    animation Animation;
    MiscFunc misc = new MiscFunc();

    @FXML
    private ComboBox<String> MuetBandComboBox;

    @FXML
    private ComboBox<String> ProgrammeComboBox;

    @FXML
    private ComboBox<String> courseCategoryComboBox;

    @FXML
    private TextField courseIDTextField;

    @FXML
    private TextField courseNameTextField;

    @FXML
    private ComboBox<String> courseSemComboBox;

    @FXML
    private ComboBox<String> courseYearComboBox;

    @FXML
    private TextField creditHourTextField;

    @FXML
    private ComboBox<String> nationalityComboBox;
    
    @FXML
    private AnchorPane modulePane;
    
    @FXML
    private Button nextButton;
    
    @FXML
    private Button exit_button;
    
    @FXML
    private Label errorLabel;
    
    boolean upScreenStatus = false;
    
    private static String courseID;
    private static String coursename;
    private static String credithour;
    private static String muetband;
    private static String programme;
    private static String coursecategory;
    private static String coursesem;
    private static String courseyear;
    private static String nationality;

    private static String courseIdSetter;
    private static String courseNameSetter;
    private static String creditHourSetter;
    private static String courseCategorySetter;
    private static String CourseYearSetter;
    private static String courseSemSetter;
    private static String muetBandSetter;
    private static String nationalitySetter;
    private static String programmeSetter;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        courseIDTextField.setText(courseIdSetter);
        courseNameTextField.setText(courseNameSetter);
        creditHourTextField.setText(creditHourSetter);
        if(courseCategorySetter != null){
            courseCategoryComboBox.setValue(courseCategorySetter);
        }
        courseYearComboBox.setValue(CourseYearSetter);
        courseSemComboBox.setValue(courseSemSetter);
        MuetBandComboBox.setValue(muetBandSetter);
        nationalityComboBox.setValue(nationalitySetter);
        ProgrammeComboBox.setValue(programmeSetter);
        
        Animation = new animation();
        if(!upScreenStatus){
            Animation.fading(modulePane);
        }
        
        //Populate the combo box with information
        ObservableList<String> category = FXCollections.observableArrayList("University Course", "KELF", "Programme Core Course", "Faculty Core Course", "Faculty Elective Course", "Specialisation Elective Course");
        ObservableList<String> year = FXCollections.observableArrayList("ALL", "1","2");
        ObservableList<String> sem = FXCollections.observableArrayList("ALL", "1", "2");
        ObservableList<String> muet = FXCollections.observableArrayList("ALL", "1", "2", "3", "4", "5", "6");
        ObservableList<String> nationalityList = FXCollections.observableArrayList("ALL", "Malaysian", "Foreigner");
        ObservableList<String> programmeList = FXCollections.observableArrayList("ALL", "Software Engineer", "Artificial Intelligence", "Data Science", "Computer System and Networking", "Information System", "Multimedia");
        
        MuetBandComboBox.setItems(muet);
        ProgrammeComboBox.setItems(programmeList);
        courseCategoryComboBox.setItems(category);
        courseSemComboBox.setItems(sem);
        courseYearComboBox.setItems(year);
        nationalityComboBox.setItems(nationalityList);
        
//        MuetBandComboBox.getSelectionModel().selectFirst();
//        ProgrammeComboBox.getSelectionModel().selectFirst();
//        courseCategoryComboBox.getSelectionModel().selectFirst();
//        courseSemComboBox.getSelectionModel().selectFirst();
//        courseYearComboBox.getSelectionModel().selectFirst();
//        nationalityComboBox.getSelectionModel().selectFirst();
        
//        int num = formatToFullCategory(courseCategorySetter);
//        System.out.println(num);
        
        //whats wrong with combobox
//        courseIDTextField.setText(courseIdSetter);
//        courseNameTextField.setText(courseNameSetter);
//        creditHourTextField.setText(creditHourSetter);
//        courseYearComboBox.setValue(CourseYearSetter);
//        courseSemComboBox.setValue(courseSemSetter);
//        MuetBandComboBox.setValue(muetBandSetter);
//        nationalityComboBox.setValue(nationalitySetter);
//        ProgrammeComboBox.setValue(programmeSetter);
    }

    @Override
    public void setScreenParent(ScreenController screenParent) {
        myController = screenParent; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void exitButton(ActionEvent event) {
        //Click on exit button to exit       
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    } 
    
    public void getValues(){
        courseID = courseIDTextField.getText();
        coursename = courseNameTextField.getText();
        credithour = creditHourTextField.getText();
        muetband = MuetBandComboBox.getValue();
        programme = ProgrammeComboBox.getValue();
        coursecategory = courseCategoryComboBox.getValue();
        coursesem = courseSemComboBox.getValue();
        courseyear = courseYearComboBox.getValue();
        nationality = nationalityComboBox.getValue();
    }
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void goToNextModule(ActionEvent event) throws IOException{
        getValues();
        if(courseID != null && coursename != null && credithour != null){
            root = FXMLLoader.load(getClass().getResource("ModuleNext.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
//            errorLabel.setText("Please fill in all the information");
        }
        
    }
    
    public void getOccDetails(){
        
    }
    
    
    public String getCourseID() {
        return courseID;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getCredithour() {
        return credithour;
    }

    public String getMuetband() {
        return muetband;
    }

    public String getProgramme() {
        return programme;
    }

    public String getCoursecategory() {
        return coursecategory;
    }

    public String getCoursesem() {
        return coursesem;
    }

    public String getCourseyear() {
        return courseyear;
    }

    public String getNationality() {
        return nationality;
    }
    
    
    public void setMuetBandComboBox(String a) {
        MuetBandComboBox.setId(a);
    }

    public void setProgrammeComboBox(String a) {
        ProgrammeComboBox.setId(a);
    }

    public void setCourseCategoryComboBox(String a) {
        courseCategoryComboBox.setId(a);
    }

    public void setCourseIDTextField(String a) {
        courseIDTextField.setText(a);
    }

    public void setCourseNameTextField(String a) {
        courseNameTextField.setText(a);
    }

    public void setCourseSemComboBox(String a) {
        courseSemComboBox.setId(a);
    }

    public void setCourseYearComboBox(String a) {
        courseYearComboBox.setId(a);
    }

    public void setCreditHourTextField(String a) {
        creditHourTextField.setText(a);
    }

    public void setNationalityComboBox(String a) {
        nationalityComboBox.setId(a);
    }
    
    
    public String getCourseIdSetter() {
        System.out.println("This is the course: " + courseIdSetter );
        return courseIdSetter;
    }

    public void setCourseIdSetter(String courseIdSetter) {
        ModuleController.courseIdSetter = courseIdSetter;
    }

    public String getCourseNameSetter() {
        return ModuleController.courseNameSetter;
    }

    public void setCourseNameSetter(String courseNameSetter) {
        ModuleController.courseNameSetter = courseNameSetter;
    }

    public String getCreditHourSetter() {
        return ModuleController.creditHourSetter;
    }

    public void setCreditHourSetter(String creditHourSetter) {
        ModuleController.creditHourSetter = creditHourSetter;
    }

    public String getCourseCategorySetter() {
        return ModuleController.courseCategorySetter;
    }

    public void setCourseCategorySetter(String courseCategorySetter) {
        ModuleController.courseCategorySetter = courseCategorySetter;
    }

    public String getCourseYearSetter() {
        return ModuleController.CourseYearSetter;
    }

    public void setCourseYearSetter(String CourseYearSetter) {
        ModuleController.CourseYearSetter = CourseYearSetter;
    }

    public String getCourseSemSetter() {
        return ModuleController.courseSemSetter;
    }

    public void setCourseSemSetter(String courseSemSetter) {
        ModuleController.courseSemSetter = courseSemSetter;
    }

    public String getMuetBandSetter() {
        return ModuleController.muetBandSetter;
    }

    public void setMuetBandSetter(String muetBandSetter) {
        ModuleController.muetBandSetter = muetBandSetter;
    }

    public String getNationalitySetter() {
        return ModuleController.nationalitySetter;
    }

    public void setNationalitySetter(String nationalitySetter) {
        ModuleController.nationalitySetter = nationalitySetter;
    }

    public String getProgrammeSetter() {
        return ModuleController.programmeSetter;
    }

    public void setProgrammeSetter(String programmeSetter) {
        ModuleController.programmeSetter = programmeSetter;
    }
    
    public int formatToFullCategory(String name){
        if(name.equals("UC")){
            return 0;
        }else if(name.equals("KELF")){
            return 1;
        }else if(name.equals("PCC")){
            return 2;
        }else if(name.equals("FCC")){
            return 3;
        }else if(name.equals("FEC")){
            return 4;
        }else if(name.equals("SEC")){
            return 5;
        }return 0;
    }
    
}
