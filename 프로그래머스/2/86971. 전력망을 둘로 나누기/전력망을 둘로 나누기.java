import java.util.*;

class Solution {
    int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        //배열의 연결 관계를 처음부터 끝까지 차례대로 하나씩 끊어보기 위한 순회
        for(int i=0; i<wires.length; i++) {
            countDividedGraph(wires, n, i);
        }
        
        return answer;
    }
    
    private void countDividedGraph(int[][] wires, int n, int unconnect) {
        //그래프화:해시테이블 {노드번호: {연결노드번호목록}}
        Map<Integer, List<Integer>> graph = new HashMap<>();
        makeGraph(wires, graph, unconnect);
        
        //만든 그래프를 순회
        Queue<Integer> waiting = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        
        waiting.offer(1); //첫번째 노드
        visited[0] = true;
        
        while(!waiting.isEmpty()) {
            int cur = waiting.poll();
            if(graph.get(cur) == null) break;
            
            for(int connection : graph.get(cur)) {
                if(visited[connection-1]) continue;
                
                visited[connection-1] = true;
                waiting.offer(connection);
            }
        }
        
        countNodes(visited);
    }
    
    private void makeGraph(int[][] wires, Map<Integer, List<Integer>> graph, int unconnect) {
        for(int i=0; i<wires.length; i++) {
            //현재 끊으려고 하는 연결관계라면 추가하지 않음
            if(i == unconnect) continue;
            
            int baseNode = wires[i][0];
            int connectedNode = wires[i][1];
            
            //연결된 노드관계를 추가
            addConnection(graph, baseNode, connectedNode); //현재 노드에 추가
            addConnection(graph, connectedNode, baseNode); //연결된 상대 노드에도 현재 노드를 추가
        }
    }
    
    private void addConnection(Map<Integer, List<Integer>> graph, int from, int to) {
        List<Integer> connections = graph.getOrDefault(from, new ArrayList<>());
        connections.add(to);
        graph.put(from, connections);
    }
    
    private void countNodes(boolean[] visited) {
        int graph1 = 0, graph2 = 0;
        
        //방문한 노드 개수 카운트
        for(boolean v:visited) {
            if(v) graph1++; else graph2++;
        }
        
        //노드 개수 차이 최소값 저장
        answer = Math.min(answer, Math.abs(graph1 - graph2));
    }
}