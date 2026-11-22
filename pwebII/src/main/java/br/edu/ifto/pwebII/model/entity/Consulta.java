package br.edu.ifto.pwebII.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
@Entity
@Table(name = "tb_consulta")
public class Consulta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Informe a data.")
    @FutureOrPresent(message = "Coloque uma data válida")
    private LocalDate data;

    @NotNull(message = "Informe o valor.")
    @Positive(message = "A consulta tem que ter valor maior que 0")
    private Double valor;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    @NotNull(message = "Selecione um médico.")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @NotNull(message = "Selecione um paciente.")
    private Paciente paciente;

    //getters e setters
    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
