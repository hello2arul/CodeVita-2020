
import java.util.*;

class Main {

    static HashMap<String, Integer> map;
    public static int skipStation(int stops) {
        if (stops == 0) {
            return 1;
        }
        map = new HashMap<>();
        return combos(0, stops + 1);
    }

    private static int combos(int A, int B) {
      	String key = A + "," + B;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (A == B) return 1;
        if (A > B)  return -1;

        int train1 = combos(A + 1, B);
        int train2 = combos(A + 2, B);
        int train3 = combos(A + 3, B);
        int res = Math.max(train1, 0) + Math.max(train2, 0) + Math.max(train3, 0);
        map.put(key, res);
        return res;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            System.out.println(skipStation(sc.nextInt()));
        }
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