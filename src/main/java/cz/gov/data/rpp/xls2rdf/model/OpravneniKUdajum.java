package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.CascadeType;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import java.util.HashSet;
import java.util.Set;

@OWLClass(iri = Vocabulary.s_c_opravneni_k_udajum) public class OpravneniKUdajum {
    @Id private String id;

    @OWLDataProperty(iri = Vocabulary.s_c_opravneni_k_udajum + "/" + "ma-kod-opravneni")
    private String kod;

    @OWLObjectProperty(iri = Vocabulary.s_c_opravneni_k_udajum + "/" + "ma-udaj-ke-cteni",
                       cascade = CascadeType.MERGE) private Set<Udaj> udajeKeCteni =
        new HashSet<>();

    @OWLObjectProperty(iri = Vocabulary.s_c_opravneni_k_udajum + "/" + "ma-udaj-k-zapisu",
                       cascade = CascadeType.MERGE) private Set<Udaj> udajeKZapisu =
        new HashSet<>();

    @OWLObjectProperty(iri = Vocabulary.s_c_opravneni_k_udajum + "/" + "ma-definujici-predpis",
                       cascade = CascadeType.MERGE) private Set<PravniPredpisZeSbirkyZakonu>
        definujiciPredpis = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Udaj> getUdajeKeCteni() {
        return udajeKeCteni;
    }

    public void setUdajeKeCteni(Set<Udaj> udajeKeCteni) {
        this.udajeKeCteni = udajeKeCteni;
    }

    public Set<Udaj> getUdajeKZapisu() {
        return udajeKZapisu;
    }

    public void setUdajeKZapisu(Set<Udaj> udajeKZapisu) {
        this.udajeKZapisu = udajeKZapisu;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public Set<PravniPredpisZeSbirkyZakonu> getDefinujiciPredpis() {
        return definujiciPredpis;
    }

    public void setDefinujiciPredpis(Set<PravniPredpisZeSbirkyZakonu> definujiciPredpis) {
        this.definujiciPredpis = definujiciPredpis;
    }
}
