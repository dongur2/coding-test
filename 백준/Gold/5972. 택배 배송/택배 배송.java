import java.util.*;
import java.io.*;

/*
  현서(1) -> 찬홍(N) 소 최소한으로 마주치는 경로 
  - 두 헛간 사이 여러 경로 가능 
  - 길의 길이는 고려하지 않음
  >>> 최소 여물 
*/
public class Main {
  static class Edge implements Comparable<Edge> {
    int to, cows;
    
    public Edge (int to, int cows) {
      this.to=to; this.cows=cows;
    }
    
    @Override
    public int compareTo(Edge b) {
      return this.cows - b.cows; //더 적은 수 우선 
    }
  }
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()); //헛간 수 
    int m = Integer.parseInt(st.nextToken()); //소 길 (양방향)
    
    Map<Integer, List<Edge>> map = new HashMap<>();
    for(int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()); //헛간1
      int b = Integer.parseInt(st.nextToken()); //헛간2
      int c = Integer.parseInt(st.nextToken()); //이 길에 있는 소 마리수  
      
      //양방향 
      map.computeIfAbsent(a, k->new ArrayList<>()).add(new Edge(b, c));
      map.computeIfAbsent(b, k->new ArrayList<>()).add(new Edge(a, c));
    }
    
    /*
      dp[i] i번 헛간까지 오면서 마주친 소 최소 마리 
    */
    int res = dijkstra(map, 1, n);
    System.out.println(res);
  }
  
  static int dijkstra(Map<Integer, List<Edge>> map, int start, int end) {
    int[] dp = new int[end+1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    
    Queue<Edge> pq = new PriorityQueue<>();
    dp[start] = 0;
    pq.offer(new Edge(start, 0));
    
    while(!pq.isEmpty()) {
      Edge curr = pq.poll();
      
      if(!map.containsKey(curr.to)) continue;
      
      for(Edge next : map.get(curr.to)) {
        int newCows = dp[curr.to] + next.cows;
        
        if(newCows < dp[next.to]) {
          dp[next.to] = newCows;
          pq.offer(new Edge(next.to, newCows));
        }
      }
    }
    
    return dp[end];
  }
}