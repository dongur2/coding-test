import java.util.*;
import java.io.*;

/*
  >>> 체력(100) 내 얻을 수 있는 최대 기쁨
  n명(1-n)
  감소 체력 L[i]
  증가 기쁨 J[i] 
  한번씩만 가능 
*/
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int n = Integer.parseInt(br.readLine());
    
    int[] need = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=0; i<n; i++) {
      need[i] = Integer.parseInt(st.nextToken());
    }
    
    int[] smile = new int[n];
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<n; i++) {
      smile[i] = Integer.parseInt(st.nextToken());
    }
    
    /* 
      dp[i] 체력 i을 소모했을 때 최대 기쁨 
    */
    int[] dp = new int[101]; 
    
    for(int i=0; i<n; i++) {
      int cost = need[i];
      int joy = smile[i];
      
      for(int hp=100; hp>cost; hp--) {
        dp[hp] = Math.max(dp[hp], dp[hp-cost]+joy);
      }
    }
    
    System.out.println(Arrays.stream(dp).max().getAsInt());
  }
}