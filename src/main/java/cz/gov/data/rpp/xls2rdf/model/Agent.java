package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.gov.data.rpp.xls2rdf.Vocabulary;

@OWLClass(iri = Vocabulary.s_c_agent) public class Agent {

    @Id private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
