/*
 * App.java
 */
package iut_rodez.lir_lang;

import iut_rodez.lir_lang.core.LirLexer;
import iut_rodez.lir_lang.core.LirParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/** Main application class for LIR language processing. */
public class App {

  /**
   * Main method to run the LIR parser.
   *
   * @param args Command line arguments (not used).
   */
  public static void main(String[] args) {
    LirLexer lexer = new LirLexer(CharStreams.fromString("10"));
    LirParser parser = new LirParser(new CommonTokenStream(lexer));
    ParseTree tree = parser.prog();
    System.out.println(tree.toStringTree(parser));
  }
}
