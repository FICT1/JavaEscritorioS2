package ni.edu.uam.registro_app.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ni.edu.uam.registro_app.dao.EstudianteDao;
import ni.edu.uam.registro_app.modelos.Estudiante;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EstudianteController implements Initializable {

    private final EstudianteDao listado = new EstudianteDao();

    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtCarrera;
    @FXML private DatePicker dpFechaNac;
    @FXML private CheckBox chkTieneBeca;
    @FXML private Label lblRegistros;
    @FXML private ComboBox<String> cbFacultad;
    @FXML private RadioButton rbHombre;
    @FXML private RadioButton rbMujer;

    @FXML private TableView<Estudiante> tvEstudiantes;
    @FXML private TableColumn<Estudiante, String> colNombres;
    @FXML private TableColumn<Estudiante, String> colApellidos;
    @FXML private TableColumn<Estudiante, String> colFacultad;
    @FXML private TableColumn<Estudiante, String> colCarrera;
    @FXML private TableColumn<Estudiante, String> colSexo;
    @FXML private TableColumn<Estudiante, String> colBeca;
    @FXML private TableColumn<Estudiante, LocalDate> colFechaNac;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbFacultad.setItems(FXCollections.observableArrayList(
                "FIA", "FCAE", "FCJHRI", "FMDC", "FO", "FCM"
        ));

        ToggleGroup grupoSexo = new ToggleGroup();
        rbHombre.setToggleGroup(grupoSexo);
        rbMujer.setToggleGroup(grupoSexo);

        configurarTabla();
    }

    private void configurarTabla() {
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colFacultad.setCellValueFactory(new PropertyValueFactory<>("facultad"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        colFechaNac.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));

        colSexo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getSexo() ? "Hombre" : "Mujer"));

        colBeca.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTienebeca() ? "Sí" : "No"));
    }

    @FXML
    protected void guardarOnClick() {
        if (!validarDatos()) {
            return;
        }
        leerDatos();
        actualizarTabla();
        contarRegistros();
        limpiarRegistro();
    }

    private boolean validarDatos() {
        if (txtNombres.getText().trim().isEmpty()) return mostrarAlerta("Complete el campo Nombres.");
        if (txtApellidos.getText().trim().isEmpty()) return mostrarAlerta("Complete el campo Apellidos.");
        if (cbFacultad.getValue() == null) return mostrarAlerta("Seleccione una Facultad.");
        if (txtCarrera.getText().trim().isEmpty()) return mostrarAlerta("Complete el campo Carrera.");
        if (dpFechaNac.getValue() == null) return mostrarAlerta("Seleccione la Fecha de Nacimiento.");

        LocalDate fecha = dpFechaNac.getValue();
        if (fecha.getYear() < 1915 || fecha.isAfter(LocalDate.now())) {
            return mostrarAlerta("La fecha de nacimiento no es válida.");
        }
        if (!rbHombre.isSelected() && !rbMujer.isSelected()) {
            return mostrarAlerta("Seleccione el sexo.");
        }
        return true;
    }

    private boolean mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Datos incompletos");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
        return false;
    }

    private void leerDatos() {
        Estudiante estudiante = new Estudiante(
                txtNombres.getText().trim(),
                txtApellidos.getText().trim(),
                txtCarrera.getText().trim(),
                dpFechaNac.getValue(),
                chkTieneBeca.isSelected(),
                cbFacultad.getValue(),
                rbHombre.isSelected()
        );
        listado.agregar(estudiante);
    }

    private void actualizarTabla() {
        ObservableList<Estudiante> listaObservable = FXCollections.observableArrayList(listado.obtenerRegistros());
        tvEstudiantes.setItems(listaObservable);
    }

    private void contarRegistros() {
        lblRegistros.setText("Registros guardados: " + listado.obtenerRegistros().size());
    }

    private void limpiarRegistro() {
        txtNombres.clear();
        txtApellidos.clear();
        txtCarrera.clear();
        dpFechaNac.setValue(null);
        chkTieneBeca.setSelected(false);
        cbFacultad.getSelectionModel().clearSelection();
        rbHombre.setSelected(false);
        rbMujer.setSelected(false);
    }
}