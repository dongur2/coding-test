import java.util.*;
import java.io.*;

//그림 개수, 가장 넓은 그림 넓이 
//그림이 없으면 넓이는 0
public class Main {
  static int[] dRow = new int[] {0, 1, 0, -1};
  static int[] dCol = new int[] {1, 0, -1, 0};
  
  static int cnt = 0;
  static int width = 0;
  
  static int n, m;
  static int[][] map;
  static boolean[][] visited;
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //n*m
    //0 빈칸, 1 색칠 
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    
    map = new int[n][m];
    visited = new boolean[n][m];
    
    for(int r=0; r<n; r++) {
      st = new StringTokenizer(br.readLine());
      for(int c=0; c<m; c++) {
        map[r][c] = Integer.parseInt(st.nextToken());
      }
    }
    
    //가로세로로 연결된 영역이 그림 하나 
    //dfs로 연결된 영역 전체 탐색 
    for(int r=0; r<n; r++) {
      for(int c=0; c<m; c++) {
        if(map[r][c] == 1 && !visited[r][c]) {
          width = Math.max(dfs(r, c), width);
          cnt++;
        }
      }
    }

    System.out.println(cnt);
    System.out.println(width);
  }
  
  public static int dfs(int r, int c) {
    int w = 1;
    visited[r][c] = true;
    
    for(int i=0; i<4; i++) {
      int nr = r + dRow[i];
      int nc = c + dCol[i];
      
      if(isValid(nr, nc)) w += dfs(nr, nc);
    }
    
    return w;
  }
  
  public static boolean isValid(int r, int c) {
    return r>=0 && r<n && c>=0 && c<m && !visited[r][c] && map[r][c] == 1;
  }
}