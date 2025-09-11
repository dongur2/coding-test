import java.util.*;
import java.io.*;


public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int t = Integer.parseInt(br.readLine());
    
    for(int i=0; i<t; i++) {
      
      int n = Integer.parseInt(br.readLine()); //동전 종류 수
      
      int[] coins = new int[n];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=0; j<n; j++) {
        coins[j] = Integer.parseInt(st.nextToken()); //오름차순 
      }
      
      int m = Integer.parseInt(br.readLine()); //만들어하는 금액 
      
      int[] cnt = new int[m+1]; //각 금액별 경우의 수 
      cnt[0] = 1; //0원은 1가지 고정 
      
      //현재 금액을 만들기 위한 경우의 수는 (현재 금액 - 동전 액수)원을 만드는 경우의 수 + 기존 값
      for(int coin : coins) {
        for(int price=coin; price<=m; price++) {
          cnt[price] += cnt[price-coin];
        }  
      }
      
      System.out.println(cnt[m]);
    } 
    
  }
}