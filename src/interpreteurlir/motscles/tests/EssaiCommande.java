/**
 * EssaiCommande.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.tests;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.motscles.*;
import interpreteurlir.programmes.Programme;

/**
 * Essais des commandes (création + éxécution)
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class EssaiCommande {

    /**
     * Essais de commandes avec arguments invalides puis valides
     * @param args non utilisé
     */
    public static void main(String[] args) {
        Contexte contexte = new Contexte();
        Commande.referencerProgramme(new Programme());
        
        /* Erreur dans commande */
        System.out.println("? debut args");
        try {
            new CommandeDebut("args", contexte);
        } catch (InterpreteurException lancee) {
            feedback(lancee);
        }
        System.out.println("? defs args");
        try {
            new CommandeDefs("args", contexte);
        } catch (InterpreteurException lancee) {
            feedback(lancee);
        }
        System.out.println("? fin args");
        try {
            new CommandeFin("args", contexte);
        } catch (InterpreteurException lancee) {
            feedback(lancee);
        }
        
        /* Commande valide et exécution */
        System.out.println("? debut");
        feedback(new CommandeDebut("", contexte).executer());
        System.out.println("? defs");
        feedback(new CommandeDefs("", contexte).executer());
        System.out.println("? liste");
        feedback(new CommandeListe("", contexte).executer());
        System.out.println("? fin");
        feedback(new CommandeFin("", contexte).executer());
        
        System.err.println("Erreur, la commande fin n'a pas quitter");

    }
    
    private static void feedback(boolean nonBesoinFeedback) {
        if (!nonBesoinFeedback) {
            System.out.println("ok");
        }
    }
    
    private static void feedback(InterpreteurException lancee) {
        System.out.println("nok : " + lancee.getMessage());
    }

}
