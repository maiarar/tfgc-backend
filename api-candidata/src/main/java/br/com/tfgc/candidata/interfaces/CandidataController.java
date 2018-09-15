package br.com.tfgc.candidata.interfaces;


import br.com.tfgc.candidata.domain.CandidataDomain;
import br.com.tfgc.candidata.exception.CandidataNotFoundException;
import br.com.tfgc.candidata.interfaces.json.Candidata;
import br.com.tfgc.candidata.interfaces.json.converter.CandidataConverter;
import br.com.tfgc.candidata.interfaces.validator.CandidataValidator;
import br.com.tfgc.candidata.service.CandidataService;
import br.com.tfgc.candidata.specification.filter.CandidataFilter;
import java.net.URI;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controller do microsserviço de Candidata.
 */
@RestController
@RequestMapping(path = "/candidatas")
public class CandidataController {

    private final CandidataService candidataService;

    //necessário para logar os erros
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @param candidataService {@link CandidataService a ser atualizada}.
     */
    @Autowired
    public CandidataController(CandidataService candidataService){
        this.candidataService = candidataService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCandidata(
        @Valid @RequestBody Candidata candidata,
        UriComponentsBuilder uriBuilder
    ){
        List<Error> errorsInCandidata = CandidataValidator.validateCandidata(candidata);

        if(Objects.nonNull(errorsInCandidata) && !errorsInCandidata.isEmpty()){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(errorsInCandidata);
        }

        CandidataDomain candidataDomainSaved;

        try {
            candidataDomainSaved = this.candidataService.createCandidata(candidata);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Error(e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
            .location(URI.create(
                uriBuilder.path("/candidatas/{"+candidataDomainSaved.getId()+"}")
                    .toUriString())).build();
    }

    /**
     * Retorna todos os registros de Candidata.
     * @param _offset registro inicial a ser retornado.
     * @param _limit quantidade limite de registros retornados.
     * @param searchString texto de busca para filtrar os registros.
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getCandidatas(
        @RequestParam(name = "_offset") Integer _offset,
        @RequestParam(name = "_limit") Integer _limit,
        @RequestParam(required = false, name = "searchString") String searchString
    ){

        List<Error> errorsInParameters = CandidataValidator.validateRequestParams(_offset, _limit, searchString);

        if(Objects.nonNull(errorsInParameters) && !errorsInParameters.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorsInParameters);
        }

        CandidataFilter candidataFilter = CandidataConverter.toCandidataDomainFilter(searchString);

        Page<CandidataDomain> candidataDomains
            = this.candidataService.findByFilter(candidataFilter, new PageRequest(_offset -1, _limit));

        return PageableResponse.
            create(CandidataConverter.toCandidata(candidataDomains)).
            contentPages(candidataDomains.getTotalPages()).
            contentRange(candidataDomains.getTotalElements()).
            build();
    }

    @RequestMapping(path = "/{candidataId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCandidataId(@PathVariable(name = "candidataId") UUID candidataId) {

        try {
            return new ResponseEntity<>(CandidataConverter.toCandidata(
                this.candidataService.getById(candidataId)), HttpStatus.OK);

        } catch (CandidataNotFoundException | NullPointerException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e);
        }

    }

    @RequestMapping(path = "/{candidataId}", method = RequestMethod.PUT)
    ResponseEntity<?> putCandidata(
        @PathVariable("candidataId") UUID candidataId, @Valid @RequestBody Candidata newCandidata) {

        try {

            List<Error> errors = CandidataValidator.validateCandidata(newCandidata);
            if (!CollectionUtils.isEmpty(errors)) {
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(errors);
            }

            CandidataDomain newCandidataDomain = CandidataConverter.toCandidataDomain(newCandidata);
            CandidataDomain candidataSaved = candidataService.getById(candidataId);

            newCandidataDomain.setId(candidataSaved.getId());

            candidataService.updateCandidata(newCandidataDomain);

        } catch (CandidataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
