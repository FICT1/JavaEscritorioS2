package ni.edu.uam.registro_app.modelos;

import javafx.fxml.FXML;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    private String nombres;
    private String apellidos;
    private String carrera;
    private LocalDate fechaNacimiento;
    private Boolean tienebeca;
}
