/**
 * TestCommande.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.Contexte;
import interpreteurlir.expressions.Expression;
import interpreteurlir.motscles.Commande;
import interpreteurlir.programmes.Programme;

/**
 * Tests unitaires de {@link interpreteurlir.motscles.Commande}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestCommande {
 
    /** Jeux d'essais de Commande valides pour les tests */
    private Commande[] fixture = { 
            new Commande("", new Contexte()),
            new Commande("coucou", new Contexte()),
            new Commande("$chaine = \"toto\" + $tata", new Contexte())
    };
    
    /**
     * Tests unitaires de {@link Commande#referencerProgramme(Programme)}
     */
    public void testReferencerProgramme() {

        Programme reference = new Programme();
        Programme[] programmes = {
                null, reference, reference, new Programme()
        };
        
        boolean[] resultatAttendu = { false, true, true, false };
        
        System.out.println("\tExécution du test de "
                + "Commande#referencerProgramme(Programme)");
        for (int numTest = 0 ; numTest < programmes.length ;  numTest++) {
            assertTrue(   Commande.referencerProgramme(programmes[numTest]) 
                       == resultatAttendu[numTest]);
        }
    }
    
    /**
     * Tests unitaires de {@link Commande#Commande(String, Contexte)}
     */
    public void testCommandeStringContexte() {
        System.out.println(
                "\tExécution du test de Commande#Commande(String, Contexte)");
        
        /* Tests Commande invalide */
        String[] arguments = { null, null, "" };
        Contexte[] contexte = { null, new Contexte(), null};
        for (int numTest = 0 ; numTest < arguments.length ; numTest++) {
            try {
                new Commande(arguments[numTest], contexte[numTest]);
                echec();
            } catch (NullPointerException lancee) { 
            }
        }
        
        try {
            new Commande("", new Contexte());
            new Commande("coucou", new Contexte());
            new Commande("$chaine = \"toto\" + $tata", new Contexte());
        } catch (NullPointerException e) {
            echec();
        }
    }
    
    
    /**
     * Tests unitaires de {@link Commande#executer()}
     */
    public void testExecuter() {
        System.out.println("\tExécution du test de Commande#executer()");
        for (Commande aTester : fixture) {
            assertFalse(aTester.executer());
        }
    }
}
