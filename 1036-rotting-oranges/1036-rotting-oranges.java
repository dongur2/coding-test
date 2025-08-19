/*
    m * n 맵
    0: 빈 칸, 1: 신선함, 2:썩음
    매 분마다 근처에 썩은게 있는 신선한 오렌지는 썩음
    > 모든 신선한 오렌지가 썩는 데 걸리는 최소 시간 리턴 (불가능 -1)
 */
import java.util.Deque; import java.util.ArrayDeque;
class Solution {
    int[] dRow = new int[]{0, 1, 0, -1};
    int[] dCol = new int[]{1, 0, -1, 0};
    
    int r, c;
    int[][] map;

    int result = 0;
    
    public int orangesRotting(int[][] grid) {
        r = grid.length; c = grid[0].length;
        
        copyArray(grid);
        bfs();

        //썩지 않은 토마토가 존재하는지 확인 후 리턴 
        return existFresh() ? -1 : result;
    }

    //O(n^2)
    void copyArray(int[][] grid) {
        map = new int[r][c];
        for(int i=0; i<r; i++) {
            map[i] = grid[i];
        }
    }

    void bfs() {
        Deque<Entry> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[r][c];
        
        //처음 상태의 썩은 토마토를 모두 큐에 추가 (썩는 건 썩은 토마토의 상하좌우로 시작) 
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(map[i][j] == 2) {
                    q.offer(new Entry(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        //시간 카운트 시작 
        while(!q.isEmpty()) {
            Entry curr = q.poll(); //현재 썩은 토마토 

            //계속해서 소요된 시간 업데이트 
            result = Math.max(result, curr.t);

            //상하좌우로 토마토가 있는지 확인
            for(int i=0; i<4; i++) {
                int nxtR = curr.r + dRow[i];
                int nxtC = curr.c + dCol[i];

                //유효한 범위 내 신선한 토마토가 있으면 썩게 하고 큐에 추가
                if(isValid(nxtR, nxtC, visited)) {
                    map[nxtR][nxtC] = 2;
                    q.offer(new Entry(nxtR, nxtC, curr.t+1));
                    visited[nxtR][nxtC] = true;
                }
            }
        }
    }

    boolean isValid(int row, int col, boolean[][] visited) {
        return row >= 0 && row < r && col >= 0 && col < c && !visited[row][col] && map[row][col] == 1;
    }

    boolean existFresh() {
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(map[i][j] == 1) return true;
            }
        }
        return false;
    }

    class Entry {
        int r, c, t;
        public Entry(int r, int c, int t) {
            this.r=r; this.c=c; this.t=t;
        }
    }
}