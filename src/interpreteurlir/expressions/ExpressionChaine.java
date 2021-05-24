/**
 * ExpressionChaine.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions;

import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.Identificateur;
import interpreteurlir.donnees.IdentificateurChaine;
import interpreteurlir.donnees.litteraux.Chaine;

/**
 * Expression de type Chaine qui peut �tre calculer.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class ExpressionChaine extends Expression {

    /** Op�rateur possible pour ce type d'expression */
    private static final char OPERATEUR = '+';
    
    /**
     * Initialise une expression de type Chaine 
     * avec les liens n�cessaires � son calcul.
     * @param texteExpression texte suivant la syntaxe d'une expression
     * @throws InterpreteurException si texteExpression n'est pas valide
     *                               ou am�ne � une incoh�rence de type
     */
    public ExpressionChaine(String texteExpression) {
        super();
        final String MESSAGE_ERREUR = "une expression ne peut �tre vide";
        
        String gauche;
        String droite;
        String aTraiter = texteExpression;
        
        /* cha�ne null ou blanche */
        if (aTraiter == null || aTraiter.isBlank()) {
            throw new InterpreteurException(MESSAGE_ERREUR);
        }
        
        aTraiter = aTraiter.trim();
        
        /* Traitement de la possible affectation */
        int indexEgal = aTraiter.startsWith("$") ? aTraiter.indexOf('=') : -1;
        if (indexEgal > -1) {
            identificateursOperandes[INDEX_AFFECTATION] = 
                    new IdentificateurChaine(aTraiter.substring(0, indexEgal));
            aTraiter = aTraiter.substring(indexEgal + 1);
        }
        
        /* Traitement du nombre d'op�rande */
        int indexPlus = detecterCaractere(aTraiter, '+');
        gauche = aTraiter;
        if (indexPlus > -1) {
            gauche = aTraiter.substring(0, indexPlus);
            droite = aTraiter.substring(indexPlus + 1, aTraiter.length());
            operateur[INDEX_OPERANDE_G] = OPERATEUR;
            initialiserOperande(droite, INDEX_OPERANDE_D);
        }
        
        initialiserOperande(gauche, INDEX_OPERANDE_G);
    }

    /**
     * Initialise l'op�rande � sa place dans l'expression.
     * @param operande repr�sentation texte de l'op�rande
     * @param index index de l'operande parmi INDEX_OPERANDE_G 
     *              et INDEX_OPERANDE_D
     * @throws IllegalArgumentException si index non valide
     */
    private void initialiserOperande(String operande, int index) {
        if (INDEX_OPERANDE_G != index && INDEX_OPERANDE_D != index) {
            throw new IllegalArgumentException("index invalide");
        }
        
        if (Chaine.isChaine(operande)) {
            litterauxOperandes[index] = new Chaine(operande);
        } else {
            identificateursOperandes[index] = 
                    new IdentificateurChaine(operande);
        }
    }


    /* non javadoc
     * @see interpreteurlir.expressions.Expression#calculer()
     */
    @Override
    public Chaine calculer() {
        Chaine valeur;
        
        super.calculer(); // exception lev�e si pas de contexte
        
        /* D�termine op�randeGauche */
        Identificateur idGauche = 
                identificateursOperandes[INDEX_OPERANDE_G];
        Chaine operandeG = (Chaine)(idGauche == null 
                           ? litterauxOperandes[INDEX_OPERANDE_G] 
                           : contexteGlobal.lireValeurVariable(idGauche));
        
        /* D�termine possible operandeDroite */
        Identificateur idDroite = 
                identificateursOperandes[INDEX_OPERANDE_D];
        Chaine operandeD = null;
        if (idDroite != null || litterauxOperandes[INDEX_OPERANDE_D] != null) {
            operandeD = (Chaine)(idDroite == null 
                                 ? litterauxOperandes[INDEX_OPERANDE_D] 
                                 : contexteGlobal.lireValeurVariable(idDroite));
        }
        
        /* Calcul de la valeur */
        valeur = operandeD == null ? operandeG 
                                   : Chaine.concatener(operandeG, operandeD);
        
        /* Affectation si n�cessaire */
        if (identificateursOperandes[INDEX_AFFECTATION] != null) {
            contexteGlobal.ajouterVariable(
                    identificateursOperandes[INDEX_AFFECTATION], valeur);
        }
        
        return valeur;
    }    

}
