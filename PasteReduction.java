import java.util.*;

//13. PasteReduction
class PasteReduction {
    public static int minPastes(String para, String broken) {
        boolean[] brokenKeys = new boolean[128];
        for(int i = 0; i < broken.length(); i++) {
            brokenKeys[broken.charAt(i)] = true;
        }
        int minPastes = 0;
        String leftOver = "";
        for(int i = 0; i < para.length(); i++) {
            char ch = para.charAt(i);
            if(brokenKeys[ch]) {
                int j = 0;
                boolean hadLeftOver = false;
                while(j < leftOver.length() && leftOver.charAt(j) == ch) {
                    j++;
                    i++;
                    ch = para.charAt(i);
                    hadLeftOver = true;
                }
                if(hadLeftOver) {
                    i--;
                    if(j >= leftOver.length()) {
                        leftOver = "";
                    } else {
                        leftOver = leftOver.substring(j);
                    }
                    continue;
                }                
                minPastes++;   
                j = 0;
                while(j < broken.length() && broken.charAt(j) != ch) {
                    j++;
                }       
                String toPaste = broken.substring(j);
                boolean decI = false;
                while(i < broken.length() && ch == broken.charAt(j)) {
                    i++;
                    ch = para.charAt(i);
                    j++;
                    decI = true;
                }
                if(decI)    i--;
                if(j < toPaste.length()) {
                    leftOver = toPaste.substring(j);
                }
            }
        }
        return minPastes;
    }
    public static void main(String[] args) {
        String paragraph = "supreme court is the highest judicial court";
        String broken = "su";
        System.out.println(minPastes(paragraph, broken));
    }
}