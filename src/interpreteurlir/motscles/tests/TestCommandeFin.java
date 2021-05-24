/**
 * TestCommandeFin.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.motscles.CommandeFin;

/**
 * Tests unitaires de {@link interpreteurlir.motscles.CommandeFin}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestCommandeFin {
    
    /**
     * Tests unitaires de {@link CommandeFin#CommandeFin(String, Contexte)}
     */
    public void testCommandeFinStringContexte() {
        System.out.println("\tExécution du test de CommandeFin"
                           + "#CommandeFin(String, Contexte)");
        
        /* Tests Commande invalide */
        String[] arguments = { "$chaine", " a   ", "fin" };
        Contexte contexte = new Contexte();
        for (int numTest = 0 ; numTest < arguments.length ; numTest++) {
            try {
                new CommandeFin(arguments[numTest], contexte);
                echec();
            } catch (InterpreteurException lancee) { 
            }
        }
        
        try {
            new CommandeFin("", new Contexte());
            new CommandeFin("    ", new Contexte());
            new CommandeFin("\t", new Contexte());
        } catch (InterpreteurException e) {
            echec();
        }
    }
    
    
    /**
     * Tests unitaires de {@link CommandeFin#executer()}
     */
    public void testExecuter() {
        System.out.println("\tExécution du test de CommandeFin#executer()");
        System.out.println("\tLe programme doit s'éteindre en affichant un "
                           + "message d'aurevoir :");
        System.out.println("Test exécuter désactiver");
        //fixture[0].executer();
    }
}
