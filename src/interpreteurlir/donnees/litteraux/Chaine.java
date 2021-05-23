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
                "Longueur maximale d'une chaîne dépassée";
        
        /** Erreur constante littéral chaîne invalide */
        private static final String ERREUR_INVALIDE = 
                "syntaxe incorrecte pour une constante de type chaîne : ";
        
        /**
         * initialise cette chaîne avec  une valeur par défaut.
         */
        public Chaine() {
                valeur = "";
        }

        /** 
         * Initialise une chaîne avec la séquence de caractères passée en argument.
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