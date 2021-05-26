/*
 * TestIdentificateurEntier.java , 08/05/2021
 * IUT Rodez 2020-2021, info1
 * pas de copyright, aucun droits
 */

package interpreteurlir.donnees.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.IdentificateurEntier;

/**
 * Tests unitaires de la classe donnees.IdentificateurEntier
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestIdentificateurEntier {

    /** Jeu d'identificateurs d'entier correctement instanciés */
    private static IdentificateurEntier[] FIXTURE = {
            new IdentificateurEntier("a"),
            new IdentificateurEntier("A"),
            new IdentificateurEntier("alpha"),
            new IdentificateurEntier("Alpha"),
            new IdentificateurEntier("Alpha5"),
            new IdentificateurEntier("jeSuisUnTresLongIdentific")
    };

    /**
     * Tests unitaires du constructeur IdentificateurEntier(String identificateur)
     */
    public static void testIdentificateurEntierString() {
        final String[] INVALIDE = {
                // Ne commence pas  par une lettre
                "9alpha",
                "  5alpha",
                "$beta",

                // Fait plus de 25 caractères
                "jeSuisUnTresLongIdentificateur", // 30 char
                "jeSuisUnTresLongIdentifica",

                // Espaces, caractères d'échapements, cas particulier
                "id 3a",
                "",
                " ",
                "\t",
                "\n",
        };

        for(int noJeu = 0; noJeu < INVALIDE.length ; noJeu++) {
            try {
                new IdentificateurEntier(INVALIDE[noJeu]);
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
                "a",
                "A",
                "alpha",
                "Alpha",
                "Alpha5",
                "jeSuisUnTresLongIdentific"
        };

        for (int noJeu = 0 ; noJeu < NOM_VALIDES.length ; noJeu++) {
            assertEquivalence(NOM_VALIDES[noJeu], FIXTURE[noJeu].getNom());
        }
    }
}
