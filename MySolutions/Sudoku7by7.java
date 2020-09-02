import java.util.*;


class Sudoku {
    // this is a special sudoku with only 1 - 7 letter, 
    // here instead of mini grids, we have 7 weird colors, see question for better understanding
    public static boolean solve(int[][] board, char[][] colors) {
        // find a row which is 0
        int[] find = findEmpty(board);
        int row = find[0];
        int col = find[1];
        // if we did not find any empty cell that means we solved it
        // hence return true
        if(row == -1)   return true;
        for(int num = 1; num <= 7; num++) {
            // loop from 1 to 7,if the placement is valid,
            // meaning the the num is not in the same row or same col or same color
            // we can place it and make a recursive call
            if(isValid(board, num, row, col, colors)) {
                // place the valid num
                board[row][col] = num;
                // call recursively
                if(solve(board, colors))
                    return true;
                // if the return true statement is not hit,
                // that means we the last placement didn't find a solution
                // so unplace it
                board[row][col] = 0;
            }
        
        }
        // if any of the above returns did not hit, that means we haven't solved it 
        return false;        
    }

    public static boolean isValid(int[][] board, int num, int row, int col, char[][] colors) {
        // check if the column has the number we are trying to place
        for(int i = 0; i < board[0].length; i++) {
            if(board[row][i] == num && col != i)
                return false;
        }
        // check if the row has the number we are trying to place
        for(int i = 0; i < board.length; i++) {
            if(board[i][col] == num && row != i)
                return false;
        }
        boolean[][] seen = new boolean[7][7];
        // check if the number we are trying to place is in the colored region
        return validColored(board, num, row, col,colors, seen, colors[row][col]); 
    }
    
    public static boolean validColored(int[][] board, int num, int row, int col, char[][] colors, boolean[][] seen, char color) {
        // if the current index is out of bounds just return true
        if(row < 0 || col < 0 || row >= board.length || col >= board[0].length) {
            return true;
        }
        // if we have reached another color or we have already visited the current cell return true
        if(colors[row][col] != color || seen[row][col]) {
            return true;
        }
        // if any of the cell with the same color already contains the num, we are trying to place 
        // in a invalid position so return false
        if(board[row][col] == num) {
            return false;
        }
        // mark the current grid as visited to prevent infinite recursive calls
        seen[row][col] = true;
        // recursivly call for all 8 neighbours,
        // top, down, left right, lef diag, right diag,
        // bottom left diag, bottom right diag
        return validColored(board, num, row + 1, col, colors, seen, color) &&
        validColored(board, num, row, col - 1, colors, seen, color) &&
        validColored(board, num, row - 1, col, colors, seen, color) &&
        validColored(board, num, row, col + 1, colors, seen, color) &&
        validColored(board, num, row + 1, col + 1, colors, seen, color) &&
        validColored(board, num, row - 1, col - 1, colors, seen, color) &&
        validColored(board, num, row - 1, col + 1, colors, seen, color) &&
        validColored(board, num, row + 1, col - 1, colors, seen, color);
    } 
    // returns the position of an empty cell
    public static int[] findEmpty(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 0)
                    return new int[] {i,j};
            }
        }
        return new int[] {-1,-1};
    }
    
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[7][7];
        char[][] colors = new char[7][7];
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        try{
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                colors[i][j] = sc.next().charAt(0);
            }
        }}
        catch(Exception e) {
            
        }
    
  
    solve(board, colors);
    for(int[] row : board) {
        for(int i : row) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    sc.close();
}
}

