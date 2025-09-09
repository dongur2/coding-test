import java.util.*;
import java.io.*;

/*
  >>> 배낭에 넣을 수 있는 물건 가치의 최댓값 -> 무게가 k 이하인 조합 중 최대 가치
  물건 n개 - 무게 w, 가치 v 
  무게 k 제한 
*/
public class Main {
  static int res = 0;
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()); //10^2
    int k = Integer.parseInt(st.nextToken()); //10^5
    
    int[][] stuffs = new int[n][2]; //[무게, 가치]
    for(int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      stuffs[i][0] = Integer.parseInt(st.nextToken()); //10^5
      stuffs[i][1] = Integer.parseInt(st.nextToken()); //10^3
    }
    
    int[] dp = new int[k+1]; //무게가 i일 때 담을 수 있는 최대 가치 
    
    for(int i=0; i<n; i++) {
      int w = stuffs[i][0]; //현재 물건 무게 
      int v = stuffs[i][1];
      
      for(int j=k; j>=w; j--) {
        /*
          무게 j까지 담을 수 있는 최대 가치는 
          현재 저장한 최대 가치와 현재 물건을 포함해서 담았을 때 가치 중 큰 것 
          ==> 물건을 하나씩 보면서 해당 물건을 담을 수 있다면 dp 값을 갱신
        */
        dp[j] = Math.max(dp[j], dp[j-w]+v);
      }
    }
    
    System.out.println(dp[k]);
  }
}