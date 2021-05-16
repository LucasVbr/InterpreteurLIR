/**
 * TestExpressionEntier.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.Contexte;
import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.Identificateur;
import interpreteurlir.donnees.IdentificateurChaine;
import interpreteurlir.donnees.IdentificateurEntier;
import interpreteurlir.donnees.litteraux.Chaine;
import interpreteurlir.donnees.litteraux.Entier;
import interpreteurlir.expressions.Expression;
import interpreteurlir.expressions.ExpressionEntier;

/**
 * Tests unitaires de {@link ExpressionEntier}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestExpressionEntier {
    
    /* jeu de test d'expressions entières valides */
    private static final ExpressionEntier[] FIXTURE = {
        new ExpressionEntier("entier = 2 + 3"),
        new ExpressionEntier("entier=2*3"),
        new ExpressionEntier("bob= marcel-2"),
        new ExpressionEntier("45 +14"),
        new ExpressionEntier("45 * -2"),
        new ExpressionEntier("affectation = 64"),
        new ExpressionEntier("affectation= marcel"),
        new ExpressionEntier("entier = j34n + pi3rr3"),
        new ExpressionEntier("       entier   = j34n"),
        new ExpressionEntier("    42"),
        new ExpressionEntier("rep0ns3=  42"),
        new ExpressionEntier("division = 12/0"),
        new ExpressionEntier("modulo = 12%0")
    };
    
    /**
     * Tests unitaires de {@link ExpressionEntier#ExpressionEntier(String)}
     */
    public static void testExpressionEntierString() {
        final String[] INVALIDES = {
            /* identificateurs non valides */
            "$bob =2",
            "j@ck= 2+3",
            "@75S= #michel",
            "unidentificateurbeaucouptroplong = 0",
            "truc.length = 9000",
            
            /* types non compatibles */
            "resultat = \"50\"",
            "resultat = 30.2",
            "resultat = 10 / 2.0",
            
            /* Nombre incorrect d'opérandes */
            "resultat = 10 * 5 + 3",
            "famille = marcel + jean + albert",
            "divisionRatee = 5 /",
            "ratee=*7",
        };
        
        for (String invalide : INVALIDES) {
            try {
                new ExpressionEntier(invalide);
                echec();
                
            } catch(InterpreteurException | ExecutionException lancee) {
                // Empty body
            }
        }
    }
    
    /**
     * Tests unitaires de {@link ExpressionEntier#calculer()}
     */
    public static void testCalculer() {
        final Entier[] RESULTATS_ATTENDUS = {
            new Entier(5),
            new Entier(6),
            new Entier(-2),
            new Entier(59),
            new Entier(-90),
            new Entier(64),
            new Entier(0),
            new Entier(3),
            new Entier(1),
            new Entier(42),
            new Entier(42),
            new Entier(0),  // Bouchon
            new Entier(0)   // Bouchon
        };
        
        /* Exception levée si contexte non référencé */
        try {
            FIXTURE[0].calculer();
            echec();
        } catch (RuntimeException e) {
            // vide
        }
        
        /* 
         * Création contexte (avec marcel = 0 j34n = 1 et pi3rr3 = 2) et 
         * référencement 
         */
        Contexte contexteGlobal = new Contexte();
        contexteGlobal.ajouterVariable(new IdentificateurEntier("marcel"),
                                       new Entier(0));
        contexteGlobal.ajouterVariable(new IdentificateurEntier("j34n"),
                                       new Entier(1));
        contexteGlobal.ajouterVariable(new IdentificateurEntier("pi3rr3"),
                                       new Entier(2));
        Expression.referencerContexte(contexteGlobal);
        System.out.print("\tContexte initial : \n" + contexteGlobal);
        
        for (int i = 0 ; i < FIXTURE.length ; i++) {
            try {
                System.out.println("\nCalcul de : " + FIXTURE[i]);
                assertTrue(FIXTURE[i].calculer()
                                     .compareTo(RESULTATS_ATTENDUS[i]) == 0);
                System.out.println("\tContexte : \n" + contexteGlobal);
            } catch (ExecutionException divzero) {
                System.out.println("Attention Division par 0");
            }
        }
    }
    
    /**
     * test de toString()
     */
    public static void testToString() {
        final String[] ATTENDUES = {
                "entier = 2 + 3",
                "entier = 2 * 3",
                "bob = marcel - 2",
                "45 + 14",
                "45 * -2",
                "affectation = 64",
                "affectation = marcel",
                "entier = j34n + pi3rr3",
                "entier = j34n",
                "42",
                "rep0ns3 = 42",
                "division = 12 / 0",
                "modulo = 12 % 0"
        };
        
        for (int i = 0 ; i < FIXTURE.length ; i++) {
            assertTrue(FIXTURE[i].toString().equals(ATTENDUES[i]));
        }
    }
}
