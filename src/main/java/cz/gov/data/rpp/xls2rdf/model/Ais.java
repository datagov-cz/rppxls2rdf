package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;

@OWLClass(iri = Vocabulary.s_c_agendovy_informacni_system) public class Ais {

    @Id private String id;

    @OWLDataProperty(iri = Vocabulary.s_c_agendovy_informacni_system + "/" + "ma-kod-ais")
    private String kod;
    @OWLDataProperty(iri = Vocabulary.s_c_agendovy_informacni_system + "/" + "ma-nazev-ais")
    private String nazev;
    @OWLDataProperty(iri = Vocabulary.s_c_agendovy_informacni_system + "/" + "ma-spravce-ais")
    private String spravce;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getSpravce() {
        return spravce;
    }

    public void setSpravce(String spravce) {
        this.spravce = spravce;
    }
}
