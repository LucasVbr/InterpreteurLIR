/*
 * ExecuteurDeTest.java                                 13 avr. 2021
 * IUT info1 2020-2021, groupe 2, aucun droit d'auteur
 */
package outils.glg;

import java.lang.reflect.Method;


/** 
 * Lanceur de test automatique ("test runner") qui exécute les méthodes de tests
 * unitaires d'une classe de test telle que :
 * <ul><li>La classe de test doit être instanciable avec des méthodes de tests
 *         unitaires qui sont des méthodes d'instance</li>
 *     <li>Les jeux d'essai mis en champ d'instance sont régénérés 
 *         automatiquement à chaque instanciation de la classe de test
 *         = "fixture de test" (fix picture of test)</li>
 *     <li>Chaque méthode de test s'exécute dans sa propre instance donc avec
 *         la fixture remise dans son état initial</li>
 *     <li>Chaque méthode de test unitaire ne doit tester qu'une seule méthode 
 *         de la classe à tester</li>
 *     <li>Chaque méthode de test unitaire s'arrête en cas d'échec 
 *         de test (failure) ou de crash (error)</li>
 * </ul>
 *  
 * @author info1 2020-2021
 */
public class ExecuteurDeTest {

    /** préfixe des méthodes de tests unitaires à lancer */
    public final static String PREFIXE_TEST = "test";
    
    /** Code erreur si la ligne de commande est mal formatée */
    public static final int ERR_NB_ARGUMENT = 1;
    
    /** Code erreur si la classe de test (argument) n'est pas trouvée */
    public static final int ERR_CLASSE_INACCESSIBLE = 2;

    /** Message d'aide à l'usage de l'outil */
    private static final String MESSAGE_USAGE 
    = "usage :  info1.outils.glg.ExecuteurDeTest nom.complet.de.classe.de.Test";
    
    /** 
     * Lancement automatique des méthodes de test
     * d'une classe de test passée en argument
     * @param args nom java complet de la classe de test à exécuter
     */
    public static void main(String[] args) {
        
        /* Analyse de la ligne de commande */
        if (args.length != 1) {
            System.err.println("Nombre d'arguments incorrects");
            System.err.println(MESSAGE_USAGE);
            System.exit(ERR_NB_ARGUMENT);
        }
        Class<?> deTest = null;
        try {
            deTest = Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            System.err.println("Classe " + args[0] + " non accessible");
            System.err.println(MESSAGE_USAGE);
            System.exit(ERR_CLASSE_INACCESSIBLE);
        }
        
        /* Lancement des méthodes de tests unitaires */
        Method[] aFiltrer = deTest.getDeclaredMethods();
        for (Method aExecuter : aFiltrer) {
            String nomMethode = aExecuter.getName();
            if (nomMethode.startsWith(PREFIXE_TEST)) {
                try {
                    aExecuter.invoke(deTest.getConstructor().newInstance());
                    /* test Ok */
                    System.out.println("Réussite de " + nomMethode);
                } catch (Exception aAnalyser) {
                    if (aAnalyser.getCause() instanceof EchecTest) {
                        System.out.println("Echec de " + nomMethode);
                    } else {
                        System.err.println("Crash de " + nomMethode + " : "
                                           + aAnalyser.getCause().getMessage());
                    }
                } 
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            }
        }
    }
}
