import java.util.*;

/*
목표에 도착할 수 있는 '최소 거리'
- 상하좌우 1칸 이동 가능
- '0': 벽, '1': 이동 가능
- 시작점 (1,1), 도착점 (n, m)
*/
class Solution {
    int maxRow, maxCol;
    boolean[][] visited;
    
    int[] dRow = {0, 1, 0, -1};
    int[] dCol = {1, 0, -1, 0};
    
    int[] end; //도착점
    
    public int solution(int[][] maps) {
        maxRow = maps.length; maxCol = maps[0].length;
        visited = new boolean[maxRow][maxCol];
        end = new int[] {maxRow-1, maxCol-1};
        
        return bfs(maps);
    }
    
    int bfs(int[][] maps) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, 1}); //좌표, 횟수
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];
            int moves = cur[2];

            if(row == end[0] && col == end[1]) return moves;
            
            for(int i=0; i<4; i++) {
                int nxtR = row + dRow[i];    
                int nxtC = col + dCol[i];

                if(isValid(maps, nxtR, nxtC)) {
                    q.offer(new int[]{nxtR, nxtC, moves+1});
                    visited[nxtR][nxtC] = true;
                }
            }            
        }

        return -1;
    }
    
    boolean isValid(int[][] maps, int r, int c) {
        return r > -1 && r < maxRow && c > -1 && c < maxCol && !visited[r][c] && maps[r][c] != 0;
    }
}