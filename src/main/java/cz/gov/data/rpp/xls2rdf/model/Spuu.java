package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;

@OWLClass(iri = Vocabulary.s_c_soukromopravni_uzivatel_udaju) public class Spuu extends Agent {

    @OWLDataProperty(
        iri = Vocabulary.s_c_soukromopravni_uzivatel_udaju + "/" + "ma-identifikaci-spuu")
    private String identifikace;

    @OWLDataProperty(iri = Vocabulary.s_c_soukromopravni_uzivatel_udaju + "/" + "ma-nazev-spuu")
    private String nazev;

    public String getIdentifikace() {
        return identifikace;
    }

    public void setIdentifikace(String identifikaceSpuu) {
        this.identifikace = identifikaceSpuu;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }
}
