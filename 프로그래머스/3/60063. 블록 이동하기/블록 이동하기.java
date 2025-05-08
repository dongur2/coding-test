import java.util.*;

//0,0 -> n-1,n-1에 도착하는 최소 시간
class Solution {
    static int[] dRow = new int[] {0, 1, 0, -1};
    static int[] dCol = new int[] {1, 0, -1, 0};
    
    static int n = 0;
    
    public int solution(int[][] board) {
        n = board.length;
        return bfs(board);
    }
    
    public static int bfs(int[][] board) {
        boolean[][][][] visited = new boolean[n][n][n][n];
        Deque<Position> q = new ArrayDeque<>();
        q.offer(new Position(0, 0, 0, 1, 0));        
        visited[0][0][0][1] = true;
        
        while(!q.isEmpty()) {
            Position curr = q.poll();
            int lx = curr.lx;
            int ly = curr.ly;
            int rx = curr.rx;
            int ry = curr.ry;
            
            //도착
            if((curr.lx == n-1 && curr.ly == n-1) || (curr.rx == n-1 && curr.ry == n-1)) return curr.moves;
            
            //로봇은 두칸 차지 - 가로/세로/회전 이동 
            //상하좌우 이동
            for(int i=0; i<4; i++) {
                int nlx = lx + dRow[i]; int nly = ly + dCol[i];
                int nrx = rx + dRow[i]; int nry = ry + dCol[i];
                
                //좌우 정렬
                Position next = sortPosition(nlx, nly, nrx, nry, curr.moves+1);
                if(isValid(board, visited, next)) {
                    q.offer(next); 
                    visited[next.lx][next.ly][next.rx][next.ry] = true;
                }
            }
            
            //회전
            //가로로 놓여있을 경우: 행이 같음  (2,2)(2,3) -> 왼쪽 고정/오른쪽 고정 (-1,-1)(+1,-1)/(-1,+1)(+1,+1)
            if(lx == rx) {
                //왼쪽 고정하고 오른쪽을 위/아래로 회전
                for(int i:new int[] {1, -1}) { //(2,2)(2,3)
                    int nx1 = lx - i; 
                    int ny1 = ly; //(1,2) //회전해서 도착하는 지점 
                    int nx2 = rx - i; 
                    int ny2 = ry; //(1,3) //회전하면서 거쳐가는 지점
                    
                    if(isInBoard(nx1, ny1) && isInBoard(nx2, ny2) && board[nx2][ny2] == 0 && board[nx1][ny1] == 0) {
                        Position p = sortPosition(lx, ly, nx1, ny1, curr.moves+1);
                    
                        if(isValid(board, visited, p)) {
                            q.offer(p);
                            visited[p.lx][p.ly][p.rx][p.ry] = true;
                        }
                    }
                }
                
                //오른쪽 고정하고 완쪽을 위/아래로 회전
                for(int i:new int[] {1, -1}) { //(2,2)(2,3)
                    int nx1 = rx - i; 
                    int ny1 = ry; //(1,3) //회전해서 도착하는 지점 
                    int nx2 = lx - i; 
                    int ny2 = ly; //(1,2) //회전하면서 거쳐가는 지점
                    
                    if(isInBoard(nx1, ny1) && isInBoard(nx2, ny2) && board[nx2][ny2] == 0 && board[nx1][ny1] == 0) {
                        Position p = sortPosition(rx, ry, nx1, ny1, curr.moves+1);
                    
                        if(isValid(board, visited, p)) {
                            q.offer(p);
                            visited[p.lx][p.ly][p.rx][p.ry] = true;
                        }
                    }
                }
            }
            
            //세로로 놓여있을 경우: 열이 같음
            if(ly == ry) {
                //위쪽 고정하고 아래쪽을 왼쪽/오른쪽으로 회전
                for(int i:new int[] {1, -1}) { //(2,2)(3,2)
                    int nx1 = lx;
                    int ny1 = ly - i; //(2,1) //회전해서 도착하는 지점 
                    int nx2 = rx;
                    int ny2 = ry - i; //(3,1) //회전하면서 거쳐가는 지점
                    
                    if(isInBoard(nx1, ny1) && isInBoard(nx2, ny2) && board[nx2][ny2] == 0 && board[nx1][ny1] == 0) {
                        Position p = sortPosition(lx, ly, nx1, ny1, curr.moves+1);
                    
                        if(isValid(board, visited, p)) {
                            q.offer(p);
                            visited[p.lx][p.ly][p.rx][p.ry] = true;
                        }
                    }
                }
                
                //아래쪽 고정하고 위쪽을 왼쪽/오른쪽으로 회전
                for(int i:new int[] {1, -1}) { //(2,2)(3,2)
                    int nx1 = rx;
                    int ny1 = ry - i; //(3,1) //회전해서 도착하는 지점 
                    int nx2 = lx;
                    int ny2 = ly - i; //(2,1) //회전하면서 거쳐가는 지점
                    
                    if(isInBoard(nx1, ny1) && isInBoard(nx2, ny2) && board[nx2][ny2] == 0 && board[nx1][ny1] == 0) {
                        Position p = sortPosition(rx, ry, nx1, ny1, curr.moves+1);
                    
                        if(isValid(board, visited, p)) {
                            q.offer(p);
                            visited[p.lx][p.ly][p.rx][p.ry] = true;
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    //항상 왼쪽이 더 작게 
    public static Position sortPosition(int lx, int ly, int rx, int ry, int moves) {
        if (lx == rx) { // 가로 방향이면 y 오름차순 기준
            if (ly > ry) {
                int tmpx = lx, tmpy = ly;
                lx = rx; ly = ry;
                rx = tmpx; ry = tmpy;
            }
        } else { // 세로 방향이면 x 오름차순 기준
            if (lx > rx) {
                int tmpx = lx, tmpy = ly;
                lx = rx; ly = ry;
                rx = tmpx; ry = tmpy;
            }
        }
        return new Position(lx, ly, rx, ry, moves);
    }
    
    public static boolean isValid(int[][] board, boolean[][][][] visited, Position p) {
        return p.lx >= 0 && p.lx < n && p.rx >= 0 && p.rx < n && p.ly >= 0 && p.ly < n && p.ry >= 0 && p.ry < n && !visited[p.lx][p.ly][p.rx][p.ry] && board[p.lx][p.ly] == 0 && board[p.rx][p.ry] == 0;
    }
    
    public static boolean isInBoard(int x, int y) {
        return x>=0 && x<n && y>=0 && y<n;
    }
    
    private static class Position {
        int lx, ly, rx, ry, moves;
        
        public Position(int lx, int ly, int rx, int ry, int m) {
            this.lx=lx; this.ly=ly; this.rx=rx; this.ry=ry; this.moves=m;
        }
    }
}