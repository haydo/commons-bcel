package Mini;

/**
 * Represents a function declaration and its arguments. Type information is contained
 * in the ASTIdent fields.
 *
 * @version $Id$
 * @author  <A HREF="http://www.berlin.de/~markus.dahm/">M. Dahm</A>
 */
public class Function implements org.apache.bcel.Constants, EnvEntry {
  private ASTIdent   name;         // Reference to the original declaration
  private ASTIdent[] args;         // Reference to argument identifiers
  private ASTExpr    body;         // Reference to function expression body
  private boolean    reserved;     // Is a key word?
  private int        line, column; // Short for name.getToken()
  private String     fun_name;     // Short for name.getName()
  private int        no_args;

  public Function(ASTIdent name, ASTIdent[] args) {
    this(name, args, false);
  }

  public Function(ASTIdent name, ASTIdent[] args, boolean reserved) {
    this.name     = name;
    this.args     = args;
    this.reserved = reserved;

    fun_name = name.getName();
    line     = name.getLine();
    column   = name.getColumn();
    setArgs(args);
  }
  
  public String toString() {
    StringBuffer buf = new StringBuffer();

    for(int i=0; i < no_args; i++) {
      buf.append(args[i].getName());

      if(i < no_args - 1)
	buf.append(", ");
    }

    String prefix = "Function " + fun_name + "(" + buf.toString() + ")";

    if(!reserved) 
      return prefix + " declared at line " + line + ", column " + column; 
    else
      return prefix + " <predefined function>";
  }

  public int        getNoArgs()       { return no_args; }
  public ASTIdent   getName()         { return name; }
  public String     getHashKey()      { return fun_name; }
  public int        getLine()         { return line; }
  public int        getColumn()       { return column; }
  public ASTIdent   getArg(int i)     { return args[i]; }
  public ASTIdent[] getArgs()         { return args; }
  public void       setArgs(ASTIdent[] args) { 
    this.args = args; 
    no_args   = (args == null)? 0 : args.length;
  }
}