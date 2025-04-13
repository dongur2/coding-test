import java.io.*;
import java.util.*;

//동생 찾는 가장 빠른 시간
public class Main {
  static int[] visited = new int[100001];
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    
    Arrays.fill(visited, -1);
    int answer = bfs(n, k);
    System.out.println(answer);
  }
  
  public static int bfs(int n, int k) {
    //시간이 더 빠른 순서를 우선
    Deque<int[]> q = new ArrayDeque<>();
    q.offer(new int[]{n, 0});
    visited[n] = 0;
    
    while(!q.isEmpty()) {
      int[] curr = q.poll();
      int loc = curr[0];
      int sec = curr[1];
      
      //순간이동 0초 - 비용이 작으므로 먼저 추가
      int nxtLoc = loc*2;
      if(isValid(nxtLoc) && visited[nxtLoc] == -1) {
        visited[nxtLoc] = sec;
        q.offerFirst(new int[]{nxtLoc, sec});
      }
      
      for(int nxt:new int[]{loc-1, loc+1}) {
        if(isValid(nxt)&& visited[nxt] == -1) {
          visited[nxt] = sec+1;
          q.offerLast(new int[]{nxt, sec+1});
        }
      }
    }
    
    //최소시간
    return visited[k];
  }
  
  public static boolean isValid(int l) {
    return l >= 0 && l < 100001;
  }
}
