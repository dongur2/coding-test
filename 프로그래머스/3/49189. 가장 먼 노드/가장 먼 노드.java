import java.util.*;

class Solution {
    /*
    ** 1번 노드에서 가장 먼 노드의 개수 반환
    - N개 노드 (1~N번)
    */
    
    int maximum = 0; //가장 먼 간선 개수 저장
    
    Map<Integer, List<Integer>> graph; //그래프 {노드 번호 : 연결된 노드 목록}
    Map<Integer, Integer> nodes; //{노드 번호 : 노드 1로부터 연결된 간선 개수}
    
    public int solution(int n, int[][] edge) {
        graph = new HashMap<>();
        nodes = new HashMap<>();
        
        return countFar(n, edge);
    }
    
    int countFar(int n, int[][] edge) {
        //1. 그래프화
        makeGraph(edge);
        
        //2. bfs
        findFarNode(n, edge);
        
        //3. count: 가장 많은 간선 개수를 가진 값을 카운트해서 반환
        int answer = 0;
        for(int node:nodes.values()) {
            if(node == maximum) answer++;
        }
        return answer;
        
        // return (int)(nodes.values().stream().filter(l->l==maximum).count());
    }
    
    // {노드:연결노드목록} 그래프로 변환
    void makeGraph(int[][] edge) {
        for(int i = 0; i < edge.length; i++) {
            addConnection(edge[i][0], edge[i][1]);
            addConnection(edge[i][1], edge[i][0]);
        }
    }
    
    //노드 간 연결관계 추가
    void addConnection(int baseNode, int addedNode) {
        if (!graph.containsKey(baseNode)) graph.put(baseNode, new ArrayList<>());
        graph.get(baseNode).add(addedNode);
    }
    
    //전체 그래프 순회하며 간선 개수 카운트
    void findFarNode(int n, int[][] edge) {
        boolean[] visited = new boolean[n+1];
        
        //start
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1); 
        visited[1] = true;
        nodes.put(1, 0); 
        
        while(!q.isEmpty()) {
            //cur
            int cur = q.poll();
            
            //near
            for(int nxt : graph.get(cur)) {
                if(visited[nxt]) continue;
                
                q.offer(nxt); 
                visited[nxt] = true;
                nodes.put(nxt, nodes.get(cur)+1); 
                
                maximum = Math.max(maximum, nodes.get(cur)+1); //가장 먼 간선 개수 업데이트
            }
        }
    }
}