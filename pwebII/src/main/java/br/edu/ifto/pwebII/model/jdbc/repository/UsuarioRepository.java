package br.edu.ifto.pwebII.model.jdbc.repository;

import br.edu.ifto.pwebII.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {
    @PersistenceContext
    private EntityManager em;
    
    // CREATE
    public void save(Usuario usuario) {
        em.persist(usuario);
    }

    // REMOVE
    public void remove(Long id) {
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.remove(usuario);
        }
    }

    // UPDATE
    public void update(Usuario usuario) {
        em.merge(usuario);
    }

    // BUSCAR
    public Usuario usuario(Long id) {
        return em.find(Usuario.class, id);
    }

    // LISTAR USUÁRIOS
    public List<Usuario> usuarios() {
        return em.createQuery("from Usuario", Usuario.class).getResultList();
    }

    // BUSCAR USUÁRIO PELO LOGIN
    public Optional<Usuario> findByLogin(String login) {
        return em.createQuery(
                        "SELECT u FROM Usuario u WHERE u.login = :login",
                        Usuario.class
                )
                .setParameter("login", login)
                .getResultStream()
                .findFirst();
    }
}
