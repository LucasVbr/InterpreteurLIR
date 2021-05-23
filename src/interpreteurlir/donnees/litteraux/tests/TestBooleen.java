/**
 * TestBooleen.java                              21 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.donnees.litteraux.tests;

import interpreteurlir.donnees.litteraux.Booleen;
import static info1.outils.glg.Assertions.*;

/** 
 * Tests unitaires des méthodes de la classe Booleen
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestBooleen {
    
    private static final Booleen[] FIXTURE = {
        new Booleen(false),
        new Booleen(true),
        new Booleen(1<2),
        new Booleen(1>=2),
        new Booleen("bob".equals("bob")),
        new Booleen(Character.isDigit('a')),
        new Booleen(!Double.isNaN(1/0.0)),
    };
    
    /** 
     * Tests unitaire de {@link Booleen#getValeur()}
     */
    public void testGetValeur() {
        final Boolean[] ATTENDUS = {
            false,
            true,
            true,
            false,
            true,
            false,
            true
        };
        System.out.println("\tExécution du test de getValeur");
        for (int i = 0 ; i < ATTENDUS.length ; i++) {
            assertTrue(ATTENDUS[i].compareTo(FIXTURE[i].getValeur())
                       == 0);
        }
    }
}
