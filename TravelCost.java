import java.util.*;

// 17. Travel Cost
class TravelCost {
    public static int minTravelCost(int[] cities, int jump) {
        return minTravelCost(cities, jump, 0);
    }
    public static int minTravelCost(int[] cities, int jump, int idx) {
        if(idx >= cities.length) {
            return cities[cities.length - 1];
        }
        if(cities[idx] == -1) {
            return -1;
        }
        int minJump = Integer.MAX_VALUE;
        for(int i = 1;  i <= jump + 1; i++) {
            int subRes = minTravelCost(cities, jump, idx + i);
            if(subRes != -1) {
                minJump = Math.min(minJump, subRes + cities[idx]);
            }
        }
        return minJump != Integer.MAX_VALUE ? minJump : -1;
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(minTravelCost(new int[] {2,-1,1,0}, 1));
        long endTime = System.currentTimeMillis();
        System.out.println("Runtime is " + (endTime - startTime));
    }
}