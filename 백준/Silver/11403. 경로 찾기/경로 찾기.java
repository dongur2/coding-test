import java.util.*;
import java.io.*;

//노드 i에서 j로 가는 간선 유무 확인
public class Main {
  static Map<Integer, Set<Integer>> graph = new HashMap<>();
  static int[][] map;
  static boolean[] visited;
  
  public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      //정점 개수
      int n = Integer.parseInt(br.readLine());
      
      //[i][j] i에서 j로 가는 간선이 존재
      //부모노드:{연결노드} 
      for(int r=0; r<n; r++) {
        StringTokenizer st = new StringTokenizer(br.readLine());  
        
        for(int c=0; c<n; c++) {
          int p = Integer.parseInt(st.nextToken());
          
          //방향 그래프  
          if(p == 1) graph.computeIfAbsent(r, k->new HashSet<>()).add(c);
        }
      }
      
      map = new int[n][n];

      //i에서 j까지 도달하는 경로를 확인해야하므로 시작점을 고정하고 dfs
      for(int node=0; node<n; node++){
        visited = new boolean[n];
        dfs(node, node);
      }
      
      for(int r=0; r<n; r++){
        for(int c=0; c<n; c++) {
          System.out.print(map[r][c]+" ");
        }
        System.out.println();
      }
  }
  
  public static void dfs(int start, int current) {
    if(graph.containsKey(current)) {
      for(int nxt : graph.get(current)) {
        if(visited[nxt]) continue;
        
        visited[nxt] = true;
        map[start][nxt] = 1; //시작점과 다음 노드는 연결되므로 1 바인딩
        dfs(start, nxt);
      }
    }
  }
  
}