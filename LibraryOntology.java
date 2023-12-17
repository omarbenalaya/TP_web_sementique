package projet;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class LibraryOntology {
    public static void main(String[] args) {
        // Créer un modèle RDF
        OntModel model = ModelFactory.createOntologyModel();

        // Définir les propriétés et les ressources
        OntClass person = model.createClass("http://example.org/Person");
        OntClass employee = model.createClass("http://example.org/Employee");
        OntClass content = model.createClass("http://example.org/Content");
        OntClass author = model.createClass("http://example.org/Author");
        OntClass library = model.createClass("http://example.org/Library");
        OntClass editor = model.createClass("http://example.org/Editor");
        OntClass articles = model.createClass("http://example.org/Articles");
        OntClass newspaper = model.createClass("http://example.org/Newspaper");
        OntClass manager = model.createClass("http://example.org/Manager");

        // Déclarer les sous-classes
        editor.addSubClass(person);
        employee.addSubClass(person);
        author.addSubClass(person);
        manager.addSubClass(person);
        
        
        // Propriétés d'objet
        ObjectProperty hasAuthor = model.createObjectProperty("http://example.org/hasAuthor");
        ObjectProperty responsibleFor = model.createObjectProperty("http://example.org/responsibleFor");
        ObjectProperty employer = model.createObjectProperty("http://example.org/employer");
        ObjectProperty possesses = model.createObjectProperty("http://example.org/possesses");
        ObjectProperty publishedIn = model.createObjectProperty("http://example.org/publishedIn");
        ObjectProperty createdBy = model.createObjectProperty("http://example.org/createdBy");

        // Propriétés de données
        DatatypeProperty phoneNumber = model.createDatatypeProperty("http://example.org/PhoneNumber");
        DatatypeProperty otherInformation = model.createDatatypeProperty("http://example.org/OtherInformation");
        DatatypeProperty salary = model.createDatatypeProperty("http://example.org/Salary");
        DatatypeProperty date = model.createDatatypeProperty("http://example.org/Data");
        DatatypeProperty explorationDate = model.createDatatypeProperty("http://example.org/ExplorationDate");
        DatatypeProperty contentSaction = model.createDatatypeProperty("http://example.org/ContentSaction");
        DatatypeProperty urgent = model.createDatatypeProperty("http://example.org/Urgent");
        DatatypeProperty pageNumber = model.createDatatypeProperty("http://example.org/PageNumber");
        DatatypeProperty layOut = model.createDatatypeProperty("http://example.org/LayOut");
        DatatypeProperty name = model.createDatatypeProperty("http://example.org/Name");
        DatatypeProperty organisation = model.createDatatypeProperty("http://example.org/Organisation");
        DatatypeProperty keyWords = model.createDatatypeProperty("http://example.org/KeyWords");
        DatatypeProperty articleType = model.createDatatypeProperty("http://example.org/ArticleType");
        DatatypeProperty contents = model.createDatatypeProperty("http://example.org/Contents");
        DatatypeProperty currentJob = model.createDatatypeProperty("http://example.org/CurrentJob");
      
        
        // Ajouter des instances
        Resource johnDoe = model.createResource("http://example.org/JohnDoe");
        Resource janeDoe = model.createResource("http://example.org/JaneDoe");
        Resource someEmployee = model.createResource("http://example.org/SomeEmployee");
        Resource someAuthor = model.createResource("http://example.org/SomeAuthor");
        Resource publicLibrary = model.createResource("http://example.org/PublicLibrary");
        Resource someEditor = model.createResource("http://example.org/SomeEditor");
        Resource someArticles = model.createResource("http://example.org/SomeArticles");
        Resource dailyNews = model.createResource("http://example.org/DailyNews");
        Resource someManager = model.createResource("http://example.org/SomeManager");

        
        
        // Affecter les types aux instances
        johnDoe.addProperty(RDF.type, person);
        johnDoe.addProperty(phoneNumber, "123456789");
        johnDoe.addProperty(otherInformation, "Some information");

        someEmployee.addProperty(RDF.type, employee);
        someEmployee.addProperty(salary, "50000");
        someEmployee.addProperty(date, "10-11-2012");

        someAuthor.addProperty(RDF.type, author);
        someAuthor.addProperty(name, "John Author");

        publicLibrary.addProperty(RDF.type, library);
        publicLibrary.addProperty(organisation, "Public Library");

        someEditor.addProperty(RDF.type, editor);
        someEditor.addProperty(salary, "60000");
        someEditor.addProperty(date, "12-10-2003");

        someArticles.addProperty(RDF.type, articles);
        someArticles.addProperty(keyWords, "Technology, Science");
        someArticles.addProperty(articleType, "Research");

        dailyNews.addProperty(RDF.type, newspaper);
        dailyNews.addProperty(contents, "Daily news");

        someManager.addProperty(RDF.type, manager);
        someManager.addProperty(currentJob, "Manager");

        // Définir les relations entre les classes
        model.add(editor, responsibleFor, employee);
        model.add(editor, employer, library);
        model.add(library, possesses, articles);
        model.add(content, publishedIn, newspaper);
        model.add(articles, createdBy, author);

        // Définir le raisonneur Pellet
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();

        // Créer un modèle inféré avec le raisonneur
        InfModel infModel = ModelFactory.createInfModel(reasoner, model);

        // Afficher le modèle avec raisonnement
        saveModelToFile(infModel, "C:\\Users\\omarb\\OneDrive\\Desktop\\tp\\ontologie.owl");
    }

    private static void saveModelToFile(InfModel infModel, String fileName) {
        try (OutputStream out = new FileOutputStream(fileName)) {
            // Écrire le modèle dans un fichier OWL
            infModel.write(out, "RDF/XML-ABBREV");
            System.out.println("Ontology saved to file: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}