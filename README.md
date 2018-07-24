# A transformer from RPP XLS files to RDF

The tools transforms XLS files of the Register of rights and responsibilities ( [Registr práv a povinností](https://rpp-ais.egon.gov.cz/gen/agendy-detail/) )
into RDF.

The data model for the transformation can be seen in [doc/model.png]() :

The tool can be used e.g. as

    new Rpp2Rdf().transformFiles(<rdf4jRepositoryUrl>, Path rppXlsFile);

which takes the file <rppXlsFile>, parses an Agenda out of it and stores it into a [JOPA](https://github.com/kbss-cvut/jopa) [RDF4J](http://rdf4j.org/) repository <rdf4jRepositoryUrl>.
The terms (ontology classes/properties) used to annotate the data stem from the data vocabulary of the RPP datasets and has the following base IRI: http://slovnik.gov.cz/datovy/rpp/pojem/.

See tests, for detailed examples.