/**
 * Etiquette.java                              13 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.programmes;

import interpreteurlir.InterpreteurException;

/**
 * Etiquette permettant de définir les ordres d'exécution 
 * des instructions d'un programme.
 * Le compteur ordinal pointe vers une étiquette.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class Etiquette implements Comparable<Etiquette> {

    /** Valeur minimale d'une étiquette */
    public static final int VALEUR_ETIQUETTE_MIN = 1;
    
    /** Valeur maximale d'une étiquette */
    public static final int VALEUR_ETIQUETTE_MAX = 99999;
    
    /** Message d'erreur pour une étiquette invalide */
    private static final String MSG_INVALIDE = 
            "valeur invalide pour une étiquette";
    
    /** valeur de cette étiquette */
    private int valeur;
    
    /**
     * Initialise une étiquette qui défini l'ordre d'exécution 
     * d'une instruction à partir d'un entier
     * @param valeur valeur de l'étiquette
     * @throws InterpreteurException si valeur n'appartient pas 
     *         à [ VALEUR_ETIQUETTE_MIN, VALEUR_ETIQUETTE_MAX ]
     */
    public Etiquette(int valeur) {
        super();
        if (valeur < VALEUR_ETIQUETTE_MIN || valeur > VALEUR_ETIQUETTE_MAX) {
            throw new InterpreteurException(MSG_INVALIDE);
        }
        
        this.valeur = valeur;
    }
    
    /**
     * Initialise une étiquette qui défini l'ordre d'exécution 
     * d'une instruction à partir d'un entier dans une chaîne de texte
     * @param valeur chaîne contenant la valeur de l'étiquette
     * @throws InterpreteurException si l'entier de valeur n'appartient pas 
     *         à [ VALEUR_ETIQUETTE_MIN, VALEUR_ETIQUETTE_MAX ]
     */
    public Etiquette(String valeur) {
        super();
        
        int valeurEntiere;
        
        try {
            valeurEntiere = Integer.valueOf(valeur.trim());
        } catch (NumberFormatException | NullPointerException lancee) {
            throw new InterpreteurException(MSG_INVALIDE);
        }
        
        if (valeurEntiere < VALEUR_ETIQUETTE_MIN 
                || valeurEntiere > VALEUR_ETIQUETTE_MAX) {
            throw new InterpreteurException(MSG_INVALIDE);
        }
        
        this.valeur = valeurEntiere;
    }

    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Integer.toString(valeur);
    }

    /**
     * @return valeur de valeur
     */
    public int getValeur() {
        return valeur;
    }

    /* non javadoc
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Etiquette aComparer) {
        return valeur - aComparer.valeur;
    }  

}
