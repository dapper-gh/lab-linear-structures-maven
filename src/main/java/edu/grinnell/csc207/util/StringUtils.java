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
  // +------------------+--------------------------------------------
  // | Provided methods |
  // +------------------+
    
  /**
   * Determine whether the parens match in string.
   */
  public static boolean checkMatching(String str) {
    Stack<Character> parens = new LinkedStack<Character>();
    for (int i = 0; i < str.length(); i++) {
      char thisChar = str.charAt(i);
      if (thisChar == '(' || thisChar == '[') {
        try {
          parens.push(thisChar);
        } catch (Exception e) {
          // Stack is full, unable to compute
          return false;
        } // try-catch
      } else if (thisChar == ')' || thisChar == ']') {
        try {
          char popped = parens.pop();
          if (thisChar == ')' && popped != '(') {
            return false;
          } else if (thisChar == ']' && popped != '[') {
            return false;
          } // if-else-if
        } catch (Exception e) {
          // Stack is empty, too many close characters
          return false;
        } // try-catch
      } // if-else
    } // for

    return parens.isEmpty();
  } // checkMatching
} // class StringUtils    

