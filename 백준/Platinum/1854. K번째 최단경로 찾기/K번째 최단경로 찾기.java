import java.util.*;
import java.io.*;

/*
  >>> k번째 최단경로 
*/
public class Main {
  static class Edge {
    int to, dist;
    public Edge(int to, int dist) {
      this.to=to; this.dist=dist;
    }
  }
  
  static class Node implements Comparable<Node>{
    int num, dist;
    public Node(int num, int dist) {
      this.num=num; this.dist=dist;
    }
    @Override
    public int compareTo(Node node) {
      return this.dist - node.dist;
    }
  }
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()); //도시 
    int m = Integer.parseInt(st.nextToken()); //도로 
    int k = Integer.parseInt(st.nextToken()); //최단경로 순번 
    
    Map<Integer, List<Edge>> map = new HashMap<>();
    
    //a -> b 도로 길이 
    for(int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()); 
      int b = Integer.parseInt(st.nextToken());  
      int c = Integer.parseInt(st.nextToken()); 
      
      map.computeIfAbsent(a, key -> new ArrayList<>()).add(new Edge(b, c));
    }
    
    //1번에서 시작, 1->n번까지 연결되어있음 
    Map<Integer, PriorityQueue<Integer>> kthShorts = dijkstra(map, n, k);
    
    //i번째 줄: 1 -> i번 도시로 가는 k번째 최단경로(존재하지 않으면 -1)
    for(int node=1; node<=n; node++) {
      System.out.println(kthShorts.get(node) == null || kthShorts.get(node).size() < k ? -1 : kthShorts.get(node).peek());
    }
  }
  
  static Map<Integer, PriorityQueue<Integer>> dijkstra(Map<Integer, List<Edge>> map, int n, int k) {
    Map<Integer, PriorityQueue<Integer>> q = new HashMap<>();
    for(int i=1; i<=n; i++) {
      q.put(i, new PriorityQueue<>((o1, o2) -> o2-o1)); //내림차순 -> k번째 최단 경로를 구해야 하므로 긴게 앞으로
    }
    
    Queue<Node> pq = new PriorityQueue<>();
    q.get(1).offer(0); //1번 도시에서 시작 
    pq.offer(new Node(1, 0));
    
    while(!pq.isEmpty()) {
      Node curr = pq.poll();
      
      if(!map.containsKey(curr.num)) continue;
      
      for(Edge next : map.get(curr.num)) {
        int newDist = curr.dist + next.dist; //새로 계산한 최단 거리 
        
        //k개보다 적을 때만 큐에 추가 -> k번째 최단 경로를 바로 꺼낼 수 있도록
        PriorityQueue<Integer> distQ = q.get(next.to);
        if(distQ.size() < k) {
          distQ.offer(newDist);
          pq.offer(new Node(next.to, newDist));
        
        //저장했던 k번째 최단 거리보다 짧은 거리가 있으면 큐에서 꺼내고 다시 저장 
        } else if (distQ.peek() > newDist) {
          distQ.poll();
          distQ.offer(newDist);
          pq.offer(new Node(next.to, newDist));
        }
      }
    }
    
    return q;
  }
}