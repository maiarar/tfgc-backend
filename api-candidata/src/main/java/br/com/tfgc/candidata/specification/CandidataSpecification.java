package br.com.tfgc.candidata.specification;

import br.com.tfgc.candidata.domain.CandidataDomain;
import br.com.tfgc.candidata.specification.filter.CandidataFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 * Specification necessária para as buscas de Candidatas.
 */
public class CandidataSpecification {

    public static Specification<CandidataDomain> candidataByFilter(CandidataFilter candidataFilter) {
        return (Root<CandidataDomain> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(Objects.nonNull(candidataFilter.getStringToSearch())){
                predicates.add(criteriaBuilder.equal(root.get("name"), candidataFilter.getStringToSearch()));
                predicates.add(criteriaBuilder.equal(root.get("experience"), candidataFilter.getStringToSearch()));
                predicates.add(criteriaBuilder.equal(root.get("ocupation"), candidataFilter.getStringToSearch()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
    
}
