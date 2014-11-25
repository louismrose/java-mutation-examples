package uk.ac.york.cs;

/**
 * Hello world!
 *
 */
public class App 
{
  public static boolean isPalindrome(String s) {
    if (s.length() <= 1) {
      return true;
  
    } else {
      char first = s.charAt(0);
      char last = s.charAt(s.length() - 1);
      String mid = s.substring(1, s.length() - 1);
  
      return first == last && isPalindrome(mid);
    }
  }
}
