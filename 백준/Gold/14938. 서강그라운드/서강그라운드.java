import java.util.*;
import java.io.*;

/*
  >>> 얻을 수 있는 아이템 최대 개수 
  - 각 지역은 다른 지역과 길로 연결 (양방향)
  - 낙하 지역 중심 m 거리 이내 습득 
  
  떨어진 지점에서 m 거리 내에 있는 지역 방문 가능 
  -> dp[i] i 지점에서 방문할 수 있는 지역의 아이템 개수 합산 
*/
public class Main {
  static class Edge implements Comparable<Edge> {
    int to, len;
    public Edge (int to, int len) {
      this.to=to; this.len=len;
    }
    @Override
    public int compareTo(Edge e) {
      return this.len - e.len;
    }
  }
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()); //지역 개수 
    int m = Integer.parseInt(st.nextToken()); //수색 범위
    int r = Integer.parseInt(st.nextToken()); //길 개수 
    
    //각 지역에 있는 아이탬 개수 
    int[] items = new int[n+1];
    st = new StringTokenizer(br.readLine());
    for(int i=1; i<=n; i++) {
      items[i] = Integer.parseInt(st.nextToken());
    }
    
    //그래프 
    Map<Integer, List<Edge>> map = new HashMap<>();
    for(int i=0; i<r; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()); //지역1
      int b = Integer.parseInt(st.nextToken()); //지역2 
      int l = Integer.parseInt(st.nextToken()); //길이 
      
      map.computeIfAbsent(a, k->new ArrayList<>()).add(new Edge(b, l));
      map.computeIfAbsent(b, k->new ArrayList<>()).add(new Edge(a, l));
    }
    
    //각 지점에 떨어졌을 때 얻을 수 있는 최대 아이템 개수 
    int res = 0;
    for(int i=1; i<=n; i++) {
      int[] dist = dijkstra(map, n, m, i);
      
      int cnt = 0;
      for(int idx=1; idx<=n; idx++) {
        if(dist[idx] <= m) cnt += items[idx];
      }
      res = Math.max(res, cnt);
    }
    System.out.println(res);
  }
  
  static int[] dijkstra(Map<Integer, List<Edge>> map, int n, int m, int from) {
    int[] dist = new int[n+1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    
    Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
    dist[from] = 0;
    pq.offer(new int[]{from, 0});
    
    while(!pq.isEmpty()) {
      int[] curr = pq.poll();
      
      if(!map.containsKey(curr[0])) continue;
      
      for(Edge next : map.get(curr[0])) {
        int newDist = dist[curr[0]] + next.len;

        if(newDist >= dist[next.to]) continue;
        
        dist[next.to] = newDist;
        pq.offer(new int[]{next.to, newDist});
      }
    }
    
    return dist;
  }
}