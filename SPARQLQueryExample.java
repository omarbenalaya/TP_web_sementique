package projet;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

public class SPARQLQueryExample {

    public static void main(String[] args) {
        // Charger un modèle RDF (peut être votre modèle inféré avec Pellet)
        OntModel model = ModelFactory.createOntologyModel();
        model.read("C:\\Users\\omarb\\OneDrive\\Desktop\\tp\\ontologie.owl");

        // Écrire une requête SPARQL pour extraire les données souhaitées
        String sparqlQuery = 
            "PREFIX ex: <http://example.org/Person>" +
            "SELECT ?person ?phoneNumber ?otherInformation WHERE {" +
            "  ?person rdf:type ex:Person." +
            "  ?person ex:PhoneNumber ?phoneNumber." +
            "  ?person ex:OtherInformation ?otherInformation." +
            "}";

        // Exécuter la requête SPARQL
        Query query = QueryFactory.create(sparqlQuery);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();

            // Traiter les résultats
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode person = soln.get("person");
                Literal phoneNumber = soln.getLiteral("phoneNumber");
                Literal otherInformation = soln.getLiteral("otherInformation");

                // Afficher les résultats
                System.out.println("Person: " + person.toString());
                System.out.println("Phone Number: " + phoneNumber.getString());
                System.out.println("Other Information: " + otherInformation.getString());
                System.out.println("----------------------");
            }
        }
    }
}
