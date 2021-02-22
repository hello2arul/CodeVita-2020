import java.util.*;
/*
T9 Inverse
Problem Description

Suppose you have a mobile phone which has a large set of contacts. At times it might get tedious to search a group of contacts, for e.g. names that contain certain alphabets in the name, not necessarily begins with that alphabet.

As you know, in a common dial-pad each digit from 2 to 9 is associated with some alphabets. When a number key is pressed all the names in the contact list which have any characters corresponding to the pressed numerical key are shown in the search result. For e.g. If numerical key 7 is pressed, all the names in contact list that have characters {p, q, r, s} anywhere in the contact names are shown.

If a second key is pressed, all combinations of first key characters an second key characters that appear in the contact names in the same order are shown in the search result. For example, if 7 is the first key that is pressed and 4 is the second key that is pressed, then all contact names that can be formed out of combination operation of {p, q, r, s} and {g, h, i}, in an order preserving manner i.e. 7-key characters followed by4-key characters viz {pg, ph, pi, qg, qh, qi, rg, rh, ri, sg, sh, si} are shown in the search result.

Similarly, if a third key is pressed, there will be even more number of combinations that will be searched by the phone.

Write a code to find which numerical keys should be pressed in what order if the desired search result is provided as input. If there are two or more key combinations to get the desired contact list, print the lexographically greater value as output.

Constraints

1 < S < 1000
Input

First line contains a string S denoting all the contact names separated by space that need to be obtained as a outcome of search operation
Output

Print lexicographically greater value as number key patterns which when pressed in that order would produce the input string S. Print "NA", if no key combinations are possible to get all the names in one search operations
Time Limit

1
Examples

Example 1:

Input:

Abhishek Ipsita Kamlesh Nisha Yash Sharon

Output:
74

Explanation:

Assume that the contact list contains only first names.


Abhishek, Kamlesh, Nisha, Yash and Sharon, all contain 'sh'. Ipsita contains 'si'. Hence pressing key '7' followed by key '4' will give the expected search result.

Example 2:

Input:

Yash Amol Mitul

Output:

NA






*/
class Main {

    public static String findCombo(String[] names) {
        if(names == null || names.length == 0 || names[0].length() == 0) {
            return "NA";
        }
        TreeSet<String> results = new TreeSet<>(
            new Comparator<String>() {
                public int compare(String s1, String s2) {
                    return s1.length() == s2.length() ? s2.compareTo(s1) : Integer.compare(s2.length(), s1.length()); 
                }
            }
        );
        //System.out.println(Arrays.toString(names));
        // conver to lowercase
        for(int i = 0; i < names.length; i++) {
            names[i] = names[i].toLowerCase();
        }
        String name = names[0];
        String cur = "";

        for(int i = 0; i < name.length(); i++) {
            cur += name.charAt(i);
            // check if current is present in every other string
            for (int j = 1; j < names.length; j++) {
                if (!containsSubstring(cur, names[j])) {
                    cur = "";
                    break;
                } else {
                    //System.out.println(cur);
                }
            }
            if (!cur.isEmpty()) {
                results.add(mapToNumber(cur));
            }
        }
        //System.out.println(results);
        return !results.isEmpty() ? results.first() : "NA";
    }
    private static String mapToNumber(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            res.append(getNumber(s.charAt(i)));
        }
        return res.toString();
    }

    private static int getNumber(char c) {
        switch(c) {
            case 'a':
            case 'b':
            case 'c':
                return 2;
            case 'd':
            case 'e':
            case 'f':
                return 3;
            case 'g':
            case 'h':
            case 'i':
                return 4;
            case 'j':
            case 'k':
            case 'l':
                return 5;
            case 'm':
            case 'n':
            case 'o':
                return 6;

            case 'p':
            case 'q':
            case 'r':
            case 's':
                return 7;
            case 't':
            case 'u':
            case 'v':
                return 8;

            case 'w':
            case 'x':
            case 'y':
            case 'z':
                return 9;
        }
        return -1;
    }
    private static boolean containsSubstring(String sub, String s) {
        if (s == null || sub == null) {
            return false;
        }
        for (int i = 0; i <= s.length() - sub.length(); i++) {
            boolean doesMatch = true;
            for(int k = 0; k < sub.length(); k++) {
                int num1 = getNumber(sub.charAt(k));
                int num2 = getNumber(s.charAt(k + i));
                if (num1 != num2) {
                    doesMatch = false;
                    break;
                }
            }
            if (doesMatch)  return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] names = sc.nextLine().split("\\s+");
        
        System.out.println(findCombo(names));
        sc.close(); 
    }
}