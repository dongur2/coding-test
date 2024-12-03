import java.util.*;

class Solution {
    /*
    Q. 네트워크의 개수 리턴
    */
    
    int cnt = 0;
    Map<Integer, List<Integer>> network;
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        network = new HashMap<>();
        visited = new boolean[n];
        
        makeNetwork(n, computers);
        
        for(int i=0; i<n; i++) {
            if(visited[i]) continue;
            
            findNetworks(i);
            cnt++;
        }
        
        return cnt;
    }
    
    void makeNetwork(int n, int[][] computers) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i==j) continue;
                if(computers[i][j] == 1) {
                    List<Integer> list = network.getOrDefault(i, new ArrayList<>());
                    list.add(j);
                    network.put(i, new ArrayList<>(list));
                };
            }
        }
    }
    
    void findNetworks(int computer) {
        visited[computer] = true;
        
        if(network.get(computer) != null) {
            for(int nxt : network.get(computer)) {
                if(visited[nxt]) continue;
                findNetworks(nxt);
            }
        }
    }
    
}