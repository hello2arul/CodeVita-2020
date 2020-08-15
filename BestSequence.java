
import java.util.*;
class BestSequence {
    public static String bestSequence(String para, char[] broken) {
        List<String> permutations = new ArrayList<>();
        Set<Character> brokenChars = new HashSet<>();
        for(char ch : broken) {
            brokenChars.add(ch);
        }
        permute(broken, 0, permutations);
        Trie trie = new Trie();
        for(String s : permutations) {
            trie.insert(s);
        }
        Map<String, Integer> countOfPermutation = new HashMap<>();
        for(String s : para.split(" ")) {
            for(int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if(brokenChars.contains(ch) && i + broken.length <= s.length()) {
                    String current = s.substring(i, i + broken.length);
                    if(trie.contains(current)) {
                        countOfPermutation.put(current, countOfPermutation.getOrDefault(current, 0) + 1);
                    }
                }
            }
        }
        String max = "";
        for(String key : countOfPermutation.keySet()) {
            if(countOfPermutation.getOrDefault(max, 0) < countOfPermutation.get(key)) {
                max = key;
            }
            else if(countOfPermutation.getOrDefault(max, 0) == countOfPermutation.get(key)) {
                max = max.compareTo(key) < 0 ? max : key;
            }
        }
        return max;
    }    
    
    public static void permute(char[] arr, int start, List<String> permutations) {
        if(start >= arr.length) {
            permutations.add(new String(arr));
            return;
        }
        for(int i = start; i < arr.length; i++) {
            swap(arr, i, start);
            permute(arr, start + 1, permutations);
            swap(arr, i, start);
        }
    }
    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(bestSequence("supreme court is the highest judicial court", new char[] {'s','u'}));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}

class Trie {
    // can add a count variable to find the number of words starting with the prefix
    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }
    private final TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null) {
                node = new TrieNode();
                current.children.put(ch, node);    
            }
            current = node;
        }
        current.endOfWord = true; 
    }

    public boolean contains(String word) {
        TrieNode current = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if(node == null)    return false;
            current = node;
        }
        return current.endOfWord;
    }
}