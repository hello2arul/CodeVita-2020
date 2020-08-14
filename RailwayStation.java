import java.util.*;


class RailwayStation {
    public static int minStations(int[][] times) {
        // sort based on starting time
        Arrays.sort(times, new Comparator<>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        // put the end time in a minHeap
        int minPlatforms = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int[] time : times) {
            if(!minHeap.isEmpty()) {
                // if the min ending time is less than the current starting time, 
                // remove it and put the current ending
                if(minHeap.peek() < time[0]) {
                    minHeap.remove();
                } else {
                    minPlatforms++;
                }
            } else {
                minPlatforms++;
            }
            minHeap.add(time[0] + time[1]);
        }
        return minPlatforms;
    }
    public static void main(String[] args) {
        System.out.println(minStations(new int[][] {{2, 4}, {6,2}}));
    }
}