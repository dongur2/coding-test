import java.util.*;
import java.io.*;

//목적지까지 이동할 수 있는 최단 거리 (불가능하면 -1)
public class Main {
  //상하좌우 이동
  static int[] dRow = {0, 1, 0, -1};
  static int[] dCol = {1, 0, -1, 0}; 
  
  static int n = -1;
  static int m = -1;
  
  static int[][] map;
  
  public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      //맵 생성
      map = new int[n][m];
      for(int r=0; r<n; r++) {
        String input = br.readLine();
        for(int c=0; c<m; c++) {
          int s = Integer.parseInt(input.charAt(c)+"");
          map[r][c] = s;
        }
      }
      
      //벽 하나를 부순 모든 경우의 수를 확인
      int moves = bfs(new boolean[n][m][2]); //최소 거리 탐색
      
      //가능한 길이 없을 경우 -1 
      System.out.println(moves);
  }

  public static int bfs(boolean[][][] visited) {
    Deque<int[]> q = new ArrayDeque<>();
    q.offer(new int[]{0, 0, 1, 0}); //항상 (0,0)에서 시작
    visited[0][0][0] = true; //벽 철거 기회 있는 상태에서 시작 
    
    while(!q.isEmpty()) {
      int[] curr = q.poll();
      int nowR = curr[0];
      int nowC = curr[1];
      int nowMoves = curr[2];
      int broked = curr[3];
      
      //목적지에 도착했으면 리턴
      if(nowR == n-1 && nowC == m-1) return nowMoves; //(n,m)
      
      for(int i=0; i<4; i++) {
        int nxtR = nowR + dRow[i];
        int nxtC = nowC + dCol[i];
        
        if(isValid(nxtR, nxtC)) {
          //벽이 아닐 경우 조건 그대로 진행 
          if(map[nxtR][nxtC] == 0 && !visited[nxtR][nxtC][broked]) {
            visited[nxtR][nxtC][broked] = true;
            q.offer(new int[]{nxtR, nxtC, nowMoves+1, broked});    
          }
          
          //벽일 경우: 기회가 남아있고, 벽 부수고 나서 도착한 적 없으면 
          if(map[nxtR][nxtC] == 1 && broked == 0 && !visited[nxtR][nxtC][1]) {
            visited[nxtR][nxtC][1] = true;
            q.offer(new int[]{nxtR, nxtC, nowMoves+1, 1});  
          }
        }
      }
    }
    //도착할 수 없으면 -1 리턴 
    return -1;
  }
  
  public static boolean isValid(int r, int c) {
    return r>=0 && r<n && c>=0 && c<m;
  }
}