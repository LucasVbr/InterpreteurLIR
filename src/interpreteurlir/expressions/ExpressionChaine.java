/**
 * ExpressionChaine.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions;

import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.IdentificateurChaine;
import interpreteurlir.donnees.litteraux.Chaine;

/**
 * Expression de type Chaine qui peut être calculer.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class ExpressionChaine extends Expression {

    /**
     * Initalise une expression de type Chaine 
     * avec les liens nécessaires à son calcul.
     * @param texteExpression texte suivant la syntaxe d'une expression
     * @throws InterpreteurException si texteExpression n'est pas valide
     *                               ou amène à une incohérence de type
     */
    public ExpressionChaine(String texteExpression) {
        super();
        final String MESSAGE_ERREUR = "une expression ne peut être vide";
        
        String gauche;
        String droite;
        String aTraiter = texteExpression;
        
        /* chaîne null ou blanche */
        if (aTraiter == null || aTraiter.isBlank()) {
            throw new InterpreteurException(MESSAGE_ERREUR);
        }
        
        /* Traitement de la possible affectation */
        int indexEgal = aTraiter.indexOf('=');
        if (indexEgal > -1) {
            identificateursOperandes[INDEX_AFFECTATION] = 
                    new IdentificateurChaine(aTraiter.substring(0, indexEgal));
            aTraiter = aTraiter.substring(indexEgal + 1);
        }
        
        /* Traitement du nombre d'opérande */
        int indexPlus = aTraiter.indexOf('+');
        gauche = aTraiter;
        if (indexPlus > -1) {
            gauche = aTraiter.substring(0, indexPlus);
            droite = aTraiter.substring(indexPlus + 1, aTraiter.length());
            initialiserOperande(droite, INDEX_OPERANDE_D);
        }
        
        initialiserOperande(gauche, INDEX_OPERANDE_G);
    }

    /**
     * Initialise l'opérande à sa place dans l'expression.
     * @param operande représentation texte de l'opérande
     * @param index index de l'operande parmi INDEX_OPERANDE_G 
     *              et INDEX_OPERANDE_D
     * @throws IllegalArgumentException si index non valide
     */
    private void initialiserOperande(String operande, int index) {
        if (INDEX_OPERANDE_G != index && INDEX_OPERANDE_D != index) {
            throw new IllegalArgumentException("index invalide");
        }
        
        if (operandeEstLitteral(operande)) {
            litterauxOperandes[index] = new Chaine(operande);
        } else {
            identificateursOperandes[index] = 
                    new IdentificateurChaine(operande);
        }
    }

    /**
     * Détermine le genre de l'opérande (Chaine ou IdentificateurChaine).
     * @param operande représentation texte de l'opérande
     * @return true si operande est du genre Litteral sinon false
     */
    private static boolean operandeEstLitteral(String operande) {
        return operande.trim().startsWith("\"");
    }

    /* non javadoc
     * @see interpreteurlir.expressions.Expression#calculer()
     */
    @Override
    public Chaine calculer() {
        // TODO Auto-generated method stub
        return null;
    }

}
