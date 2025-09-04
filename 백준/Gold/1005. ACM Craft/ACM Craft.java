import java.util.*;
import java.io.*;

/*
  >>> 특정 건물을 가장 빨리 지을 때까지 걸리는 최소 시간
  - 게임마다 건물 건축 순서 고정 
*/
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int T = Integer.parseInt(br.readLine());
    
    for(int i=0; i<T; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());  
      int n = Integer.parseInt(st.nextToken()); //건물 개수  (1-n번)
      int k = Integer.parseInt(st.nextToken()); //건설순서 규칙 개수 
      
      //건물당 건설에 걸리는 시간 
      int[] build = new int[n+1];
      st = new StringTokenizer(br.readLine());
      for(int j=1; j<=n; j++) {
        build[j] = Integer.parseInt(st.nextToken());
      }
      
      //건설순서 x->y 
      Map<Integer, List<Integer>> g = new HashMap<>();
      int[] pre = new int[n+1]; //선행되어야 하는 건물 개수 
      
      for(int j=0; j<k; j++) {
        st = new StringTokenizer(br.readLine());  
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        g.computeIfAbsent(x, key -> new ArrayList<>()).add(y);
        pre[y]++;
      }
      
      int w = Integer.parseInt(br.readLine()); //특정 건물 
      
      /*
        w를 짓는 최소 시간 = 선행 건물이 끝나는 시간 중 최대값 + w 건물 건설 시간 
        dp[i] : i번째 건물 짓는데 걸리는 최소 시간 
      */
      int[] dp = new int[n+1];
      
      Queue<Integer> q = new ArrayDeque<>();
      
      //선행 건물 없는 건물의 경우 즉시 건설 
      for(int j=1; j<=n; j++) {
        if(pre[j] == 0) {
          dp[j] = build[j];
          q.offer(j);
        }
      }
      
      while (!q.isEmpty()) {
        int curr = q.poll();
        
        if(!g.containsKey(curr)) continue;
        for (int nxt : g.get(curr)) {
          dp[nxt] = Math.max(dp[nxt], dp[curr] + build[nxt]); //새로 계산한 시간이 더 크면 그 시간
          if (--pre[nxt] == 0) q.offer(nxt); //선행건물 개수 확인 
        }
      }
      
      System.out.println(dp[w]);
    }
    
    
  }
}