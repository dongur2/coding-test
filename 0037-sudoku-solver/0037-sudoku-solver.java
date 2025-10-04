class Solution {
    public void solveSudoku(char[][] board) {
        dfs(board);
    }

    boolean dfs(char[][] board) {
        for(int r=0; r<9; r++) {
            for(int c=0; c<9; c++) {
                if(board[r][c] != '.') continue;

                //1-9 넣어보기
                for(char num='1'; num<='9'; num++) {
                    if(isValid(board, r, c, num)) {
                        board[r][c] = num;
                        if(dfs(board)) return true;
                        board[r][c] = '.'; 
                    }
                }
                //1-9 중 유효값 없으면 리턴 
                return false;
            }
        }
        return true;
    }

    boolean isValid(char[][] board, int row, int col, char num) {
        for(int i=0; i<9; i++) {
            //가로세로
            if(board[row][i] == num && i != col) return false;
            if(board[i][col] == num && i != row) return false;

            //3*3 그리드
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == num) return false;
        }
        return true;
    }
}