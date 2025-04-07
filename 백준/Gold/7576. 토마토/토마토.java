import java.util.*;
import java.io.*;

public class Main {
    //상하좌우 인접 토마토가 익었을 경우 하루지나고 익음 
    static int[] dRow = {0, 1, 0, -1};
    static int[] dCol = {1, 0, -1, 0};
    
    static int[][] map;
    static boolean[][] visited;
    
    static int days = Integer.MIN_VALUE;
    
    //토마토가 모두 익는데 필요한 최소 일수
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      
      map = new int[n][m];
      visited = new boolean[n][m];
      
      //토마토 지도 -1:없음 0:안익은 토마토 1:익은 토마토
      for(int r=0; r<n; r++) {
        String ts = br.readLine();
        String[] tsArr = ts.split(" ");
        
        for(int c=0; c<m; c++) {
          int t = Integer.parseInt(tsArr[c]);
          map[r][c] = t;
        }
      }
      
      //익은 토마토 탐색
      Deque<int[]> q = new ArrayDeque<>();
      for(int r=0; r<n; r++) {
        for(int c=0; c<m; c++) {
          //방문한 적 없는 익은 토마토면 큐에 추가 (시작점)
          //큐를 밖으로 빼서 처음에 익은 토마토가 떨어져 있을 경우, 동시에 시작할 수 있도록 처리
          if(map[r][c] == 1 && !visited[r][c]) {
            q.offer(new int[]{r, c, 0});
            visited[r][c] = true;
          }
        }
      }
      
      //시작점을 가지고 bfs
      bfs(q);
      
      //안익은 토마토가 있는지 확인 후 없으면 일수 리턴
      for(int r=0; r<n; r++) {
        for(int c=0; c<m; c++) {
          if(map[r][c] == 0) {
            days = -1; break;
          }
        }
      }
      
      System.out.println(days);
    }
    
    public static void bfs(Deque<int[]> q) {
      while(!q.isEmpty()) {
        int[] curr = q.poll();
        int nRow = curr[0];
        int nCol = curr[1];
        int nDay = curr[2];
        
        if(q.isEmpty()) days = Math.max(days, nDay);
        
        for(int i=0; i<4; i++) {
          int nxtRow = nRow + dRow[i];
          int nxtCol = nCol + dCol[i];
          
          if(isValid(nxtRow, nxtCol)) {
            visited[nxtRow][nxtCol] = true;
            
            //익은 토마토일 경우 일수 그대로
            if(map[nxtRow][nxtCol] == 1) q.offer(new int[]{nxtRow, nxtCol, nDay});
            //안익은 토마토일 경우 하루 추가
            else if(map[nxtRow][nxtCol] == 0) {
              q.offer(new int[]{nxtRow, nxtCol, nDay+1});
              map[nxtRow][nxtCol] = 1;
            }
          }
        }
      }
    }
    
    public static boolean isValid(int r, int c) {
      return r >= 0 && r < map.length && c >= 0 && c < map[0].length && !visited[r][c] && map[r][c] != -1;
    }
}