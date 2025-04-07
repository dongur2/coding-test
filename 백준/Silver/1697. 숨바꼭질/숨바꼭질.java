import java.util.*;
import java.io.*;

public class Main {
    static boolean[] visited;
    static final int MAX_LEN = 100001;
    
    //동생이 있는 위치로 갈 수 있는 최소 시간초 -> 최소:bfs?
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken()); //수빈
      int k = Integer.parseInt(st.nextToken()); //동생
      
      int len = Math.max(n, k);
      visited = new boolean[MAX_LEN]; //수빈의 위치가 동생보다 멀 수 있음, 순간이동 (2x)로 범위 여유 필요
  
      int answer = bfs(len, n, k);
      System.out.println(answer);
    }
    
    public static int bfs(int len, int n, int k) {
      Deque<int[]> q = new ArrayDeque<>();
      q.offer(new int[]{n, 0});
      visited[n] = true;
      
      while(!q.isEmpty()) {
        int[] curr = q.poll();
        int location = curr[0];
        int sec = curr[1];
        
        if(location == k) return sec;
        
        //앞뒤로 한칸씩 이동
        for(int i:new int[] {1, -1}) {
          int nxtLocation = location + i;
          
          if(isValid(nxtLocation)) {
            q.offer(new int[]{nxtLocation, sec+1});
            visited[nxtLocation] = true;
          }
        }
        
        //앞으로 두칸 이동(순간이동)
        int nxtLocation = 2 * location;
        if(isValid(nxtLocation)) {
          q.offer(new int[]{nxtLocation, sec+1});
          visited[nxtLocation] = true;
        }
      }
      
      return -1;
    }
    
    //범위 내 & 방문 전 
    public static boolean isValid(int l) {
      return l >= 0 && l < MAX_LEN && !visited[l];
    }
}