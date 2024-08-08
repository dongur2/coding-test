import java.util.*;

/*
(0,0)에서 시작 - 상대팀 진영(오른쪽 끝)에 도착할 수 있는 최단 거리 반환 (불가능하면 -1 반환)
0인 칸은 불가능, 1인 칸은 갈 수 있음
*/
class Solution {
    int[] rowArr = {0, 1, 0, -1}, colArr = {1, 0, -1, 0};
    int rowLen, colLen;
    boolean[][] visited;
    
    public int solution(int[][] maps) {
        rowLen = maps.length; colLen = maps[0].length;
        visited = new boolean[rowLen][colLen];
        
        return bfs(maps);
    }
    
    int bfs(int[][] maps) {
        //start
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 1}); visited[0][0] = true;
        
        //cur
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0], curC = cur[1], curCnt = cur[2];
            
            //near
            for(int i=0; i<4; i++) {
                int nxtR = curR + rowArr[i];
                int nxtC = curC + colArr[i];
                
                if(nxtR == rowLen - 1 && nxtC == colLen - 1) return curCnt + 1; //다음 노드가 도착이면 리턴
                if(isValid(maps, nxtR, nxtC)) {
                    q.offer(new int[]{nxtR, nxtC, curCnt+1});
                    visited[nxtR][nxtC] = true;
                }
            }
        }
        return -1;
    }
    
    boolean isValid(int[][] maps, int r, int c) {
        return r >= 0 && r < rowLen && c >= 0 && c < colLen && !visited[r][c] && maps[r][c] == 1;
    }
}