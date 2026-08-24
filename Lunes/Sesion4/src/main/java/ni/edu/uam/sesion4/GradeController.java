package ni.edu.uam.sesion4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;
import ni.edu.uam.sesion4.dao.GradeDao;
import ni.edu.uam.sesion4.models.Student;

public class GradeController {

    GradeDao grades = new GradeDao();


    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCareer;
    @FXML
    private TextField txtGrade;
    @FXML
    private Label lblCountReg;

    @FXML
    private Button btnSave;


    @FXML
    protected void saveButtonAction(){
        addGrade();
        countGrade();
    }


    private void addGrade(){
        String name = txtName.getText();
        String career = txtGrade.getText();
        int grade = Integer.parseInt(txtGrade.getText());
        saveGrade(new Student(name, career, grade));

    }


    private void saveGrade(Student student){
        grades.addGrade(student);
    }


    private void countGrade(){
        lblCountReg.setText("Registros guardados: "+ grades.getGrades().size());
    }
}
