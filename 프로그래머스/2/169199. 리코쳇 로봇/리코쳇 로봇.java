import java.util.*;

//출발점에서 목표점까지 도달하는 최소 이동 수
class Solution {
    static String[] b;
    static boolean[][] visited;
    static int row = -1, col = -1;
    
    static int[] start = new int[2];
    static int[] goal = new int[2];
    
    static int[] dRow = {0, 1, 0, -1};
    static int[] dCol = {1, 0, -1, 0};
    
    public int solution(String[] board) {
        //보드판 복사
        b = Arrays.copyOf(board, board.length);
        row = b.length; 
        col = b[0].length();
        visited = new boolean[row][col];
        
        //로봇 위치 (출발점), 목표점 찾기
        for(int r=0; r<row; r++) {
            String line = board[r];
            if(line.indexOf('R') > -1) {
                start[0] = r;
                start[1] = line.indexOf('R');
            }
            if(line.indexOf('G') > -1) {
                goal[0] = r;
                goal[1] = line.indexOf('G');
            }
        }
        
        return bfs();
    }
    
    public static int bfs() {
        //initial
        Deque<int[]> q = new ArrayDeque<>();
        visited[start[0]][start[1]] = true;
        q.offer(new int[]{start[0], start[1], 0});
        
        //queue
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int nowR = curr[0];
            int nowC = curr[1];
            int moves = curr[2];
            
            //현재 위치가 목표점이라면 움직임 수 리턴
            if(nowR == goal[0] && nowC == goal[1]) return moves;
            
            //상하좌우 확인
            for(int i=0; i<4; i++) {
                int nxtR = nowR;
                int nxtC = nowC;
                
                //장애물이나 보드판 끝을 마주할 때까지 이동
                while(isValid(nxtR + dRow[i], nxtC + dCol[i])) {
                    nxtR += dRow[i];
                    nxtC += dCol[i];
                }
                
                //방문한 적 있으면 중지   
                if(visited[nxtR][nxtC]) continue;
                
                //방문한 적 없는 위치면 진행
                q.offer(new int[]{nxtR, nxtC, moves+1});
                visited[nxtR][nxtC] = true;
            }
        }            
        //도달하지 못했을 경우 -1
        return -1;
    }
    
    public static boolean isValid(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < col && b[r].charAt(c) != 'D';
    }
}