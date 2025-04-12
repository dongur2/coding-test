import java.util.*;
import java.io.*;

//수빈이가 동생을 찾을 수 있는 "최소 시간"과 "최소 시간이 걸리는 방법 수"
public class Main {
  static int[] times;
  
  static int minTime = Integer.MAX_VALUE;
  static int ways = 0;
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    times = new int[100001];
    int s = Integer.parseInt(st.nextToken()); //수빈
    int y = Integer.parseInt(st.nextToken()); //동생
    
    bfs(s, y);
    
    System.out.println(minTime);
    System.out.println(ways);
  }
  
  public static void bfs(int s, int y) {
    Deque<int[]> q = new ArrayDeque<>();
    q.offer(new int[]{s, 0}); //위치, 초 
    times[s] = 1; //0은 미방문 처리 위해 0초 => 1초
    
    while(!q.isEmpty()) {
      int[] curr = q.poll();
      int loc = curr[0];
      int sec = curr[1];
      
      //동생을 찾으면
      if(loc == y) {
        //최초일 경우 저장 
        if(minTime == Integer.MAX_VALUE) minTime = sec;
        //최소 시간이면 방법 카운트
        if(minTime == sec) ways++;
        continue; //이후 무시
      }
      
      //좌우 한칸, 2배 앞 
      for(int nxt : new int[]{loc-1, loc+1, loc*2}) {
        //다음 노드를 방문한 적 없거나, 이전에 동일한 시간이 걸려 도달한 적 있다면
        //다음 차례에 방문하도록 큐에 추가
        if(isValid(nxt, sec)) {
          q.offer(new int[]{nxt, sec+1});
          times[nxt] = sec+1;
        }
      }
    }
  }
  
  public static boolean isValid(int n, int sec) {
    return n >= 0 && n < 100001 && (times[n] == 0 || times[n] == sec+1);
  }
}