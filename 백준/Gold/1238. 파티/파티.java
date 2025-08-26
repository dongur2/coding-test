import java.util.*;
import java.io.*;

/*
  >>> 학생 중 최대 왕복 시간
  - n개 마을/n개 학생 
  - m개 단방향 도로 
  - X 마을 기준
*/
public class Main {
  static final int INF = Integer.MAX_VALUE;
  
  //시작도시: [도착도시+비용]
  static Map<Integer, List<Road>> map = new HashMap<>();
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    int x = Integer.parseInt(st.nextToken());
    
    //도로
    for(int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      
      //맵에 등록  
      map.computeIfAbsent(from, k->new ArrayList<>()).add(new Road(from, to, cost));
    }
    
    //각 도시 -> X 최소 비용
    int[] toX = new int[n+1];
    for(int i=1; i<=n; i++) {
      //x도시는 무시 (어차피 0)
      if(i == x) continue;
  
      int[] costs = explore(n, i);
      toX[i] = costs[x];
    }
    
    //X -> 각 도시 최소 비용
    int[] fromX = explore(n, x);
    
    //최대 왕복 비용 구하기 
    int result = -1;
    for(int i=1; i<=n; i++) {
      result = Math.max(result, toX[i]+fromX[i]);
    }
    System.out.println(result);
  }
  
  static int[] explore(int n, int start) {
      int[] dist = new int[n+1];
      Arrays.fill(dist, INF);
      dist[start] = 0;
  
      PriorityQueue<Road> pq = new PriorityQueue<>();
      pq.offer(new Road(start, start, 0)); 
  
      while(!pq.isEmpty()) {
          Road curr = pq.poll();
          if(dist[curr.to] < curr.cost) continue;
  
          if(map.get(curr.to) == null) continue;
          for(Road next : map.get(curr.to)) {
              int newCost = dist[curr.to] + next.cost;
              if(newCost < dist[next.to]) {
                  dist[next.to] = newCost;
                  pq.add(new Road(curr.to, next.to, newCost));
              }
          }
      }
      return dist;
  }
  
  static class Road implements Comparable<Road>{
    int from, to, cost;
    public Road(int from, int to, int cost) {
      this.from=from; this.to=to; this.cost=cost;
    }
    
    @Override
    public int compareTo(Road o) {
        return this.cost - o.cost;
    }
  }

}