/**
 * Entier.java                              8 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.donnees.litteraux;

import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;

/**
 * Constante litt�rale de type entier
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class Entier extends Litteral {
    
    /** 
     * Initialisation de cet entier avec une valeur pass�e en argument
     * @param unEntier valeur de l'entier � construire
     */
    public Entier(int unEntier) {
    
        super.valeur = unEntier;
    }

    /** 
     * Initialisation de cet entier avec une valeur pass�e en argument
     * @param uneValeur cha�ne contenant l'entier � construire
     */
    public Entier(String uneValeur) {
        if (!isEntier(uneValeur)) {
            throw new InterpreteurException(uneValeur 
                                            + " n'est pas un nombre entier");
        }

        valeur = Integer.valueOf(uneValeur);
    }
    
    /** 
     * Pr�dicat de validit� d'une cha�ne en tant que nombre entier sign�
     * @param uneValeur la cha�ne � tester
     * @return true si uneValeur est un entier sinon false
     */
    public static boolean isEntier(String uneValeur) {
        if (uneValeur == null) {
            return false;
        }
        
        try {
            Integer.valueOf(uneValeur.trim());
        } catch (NumberFormatException lancee) {
            return false;
        }
        
        return true;
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
     * Compare cet entier � un autre entier
     * @param autre entier avec lequel this est compar�
     * @return une {@code valeur < 0} lorsque {@code autre > cet entier}
     *         une {@code valeur > 0} lorsque {@code autre < cet entier}
     *         une {@code valeur = 0} lorsque autre et cet entier sont �gaux
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
     * @return le r�sultat de premier auquel on soustrait second
     */
    public static Entier soustrait(Entier premier, Entier second) {
        return new Entier((int) premier.getValeur() - (int) second.getValeur());
        
    }
    
    /** 
     * Multiplication de deux entiers
     * @param premier Entier
     * @param second Entier
     * @return le r�sultat de premier multipli� par second
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
            throw new ExecutionException("division par 0");
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
            throw new ExecutionException("division par 0");
        }
        return new Entier((int) premier.getValeur() % (int) second.getValeur());
    }

    /* non javadoc
     * @see Litteral#compareTo(Litteral)
     */
    @Override
    public int compareTo(Litteral autre) {
        return ((Integer)valeur).compareTo((Integer)autre.valeur);
    }
}
