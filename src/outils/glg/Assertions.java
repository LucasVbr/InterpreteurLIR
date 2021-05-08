/*
 * Assertions.java                                 7 avr. 2021
 * IUT info1 2020-2021, groupe 2, aucun droit d'auteur
 */
package outils.glg;

/** 
 * Propositions logiques de test qui propage EchecTest 
 * si elles ne sont pas vérifiées
 * @author info1 202-2021
 */
public class Assertions {
    
    /**
     * Assertion vérifiant qu'une expression booléenne est vraie.
     * Ce test échoue si elle est fausse
     * @param condition expression booléenne à tester
     */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new EchecTest();
        }
    }
    
    /** 
     * Assertion testant l'équivalence de 2 objets selon la relation
     * d'équivalence de base equals (@see java.lang.Object#equals)
     * @param attendu valeur attendue pour le test
     * @param obtenu  valeur obtenue à tester
     */
    public static void assertEquivalent(Object attendu, Object obtenu) {
        assertTrue(obtenu.equals(attendu)); 
    }
    
    /**
     * Echec systématique de test
     * (signaler que une série de test est insuffisante)
     */
    public static void echec() {
        assertTrue(false);
    }
}
