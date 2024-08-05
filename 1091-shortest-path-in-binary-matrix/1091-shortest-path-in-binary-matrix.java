import java.util.*;

class Solution {
    /*
    ** 왼쪽 맨 위 노드에서 오른쪽 맨 아래 노드까지 갈 수 있는 '최단 거리'를 반환
    - 갈 수 없으면 -1을 반환
    - 인접한 8방향에 있는 '0'으로만 움직일 수 있음: 상하좌우/대각선
    
    최단 거리니까, BFS
    */
    
    int rowLen, colLen;
    boolean[][] visited;
    
    int[] rowArr = {0, 1, 1, 1, 0, -1, -1, -1};
    int[] colArr = {1, 1, 0, -1, -1, -1, 0, 1};
    
    public int shortestPathBinaryMatrix(int[][] grid) {
        rowLen = grid.length; colLen = grid[0].length; 
        visited = new boolean[rowLen][colLen];
        
        return findWay(grid);        
    }
    
    int findWay(int[][] grid) {
        if(grid[0][0] == 1 || grid[rowLen-1][colLen-1] == 1) return -1;

        //start
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 1}); visited[0][0] = true;
        
        while(!q.isEmpty()) {
            //current node
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curCnt = cur[2];
            
            if(grid[curR][curC] == 0 && curR == rowLen-1 && curC == colLen-1) return curCnt;
            
            //find ways (0)
            for(int i=0; i<8; i++) {
                int nextR = curR + rowArr[i];
                int nextC = curC + colArr[i];
                
                if(nextR == rowLen-1 && nextC == colLen-1) return curCnt + 1;
                if(isValid(grid, nextR, nextC)) {
                    q.offer(new int[]{nextR, nextC, curCnt+1}); visited[nextR][nextC] = true;
                }
            }
        }
        return -1;
    }
    
    boolean isValid(int[][] grid, int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen && !visited[row][col] && grid[row][col] == 0;
    }
}