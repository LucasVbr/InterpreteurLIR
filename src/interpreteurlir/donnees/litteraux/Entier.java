/**
 * Entier.java                              8 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.donnees.litteraux;

import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;

/**
 * Constante littérale de type entier
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class Entier extends Litteral {

    private static final int LONG_CH_MAX = 11;

    /** Valeur entière minimale */
    public static int VALEUR_MIN = Integer.MIN_VALUE;
    
    /** Valeur entière minimale */
    public static int VALEUR_MAX = Integer.MAX_VALUE;
    
    /** 
     * Initialisation de cet entier avec une valeur passée en argument
     * @param entier
     * @throws InterpreteurException lorsque entier n'est pas un Entier
     */
    @SuppressWarnings("boxing")
    public Entier(int entier) {
    
        if (VALEUR_MIN > entier && entier > VALEUR_MAX) {
            throw new InterpreteurException("Erreur. " + entier 
                                            + " n'est pas un entier. ");
        }
        super.valeur = entier;
    }

    /** 
     * Initialisation de cet entier avec une valeur passée en argument
     * @param uneValeur 
     */
    @SuppressWarnings("boxing")
    public Entier(String uneValeur) {
        int entier;
        try {
            entier = (int) Integer.valueOf(uneValeur);
        } catch (NumberFormatException lancee) {
            throw new InterpreteurException(uneValeur 
                                            + " n'est pas un nombre entier. ");
        }
        if (VALEUR_MIN > entier && entier > VALEUR_MAX) {
            valeur = entier;
        }
    }
    
    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return valeur.toString();
    }

    @Override
    public Integer getValeur() {
        return (Integer) super.valeur;
    }

    /** 
     * Compare cet entier à un autre entier
     * @param autre
     * @return une valeur < 0 lorsque autre > cet entier
     *         une valeur > 0 lorsque autre < cet entier
     *         une valeur = 0 lorsque autre et cet entier sont égaux
     */
    public int compareTo(Entier autre) {
        return ((Integer) valeur).compareTo(autre.getValeur());
    }

    /** 
     * Somme de deux entiers
     * @param premier Entier
     * @param second Entier
     * @return la somme des deux entiers
     */
    public static Entier somme(Entier premier, Entier second) {
        return new Entier((int) premier.getValeur() + (int) second.getValeur());
        
    }
    
    /** 
     * Soustraction de deux entiers
     * @param premier Entier
     * @param second Entier
     * @return le résultat de premier auquel on soustrait second
     */
    public static Entier soustrait(Entier premier, Entier second) {
        return new Entier((int) premier.getValeur() - (int) second.getValeur());
        
    }
    
    /** 
     * Multiplication de deux entiers
     * @param premier Entier
     * @param second Entier
     * @return le résultat de premier multiplié par second
     */
    public static Entier multiplie(Entier premier, Entier second) {
        return new Entier((int) premier.getValeur() * (int) second.getValeur());
    }
    
    /** 
     * Division de deux entiers
     * @param premier Entier
     * @param second Entier
     * @return le quotient de la division de premier par second
     * @throws ExecutionException lorsque le diviseur est nul
     */
    public static Entier quotient(Entier premier, Entier second) {
        if (second.compareTo(new Entier (0)) == 0) {
            throw new ExecutionException("Erreur. Division par 0. ");
        }
        return new Entier((int) premier.getValeur() / (int) second.getValeur());
    }
    
    /** 
     * Division de deux entiers
     * @param premier Entier
     * @param second Entier
     * @return le reste de la division de premier par second
     * @throws ExecutionException lorsque le diviseur est nul
     */
    public static Entier reste(Entier premier, Entier second) {
        if (second.compareTo(new Entier (0)) == 0) {
            throw new ExecutionException("Erreur. Division par 0. ");
        }
        return new Entier((int) premier.getValeur() % (int) second.getValeur());
    }
}
