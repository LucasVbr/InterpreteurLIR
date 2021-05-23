/**
 * Chaine.java                                    7 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.donnees.litteraux;

import interpreteurlir.InterpreteurException;

/**  
 * Constante littérale de type chaîne de caractères.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class Chaine extends Litteral {
        
        /** Longueur maximale d'une chaîne */
        public static final int LG_MAX_CHAINE = 70;
        
        /** Erreur chaîne trop longue */
        private static final String ERREUR_LG_MAX = 
                "longueur maximale d'une chaîne dépassée";
        
        /** Erreur constante littéral chaîne invalide */
        private static final String ERREUR_INVALIDE = 
                " n'est pas une constante de type chaîne";
        
        /**
         * initialise cette chaîne avec  une valeur par défaut.
         */
        public Chaine() {
                valeur = "";
        }

        /** 
         * Initialise une chaîne avec la séquence 
         * de caractères passée en argument.
         * @param uneValeur valeur de la chaine à construire (entre guillemets)
         */
        public Chaine(String uneValeur) {
            uneValeur = uneValeur.trim();
            if (!isChaine(uneValeur)) {
                throw new InterpreteurException(uneValeur + ERREUR_INVALIDE);
            }

            uneValeur = uneValeur.substring(1, uneValeur.length() - 1);

            if (uneValeur.length() > LG_MAX_CHAINE) 
                throw new InterpreteurException(ERREUR_LG_MAX);

            valeur = uneValeur;
        }

        /** 
         * Prédicat de validité d'une constante littérale de type chaîne.
         * @param uneValeur valeur aTester
         * @return true si uneValeur est une chaîne sinon false
         */
        public static boolean isChaine(String uneValeur) {
            uneValeur = uneValeur.trim();
            return  uneValeur.length() >= 2 
                    && uneValeur.startsWith("\"") && uneValeur.endsWith("\"");
        }
        
        /** 
         * Concatène deux chaînes ensemble. Opération non commutative: 
         * a + b != b + a
         * @param a une Chaîne
         * @param b une autre Chaîne
         * @return une nouvelle Chaîne.
         */
        public static Chaine concatener(Chaine a, Chaine b) {
            return new Chaine("\"" + a.valeur + b.valeur + "\"");
        }
        
        /* non javadoc
         * @see Litteral#compareTo(Litteral)
         */
        @Override
        public int compareTo(Litteral autre) {
                return valeur.toString().compareTo(autre.valeur.toString());
        }

        /* non javadoc
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
                return '\"' + valeur.toString() + '\"';
        }
}