/**
 * TestCommandeDebut.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.InterpreteurException;
import interpreteurlir.Contexte;
import interpreteurlir.motscles.Commande;
import interpreteurlir.motscles.CommandeDebut;
import interpreteurlir.programmes.Programme;

/**
 * Tests unitaires de {@link interpreteurlir.motscles.CommandeDebut}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestCommandeDebut {
 
    /** Jeux d'essais de CommandeDebut valides pour les tests */
    private CommandeDebut[] fixture = { 
            new CommandeDebut("", new Contexte()),
            new CommandeDebut("    ", new Contexte()),
            new CommandeDebut("\t", new Contexte()),
    };
    
    /**
     * Tests unitaires de {@link CommandeDebut#CommandeDebut(String, Contexte)}
     */
    public void testCommandeDebutStringContexte() {
        System.out.println("\tExécution du test de CommandeDebut"
                           + "#CommandeDebut(String, Contexte)");
        
        /* Tests Commande invalide */
        String[] arguments = { "$chaine", " a   ", "fin" };
        Contexte contexte = new Contexte();
        for (int numTest = 0 ; numTest < arguments.length ; numTest++) {
            try {
                new CommandeDebut(arguments[numTest], contexte);
                echec();
            } catch (InterpreteurException lancee) { 
            }
        }
        
        try {
            new CommandeDebut("", new Contexte());
            new CommandeDebut("    ", new Contexte());
            new CommandeDebut("\t", new Contexte());
        } catch (InterpreteurException e) {
            echec();
        }
    }
    
    
    /**
     * Tests unitaires de {@link CommandeDebut#executer()}
     */
    public void testExecuter() {
        Commande.referencerProgramme(new Programme());
        System.out.println("\tExécution du test de CommandeDebut#executer()");
        for (CommandeDebut cmd : fixture) {
            assertFalse(cmd.executer());
        }
        
    }
}
