package cz.gov.data.rpp.xls2rdf.model;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jopa.model.annotations.OWLDataProperty;
import cz.gov.data.rpp.xls2rdf.Vocabulary;

@OWLClass(iri = Vocabulary.s_c_pravni_predpis_ze_sbirky_zakonu)
public class PravniPredpisZeSbirkyZakonu extends PravniPredpis {

    // cislo, rokVydani, paragraf, odstavec, pismeno
    @OWLDataProperty(iri = Vocabulary.s_c_pravni_predpis_ze_sbirky_zakonu + "/" + "ma-cislo")
    private String cislo;

    @OWLDataProperty(iri = Vocabulary.s_c_pravni_predpis_ze_sbirky_zakonu + "/" + "ma-rok-vydani")
    private String rokVydani;

    @OWLDataProperty(iri = Vocabulary.s_c_pravni_predpis_ze_sbirky_zakonu + "/" + "ma-nazev")
    private String nazev;

    @OWLDataProperty(iri = Vocabulary.s_c_pravni_predpis_ze_sbirky_zakonu + "/" + "ma-paragraf")
    private String paragraf;

    @OWLDataProperty(iri = Vocabulary.s_c_pravni_predpis_ze_sbirky_zakonu + "/" + "ma-odstavec")
    private String odstavec;

    @OWLDataProperty(iri = Vocabulary.s_c_pravni_predpis_ze_sbirky_zakonu + "/" + "ma-pismeno")
    private String pismeno;

    public String getCislo() {
        return cislo;
    }

    public void setCislo(String cislo) {
        this.cislo = cislo;
    }

    public String getRokVydani() {
        return rokVydani;
    }

    public void setRokVydani(String rokVydani) {
        this.rokVydani = rokVydani;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getParagraf() {
        return paragraf;
    }

    public void setParagraf(String paragraf) {
        this.paragraf = paragraf;
    }

    public String getOdstavec() {
        return odstavec;
    }

    public void setOdstavec(String odstavec) {
        this.odstavec = odstavec;
    }

    public String getPismeno() {
        return pismeno;
    }

    public void setPismeno(String pismeno) {
        this.pismeno = pismeno;
    }

    @Override public String toString() {
        return "PravniPredpisZeSbirkyZakonu{" + "cislo='" + cislo + '\'' + ", rokVydani='"
               + rokVydani + '\'' + ", nazev='" + nazev + '\'' + ", paragraf='" + paragraf + '\''
               + ", odstavec='" + odstavec + '\'' + ", pismeno='" + pismeno + '\'' + '}';
    }
}
