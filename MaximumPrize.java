import java.util.*;

// 5. MaximumPrice
class MaximumPrize {
    // 2->5->6-7
    // multiply max adj and add small adj
    /*
        bad in times of runtime, fails when array contains negaive
    */
    public static int maxPrice(Integer[] arr) {
        if(arr == null || arr.length == 0)
            return 0;
        if(arr.length == 0) return arr[0];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            new Comparator<>(){
                @Override
                public int compare(int[] a, int[] b) {
                    int maxb = Math.max(b[1], b[3]);
                    int minb = Math.min(b[1], b[3]);
                    int maxa = Math.max(a[1], a[3]);
                    int mina = Math.min(a[1], a[3]);
                    int profita = (maxa * a[2]) + mina;
                    int profitb = (maxb * b[2]) + minb;
                    // if the profit turns out to be same, we need the minium element to maximum
                    // the multiply then add possiblity
                    return  profita != profitb ? (profita - profitb) : (b[2] - a[2]);
                }
            }
        );
        int profit = 0;
        int deleted = 0;
        while(deleted != arr.length) {
            for(int i = 0; i < arr.length; i++) {
                // removed element;
                if(arr[i] == null)
                    continue;
                if(i == 0) {
                    int right = findElement(arr, i, false);
                    if(right == -1) right = 1;
                    else            right = arr[right];
                    minHeap.add(new int[] {i, 0, arr[i], right});
                } else if(i == arr.length - 1) {
                    int left = findElement(arr, i, true);
                    if(left == -1) left = 1;
                    else           left = arr[left];
                    minHeap.add(new int[] {i, left, arr[i], 0});
                } else {
                    // assuming all elements in the array are positive
                    int left = findElement(arr, i, true);
                    int right = findElement(arr, i, false);
                    if(left != -1)  left = arr[left];
                    if(right != -1) right = arr[right];
                    if(left == -1 && right == -1) {
                        left = 0;
                        right = 1;
                    } else if(left == -1) {
                        left = 0;
                    } else if(right == -1) {
                        right = 0;
                    }
                    minHeap.add(new int[] {i, left, arr[i], right});
                }
                if(minHeap.size() > 1) {
                    minHeap.remove();
                }
            }
            deleted++;
            int[] current = minHeap.remove();
            arr[current[0]] = null;
            System.out.println(current[2]);
            int max = Math.max(current[1], current[3]);
            int min = Math.min(current[1], current[3]);
            profit += ((current[2] * max) + min);
            //System.out.println(profit);
        }
        return profit;
    }
    public static int findElement(Integer[] arr, int idx, boolean left) {
        if(left) {
            for(int i = idx - 1; i >= 0; i--) {
                if(arr[i] != null) {
                    return i;
                }
            }
        } else {
            for(int i = idx + 1; i < arr.length; i++) {
                if(arr[i] != null) {
                    return i;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        Integer[] arr = {2,5,6,7};
        System.out.println(maxPrice(arr));
        sc.close();
        long endTime = System.currentTimeMillis();
        System.out.println("runtime is " + (endTime - startTime));
    }
}