import java.util.*;
import java.io.*;

/*
Q. A도시에서 B도시로 가는 데 드는 '최소 비용'과 '경로' 출력 
- 경로가 없는 경우는 없음 
*/
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int n = Integer.parseInt(br.readLine()); //도시 개수 10^3
    int m = Integer.parseInt(br.readLine()); //버스 개수 10^5
    
    //그래프 - 출발도시:{버스노선}
    Map<Integer, List<Bus>> map = new HashMap<>();
    for(int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());  
      
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken()); //0<=cost<10^5
      
      map.computeIfAbsent(from, k->new ArrayList<>()).add(new Bus(to, cost));
    }
    
    st = new StringTokenizer(br.readLine());  
    int start = Integer.parseInt(st.nextToken());
    int finish = Integer.parseInt(st.nextToken());
    
    //최소 비용 
    long[] costs = new long[n+1];
    Arrays.fill(costs, Long.MAX_VALUE);
    
    Queue<Bus> q = new PriorityQueue<>();
    q.offer(new Bus(start, 0, 1, new ArrayList<>(List.of(start))));
    costs[start] = 0;
    
    while(!q.isEmpty()) {
      Bus curr = q.poll();
      int curLoc = curr.to;
      int curCost = curr.cost;
      int curCnt = curr.cnt;
      List<Integer> curLine = curr.line;
      
      if(curLoc == finish) {
        System.out.println(curCost + "\n" + curCnt + "\n" + curr.toString());
        break;
      }
      
      if(map.get(curLoc) == null) continue;
      for(Bus nxt:map.get(curLoc)) {
        int nxtLoc = nxt.to;
        int nxtCost = curCost + nxt.cost;
        
        if(costs[nxtLoc] > nxtCost) {
          costs[nxtLoc] = nxtCost;
          
          List<Integer> newLine = new ArrayList<>(curLine);
          newLine.add(nxtLoc);
          
          q.offer(new Bus(nxtLoc, nxtCost, curCnt+1, newLine));
        }
      }
    }
  }
  
  private static class Bus implements Comparable<Bus> {
    int to, cost, cnt;
    List<Integer> line;
    
    public Bus(int t, int c) {
      this.to=t; this.cost=c;
    }
    
    public Bus(int t, int cost, int cnt) {
      this.to=t; this.cost=cost; this.cnt=cnt;
    }
    
    public Bus(int t, int cost, int cnt, List<Integer> line) {
      this.to=t; this.cost=cost; this.cnt=cnt; this.line=line;
    }
    
    @Override
    public int compareTo(Bus b) {
      return this.cost - b.cost;
    }
    
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      
      for(Integer l:this.line) {
        sb.append(l+" ");
      }
      
      return sb.toString();
    }
  }
}