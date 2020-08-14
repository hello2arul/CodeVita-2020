// import java.util.*;


// // C -> correct i guess
// class Main {
//     public static int breakCadbury(int x, int y, Map<Set<Integer>, Integer> map) {
//         if(x < y ) {
//             int temp = x;
//             x = y; 
//             y = temp;
//         }
//         if(x == 0 || y == 0) {
//             return 0;
//         }
//         if(x == y) {
//             return 1;
//         }
//         else {
//             Set<Integer> set = new HashSet<>(Arrays.asList(x,y));
//             if(!map.containsKey(set)) {
//                 set.addAll(Arrays.asList(x, y));
//                 map.put(set, 1 + breakCadbury(x - y, y, map));
//             }
//             return map.get(set);
//         }
//     }
//     public static int generateCombos(int minLength, int maxLength, int minWidth, int maxWidth) {
//         int res = 0;
//         Map<Set<Integer>, Integer> map = new HashMap<>();
//         // Set<Integer> set = new HashSet<>();
//         // set.add(1);
//         // set.add(2);
//         // map.put(set, 1);
//         // System.out.println(map.containsKey(new HashSet<>(Arrays.asList(1,2))));
//         // System.out.println(map.containsKey(new HashSet<>(Arrays.asList(2, 1))));
//         for(int i = minLength; i <= maxLength; i++) {
//             for(int j = minWidth; j <= maxWidth; j++) {
//                 res += breakCadbury(i, j, map);
//             }
//         }
//         return res;
//     }
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int minLength = 5;//sc.nextInt();
//         int maxLength = 7;//sc.nextInt();
//         int minWidth = 3;//sc.nextInt();
//         int maxWidth = 4;//sc.nextInt();
//         sc.close();
//         System.out.println(generateCombos(minLength, maxLength, minWidth, maxWidth));
//     }
// }

//D, correct - i guess
// class Main {
//     int res = Integer.MAX_VALUE;
//     public void minTime(int totalSum, int sum, int i, List<Integer> petrols) {
//         int minTime = Math.max(sum, totalSum - sum);
//         if(minTime < res) {
//             res = minTime;
//         }        
//         if(i >= petrols.size())
//             return;
//         minTime(totalSum, sum + petrols.get(i), i + 1, petrols);
//         minTime(totalSum, sum, i + 1, petrols);
        
//     }
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         List<Integer> petrols = new ArrayList<>();
//         int sum = 0;
//         while(sc.hasNextInt()) {
//             int num = sc.nextInt();
//             petrols.add(num);
//             sum += num;       
//         }
//         sc.close();
//         Main soln = new Main();
//         soln.minTime(sum, 0, 0,petrols);
//         System.out.println(soln.res);
//     }
// }

//B
// class Main {
//     public static int findPairs(int[] arr) {
//         computeBitPairs(arr);  
//         int pairs = 0;
//         for(int i = 0; i < arr.length; i++) {
//             for(int j = i + 2; j < arr.length; j += 2) {
//                 if((arr[i] / 10) == (arr[j] / 10)) {
//                     pairs++;
//                     break;
//                 } 
//             }
//         }
//         return pairs;
//     }
//     public static void computeBitPairs(int[] arr) {
//         for(int i = 0; i < arr.length; i++) {
//             int num = arr[i];
//             int max = Integer.MIN_VALUE;
//             int min = Integer.MAX_VALUE;
//             while(num > 0) {
//                 max = Math.max(max, num % 10);
//                 min = Math.min(min, num % 10);
//                 num /= 10;
//             }
//             arr[i] = ((max * 11) + (min * 7)) % 100;
//         }
//     }
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int size = sc.nextInt();
//         int arr[] = new int[size];
//         for(int i = 0; i < size; i++)
//             arr[i] = sc.nextInt();
//         sc.close();
//         System.out.println(findPairs(arr));
//     }
// }

// A
// class Main {
//     public static int performSwayam(String s1, String s2) {
//         List<Character> grooms = new LinkedList<>();
        
//         for(char c: s2.toCharArray())
//             grooms.add(c);
//         for(int i = 0; i < s1.length(); i++) {
//             if(s1.charAt(i) == grooms.get(0)) {
//                 grooms.remove(0);
//             } 
//             else {
//                 List<Character> nonMatches = new ArrayList<>();
//                 while(s1.charAt(i) != grooms.get(0)) {
//                     if(nonMatches.size() == s1.length())
//                         return i;
//                     char groom = grooms.remove(0);
//                     nonMatches.add(groom);
//                     grooms.add(groom);
//                 }
//                 grooms.remove(0);
//             }
//         }
//         return 0;
//     }
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int len = sc.nextInt();
//         String s1 = sc.next();
//         String s2 = sc.next();
//         System.out.println(performSwayam(s1, s2));
//     }
// }

//E correct - TLE
// class Main {
//     public static int findSwaps(int[] arr) {
//         int swaps = 0;   
//         int[] initial = new int[arr.length];
//         for(int i = 0; i < initial.length; i++) {
//             initial[i] = i;
//         }
//         String originalPos = Arrays.toString(initial);
//         String newPos = new String();
//         while(!newPos.equals(originalPos)) {
//             int[] copy = new int[arr.length];
//             for(int i = 0; i < arr.length; i++) {
//                 copy[i] = initial[i];
//             }
//             for(int i = 0; i < arr.length; i++) {
//                 initial[arr[i] - 1] = copy[i]; 
//             }
//             newPos = Arrays.toString(initial);
//             System.out.println(newPos);
//             swaps++;
//         }
//         //System.out.println(originalPos);
//         return swaps;
//     }
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int t = sc.nextInt();
//         while(t-- > 0) {
//             int size = sc.nextInt();
//             int[] arr = new int[size];
//             for(int i = 0; i < size; i++)
//                 arr[i] = sc.nextInt();
//             System.out.println(findSwaps(arr));
//         }
//         sc.close();
//     }
// }

//F 
// A H L1  L2  M  C
// class Main {
//     public static int NCR(int n, int r) {
//         if (n == r || r == 0)
//             return 1;
//         return NCR(n - 1, r - 1) + NCR(n - 1, r);
//     }
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int t = sc.nextInt();
//         while(t-- > 0) {
//             int A = sc.nextInt();
//             int H = sc.nextInt();
//             int L1 = sc.nextInt();
//             int L2 = sc.nextInt();
//             int M = sc.nextInt();
//             int C = sc.nextInt();
//             int L = (L1 / L2);
//         }
//         sc.close();
//     }
// }




// class MinCoins {

//     public static String minCoins(int num) {
//         int fives = (num - 4) / 5;
//         int ones = (num - (5 * fives)) % 2 == 0 ? 2 : 1;
//         int twos = (num - (5 * fives) - ones) / 2;
//         return "fives " + fives + " twos " + twos + " ones " + ones;
//      }
    
//     public static void main(String[] args) {
//         System.out.println(minCoins(13));
//     }
// }


