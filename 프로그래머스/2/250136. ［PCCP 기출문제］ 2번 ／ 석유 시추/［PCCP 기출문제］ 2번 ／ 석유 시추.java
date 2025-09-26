import java.util.Arrays; import java.util.Map; import java.util.HashMap; import java.util.Set; import java.util.HashSet;
import java.util.Queue; import java.util.ArrayDeque;
//시추관을 수직으로 하나만 뚫을 수 있을 때, 뽑을 수 있는 가장 많은 석유량
class Solution {
    int n, m;
    
    int[] dRow = new int[]{0, 1, 0, -1};
    int[] dCol = new int[]{1, 0, -1, 0};

    int[][] group;
    
    public int solution(int[][] land) {
        int answer = 0;
        
        n = land.length; //세로
        m = land[0].length; //가로
        
        //석유 덩어리 별 그룹 부여 
        group = new int[n][m];
        
        int g = 1;
        Map<Integer, Integer> map = new HashMap<>(); //그룹별 석유량
        
        //석유 덩어리별 그룹 부여 + 개수 저장 
        for(int r=0; r<n; r++) {
            for(int c=0; c<m; c++) {
                if(land[r][c] == 1 && group[r][c] == 0) {
                    // int cnt = dfs(land, group, r, c, g);
                    int cnt = bfs(land, group, r, c, g);
                    map.put(g++, cnt);
                }
            }
        }
        
        //석유 시추 
        for(int c=0; c<m; c++) {
            int oil = 0; //찾은 석유량
            Set<Integer> found = new HashSet<>(); //중복 필터링
            
            for(int r=0; r<n; r++) {
                if(land[r][c] == 1) {
                    int groupId = group[r][c];
                    if(groupId != 0 && !found.contains(groupId)) {
                        found.add(groupId);
                        oil += map.get(groupId);
                    }
                }
            }
            
            answer = Math.max(answer, oil);
        }
        
        
        return answer;
    }
    
    int bfs(int[][] land, int[][] group, int r, int c, int g) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {r, c});
        
        group[r][c] = g;
        int cnt = 0;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0]; int cy = curr[1];
            cnt++;
            
            for(int i=0; i<4; i++) {
                int nx = cx + dRow[i];
                int ny = cy + dCol[i];
                
                if(isValid(nx, ny) && land[nx][ny] == 1 && group[nx][ny] == 0) {
                    q.offer(new int[]{nx, ny});
                    group[nx][ny] = g;
                }
            }
        }
        
        return cnt;
    }
    
    int dfs(int[][] land, int[][] group, int r, int c, int g) {
        group[r][c] = g;
        
        int cnt = 1;
        
        for(int i=0; i<4; i++) {
            int nr = r + dRow[i];
            int nc = c + dCol[i];
            if(isValid(nr, nc) && land[nr][nc] == 1 && group[nr][nc] == 0) {
                cnt += dfs(land, group, nr, nc, g);
            }
        }
        
        return cnt;
    }
    
    boolean isValid(int r, int c) {
        return r>=0 && r<n && c>=0 && c<m;
    }
}