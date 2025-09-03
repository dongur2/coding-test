import java.util.*;
import java.io.*;

/*
  nxn 방 
  - (1,1)시작:항상 흰방 -> (n,n)끝:항상 흰방 
  - 붙어있는 흰방은 이동 가능/검은방은 이동 불가
  - 이동하면서 바꿔야 하는 검은방 최소 개수 (바꾸면 이동 가능)
*/
public class Main {
  static int[] dRow = new int[] {0, 1, 0, -1};
  static int[] dCol = new int[] {1, 0, -1, 0};
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int n = Integer.parseInt(br.readLine());
    
    int[][] room = new int[n+1][n+1];
    for(int i=1; i<=n; i++) {
      String row = br.readLine();
      for(int j=1; j<=n; j++) {
        room[i][j] = Character.getNumericValue(row.charAt(j-1));
      }
    }
    
    /*
      그 지점까지 가는 데 바꾼 최소 개수 
    */
    int res = dijkstra(room, n);
    System.out.println(res);
  }
  
  static int dijkstra(int[][] room, int n) {
    int[][] dp = new int[n+1][n+1];
    for(int[] d : dp) {
      Arrays.fill(d, Integer.MAX_VALUE);  
    }
    
    Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
    dp[1][1] = 0;
    pq.offer(new int[]{1, 1, 0});
    
    while(!pq.isEmpty()) {
      int[] curr = pq.poll();
      
      for(int i=0; i<4; i++) {
        int nextRow = curr[0] + dRow[i];
        int nextCol = curr[1] + dCol[i];
        
        if(isValid(n, nextRow, nextCol)) {
          /*
            흰 방일경우 기존 값과 비교 (새로 방 바꾸지 않음)
            검은 방일 경우 추가로 방 바꾼 횟수와 비교
          */
          int newCnt = (room[nextRow][nextCol] == 1) ? curr[2] : curr[2] + 1;
            
          if(newCnt >= dp[nextRow][nextCol]) continue;
          
          //새로 방 바꾼 횟수가 더 작으면 업데이트 후 이동
          dp[nextRow][nextCol] = newCnt;
          pq.offer(new int[] {nextRow, nextCol, newCnt});
        }
      }
    }
    
    return dp[n][n];
  }
  
  static boolean isValid(int n, int r, int c) {
    return r > 0 && r <= n && c > 0 && c <= n;
  }
}