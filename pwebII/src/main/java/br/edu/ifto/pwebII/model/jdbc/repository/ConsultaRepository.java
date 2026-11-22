package br.edu.ifto.pwebII.model.jdbc.repository;

import br.edu.ifto.pwebII.model.entity.Consulta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsultaRepository {

    @PersistenceContext
    private EntityManager em;

    // CREATE
    public void save(Consulta consulta) {
        em.persist(consulta);
    }

    //REMOVE
    public void remove(Long id) {
        Consulta consulta = em.find(Consulta.class, id);
        if (consulta != null) {
            em.remove(consulta);
        }
    }

    //UPDATE
    public void update(Consulta consulta){
        em.merge(consulta);
    }

    //buscar
    public Consulta consulta(Long id) {
        return em.find(Consulta.class, id);
    }

    // listar todos
    public List<Consulta> consultas() {
        return em.createQuery("from Consulta").getResultList();
    }

    // listar consultas de um paciente
    public List<Consulta> consultasPorPaciente(Long id) {
        return em.createQuery(
                        "from Consulta c where c.paciente.id = :id",
                        Consulta.class
                )
                .setParameter("id", id)
                .getResultList();
    }

    // listar consultas de um médico
    public List<Consulta> consultasPorMedico(Long id) {
        return em.createQuery(
                        "from Consulta c where c.medico.id = :id",
                        Consulta.class
                )
                .setParameter("id", id)
                .getResultList();
    }
}
