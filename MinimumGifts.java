import java.util.Arrays;


class MinimumGifts {
    public static int minimumGifts(int[] ratings) {
        int[] gifts = new int[ratings.length];
        Arrays.fill(gifts, 1);
        for(int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i - 1]) {
                gifts[i] = gifts[i - 1] + 1;
            }
        }
        int sum = gifts[ratings.length - 1];
        for(int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) {
                gifts[i] = Math.max(gifts[i], gifts[i + 1] + 1);
            }
            sum += gifts[i];
        }
        return sum;
    }   
    public static void main(String[] args) {
        System.out.println(minimumGifts(new int[] {3,2}));
    } 
}