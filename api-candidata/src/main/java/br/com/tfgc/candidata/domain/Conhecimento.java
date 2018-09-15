package br.com.tfgc.candidata.domain;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

/**
 * Entidade que representa a tabela de Conhecimento.
 */
@Entity
@Table(name = "conhecimento")
public class Conhecimento implements Serializable{

    private static final long serialVersionUID = -3388290985783281605L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "tx_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "nr_id_tecnologia", referencedColumnName = "tx_id")
    private TecnologiaDomain tecnologia;

    @Column(name = "nr_nivel_conhecimento")
    private Integer levelKnowledge;

    @ManyToOne
    @JoinColumn(name = "nr_id_candidata", referencedColumnName = "tx_id")
    private CandidataDomain candidata;

    public Conhecimento(TecnologiaDomain tecnologia, Integer levelKnowledge,
        CandidataDomain candidata) {
        this.tecnologia = tecnologia;
        this.levelKnowledge = levelKnowledge;
        this.candidata = candidata;
    }

    public Conhecimento() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TecnologiaDomain getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(TecnologiaDomain tecnologia) {
        this.tecnologia = tecnologia;
    }

    public Integer getLevelKnowledge() {
        return levelKnowledge;
    }

    public void setLevelKnowledge(Integer levelKnowledge) {
        this.levelKnowledge = levelKnowledge;
    }

    public CandidataDomain getCandidata() {
        return candidata;
    }

    public void setCandidata(CandidataDomain candidata) {
        this.candidata = candidata;
    }
}
