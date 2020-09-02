import java.util.*;
// Time Limit Exceedeed
class SecretWord {

    public static String secretWord(String s, String[][] pairs) {
        Map<Character, Character> map = new HashMap<>();
        StringBuilder res = new StringBuilder();
        Set<String[]> unmapped = new HashSet<>();
        boolean[] mappedChars = new boolean[26];
        for(String[] pair : pairs) {
            int[] table1 = new int[26];
            int[] table2 = new int[26];
            char[] s1 = pair[0].toCharArray();
            char[] s2 = pair[1].toCharArray();
            boolean found = false;
            for(int i = 0; i < s1.length; i++) {
                table1[s1[i]- 'a']++;
                table2[s2[i] - 'a']++;
                if(table1[pair[0].charAt(i) - 'a'] > 1 || table2[pair[1].charAt(i) - 'a'] > 1) {
                    found = true;
                }
            }
            if(found) {
                for(char ch : s1) {
                    int curr = table1[ch - 'a'];
                    for(char c: s2) {
                        if(table2[c - 'a'] == curr && !map.containsKey(ch)) {
                            map.put(ch, c);
                            mappedChars[c - 'a'] = true;
                        }
                    }
                }                
            } else {
                unmapped.add(new String[] {pair[0], pair[1]});
            }         
        }
        for(String[] pair : unmapped) {
            int[] table1 = new int[26];
            int[] table2 = new int[26];
            for(int i = 0; i < pair[0].length(); i++) {
                table1[pair[0].charAt(i) - 'a']++;
                table2[pair[1].charAt(i) - 'a']++;
            }
            char[] s1 = pair[0].toCharArray();
            char[] s2 = pair[1].toCharArray();
            for(char ch : s1) {
                int curr = table1[ch - 'a'];
                if(!map.containsKey(ch)) {
                    for(char c: s2) {
                        if(table2[c - 'a'] == curr && !mappedChars[c - 'a']) {
                            map.put(ch, c);
                        }
                    }
                }
            }   
        }


        for(int i = 0; i < s.length(); i++) {
            res.append(map.get(s.charAt(i)));
        }
        return res.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int N = sc.nextInt();
        String[][] pairs = new String[N][2];
        for(int i = 0; i < N; i++) {
            pairs[i][0] = sc.next();
            pairs[i][1] = sc.next();
        }
        System.out.println(secretWord(s, pairs));
        sc.close();
    }
}


