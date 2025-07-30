//단어가 그리드에 존재하는지 여부 리턴 (중복 사용 불가, 인접 위치-상하좌우-로만 이동)
class Solution {
    int[] dRow = new int[] {0, 1, 0, -1};
    int[] dCol = new int[] {1, 0, -1, 0};

    int r, c;
    boolean[][] visited; 

    public boolean exist(char[][] board, String word) {
        r = board.length; c = board[0].length;
        visited = new boolean[r][c];

        //첫글자 만나면 탐색 시작
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(board[i][j] == word.charAt(0)) {
                    visited[i][j] = true;
                    if(find(board, word, i, j, 1)) return true;
                    visited[i][j] = false;
                }
            }
        }

        return false;
    }

    private boolean find(char[][] board, String word, int row, int col, int idx) {
        //다 찾았으면 중지 
        if(idx == word.length()) return true;

        //상하좌우 확인
        for(int i=0; i<4; i++) {
            int newR = row + dRow[i];
            int newC = col + dCol[i];

            if(isValid(newR, newC) && word.charAt(idx) == board[newR][newC]) {
                visited[newR][newC] = true;
                if(find(board, word, newR, newC, idx+1)) return true;
                visited[newR][newC] = false;
            }
        }

        return false;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < r && col >= 0 && col < c && !visited[row][col];
    }
}