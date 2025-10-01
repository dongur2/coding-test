class Solution {
    int cnt = 0;
    boolean[] visited;

    String answer = "";
    public String getPermutation(int n, int k) {
        visited = new boolean[n+1];
        dfs(n, k, "");        
        return answer;
    }

    boolean dfs(int n, int k, String num) {
        //길이 만족
        if(num.length() == n) {
            if(++cnt == k) {
                answer = num; return true;
            }
            return false;
        }

        for(int curr=1; curr<=n; curr++) {
            if(visited[curr]) continue;
            visited[curr] = true;
            if(dfs(n, k, num+curr)) return true;
            visited[curr] = false;
        }

        return false;
    }
}