import java.util.*;
import java.io.*;

//거스름돈으로 주는 동전의 "최소" 개수  (거스름돈을 줄 수 없으면 -1)
public class Main {
  public static void main(String[] args) throws IOException{
    //동전: 2원, 5원
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //금액
    int n = Integer.parseInt(br.readLine());
    
    //금액(인덱스)을 만드는 동전 개수 
    int[] coins = new int[100001];
    Arrays.fill(coins, Integer.MAX_VALUE);
    
    //0원은 0개
    coins[0] = 0;
    
    //2원과 5원의 경우를 각각 수행 
    for(int price=1; price<=n; price++) {
      if(price>=2 && coins[price-2] != Integer.MAX_VALUE) coins[price] = Math.min(coins[price], coins[price-2]+1);
      if(price>=5 && coins[price-5] != Integer.MAX_VALUE) coins[price] = Math.min(coins[price], coins[price-5]+1);
    }
    
    System.out.println((coins[n] == Integer.MAX_VALUE) ? -1 : coins[n]);
  }
}