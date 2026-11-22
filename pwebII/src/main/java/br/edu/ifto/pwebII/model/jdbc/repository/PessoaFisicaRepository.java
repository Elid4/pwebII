package br.edu.ifto.pwebII.model.jdbc.repository;

import br.edu.ifto.pwebII.model.entity.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Repository
public class PessoaFisicaRepository {

    @PersistenceContext
    private EntityManager em;

    public boolean cpfExiste(String cpf, Long id) {

        String jpql = "SELECT COUNT(p) FROM PessoaFisica p " +
                "WHERE p.cpf = :cpf"; //conta o numeros de cpf

        if (id != null) {
            jpql += " AND p.id <> :id"; //se tiver id ele ignora o id dessa pessoa
        }

        var query = em.createQuery(jpql, Long.class);
        query.setParameter("cpf", cpf); //insere o parametro cpf

        if (id != null) {
            query.setParameter("id", id);// ve se tem id para colocar com oparametro se tiver para ignorar a linha dele
        }

        return query.getSingleResult() > 0;//pega o final de cpf encontrado e se for maior que 0 retorn true
    }
}
