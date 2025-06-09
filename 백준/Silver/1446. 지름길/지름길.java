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
---- 운전해야 하는 "최소 거리"
단방향 지름길 
고속도로 역주행 불가
*/
public class Main {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()); //지름길 개수 
    int d = Integer.parseInt(st.nextToken()); //고속도로 길이
    
    Map<Integer, List<Entry>> map = new HashMap<>();
    
    //지름길 (시작, 도착, 길이)
    for(int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      
      int start = Integer.parseInt(st.nextToken());
      int finish = Integer.parseInt(st.nextToken());
      int len = Integer.parseInt(st.nextToken());
      
      //도착 위치가 고속도로 길이를 초과하면 무시(역주행 불가)
      if(finish > d) continue;
  
      map.computeIfAbsent(start, k->new ArrayList<>()).add(new Entry(finish, len));
    }
    
    //해당 위치까지의 최소 거리 저장 리스트 
    int[] dists = new int[d+1];
    Arrays.fill(dists, Integer.MAX_VALUE);
    
    //작은 거리 우선 큐 
    Queue<Entry> pq = new PriorityQueue<>();
    pq.offer(new Entry(0, 0)); //0에서 시작
    dists[0] = 0;
    
    while(!pq.isEmpty()) {
      //현재 위치 
      Entry curr = pq.poll();
      int nowLoc = curr.loc;
      int nowDist = curr.dist;
      
      //고속도로 진행(한 칸씩 이동)
      int nxtLoc = nowLoc+1;
      int nxtDist = nowDist+1;
      if(nxtLoc <= d && dists[nxtLoc] > nxtDist) {
        pq.offer(new Entry(nxtLoc, nxtDist));
        dists[nxtLoc] = nxtDist;
      }
      
      //이어진 지름길이 있을 경우 
      if(map.get(nowLoc) != null) {
        for(Entry shortCut:map.get(nowLoc)) {
          nxtLoc = shortCut.loc;
          nxtDist = nowDist + shortCut.dist;
          
          //저장된 거리보다 짧은 거리라면 저장하고 추가 
          if(dists[nxtLoc] > nxtDist) {
            dists[nxtLoc] = nxtDist;
            pq.offer(new Entry(nxtLoc, nxtDist));
          }
        }
      }
    }
    
    System.out.println(dists[d]);
  } 
  
  private static class Entry implements Comparable<Entry> {
    int loc, dist;
    
    public Entry(int t, int d) {
      this.loc=t; this.dist=d;
    }
    
    @Override
    public int compareTo(Entry s) {
      return this.dist - s.dist;
    }
  }
}