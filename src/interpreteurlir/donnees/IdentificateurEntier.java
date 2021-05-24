/*
 * IdentificateurEntier.java , 08/05/2021
 * IUT Rodez 2020-2021, info1
 * pas de copyright, aucun droits
 */

package interpreteurlir.donnees;

import interpreteurlir.InterpreteurException;

/**
 * Identificateur d'entier utilis� pour instancier des variables du m�me type.
 * Un identificateur entier commence par une lettre suivie d'au plus 24
 * caract�re alphanum�riques.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class IdentificateurEntier extends Identificateur {

    /**
     * Instantiation de cet identificateur d'entier avec le nom sp�cifi� 
     * en argument. L�ve une exception si l'identificateur n'est pas
     * valide.
     * @param identificateur � instancier
     * @throws InterpreteurException si l'identificateur est invalide
     */
    public IdentificateurEntier(String identificateur) {
        super(identificateur);

        if(!isIdentificateurEntier(identificateur)) {
           throw new InterpreteurException(identificateur
                                           + " n'est pas un identificateur"
                                           + " d'entier");
        }
    }

    /**
     * Pr�dicat attestant la validit� de l'identificateur
     * 
     * Un identificateur d'entier est valide si
     * - Il contient au maximum 24 caract�res
     * - Commence obligatoirement par une lettre (majuscule ou minuscule)
     * - suivie uniquement de lettres (majuscule ou minuscule) ou de chiffres
     * 
     * @param aTester � tester
     * @return true si l'identificateur est bien un identificateur d'entier
     *                 false sinon
     */
    public static boolean isIdentificateurEntier(String aTester) {
        return aTester.length() <= 25 && Character.isLetter(aTester.charAt(0))
            && isAlphanumerique(aTester.substring(1));
    }
}
