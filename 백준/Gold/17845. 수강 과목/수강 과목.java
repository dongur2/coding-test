import java.util.*;
import java.io.*;

/*
  >>> 얻을 수 있는 최대 중요도 
  공부 시간 한계 
*/
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()); //최대 공부 시간 
    int k = Integer.parseInt(st.nextToken()); //과목 수  
    
    int[][] subjects = new int[k][2];
    for(int i=0; i<k; i++) {
      st = new StringTokenizer(br.readLine());
      int p = Integer.parseInt(st.nextToken()); //중요도 
      int t = Integer.parseInt(st.nextToken()); //필요 시간   
      subjects[i][0] = p; subjects[i][1] = t;
    }
    
    long[] dp = new long[n+1]; //dp[i] i시간 썼을 때 얻는 최대 중요도
    for(int[] sub:subjects) {
      int priority = sub[0];
      int time = sub[1];
      
      //현재 과목을 들었을 때와 듣지 않았을 때 비교 
      for(int t=n; t>=time; t--) {
        dp[t] = Math.max(dp[t], dp[t-time]+priority);
      }
    }
    
    System.out.println(Arrays.stream(dp).max().getAsLong());
  }
}