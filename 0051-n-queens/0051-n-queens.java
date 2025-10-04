import java.util.List; import java.util.ArrayList;

class Solution {
    List<List<String>> answer = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(char[] b:board) {
            Arrays.fill(b, '.');
        }

        dfs(board, n, 0);        

        return answer;    
    }

    void dfs(char[][] board, int n, int row) {
        if(row == n) {
            List<String> res = new ArrayList<>();
            for(char[] line : board) {
                res.add(String.valueOf(line));
            }
            answer.add(res);
            return;
        }

        for(int col=0; col<n; col++) {
            if(!isValid(board, n, row, col)) continue;
            board[row][col] = 'Q';
            dfs(board, n, row+1);
            board[row][col] = '.';
        }
    }

    boolean isValid(char[][] board, int n, int row, int col) {
        //위
        for(int r=row-1; r>=0; r--) {
            if(board[r][col] == 'Q') return false;
        }

        //대각선 왼쪽 위
        for(int r=row-1, c=col-1; r>=0 && c>=0; r--, c--) {
            if(board[r][c] == 'Q') return false;
        }

        //대각선 오른쪽 위
        for(int r=row-1, c=col+1; r>=0&& c<n; r--, c++) {
            if(board[r][c] == 'Q') return false;
        }

        return true;
    }
}