/**
 * Litteral.java                                    7 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.donnees.litteraux;

/**
 * Valeur litt�rale utilis�e dans une expression.
 * Chaque litt�rale est reconnue par sont type.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public abstract class Litteral implements Comparable<Litteral> {  
        
        /** valeur de ce litt�ral */
        protected Object valeur;
        
        /** 
         * Initialise ce litt�ral par d�faut. 
         */
        protected Litteral() {
                super();
        }

        /** 
         * Initialise cette valeur avec un objet argument.
         * @param valeur valeur du litt�ral � construire
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