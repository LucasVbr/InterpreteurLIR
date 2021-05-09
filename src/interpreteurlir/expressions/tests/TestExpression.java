/**
 * TestExpression.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.expressions.Expression;
import interpreteurlir.expressions.ExpressionChaine;
import interpreteurlir.expressions.ExpressionEntier;
import interpreteurlir.Contexte;

/**
 * Tests unitaires de {@link Expression}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestExpression {
    
    /** Jeu d'essai d'expression typée */
    private Expression[] fixture = {
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
        // TODO expression entière
    };
    
    /**
     * Tests unitaires de {@link Expression#referencerContexte(Contexte)}
     */
    public void testReferencerContexte() {

        Contexte reference = new Contexte();
        Contexte[] contextes = {
                null, reference, reference, new Contexte()
        };
        
        boolean[] resultatAttendu = { false, true, true, false };
        
        System.out.println("\tExécution du test de "
                + "Expression#referencerContexte(Contexte)");
        for (int numTest = 0 ; numTest < contextes.length ;  numTest++) {
            assertTrue(   Expression.referencerContexte(contextes[numTest]) 
                       == resultatAttendu[numTest]);
        }
    }
    
    /**
     * Tests unitaires de {@link Expression#toString()}
     */
    public void testToString() {
        final String[] chaineAttendue = {
            "$chaine = \"texte\"",  
            "$chaine = \"tata\"",
            "$tata",
            "\"une chaine de texte\"",
            "$chaine = \"toto\" + \"titi\"",
            "$chaine = $toto + \"titi\"",
            "$chaine = \"toto\" + $titi",
            "$chaine = $toto + $titi",
            "\"toto\" + \"titi\"",
            "$toto + \"titi\"",
            "\"toto\" + $titi",
            "$toto + $titi"       
        };
        
        System.out.println("\tExécution du test de Expression#toString()");
        for (int numTest = 0 ; numTest < chaineAttendue.length ; numTest++) {
            assertEquivalence(chaineAttendue[numTest], 
                              fixture[numTest].toString());
        }
    }
    
    /**
     * Tests unitaires de {@link Expression#determinerTypeExpression(String)}
     */
    public void testDeterminerTypeExpression() {
        final String[] TEXTE_EXPRESSION = {
            /* Expression de type chaine */
            "$chaine = \"texte\"",  
            "$chaine=\"tata\"",
            "   $tata  \t  ",
            "   \"une chaine de texte\"",
            "$chaine= \"toto\"+\"titi\"",
            "   $chaine= $toto +\"titi\"",
            "$chaine= \"toto\"+ $titi",
            "$chaine=$toto +$titi",
            "   \"toto\"+\"titi\"",
            "\t$toto +\"titi\"",
            "\"toto\"+ $titi",
            "$toto +    $titi", 
            
            /* Expression de type Entier */
            "78",
            "  entier",
            "78   %89",
            "  entier- nombre",
            "\t  entier/78",
            "entier = 78  ",
            " nombre= nombre + 78",
            " entier =78 *2"
            // TODO expressionEntier
        };
        
        final int INDEX_DEBUT_ENTIER = 12;
        
        System.out.println("\tExécution du test de "
                           + "Expression#determinerTypeExpression(String)");
        
        for (int numTest = 0; numTest < TEXTE_EXPRESSION.length ; numTest++) {
            if (numTest < INDEX_DEBUT_ENTIER) {
                assertTrue(Expression.determinerTypeExpression(
                        TEXTE_EXPRESSION[numTest]) instanceof ExpressionChaine);
            } else {
                assertTrue(Expression.determinerTypeExpression(
                        TEXTE_EXPRESSION[numTest]) instanceof ExpressionEntier);
            }
        }
    }
    

}
