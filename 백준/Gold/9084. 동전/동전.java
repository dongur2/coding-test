import java.util.*;
import java.io.*;

/*
  >>> n가지 동전으로 금액 m을 만드는 모든 방법의 수 
  
*/
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int t = Integer.parseInt(br.readLine());
    
    for(int i=0; i<t; i++) {
      
      int n = Integer.parseInt(br.readLine()); //동전 종류 개수 
      
      //동전 종류  1 ~ 10^4
      StringTokenizer st = new StringTokenizer(br.readLine());
      int[] coins = new int[n]; //오름차순 
      for(int j=0; j<n; j++) {
        coins[j] = Integer.parseInt(st.nextToken());
      }
      
      int m = Integer.parseInt(br.readLine()); //만들어야하는 금액 
      
      int[] cnt = new int[m+1]; //cnt[x]: 금액 x를 만드는 경우의 수 
      cnt[0] = 1; //0원은 1가지 (0)
      
      for(int coin : coins) {
        for(int price=coin; price<=m; price++) {
          cnt[price] += cnt[price-coin]; 
        }
      }
      
      System.out.println(cnt[m]);
      
    }
  }
}