//https://school.programmers.co.kr/learn/courses/15009/lessons/121690

import java.util.Queue;
import java.util.ArrayDeque;

//출발지에서 보물이 있는 곳으로 이동하는데 필요한 최소 시간
//(1,1) -> (n,m) - 함정 존재
//상하좌우 1칸씩 이동 (1초)
//신발 한번 사용 가능 - 2칸 이동
class Solution {
    static int[] dRow = new int[] {0, 1, 0, -1};
    static int[] dCol = new int[] {1, 0, -1, 0};
    
    static int[][] map;
    
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int m, int[][] hole) {
        //맵 상하반전
        makeMap(n, m, hole);
        
        //탐색
        answer = Math.min(bfs(n, m, 1, 1), answer);
        
        return answer;
    }
    
    public static void makeMap(int n, int m, int[][] hole) {
        map = new int[m+1][n+1];
        
        //맵 회전
        for(int[] h:hole) {
            map[h[1]][h[0]] = 1;
        }
    }
    
    public static int bfs(int n, int m, int sx, int sy) {
        boolean[][][] visited = new boolean[m+1][n+1][2];
        Queue<Entry> q = new ArrayDeque<>();
        q.offer(new Entry(sx, sy, 1, 0)); //신발 사용 여부
        visited[sx][sy][0] = true;
        
        while(!q.isEmpty()) {
            Entry curr = q.poll();
            int cx = curr.x;
            int cy = curr.y;
            int cs = curr.shoe;
            int steps = curr.steps;
            
            //보물 지점에 도착했으면 리턴
            if(cx == m && cy == n) return steps;
            
            for(int i=0; i<4; i++) {
                int nx = cx + dRow[i];
                int ny = cy + dCol[i];
                
                if(isValid(n, m, nx, ny, cs, visited)) {
                    q.offer(new Entry(nx, ny, cs, steps+1));
                    visited[nx][ny][cs] = true;
                }
            }
            
            //신발 아직 안썼을 경우
            if(cs == 1) {
              for(int i=0; i<4; i++) {
                int nx = cx + dRow[i]*2;
                int ny = cy + dCol[i]*2;
                
                    if(isValid(n, m, nx, ny, cs, visited)) {
                        q.offer(new Entry(nx, ny, cs-1, steps+1));
                        visited[nx][ny][cs-1] = true;
                    }
                }
            }
        }
        
        return -1;
    }
    
    public static boolean isValid(int n, int m, int x, int y, int shoe, boolean[][][] visited) {
        return x>0 && x<=m && y>0 && y<=n && !visited[x][y][shoe] && map[x][y] != 1;
    }
    
    private static class Entry {
        int x, y, shoe, steps;
        
        public Entry(int x, int y, int shoe, int steps) {
            this.x=x; this.y=y; this.shoe=shoe; this.steps=steps;
        }
    }
}
