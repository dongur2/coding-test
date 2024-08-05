import java.util.*;

class Solution {
    /*
    ** 상대 팀 진영에 도착하기 위해 지나야 하는 '최소' 칸의 개수 반환
    - 상대 팀 진영[n][m]에 도착할 수 없으면 -1 반환
    - 캐릭터는 [0][0]에서 시작
    - 상하좌우로 값이 1인 칸으로 이동 가능; 주어진 맵 바깥으로 나갈 수 없음
    
    최소 칸의 개수니까 BFS
    */
    
    int rowLen, colLen;
    boolean[][] visited;
    
    int[] rowArr = {0, 1, 0, -1};
    int[] colArr = {1, 0, -1, 0};
    
    public int solution(int[][] maps) {
        rowLen = maps.length; colLen = maps[0].length;
        visited = new boolean[rowLen][colLen];
        
        return findWays(maps);
    }
    
    int findWays(int[][] maps) {
        //start
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 1}); visited[0][0] = true;
        
        //find way
        while(!q.isEmpty()) {
            //current node
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curCnt = cur[2];
            
            //next node
            for(int i=0; i<4; i++) {
                int nextR = curR + rowArr[i];
                int nextC = curC + colArr[i];
                
                if(isValid(maps, nextR, nextC)) {
                    if(nextR == rowLen - 1 && nextC == colLen - 1) return curCnt + 1;
                    
                    q.offer(new int[]{nextR, nextC, curCnt + 1});
                    visited[nextR][nextC] = true;
                }
            }
        }
        return -1;
    }
    
    boolean isValid(int[][] maps, int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen && !visited[row][col] && maps[row][col] == 1;
    }
}