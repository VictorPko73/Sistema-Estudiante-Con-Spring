package gm.estudiantes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

// Para evitar todo el codigo repetitivo se introduce lo siguiente (boiler plate)
//Se generan todo los metodo get y set automaticamente con (@Data)

@Data

// Para agragar el contructor vacio introducimos lo siguiente @NoArgsConstructor
@NoArgsConstructor

// Para agregar el constructor con todos los argumentos
@AllArgsConstructor

// Para agregar los metodo toString
@ToString

public class Estudiante {
    @Id //para indicar que es una llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para generar de manera automatica la llave primaria
    private Integer idEstudiante;

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
//    @Override
//    public String toString() {
//        return "Estudiante{id=" + idEstudiante +
//                ", nombre='" + nombre + '\'' +
//                ", apellido='" + apellido + '\'' +
//                ", telefono='" + telefono + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }


}

