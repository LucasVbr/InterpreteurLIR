/**
 * TestContexte.java                              8 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.Contexte;
import interpreteurlir.donnees.*;
import interpreteurlir.donnees.litteraux.*;

/**
 * Tests unitaires de {@link Contexte}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestContexte {

    /** Jeux de tests de Contexte */
    private Contexte[] fixture = { 
            new Contexte(), new Contexte(), new Contexte(),
    };
    
    /**
     * Tests unitaires de {@link Contexte#Contexte()}
     */
    public void testContexte() {
        System.out.println("\tExécution du test de Contexte#Contexte()");
        try {
            new Contexte();  
        } catch (Exception e) {
            echec();
        }
    }
    
    /**
     * Tests unitaires de 
     * {@link Contexte#ajouterVariable(Identificateur, Litteral)}
     */
    public void testAjouterVariable() {
        Identificateur[] id = {
                new IdentificateurChaine("$chaine"), // ajout dans liste vide
                new IdentificateurEntier("entier"), // ajout fin
                new IdentificateurChaine("$zoro"),  // ajout milieu
                // modif variable présente
                new IdentificateurChaine("$chaine"), // ajout dans liste vide
                new IdentificateurEntier("entier"), // ajout fin
                new IdentificateurChaine("$zoro"),  // ajout milieu
                
                new IdentificateurChaine("$abcd"),  // ajout debut
        };
        
        Litteral[] valeur = {
                new Chaine("\"blabla\""),
                new Entier(25),
                new Chaine("\"Zoro le héro\""),
                
                new Chaine("\"viveLa Vie\""),
                new Entier(-1),
                new Chaine("\"   ah ah !  \""),
                
                new Chaine("\"lol\""),
        };
        
        System.out.println("\tExécution du test de "
                + "Contexte#ajouterVariable(Identificateur, Litteral)");
        
        for (int numAjout = 0 ; numAjout < id.length ; numAjout++) {
            fixture[0].ajouterVariable(id[numAjout], valeur[numAjout]);
            if (numAjout == 2) {
                System.out.println(fixture[0].toString());
            }
        }
        System.out.println(fixture[0].toString());
    }
    
    /**
     * Tests unitaires de {@link Contexte#toString()}
     */
    public void testToString() {
        String[] chaineAttendue = {
                "aucune variable n'est définie\n",
                "aucune variable n'est définie\n",
                "aucune variable n'est définie\n",
                // TODO refaire quand totalement fonctionnel
        };
        
        System.out.println("\tExécution du test de Contexte#toString()");
        for (int numTest = 0 ; numTest < chaineAttendue.length ; numTest++) {
            assertEquivalence(fixture[numTest].toString(),
                              chaineAttendue[numTest]);
        }
    }
    
    /**
     * Tests unitaires de {@link Contexte#raz()}
     */
    public void testRaz() {
        String toStringVide = "aucune variable n'est définie\n";
        
        // fixture 0 est vide
        
        // fixture 1 a 3 éléments à vider
        fixture[1].ajouterVariable(new IdentificateurChaine("$chaine"), 
                                   new Chaine("\"blabla\""));
        fixture[1].ajouterVariable(new IdentificateurEntier("entier"), 
                                   new Entier(25));
        fixture[1].ajouterVariable(new IdentificateurChaine("$zoro"), 
                                   new Chaine("\"Zoro le héro\""));
        
        // fixture 2 a 1 éléments unique
        fixture[1].ajouterVariable(new IdentificateurChaine("$zer"), 
                                   new Chaine("\"blvzgr\""));
        
        System.out.println("\tExécution du test de Contexte#raz()");
        for (Contexte aTester : fixture) {
            aTester.raz();
            // toString doit être celui d'un contexte vide
            assertEquivalence(toStringVide, aTester.toString());
        }
    }
    
    /**
     * Tests unitaire de {@link Contexte#lireValeurVariable(Identificateur)}
     */
    public void testLireValeurVariable() {

        System.out.println("\tExécution du test de "
                           + "Contexte#lireValeurVariable(Identificateur)");
        
        // lire valeur défaut contexte vid
        assertEquivalence(fixture[0].lireValeurVariable(
                new IdentificateurChaine("$chaine")).getValeur(), "");
        assertEquivalence(fixture[0].lireValeurVariable(
                new IdentificateurEntier("entier")).getValeur(), 
                                         Integer.valueOf(0));
        
        // lire valeur par défaut dans contexte non vide
        fixture[1].ajouterVariable(new IdentificateurChaine("$zoro"), 
                new Chaine("\"Zoro le héro\""));
        
        assertEquivalence(fixture[1].lireValeurVariable(
                new IdentificateurChaine("$chaine")).getValeur(), "");
        assertEquivalence(fixture[1].lireValeurVariable(
                new IdentificateurEntier("entier")).getValeur(), 0);
        
        // lire valeur qui existent déjà
        fixture[1].ajouterVariable(new IdentificateurChaine("$chaine"), 
                                   new Chaine("\"blabla\""));
        fixture[1].ajouterVariable(new IdentificateurEntier("entier"), 
                                   new Entier(25));
        
        System.out.println(fixture[1].lireValeurVariable(
                new IdentificateurChaine("$zoro")).getValeur());
        
        
        assertEquivalence(fixture[1].lireValeurVariable(
                new IdentificateurChaine("$chaine")).getValeur(), "blabla");
        assertEquivalence(fixture[1].lireValeurVariable(
                          new IdentificateurEntier("entier")).getValeur(), 25);
        assertEquivalence(fixture[1].lireValeurVariable(
                              new IdentificateurChaine("$zoro")).getValeur(), 
                          "Zoro le héro");

        
    }
}
