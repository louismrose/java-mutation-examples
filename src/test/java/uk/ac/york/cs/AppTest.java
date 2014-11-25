package uk.ac.york.cs;

import static org.junit.Assert.*;
import org.junit.Test;

public class AppTest {

  @Test
  public void acceptsPalindrome() { 
    assertTrue(App.isPalindrome("noon"));
  }

  @Test
  public void rejectsNonPalindrome() { 
    assertFalse(App.isPalindrome("midnight"));
  }

  @Test
  public void rejectsNearPalindrome() { 
    assertFalse(App.isPalindrome("neon"));
  }

  @Test
  public void acceptsOddPalindrome() {
    assertTrue(App.isPalindrome("eve"));
  }
}