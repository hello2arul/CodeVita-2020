import java.util.*;

class Main {    
    public static long maxFunds(int[] amounts, int[][] pairs) {
        boolean[] grouped = new boolean[amounts.length];
        HashSet<Integer>[] sets = new HashSet[amounts.length];
        int idx = 0;
        for(int[] pair: pairs) {
            int a = pair[0];
            int b = pair[1];
            if (grouped[a] && grouped[b]) {
                continue;
            }
            if (!grouped[a] && !grouped[b]) {
                sets[idx] = new HashSet<Integer>();
                sets[idx].add(a);
                sets[idx].add(b);
                idx++;                                
            } else {
                if (grouped[a]) {
                    for (int i = 0; i < sets.length; i++) {
                        if (sets[i] != null && sets[i].contains(a)) {
                            sets[i].add(b);
                            break;
                        }
                    }
                } else {
                    for (int i = 0; i < sets.length; i++) {
                        if (sets[i] != null && sets[i].contains(b)) {
                            sets[i].add(a);
                            break;
                        }
                    }
                }
            }
            grouped[a] = grouped[b] = true;
        }
        long max = Long.MIN_VALUE;
        for (HashSet<Integer> set: sets) {
            if( set != null) {
                long val = 0;
                for(int i: set) {
                    val += amounts[i];
                }
                max = Math.max(max, val);
            }
        }
        for (int i = 1; i < grouped.length; i++) {
            if (!grouped[i]) {
                max = Math.max(max, amounts[i]);
            }
        }

        return max;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size + 1];
        for (int i = 1; i <= size; i++) {
            arr[i] = sc.nextInt();
        }
        int no = sc.nextInt();
        int[][] pairs = new int[no][2];
        for (int i = 0; i < no; i++) {
            pairs[i][0] = sc.nextInt();
            pairs[i][1] = sc.nextInt();
        } 
        System.out.println(maxFunds(arr, pairs));
        sc.close();
    }
}

/*
  int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] arr = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
*/