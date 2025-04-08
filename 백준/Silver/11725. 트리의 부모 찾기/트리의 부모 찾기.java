import java.util.*;
import java.io.*;

//각 노드의 부모 번호
public class Main {
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      //노드 개수
      int n = Integer.parseInt(br.readLine());
      
      //노드:[연결노드] 그래프
      Map<Integer, List<Integer>> graph = new HashMap<>();
      
      for(int i=0; i<n-1; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        graph.computeIfAbsent(p, k->new ArrayList<>()).add(c);
        graph.computeIfAbsent(c, k->new ArrayList<>()).add(p);
      }
    
      //dfs로 탐색하면서 부모노드 저장
      int[] arr = new int[n+1];
      boolean[] visited = new boolean[n+1];
      dfs(graph, visited, arr, 1);
      
      //부모노드 출력 
      for(int i=2; i<=n; i++) {
        System.out.println(arr[i]);
      }
    }
    
    public static void dfs(Map<Integer, List<Integer>> g, boolean[] visited, int[] arr, int node) {
      if(!g.containsKey(node)) return;
      
      //자식 노드 
      List<Integer> children = g.get(node);
      for(int c:children) {
        if(visited[c]) continue;
        
        arr[c] = node; //부모노드 저장 
        visited[c] = true; //방문 체크
        dfs(g, visited, arr, c); //이동
      }
    }
}