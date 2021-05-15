/**
 * TestInstructionRetour.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles.instructions.tests;

import interpreteurlir.motscles.Commande;
import interpreteurlir.motscles.instructions.InstructionProcedure;
import interpreteurlir.motscles.instructions.InstructionRetour;
import interpreteurlir.motscles.instructions.InstructionVar;
import interpreteurlir.programmes.Etiquette;
import interpreteurlir.programmes.Programme;
import interpreteurlir.Contexte;
import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.IdentificateurEntier;
import interpreteurlir.expressions.Expression;

import static info1.outils.glg.Assertions.*;

import info1.outils.glg.TestException;

/**
 * Tests unitaires de {@link InstructionRetour}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 *
 */
public class TestInstructionRetour {
    
    /** Contexte global pour les tests */
    private final Contexte CONTEXTE = new Contexte();
    
    /** Programme utilisé dans les tests */
    private final Programme PROG_REFERENCE = new Programme();
    
    /** Jeu de donnée d'InstructionRetour valides */
    private final InstructionRetour[] FIXTURE = {
            new InstructionRetour("", CONTEXTE),
            new InstructionRetour("   ", CONTEXTE),
            new InstructionRetour("\t", CONTEXTE),
            new InstructionRetour("\t   ", CONTEXTE)
    };
    
    /**
     * Tests unitaires de
     * {@link InstructionRetour#InstructionRetour(String, Contexte)}
     */
    public void testInstructionRetourStringContexte() {
        System.out.println("\t Exécution du test de "
                + "InstructionRetour#InstructionRetour(String, Contexte)");
        
        final String[] ARGS_INVALIDES = {
                "    a    ",
                "bonjour bonsoir",
                "513",
                "@!?/",
                "^p65Na@"
        };
        
        for (int i = 0 ; i < ARGS_INVALIDES.length ; i++) {
            try {
                new InstructionRetour(ARGS_INVALIDES[i], CONTEXTE);
                echec();
            } catch (InterpreteurException lancee) {
                // Test OK
            }
        }
        
        try {
            new InstructionRetour("", CONTEXTE);
            new InstructionRetour("   ", CONTEXTE);
            new InstructionRetour("\t", CONTEXTE);
            new InstructionRetour("\t   ", CONTEXTE);
        } catch (InterpreteurException lancee) {
            echec();
        }
    }
    
    /**
     * Tests unitaires de {@link InstructionRetour#toString()}
     */
    public void testToString() {
        System.out.println("\tExecution du test de "
                           + "InstructionRetour#toString()");
        
        for (int i = 0 ; i < FIXTURE.length ; i++) {
            assertEquivalence("retour", FIXTURE[i].toString());
        }
    }
    
    /**
     * Tests unitaires de {@link InstructionRetour#executer()}
     */
    public void testExecuter() {
        System.out.println("\tExecution du test de "
                           + "InstructionRetour#executer()");
        
        for(InstructionRetour instruction : FIXTURE) {
            try {
                instruction.executer();
                echec();
            } catch (RuntimeException e) {
                if (e instanceof TestException) {
                    echec();
                }
                // Test OK
            }
        }
        
        Commande.referencerProgramme(PROG_REFERENCE);
        Expression.referencerContexte(CONTEXTE);
        
        /* Test retour procedure invalide */
        PROG_REFERENCE.ajouterLigne(new Etiquette(1), FIXTURE[0]);
        PROG_REFERENCE.ajouterLigne(new Etiquette(4),
                                    new InstructionProcedure("1", CONTEXTE));
        PROG_REFERENCE.ajouterLigne(new Etiquette(10), FIXTURE[1]);
        
        try {
            PROG_REFERENCE.lancer(new Etiquette(2));
            echec();
        } catch (ExecutionException lancee) {
            // Test OK
        }
        
        PROG_REFERENCE.raz();
        
        /* Tests retour procedure valide */
        PROG_REFERENCE.ajouterLigne(new Etiquette(1),
                                    new InstructionVar("test=5", CONTEXTE));
        PROG_REFERENCE.ajouterLigne(new Etiquette(2), FIXTURE[0]);
        PROG_REFERENCE.ajouterLigne(new Etiquette(3),
                                    new InstructionVar("test=-1", CONTEXTE));
        PROG_REFERENCE.ajouterLigne(new Etiquette(4),
                                    new InstructionProcedure("1", CONTEXTE));
        
        PROG_REFERENCE.lancer(new Etiquette(3));
        assertEquivalence(CONTEXTE.lireValeurVariable(
                          new IdentificateurEntier("test")).getValeur(), 5);
    }
}
