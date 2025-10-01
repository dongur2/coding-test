class Solution {
    int answer = 0;
    boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {    
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return answer;
    }
    
    void dfs(int k, int[][] dungeons, int cnt) {
        for(int curr=0; curr<dungeons.length; curr++) {
            if(visited[curr]) continue;
            if(dungeons[curr][0] > k) continue;
            
            visited[curr] = true;
            dfs(k-dungeons[curr][1], dungeons, cnt+1);
            visited[curr] = false;
        }
        
        answer= Math.max(answer, cnt);
    }
}