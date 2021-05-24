/**
 * ExpressionEntier.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions;

import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.Identificateur;
import interpreteurlir.donnees.IdentificateurEntier;
import interpreteurlir.donnees.litteraux.Entier;

/**
 * Expression de type Entier qui peut �tre calculer.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class ExpressionEntier extends Expression {
    
    /** Liste des op�rateurs possibles sur les entiers */
    private static final char[] OPERATEURS = {'+', '-', '*', '/', '%'};
    
    /** message d'erreur de cha�ne null ou vide */
    private static final String ERREUR_VIDE = 
            "une expression ne peut �tre vide";
    
    /** Erreur op�rande attendue */
    private static final String OPERANDE_D_MANQUANT = 
            " attend un op�rande droit";
    

    /**
     * Initialise une expression de type Entier avec les liens n�cessaires � son
     * calcule.
     * @param texteExpression texte suivant la syntaxe d'une expression
     * @throws InterpreteurException si texteExpression n'est pas valide
     *                               ou am�ne � une incoh�rence de type
     */
    public ExpressionEntier(String texteExpression) {
        super();
        String gauche;
        String droite;
        String aTraiter;
        
        if (texteExpression == null || texteExpression.isBlank()) {
            throw new InterpreteurException(ERREUR_VIDE);
        }
        
        aTraiter = texteExpression.trim();
        
        /* Traitement d'une �ventuelle affectation */
        int indexEgal = aTraiter.indexOf('=');
        if (indexEgal > 0) {
            identificateursOperandes[INDEX_AFFECTATION] = 
                    new IdentificateurEntier(aTraiter.substring(0, indexEgal)
                                                     .trim());
        
            aTraiter = aTraiter.substring(indexEgal + 1).trim();
        }
        
        /* Rep�rage de l'op�rateur et de l'op�rande droite s'ils existent */
        int indexOperateur = detecterOperateur(aTraiter);
        gauche = aTraiter.trim();
        if (indexOperateur > 0) {
            operateur[INDEX_OPERATEUR_G] = aTraiter.charAt(indexOperateur);
            gauche = aTraiter.substring(0, indexOperateur).trim();
            
            if (aTraiter.length() - 1 <= indexOperateur) {
                throw new ExecutionException(aTraiter + OPERANDE_D_MANQUANT);
            }
            
            droite = aTraiter.substring(indexOperateur + 1).trim();
            initialiserOperande(droite, INDEX_OPERANDE_D);
        }
        
        initialiserOperande(gauche, INDEX_OPERANDE_G);
    }

    /**
     * D�tecte la pr�sence d'un op�rateur dans cette expression et renvoie
     * sa position
     * @param expression dont on cherche � conna�tre la position de l'op�rande
     * @return position sous forme d'entier, -1 si pas d'op�rateur
     */
    private static int detecterOperateur(String expression) {
        for (int i = 1 ; i < expression.length() ; i++) {
            for (char operateur : OPERATEURS) {
                if (operateur == expression.charAt(i)) {
                    return i;
                }
            }
        }
        
        return -1;
    }

    /**
     * Initialise l'op�rande � sa place dans l'expression.
     * @param operande op�rande � initialiser
     * @param index de l'op�rande
     */
    private void initialiserOperande(String operande, int index) {
        if (INDEX_OPERANDE_G != index && INDEX_OPERANDE_D != index) {
            throw new IllegalArgumentException("index invalide");
        }
        
        if (Entier.isEntier(operande)) {
            litterauxOperandes[index] = new Entier(operande);
        } else {
            identificateursOperandes[index] = 
                    new IdentificateurEntier(operande);
        }
        
    }

    /* non javadoc
     * @see interpreteurlir.expressions.Expression#calculer()
     */
    @Override
    public Entier calculer() {
        Entier valeur;
        super.calculer();
        
        /* D�termine op�randeGauche */
        Identificateur idGauche = 
                identificateursOperandes[INDEX_OPERANDE_G];
        Entier operandeG = (Entier)(idGauche == null 
                           ? litterauxOperandes[INDEX_OPERANDE_G] 
                           : contexteGlobal.lireValeurVariable(idGauche));
        
        /* D�termine possible operandeDroite */
        Identificateur idDroite = 
                identificateursOperandes[INDEX_OPERANDE_D];
        Entier operandeD = null;
        if (idDroite != null || litterauxOperandes[INDEX_OPERANDE_D] != null) {
            operandeD = (Entier)(idDroite == null 
                                 ? litterauxOperandes[INDEX_OPERANDE_D] 
                                 : contexteGlobal.lireValeurVariable(idDroite));
        }
        
        /* Calcul de la valeur */
        valeur = operandeD == null 
                 ? operandeG 
                 : switch (operateur[INDEX_OPERATEUR_G]) {
                   case '+' -> Entier.somme(operandeG, operandeD);
                   case '-' -> Entier.soustrait(operandeG, operandeD);
                   case '*' -> Entier.multiplie(operandeG, operandeD);
                   case '/' -> Entier.quotient(operandeG, operandeD);
                   case '%' -> Entier.reste(operandeG, operandeD);
                   default -> operandeG;
                   };
        
        /* Affectation si n�cessaire */
        if (identificateursOperandes[INDEX_AFFECTATION] != null) {
            contexteGlobal.ajouterVariable(
                    identificateursOperandes[INDEX_AFFECTATION], valeur);
        }
        
        return valeur;
    }

}