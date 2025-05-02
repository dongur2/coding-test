import java.util.Deque;
import java.util.ArrayDeque;
//(0,0) -> (n-1, n-1)에 도착하는 최단 거리 (불가능하면 -1)
class Solution {
    int n = 0, m = 0;
    boolean[][] visited;
    
    int[] dRow = new int[] {0, 1, 0, -1};
    int[] dCol = new int[] {1, 0, -1, 0};
    
    public int solution(int[][] maps) {
        n = maps.length; m = maps[0].length;
        visited = new boolean[n][m];
        
        return bfs(maps);
    }
    
    public int bfs(int[][] maps) {
        Deque<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, 1));
        
        while(!q.isEmpty()) {
            Node curr = q.poll();
            
            if(curr.row == n-1 && curr.col == m-1) return curr.moves;
            
            for(int i=0; i<4; i++) {
                int nxtR = curr.row + dRow[i];
                int nxtC = curr.col + dCol[i];
                
                if(isValid(maps, nxtR, nxtC)) {
                    visited[nxtR][nxtC] = true;
                    q.offer(new Node(nxtR, nxtC, curr.moves+1));
                }
            }
        }
        return -1;
    }
    
    public boolean isValid(int[][] maps, int r, int c) {
        return r>=0 && r<n && c>=0 && c<m && !visited[r][c] && maps[r][c] == 1;
    }
    
    private class Node {
        private int row;
        private int col;
        private int moves;
        
        public Node(int row, int col, int moves) {
            this.row = row;
            this.col = col;
            this.moves = moves;
        }
    }
}