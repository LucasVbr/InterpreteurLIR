/**
 * TestCommandeDefs.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.motscles.CommandeDefs;

/**
 * Tests unitaires de {@link interpreteurlir.motscles.CommandeDefs}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestCommandeDefs {
 
    /** Jeux d'essais de CommandeDefs valides pour les tests */
    private CommandeDefs[] fixture = { 
            new CommandeDefs("", new Contexte()),
            new CommandeDefs("    ", new Contexte()),
            new CommandeDefs("\t", new Contexte()),
    };
    
    /**
     * Tests unitaires de {@link CommandeDefs#CommandeDefs(String, Contexte)}
     */
    public void testCommandeDefsStringContexte() {
        System.out.println("\tExécution du test de CommandeDefs"
                           + "#CommandeDefs(String, Contexte)");
        
        /* Tests Commande invalide */
        String[] arguments = { "$chaine", " a   ", "fin" };
        Contexte contexte = new Contexte();
        for (int numTest = 0 ; numTest < arguments.length ; numTest++) {
            try {
                new CommandeDefs(arguments[numTest], contexte);
                echec();
            } catch (InterpreteurException lancee) { 
            }
        }
        
        try {
            new CommandeDefs("", new Contexte());
            new CommandeDefs("    ", new Contexte());
            new CommandeDefs("\t", new Contexte());
        } catch (InterpreteurException e) {
            echec();
        }
    }
    
    
    /**
     * Tests unitaires de {@link CommandeDefs#executer()}
     */
    public void testExecuter() {
        System.out.println("\tExécution du test de CommandeDefs#executer()");
        for (CommandeDefs cmd : fixture) {
            System.out.println("Affichage du contexte :");
            assertTrue(cmd.executer());
        }
        
    }
}
