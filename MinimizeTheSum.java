import java.util.*;

// 16. MinimizeTheSUm
class MinimizeTheSum {
    // not considering negatives
    public static int miniizeTheSum(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int sum = 0;
        for(int i : arr) {
            sum += i;
            maxHeap.add(i);
        }
        int editSum = 0;
        while(k-- > 0) {
            maxHeap.add(maxHeap.remove() / 2);
        }
        while(!maxHeap.isEmpty()) {
            editSum += maxHeap.remove();
        }
        return Math.min(sum, editSum);
    }
    public static void main(String[] args) {
        System.out.println(miniizeTheSum(new int[] {20, 7, 5, 4}, 3));
    }    
}