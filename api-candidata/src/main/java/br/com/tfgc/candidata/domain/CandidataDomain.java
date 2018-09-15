package br.com.tfgc.candidata.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

/**
 * Entidade que representa a tabela de Candidata.
 */
@Entity
@Table(name = "candidata")
public class CandidataDomain implements Serializable{

    private static final long serialVersionUID = -4692097293453361637L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "tx_id")
    private UUID id;

    @Column(name = "tx_name", length = 100)
    private String name;

    @Column(name = "dt_birthday", nullable = false)
    private Calendar birthday;

    @Column(name = "tx_occupation", length = 50)
    private String occupation;

    @Column(name = "tx_experience", length = 300)
    private String experience;

    @Column(name = "tx_email", length = 50)
    private String email;

    @Column(name = "tx_cpf", length = 11)
    private String cpf;

    public CandidataDomain(String name, Calendar birthday, String occupation,
        String experience, String email, String cpf) {
        this.name = name;
        this.birthday = birthday;
        this.occupation = occupation;
        this.experience = experience;
        this.email = email;
        this.cpf = cpf;
    }

    public CandidataDomain() {
    }

    public UUID getId() {
        return id;
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

    public void setId(UUID id) {
        this.id = id;
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
