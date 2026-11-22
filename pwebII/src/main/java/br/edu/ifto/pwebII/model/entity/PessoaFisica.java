package br.edu.ifto.pwebII.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class PessoaFisica extends Pessoa {

    @NotBlank(message = "Informe o nome.")
    @Size(max = 100, message = "O nome deve ter no máximo {max} caracteres.")
    private String nome;

    private LocalDate dataNascimento;

    @NotBlank(message = "Informe o CPF.")
    @Pattern(regexp = "[0-9]{11}", message = "O CPF deve conter onze dígitos.")
    @Column(unique = true)
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @NotNull(message = "A data de nascimento é obrigatória.")//testa se é NULL
    @Past(message = "A data de nascimento deve estar no passado.") //testa se é no futuro ou presente se sim retorna erro
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @AssertTrue(message = "A pessoa deve ter pelo menos 18 anos.")//testa se o metodo retorna true
    public boolean isMaiorDeIdade() {
        return dataNascimento != null ||
                !dataNascimento.isAfter(LocalDate.now().minusYears(18));
    }
}