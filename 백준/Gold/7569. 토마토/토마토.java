import java.util.*;
import java.io.*;

public class Main {
    //인접 지역: 상하좌우, 위아래
    static int[] dRow = {0, 1, 0, -1};
    static int[] dCol = {1, 0, -1, 0};
    static int[] dHgh = {-1, 1}; //위아래
    
    static int[][][] map;
    static boolean[][][] visited;
    
    //토마토가 익는데 필요한 최소 일수(->bfs)
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      
      //토마토 상자 생성 (0:안익은 토마토, 1:익은 토마토, -1:없음)
      map = new int[h][n][m];
      visited = new boolean[h][n][m];
      
      for(int l=0; l<h; l++) {
        for(int r=0; r<n; r++) { 
          st = new StringTokenizer(br.readLine());
          
          for(int c=0; c<m; c++) {
            int t = Integer.parseInt(st.nextToken());
            map[l][r][c] = t;
          }
        }
      }
      
      //시작할 큐 생성 및 시작할 익은 토마토 탐색
      Deque<int[]> q = new ArrayDeque<>(); //좌표, 일수
      for(int l=0; l<h; l++) {
        for(int r=0; r<n; r++) { 
          for(int c=0; c<m; c++) {
            if(map[l][r][c] == 1) {
              visited[l][r][c] = true;
              q.offer(new int[]{l, r, c, 0});
            }
          }
        }
      }
      
      //카운팅
      int answer = Integer.MIN_VALUE;
      answer = Math.max(bfs(q, n, m, h), answer);
      
      for(int l=0; l<h; l++) {
        for(int r=0; r<n; r++) { 
          for(int c=0; c<m; c++) {
            if(map[l][r][c] == 0) {
              answer = -1; break;
            }
          }
        }
      }
      
      System.out.println(answer);
    }
    
    public static int bfs(Deque<int[]> q, int n, int m, int h) {
      while(!q.isEmpty()) {
        int[] curr = q.poll();
        int nowHeight = curr[0];
        int nowRow = curr[1];
        int nowCol = curr[2];
        int nowDay = curr[3];
        
        //상하좌우
        for(int i=0; i<4; i++) {
          int nxtR = nowRow + dRow[i];
          int nxtC = nowCol + dCol[i];
          
          if(isValid(n, m, h, nxtR, nxtC, nowHeight)) {
            int days = map[nowHeight][nxtR][nxtC] == 0 ? nowDay+1 : nowDay; //안익은 경우에만 일수 카운트
            visited[nowHeight][nxtR][nxtC] = true;
            map[nowHeight][nxtR][nxtC] = 1;
            q.offer(new int[]{nowHeight, nxtR, nxtC, days});
          }
        }
        
        //위아래
        for(int i=0; i<2; i++) {
          int nxtH = nowHeight + dHgh[i];
          
          if(isValid(n, m, h, nowRow, nowCol, nxtH)) {
            int days = map[nxtH][nowRow][nowCol] == 0 ? nowDay+1 : nowDay; //안익은 경우에만 일수 카운트
            visited[nxtH][nowRow][nowCol] = true;
            map[nxtH][nowRow][nowCol] = 1;
            q.offer(new int[]{nxtH, nowRow, nowCol, days});
          }
        }
        
        //더이상 대기열이 없으면 중지
        if(q.isEmpty()) return nowDay;
      }
      return -1;
    }
    
    public static boolean isValid(int n, int m, int h, int r, int c, int l) {
      return r >= 0 && r < n && c >= 0 && c < m && l >= 0 && l < h && !visited[l][r][c] && map[l][r][c] != -1;
    }
}