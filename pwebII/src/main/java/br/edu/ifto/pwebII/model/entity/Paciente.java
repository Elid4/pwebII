package br.edu.ifto.pwebII.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_paciente")
@PrimaryKeyJoinColumn(name = "id_pessoa_fisica")
public class Paciente extends PessoaFisica {
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE)
    private List<Consulta> consultas;




}

