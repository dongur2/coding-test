import java.util.*;
import java.io.*;

/*
  동굴: N x N (현재 위치: 0,0) 2<=n<=125
  출구: N-1,N-1 
  -칸마다 루피 -> 루피 크기만큼 소지금 감소 0<=k(루피크기)<=9
  -상하좌우 1칸씩 이동
  >>> 현재 위치 -> 출구까지 최소 감소 금액
*/
public class Main {
  static int[] dRow = new int[] {0, 1, 0, -1};
  static int[] dCol = new int[] {1, 0, -1, 0};
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int phase = 1;
    while(true) {
      int n = Integer.parseInt(br.readLine()); //동굴 크기 
      if(n == 0) break; //n=0일 때 입력 종료 
      
      //동굴 맵 생성 
      int[][] cave = new int[n][n];
      for(int i=0; i<n; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<n; j++) {
          cave[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      
      //각 위치까지의 최소 누적합 계산 
      int res = dj(cave, n);
      System.out.printf("Problem %d: %d\n", phase++, res);
    }
  }
  
  static int dj(int[][] cave, int n) {
    int[][] dp = new int[n][n];
    for(int[] d:dp) {
      Arrays.fill(d, Integer.MAX_VALUE);  
    }
    
    //더 작은 누적합 우선 
    PriorityQueue<Entry> pq = new PriorityQueue<>();
    
    //시작점 
    dp[0][0] = cave[0][0];
    pq.offer(new Entry(0, 0, dp[0][0]));
    
    while(!pq.isEmpty()) {
      Entry curr = pq.poll();
      
      //상하좌우 탐색 
      for(int i=0; i<4; i++) {
        int newX = curr.x + dRow[i];
        int newY = curr.y + dCol[i];
        
        if(!isValid(n, newX, newY)) continue;
        
        //기존 누적합보다 새로 계산한 누적합이 작을 때만 업데이트 후 이동 
        int newSum = curr.sum + cave[newX][newY];
        if(dp[newX][newY] > newSum) {
          dp[newX][newY] = newSum;
          pq.offer(new Entry(newX, newY, newSum));
        }
      }
    }
    
    return dp[n-1][n-1];
  }
  
  static boolean isValid(int n, int x, int y) {
    return x >= 0 && x < n && y >= 0 && y < n;
  }
  
  static class Entry implements Comparable<Entry>{
    int x, y, sum;
    public Entry(int x, int y, int sum) {
      this.x=x; this.y=y; this.sum=sum;
    }
    @Override
    public int compareTo(Entry e) {
      return this.sum - e.sum;
    }
  }
}