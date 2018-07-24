package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.CascadeType;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import java.util.HashSet;
import java.util.Set;

@OWLClass(iri = Vocabulary.s_c_udaj) public class Udaj {

    @Id private String id;

    @OWLDataProperty(iri = Vocabulary.s_c_udaj + "/" + "ma-kod") private String kod;

    @OWLDataProperty(iri = Vocabulary.s_c_udaj + "/" + "ma-nazev") private String nazev;

    @OWLDataProperty(iri = Vocabulary.s_c_udaj + "/" + "ma-popis") private String popis;

    @OWLDataProperty(iri = Vocabulary.s_c_udaj + "/" + "ma-typ") private String typ;

    @OWLDataProperty(iri = Vocabulary.s_c_udaj + "/" + "je-verejny") private Boolean verejny;

    @OWLObjectProperty(iri = Vocabulary.s_c_udaj + "/" + "ma-pravni-predpis-ze-sbirky-zakonu",
                       cascade = CascadeType.MERGE) private Set<PravniPredpisZeSbirkyZakonu>
        pravniPredpisy = new HashSet<>();

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

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Set<PravniPredpisZeSbirkyZakonu> getPravniPredpisy() {
        return pravniPredpisy;
    }

    public void setPravniPredpisy(Set<PravniPredpisZeSbirkyZakonu> pravniPredpisy) {
        this.pravniPredpisy = pravniPredpisy;
    }

    public Boolean getVerejny() {
        return verejny;
    }

    public void setVerejny(Boolean verejny) {
        this.verejny = verejny;
    }
}
