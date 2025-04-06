import java.util.*;
import java.io.*;

public class Main {
    static int[] dRow = {0, 1, 0, -1};
    static int[] dCol = {1, 0, -1, 0};
    
    static int[][] map;
    static boolean[][] visited;
    
    static int n = 0;
    static int cnt = 0;
    static List<Integer> area = new ArrayList<>();
    
    //단지에 속한 집 개수를 오름차순으로 출력
    //연결되어 있는 그룹이 단지 (상하좌우)
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      n = Integer.parseInt(br.readLine()); //가로세로
      
      map = new int[n][n];
      visited = new boolean[n][n];
    
      for(int r=0; r<n; r++) {
        String line = br.readLine();
        for(int c=0; c<n; c++) {
          map[r][c] = Character.getNumericValue(line.charAt(c));
        }
      }
      
      for(int r=0; r<n; r++) {
        for(int c=0; c<n; c++) {
          if(visited[r][c] || map[r][c] == 0) continue;
          
          visited[r][c] = true;
          dfs(r, c); //새로운 단지 탐색
          area.add(cnt); //집 개수 저장
          cnt = 0; //집 개수 초기화
        }
      }
      
      System.out.println(area.size());
      area.sort((a1, a2) -> a1 - a2);
      for(int a:area) {
        System.out.println(a);
      }
    }
    
    public static void dfs(int row, int col) {
      cnt++;
      
      for(int i=0; i<4; i++) {
        int nxtR = row + dRow[i];
        int nxtC = col + dCol[i];
        
        if(isValid(nxtR, nxtC)) {
          visited[nxtR][nxtC] = true;
          dfs(nxtR, nxtC);
        }
      }
    }
    
    public static boolean isValid(int r, int c) {
      return r >= 0 && r < n && c >= 0 && c < n && !visited[r][c] && map[r][c] == 1;
    }
 
}