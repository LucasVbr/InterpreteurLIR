/**
 * Chaine.java                                    7 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.donnees.litteraux;

import interpreteurlir.InterpreteurException;

/**  
 * Constante litt�rale de type cha�ne de caract�res.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class Chaine extends Litteral {
        
        /** Longueur maximale d'une cha�ne */
        public static final int LG_MAX_CHAINE = 70;
        
        /** Erreur cha�ne trop longue */
        private static final String ERREUR_LG_MAX = 
                "Longueur maximale d'une cha�ne d�pass�e";
        
        /** Erreur constante litt�ral cha�ne invalide */
        private static final String ERREUR_INVALIDE = 
                "syntaxe incorrecte pour une constante de type cha�ne : ";
        
        /**
         * initialise cette cha�ne avec  une valeur par d�faut.
         */
        public Chaine() {
                valeur = "";
        }

        /** 
         * Initialise une cha�ne avec la s�quence de caract�res pass�e en argument.
         * @param uneValeur
         */
        public Chaine(String uneValeur) {
            uneValeur = uneValeur.trim();
                if (!uneValeur.startsWith("\"") || !uneValeur.endsWith("\"")) {
                    throw new InterpreteurException(ERREUR_INVALIDE 
                                                    + uneValeur);
                }
                
                uneValeur = uneValeur.substring(1, uneValeur.length() - 1);
                
                if (uneValeur.length() > LG_MAX_CHAINE) 
                    throw new InterpreteurException(ERREUR_LG_MAX);
                    
                valeur = uneValeur;
        }
        
        /** 
         * Concat�ne deux cha�nes ensemble. Op�ration non commutative: 
         * a + b != b + a
         * @param a une Cha�ne
         * @param b une autre Cha�ne
         * @return une nouvelle Cha�ne.
         */
        public static Chaine concatener(Chaine a, Chaine b) {
                
                return new Chaine("\"" + a.valeur + b.valeur + "\"");
        }
        
        /* non javadoc
         * @see interpreteurlir.donnees.litteraux.Litteral#compareTo(interpreteurlir.donnees.litteraux.Litteral)
         */
        @Override
        public int compareTo(Litteral autre) {
                return this.valeur.toString().compareTo(autre.valeur.toString());
        }

        /* non javadoc
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
                return '\"' + valeur.toString() + '\"';
        }
}