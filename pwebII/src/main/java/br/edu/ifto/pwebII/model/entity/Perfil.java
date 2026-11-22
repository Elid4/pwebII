package br.edu.ifto.pwebII.model.entity;

import br.edu.ifto.pwebII.model.entity.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 30)
    private String nome; // ADMIN, USER: sem o prefixo ROLE_

    @ManyToMany(mappedBy = "perfis")
    private List<Usuario> usuarios = new ArrayList<>();

    public Perfil() {
    }

    public Perfil(String nome) {
        this.nome = nome;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Usuario> getUsuarios() { return usuarios; }
}