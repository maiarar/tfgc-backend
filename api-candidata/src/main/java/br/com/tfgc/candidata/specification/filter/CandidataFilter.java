package br.com.tfgc.candidata.specification.filter;

/**
 * Filtro de busca para Candidata.
 */
public class CandidataFilter {

    private String stringToSearch;

    public CandidataFilter() {
        this.stringToSearch = "";
    }

    public CandidataFilter(String stringToSearch) {
        this.stringToSearch = stringToSearch;
    }

    public String getStringToSearch() {
        return stringToSearch;
    }

    public void setStringToSearch(String stringToSearch) {
        this.stringToSearch = stringToSearch;
    }
}
