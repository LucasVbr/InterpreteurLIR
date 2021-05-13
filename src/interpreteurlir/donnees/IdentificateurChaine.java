/*
 * IdentificateurChaine.java, 08/05/2021
 * IUT Rodez 2020-2021, info1
 * pas de copyright, aucun droits
 */

package interpreteurlir.donnees;

import interpreteurlir.InterpreteurException;

/**
 * Identificateur de chaîne
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class IdentificateurChaine extends Identificateur {

    /**
     * Instantiation d'identificateur de chaîne
     * @param identificateur a instancier
     * @throws InterpreteurException si l'identificateur est invalide
     */
    public IdentificateurChaine(String identificateur) {
        super(identificateur);
        identificateur = identificateur.trim();
        if(!isIdentificateurChaine(identificateur)) {
           throw new InterpreteurException(identificateur
                                           + " n'est pas un identificateur"
                                           + " de chaine");
        }
    }

    /**
     * Prédicat attestant la validité de l'identificateur
     * @param identificateur à tester
     * @return true si l'identificateur est bien un identificateur d'entier
     *                 false sinon
     */
    private static boolean isIdentificateurChaine(String identificateur) {

        return identificateur.length() >= 2
                && identificateur.charAt(0) == '$'
                && Character.isLetter(identificateur.charAt(1));
    }
}
