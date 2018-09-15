package br.com.tfgc.candidata.service.impl;

import br.com.tfgc.candidata.domain.CandidataDomain;
import br.com.tfgc.candidata.exception.CandidataNotFoundException;
import br.com.tfgc.candidata.interfaces.Messages;
import br.com.tfgc.candidata.interfaces.json.Candidata;
import br.com.tfgc.candidata.interfaces.json.Nivel;
import br.com.tfgc.candidata.interfaces.json.converter.CandidataConverter;
import br.com.tfgc.candidata.repository.CandidataRepository;
import br.com.tfgc.candidata.service.CandidataService;
import br.com.tfgc.candidata.specification.CandidataSpecification;
import br.com.tfgc.candidata.specification.filter.CandidataFilter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CandidataServiceImpl implements CandidataService{

    /**
     * Repository para Candidata.
     */
    @Autowired
    private CandidataRepository candidataRepository;

    public CandidataDomain createCandidata(@Valid Candidata candidataToSave) {
        CandidataDomain candidataDomain = CandidataConverter.toCandidataDomain(candidataToSave);
        return this.candidataRepository.save(candidataDomain);
    }

    public Page<CandidataDomain> findByFilter(CandidataFilter candidataFilter,
        Pageable pageable) {

        return this.candidataRepository.findAll(CandidataSpecification.candidataByFilter(candidataFilter), pageable);
    }

    public CandidataDomain getById(UUID candidataId) throws CandidataNotFoundException {

        Optional<CandidataDomain> candidataDomain = this.candidataRepository.findById(candidataId);

        if (!candidataDomain.isPresent()) {
            throw new CandidataNotFoundException(Messages.CANDIDATA_NOT_FOUND);
        }

        return candidataDomain.get(); 
    }

    public void updateCandidata(CandidataDomain candidataDomain) {
        this.candidataRepository.save(candidataDomain);
    }


}
