package br.edu.ifto.pwebII.model.jdbc.repository;

import br.edu.ifto.pwebII.model.entity.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PacienteRepository {

    @PersistenceContext
    private EntityManager em; //objeto que permite falar com o banco

    //create
    public void save(Paciente paciente) {
        em.persist(paciente);
    }

    //remove
    public void remove(Long id) {
        Paciente paciente = em.find(Paciente.class, id); //procure o paciente ocm esse id
        if (paciente != null) {
            em.remove(paciente);
        }
    }

    //update
    public void update(Paciente paciente) {
        em.merge(paciente);
    }

    //buscar
    public Paciente paciente(Long id) {
        return em.find(Paciente.class, id);
    }

    //listar pacientes
    public List<Paciente> pacientes() {
        return em.createQuery("from Paciente").getResultList();
    }


}
