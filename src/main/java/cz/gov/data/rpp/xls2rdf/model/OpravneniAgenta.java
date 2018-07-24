package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.CascadeType;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;

@OWLClass(iri = Vocabulary.s_c_opravneni_agenta) public class OpravneniAgenta {
    // kodCinnosti
    @Id private String id;

    @OWLObjectProperty(iri = Vocabulary.s_c_opravneni_agenta + "/" + "ma-agenta",
                       cascade = CascadeType.MERGE) private Agent agent;

    @OWLObjectProperty(iri = Vocabulary.s_c_opravneni_agenta + "/" + "ma-opravneni",
                       cascade = CascadeType.MERGE) private OpravneniKUdajum opravneniKUdajum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public OpravneniKUdajum getOpravneniKUdajum() {
        return opravneniKUdajum;
    }

    public void setOpravneniKUdajum(OpravneniKUdajum opravneniKUdajum) {
        this.opravneniKUdajum = opravneniKUdajum;
    }
}
