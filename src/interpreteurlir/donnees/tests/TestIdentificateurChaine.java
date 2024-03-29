/*
 * TestIdentificateurChaine.java, 08/05/2021
 * IUT Rodez 2020-2021, info1
 * pas de copyright, aucun droits
 */

package interpreteurlir.donnees.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.IdentificateurChaine;

/**
 * Tests unitaires de la classe donnees.IdentificateurEntier
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestIdentificateurChaine {
    /** Jeu d'identificateurs de cha�ne correctement instanci�s */
    private static IdentificateurChaine[] FIXTURE = {
            new IdentificateurChaine("$a"),
            new IdentificateurChaine("$A"),
            new IdentificateurChaine("$alpha"),
            new IdentificateurChaine("$Alpha"),
            new IdentificateurChaine("$Alpha5"),
            new IdentificateurChaine("$jeSuisUnTresLongIdentifi")
    };

    /**
     * Tests unitaires du constructeur 
     * IdentificateurEntier(String identificateur)
     */
    public static void testIdentificateurChaineString() {
        final String[] INVALIDE = {
                "",

                // Commence par une lettre
                "9alpha",
                "  5alpha",

                // Fait au maximum 25 caract�res
                "$jeSuisUnTresLongIdentificateur", // 30 char
                "$jeSuisUnTresLongIdentifica",

                // Espaces
                "id 3a",
                "$id 3a",
                " ",
                "$ ",

                // caract�res d'�chapements
                "\t",
                "\n",
                "$\t",
                "$\n",

                // , cas particulier
                "$",
                "$1"
        };
        System.out.println("\tEx�cution du test de "
                           + "IdentificateurEntier(String identificateur)");
        for(int noJeu = 0; noJeu < INVALIDE.length ; noJeu++) {
            try {
                new IdentificateurChaine(INVALIDE[noJeu]);
                echec();
            } catch (InterpreteurException lancee) {
                // test OK
            }
        }
        
    }

    /**
     * Tests unitaires de getNom()
     */
    public static void testGetNom() {
        final String[] NOM_VALIDES = {
                "$a",
                "$A",
                "$alpha",
                "$Alpha",
                "$Alpha5",
                "$jeSuisUnTresLongIdentifi"
        };

        System.out.println("\tEx�cution du test de getNom()");
        for (int noJeu = 0 ; noJeu < NOM_VALIDES.length ; noJeu++) {
            assertEquivalence(NOM_VALIDES[noJeu], FIXTURE[noJeu].getNom());
        }
    }
}
