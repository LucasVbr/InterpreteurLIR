/*
 * Identificateur.java , 08/05/2021
 * IUT Rodez 2020-2021, info1
 * pas de copyright, aucun droits
 */

package interpreteurlir.donnees;

import interpreteurlir.InterpreteurException;

/**
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class Identificateur /* extends Variable */
implements Comparable<Identificateur> {

    /** Longueur maximale d'un identificateur (ne prend pas en compte le $) */
    public static final int LONGUEUR_MAX = 25;

    /** Nom identificateur */
    private String nom;

    /**
     * Instantiation de l'identificateur
     * @param identificateur
     */
    public Identificateur(String identificateur) {
        super();
        identificateur = identificateur.trim();
        if(!isIdentificateur(identificateur)) {
            throw new InterpreteurException(identificateur
                    + " n'est pas un identificateur");
        }

        nom = identificateur;
    }

    /**
     * Prédicat qui vérifie si une chaîne correspond à un identificateur
     * <ul>
     *     <li>Longueur comprise entre 1 et 24 caractères</li>
     *     <li>N'est pas une chaîne vide</li>
     *     <li>N'est pas null</li>
     * </ul>
     * @param aTester
     * @return true si le prédicat est vérifié
     *         false sinon
     */
    public static boolean isIdentificateur(String aTester) {
        return aTester != null
                && aTester.length() > 0
                && !removeDollar(aTester).isBlank()
                && removeDollar(aTester).length() <= LONGUEUR_MAX
                && isAlphanumerique(removeDollar(aTester));
    }

    /**
     * Supprime le caractère $ si il est présent en premier dans la chaîne
     * @param chaine a modifier
     * @return la chaîne modifiée
     */
    public static String removeDollar(String chaine) {
        if(chaine.charAt(0) == '$') return chaine.substring(1);
        return chaine;
    }

    /**
     * Prédicat testant si une chaîne est composée de chiffre ou de lettres
     * @param aTester chaîne a tester
     * @return true si le prédicat est vérifié
     *         false sinon
     */
    public static boolean isAlphanumerique(String aTester) {
        int index;
        for (index = 0 ;
            index < aTester.length()
            && Character.isLetterOrDigit(aTester.charAt(index)) ;
            index++)
            ;    // Corps vide

            return index >= aTester.length();
    }

    /**
     * @return la valeur de nom
     */
    public String getNom() {
        return nom;
    }

    /* non javadoc - @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return nom;
    }

    /* non javadoc - @see java.lang.String#Comparable() */
    @Override
    public int compareTo(Identificateur aComparer) {
        return nom.compareTo(aComparer.nom);
    }
}
