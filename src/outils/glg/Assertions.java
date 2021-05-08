/*
 * Assertions.java                                 7 avr. 2021
 * IUT info1 2020-2021, groupe 2, aucun droit d'auteur
 */
package outils.glg;

/** 
 * Propositions logiques de test qui propage EchecTest 
 * si elles ne sont pas v�rifi�es
 * @author info1 202-2021
 */
public class Assertions {
    
    /**
     * Assertion v�rifiant qu'une expression bool�enne est vraie.
     * Ce test �choue si elle est fausse
     * @param condition expression bool�enne � tester
     */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new EchecTest();
        }
    }
    
    /** 
     * Assertion testant l'�quivalence de 2 objets selon la relation
     * d'�quivalence de base equals (@see java.lang.Object#equals)
     * @param attendu valeur attendue pour le test
     * @param obtenu  valeur obtenue � tester
     */
    public static void assertEquivalent(Object attendu, Object obtenu) {
        assertTrue(obtenu.equals(attendu)); 
    }
    
    /**
     * Echec syst�matique de test
     * (signaler que une s�rie de test est insuffisante)
     */
    public static void echec() {
        assertTrue(false);
    }
}
