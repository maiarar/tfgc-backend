package br.com.tfgc.candidata.interfaces.validator;

import br.com.tfgc.candidata.interfaces.Messages;
import br.com.tfgc.candidata.interfaces.json.Candidata;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Classe que valida os campos da Candidata (JSON).
 */
public class CandidataValidator {

    public static final int CPF_SIZE = 11;
    public static final Pattern REGEX_VALIDATE_EMAIL =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * Valida os campos da Candiata.
     * @param candidata {@link Candidata} a ser validada.
     * @return lista de erros.
     */
    public static List<Error> validateCandidata(Candidata candidata) {
        List<Error> errorsInCandidata = new ArrayList<>();

        if(StringUtils.isBlank(candidata.getCpf())){
            errorsInCandidata.add(new Error(Messages.CPF_NOT_INFORMED));
        }else{
            if(!NumberUtils.isDigits(candidata.getCpf())){
                errorsInCandidata.add(new Error(Messages.CPF_WITH_NON_NUMERIC_CHARACTERS));
            }
            if(candidata.getCpf().length()!= CPF_SIZE){
                errorsInCandidata.add(new Error(Messages.CPF_WITH_WRONG_SIZE));
            }
        }

        if(StringUtils.isBlank(candidata.getEmail())){
            errorsInCandidata.add(new Error(Messages.EMAIL_NOT_INFORMED));
        }else{
            String email = candidata.getEmail();
            Matcher matcher = REGEX_VALIDATE_EMAIL.matcher(email);
            if(!matcher.find()) {
                errorsInCandidata.add(new Error(Messages.INVALID_EMAIL_FORMAT));
            }
        }

        return errorsInCandidata;
    }

    /**
     * Valida os parâmetros de busca.
     * @return lista de erros.
     */
    public static List<Error> validateRequestParams(Integer offset, Integer limit,
        String searchString) {
        List<Error> errorsInParameters = new ArrayList<>();

        if(Objects.isNull(offset)||offset<1){
            errorsInParameters.add(new Error(Messages.OFFSET_WITH_INVALID_PARAMETER));
        }
        if(Objects.isNull(limit)||limit<1){
            errorsInParameters.add(new Error(Messages.LIMIT_WITH_INVALID_PARAMETER));
        }
        if(Objects.isNull(searchString)){
            errorsInParameters.add(new Error(Messages.SEARCH_STRING_WITH_INVALID_PARAMETER));
        }
        return errorsInParameters;
    }
}
