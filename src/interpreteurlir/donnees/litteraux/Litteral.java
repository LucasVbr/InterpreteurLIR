/**
 * Litteral.java                                    7 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.donnees.litteraux;

/**
 * Valeur littérale utilisée dans une expression.
 * Chaque littérale est reconnue par sont type.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public abstract class Litteral implements Comparable<Litteral> {  
        
        /** valeur de ce littéral */
        protected Object valeur;
        
        /** 
         * Initialise ce littéral par défaut. 
         */
        protected Litteral() {
                super();
        }

        /** 
         * Initialise cette valeur avec un objet argument.
         * @param valeur valeur du littéral à construire
         */
        public Litteral(Object valeur) {
                super();
                this.valeur = valeur;
        }

        /**
         * @return la valeur de valeur
         */
        public Object getValeur() {
                return valeur;
        }

        /* non javadoc
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
                return valeur.toString();
        }

        /* non javadoc
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        @Override
        public abstract int compareTo(Litteral autre);
}