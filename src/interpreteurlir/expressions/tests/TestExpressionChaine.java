/**
 * TestExpressionChaine.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.IdentificateurChaine;
import interpreteurlir.donnees.litteraux.Chaine;
import interpreteurlir.expressions.Expression;
import interpreteurlir.expressions.ExpressionChaine;

/**
 * Tests unitaires de {@link ExpressionChaine}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestExpressionChaine {
    
    /** Jeu de tests d'expression chaîne valides*/
    private ExpressionChaine[] fixture = {
        new ExpressionChaine("$chaine = \"texte\""),  
        new ExpressionChaine("$chaine=\"tata\""),
        new ExpressionChaine("   $tata  \t  "),
        new ExpressionChaine("\"une chaine de texte\""),
        new ExpressionChaine("$chaine= \"toto\"+\"titi\""),
        new ExpressionChaine("$chaine= $toto +\"titi\""),
        new ExpressionChaine("$chaine= \"toto\"+ $titi"),
        new ExpressionChaine("$chaine=$toto +$titi"),
        new ExpressionChaine("   \"toto\"+\"titi\""),
        new ExpressionChaine("$toto +\"titi\""),
        new ExpressionChaine("\"toto\"+ $titi"),
        new ExpressionChaine("$toto +    $titi"),
        new ExpressionChaine("\"ab=bc\""),
        new ExpressionChaine("$chaine = \"ab+cd\"+$toto")
    };

    /**
     * Tests unitaires de {@link ExpressionChaine#ExpressionChaine(String)}
     */
    public void testExpressionChaineString() {
        
        final String[] INVALIDES = {
            null,    
            "",
            "3,1415", "3.1415", "1.7976931348623157E308",
            "45", "-89",
            "tata + $toto",
            "\"chaine\" = $vie",
            "$chaine / \"tata\"",
            "£", "$" 
        };
        
        final String[] VALIDES = {
            "$chaine = \"texte\"",  
            "$chaine=\"tata\"",
            "   $tata  \t  ",
            "\"une chaine de texte\"",
            "$chaine= \"toto\"+\"titi\"",
            "$chaine= $toto +\"titi\"",
            "$chaine= \"toto\"+ $titi",
            "$chaine=$toto +$titi",
            "   \"toto\"+\"titi\"",
            "$toto +\"titi\"",
            "\"toto\"+ $titi",
            "$toto +    $titi",
            "\"ab=bc\"",
            "$chaine = \"ab+cd\"+$toto"
        };
        
        System.out.println("\tExécution du test de "
                + "ExpressionChaine#ExpressionChaine(String)");
        for (String texteArgs : INVALIDES) {
            try {
                new ExpressionChaine(texteArgs);
                echec();
            } catch (InterpreteurException lancee) { 
                // empty
            }
        }
        
        for (String texteArgs : VALIDES) {
            try {
                new ExpressionChaine(texteArgs);
            } catch (InterpreteurException lancee) { 
                echec();
            }
        }     
    }
    
    /**
     * Tests unitaires de {@link ExpressionChaine#calculer()}
     */
    public void testCalculer() {
        final Chaine[] RESULTAT_ATTENDU = {
            new Chaine("\"texte\""),
            new Chaine("\"tata\""),
            new Chaine("\"\""),
            new Chaine("\"une chaine de texte\""),
            new Chaine("\"tototiti\""),
            new Chaine("\"valTototiti\""),
            new Chaine("\"toto\""),
            new Chaine("\"valToto\""),
            new Chaine("\"tototiti\""),
            new Chaine("\"valTototiti\""),
            new Chaine("\"toto\""),
            new Chaine("\"valToto\""),
            new Chaine("\"ab=bc\""),
            new Chaine("\"ab+cdvalToto\"")
        };
        
        System.out.println("\tExécution du test de "
                + "ExpressionChaine#calculer()");
        
        /* Exception levée si contexte non référencé */
        try {
            fixture[0].calculer();
            echec();
        } catch (RuntimeException e) {
            // vide
        }
        
        /* Création contexte (avec $toto = "valToto") et référencement */
        Contexte contexteGlobal = new Contexte();
        contexteGlobal.ajouterVariable(new IdentificateurChaine("$toto"),
                                       new Chaine("\"valToto\""));
        Expression.referencerContexte(contexteGlobal);
        System.out.print("\tContexte initial : \n" + contexteGlobal);
        
        for (int numTest = 0; numTest < RESULTAT_ATTENDU.length ; numTest++) {
            System.out.println("\nCalcul de : " + fixture[numTest]);
            assertEquivalence(fixture[numTest].calculer()
                    .compareTo(RESULTAT_ATTENDU[numTest]), 0);
            System.out.println("\tContexte : \n" + contexteGlobal);
        }
        
    }
}
