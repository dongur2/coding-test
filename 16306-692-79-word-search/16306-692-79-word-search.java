import java.util.*;

/*
 Q. 주어진 보드판에서 글자를 이어서 주어진 단어를 만들 수 있는지 여부를 리턴
 - 상하좌우로 이어진 칸을 이을 수 있음(4방향)
 - 같은 칸 중복 방문 불가

 1. 위에서부터 차례로 순회
 2. 시작글자를 만날 경우 그 자리에서 dfs 시작
 */
class Solution {
    int[] rowArr = new int[]{0, 1, 0, -1};
    int[] colArr = new int[]{1, 0, -1, 0};

    int rowLength, colLength;

    public boolean exist(char[][] board, String word) {
        rowLength = board.length;
        colLength = board[0].length;
        
        //전체 순회
        for(int r=0; r<rowLength; r++) {
            for(int c=0; c<colLength; c++) {
                //시작글자를 만나면 방문처리 후 dfs 시작: 단어를 완성하면 즉시 true 리턴
                if(board[r][c] == word.charAt(0)) {
                    boolean[][] visited = new boolean[rowLength][colLength];
                    visited[r][c] = true;
                    if(canMakeWord(board, word, visited, r, c, 0, String.valueOf(board[r][c]))) return true; 
                }
            }
        }
        
        //글자를 완성한 적이 없으면 falsea 리턴
        return false;
    }

    boolean canMakeWord(char[][] board, String word, boolean[][] visited, int row, int col, int idx, String tempWord) {
        //만들고 있는 단어의 길이와 주어진 단어의 길이가 일치할 경우 확인
        if(tempWord.length() == word.length()) return tempWord.equals(word);

        //상하좌우 탐색
        for(int i=0; i<4; i++) {
            int nxtR = row + rowArr[i];
            int nxtC = col + colArr[i];

            //유효한 위치 + 다음 글자와 일치 + 방문한 적 없을 경우
            if(isValid(board, word.charAt(idx+1), nxtR, nxtC, visited)) {
                visited[nxtR][nxtC] = true;
                if(canMakeWord(board, word, visited, nxtR, nxtC, idx+1, tempWord + board[nxtR][nxtC] + "")) return true;
                visited[nxtR][nxtC] = false;
            }
        }

        return false;
    }

    boolean isValid(char[][] board, char nxtChar, int r, int c, boolean[][] visited) {
        return r >= 0 && r < rowLength && c >= 0 && c < colLength && board[r][c] == nxtChar && !visited[r][c];
    }
}