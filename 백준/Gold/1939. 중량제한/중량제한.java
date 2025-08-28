import java.util.*;
import java.io.*;

/*
  N개 섬 (2~10^4) - 다리로 연결 (양방향, 같은 두 섬 사이 여러 다리 가능)
  섬 2개에 공장 
  공장 -> 공장 수송 -- 다리 중량 제한
  >>> 한 번 이동할 때 옮길 수 있는 중량 최댓값 
*/
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken()); //섬
    int M = Integer.parseInt(st.nextToken()); //다리 
    
    //시작 섬:{연결 다리}
    Map<Integer, List<Bridge>> map = new HashMap<>();
    
    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int node1 = Integer.parseInt(st.nextToken()); //섬1
      int node2 = Integer.parseInt(st.nextToken()); //섬2
      int weight = Integer.parseInt(st.nextToken()); //중량 제한
      
      //양방향
      map.computeIfAbsent(node1, k -> new ArrayList<>()).add(new Bridge(node2, weight));
      map.computeIfAbsent(node2, k -> new ArrayList<>()).add(new Bridge(node1, weight));
    }
    
    st = new StringTokenizer(br.readLine());
    int fac1 = Integer.parseInt(st.nextToken()); //공장1 
    int fac2 = Integer.parseInt(st.nextToken()); //공장2
    
    //공장1 -> 공장2로 가는 경로에서 최소 무게 제한 
    System.out.println(dj(N, map, fac1, fac2));
  }
  
  static int dj(int N, Map<Integer, List<Bridge>> map, int fac1, int fac2) {
    int[] dp = new int[N+1]; //0으로 초기화
    dp[fac1] = 1000000000; //출발지
    
    boolean[] visited = new boolean[N+1];
    
    PriorityQueue<Bridge> pq = new PriorityQueue<>();  //더 큰 무게 우선 큐
    pq.offer(new Bridge(fac1, dp[fac1]));
    
    while(!pq.isEmpty()) {
      Bridge curr = pq.poll();
      
      if(visited[curr.to]) continue;
      
      visited[curr.to] = true;
      if(curr.to == fac2) return dp[fac2]; 
      
      if(map.get(curr.to) == null) continue;
      
      for(Bridge next : map.get(curr.to)) {
        //새로운 무게 제한: 현재 섬의 무게 제한과 현재 연결 다리의 무게 제한 중 더 작은 값 
        int newLimit = Math.min(curr.weight, next.weight);
        
        //새로운 무게 제한이 저장된 값(섬의 무게 제한)보다 클 경우에만 업데이트하고 방문 
        if(dp[next.to] < newLimit) {
          dp[next.to] = newLimit; 
          pq.offer(new Bridge(next.to, newLimit));
        }
      }
    }
    
    return dp[fac2];
  }
  
  static class Bridge implements Comparable<Bridge> {
    int to, weight;
    public Bridge (int to, int weight) {
      this.to=to; this.weight=weight;
    }

    //더 큰 무게 제한이 우선 
    @Override
    public int compareTo(Bridge b) {
      return b.weight - this.weight;
    }
  }
}