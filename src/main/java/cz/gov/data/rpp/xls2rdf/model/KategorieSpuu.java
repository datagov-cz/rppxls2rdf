package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;

@OWLClass(iri = Vocabulary.s_c_kategorie_soukromopravniho_uzivatele_udaju)
public class KategorieSpuu extends TypAgenta {

    @OWLDataProperty(iri = Vocabulary.s_c_kategorie_soukromopravniho_uzivatele_udaju + "/"
                           + "ma-identifikaci-kategorie-spuu") private String identifikace;

    @OWLDataProperty(iri = Vocabulary.s_c_kategorie_soukromopravniho_uzivatele_udaju + "/"
                           + "ma-nazev-kategorie-spuu") private String nazev;

    public String getIdentifikace() {
        return identifikace;
    }

    public void setIdentifikace(String identifikace) {
        this.identifikace = identifikace;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

}
