
import java.util.*;

class PathThroughGraph {
    public static int edgesThroughGraph(int a, int b) {
        if(a == b)  return 0;
        int tempa = a;
        int tempb = b;
        // set a as max and b as min
        a = Math.max(tempa, tempb);
        b = Math.min(tempa, tempb);
        // a treeset by default, sorts them is ascending order,
        // Collections.reverseOrder() sorts them is descending order
        // Also set doesn't allow duplicates
        Set<Integer> aFactors = new TreeSet<>(Collections.reverseOrder());
        Set<Integer> bFactors = new TreeSet<>(Collections.reverseOrder());

        // the logic is, find a's factors which are  less than a and b's factors which are less than a
        // you will need to take a  look at the sample inputs to better understand it
        // see below
        

        // finding factors of a
        int num = a;
        for(int i = a - 1; i >= 1; i--) {
            if(num % i == 0) {
                aFactors.add(i);
                num = i;
                i = num - 1;
            }
        }
        // finding factors of b
        num = b;
        for(int i = b - 1; i >= 1; i--) {
            if(num % i == 0) {
                bFactors.add(i);
                num = i;
                i = num - 1;
            }
        }

        // if b is a factor of a, return the number of elements in b till we find a
        // eg) a = 4 , b = 2,
        //  a's factors -> 4 -> 2 - 1
        // b's factors -> 2 -> 1
        // output 4 <-> 2 <-> 1, op -> 2 edges(the arrows)
        // since a contains 2-> 1, start looping from 4 and break at 2 and return the count
        if(aFactors.contains(b)) {
            int count = 1;
            for(int i : aFactors) {
                if(i == b) {
                    break;
                }
                count++;
            }
            return count;
        } 
        // if b is not a factor of a, add everything from b to a,
        // since set doesnt allow duplicates, you will  be left with only unique factors,
        // return a's size + 1, since we wont be including a itself in the set add 1 to the result
        aFactors.addAll(bFactors);
        return aFactors.size() + 1;
    }    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        System.out.println(edgesThroughGraph(M, N));
        sc.close();
    }
}