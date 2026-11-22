package br.edu.ifto.pwebII.model.jdbc.repository;


import br.edu.ifto.pwebII.model.entity.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicoRepository {
    
    @PersistenceContext
    private EntityManager em;

    // CREATE
    public void save(Medico medico) {
        em.persist(medico);
    }

    // READ - buscar um funcionário pelo ID
    public Medico medico(Long id) {
        return em.find(Medico.class, id);
    }

    // READ - listar todos
    public List<Medico> medico() {
        Query query = em.createQuery("from Medico");
        return query.getResultList();
    }

    // DELETE
    public void remove(Long id) {
        Medico medico = em.find(Medico.class, id);

        if (medico != null) {
            em.remove(medico);
        }
    }

    // UPDATE
    public void update(Medico medico) {
        em.merge(medico);
    }

    
}
