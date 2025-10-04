import java.util.Map; import java.util.HashMap; import java.util.List; import java.util.ArrayList;

class Solution {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    int answer = 0;
    
    public int solution(int[] info, int[][] edges) {
        //그래프
        for(int[] edge:edges) graph.computeIfAbsent(edge[0], k->new ArrayList<>()).add(edge[1]);
        
        dfs(info, graph.get(0), 1, 0);
        
        return answer;
    }
    
    void dfs(int[] info, List<Integer> q, int s, int w) {
        answer = Math.max(answer, s);
        
        for(int i=0; i<q.size(); i++) {
            int newSheep = s; int newWolf = w;
            
            int next = q.get(i);
            if(info[next] == 1) newWolf++; else newSheep++;
            
            if(newSheep <= newWolf) continue;
            
            List<Integer> newQ = new ArrayList<>(q);
            newQ.remove(i);
            if(graph.containsKey(next)) newQ.addAll(graph.get(next));
            
            dfs(info, newQ, newSheep, newWolf);
        }
    }
    
}