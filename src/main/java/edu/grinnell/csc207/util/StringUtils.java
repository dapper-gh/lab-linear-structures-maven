package edu.grinnell.csc207.util;

import edu.grinnell.csc207.linear.Stack;
import edu.grinnell.csc207.linear.LinkedStack;

import java.io.PrintWriter;

/**
 * Assorted utilities for working with strings.
 * 
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */ 
public class StringUtils {
  private static class StartingToken {
    public int index;
    public char symbol;

    public StartingToken(int index1, char symbol1) {
      this.index = index1;
      this.symbol = symbol1;
    }

    public char getClosing() {
      if (this.symbol == '[') {
        return ']';
      } else if (this.symbol == '(') {
        return ')';
      } else {
        throw new RuntimeException("Invalid char in StartingToken");
      } // if-else
    } // getClosing()
  } // class StartingToken

  // +------------------+--------------------------------------------
  // | Provided methods |
  // +------------------+
    
  /**
   * Determine whether the parens match in string.
   */
  public static void checkMatching(String str) throws Exception {
    Stack<StartingToken> parens = new LinkedStack<StartingToken>();
    for (int i = 0; i < str.length(); i++) {
      char thisChar = str.charAt(i);
      if (thisChar == '(' || thisChar == '[') {
        try {
          parens.push(new StartingToken(i, thisChar));
        } catch (Exception e) {
          // Stack is full, unable to compute
          throw new Exception("Internal stack is full");
        } // try-catch
      } else if (thisChar == ')' || thisChar == ']') {
        StartingToken popped;
        try {
          popped = parens.pop();
        } catch (Exception e) {
          throw new Exception("Unmatched closing parenthesis");
        }
        if (thisChar != popped.getClosing()) {
          throw new Exception("Closing token '"
          + thisChar
          + "' at position "
          + i
          + " does not match closing token '"
          + popped.symbol
          + "' at position "
          + popped.index);
        } // if
      } // if-else
    } // for

    if (!parens.isEmpty()) {
      StartingToken popped = parens.pop();
      throw new Exception("Unmatched opening token '"
        + popped.symbol
        + "' at position '"
        + popped.index
        + "'");
    } // if
  } // checkMatching
} // class StringUtils    

