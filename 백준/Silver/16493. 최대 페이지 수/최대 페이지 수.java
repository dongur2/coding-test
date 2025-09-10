import java.util.*;
import java.io.*;

/*
  >>> 읽을 수 있는 최대 페이지 수
  남은 기간 n일 
  각 챕터별 - 소요일 수, 페이지 수 
  챕터 순서 상관 없음 
  ==> dp[i] = i일까지 읽은 최대 페이지 수 => 차례대로 챕터를 포함한 경우와 기존 값을 비교 
*/
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()); //남은 일수 
    int m = Integer.parseInt(st.nextToken()); //챕터 개수 
    
    int[][] chapters = new int[m+1][2];
    for(int i=1; i<=m; i++) {
      st = new StringTokenizer(br.readLine());
      int d = Integer.parseInt(st.nextToken()); //챕터 소요일 수
      int p = Integer.parseInt(st.nextToken()); //챕터 페이지 수
      chapters[i][0] = d; chapters[i][1] = p;
    }
    
    int[] dp = new int[n+1];
    for(int i=1; i<=m; i++) {
      int period = chapters[i][0];
      int pages = chapters[i][1];
      
      for(int day=n; day>=period; day--) {
        dp[day] = Math.max(dp[day], dp[day-period]+pages);
      }
    }
    
    System.out.println(Arrays.stream(dp).max().getAsInt());
  }
}