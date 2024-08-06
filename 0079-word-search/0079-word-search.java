import java.util.*;

class Solution {
    /* 주어진 단어(word)가 주어진 그리드(board)에 존재하는지 여부를 리턴 - 상하좌우로 이동 가능 & 중복 방문 불가능 
    1. 전체 노드를 차례로 순회
    2. 단어 첫 글자를 만나면 탐색 시작
    3. 상하좌우를 탐색 - 다음 글자일 경우에 큐에 추가하고 그 노드로 이동하여 재탐색
    4. 다음 노드가 끝 글자일 경우 즉시 true 리턴 
    5. 모든 반복이 끝났음에도 단어를 찾지 못했다면 false 리턴
    */
    
    int rowLen, colLen;
    char[][] copiedBoard; String copiedWord;
    boolean isContained = false; //단어 존재 여부
    int[] rowArr = {0, 1, 0, -1}, colArr = {1, 0, -1, 0};
    
    public boolean exist(char[][] board, String word) {
        rowLen = board.length; colLen = board[0].length;
        copiedBoard = board; copiedWord = word;
        
        //전체 노드를 차례로 순회하며 첫글자를 만나면 dfs 순회 시작
        for(int r=0; r<rowLen; r++) {
            for(int c=0; c<colLen; c++) {
                if(board[r][c] == word.charAt(0)) dfs(new boolean[rowLen][colLen], r, c, 0); //visited, row, col, charAt(idx)
            }
            if(isContained) return true;
        }
        return false;
    }
    
    void dfs(boolean[][] visited, int row, int col, int idx) {
        visited[row][col] = true; // 0, 0, 0 (A)
        
        if(idx == copiedWord.length() - 1) {
            isContained = true; 
            return;
        }
        
        //상하좌우 탐색
        for(int i=0; i<4; i++) {
            int nxtR = row + rowArr[i];
            int nxtC = col + colArr[i];
            
            //B
            if(isValid(nxtR, nxtC, visited) && copiedWord.charAt(idx+1) == copiedBoard[nxtR][nxtC]) {
                dfs(visited, nxtR, nxtC, idx+1);
            }
        }
        
        visited[row][col] = false;
    }
    
    boolean isValid(int r, int c, boolean[][] visited) {
        return r >= 0 && r < rowLen && c >= 0 && c < colLen && !visited[r][c];
    }
}