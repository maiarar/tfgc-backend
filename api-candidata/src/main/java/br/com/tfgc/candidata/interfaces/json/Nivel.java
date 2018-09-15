package br.com.tfgc.candidata.interfaces.json;


import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 *Representação em JSON do Nível de conhecimento de uma Candidata.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Nivel {

    /**
     * Id da tecnologia.
     */
    private UUID idTecnologia;

    /**
     * Nome da tecnologia.
     */
    @NotBlank
    @Length(max = 50)
    private String nameTecnologia;

    /**
     * Nível da candidata na tecnologia.
     */
    @NotNull
    private Integer levelCandidata;

    /**
     * Informa se é uma nova tecnologia a ser salva na base ou não.
     */
    @NotBlank
    @NotNull
    private Boolean newInsertion;

    public Nivel(UUID idTecnologia,
        @NotBlank @Length(max = 50) String nameTecnologia,
        @NotNull Integer levelCandidata,
        @NotBlank @NotNull Boolean newInsertion) {
        this.idTecnologia = idTecnologia;
        this.nameTecnologia = nameTecnologia;
        this.levelCandidata = levelCandidata;
        this.newInsertion = newInsertion;
    }

    public Nivel() {
    }

    public UUID getIdTecnologia() {
        return idTecnologia;
    }

    public void setIdTecnologia(UUID idTecnologia) {
        this.idTecnologia = idTecnologia;
    }

    public String getNameTecnologia() {
        return nameTecnologia;
    }

    public void setNameTecnologia(String nameTecnologia) {
        this.nameTecnologia = nameTecnologia;
    }

    public Integer getLevelCandidata() {
        return levelCandidata;
    }

    public void setLevelCandidata(Integer levelCandidata) {
        this.levelCandidata = levelCandidata;
    }

    public Boolean getNewInsertion() {
        return newInsertion;
    }

    public void setNewInsertion(Boolean newInsertion) {
        this.newInsertion = newInsertion;
    }
}
