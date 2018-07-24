package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.CascadeType;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import java.util.HashSet;
import java.util.Set;

@OWLClass(iri = Vocabulary.s_c_cinnost) public class Cinnost {
    // kodCinnosti
    @Id private String id;

    @OWLDataProperty(iri = Vocabulary.s_c_cinnost + "/" + "ma-kod-cinnosti") private String kod;
    @OWLDataProperty(iri = Vocabulary.s_c_cinnost + "/" + "ma-nazev-cinnosti") private String nazev;
    @OWLDataProperty(iri = Vocabulary.s_c_cinnost + "/" + "ma-popis-cinnosti") private String popis;
    @OWLDataProperty(iri = Vocabulary.s_c_cinnost + "/" + "ma-platnost-cinnosti-od") private String
        platnostOd;
    @OWLDataProperty(iri = Vocabulary.s_c_cinnost + "/" + "ma-platnost-cinnosti-do") private String
        platnostDo;
    @OWLDataProperty(iri = Vocabulary.s_c_cinnost + "/" + "ma-typ-cinnosti") private String
        typCinnosti;

    @OWLObjectProperty(iri = Vocabulary.s_c_cinnost + "/" + "ma-agenta-v-roli",
                       cascade = CascadeType.MERGE) private Set<Agent> agentiVRoli =
        new HashSet<>();

    @OWLObjectProperty(iri = Vocabulary.s_c_cinnost + "/" + "ma-kategorii-agenta-v-roli",
                       cascade = CascadeType.MERGE) private Set<TypAgenta> kategorieAgentuVRoli =
        new HashSet<>();

    @OWLObjectProperty(iri = Vocabulary.s_c_cinnost + "/" + "ma-opravneni-agenta-v-roli",
                       cascade = CascadeType.MERGE) private Set<OpravneniAgenta> opravneniAgenta =
        new HashSet<>();

    @OWLObjectProperty(iri = Vocabulary.s_c_cinnost + "/" + "ma-opravneni-kategorie-agenta-v-roli",
                       cascade = CascadeType.MERGE) private Set<OpravneniTypuAgenta>
        opravneniKategorieAgentu = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kodCinnosti) {
        this.kod = kodCinnosti;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazevCinnosti) {
        this.nazev = nazevCinnosti;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popisCinnosti) {
        this.popis = popisCinnosti;
    }

    public String getPlatnostOd() {
        return platnostOd;
    }

    public void setPlatnostOd(String platnostCinnostiOd) {
        this.platnostOd = platnostCinnostiOd;
    }

    public String getPlatnostDo() {
        return platnostDo;
    }

    public void setPlatnostDo(String platnostCinnostiDo) {
        this.platnostDo = platnostCinnostiDo;
    }

    public String getTypCinnosti() {
        return typCinnosti;
    }

    public void setTypCinnosti(String typCinnosti) {
        this.typCinnosti = typCinnosti;
    }

    public Set<Agent> getAgentiVRoli() {
        return agentiVRoli;
    }

    public void setAgentiVRoli(Set<Agent> agentiVRoli) {
        this.agentiVRoli = agentiVRoli;
    }

    public Set<TypAgenta> getKategorieAgentuVRoli() {
        return kategorieAgentuVRoli;
    }

    public void setKategorieAgentuVRoli(Set<TypAgenta> kategorieAgentuVRoli) {
        this.kategorieAgentuVRoli = kategorieAgentuVRoli;
    }

    public Set<OpravneniAgenta> getOpravneniAgenta() {
        return opravneniAgenta;
    }

    public void setOpravneniAgenta(Set<OpravneniAgenta> opravneniAgenta) {
        this.opravneniAgenta = opravneniAgenta;
    }

    public Set<OpravneniTypuAgenta> getOpravneniKategorieAgentu() {
        return opravneniKategorieAgentu;
    }

    public void setOpravneniKategorieAgentu(Set<OpravneniTypuAgenta> opravneniKategorieAgentu) {
        this.opravneniKategorieAgentu = opravneniKategorieAgentu;
    }
}
