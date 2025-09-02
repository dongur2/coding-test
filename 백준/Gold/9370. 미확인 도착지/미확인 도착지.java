import java.util.*;
import java.io.*;

/*
  s에서 출발 - 목적지 후보 중 하나로 최단 거리 이동
  >> 각 케이스마다 목적지 후보 중 가능한 목적지를 오름차순으로 출력 
*/
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //테스트 케이스 개수 
    int T = Integer.parseInt(br.readLine());
    
    for(int i=0; i<T; i++) {
     
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken()); //교차로 개수 
      int m = Integer.parseInt(st.nextToken()); //도로 개수 
      int t = Integer.parseInt(st.nextToken()); //목적지 후보 개수 
      
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken()); //출발지
      int g = Integer.parseInt(st.nextToken()); //교차로 기준1 
      int h = Integer.parseInt(st.nextToken()); //교차로 기준2
      /*
        g, h를 연결하는 간선은 무조건 지나야 한다.
      */
      
      //무방향 가중치 그래프 생성 
      Map<Integer, List<Edge>> graph = new HashMap<>();
      for(int j=0; j<m; j++) {
        st = new StringTokenizer(br.readLine());
        
        //노드 1과 노드2, 그 사이 무방향 가중치 간선 
        int a = Integer.parseInt(st.nextToken()); //지점1
        int b = Integer.parseInt(st.nextToken()); //지점2
        int d = Integer.parseInt(st.nextToken()); //a와 b 사이 양방향 도로의 길이 
        
        graph.computeIfAbsent(a, k->new ArrayList<>()).add(new Edge(b, d));
        graph.computeIfAbsent(b, k->new ArrayList<>()).add(new Edge(a, d));
      }
      
      /*
        무조건 g,h연결하는 간선을 지나서 갈 수 있는 목적지를 구하는 것이므로
        s->g, g->h, h->목적지 후보 목록 
        이렇게?
        
        -- g->h를 거쳐갔을 때 최단 거리여야함
        안거쳐갔을 때가 최단 거리면 불가능 
      */
      int[] distS = dajikstra(graph, s); //s에서 모든 점까지의 최단 거리
      int[] distG = dajikstra(graph, g); //g에서 모든 점까지의 최단 거리
      int[] distH = dajikstra(graph, h); //h에서 모든 점까지의 최단 거리
      
      int gh = cross(graph, g, h); //g-h 사이 도로의 거리 - 무조건 지나야 하는 도로 
      
      List<Integer> answer = new ArrayList<>();
      
      for(int j=0; j<t; j++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        
        int path1 = distS[g] + gh + distH[x];
        int path2 = distS[h] + gh + distG[x];
        int shortest = distS[x];
    
        //g-h를 거쳤을 때 최단 거리여야 조건 만족 
        if (shortest == path1 || shortest == path2) answer.add(x);
      }
      
      //오름차순 출력 
      Collections.sort(answer);
      answer.forEach(a -> System.out.print(a+" "));
      System.out.println();
    }
  }
  
  static int[] dajikstra(Map<Integer, List<Edge>> graph, int start) {
    int[] dist = new int[1000000];
    Arrays.fill(dist, Integer.MAX_VALUE);
    
    Queue<Edge> pq = new PriorityQueue<>();
    dist[start] = 0;
    pq.offer(new Edge(start, 0));
    
    while(!pq.isEmpty()) {
      Edge curr = pq.poll();
      
      if(graph.get(curr.to) == null) continue;
      
      for(Edge next : graph.get(curr.to)) {
        int newDist = dist[curr.to] + next.dist;
        if(newDist < dist[next.to]) {
          dist[next.to] = newDist;
          pq.offer(new Edge(next.to, dist[next.to]));
        }
      }
    }
    
    return dist;
  }
  
  static int cross(Map<Integer, List<Edge>> graph, int from, int to) {
    for(Edge e : graph.get(from)) {
      if(e.to == to) return e.dist;
    }
    return 0;
  }
  
  static class Edge implements Comparable<Edge> {
    int to, dist;
    public Edge(int to, int dist) {
      this.to=to; this.dist=dist;
    }
    
    @Override
    public int compareTo(Edge e) {
      return this.dist - e.dist;
    }
  }
}