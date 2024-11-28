import java.util.*;
 
class Solution {
    /*
    Q. [0][0]에서 [n-1][m-1]에 도착할 수 있는 최단 거리를 반환 (도착할 수 없으면 -1)
    - 0 = 벽, 1 = 통로
    - 상하좌우로 1칸씩 이동 가능 
    - 맵을 벗어난 이동 불가능
    */
    
    int[] rowArr = {0, 1, 0, -1}, colArr = {1, 0, -1, 0};
    
    int maxRow, maxCol;
    boolean[][] visited;
    
    public int solution(int[][] maps) {
        maxRow = maps.length;
        maxCol = maps[0].length;
        visited = new boolean[maxRow][maxCol];
        
        return bfs(maps, 0, 0);
    }
    
    int bfs(int[][] maps, int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        
        //start
        q.offer(new int[]{r, c, 1});
        visited[r][c] = true;
        
        //queue
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curCnt = cur[2];
            
            if(curR == maxRow-1 && curC == maxCol-1) return curCnt;
            
            for(int i=0; i<4; i++) {
                int nxtR = curR + rowArr[i];
                int nxtC = curC + colArr[i];
                            
                if(isValid(maps, nxtR, nxtC)) {
                    q.offer(new int[]{nxtR, nxtC, curCnt+1});
                    visited[nxtR][nxtC] = true;
                }
            }
        }
        return -1;
    }
    
    boolean isValid(int[][] maps, int row, int col) {
        return row >= 0 && row < maxRow && col >= 0 && col < maxCol && maps[row][col] == 1 && !visited[row][col]; 
    }
}