import java.util.*;

class Solution {
    int[] rowArr = {0, 1, 0, -1}, colArr = {1, 0, -1, 0};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        Arrays.fill(answer, 1);

        //강의실 차례대로 순회
        for(int i=0; i<5; i++) {
            isOkay(places[i], new boolean[5][5], answer, i);            
        }
        return answer;
    }
    
    void isOkay(String[] place, boolean[][] visited, int[] answer, int room) {
        for(int r=0; r<5; r++) {
            for(int c=0; c<5; c++) {
                if(place[r].charAt(c) == 'P' && !bfs(place, visited, r, c)) answer[room] = 0;
            }
        }
    }
    
    boolean bfs(String[] place, boolean[][] visited, int row, int col) {
        //start
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{row, col, 0}); visited[row][col] = true;
        
        //cur
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curCnt = cur[2];

            if(curCnt == 2) return true;
            
            //near
            for(int i=0; i<4; i++) {
                int nxtR = curR + rowArr[i];
                int nxtC = curC + colArr[i];
                
                if(isValid(nxtR, nxtC, visited)) {
                    //사람 있으면 즉시 X
                    if(place[nxtR].charAt(nxtC) == 'P') return false;
                    //파티션 제외 재탐색
                    else if(place[nxtR].charAt(nxtC) != 'X') {
                        q.offer(new int[]{nxtR, nxtC, curCnt+1});
                    }
                }
            }
        }
        return true;
    }
    
    boolean isValid(int r, int c, boolean[][] visited) {
        return r >= 0 && r < 5 && c >= 0 && c < 5 && !visited[r][c];
    }
}