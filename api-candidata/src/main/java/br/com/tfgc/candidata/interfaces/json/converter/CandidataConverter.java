package br.com.tfgc.candidata.interfaces.json.converter;

import br.com.tfgc.candidata.domain.CandidataDomain;
import br.com.tfgc.candidata.interfaces.json.Candidata;
import br.com.tfgc.candidata.specification.filter.CandidataFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Page;

/**
 * Converte entidades para objetos JSON - e vice-e-versa.
 */
public class CandidataConverter {

    /**
     * Converte um objeto JSON para uma Entidade.
     * @param candidataInJSON {@link Candidata} a ser convertida para uma {@link CandidataDomain}.
     * @return uma nova entidade de Candidata.
     */
    public static CandidataDomain toCandidataDomain(Candidata candidataInJSON) {
        CandidataDomain candidataDomain = new CandidataDomain();

        candidataDomain.setBirthday(candidataInJSON.getBirthday());
        candidataDomain.setCpf(candidataInJSON.getCpf());
        candidataDomain.setEmail(candidataInJSON.getEmail());
        candidataDomain.setExperience(candidataInJSON.getExperience());
        candidataDomain.setName(candidataInJSON.getName());
        candidataDomain.setOccupation(candidataInJSON.getOccupation());

        return candidataDomain;
    }

    /**
     * Converte uma String de busca para um objeto Filter.
     * @param searchString String de busca.
     * @return objeto Filtro.
     */
    public static CandidataFilter toCandidataDomainFilter(String searchString) {
        return new CandidataFilter(searchString);
    }

    /**
     * Converte uma lista de Entidades para uma lista de objetos JSON.
     * @param candidataDomains lista de {@link CandidataDomain} a ser convertida para uma lista 
     * de {@link Candidata}.
     * @return uma nova lista de objetos JSON de Candidata.
     */
    public static List<Candidata> toCandidata(Page<CandidataDomain> candidataDomains) {
        if(Objects.isNull(candidataDomains) || candidataDomains.getTotalElements() < 1){
            return new ArrayList<>();
        }

        List<Candidata> candidatas = new ArrayList<>();

        candidataDomains.forEach(candidataDomain -> candidatas.add(toCandidata(candidataDomain)));

        return candidatas;
    }

    /**
     * Converte uma Entidade para um objeto JSON.
     * @param candidataDomain {@link CandidataDomain} a ser convertida para uma {@link Candidata}.
     * @return um novo objeto JSON de Candidata.
     */
    public static Candidata toCandidata(CandidataDomain candidataDomain) {
        if(Objects.isNull(candidataDomain)){
            return null;
        }
        return new Candidata(candidataDomain.getName(),
            candidataDomain.getBirthday(),
            candidataDomain.getOccupation(),
            candidataDomain.getExperience(),
            candidataDomain.getEmail(),
            candidataDomain.getCpf());

    }
}
