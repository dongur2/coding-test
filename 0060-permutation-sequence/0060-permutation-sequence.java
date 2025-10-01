class Solution {
    int cnt = 0;
    boolean[] visited;

    public String getPermutation(int n, int k) {
        visited = new boolean[n+1];
        char[] answer = new char[n];
        dfs(n, k, 0, answer);        
        return new String(answer);
    }

    boolean dfs(int n, int k, int idx, char[] num) {
        //길이 만족
        if(idx == n) {
            if(++cnt == k) return true;
            return false;
        }

        for(int curr=1; curr<=n; curr++) {
            if(visited[curr]) continue;
            visited[curr] = true; num[idx] = (char)(curr+'0');
            if(dfs(n, k, idx+1, num)) return true;
            visited[curr] = false; num[idx] = '0';
        }

        return false;
    }
}