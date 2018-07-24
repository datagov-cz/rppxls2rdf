package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.CascadeType;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import java.util.HashSet;
import java.util.Set;

@OWLClass(iri = Vocabulary.s_c_pozadavek_na_pristup_k_agende)
public class PozadavekNaPristupKAgende {

    @Id private String id;

    @OWLObjectProperty(
        iri = Vocabulary.s_c_pozadavek_na_pristup_k_agende + "/" + "ma-agendu-poskytujici-udaje",
        cascade = CascadeType.MERGE) private Agenda agendaPoskytujiciUdaje;

    @OWLObjectProperty(
        iri = Vocabulary.s_c_pozadavek_na_pristup_k_agende + "/" + "ma-agendovy-informacni-system",
        cascade = CascadeType.MERGE) private Set<Ais> aisy = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Agenda getAgendaPoskytujiciUdaje() {
        return agendaPoskytujiciUdaje;
    }

    public void setAgendaPoskytujiciUdaje(Agenda agendaPoskytujiciUdaje) {
        this.agendaPoskytujiciUdaje = agendaPoskytujiciUdaje;
    }

    public Set<Ais> getAisy() {
        return aisy;
    }

    public void setAisy(Set<Ais> aisy) {
        this.aisy = aisy;
    }
}
