import java.util.Arrays;

class Solution {
    public int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();
        
        int answer = n * m; //전체 컨테이너 개수 
        
        char[][] st = new char[n+2][m+2];
        for(char[] s:st) {
            Arrays.fill(s, '0');
        }

        for(int r=0; r<n; r++) {
            for(int c=0; c<m; c++) {
                st[r+1][c+1] = storage[r].charAt(c);
            }
        }
        
        //각 요청을 순서대로 처리
        for(String request : requests) {
            char container = request.charAt(0);
            
            //단일 문자: 지금 접근 가능한 컨테이너만 꺼냄
            if(request.length() == 1) {
                boolean[][] visited = new boolean[n+2][m+2];
                for(int c=0; c<=m+1; c++) {
                    if(st[0][c] == '0') answer -= dfs(st, n, m, 0, c, container, visited);
                    if(st[n+1][c] == '0') answer -= dfs(st, n, m, n+1, c, container, visited);
                }
                
                for(int r=0; r<=n+1; r++) {
                    if(st[r][0] == '0') answer -= dfs(st, n, m, r, 0, container, visited);
                    if(st[r][m+1] == '0') answer -= dfs(st, n, m, r, m+1, container, visited);
                }
            }
            
            //중복 문자: 모든 컨테이너 꺼냄
            else {
                for(int r=1; r<=n; r++) {
                    for(int c=1; c<=m; c++) {
                        if(st[r][c] == container) {
                            answer--; st[r][c] = '0';
                        }
                    }
                }
            }
        }
        
        
        return answer;
    }
    
    static int[] dRow = new int[] {0, 1, 0, -1};
    static int[] dCol = new int[] {1, 0, -1, 0};
    
    private int dfs(char[][] st, int n, int m, int r, int c, int target, boolean[][] visited) {
        int cnt = 0;
        
        visited[r][c] = true;
        
        for(int i=0; i<4; i++) {
            int nRow = r + dRow[i];
            int nCol = c + dCol[i];
            
            if(nRow >= 1 && nRow <=n && nCol >= 1 && nCol <= m && !visited[nRow][nCol]) {
                if(st[nRow][nCol] == '0') cnt += dfs(st, n, m, nRow, nCol, target, visited); 
                else if(st[nRow][nCol] == target) {
                    cnt++; st[nRow][nCol] = '0'; visited[nRow][nCol] = true;
                }
            }
        }
        
        return cnt;
    }
}