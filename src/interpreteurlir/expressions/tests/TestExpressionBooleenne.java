/**
 * TestExpressionBooleenne.java                              21 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions.tests;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.IdentificateurChaine;
import interpreteurlir.donnees.IdentificateurEntier;
import interpreteurlir.donnees.litteraux.Chaine;
import interpreteurlir.donnees.litteraux.Entier;
import interpreteurlir.expressions.Expression;
import interpreteurlir.expressions.ExpressionBooleenne;
import static info1.outils.glg.Assertions.*;

/** 
 * Tests unitaires des méthodes de la classe ExpressionBooleenne
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestExpressionBooleenne {
    
//    private final ExpressionBooleenne[] FIXTURE_LITTERALE = {
//        /* Expression logique sur des Entiers AVEC ESPACES */
//        new ExpressionBooleenne("1 = 1"), // true
//        new ExpressionBooleenne("1 = 2"), // false
//        new ExpressionBooleenne("1 < 2"),
//        new ExpressionBooleenne("1 < 1"),
//        new ExpressionBooleenne("1 <> 2"),
//        new ExpressionBooleenne("1 <> 1"),
//        new ExpressionBooleenne("1 <= 1"),
//        new ExpressionBooleenne("1 <= 5"),
//        new ExpressionBooleenne("1 > -3"),
//        new ExpressionBooleenne("1 > 56"),
//        new ExpressionBooleenne("1 >= 1"),
//        new ExpressionBooleenne("1 >= 45"),
//        /* Expression logique sur des Entiers SANS ESPACES */
//        new ExpressionBooleenne("1=1"),
//        new ExpressionBooleenne("1=2"),
//        new ExpressionBooleenne("1<2"),
//        new ExpressionBooleenne("1<1"),
//        new ExpressionBooleenne("1<>2"),
//        new ExpressionBooleenne("1<>1"),
//        new ExpressionBooleenne("1<=1"),
//        new ExpressionBooleenne("1<=5"),
//        new ExpressionBooleenne("1>-3"),
//        new ExpressionBooleenne("1>56"),
//        new ExpressionBooleenne("1>=1"),
//        new ExpressionBooleenne("1>=45"),
//        /* Expression logique sur des Entiers MOITIE ESPACES */
//        new ExpressionBooleenne("    1=1"),
//        new ExpressionBooleenne("1=2    "),
//        new ExpressionBooleenne("1 <2"),
//        new ExpressionBooleenne("1< 1"),
//        new ExpressionBooleenne("1  <>2"),
//        new ExpressionBooleenne("1<>   1"),
//        new ExpressionBooleenne("   1<=1   "),
//        new ExpressionBooleenne("1   <=   5"),
//        new ExpressionBooleenne("    1   >  -3   "),
//        new ExpressionBooleenne("1>56\t"),
//        new ExpressionBooleenne("   1    >=  1    "),
//        new ExpressionBooleenne("    1       >=    45   "),
//        
//        /* Expression logique sur des Chaines AVEC ESPACES */
//        new ExpressionBooleenne("\"TATA\" = \"TATA\""),
//        new ExpressionBooleenne("\"TATA\" = \"TITI\""),
//        new ExpressionBooleenne("\"TATA\" < \"TITI\""),
//        new ExpressionBooleenne("\"TOTO\" < \"TITI\""),
//        new ExpressionBooleenne("\"TOTO\" <> \"TATA\""),
//        new ExpressionBooleenne("\"TATA\" <> \"TATA\""),
//        new ExpressionBooleenne("\"TATA\" <= \"TATA\""),
//        new ExpressionBooleenne("\"TITI\" <= \"TATA\""),
//        new ExpressionBooleenne("\"TATA\" > \"FOO BAR\""),
//        new ExpressionBooleenne("\"FOO BAR\" > \"TATA\""),
//        new ExpressionBooleenne("\"TATA\" >= \"TATA\""),
//        new ExpressionBooleenne("\"FOO BAR\" >= \"TATA\""),
//        /* Expression logique sur des Chaines SANS ESPACES */
//        new ExpressionBooleenne("\"TATA\"=\"TATA\""),
//        new ExpressionBooleenne("\"TATA\"=\"TITI\""),
//        new ExpressionBooleenne("\"TATA\"<\"TITI\""),
//        new ExpressionBooleenne("\"TOTO\"<\"TITI\""),
//        new ExpressionBooleenne("\"TOTO\"<>\"TATA\""),
//        new ExpressionBooleenne("\"TATA\"<>\"TATA\""),
//        new ExpressionBooleenne("\"TATA\"<=\"TATA\""),
//        new ExpressionBooleenne("\"TITI\"<=\"TATA\""),
//        new ExpressionBooleenne("\"TATA\">\"FOO BAR\""),
//        new ExpressionBooleenne("\"FOO BAR\">\"TATA\""),
//        new ExpressionBooleenne("\"TATA\">=\"TATA\""),
//        new ExpressionBooleenne("\"FOO BAR\">=\"TATA\""),
//        /* Expression logique sur des Chaines MOITIE ESPACES */
//        new ExpressionBooleenne("        \"TATA\" = \"TATA\""),
//        new ExpressionBooleenne("\"TATA\"           = \"TITI\""),
//        new ExpressionBooleenne("\"TATA\" <          \"TITI\""),
//        new ExpressionBooleenne("\"TOTO\" < \"TITI\"       "),
//        new ExpressionBooleenne("     \"TOTO\"<> \"TATA\"       "),
//        new ExpressionBooleenne("\"TATA\"     <>     \"TATA\"     "),
//        new ExpressionBooleenne("        \"TATA\" <=\"TATA\""),
//        new ExpressionBooleenne("\"TITI\" <=       \"TATA\""),
//        new ExpressionBooleenne("      \"TATA\" > \"FOO BAR\""),
//        new ExpressionBooleenne("    \"FOO BAR    \"       > \"TATA\""),
//        new ExpressionBooleenne("\"TATA\"        >=         \"TATA\""),
//        new ExpressionBooleenne("            \"FOO BAR\" >=      \"TATA\""),
//        /* Expression logique sur des Chaines AVEC OPERATEURS */
//        new ExpressionBooleenne("\"FOO BAR\"<>\"TATA=TOTO\""),
//        new ExpressionBooleenne("\"FOO BAR=FLEMME\">\"TOTO\""),
//        new ExpressionBooleenne("\"FOO BAR > FLEMME\"=\"TOTO\""),
//        new ExpressionBooleenne("\"FOO BAR<>FLEMME\">\"TOTO\""),
//        
//    };
//        
//    private final ExpressionBooleenne[] FIXTURE_ID = {
//        /* Expression logique sur des IdEntier et Entiers */
//        new ExpressionBooleenne("marcel <= 10"), // true
//        new ExpressionBooleenne("marcel > j34n"), // false
//        new ExpressionBooleenne("2 = pi3rr3"),
//        new ExpressionBooleenne("j34n = pi3rr3"),
//        /* Expression logique sur des IdChaine et Chaines */
//        new ExpressionBooleenne("$sanchis < $barrios"),
//        new ExpressionBooleenne("$servieres > \"Windows\""),
//        new ExpressionBooleenne("$barrios <> $servieres"),
//        new ExpressionBooleenne("\"coucou\" = $barrios"),
//    };

    /** 
     * Tests unitaire de {@link ExpressionBooleenne#ExpressionBooleenne(String)}
     */
    public void testExpressionBooleenne() {
        
        final String[] INVALIDES = {
            /* Pas d'opérateur */
            "",
            "2 5",
            "\"John Doe\"",
            "\"Foo bar\" $serpillere",
            "entier -20",
            /* Opérateurs invalides */
            "-89 + 67",
            "-8979 % 7",
            "35 * 12",
            "89 / 12",
            "65 - 74",
            "\"Foo bar\" + $serpillere",
            /* Expressions logiques avec opérateurs invalides */
            "78 < =  45",
            "entier > = 56",
            "\"Foo bar\" < > $serpillere",
            "$coucou >< $dollarchaine",
            "78 => 45",
            "32 =< 61",
            "32 == 61",
            /* Plus de 2 opérandes et 1 opérateur */
            "65 <> 45 = 45",
            "entier > 85 && 45 = 12",
            "entier <= 85 || 45 <> 12",
            /* Caractères entre les opérandes et l'opérateur */
            "\"Foo bar\"  . > $serpillere",
            "\"Foo bar\"  < _ $serpillere",
            "\"Foo bar\" + $balai > serpillere",
            
            /* Incompatibilité entre types d'opérandes */
            "\"Foo bar\" > serpillere",
            "serpillere <> \"Foo bar\"",
            "15 > $coucou",
            "\"coucou\" <> 45",
            "$coucou = entier"
        };

        System.out.println("\tExécution du test de ExpressionBooleenne()");
        
//        for (String aTester : INVALIDES) {
//            try {
//                new ExpressionBooleenne(aTester);
//                echec();
//            } catch (InterpreteurException lancee) {
//                // Test OK
//            }
//        }
        
//        try {
            new ExpressionBooleenne("1 = 1"); // true
            new ExpressionBooleenne("1 = 2"); // false
            new ExpressionBooleenne("1 < 2");
//        } catch (InterpreteurException lancee) {
//            echec();
//        }
    }
    
    /** 
     * Tests unitaire de {@link ExpressionBooleenne#calculer()}
     */
    public void testCalculer() {
        
        Contexte contexteGlobal = new Contexte();
        contexteGlobal.ajouterVariable(new IdentificateurEntier("marcel"),
                                       new Entier(0));
        contexteGlobal.ajouterVariable(new IdentificateurEntier("j34n"),
                                       new Entier(1));
        contexteGlobal.ajouterVariable(new IdentificateurEntier("pi3rr3"),
                                       new Entier(2));
        contexteGlobal.ajouterVariable(new IdentificateurChaine("$sanchis"),
                new Chaine("\"coucou\""));
        contexteGlobal.ajouterVariable(new IdentificateurChaine("$barrios"),
                new Chaine("\"java\""));
        contexteGlobal.ajouterVariable(new IdentificateurChaine("$servieres"),
                new Chaine("\"WinDesign\""));
        Expression.referencerContexte(contexteGlobal);
        System.out.print("\tContexte initial : \n" + contexteGlobal);
        
        //TODO écrire les tests
        echec();
    }
    
    /** 
     * Tests unitaire de {@link ExpressionBooleenne#toString()}
     */
    public void testToString() {
        //TODO écrire les tests "opG opr opD"
        echec();
    }
}