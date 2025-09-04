import java.util.*;
import java.io.*;

/*
  >>> 1번 도시->i번 도시로 가는 k번째 최단경로 (존재하지 않으면 -1)
  - 1번 도시에서 시작 
  - 같은 도시 중복 방문 가능 
*/
public class Main {
  static class Edge {
    int to, dist;
    public Edge(int to, int dist) { this.to=to; this.dist=dist; }
  }
  static class City implements Comparable<City> {
    int num, dist;
    public City(int num, int dist) { this.num=num; this.dist=dist; }
    @Override public int compareTo(City c) { return this.dist - c.dist; }
  }
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()); //도시 개수 
    int m = Integer.parseInt(st.nextToken()); //도로 개수 
    int k = Integer.parseInt(st.nextToken()); 
    
    Map<Integer, List<Edge>> map = new HashMap<>();
    for(int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()); //출발 
      int b = Integer.parseInt(st.nextToken()); //도착 
      int c = Integer.parseInt(st.nextToken()); //거리 
      map.computeIfAbsent(a, key -> new ArrayList<>()).add(new Edge(b, c));
    }
    
    //각 도시별로 최대 k개 최단 거리 후보 저장 - 내림차순 
    Map<Integer, Queue<Integer>> dist = new HashMap<>();
    for(int i=1; i<=n; i++) {
      dist.put(i, new PriorityQueue<>((o1, o2) -> o2 - o1));
    }
    
    //각 도시별 최단 거리 후보 저장 
    dijkstra(map, dist, k);
    
    //k개 후보를 채우지 못했으면 k번쨰 최단 거리가 없음 => -1 / 아니라면 가장 앞 원소 출력 
    for(int i=1; i<=n; i++) {
      System.out.println(dist.get(i).size() < k ? -1 : dist.get(i).peek());
    }
    
  }
  
  static void dijkstra(Map<Integer, List<Edge>> map, Map<Integer, Queue<Integer>> dist, int k) {
    Queue<City> q = new PriorityQueue<>();
    dist.get(1).offer(0); //시작 도시인 1번은 첫번째 최단 거리가 0
    q.offer(new City(1, 0));
    
    while(!q.isEmpty()) {
      City curr = q.poll();
      
      if(!map.containsKey(curr.num)) continue;
      
      for(Edge next : map.get(curr.num)) {
        int newDist = curr.dist + next.dist;
        
        //다음 도시의 최단 거리 후보 개수가 k개 미만이면 추가 
        if(dist.get(next.to).size() < k) {
          dist.get(next.to).offer(newDist);
          q.offer(new City(next.to, newDist));
        
        //k개 채웠지만 저장된 k번째 최단 거리가 새로 계산한 거리보다 길다면 대체 
        } else if(dist.get(next.to).peek() > newDist) {
          dist.get(next.to).poll();
          dist.get(next.to).offer(newDist);
          q.offer(new City(next.to, newDist));
        }
      }
    }
  }

    
}