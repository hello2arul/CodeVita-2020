import java.util.*;

// wrong
class DietPlan {
    public static String leftDiet(int maxP, int maxC, int maxF, List<int[]> pcfs) {

        boolean hasValidDiets = true;
        while(hasValidDiets) {
            hasValidDiets = false;
            for(int[] diet : pcfs) {
                if(isValidDiet(maxP, maxC, maxF, diet[0], diet[1], diet[2])) {
                    maxP -= diet[0];
                    maxC -= diet[1];
                    maxF -= diet[2];
                    hasValidDiets = true;
                }
            }
        }
        
        return maxP + " " + maxC + " " + maxF;
    }
    public static boolean isValidDiet(int remP, int remC, int remF, int P, int C,  int F) {
        return remp - P >= 0 && remC - C >= 0 && remF - F >= 0;
    }
    public static void main(String[] args) {
        
    }
}