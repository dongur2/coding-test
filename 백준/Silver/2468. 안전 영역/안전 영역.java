import java.util.*;
import java.io.*;

//물에 잠기지 않는 안전 영역의 최대 개수 
public class Main {
    static int[][] map;
    static boolean[][] visited;
    
    //상하좌우 인접
    static int[] dRow = {0, 1, 0, -1};
    static int[] dCol = {1, 0, -1, 0};
    
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(br.readLine());
      
      //지도 생성 
      map = new int[n][n];
      
      //주어진 강수량
      Set<Integer> rains = new HashSet<>();
      rains.add(0); //비가 아예 오지 않는 경우 추가
      
      for(int r=0; r<n; r++) {
        String input = br.readLine();
        String[] height = input.split(" ");
        for(int c=0; c<n; c++) {
          int h = Integer.parseInt(height[c]);
          map[r][c] = h;
          rains.add(h);
        }
      }
      
      //주어진 모든 높이의 강수량의 경우에 대해 안전영역 확인
      int maxSafety = 1;
      for(int rain:rains) {
        //방문 초기화
        visited = new boolean[n][n];
        
        int safe = 0;
        for(int r=0; r<n; r++){
          for(int c=0; c<n; c++) {
            //방문한 적 없는 안전영역 발견 시 이어진 영역 탐색
            if(!visited[r][c] && map[r][c] > rain) {
              visited[r][c] = true;
              safe++;
              dfs(n, r, c, rain);
            }     
          }
        }
        maxSafety = Math.max(maxSafety, safe);
      }
      
      System.out.println(maxSafety);
    }
    
    public static void dfs(int n, int row, int col, int rain) {
      for(int i=0; i<4; i++) {
        int nxtR = row + dRow[i]; 
        int nxtC = col + dCol[i];
        
        if(isValid(n, nxtR, nxtC, rain)) {
          visited[nxtR][nxtC] = true;
          dfs(n, nxtR, nxtC, rain);
        }
      }
    }
    
    //안전구역 
    public static boolean isValid(int n, int r, int c, int rain) {
      return r >= 0 && r < n && c >= 0 && c < n && !visited[r][c] && map[r][c] > rain;
    }
}