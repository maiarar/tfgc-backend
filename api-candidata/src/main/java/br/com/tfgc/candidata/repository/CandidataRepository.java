package br.com.tfgc.candidata.repository;

import br.com.tfgc.candidata.domain.CandidataDomain;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface CandidataRepository extends
    CrudRepository<CandidataDomain, UUID>,
    PagingAndSortingRepository<CandidataDomain, UUID>,
    JpaSpecificationExecutor<CandidataDomain>{

}
