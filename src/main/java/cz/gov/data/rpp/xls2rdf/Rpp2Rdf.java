package cz.gov.data.rpp.xls2rdf;

import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.model.JOPAPersistenceProperties;
import cz.cvut.kbss.jopa.model.descriptors.EntityDescriptor;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.utils.Registry;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rpp2Rdf {

    private static final Logger LOG = LoggerFactory.getLogger(Rpp2Rdf.class);

    private List<Listener> listeners = new ArrayList<>();

    public void addListener(final Listener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeListener(final Listener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    private void initJopa(final String rdf4jRepositoryUrl) {
        final Map<String, String> props = new HashMap<>();
        props.put(JOPAPersistenceProperties.SCAN_PACKAGE,
            "cz.gov.data.rpp.xls2rdf");
        props.put(JOPAPersistenceProperties.ONTOLOGY_PHYSICAL_URI_KEY, rdf4jRepositoryUrl);
        PersistenceFactory.init(props);
    }

    private void fire(final Agenda agenda) {
        for (final Listener l : listeners) {
            try {
                l.addAgenda(agenda);
            } catch (Exception e) {
                LOG.warn("Listener ");
                e.printStackTrace();
            }
        }
    }

    /**
     * @param rppXlsFiles files to transform.
     */
    private void transform(Stream<Path> rppXlsFiles) {
        final EntityManager em = PersistenceFactory.createEntityManager();
        final Processor proc = new Processor();
        rppXlsFiles
            .filter(file -> !Files.isDirectory(file))
            .filter(file -> file.getFileName().toString().endsWith("xlsx")).forEach(f -> {
            try (InputStream is = Files.newInputStream(f)) {
                System.out.println(f.getFileName());
                final Agenda agenda = proc.process(f.getFileName().toString(),is);
                if (agenda.getPlatnostDo() != null && !agenda.getPlatnostDo().trim().equals("")) {
                    return;
                }
                fire(agenda);
                LOG.info("Starting transaction for {} ... ", agenda.getKod());
                em.getTransaction().begin();
                LOG.info("Merging ...");
                em.merge(agenda, new EntityDescriptor(URI.create(agenda.getId())));
                LOG.info("Committing transaction ...");
                em.getTransaction().commit();
                LOG.info("Done.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @param rppXlsFiles        files to transform.
     * @param rdf4jRepositoryUrl URL of the Rdf4jRepositoryUrl
     */
    public void transformFiles(String rdf4jRepositoryUrl, Path... rppXlsFiles) {
        initJopa(rdf4jRepositoryUrl);
        transform(Arrays.stream(rppXlsFiles));
    }

    /**
     * @param rppXlsFilesDir     directory with XLS files to transform.
     * @param rdf4jRepositoryUrl URL of the Rdf4jRepositoryUrl
     */
    public void transformDirectory(Path rppXlsFilesDir, String rdf4jRepositoryUrl) {
        initJopa(rdf4jRepositoryUrl);

        try {
            transform(Files.list(rppXlsFilesDir));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface Listener {
        void addAgenda(Agenda a);
    }
}
