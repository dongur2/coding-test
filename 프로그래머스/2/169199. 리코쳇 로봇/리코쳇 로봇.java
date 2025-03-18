import java.util.*;

/*
- 시작점에서 도착점까지 필요한 최소 이동 횟수: 제일 빠르게 도착[bfs]
- 상하좌우로 끝까지 이동
*/
class Solution {
    int[] dRow = {0, 1, 0, -1};
    int[] dCol = {1, 0, -1, 0};
    
    //시작점, 도착점
    int[] start = new int[2];
    int[] end = new int[2];
    
    boolean[][] visited;
    
    public int solution(String[] board) {
        visited = new boolean[board.length][board[0].length()];
        
        findPoints(board);
        return bfs(board);
    }
    
    void findPoints(String[] board) {
        for(int r=0; r<board.length; r++) {
            if(board[r].indexOf('R') > -1) {
                start[0] = r;
                start[1] = board[r].indexOf('R');
            }
            if(board[r].indexOf('G') > -1) {
                end[0] = r;
                end[1] = board[r].indexOf('G');
            }
        }
    }
    
    int bfs(String[] board) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {start[0], start[1], 0}); //[현재 좌표, 이동 횟수]
        visited[start[0]][start[1]] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];
            int moves = cur[2];
            
            //현재 위치가 도착점이면 리턴
            if(row == end[0] && col == end[1]) return moves;
            
            for(int i=0; i<4; i++) {
                int newRow = row, newCol = col;
                
                //장애물이 없는 방향으로 끝까지 이동
                while(isValid(board, newRow + dRow[i], newCol + dCol[i])) {
                    newRow += dRow[i];
                    newCol += dCol[i];
                }
                
                //도착: 방문한 적 없으면 대기열에 추가
                if(!visited[newRow][newCol]) {
                    q.offer(new int[]{newRow, newCol, moves + 1});
                    visited[newRow][newCol] = true;
                }

            }
        }
        
        return -1;
    }
    
    boolean isValid(String[] board, int r, int c) {
        return r > -1 && r < board.length && c > -1 && c < board[0].length() && board[r].charAt(c) != 'D';
    }
}