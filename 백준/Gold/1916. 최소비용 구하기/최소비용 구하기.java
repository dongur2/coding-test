import java.util.StringTokenizer;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

/*
---- 출발 도시에서 도착 도시까지 가는 데 필요한 최소 비용
*/
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st;
    
    int n = Integer.parseInt(br.readLine()); //도시 개수 (10^3)
    int m = Integer.parseInt(br.readLine()); //버스 개수 (10^5)
    
    Map<Integer, List<Bus>> map = new HashMap<>();
    
    for(int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken()); //0<=cost<10^5
      
      map.computeIfAbsent(from, k->new ArrayList<>()).add(new Bus(to, cost));
    }
    
    st = new StringTokenizer(br.readLine());
    int startCity = Integer.parseInt(st.nextToken()); //출발 도시
    int endCity = Integer.parseInt(st.nextToken()); //도착 도시
    
    int[] costs = new int[n+1]; //최소 비용 저장 배열 
    Arrays.fill(costs, Integer.MAX_VALUE);
    
    Queue<Bus> pq = new PriorityQueue<>();
    pq.offer(new Bus(startCity, 0));
    costs[startCity] = 0;
    
    while(!pq.isEmpty()) {
      Bus curr = pq.poll();
      int nowLoc = curr.to;
      int nowCost = curr.cost;
      
      if(nowLoc == endCity) break;
      
      if(map.get(nowLoc) != null) {
       for(Bus nxt:map.get(nowLoc)) {
          int nxtLoc = nxt.to;
          int nxtCost = nowCost + nxt.cost;
        
          if(costs[nxtLoc] > nxtCost) {
            costs[nxtLoc] = nxtCost;
            pq.offer(new Bus(nxtLoc, nxtCost));
          }
        } 
      }
    }
    
    System.out.println(costs[endCity]);
  }
  
  private static class Bus implements Comparable<Bus> {
    int to, cost;
    
    public Bus(int t, int c) {
      this.to=t; this.cost=c;
    }
    
    @Override
    public int compareTo(Bus b) {
      return this.cost - b.cost;
    }
  }
}