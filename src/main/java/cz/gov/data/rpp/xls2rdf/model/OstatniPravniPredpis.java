package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;

@OWLClass(iri = Vocabulary.s_c_ostatni_pravni_predpis) public class OstatniPravniPredpis
    extends PravniPredpis {

    // nazev
    // ostatniPravniPredpisyAJejichUstanoveni
    @OWLDataProperty(iri = Vocabulary.s_c_ostatni_pravni_predpis + "/" + "ma-nazev") private String
        nazev;

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }
}
