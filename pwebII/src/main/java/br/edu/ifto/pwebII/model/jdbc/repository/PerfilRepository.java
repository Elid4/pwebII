package br.edu.ifto.pwebII.model.jdbc.repository;
import br.edu.ifto.pwebII.model.entity.Paciente;
import br.edu.ifto.pwebII.model.entity.Perfil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PerfilRepository {
    @PersistenceContext
    private EntityManager em;

    //CREATE
    public void save(Perfil perfil) {
        em.persist(perfil);
    }

    //remove
    public void remove(Long id) {
        Perfil perfil = em.find(Perfil.class, id); //procure o paciente ocm esse id
        if (perfil != null) {
            em.remove(perfil);
        }
    }

    //update
    public void update(Perfil perfil) {
        em.merge(perfil);
    }

    //buscar
    public Perfil perfil(Long id) {
        return em.find(Perfil.class, id);
    }

    //listar pacientes
    public List<Perfil> perfil() {
        return em.createQuery("from Perfil").getResultList();
    }
}