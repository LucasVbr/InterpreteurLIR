/**
 * TestInstructionSi.java                              22 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions.tests;

import interpreteurlir.motscles.Commande;
import interpreteurlir.motscles.instructions.InstructionSi;
import interpreteurlir.motscles.instructions.InstructionVar;
import interpreteurlir.programmes.*;
import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.*;
import interpreteurlir.donnees.litteraux.*;
import interpreteurlir.expressions.Expression;

import static info1.outils.glg.Assertions.*;

/**
 * Tests unitaires de {@link InstructionSi}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestInstructionSi {

    /** contexte pour les tests */
    private Contexte contexte = new Contexte();
    
    /** programme pour les tests */
    private Programme prog = new Programme();
    
    /** Jeu de donnée d'instruction si vaen valides pour les tests*/
    private InstructionSi[] fixture = {
        new InstructionSi("45 = 2 vaen 15", contexte),
        new InstructionSi("age >= 130 vaen 1000", contexte),
        new InstructionSi("$prenom <>\"défaut\" vaen 16", contexte),
        new InstructionSi("resultat    < 20 vaen 17", contexte),
        new InstructionSi("resultat < moyenne vaen 18", contexte),
        new InstructionSi("age > 20 vaen   19", contexte),
        new InstructionSi("\"tata\"    = \"tata\"vaen 20", contexte),
        new InstructionSi("\"toto  \" > $toto vaen1502", contexte),
        new InstructionSi("-5 <= 0 vaen 21", contexte),
        new InstructionSi("resultat < 20    vaen 22", contexte),
    };
    
    /**
     * Tests unitaires de {@link InstructionSi#InstructionSi(String, Contexte)}
     */
    public void testInstructionSiStringContexte() {
        final String[] ARGS_INVALIDES = {
            "",
            "   \t",
            " entier < index",
            "vaen 1050",
            "age = 10 vaen",
            " $prenom = \"défaut\" va 10",
            "$prenom <> $nom  goto 45",
            "$prenom <> $nom  vaen dix",
            "$prenom != $nom  vaen 45",
            /* erreur de type */
            "$prenom <> 5 vaen 450",
            "age > \"\" vaen 450",
            "age >= $prenom vaen 450",
            "\"dix\" = 10 vaen 4500",
        };
        
        System.out.println("\tExécution du test de "
                           + "InstructionSi#InstructionSi(String, Contexte)");
        
        for (String aTester : ARGS_INVALIDES) {
            try {
                new InstructionSi(aTester, contexte);
                echec();
            } catch (InterpreteurException lancee) {
                // testok
            }
        }
        
        try {
            new InstructionSi("45 = 2 vaen 15", contexte);
            new InstructionSi("age >= 130 vaen 1000", contexte);
            new InstructionSi("$prenom <>\"défaut\" vaen 15", contexte);
            new InstructionSi("resultat    < 20 vaen 15", contexte);
            new InstructionSi("resultat < moyenne vaen 15", contexte);
            new InstructionSi("age > 20 vaen   15", contexte);
            new InstructionSi("\"tata\"    = \"tata\"vaen 15", contexte);
            new InstructionSi("\"toto  \" > $toto vaen1502", contexte);
            new InstructionSi("-5 <= 0 vaen 15", contexte);
            new InstructionSi("resultat < 20    vaen 15", contexte);
            new InstructionSi("$chaine <= \"vaen 15\"    vaen 15", contexte);
        } catch (InterpreteurException lancee) {
            echec();
        }
    }
    
    /**
     * Tests unitaires de {@link InstructionSi#toString()}    
     */
    public void testToString() {
        final String[] ATTENDU = {
            "si 45 = 2 vaen 15",
            "si age >= 130 vaen 1000",
            "si $prenom <> \"défaut\" vaen 16",
            "si resultat < 20 vaen 17",
            "si resultat < moyenne vaen 18",
            "si age > 20 vaen 19",
            "si \"tata\" = \"tata\" vaen 20",
            "si \"toto  \" > $toto vaen 1502",
            "si -5 <= 0 vaen 21",
            "si resultat < 20 vaen 22",
        };
        System.out.println("\tExécution du test de InstructionSi#toString()");
        
        for (int numTest = 0 ; numTest < ATTENDU.length ; numTest++) {
            assertEquivalence(ATTENDU[numTest], fixture[numTest].toString());
        }
    }
    
    /**
     * Tests unitaires de {@link InstructionSi#executer()}
     */
    public void testExecuter() {
        Commande.referencerProgramme(prog);
        prog.ajouterLigne(new Etiquette(15), 
                new InstructionVar("valeur = valeur -1", contexte));
        prog.ajouterLigne(new Etiquette(16), 
                new InstructionVar("valeur = valeur -1", contexte));
        prog.ajouterLigne(new Etiquette(17), 
                new InstructionVar("valeur = valeur -1", contexte));
        prog.ajouterLigne(new Etiquette(18), 
                new InstructionVar("valeur = valeur -1", contexte));
        prog.ajouterLigne(new Etiquette(19), 
                new InstructionVar("valeur = valeur -1", contexte));
        prog.ajouterLigne(new Etiquette(20), 
                new InstructionVar("valeur = valeur -1", contexte));
        prog.ajouterLigne(new Etiquette(21), 
                new InstructionVar("valeur = valeur -1", contexte));
        prog.ajouterLigne(new Etiquette(22), 
                new InstructionVar("valeur = valeur -1", contexte));
        prog.ajouterLigne(new Etiquette(1000), 
                new InstructionVar("valeur = valeur -1", contexte));
        prog.ajouterLigne(new Etiquette(1502), 
                new InstructionVar("valeur = valeur -1", contexte));
        Expression.referencerContexte(contexte);

        
        final int[] VALEUR_ATTENDU = {
            0, // pas de saut
            0,
            -9, // saut en 16
            -8, // saut en 17
            0,
            -6, // saut en 19
            -5, // saut en 20
            -1, // saut en 1502
            -4, // saut en 21
            -3, // saut en 22
        };
        
        System.out.println("\tExécution du test de InstructionSi#executer()");
        
        for (int numTest = 0 ; numTest < VALEUR_ATTENDU.length ; numTest++) {
            /* initialisation du contexte */
            contexte.raz();
            contexte.ajouterVariable(new IdentificateurEntier("moyenne"), 
                    new Entier("-2"));
            contexte.ajouterVariable(new IdentificateurEntier("age"), 
                    new Entier("99"));
            contexte.ajouterVariable(new IdentificateurChaine("$toto"), 
                    new Chaine("\"toto\""));
            
            fixture[numTest].executer();
            assertEquivalence(VALEUR_ATTENDU[numTest], 
                              ((Integer)contexte.lireValeurVariable(
                                      new IdentificateurEntier("valeur"))
                                      .getValeur()).intValue());
        } 
    }
    

}
