/**
 * TestExpressionChaine.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.InterpreteurException;
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
}
