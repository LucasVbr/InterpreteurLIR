/**
 * Expression.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.Identificateur;
import interpreteurlir.donnees.litteraux.Litteral;

/**
 * Une expression contient tous les liens et donn�es n�cessaires � son calcul.
 * Une expression peut �tre calcul�e pour obtenir une valeur.
 * Elle peut affecter une valeur � une variable dans le contexte.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public abstract class Expression {
    
    /** Index de l'operande gauche */
    protected static final int INDEX_OPERANDE_G = 0;
    
    /** Index de l'operande droite */
    protected static final int INDEX_OPERANDE_D = 1;
    
    /** Index de de l'identificateur pour l'affectation */
    protected static final int INDEX_AFFECTATION = 2;
    
    /** Index du premier symbole de l'op�rateur */
    protected static final int INDEX_OPERATEUR_G = 0;
    
    /** Index du second symbole de l'op�rateur */
    protected static final int INDEX_OPERATEUR_D = 1;

    /** Contexte global pour acc�der aux donn�es. */
    protected static Contexte contexteGlobal;
    
    /** Identificateurs op�randes de cette l'expression */
    protected Identificateur[] identificateursOperandes;
    
    /** Litt�raux op�randes de cette expression */
    protected Litteral[] litterauxOperandes;
    
    /** 
     * Op�rateur de cette expression potentiellement 
     * compos� de plusieurs symboles 
     */
    protected char[] operateur;
    
    /**
     * Initialise une expression par d�faut avec les liens n�cessaires �
     * son calcul.
     */
    protected Expression() {
        super();
        final int NB_IDENTIFICATEUR = 3;
        final int NB_LITTERAL = 2;
        
        operateur = new char[2];
        operateur[INDEX_OPERATEUR_G] = '\u0000';
        operateur[INDEX_OPERATEUR_D] = '\u0000';
        
        identificateursOperandes = new Identificateur[NB_IDENTIFICATEUR];
        litterauxOperandes = new Litteral[NB_LITTERAL];
    }
    
    /**
     * Calculer la valeur de cette expression � ce moment pr�cis.
     * Peut acc�der au contexte.
     * @return un Litteral de valeur du r�sultat de l'expression
     * @throws RuntimeException si le contexte n'est pas r�f�rencer 
     *                          dans la classe Expression
     */
    public Litteral calculer() {
        if (contexteGlobal == null) {
            throw new RuntimeException("Le contexte doit �tre r�f�renc� "
                                       + "dans la classe Expression");
        }
        return null;  
    }   
    
    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder("");
        
        Identificateur affect = identificateursOperandes[INDEX_AFFECTATION];
        resultat.append(affect == null ? "" : (affect.toString() + " = "));
        
        if (litterauxOperandes[INDEX_OPERANDE_G] != null) {
            resultat.append(litterauxOperandes[INDEX_OPERANDE_G]);
        } else {
            resultat.append(identificateursOperandes[INDEX_OPERANDE_G]);
        }
        resultat.append(" ");
        
        if (operateur[INDEX_OPERATEUR_G] != '\u0000') {
            resultat.append(operateur[INDEX_OPERATEUR_G]);
        }
        if (operateur[INDEX_OPERATEUR_D] != '\u0000') {
            resultat.append(operateur[INDEX_OPERATEUR_D]);
        }
        
        resultat.append(" ");
        if (litterauxOperandes[INDEX_OPERANDE_D] != null) {
            resultat.append(litterauxOperandes[INDEX_OPERANDE_D]);
        } else if (identificateursOperandes[INDEX_OPERANDE_D] != null) {
            resultat.append(identificateursOperandes[INDEX_OPERANDE_D]);
        }       
        
        return resultat.toString().trim();
    }

    /**
     * R�f�rence le contexte pour acc�der aux variables lors du calcul.
     * Le r�f�rencement vaut pour toutes les expressions 
     * et est possible une unique fois.
     * @param aReferencer r�f�rence du contexte global
     * @return <ul><li>true si le contexte a pu �tre r�f�renc�</li>
     *             <li>true si aReferencer == contexte d�j� r�f�rencer</li>
     *             <li>false si aReferencer est null</li>
     *             <li>false si un contexte est d�j� r�f�rencer</li>
     *         </ul>
     */
    public static boolean referencerContexte(Contexte aReferencer) {
        if (aReferencer != null 
                && (contexteGlobal == null || aReferencer == contexteGlobal)) {
            contexteGlobal = aReferencer;
            return true;
        }
        return false;
    }
    
    /**
     * D�termine et cr�� une expression du bon type selon texteExpression.
     * Les types possibles sont ExpressionChaine ou ExpressionEntier.
     * @param texteExpression texte suivant la syntaxe d'une expression
     * @return l'expression du bon type correspondant � texteExpression
     * @throws InterpreteurException si texteExpression n'est pas valide
     *                               ou am�ne � une incoh�rence de type
     */
    public static Expression determinerTypeExpression(String texteExpression) {
        String aTraiter = texteExpression.trim();
        if (aTraiter.startsWith("$") || aTraiter.startsWith("\"")) {
            return new ExpressionChaine(aTraiter);
        } else {
            return new ExpressionEntier(aTraiter);
        }
    }
    
    /**
     * D�termine l'index du caract�re en dehors des constantes litt�rales
     * @param aTraiter cha�ne � traiter
     * @param caractere op�rateur � chercher hors guillemet
     * @return index dans � traiter du plus sinon -1 si aucun plus
     */
    public static int detecterCaractere(String aTraiter, char caractere) {
        char[] aTester = aTraiter.toCharArray();
        int indexPlus;
        int nbGuillemet = 0;
        for (indexPlus = 0 ; 
             indexPlus < aTester.length 
             && (aTester[indexPlus] != caractere || nbGuillemet % 2 != 0) ;
             indexPlus++) {
            
            if (aTester[indexPlus] == '"') {
                nbGuillemet++;
            } 
        }
        return indexPlus >= aTester.length ? -1 : indexPlus;
    }
}
