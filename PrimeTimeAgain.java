
import java.util.*;

//15 prime time again
class PrimeTimeAgain {
    public static int primePairs(int times, int part) {
        boolean[] primes = new boolean[times + 1];
        for(int i = 2; i <= times; i++) {
            if(!primes[i]) {
                for(int j = i + i; j <= times; j += i) {
                    primes[j] = true;
                }
            }
        }
        int pairs = 0;
        int time = times / part;
        for(int i = 2; i < time; i++) {
            if(!primes[i] && isValidPair(i, time, part, primes)) {
                pairs++;
            }
        }
        return pairs;
    }
    public static boolean isValidPair(int time, int maxTime, int parts, boolean[] primes) {
        for(int i = 1; i < parts; i++) {
            time += maxTime;
            if(primes[time]) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(primePairs(49, 7));
    }    
}