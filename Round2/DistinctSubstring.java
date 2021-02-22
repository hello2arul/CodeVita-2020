import java.util.*;

class Main {    
    public static String distinctSubstring(String s) {
        
        TreeMap<String, Integer> res = new TreeMap<>();

        for(int i = 0; i < s.length(); i++) {
            boolean[] seen = new boolean[128];
            StringBuilder substring = new StringBuilder();

            for(int j = i; j < s.length(); j++) {
                char ch = s.charAt(j);
                
                if(!seen[ch]) {
                    substring.append(ch);
                    seen[ch] = true;
                    if (j == s.length() - 1) {
                        if (ch == substring.charAt(0)) {
                            if (res.isEmpty()) {
                                res.put(substring.toString(), i);
                            }
                        } else {
                            if (!isProper(s, res, substring.toString(), i))
                                res.put(substring.toString(), i);
                        }
                    }
                } else {
                    if (ch == substring.charAt(0)) {
                        if (res.isEmpty()) {
                            res.put(substring.toString(), i);
                        }
                    } else {
                        if (!isProper(s, res, substring.toString(), i))
                            res.put(substring.toString(), i);
                    }
                    break;
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (String subRes: res.keySet()) {
            result.append(subRes + " ");
        }
        return result.toString().trim();
    }

    private static boolean isProper(String s, TreeMap<String, Integer> set, String cur, int i) {
        
        for (String key: set.keySet()) {
            int startIdx = set.get(key);
            if (i >= startIdx && i + cur.length() <= startIdx + key.length()) {
                if (isSubseq(key, cur)) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean isSubseq(String s, String t) {
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
        }
        return j == t.length();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        String word = sc.next();
        System.out.println(distinctSubstring(word));
        sc.close();
    }
}

