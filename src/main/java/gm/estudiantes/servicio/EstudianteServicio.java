package gm.estudiantes.servicio;

import gm.estudiantes.modelo.Estudiante;
import gm.estudiantes.repositorio.EstudianteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //Lo agregamos para poder inyectar las dependencias que necesitamos
public class EstudianteServicio implements IEstudianteServicio{

    @Autowired //Para poder inyectar la dependencia que necesitamos
    private EstudianteRepositorio estudianteRepositorio;


    @Override
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes= estudianteRepositorio.findAll();//find all para qeu devuelva toda la lista
        return estudiantes;
    }

    @Override
    public Estudiante buscarEstudiantePorId(Integer idEstudiante) {
        Estudiante estudiante = estudianteRepositorio.findById(idEstudiante).orElse(null); // orElse devuelve nulo si no encuentra el objeto
        return estudiante;
    }

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.save(estudiante);

    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.delete(estudiante);
    }
}
