package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.CascadeType;
import cz.cvut.kbss.jopa.model.annotations.Id;
import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.cvut.kbss.jopa.model.annotations.OWLObjectProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@OWLClass(iri = Vocabulary.s_c_agenda) public class Agenda {

    // kodAgendy
    @Id private String id;

    @OWLDataProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-kod-agendy") private String kod;
    @OWLDataProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-nazev-agendy") private String nazev;
    @OWLDataProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-platnost-agendy-od") private Date
        platnostOd;
    @OWLDataProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-platnost-agendy-do") private String
        platnostDo;
    @OWLDataProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-datum-vzniku") private Date
        datumVzniku;
    @OWLDataProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-datum-zaniku") private String
        datumZaniku;

    @OWLObjectProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-ohlasovatele-agendy",
                       cascade = CascadeType.MERGE) private Ovm ohlasovatel;

    @OWLObjectProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-vykonavatele",
                       cascade = CascadeType.MERGE) private Set<Agent> vykonavatele =
        new HashSet<>();

    @OWLObjectProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-kategorii-vykonavatele",
                       cascade = CascadeType.MERGE) private Set<TypAgenta> kategorieVykonavatele =
        new HashSet<>();

    @OWLObjectProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-pravni-predpis",
                       cascade = CascadeType.MERGE) private Set<PravniPredpis> pravniPredpisy =
        new HashSet<>();

    @OWLObjectProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-hlavni-pravni-predpis",
                       cascade = CascadeType.MERGE) private Set<PravniPredpis>
        hlavniPravniPredpisy = new HashSet<>();

    @OWLObjectProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-pozadavek-na-pristup-k-agende",
                       cascade = CascadeType.MERGE) private Set<PozadavekNaPristupKAgende>
        pozadavkyNaPristupKAgendam = new HashSet<>();

    @OWLObjectProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-opravneni-k-udajum",
                       cascade = CascadeType.MERGE) private Set<OpravneniKUdajum> opravneni =
        new HashSet<>();

    @OWLObjectProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-cinnost",
                       cascade = CascadeType.MERGE) private Set<Cinnost> cinnosti = new HashSet<>();

    @OWLObjectProperty(iri = Vocabulary.s_c_agenda + "/" + "ma-objekt", cascade = CascadeType.MERGE)
    private Set<Objekt> objekty = new HashSet<>();

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

    public Date getPlatnostOd() {
        return platnostOd;
    }

    public void setPlatnostOd(Date platnostOd) {
        this.platnostOd = platnostOd;
    }

    public String getPlatnostDo() {
        return platnostDo;
    }

    public void setPlatnostDo(String platnostDo) {
        this.platnostDo = platnostDo;
    }

    public Date getDatumVzniku() {
        return datumVzniku;
    }

    public void setDatumVzniku(Date datumVzniku) {
        this.datumVzniku = datumVzniku;
    }

    public String getDatumZaniku() {
        return datumZaniku;
    }

    public void setDatumZaniku(String datumZaniku) {
        this.datumZaniku = datumZaniku;
    }

    public Ovm getOhlasovatel() {
        return ohlasovatel;
    }

    public void setOhlasovatel(Ovm ohlasovatel) {
        this.ohlasovatel = ohlasovatel;
    }

    public Set<PravniPredpis> getHlavniPravniPredpisy() {
        return hlavniPravniPredpisy;
    }

    public void setHlavniPravniPredpisy(Set<PravniPredpis> hlavniPravniPredpisyZeSbirkyZakonu) {
        this.hlavniPravniPredpisy = hlavniPravniPredpisyZeSbirkyZakonu;
    }

    public Set<Agent> getVykonavatele() {
        return vykonavatele;
    }

    public void setVykonavatele(Set<Agent> vykonavatele) {
        this.vykonavatele = vykonavatele;
    }

    public Set<TypAgenta> getKategorieVykonavatele() {
        return kategorieVykonavatele;
    }

    public void setKategorieVykonavatele(Set<TypAgenta> kategorieVykonavatele) {
        this.kategorieVykonavatele = kategorieVykonavatele;
    }

    public Set<PravniPredpis> getPravniPredpisy() {
        return pravniPredpisy;
    }

    public void setPravniPredpisy(Set<PravniPredpis> pravniPredpisy) {
        this.pravniPredpisy = pravniPredpisy;
    }

    public Set<Cinnost> getCinnosti() {
        return cinnosti;
    }

    public void setCinnosti(Set<Cinnost> cinnosti) {
        this.cinnosti = cinnosti;
    }

    public Set<Objekt> getObjekty() {
        return objekty;
    }

    public void setObjekty(Set<Objekt> objekty) {
        this.objekty = objekty;
    }

    public Set<PozadavekNaPristupKAgende> getPozadavkyNaPristupKAgendam() {
        return pozadavkyNaPristupKAgendam;
    }

    public void setPozadavkyNaPristupKAgendam(
        Set<PozadavekNaPristupKAgende> pozadavkyNaPristupKAgendam) {
        this.pozadavkyNaPristupKAgendam = pozadavkyNaPristupKAgendam;
    }

    public Set<OpravneniKUdajum> getOpravneni() {
        return opravneni;
    }

    public void setOpravneni(Set<OpravneniKUdajum> opravneni) {
        this.opravneni = opravneni;
    }
}
