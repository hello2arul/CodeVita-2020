import java.util.*;


//TLE
class StringMerge {
    public static String mergeStrings(String target, String[] subStrings) {
        Set<String> dict = new HashSet<>();
        for(String s : subStrings) {
            for(String perm : permuteArray(s.toCharArray())) {
                dict.add(perm);
            }
        }
        return wordBreak(target, dict) ? "YES": "NO";
    }

    public static boolean wordBreak(String s, Set<String> dict) {
        
        boolean[] dp = new boolean[s.length() + 1];
        
        dp[0] = true;
        for(int i=1; i <= s.length(); i++){
            for(int j=0; j < i; j++){
                if(dp[j] && dict.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[s.length()];
    }
    

    public static List<String> permuteArray(char[] array) {
        List<String> result = new ArrayList<>();
        arrayPermutations(array, 0, result);
        return result;
    }
    public static void arrayPermutations(char[] array, int start, List<String> result) {
        if(start >= array.length) {
            result.add(new String(array));
        }else{
            for(int i = start; i < array.length; i++) {
                swap(array, start, i);
                arrayPermutations(array, start + 1, result);
                swap(array, start, i);
            }
        }
    }
    public static void swap(char[] array, int pos1, int pos2) {
        char temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String large = sc.next();
        int N = sc.nextInt();
        String[] small = new String[N];
        for(int i = 0; i < N; i++) {
            small[i] = sc.next();
        }
        System.out.println(mergeStrings(large, small));
    }
}