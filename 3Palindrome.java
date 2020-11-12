import java.util.*;

/*
    Given an input string word, split the string into exactly 3 palindromic substrings.
    Working from left to right, choose the smallest split for the first substring that 
    still allows the remaining word to be split into 2 palindromes.
*/


class ThreePalindrome {
    public static String splitIntoThreePalindromes(String s) {
        for(int i = 1; i <= s.length(); i++) {
            String current = s.substring(0, i);
            if(isPalindrome(current)) {
                for(int j = i + 1; j < s.length(); j++) {
                    String second = s.substring(i, j);
                    if(isPalindrome(second)) {
                        String third = s.substring(j);
                        if(isPalindrome(third)) {
                            return current + "\n" + second + "\n" + third;
                        }
                    }
                }
            }
        }
        return "impossible";
    }
    public static boolean isPalindrome(String s) {
        for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if(s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(splitIntoThreePalindromes(s));
        sc.close();
    }
}
