import java.util.*;
import java.io.*;

public class Main {
    public static int[] coins = {500, 100, 50, 10, 5, 1};
    
    //최소 잔돈 개수
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int answer = 0;
      
      //금액
      int p = Integer.parseInt(br.readLine());
     
      //거스름돈
      int change = 1000 - p;
      
      int remain = change;
      while(remain > 0) {
        for(int i=0; i<6; i++) {
          if(remain / coins[i] == 0) continue;
          
          int coin = remain / coins[i];
          answer += coin; //동전 개수 추가
          
          remain %= coins[i]; //나머지 진행
        }  
      }
      
      System.out.print(answer);
  }
}