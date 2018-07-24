package cz.gov.data.rpp.xls2rdf;

public class Vocabulary {
    public static final String DATA_BASE = "http://data.gov.cz/zdroj/datová-sada/rpp/";

    public static final String BASE = "http://slovnik.gov.cz/datový/rpp/";
    public static final String PREFIX = BASE + "pojem/";

    public static final String AGENDA = "agenda";

    public static final String CINNOST = "cinnost";
    public static final String AGENT = "agent";
    public static final String TYP_AGENTA = "typ-agenta";
    public static final String KATEGORIE_SOUKROMOPRAVNIHO_UZIVATELE_UDAJU =
        "kategorie-soukromopravniho-uzivatele-udaju";
    public static final String KATEGORIE_ORGANU_VEREJNE_MOCI = "kategorie-organu-verejne-moci";
    public static final String AGENDOVY_INFORMACNI_SYSTEM = "agendovy-informacni-system";
    public static final String POZADAVEK_NA_PRISTUP_K_AGENDE = "pozadavek-na-pristup-k-agende";
    public static final String OPRAVNENI_AGENTA = "opravneni-agenta";
    public static final String OPRAVNENI_TYPU_AGENTA = "opravneni-typu-agenta";
    public static final String ORGAN_VEREJNE_MOCI = "organ-verejne-moci";
    public static final String SOUKROMOPRAVNI_UZIVATEL_UDAJU = "soukromopravni-uzivatel-udaju";
    public static final String PRAVNI_PREDPIS = "pravni-predpis";
    public static final String PRAVNI_PREDPIS_ZE_SBIRKY_ZAKONU = "pravni-predpis-ze-sbirky-zakonu";
    public static final String OSTATNI_PRAVNI_PREDPIS = "ostatni-pravni-predpis";
    public static final String OPRAVNENI_K_UDAJU = "opravneni-k-udaju";
    public static final String OBJEKT = "objekt";
    public static final String UDAJ = "udaj";

    public static final String s_c_agenda = PREFIX + AGENDA;
    public static final String s_c_cinnost = PREFIX + CINNOST;
    public static final String s_c_agent = PREFIX + AGENT;
    public static final String s_c_typ_agenta = PREFIX + TYP_AGENTA;
    public static final String s_c_kategorie_soukromopravniho_uzivatele_udaju =
        PREFIX + KATEGORIE_SOUKROMOPRAVNIHO_UZIVATELE_UDAJU;
    public static final String s_c_kategorie_organu_verejne_moci =
        PREFIX + KATEGORIE_ORGANU_VEREJNE_MOCI;
    public static final String s_c_agendovy_informacni_system =
        PREFIX + AGENDOVY_INFORMACNI_SYSTEM;
    public static final String s_c_pozadavek_na_pristup_k_agende =
        PREFIX + POZADAVEK_NA_PRISTUP_K_AGENDE;
    public static final String s_c_opravneni_agenta = PREFIX + OPRAVNENI_AGENTA;
    public static final String s_c_opravneni_typu_agenta = PREFIX + OPRAVNENI_TYPU_AGENTA;

    public static final String s_c_organ_verejne_moci = PREFIX + ORGAN_VEREJNE_MOCI;
    public static final String s_c_soukromopravni_uzivatel_udaju =
        PREFIX + SOUKROMOPRAVNI_UZIVATEL_UDAJU;
    public static final String s_c_pravni_predpis = PREFIX + PRAVNI_PREDPIS;
    public static final String s_c_pravni_predpis_ze_sbirky_zakonu =
        PREFIX + PRAVNI_PREDPIS_ZE_SBIRKY_ZAKONU;
    public static final String s_c_ostatni_pravni_predpis = PREFIX + OSTATNI_PRAVNI_PREDPIS;
    public static final String s_c_opravneni_k_udajum = PREFIX + OPRAVNENI_K_UDAJU;

    public static final String s_c_objekt = PREFIX + OBJEKT;
    public static final String s_c_udaj = PREFIX + UDAJ;

    public static String getClassInstance(final String classLocalName, final String instanceLocalName) {
        return DATA_BASE+classLocalName+"/"+instanceLocalName;
    }
}