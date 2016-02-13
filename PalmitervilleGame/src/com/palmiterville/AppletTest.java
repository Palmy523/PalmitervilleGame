package com.palmiterville;


public class AppletTest {

	public static void main(String[] args) {
		System.out.println(funReverse("hello"));
	}
    public static String funReverse(String s) {
        for (int i = 0; i < s.length(); i++) {
          s = reverse(s, i);
        }
        return s;
    }
    
    public static String reverse(String s, int index) {
      String reverse;
      if (index == 0) {
        reverse = "";
      } else if (index == s.length() - 1) {
        return s;
      } else {
        reverse = s.substring(0, index);
      }
      for (int i = s.length() - 1; i >= index; i--) {
        reverse.concat(String.valueOf(s.charAt(i)));
      }
      return reverse;
    }
	
}
