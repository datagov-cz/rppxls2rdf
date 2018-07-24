package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.CascadeType;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import java.util.HashSet;
import java.util.Set;

@OWLClass(iri = Vocabulary.s_c_objekt) public class Objekt {

    @Id private String id;

    @OWLDataProperty(iri = Vocabulary.s_c_objekt + "/" + "ma-kod-objektu") private String kod;

    @OWLDataProperty(iri = Vocabulary.s_c_objekt + "/" + "ma-nazev-objektu") private String nazev;

    @OWLDataProperty(iri = Vocabulary.s_c_objekt + "/" + "ma-popis-objektu") private String popis;

    @OWLObjectProperty(iri = Vocabulary.s_c_objekt + "/" + "ma-pravni-predpis",
                       cascade = CascadeType.MERGE) private Set<PravniPredpisZeSbirkyZakonu>
        pravniPredpisy = new HashSet<>();

    @OWLObjectProperty(iri = Vocabulary.s_c_objekt + "/" + "ma-udaj", cascade = CascadeType.MERGE)
    private Set<Udaj> udaje = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public Set<Udaj> getUdaje() {
        return udaje;
    }

    public void setUdaje(Set<Udaj> udaje) {
        this.udaje = udaje;
    }

    public Set<PravniPredpisZeSbirkyZakonu> getPravniPredpisy() {
        return pravniPredpisy;
    }

    public void setPravniPredpisy(Set<PravniPredpisZeSbirkyZakonu> pravniPredpisy) {
        this.pravniPredpisy = pravniPredpisy;
    }
}
