import java.util.*;
import java.io.*;

/*
Q. 1번에서 출발하여 >주어진 두 정점을 반드시 통과하면서< N번에 도착할 수 있는 최단 거리
- 무방향 그래프
- 중복 방문 가능 
*/
public class Main {
  static int n, e;
  static int p1, p2;
  static Map<Integer, List<Node>> map = new HashMap<>();
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken()); //정점 개수 2~800
    e = Integer.parseInt(st.nextToken()); //간선 개수 0~200,000
    
    //무방향 그래프 
    // Map<Integer, List<Node>> map = new HashMap<>();
    
    //간선 
    for(int i=0; i<e; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int dist = Integer.parseInt(st.nextToken()); //1~1000
      
      map.computeIfAbsent(from, k->new ArrayList<>()).add(new Node(to, dist));
      map.computeIfAbsent(to, k->new ArrayList<>()).add(new Node(from, dist));
    }
    
    //무조건 지나야 하는 두 정점 
    st = new StringTokenizer(br.readLine());
    p1 = Integer.parseInt(st.nextToken());
    p2 = Integer.parseInt(st.nextToken());
    
    //구해야하는 거리 (구간 3개)
    long dist1ToP1 = dijkstra(1, p1);
    long distP2ToN = dijkstra(p2, n);
    
    long distP1ToP2 = dijkstra(p1, p2);
    
    long dist1ToP2 = dijkstra(1, p2);
    long distP1ToN = dijkstra(p1, n);
    
    long dist1, dist2;
    
    //p1<->p2 경로 없을 경우 무조건 불가능 
    if(distP1ToP2 == -1) {
      System.out.println("-1"); return;
    }
    
    //1->p1 -> p2->n
    if(dist1ToP1 == -1 || distP2ToN == -1) dist1 = -1;
    else dist1 = dist1ToP1 + distP1ToP2 + distP2ToN;
    
    //1->p2 -> p1->n
    if(dist1ToP2 == -1 || distP1ToN == -1) dist2 = -1;
    else dist2 = dist1ToP2 + distP1ToP2 + distP1ToN;
    
    System.out.println(Math.min(dist1, dist2));
  }
  
  private static long dijkstra(int start, int goal) {
    long[] dists = new long[n+1];
    Arrays.fill(dists, Long.MAX_VALUE);
    
    Queue<Node> q = new PriorityQueue<>();
    q.offer(new Node(start, 0));
    dists[start] = 0;
    
    while(!q.isEmpty()) {
      Node curr = q.poll();
      int curLoc = curr.to;
      long curDist = curr.dist;
      
      //도착 
      if(curLoc == goal) return curDist;
      
      if(map.get(curLoc) == null) continue;
      for(Node nxt:map.get(curLoc)) {
        int nxtLoc = nxt.to;
        long nxtDist = curDist + nxt.dist;
        
        if(dists[nxtLoc] > nxtDist) {
          dists[nxtLoc] = nxtDist;
          q.offer(new Node(nxtLoc, nxtDist));
        }
      }
    }
    
    return -1;
  }
  
  private static class Node implements Comparable<Node> {
    int to; long dist;
    
    public Node(int t, long d) {
      this.to=t; this.dist=d;
    }
    
    @Override
    public int compareTo(Node o) {
      return (int)(this.dist - o.dist);
    }
  }
}