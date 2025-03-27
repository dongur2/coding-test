import java.util.*;

//목표점에 도착하기 위해 필요한 최소 이동 수 [최소 이동 수니까 bfs]
class Solution {
    //상하좌우 이동 가능
    int[] dRow = {0, 1, 0, -1};
    int[] dCol = {1, 0, -1, 0};
    
    //시작점(R), 목표점(G)
    int[] start = new int[2];
    int[] goal = new int[2];
    
    public int solution(String[] board) {
        //시작, 목표 좌표 탐색
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
                goal[0] = r;
                goal[1] = board[r].indexOf('G');
            }
        }
    }
    
    int bfs(String[] board) {
        boolean[][] visited = new boolean[board.length][board[0].length()];
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start[0], start[1], 0}); //좌표, 이동 횟수
        visited[start[0]][start[1]] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];
            int moves = cur[2];
            
            //목표에 도착했을 경우 리턴
            if(row == goal[0] && col == goal[1]) return moves;
            
            //상하좌우 확인 후 방향 정하기
            for(int i=0; i<4; i++) {
                int newRow = row, newCol = col;
                
                //장애물에 부딪치거나 맵 끝까지 이동
                while(isValid(board, newRow + dRow[i], newCol + dCol[i])) {
                    newRow += dRow[i];
                    newCol += dCol[i];
                }

                //도착한 위치에 방문한 적 없으면 큐에 추가
                if(!visited[newRow][newCol]) {
                    q.offer(new int[]{newRow, newCol, moves+1});
                    visited[newRow][newCol] = true;
                }
            }
        }
        //목표에 도착하지 못했을 경우
        return -1;
    }
    
    boolean isValid(String[] board, int r, int c) {
        return r > -1 && r < board.length && c > -1 && c < board[0].length() && board[r].charAt(c) != 'D';
    }
}