import java.util.*;
import java.io.*;

public class Main {
    //인접 이동 
    static int[] dRow = {0, 1, 0, -1};
    static int[] dCol = {1, 0, -1, 0};
    
    static int[][] map;
    static boolean[][] visited;
    
    //(1, 1) -> (n, m)으로 이동하는 데 필요한 최소 칸 수 (bfs)
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      map = new int[n][m];
      visited = new boolean[n][m];
      
      //지도
      for(int r=0; r<n; r++) {
        String line = br.readLine();
        for(int c=0; c<m; c++) {
          int num = Integer.parseInt(line.charAt(c)+"");
          map[r][c] = num;
        }
      }
      
      System.out.println(bfs(n, m));
    }
    
    public static int bfs(int n, int m) {
      Deque<int[]> q = new ArrayDeque<>();
      q.offer(new int[] {0, 0, 1});
      visited[0][0] = true;
      
      while(!q.isEmpty()) {
        int[] curr = q.poll();
        int row = curr[0];
        int col = curr[1];
        int cnt = curr[2];
        
        if(row == n-1 && col == m-1) return cnt;
        
        for(int i=0; i<4; i++) {
          int nxtRow = row + dRow[i];
          int nxtCol = col + dCol[i];
          
          if(isValid(n, m, nxtRow, nxtCol)) {
            q.offer(new int[]{nxtRow, nxtCol, cnt+1});
            visited[nxtRow][nxtCol] = true;
          }
        }
      }
      
      return -1;
    }
    
    public static boolean isValid(int n, int m, int r, int c) {
      return r >= 0 && r < n && c >= 0 && c < m && map[r][c] == 1 && !visited[r][c];
    }
}