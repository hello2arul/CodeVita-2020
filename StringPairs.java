import java.util.*;
// RTE for no reason
class ConvertToWords {
    private final String[] LESS_THAN_20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen" };
    private final String[] TENS = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
            "Ninety" };
    private final String[] THOUSANDS = { "", "Thousand", "Million", "Billion" };

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";

        int i = 0;
        String words = "";

        while (num > 0) {
            if (num % 1000 != 0)
                words = helper(num % 1000) + THOUSANDS[i] + " " + words;
            num /= 1000;
            i++;
        }

        return words.trim();
    }

    private String helper(int num) {
        if (num == 0)
            return "";
        else if (num < 20)
            return LESS_THAN_20[num] + " ";
        else if (num < 100)
            return TENS[num / 10] + " " + helper(num % 10);
        else
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
    }
}

class StringPairs {
    
    ConvertToWords wordConverter = new ConvertToWords();

    public String stringPairs(int[] arr) {
        List<String> words = new ArrayList<>();
        for (int i : arr)
            words.add(wordConverter.numberToWords(i));
        int D = 0;
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                if (isVowel(ch)) {
                    D++;
                }
            }
        }
        Set<Set<Integer>> pairs = new HashSet<>();
        Set<Integer> nums = new HashSet<>();
        for (int i : arr) {
            int diff = D - i;
            if (nums.contains(diff)) {
                pairs.add(new HashSet<>(Arrays.asList(diff, i)));
            }
            nums.add(i);
        }
        return pairs.size() > 100 ? "greater 100"
                : (pairs.size() == 100 ? "hundred" : wordConverter.numberToWords(pairs.size()).toLowerCase());
    }

    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(new Main().stringPairs(arr));
    }
}