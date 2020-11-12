import java.util.Scanner;

import java.util.*;
/*
    Given an array arr, of size N, find whether it is possible to rearrange the elements
    of array such that sum of no two adjacent elements is divisible by 3.
*/
// bruteforce
class FactorsOfThree {
    public static String isPossible(int[] arr) {
        int prev = arr[0];
        boolean[] included = new boolean[arr.length];
        included[0] = true;
        boolean solved = false;
        while(!solved) {
            solved = true;
            for(int i = 1; i < arr.length; i++) {
                if(!included[i] && (prev + arr[i]) % 3 != 0) {
                    included[i] = true;
                    prev = arr[i];
                    solved = false;
                }
            }
            if(solved) {
                for(boolean b: included) {
                    if(!b){
                        return "No";
                    }
                }
            }
        }
        return "Yes";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int size = sc.nextInt();
            int[] arr = new int[size];
            for(int i = 0; i < size; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.println(isPossible(arr));
        }
        sc.close();
    }
}
