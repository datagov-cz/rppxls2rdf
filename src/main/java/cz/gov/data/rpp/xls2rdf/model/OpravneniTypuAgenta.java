package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.CascadeType;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;

@OWLClass(iri = Vocabulary.s_c_opravneni_typu_agenta) public class OpravneniTypuAgenta {

    @Id private String id;

    @OWLObjectProperty(iri = Vocabulary.s_c_opravneni_typu_agenta + "/" + "ma-typ-agenta",
                       cascade = CascadeType.MERGE) private TypAgenta typAgenta;

    @OWLObjectProperty(iri = Vocabulary.s_c_opravneni_typu_agenta + "/" + "ma-opravneni",
                       cascade = CascadeType.MERGE) private OpravneniKUdajum opravneniKUdajum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TypAgenta getTypAgenta() {
        return typAgenta;
    }

    public void setTypAgenta(TypAgenta typAgenta) {
        this.typAgenta = typAgenta;
    }

    public OpravneniKUdajum getOpravneniKUdajum() {
        return opravneniKUdajum;
    }

    public void setOpravneniKUdajum(OpravneniKUdajum opravneniKUdajum) {
        this.opravneniKUdajum = opravneniKUdajum;
    }
}
