import java.util.*; import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int T = Integer.parseInt(br.readLine());
    
    StringTokenizer st;
    
    for(int i=0; i<T; i++) {
        
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken()); //건물 개수 
      int k = Integer.parseInt(st.nextToken()); //규칙 개수 
      
      //건물별 건축 시간 
      st = new StringTokenizer(br.readLine());
      
      int[] times = new int[n+1];  
      for(int j=1; j<=n; j++) {
        times[j] = Integer.parseInt(st.nextToken());
      }
      
      //건물 건축 순서 
      Map<Integer, List<Integer>> rules = new HashMap<>();
      
      int[] pre = new int[n+1]; //선행 개수 
      
      for(int j=0; j<k; j++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        
        rules.computeIfAbsent(x, key -> new ArrayList<>()).add(y);
        
        //선행 건축물 개수 
        pre[y]++;
      }
      
      //최종적으로 지어야 하는 건물 
      int w = Integer.parseInt(br.readLine());
      
      /* 
        순서대로 건축 
      */
      
      int[] dp = new int[n+1]; //i번째 건물 짓는데 걸리는 최소 시간
      
      Queue<Integer> q = new ArrayDeque<>();
      
      //선행 건물 없는 건물 우선 
      for(int j=1; j<=n; j++) {
        if(pre[j] == 0) {
          dp[j] = times[j];
          q.offer(j);
        }
      }
      
      while(!q.isEmpty()) {
        int curr = q.poll();
        
        if(!rules.containsKey(curr)) continue;
        
        for(int next : rules.get(curr)) {
          dp[next] = Math.max(dp[next], dp[curr] + times[next]);
          if(--pre[next] == 0) q.offer(next);
        }
      }
      
      System.out.println(dp[w]);
    }
    
  }
}