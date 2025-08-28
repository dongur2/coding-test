import java.util.*;
import java.io.*;

/*
  >>> 출발점 -> 도착점 사이 '거의 최단 경로'
  [거의 최단 경로] 최단 경로에 포함되지 않는 도로로만 이루어진 경로 중 최단 경로
  *거의 최단 경로는 여러 개이거나 없을 수도 있다.
  
  - 장소 수 N (2<=N<=500) 0번~N-1번
  - 도로 수 M (1<=M<=10^4)
  - 시작점 S, 도착점 D (둘은 무조건 다르다)
  - 방향 가중치 그래프 
*/
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    while(true) {
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken()); //장소 개수 
      int m = Integer.parseInt(st.nextToken()); //도로 개수
      
      if(n == 0 && m == 0) break; //00이면 종료 
      
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken()); //출발지 
      int d = Integer.parseInt(st.nextToken()); //도착지
      
      //맵 생성 
      Map<Integer, List<Load>> map = new HashMap<>();
      for(int i=0; i<m; i++) {
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken()); //출발 장소 
        int to = Integer.parseInt(st.nextToken()); //도착 장소 
        int length = Integer.parseInt(st.nextToken()); //도로 길이  
        
        map.computeIfAbsent(from, k->new ArrayList<>()).add(new Load(to, length));
      }
      
      //제외할 장소 
      Map<Integer, List<Integer>> prev = dijkstra1(map, n, s, d);
      
      //간선 제거 
      boolean[][] removed = new boolean[n][n];
      remove(removed, prev, d);
      
      //간선 제거 후 다시 최단 거리 찾기 
      System.out.println(dijkstra2(map, n, s, d, removed));
    }
  }
  
  static Map<Integer, List<Integer>> dijkstra1(Map<Integer, List<Load>> map, int n, int s, int d) {
    Map<Integer, List<Integer>> prev = new HashMap<>();
    
    int[] dp = new int[n];
    Arrays.fill(dp, Integer.MAX_VALUE);
    
    //짧은 거리 우선 
    PriorityQueue<Load> pq = new PriorityQueue<>();
    dp[s] = 0; //출발지 초기화 
    pq.offer(new Load(s, 0)); 
    
    while(!pq.isEmpty()) {
      Load curr = pq.poll();
      if (curr.dist > dp[curr.to]) continue;
      
      if(map.get(curr.to) == null) continue;
      for(Load next : map.get(curr.to)) {
        int newDist = curr.dist + next.dist;
        
        if(dp[next.to] > newDist) {
          dp[next.to] = newDist;
          pq.offer(new Load(next.to, newDist));
          prev.put(next.to, new ArrayList<>(List.of(curr.to)));
        } else if(dp[next.to] == newDist) prev.computeIfAbsent(next.to, k -> new ArrayList<>()).add(curr.to);
      }
    }
    
    return prev;
  }
  
  static void remove(boolean[][] removed, Map<Integer, List<Integer>> prev, int d) {
    Deque<Integer> q = new ArrayDeque<>();
    q.offer(d);
    
    while(!q.isEmpty()) {
      int curr = q.poll();
      
      if (!prev.containsKey(curr)) continue;

      for(int next : prev.get(curr)) {
        if(removed[next][curr]) continue;
        removed[next][curr] = true; q.offer(next);
      }
    }
  }
  
  static int dijkstra2(Map<Integer, List<Load>> map, int n, int s, int d, boolean[][] removed) {
      int[] dist = new int[n];
      Arrays.fill(dist, Integer.MAX_VALUE);
      PriorityQueue<Load> pq = new PriorityQueue<>();
      dist[s] = 0;
      pq.offer(new Load(s, 0));
  
      while (!pq.isEmpty()) {
          Load curr = pq.poll();
          if (curr.dist > dist[curr.to]) continue;
  
          if (map.get(curr.to) == null) continue;
          for (Load next : map.get(curr.to)) {
              if (removed[curr.to][next.to]) continue; //제거된 간선 무시
              int nd = curr.dist + next.dist;
              if (dist[next.to] > nd) {
                  dist[next.to] = nd;
                  pq.offer(new Load(next.to, nd));
              }
          }
      }
      return dist[d] == Integer.MAX_VALUE ? -1 : dist[d];
  }
    
  static class Load implements Comparable<Load> {
    int to, dist;
    
    public Load(int to, int dist) {
      this.to=to; this.dist=dist;
    }
    @Override
    public int compareTo(Load l) {
      return this.dist - l.dist;
    }
  }

}