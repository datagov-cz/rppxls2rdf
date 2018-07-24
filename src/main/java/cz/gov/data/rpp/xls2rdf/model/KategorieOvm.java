package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;

@OWLClass(iri = Vocabulary.s_c_kategorie_organu_verejne_moci) public class KategorieOvm
    extends TypAgenta {

    @OWLDataProperty(
        iri = Vocabulary.s_c_kategorie_organu_verejne_moci + "/" + "ma-identifikaci-kategorie-ovm")
    private String identifikace;

    @OWLDataProperty(
        iri = Vocabulary.s_c_kategorie_organu_verejne_moci + "/" + "ma-nazev-kategorie-ovm")
    private String nazev;

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

    @Override public String toString() {
        return "KategorieOvm{" + "identifikace='" + identifikace + '\'' + ", nazev='" + nazev + '\''
               + '}';
    }
}
