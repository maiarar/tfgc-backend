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
 * Entidade que representa uma Candidata.
 */
@Entity
@Table(name = "tecnologia")
public class TecnologiaDomain implements Serializable{

    private static final long serialVersionUID = -9036500518687396229L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "tx_id")
    private UUID id;

    @Column(name = "tx_name", length = 100)
    private String name;

    public TecnologiaDomain(String name) {
        this.name = name;
    }

    public TecnologiaDomain() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
