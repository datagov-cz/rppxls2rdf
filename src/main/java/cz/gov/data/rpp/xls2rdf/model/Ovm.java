package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;

@OWLClass(iri = Vocabulary.s_c_organ_verejne_moci) public class Ovm extends Agent {

    // identifikaceOvm
    @OWLDataProperty(iri = Vocabulary.s_c_organ_verejne_moci + "/" + "ma-identifikaci-ovm")
    private String identifikace;

    @OWLDataProperty(iri = Vocabulary.s_c_organ_verejne_moci + "/" + "ma-nazev-ovm") private String
        nazev;

    public String getIdentifikace() {
        return identifikace;
    }

    public void setIdentifikace(String identifikaceOvm) {
        this.identifikace = identifikaceOvm;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazevOvm) {
        this.nazev = nazevOvm;
    }

    @Override public String toString() {
        return "Ovm{" + "id='" + getId() + '\'' + ", identifikaceOvm='" + identifikace + '\''
               + ", nazevOvm='" + nazev + '\'' + '}';
    }
}
