package br.com.tfgc.candidata.service;

import br.com.tfgc.candidata.domain.CandidataDomain;
import br.com.tfgc.candidata.exception.CandidataNotFoundException;
import br.com.tfgc.candidata.interfaces.json.Candidata;
import br.com.tfgc.candidata.interfaces.json.Nivel;
import br.com.tfgc.candidata.specification.filter.CandidataFilter;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CandidataService {

    /**
     * Responsável por salvar a candidata.
     * @param candidata {@link Candidata} a ser salva.
     * @return Entidade Candidata.
     */
    CandidataDomain createCandidata(@Valid Candidata candidata);

    /**
     * Responsável por buscar Candidatas de acordo com um filtro.
     * @param candidataFilter Filtro de busca.
     * @return Candidatas filtradas.
     */
    Page<CandidataDomain> findByFilter(CandidataFilter candidataFilter, Pageable pageable);

    /**
     * Responsável por buscar determinada Candidata de acordo com seu Id.
     * @param candidataId id da Candidata a ser retornada.
     * @return uma entidade de Candidata.
     * @throws CandidataNotFoundException caso a Candidata nao seja encontrada.
     */
    CandidataDomain getById(UUID candidataId) throws CandidataNotFoundException;

    /**
     * Responsável por atualizar determinada Candidata de acordo com seu Id.
     * @param candidataDomain {@link CandidataDomain} a ser atualizada.
     * @throws CandidataNotFoundException caso a Candidata nao seja encontrada.
     */
    void updateCandidata(CandidataDomain candidataDomain);

    /**
     *  Responsável por salvaruma lista de objetos Conhecimento de uma candidata.
     * @param conhecimentoList lista de objetos de Conhecimento.
     * @param candidataId id da Candidata
     * @throws CandidataNotFoundException caso a Candidata nao seja encontrada.
     */
    void saveConhecimento(UUID candidataId, List<Conhecimento> conhecimentoList) throws CandidataNotFoundException;
}
