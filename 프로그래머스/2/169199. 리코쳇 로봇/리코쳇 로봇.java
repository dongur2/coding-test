import java.util.*;
/*
* R: 출발, G: 도착, D: 장애물
* 상하좌우 끝까지 한번에 이동
* 도착지점까지 최소 몇 번 이동해야 하는가? -> bfs
*/
class Solution {
    //상하좌우
    int[] dRow = new int[] {0, 1, 0, -1};
    int[] dCol = new int[] {1, 0, -1, 0};
    
    //보드게임판 크기
    int maxRow, maxCol;
    
    //시작점, 도착점
    int[] start = new int[2];
    int[] end = new int[2];
    
    public int solution(String[] board) {
        maxRow = board.length; 
        maxCol = board[0].length();
        
        findPoint(board);
        
        return bfs(board, start[0], start[1], new boolean[maxRow][maxCol]);
    }
    
    //시작점, 도착점 탐색
    void findPoint(String[] board) {
        for(int r=0; r<maxRow; r++) {
            if(board[r].indexOf('R') > -1) {
                start[0] = r;
                start[1] = board[r].indexOf('R');
            } else if(board[r].indexOf('G') > -1) {
                end[0] = r;
                end[1] = board[r].indexOf('G');
            }
        }
    }
    
    int bfs(String[] board, int startRow, int startCol, boolean[][] visited) {
        //시작
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startRow, startCol, 0});
        visited[startRow][startCol] = true;
        
        //큐 반복
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];
            int moves = cur[2];
            
            //도착점이면 리턴
            if(row == end[0] && col == end[1]) return moves;
            
            for(int i=0; i<4; i++) {
                int newR = row, newC = col;
                
                //막다른 길까지 이동
                while(isValid(board, newR + dRow[i], newC + dCol[i])) {
                    newR += dRow[i];
                    newC += dCol[i];
                }
                
                //도착한 곳이 방문한 적 없는 곳이면 추가
                if(!visited[newR][newC]) {
                    q.offer(new int[]{newR, newC, moves+1});
                    visited[newR][newC] = true;
                }
            }
        }
        return -1;
    }
    
    boolean isValid(String[] board, int r, int c) {
        return r > -1 && r < maxRow && c > -1 && c < maxCol && board[r].charAt(c) != 'D';
    }
}