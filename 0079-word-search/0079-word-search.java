//보드에 단어가 있는지 여부 리턴 
class Solution {
    //상하좌우 인접
    static int[] dRow = new int[] {0, 1, 0, -1};
    static int[] dCol = new int[] {1, 0, -1, 0};

    static int m = 0, n = 0;
    static boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        m = board.length; n = board[0].length;
        visited = new boolean[m][n];

        //모든 칸을 차례로 탐색하면서 첫 글자를 발견하면 그 자리에서 dfs로 다음 글자 탐색
        for(int r=0; r<m; r++) {
            for(int c=0; c<n; c++) {
                if(board[r][c] == word.charAt(0)) {
                    visited[r][c] = true;
                    if(dfs(board, word, r, c, 1)) return true;
                    visited[r][c] = false;
                }
            }
        }
        //탐색이 끝날 때까지 단어를 찾지 못했으면 실패
        return false;
    }

    public static boolean dfs(char[][] board, String word, int row, int col, int idx) {
        //모든 문자를 다 찾았으면 종료
        if(idx == word.length()) return true;

        for(int i=0; i<4; i++) {
            int nr = row + dRow[i];
            int nc = col + dCol[i];

            if(isValid(nr, nc) && board[nr][nc] == word.charAt(idx)) {
                visited[nr][nc] = true;
                if(dfs(board, word, nr, nc, idx+1)) return true;
                visited[nr][nc] = false;
            }
        }

        return false;
    }

    public static boolean isValid(int r, int c) {
        return r>=0 && r<m && c>=0 && c<n && !visited[r][c];
    }
}