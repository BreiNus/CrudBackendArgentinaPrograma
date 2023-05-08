package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.entity.Proyectos;
import Backend.example.BackEndCRUD.repository.ProyectosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProyectosService implements IProyectosService{

    @Autowired
    public ProyectosRepository proyectosRepository;
    @Override
    public List<Proyectos> verProyectos() {
        return proyectosRepository.findAll();
    }

    @Override
    public void crearProyectos(Proyectos proyectos) {
    proyectosRepository.save(proyectos);
    }

    @Override
    public void borrarProyecto(Long id) {
    proyectosRepository.deleteById(id);
    }

    @Override
    public Proyectos buscarProyectos(Long id) {
        return proyectosRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existById(Long id) {
        return proyectosRepository.existsById(id);
    }

    @Override
    public Optional<Proyectos> getByNombreProyecto(String nombreProyecto) {
        return proyectosRepository.findByNombreProyecto(nombreProyecto);
    }

    @Override
    public boolean existByNombreProyecto(String nombreProyecto) {
        return proyectosRepository.existsByNombreProyecto(nombreProyecto);
    }
}
