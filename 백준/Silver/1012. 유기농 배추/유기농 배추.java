import java.util.*;
import java.io.*;

public class Main {
    static int[] dRow = {0, 1, 0, -1};
    static int[] dCol = {1, 0, -1, 0};
    
    static List<Integer> answer = new ArrayList<>();
    
    //필요한 지렁이 수
    //배추가 모여있는 지역 당 1마리 -> 군집 개수
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int t = Integer.parseInt(br.readLine());
      
      for(int i=0; i<t; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int m = Integer.parseInt(st.nextToken()); //가로 
        int n = Integer.parseInt(st.nextToken()); //세로 
        int k = Integer.parseInt(st.nextToken()); //배추 개수
        
        int[][] map = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        
        for(int j=0; j<k; j++) {
          st = new StringTokenizer(br.readLine());
          int row = Integer.parseInt(st.nextToken());
          int col = Integer.parseInt(st.nextToken());
          
          map[row][col] = 1;
        }
        
        int cnt = 0;
        for(int r=0; r<m; r++) {
          for(int c=0; c<n; c++) {
            if(visited[r][c] || map[r][c] == 0) continue;
            
            visited[r][c] = true;
            cnt++;
            dfs(map, visited, r, c);
          }
        }
        
        answer.add(cnt);
      }
      
      for(int a:answer) {
        System.out.println(a);
      }
    }
    
    public static void dfs(int[][] map, boolean[][] visited, int row, int col) {
      for(int i=0; i<4; i++) {
        int nxtR = row + dRow[i];
        int nxtC = col + dCol[i];
        
        if(isValid(map, visited, nxtR, nxtC)) {
          visited[nxtR][nxtC] = true;
          dfs(map, visited, nxtR, nxtC);
        }
      }
    }
    
    public static boolean isValid(int[][] map, boolean[][] visited, int r, int c) {
      return r >= 0 && r < map.length && c >= 0 && c < map[0].length && !visited[r][c] && map[r][c] == 1;
    }
}