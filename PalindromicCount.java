import java.util.*;

/*
Palindromic Count
Problem Description

Given a string Str, and a set of numbers (S) which determine the length of palindromic substrings contained within S, find out the count of all such palindromes.

Constraints

0 < N <= 10 ^ 4

0 < M <= 10 ^ 4
Input

First line contains an integer N which is length of string

Second line contains a string of length N containing all lowercase characters

Third line contains an integer M which is the number of elements in set S

Fourth line contains M space separated integers which correspond to lengths of palindromic substrings that must be found within Str
Output

Count of all palindromic substrings which are of lengths corresponding to numbers in set S
Time Limit

1
Examples

There will be 3 palindromic substrings of length 6

abccbaabccba

abccbaabccba

abccbaabccba

Note: - Palindrome abcccba is counted twice since it appears twice in original string Str i.e. abccbaabccba

Example 2

Input

8

xyxzyxyz

3

1 3 5

Output

11

Explanation:

There will be 8 palindromic substrings of length 1

There will be 2 palindromic substrings of length 3

xyxzyxyz

xyxzyxyz

There will be 1 palindromic substring of length 5

xyxzyxyz

So total count is 11
*/
class PalindromicCount {

    public static int findPalindromicSubstrings(String s, int[] lens) {
        int count = 0;
        for (int len: lens) {
            for(int i = 0; i <= s.length() - len; i++) {
                boolean isPalindrome = isPalindrome(s, len, i);
                count +=  isPalindrome ? 1 : 0;
            }
        }
        return count;
    }
    private static boolean isPalindrome(String s, int k, int idx) {
        int i = idx;
        int j = idx + k - 1;
        boolean isPalindrome = true;
        while(j < s.length() && i < j) {
            if(s.charAt(i) != s.charAt(j)) {
                isPalindrome = false;
                break;
            }
            i++;
            j--;
        }
        return isPalindrome;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextInt(); // len of string
        String s = sc.next();
        int size = sc.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(findPalindromicSubstrings(s, arr));
        sc.close();
    }
}