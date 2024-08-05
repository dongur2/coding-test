import java.util.*;

class Solution {
    /*
     ** 주어진 맵(grid)에 있는 섬의 개수를 반환
     1. 전체 노드를 차례로 순회
     2. 땅(1)을 만나면 그 노드에서 상하좌우로 연결된 땅을 탐색: 연결된 땅이 없을 때까지
        - 그 땅이 방문한 적 없는 새로운 땅일 경우에만 탐색을 진행
     */

    int rowLen, colLen;
    boolean[][] visited;

    int[] rowArr = {0, 1, 0, -1};
    int[] colArr = {1, 0, -1, 0};

    public int numIslands(char[][] grid) {
        rowLen = grid.length; colLen= grid[0].length;
        visited = new boolean[rowLen][colLen];

        int answer = 0;
        for(int r=0; r<rowLen; r++) {
            for(int c=0; c<colLen; c++) {
                if(grid[r][c] == '1' && !visited[r][c]) {
                    bfs(grid, r, c);
                    answer++;
                }
            }
        }
        return answer;
    }

    void bfs(char[][] grid, int r, int c) {
        //start
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c}); visited[r][c] = true;

        while(!q.isEmpty()) {
            //current node
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            //next node
            for(int i=0; i<4; i++) {
                int nextR = curR + rowArr[i];
                int nextC = curC + colArr[i];

                if(isValid(grid, nextR, nextC)) {
                    q.offer(new int[]{nextR, nextC});
                    visited[nextR][nextC] = true;
                }
            }
        }
    }

    boolean isValid(char[][] grid, int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen && !visited[row][col] && grid[row][col] == '1';
    }
}