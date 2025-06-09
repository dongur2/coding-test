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
1~N번 도시, M개 단방향 도로(모든 도로 거리는 1)
-- 특정 도시에서 출발하여 도착할 수 있는 모든 도시 
-- 중애 "최단 거리가 K"인 모든 도시 번호 출력 
*/
public class Main {
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()); //도시 개수
    int m = Integer.parseInt(st.nextToken()); //도로 개수 
    int k = Integer.parseInt(st.nextToken()); //요구하는 최단 거리  
    int x = Integer.parseInt(st.nextToken()); //출발 도시 번호 
    
    //그래프 생성
    Map<Integer, List<City>> map = new HashMap<>();
    for(int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      
      map.computeIfAbsent(from, c->new ArrayList<>()).add(new City(to, 0));
    }
    
    //거리 저장할 배열 (1번 도시~n번 도시)
    int[] dists = new int[n+1];
    Arrays.fill(dists, Integer.MAX_VALUE); //최초값은 최대값(무한)
    
    //지금까지의 거리가 작은 것부터 꺼낼 큐 
    Queue<City> pq = new PriorityQueue<>();
    
    //x번 도시에서 출발 
    pq.offer(new City(x, 0));
    dists[x] = 0;
    
    while(!pq.isEmpty()) {
      //현재 위치한 도시 
      City curr = pq.poll();
      int curNum = curr.num;
      int curDist = curr.dist;
      
      //인접도시가 없으면 중지 
      if(map.get(curNum) == null || map.get(curNum).isEmpty()) continue;
      //인접도시 탐색
      for(City next:map.get(curNum)) {
        int newDist = curDist + 1;
        
        //만약 이미 저장된 거리가 더 작다면 무시 
        if(newDist > dists[next.num]) continue;
        //만약 이미 저장된 거리보다 더 최소로 갈 수 있는 거리라면 저장하고 큐에 추가
        dists[next.num] = newDist;
        pq.offer(new City(next.num, newDist));
      }
    }
    
    //거리가 K인 도시 번호 저장
    List<Integer> ans = new ArrayList<>();
    for(int idx=1; idx<=n; idx++) {
      if(dists[idx] == k) ans.add(idx);
    }
    
    //없으면 -1, 있으면 출력 
    if(ans.isEmpty()) System.out.println("-1");
    else {
      ans.forEach(a->System.out.println(a));
    }
  }
  
  private static class City implements Comparable<City> {
    int num; int dist;
    
    public City(int num, int dist) {
      this.num=num; this.dist=dist;
    }
    
    @Override
    public int compareTo(City c) {
      return this.dist - c.dist;
    }
  }
}