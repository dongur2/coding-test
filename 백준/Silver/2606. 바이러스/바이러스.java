import java.util.*;
import java.io.*;

public class Main {
    static Map<Integer, List<Integer>> map = new HashMap<>();
    static boolean[] visited;
    
    static int cnt = 0;
    
    //바이러스에 걸린 컴퓨터 수
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(br.readLine());//컴퓨터 수 1번~n번 
      int m = Integer.parseInt(br.readLine());//간선 수
      
      for(int i=0; i<m; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int node = Integer.parseInt(st.nextToken());
        int next = Integer.parseInt(st.nextToken());
        
        map.computeIfAbsent(node, k -> new ArrayList<>()).add(next);
        map.computeIfAbsent(next, k -> new ArrayList<>()).add(node);
      }
      
      visited = new boolean[n+1];
      
      visited[1] = true;
      dfs(n, m, 1);
      
      System.out.println(cnt-1); //1번 컴퓨터 제외
    }
    
    public static void dfs(int n, int m, int node) {
      cnt++;
      
      //자식 노드가 없으면 중지
      if(!map.containsKey(node)) return;
      
      List<Integer> nextNodes = map.get(node);
      for(int i=0; i<nextNodes.size(); i++) {
        if(visited[nextNodes.get(i)]) continue;
        
        visited[nextNodes.get(i)] = true;
        dfs(n, m, nextNodes.get(i));
      }
    }
}