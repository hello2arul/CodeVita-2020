import java.util.*;

// 6. Constellation
class Constellation{
    // A E I O U
    public static String findStarPattern(char[][] grid, int N) {
        String A = " * \n***\n* *\n";
        String E = "***\n***\n***\n";
        String I = "***\n * \n***\n";
        String O = "***\n* *\n***\n";
        String U = "* *\n* *\n***\n";
        Map<String, Character> starMap = new HashMap<>();
        starMap.put(A, 'A');
        starMap.put(E, 'E');
        starMap.put(I, 'I');
        starMap.put(O, 'O');
        starMap.put(U, 'U');
        boolean[] unwantedCols = new boolean[grid[0].length];
        int hashes = 0;
        int words = 0;
        for(int j = 0; j < grid[0].length; j++) {
            if(grid[0][j] == '#') {
                hashes++;
                words++;
                unwantedCols[j] = true;
            } else if((grid[0][j] == '.' && grid[1][j] == '.' && grid[2][j] == '.')) {
                unwantedCols[j] = true;
                words++;
            }
        }
        words = (grid[0].length - words) / 3;
        StringBuilder[] stars = new StringBuilder[words];
        for(int i = 0; i < 3; i++) {
            int idx = 0;
            for(int j = 0; j < grid[0].length; j += 3) {
                while(j < grid[0].length && unwantedCols[j]) {
                    j++;
                }

                if(stars[idx] == null) {
                    stars[idx] = new StringBuilder();
                }
                for(int k = j; k < grid[0].length && k < j + 3; k++) {
                    if(grid[i][k] == '*') {
                        stars[idx].append('*');
                    } else if(grid[i][k] == '.') {
                        stars[idx].append(' ');
                    } 
                }
                stars[idx].append('\n');
                idx = (idx + 1) % words;
            }
        }
        // System.out.println(Arrays.toString(stars));
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < stars.length; i++) {
            String star = stars[i].toString();
            
            if(starMap.containsKey(star)) {
                result.append(starMap.get(star));
            }
            if(hashes-- > 0)    result.append('#');
        }


        return result.toString();
        
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        char[][] stars = {{'*','.', '*', '#','.', '*', '*', '*', '#', '.', '*', '.'},
                          {'*', '.' ,'*' ,'#','.','.', '*', '.', '#', '*', '*' ,'*'},
                          {'*', '*' ,'*' ,'#','.','*' ,'*' ,'*' ,'#' ,'*' ,'.' ,'*'}};
        System.out.println(findStarPattern(stars, 12));
        System.out.println("runtime is " + (endTime - startTime));
    }
}