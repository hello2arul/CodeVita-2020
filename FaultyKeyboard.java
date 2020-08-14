import java.util.*;


// 3. Faulty Keyboard
class FaultyKeyboard{
    // paste backspace cursor
    public static int noOfOperations(String text, String broken) {
        String[] texts = text.split(" ");
        Set<Character> brokenChars = new HashSet<>();
        for(int i = 0; i < broken.length(); i++) {
            brokenChars.add(broken.charAt(i));
        }
        int operations = 0;
        for(String s : texts) {
            // paste
            if(s.length() == broken.length() && s.equals(broken)) {
                operations++;
            } else {
                for(int i = 0; i < s.length(); i++) {
                    char current = s.charAt(i);
                    if(brokenChars.contains(current)) {
                        operations++;
                        int j = broken.length() - 1;
                        if(i + j + 1 <= s.length() && broken.equals(s.substring(i, i + j + 1))) {
                            i += (broken.length() - 1);
                            continue;
                        }
                        
                        while(j >= 0 && current != broken.charAt(j)) {
                            j--;
                            operations++;
                        }
                        if(j != 0) {
                            // move left, delete then move back
                            operations += (j + 2);
                        } 
                    } 
                }
            }
            System.out.println(s + " -> " + operations);
        }
        return operations;        
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String text = "supreme court is the highest judicial cour";
        String broken = "su";
        System.out.println(noOfOperations(text, broken));
        long endTime = System.currentTimeMillis();
        System.out.println("runtime is " + (endTime - startTime));
    }
}
