import java.util.*;
import java.io.*;

public class Main {
    //바이러는 상하좌우로 인접한 빈 칸으로 전파
    static int[] dRow = {0, 1, 0, -1};
    static int[] dCol = {1, 0, -1, 0};
    
    static int[][] map;
    static boolean[][] visited;
    
    static int[][] newMap;
    
    static int answer = Integer.MIN_VALUE;
    
    //안전 영역 크기의 최대값 - dfs로 끝까지 감염
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                  
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      map = new int[n][m];
      visited = new boolean[n][m];
      
      //지도 생성 0:빈칸, 1:벽, 2:바이러스
      for(int r=0; r<n; r++) {
        String line = br.readLine();
        String[] arr = line.split(" ");
        
        for(int c=0; c<m; c++) {
          map[r][c] = Integer.parseInt(arr[c]);
        }
      }
      
      //벽 3개 세우기 (중복 불허용, 순서 상관없음 -> 조합)
      //벽을 세울 수 있는 모든 경우의 수에서 감염 후 안전구역 카운트 
      getMaxSafety(n, m, 0);
      
      System.out.println(answer);
    }
    
    public static void getMaxSafety(int n, int m, int walls) {
      //벽 3개 다 세웠으면 
      if(walls == 3) {
        //맵 복사 
        newMap = new int[n][m];
        for(int i=0; i<n; i++) {
          // newMap[i] = Arrays.copyOfRange(map[i], 0, m);
          newMap[i] = Arrays.copyOf(map[i], m);
        }
        
        //감염
        visited = new boolean[n][m]; //방문 초기화 
        infect(n, m);
        
        //안전구역 카운트
        int safety = countSafety(n, m);
        answer = Math.max(answer, safety);
        
        return;
      }
      
      
      for(int r=0; r<n; r++) {
        for(int c=0; c<m; c++) {
          //빈 공간이면 벽 생성
          if(map[r][c] == 0) {
            map[r][c] = 1;
            getMaxSafety(n, m, walls+1);
            map[r][c] = 0; //복구
          } 
        }
      }
    }
    
    public static int countSafety(int n, int m) {
      //안전 구역 카운트
      int cnt = 0;
      for(int r=0; r<n; r++) {
        for(int c=0; c<m; c++) {
          if(newMap[r][c] == 0) cnt++;
        }
      }     
      return cnt;
    }
    
    public static void infect(int n, int m) {
      for(int r=0; r<n; r++) {
        for(int c=0; c<m; c++) {
          //바이러스를 발견하면 감염 시작
          if(newMap[r][c] == 2 && !visited[r][c]) {
            visited[r][c] = true;
            dfs(n, m, r, c);
          }
        }
      }
    }
    
    public static void dfs(int n, int m, int r, int c) {
      //인접 지역에 바이러스나 빈 공간이 있을 경우 이동
      for(int i=0; i<4; i++) {
        int nxtR = r + dRow[i];
        int nxtC = c + dCol[i];
        
        if(isValid(n, m, nxtR, nxtC)) {
          visited[nxtR][nxtC] = true;
          //빈 공간이면 감염 
          if(newMap[nxtR][nxtC] == 0) newMap[nxtR][nxtC] = 2; 
          dfs(n, m, nxtR, nxtC); //이동
        }
      }
    }
    
    public static boolean isValid(int n, int m, int r, int c) {
      return r >= 0 && r < n && c >= 0 && c < m && !visited[r][c] && newMap[r][c] != 1;
    }
}