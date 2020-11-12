
import java.util.*;

class LargestGoldIngot {
    // volume = length * breadth * height
    public static int largestGoldIngot(int[] golds, int breadth, int height) {
        if(golds == null || golds.length == 0)  
            return 0;
        if(golds.length == 1)     
            return golds[0] *breadth * height;

        int sum = 0;
        for(int gold: golds) {
            sum += gold;
        }
        // this will be the total volume
        sum = (sum * height * breadth);
        // finding the largest cuboid volume
        // see below for the simpler solution
        // we will be storing the indices in the stack
        // previous smaller
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        int max = 0;
        // we are finding the area, considering it as a 2d plane
        for(int i = 1; i < golds.length; i++) {
            int current = golds[i];
            // if the current height is taller or equal to whatever is top  of the stack ,
            // just  push the current element  to the stack
            if(current >= golds[stack.peek()]) {
                stack.add(i);
            }else{
                // current will be the right smaller and stack will hold the left smaller
                while(!stack.isEmpty() && current < golds[stack.peek()]) {
                    int temp = golds[stack.pop()];
                    if(stack.isEmpty()) {
                        // if the stack is empty use the current index as width
                        max = Math.max(max,temp*i);
                    }else{
                        // the x axis difference will give the breadth,
                        // the height is arr[i]
                        max =  Math.max(max,temp*(i-stack.peek()-1)); 
                    }
                }
                stack.add(i);
            }
        }
        if(!stack.isEmpty()) {
            int i = golds.length;
            while(!stack.isEmpty()) {
                    int temp = golds[stack.pop()];
                    if(stack.isEmpty()) {
                        max = Math.max(max,temp*i);
                    }else{
                        max =  Math.max(max,temp*(i-stack.peek()-1));
                    }
                }
            }
        // almost submitted it 10 times because i didn't use (int)(Math.pow(10, 9) + 7)
        // total volume - max volume
        return (sum - (max * breadth * height)) % (int)(Math.pow(10, 9) + 7);
    }
    // simpler version to find max area
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        for(int i = 0; i < heights.length; i++) {
            int curr = heights[i];
            for(int left = i - 1; left >= 0 && heights[left] >= heights[i]; left--) {
                curr += heights[i];
            }
            for(int right = i + 1; right < heights.length && 
                                   heights[right] >= heights[i]; right++) {
                curr += heights[i];
            }
            maxArea = Math.max(curr, maxArea);
        }
        return maxArea;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int breadth = sc.nextInt();
        int height = sc.nextInt();
        int[] golds = new int[len];
        for(int i = 0; i < len; i++) {
            golds[i] = sc.nextInt();
        }
        System.out.println(largestGoldIngot(golds, breadth, height));
        sc.close();
    }
}