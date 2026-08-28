package ni.edu.uam.registro_app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ni.edu.uam.registro_app.dao.EstudianteDao;
import ni.edu.uam.registro_app.modelos.Estudiante;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EstudianteController implements Initializable {

    EstudianteDao listado = new EstudianteDao();

    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtCarrera;
    @FXML
    private DatePicker dpFechaNac;
    @FXML
    private CheckBox chkTieneBeca;
    @FXML
    private Label lblRegistros;
    @FXML
    private ComboBox<String> cbSexo;
    @FXML
    private ListView<String> lvRegistro;


    @FXML
    protected void guardarOnClick() {
        leerDatos();
        contarRegistros();
        mostrarenListView();
        tieneBeca();
        sexo();
        limpiarRegistrodespuesdeAgregar();
    }


    private void leerDatos() {
        String nombre = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String carrera = txtCarrera.getText();
        LocalDate fechaNac = dpFechaNac.getValue();
        Boolean tieneBeca = chkTieneBeca.isSelected();
        Boolean sexo = null;
        if (cbSexo.getValue() != null) {
            if (cbSexo.getValue().equals("Masculino")) {
                sexo = true;
            } else if (cbSexo.getValue().equals("Femenino")) {
                sexo = false;
            }
        }
        agregarDatos(new Estudiante(
                nombre, apellidos, carrera, fechaNac, tieneBeca, sexo
        ));
    }

    private void agregarDatos(Estudiante estudiante) {
        listado.agregar(estudiante);
    }

    private void contarRegistros() {
        lblRegistros.setText(
                "Registros almacenados: " + listado.obtenerRegistros().size()
        );
    }

    private void limpiarRegistrodespuesdeAgregar() {
        txtNombres.clear();
        txtApellidos.clear();
        txtCarrera.clear();
        dpFechaNac.setValue(null);
        chkTieneBeca.setSelected(false);
        cbSexo.getSelectionModel().clearSelection();
    }
    private void mostrarenListView() {
        ObservableList<String> items =
                FXCollections.observableArrayList();

        for (Estudiante estudiante : listado.obtenerRegistros()) {
            String sexo;

            if (estudiante.getSexo() == null) {
                sexo = "No seleccionado";
            } else if (estudiante.getSexo()) {
                sexo = "Masculino";
            } else {
                sexo = "Femenino";
            }
            String beca;
            if (estudiante.getTienebeca()) {
                beca = "Con beca ✓";
            } else {
                beca = "Sin beca X";
            }

            String item = estudiante.getNombres() + " " + estudiante.getApellidos() + " - " + estudiante.getCarrera()
                    + " - " + sexo + " - " + beca + " - " + estudiante.getFechaNacimiento(); items.add(item);
        }
        lvRegistro.setItems(items);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items = FXCollections.observableArrayList(
                "Masculino", "Femenino"
        );
        cbSexo.setItems(items);
    }

    private void tieneBeca() {

        if (chkTieneBeca.isSelected()) {
            System.out.println("✓");
        } else {
            System.out.println("✕");
        }
    }

    private void sexo() {

        if (cbSexo.getValue() != null) {
            System.out.println(cbSexo.getValue());
        }
    }
}