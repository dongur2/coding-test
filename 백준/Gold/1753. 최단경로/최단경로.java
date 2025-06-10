import java.util.*;
import java.io.*;

/*
Q.시작점에서 다른 모든 정점에 대한 최단 경로 구하기
- 서로 다른 정점 사이에 여러 간선 존재 가능 
- 경로가 존재하지 않을 경우 INF
*/
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int v = Integer.parseInt(st.nextToken()); //정점 개수 10^3 - 1번~v번 
    int e = Integer.parseInt(st.nextToken()); //간선 개수 10^3
    
    int start = Integer.parseInt(br.readLine()); //시작점 
    
    //그래프
    Map<Integer, List<Node>> map = new HashMap<>();
    
    //간선 
    for(int i=0; i<e; i++) {
      st = new StringTokenizer(br.readLine());
      
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int dist = Integer.parseInt(st.nextToken());
      
      map.computeIfAbsent(from, k->new ArrayList<>()).add(new Node(to, dist));
    }
    
    //최소 거리 저장 
    int[] dist = new int[v+1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    
    getMinimumDist(map, dist, start);
    
    for(int i=1; i<=v; i++) {
      if(dist[i] == Integer.MAX_VALUE) System.out.println("INF");
      else System.out.println(dist[i]);
    }
  }
  
  private static void getMinimumDist(Map<Integer, List<Node>> map, int[] dist, int start) {
    Queue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));
    dist[start] = 0;
    
    while(!pq.isEmpty()) {
      Node curr = pq.poll();
      int curLoc = curr.to;
      int curDist = curr.dist;
      
      if(map.get(curLoc) == null) continue;
      for(Node nxt:map.get(curLoc)) {
        int nxtLoc = nxt.to;
        int nxtDist = curDist + nxt.dist;
        
        if(dist[nxtLoc] > nxtDist) {
          dist[nxtLoc] = nxtDist;
          pq.offer(new Node(nxtLoc, nxtDist));
        }
      }
    }
  }
  
  private static class Node implements Comparable<Node> {
    int to, dist;
    
    public Node(int t, int d) {
      this.to=t; this.dist=d;
    }
    
    @Override
    public int compareTo(Node n) {
      return this.dist - n.dist;
    }
  }
}