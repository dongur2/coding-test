import java.util.*;
import java.io.*;

public class Main {
    static Map<Integer, List<Integer>> graph = new HashMap<>();
    static boolean[] visited;
    static final int MAX_LEN = 1001; //노드 번호가 순서대로라는 보장 없으므로 최대 길이로 정의
    
    //무방향 그래프에서 연결 요소 개수 (그래프 개수)
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      //그래프 생성
      for(int i=0; i<m; i++) {
        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        
        //무방향 그래프
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
      }
      
      visited = new boolean[MAX_LEN];
      
      //전체 노드 순서대로 탐색
      int cnt = 0;
      for(int node=1; node<=n; node++) {
        if(visited[node]) continue;
        
        //방문한 적 없는 그래프 탐색 시작 -> 카운트
        cnt++;
        visited[node] = true;
        dfs(node);
      }
      
      System.out.println(cnt);
    }
    
    public static void dfs(int node) {
      //이어진 노드가 없으면 중지
      if(!graph.containsKey(node)) return;
      
      //이어진 노드가 있으면 탐색
      List<Integer> nextNodes = graph.get(node);
      //방문한 적 없는 노드만 방문
      for(int nxt:nextNodes) {
        if(visited[nxt]) continue;
        
        visited[nxt] = true; //체크
        dfs(nxt);
      }
    }
}