package ni.edu.uam.registro_app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ni.edu.uam.registro_app.dao.EstudianteDao;
import ni.edu.uam.registro_app.modelos.Estudiante;

import java.time.LocalDate;

public class EstudianteController {

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
    private ListView lvRegistro;

    @FXML
    private ComboBox cbSexo;


    @FXML
    protected void guardarOnClick(){
        leerDatos();
        contarRegistros();
        limpiarRegistrodespuesdeAgregar();
        mostrarenListView();
    }

    @FXML
    protected void mostrarenCombobox(){
        llenarComboBox();
    }

    private void leerDatos(){
        String nombre = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String carrera = txtCarrera.getText();
        LocalDate fechaNac = dpFechaNac.getValue();
        Boolean tieneBeca = chkTieneBeca.isSelected();
        agregarDatos(new Estudiante(nombre, apellidos, carrera, fechaNac, tieneBeca));
    }

    private void agregarDatos(Estudiante estudiante){
        listado.agregar(estudiante);
    }

    private void contarRegistros(){
        lblRegistros.setText("Registros almacenados: " + listado.obtenerRegistros().size());
    }

    private void limpiarRegistrodespuesdeAgregar(){
        txtNombres.clear();
        txtApellidos.clear();
        txtCarrera.clear();
        dpFechaNac.setValue(null);
        chkTieneBeca.setSelected(false);
    }

    private void mostrarenListView(){

    }


    private void llenarComboBox(){
        cbSexo.getItems().addAll("Masculino", "Femenino", "Therian");
    }
}
