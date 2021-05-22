/**
 * TestCommandeEfface.java                                           16 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.motscles.Commande;
import interpreteurlir.motscles.CommandeEfface;
import interpreteurlir.motscles.instructions.InstructionAffiche;
import interpreteurlir.programmes.Etiquette;
import interpreteurlir.programmes.Programme;

/**
 * Tests unitaires de la commande d'effacement de lignes de codes pour 
 * l'interpréteur LIR.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestCommandeEfface {
    
    /** Contexte pour tests */
    public static final Contexte CONTEXTE_TESTS = new Contexte();
    
    /** Programme global pour tests */
    public static final Programme PGM_TESTS = new Programme();
    
    /** Jeu de test valide */
    public static final CommandeEfface[] FIXTURE = {
        new CommandeEfface("1:99999", CONTEXTE_TESTS),
        new CommandeEfface(" 1 : 99999 ", CONTEXTE_TESTS),
        new CommandeEfface("99999 :1 ", CONTEXTE_TESTS),
        new CommandeEfface("1 : 1", CONTEXTE_TESTS),
        new CommandeEfface(" 250: 150 ", CONTEXTE_TESTS)
    };
    
    /** Test du constructeur */
    public static void testCommandeEfface() {
        
        final String[] INVALIDES = {
            "",
            "23 : ",
            " : 15",
            "coucou",
            "50 : coucou",
            "12.4: 24",
            "-14 : 90",
            "\'a\' : 99"
        };
        
        System.out.println("Exécution du test de CommandeEfface"
                           + "(String, Contexte)");
        for (String aTester : INVALIDES) {
            try {
                new CommandeEfface(aTester, CONTEXTE_TESTS);
                echec();
            } catch (InterpreteurException e) {
                // TODO: handle exception
            }
        }
    }
    
    /** Test de executer() */
    public static void testExecuter() {
        
        System.out.println("Exécution du test d'executer()\nTest visuel :");
        Commande.referencerProgramme(PGM_TESTS);
        PGM_TESTS.ajouterLigne(new Etiquette(10), 
                new InstructionAffiche("Bonjour", CONTEXTE_TESTS));
        PGM_TESTS.ajouterLigne(new Etiquette(20), 
                new InstructionAffiche("Comment", CONTEXTE_TESTS));
        PGM_TESTS.ajouterLigne(new Etiquette(30), 
                new InstructionAffiche("Allez", CONTEXTE_TESTS));
        PGM_TESTS.ajouterLigne(new Etiquette(40), 
                new InstructionAffiche("Vous", CONTEXTE_TESTS));
        PGM_TESTS.ajouterLigne(new Etiquette(50), 
                new InstructionAffiche("foobar", CONTEXTE_TESTS));
        System.out.println(PGM_TESTS);
        
        CommandeEfface effacement = new CommandeEfface("20:30", CONTEXTE_TESTS);
        effacement.executer();
        
        System.out.println(PGM_TESTS);
        
    }
}
