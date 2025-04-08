import java.util.*;
import java.io.*;

public class Main {
    //색약인 사람이 보는 구역의 수 & 아닌 사람이 보는 구역의 수
    //색약: 빨강, 초록 하나 취급 
    //dfs로 같은 색인 구역을 카운트
    
    static boolean[][] visited;
    
    static int[] dRow = {0, 1, 0, -1};
    static int[] dCol = {1, 0, -1, 0};
    
    //지역 수 
    static int colorsExceptRG = 0,  colors = 0;
    
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(br.readLine());
      
      //지도 생성
      char[][] map = new char[n][n];
      char[][] mapWithNoRedAndGreen = new char[n][n];
      
      visited = new boolean[n][n];
      
      for(int r=0; r<n; r++) {
        String input = br.readLine();
        char[] arr = input.toCharArray();
        
        for(int c=0; c<n; c++) {
          map[r][c] = arr[c];
          mapWithNoRedAndGreen[r][c] = (arr[c] != 'B') ? 'N' : 'B'; //색약 지도는 빨/초 병합
        }
      }
      
      //각각 지도를 매개변수로 전달해서 탐색
      colors = countColors(n, map);
      visited = new boolean[n][n]; //방문 초기화 
      colorsExceptRG = countColors(n, mapWithNoRedAndGreen); 
      
      System.out.println(colors +" "+colorsExceptRG);
    }
    
    public static int countColors(int n, char[][] map) {
      int cnt = 0;
      
      //방문하지 않은 구역을 발견하면 탐색
      for(int r=0; r<n; r++) {
        for(int c=0; c<n; c++) {
          if(visited[r][c]) continue;
          
          cnt++;
          dfs(n, r, c, map[r][c], map); //현재 색깔과 일치하는 지역만 탐색
        }
      }
      
      return cnt;
    }
    
    public static void dfs(int n, int r, int c, char color, char[][] map) {
      //인접한 지역에 같은 색이 있는지 탐색
      //존재하면 이동
      for(int i=0; i<4; i++) {
        int nxtR = r + dRow[i];
        int nxtC = c + dCol[i];
        
        //범위 내 + 같은 색상 + 방문하지 않은 영역이면 방문
        if(isValid(n, nxtR, nxtC, color, map)) {
          visited[nxtR][nxtC] = true;
          dfs(n, nxtR, nxtC, color, map);
        }
      }
    }
    
    public static boolean isValid(int n, int r, int c, char color, char[][] map) {
      return r >= 0 && r < n && c >= 0 && c < n && !visited[r][c] && map[r][c] == color;
    }
}