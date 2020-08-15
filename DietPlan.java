import java.util.*;


class DietPlan {
    public static String leftDiet(int maxP, int maxC, int maxF, List<int[]> pcfs) {
        int sumP = 0;
        int sumC = 0;
        int sumF = 0;
        for(int[] diet: pcfs) {
            sumP += diet[0];
            sumC += diet[1];
            sumF += diet[2];
        }
        // getting all of the varieties in equal amount
        while(isValidDiet(maxP, maxC, maxF, sumP, sumC, sumF)) {
            maxP -= sumP;
            maxC -= sumC;
            maxF -= sumF;
        }
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            new Comparator<>() {
                public int compare(int[] a, int[] b) {
                    return (b[0] + b[1] + b[2])  - (a[0] + a[1] + a[2]) ;
                }
            }
        );
        for(int[] diet : pcfs) {
            if(isValidDiet(maxP, maxC, maxF, diet[0], diet[1], diet[2])) {
                maxHeap.add(diet);
            }
        }
        while(!maxHeap.isEmpty()) {
            int[] current = maxHeap.remove();
            if(isValidDiet(maxP, maxC, maxF, current[0], current[1], current[2])) {
                maxP -= current[0];
                maxC -= current[1];
                maxF -= current[2];
            } 
        }
        return maxP + " " + maxC + " " + maxF;
    }
    public static boolean isValidDiet(int remP, int remC, int remF, int P, int C,  int F) {
        return remP - P >= 0 && remC - C >= 0 && remF - F >= 0;
    }
    public static void main(String[] args) {
        List<int[]> pcfs = new ArrayList<>();
        pcfs.add(new int[] {4, 9, 2});
        pcfs.add(new int[] {4, 3, 2});
        pcfs.add(new int[] {7, 1, 3});
        System.out.println(leftDiet(130, 120, 110, pcfs));
    }
}