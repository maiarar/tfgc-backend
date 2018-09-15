package br.com.tfgc.candidata.interfaces.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Calendar;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 *Representação em JSON de uma Candidata.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Candidata {

    /**
     * Nome da Candidata.
     */
    @Length(max = 100)
    @NotBlank
    private String name;

    /**
     * Data de nascimento da Candidata.
     */
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    @NotBlank
    private Calendar birthday;

    /**
     * Atuação da Candidata.
     */
    @Length(max = 50)
    @NotBlank
    private String occupation;


    /**
     * Experiência da Candidata.
     */
    @Length(max = 300)
    @NotBlank
    private String experience;

    /**
     * E-mail da Candidata.
     */
    @Length(max = 50)
    @NotBlank
    private String email;

    /**
     * CPF da candidata.
     */
    @Length(max = 11)
    @NotBlank
    private String cpf;

    /**
     * Construtor vazio.
     */
    public Candidata() {
    }

    /**
     * @param name nome da candidata.
     * @param birthday data de nascimento da candidata
     * @param occupation atuação da candidata
     * @param experience experiência da candidata
     * @param email e-mail da candidata
     * @param cpf CPF da candidata
     */
    public Candidata(
        @Length(max = 50) @NotBlank String name,
        @NotBlank Calendar birthday,
        @Length(max = 50) @NotBlank String occupation,
        @Length(max = 50) @NotBlank String experience,
        @Length(max = 50) @NotBlank String email,
        @Length(max = 11) @NotBlank String cpf) {
        this.name = name;
        this.birthday = birthday;
        this.occupation = occupation;
        this.experience = experience;
        this.email = email;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
