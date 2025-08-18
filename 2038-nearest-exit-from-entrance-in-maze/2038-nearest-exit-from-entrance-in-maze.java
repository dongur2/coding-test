/*
    m * n (0으로 시작)
    '.': 통로, '+': 벽
    int[] entrance: 입구 좌표 (시작점)

    상하좌우로 한 칸 이동 가능
    출구: 맵의 가장자리
    입구 근처 가장 가까운 출구로의 거리 찾기 / 도달할 수 없으면 -1
    - 입구는 출구로 치지 않음
 */
import java.util.Deque; import java.util.ArrayDeque;
class Solution {
    //상하좌우
    int[] dRow = new int[] {0, 1, 0, -1}, dCol = new int[]{1, 0, -1, 0};
    int m, n;

    boolean[][] visited;

    int result = Integer.MAX_VALUE;

    public int nearestExit(char[][] maze, int[] entrance) {
        m = maze.length; n = maze[0].length;
        visited = new boolean[m][n];

        bfs(maze, entrance);    

        return result;
    }

    void bfs(char[][] maze, int[] entrance) {
        Deque<Entry> q = new ArrayDeque<>();
        q.offer(new Entry(entrance[0], entrance[1], 0));
        visited[entrance[0]][entrance[1]] = true;

        while(!q.isEmpty()) {
            Entry curr = q.poll();

            if(isExist(curr) && !(curr.r == entrance[0] && curr.c == entrance[1])) {
                result = Math.min(result, curr.dist); 
                continue;
            }

            for(int i=0; i<4; i++) {
                int nxtR = curr.r + dRow[i];
                int nxtC = curr.c + dCol[i];

                if(isValid(maze, nxtR, nxtC)) {
                    q.offer(new Entry(nxtR, nxtC, curr.dist+1));
                    visited[nxtR][nxtC] = true;
                }
            }
        }

        if(result == Integer.MAX_VALUE) result = -1;
    }

    boolean isExist(Entry curr) {
        return curr.r == m-1 || curr.r == 0 || curr.c == n-1 || curr.c == 0;
    }

    boolean isValid(char[][] maze, int r, int c) {
        return r >= 0 && r < m && c >= 0 && c < n && !visited[r][c] && maze[r][c] == '.';
    }

    class Entry {
        int r, c, dist;
        public Entry(int r, int c, int dist) {
            this.r=r; this.c=c; this.dist=dist;
        }
    }
}