import java.util.*;
import java.io.*;

/*
  미로 N*M - 빈방/벽
  -운영진 항상 모두 같은 방 
  -상하좌우에 있는 빈 방으로 이동 가능 
  -벽 부수고 이동 가능 
  >>> (1,1) -> (n,m) 이동하는 데 부숴야 하는 최소 벽 개수 
*/
public class Main {
  static final int INF = Integer.MAX_VALUE;
  
  static int[] dRow = new int[] {0, 1, 0, -1};
  static int[] dCol = new int[] {1, 0, -1, 0};
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int M = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());
    
    //map 
    int[][] map = new int[N+1][M+1];
    for(int i=1; i<=N; i++) {
      String row = br.readLine();
      for(int j=0; j<M; j++) {
        map[i][j+1] = Character.getNumericValue(row.charAt(j));
      }
    }
    
    //각 칸에 오는 데 부숴야하는 최소 벽 개수 업데이트 
    System.out.println(dj(N, M, map));
  }
  
  static int dj(int N, int M, int[][] map) {
    int[][] dp = new int[N+1][M+1];
    
    //INF 초기화 
    for(int[] d:dp) {
      Arrays.fill(d, INF);
    }
    
    PriorityQueue<Room> pq = new PriorityQueue<>();
    
    //출발점 
    pq.offer(new Room(1, 1, 0));
    dp[1][1] = 0;
    
    while(!pq.isEmpty()) {
      Room curr = pq.poll();
      
      //인접 방 중에 새로 계산한 벽 부순 개수가 작거나 같을 경우만 이동 
      for(int i=0; i<4; i++) {
        int nx = curr.x + dRow[i];
        int ny = curr.y + dCol[i];
        
        //범위 내 
        if(nx >= 1 && nx <= N && ny >= 1 && ny <= M) {
          int newBrokenWalls = curr.w + ((map[nx][ny] == 0) ? 0 : 1);
          if(dp[nx][ny] > newBrokenWalls) {
            dp[nx][ny] = newBrokenWalls;
            pq.offer(new Room(nx, ny, newBrokenWalls));
          }
        }
      }
    }
    
    return dp[N][M];
  }
  
  static class Room implements Comparable<Room> {
    int x, y, w;
    
    public Room(int x, int y, int w) {
      this.x=x; this.y=y; this.w=w;
    }
    
    //벽 부순 개수 작은 칸 먼저 
    @Override
    public int compareTo(Room r) {
      return this.w - r.w;
    }
  }
}