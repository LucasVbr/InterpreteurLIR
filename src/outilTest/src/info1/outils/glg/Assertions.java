/*
 * Assertions.java                                                 7 avr. 2021
 * IUT info1 2020-2021 groupe 1, pas de copyright, aucun droit
 */
package info1.outils.glg;

/** 
 * Propositions logiques qui permettent des tests en propageant 
 * un échec par l'exception TestException
 * @author info1 202-2021
 *
 */
public class Assertions {

    /** 
     * Test d'une expression booléenne qui réussit si elle est vraie
     * @param condition expression à tester
     */
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new TestException("");
        }
    }
    
    /** 
     * Test d'une expression booléenne qui réussit si elle est fausse
     * @param condition expression à tester
     */
    public static void assertFalse(boolean condition) {
        assertTrue(!condition); 
    }
    
    /**
     * Provoque un échec systématique
     */
    public static void echec() {
        assertTrue(false);
    }
    
    /** 
     * Test de l'équivalence de deux objets qui réussit si les objets 
     * sont equals (@see java.lang.Object#equals) 
     * @param attendu objet attendu qui doit être équivalent à obtenu
     * @param obtenu  objet obtenu  qui doit être équivalent à attendu
     */
    public static void assertEquivalence(Object attendu, Object obtenu) {
        assertTrue(attendu.equals(obtenu));
    }
    
    /** 
     * Test de l'équivalence de deux expressions qui réussit si les valeurs 
     * sont égales
     * @param attendu valeur attendue qui doit être équivalente à obtenu
     * @param obtenu  valeur obtenue  qui doit être équivalente à attendu
     */
    public static void assertEquivalence(int attendu, int obtenu) {
        assertTrue(attendu == obtenu);
    }
    
    
    
}
