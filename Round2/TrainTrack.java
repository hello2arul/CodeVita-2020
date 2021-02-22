import java.util.*;

class Main {    

    public static int minStations(int[][] times, int target) {
        // sort based on starting time
        Arrays.sort(times, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1])   return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            }
        });
        // put the end time in a minHeap
        int platforms = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int[] time : times) {
            if(!minHeap.isEmpty()) {
                // if the min ending time is less than the current starting time, 
                // remove it and put the current ending
                if(minHeap.peek() < time[1]) {
                    minHeap.remove();
                    platforms--; 
                } else {
                    platforms++;
                }
            } else {
                platforms++;
            }
            if (time[0] == target) {
                res = platforms;
            }
            map.put(platforms, map.getOrDefault(platforms, 0) + 1);
            minHeap.add(time[1] + time[2]);
        }

        int busiest = 1;
        for (int key: map.keySet()) {
            if (map.get(key) > map.getOrDefault(busiest, 0)) {
                busiest = key;
            }
        }

        TreeSet<Integer> set = new TreeSet<>();
        set.add(busiest);
        for (int key: map.keySet()) {
            if (key != busiest && map.get(key) == map.get(busiest)) {
                set.add(key);
            }
        }
        System.out.println(res);
        for(int subRes: set) {
            System.out.println(subRes);
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[][] arr = new int[t][3];
        for (int i = 0; i < t; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
        }
        int target = sc.nextInt();
        minStations(arr, target);
        sc.close();
    }
}

