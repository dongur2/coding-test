import java.util.*;
import java.io.*;

//두 사람의 촌수 계산 (없을 경우 -1)
public class Main {
  /*
  부모 자식간 관계
  [부모번호 자식번호] - 부모는 최대 1명
  
  그러니까 서로 사이에 있는 간선의 수를 구하면 촌수
  */
  static Map<Integer, List<Integer>> graph = new HashMap<>();
  static boolean[] visited;
  
  static int answer = -1, p1 = -1, p2 = -1;
  
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int n = Integer.parseInt(br.readLine()); //전체 사람 수 (노드 개수)
    
    //촌수를 계산할 두 명 
    StringTokenizer st = new StringTokenizer(br.readLine());
    p1 = Integer.parseInt(st.nextToken());
    p2 = Integer.parseInt(st.nextToken());
    
    //부모노드:{자식노드}
    int m = Integer.parseInt(br.readLine());
    visited = new boolean[101];
    
    for(int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      
      graph.computeIfAbsent(x, k->new ArrayList<>()).add(y);
      graph.computeIfAbsent(y, k->new ArrayList<>()).add(x);
    }
    
    //p1으로부터 p2로 이동하면서 간선 카운트 
    visited[0] = true;
    dfs(p1, 0); //0촌(자기자신)
    
    System.out.println(answer);
  }
  
  public static void dfs(int node, int cnt) {
    //목적지(p2)에 도착했으면 값 바인딩 후 리턴
    if(node == p2) {
      answer = cnt;
      return;
    }
    
    //더 이상 자식노드가 없으면 리턴 
    if(!graph.containsKey(node)) return;
   
    List<Integer> nextNodes = graph.get(node);
    for(int nxt:nextNodes) {
      if(visited[nxt]) continue;
      
      visited[nxt] = true;
      dfs(nxt, cnt+1);
    }
  }
}