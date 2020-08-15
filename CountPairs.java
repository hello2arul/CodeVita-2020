import java.util.*;



class CountPairs {
    public static int countPairs(int[] arr, int k) {
        int happyNumbers = 0;
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                if(i != j && arr[j] - k <= arr[i] && arr[i] <= arr[j] + k) {
                    //System.out.println(arr[i]);
                    happyNumbers++;
                    break;
                }
            }
        }
        return happyNumbers;
    }
    public static void main(String[] args) {
        System.out.println(countPairs(new int[] {5,5,7,9,15,2}, 3));
    }
}