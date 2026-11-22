package br.edu.ifto.pwebII.model.entity;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifto.pwebII.model.entity.Perfil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String login;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String password; // hash BCrypt, nunca a senha em texto puro

    private boolean ativo = true;

    @ManyToMany
    @JoinTable(name = "usuario_perfil", // opcional: apenas explicita a tabela de associação
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfis = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void adicionarPerfil(Perfil perfil) {
        perfis.add(perfil);
        perfil.getUsuarios().add(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }



    public List<Perfil> getPerfis() { return perfis; }
}