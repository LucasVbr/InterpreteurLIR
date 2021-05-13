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

    /** Valeur entière minimale */
    public static int VALEUR_MIN = Integer.MIN_VALUE;
    
    /** Valeur entière minimale */
    public static int VALEUR_MAX = Integer.MAX_VALUE;
    
    /** Valeur entière minimale */
    public static int VALEUR_DEFAUT = 1;
    
    //TODO constructeur avec string
    
    /**
     * Initialisation de cet entier avec une valeur par défaut
     */
    @SuppressWarnings("boxing")
    public Entier() {
        //super(Integer.valueOf(VALEUR_DEFAUT));
        super.valeur = VALEUR_DEFAUT;
    }
    
    /** 
     * Initialisation de cet entier avec une valeur passée en argument
     * @param unEntier
     * @throws InterpreteurException lorsque entier n'est pas un Entier
     */
    @SuppressWarnings("boxing")
    public Entier(int unEntier) {

        if (! isEntier(unEntier)) {
            throw new InterpreteurException("Erreur. " + unEntier 
                                            + " n'est pas un entier. ");
        }
        super.valeur = unEntier;
    }

    private static boolean isEntier(int entier) {
        return VALEUR_MIN <= entier && entier <= VALEUR_MAX;
    }

    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return valeur.toString();
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

    @Override
    public Integer getValeur() {
        return (Integer) super.valeur;
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
