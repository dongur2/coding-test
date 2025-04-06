import java.util.*;
import java.io.*;

public class Main {
    static boolean[] visited;
    
    //dfs 탐색 결과, bfs 탐색 결과
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int root = Integer.parseInt(st.nextToken());
      
      visited = new boolean[n+1];
      
      //그래프 생성: 양방향 그래프
      Map<Integer, List<Integer>> graph = new HashMap<>();
      for(int i=0; i<m; i++) {
        st = new StringTokenizer(br.readLine());
        
        int node = Integer.parseInt(st.nextToken());
        int next = Integer.parseInt(st.nextToken());
        graph.computeIfAbsent(node, k -> new ArrayList<>()).add(next);
        graph.computeIfAbsent(next, k -> new ArrayList<>()).add(node);
      }
      
      //방문 가능 접점이 여러 개일 경우 작은 번호부터 방문
      for(int key:graph.keySet()) {
        List<Integer> list = graph.get(key);
        list.sort((l1, l2) -> l1 - l2);
      }
      
      visited[root] = true;
      dfs(graph, root);
      
      Arrays.fill(visited, false);
      System.out.println();
      
      bfs(graph, root);
  }
  
  public static void dfs(Map<Integer, List<Integer>> g, int node) {
    System.out.print(node+" ");
    if(!g.containsKey(node)) return;
    
    List<Integer> nextNodes = g.get(node);
    for(int i=0; i<nextNodes.size(); i++) {
      if(visited[nextNodes.get(i)]) continue;
      
      visited[nextNodes.get(i)] = true;
      dfs(g, nextNodes.get(i));
    }
  }
  
  public static void bfs(Map<Integer, List<Integer>> g, int root) {
    Deque<Integer> q = new ArrayDeque<>();
    q.offer(root); visited[root] = true;
    
    while(!q.isEmpty()) {
      int curr = q.poll();
      System.out.print(curr+" ");
      
      if(g.containsKey(curr)) {
        List<Integer> nextNodes = g.get(curr);
        
        for(int next:nextNodes) {
          if(visited[next]) continue;
          q.offer(next); visited[next] = true;
        }
      }
    }
  }
}