package br.edu.ifto.pwebII.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "tb_medico")
@PrimaryKeyJoinColumn(name = "id_pessoa_fisica")
public class Medico extends PessoaFisica{
    @NotBlank(message = "Digite o CRM")
    private String crm;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.REMOVE)
    private List<Consulta> consultas;


    //getters e setters
    public String getCrm() {
        return crm;
    }
    public void setCrm(String crm) {
        this.crm = crm;
    }

}
