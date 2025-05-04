import java.util.Deque;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.io.*;
/*
구멍이 하나 있는 보드에서 "10번 이하"로 보드를 움직여서
"빨간 구슬만 구멍으로 빼낼 수 있는지" 여부 

- 상하좌우로 이동 가능 
- 빨간 구슬과 파란 구슬은 <동시에 한 방향>으로 장애물에 부딪혀 멈출 때까지 이동
- 오직 빨간 구슬만 탈출 
- 두 구슬은 동시에 같은 위치에 있을 수 없음 
*/
public class Main {
    static int[] dRow = {0, 1, 0, -1};
    static int[] dCol = {1, 0, -1, 0};
    
    static int n = 0, m = 0; //height n, width m
    static char[][] map;
    static boolean[][][][] visited;
    
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      //맵 생성 
      map = new char[n][m];
      visited = new boolean[n][m][n][m]; // 두 공의 각 위치 방문 여부 
      
      int rx = 0, ry = 0, bx = 0, by = 0; //두 공의 위치 
      for(int r=0; r<n; r++) {
        char[] line = br.readLine().toCharArray();
        for(int c=0; c<m; c++) {
          map[r][c] = line[c];
          
          if(map[r][c] == 'R') {
            rx = r; ry = c;
          }
          
          if(map[r][c] == 'B') {
            bx = r; by = c;
          }
        }
      }
      
      System.out.print(bfs(rx, ry, bx, by, 1));
    }
    
    public static int bfs(int rx, int ry, int bx, int by, int moves) {
      Deque<Balls> q = new ArrayDeque<>();
      q.offer(new Balls(rx, ry, bx, by, moves));
      visited[rx][ry][bx][by] = true;
      
      while(!q.isEmpty()) {
        Balls curr = q.poll();
        
        //10번 초과했을 경우 중지
        if(curr.moves > 10) return 0;
        
        //상하좌우 탐색 - 공 각각 동시 이동 고려 
        for(int i=0; i<4; i++) {
          //공이 한 방향으로 굴러가 멈춘 최종 위치와 움직인 칸 개수 
          int[] red = move(curr.rx, curr.ry, dRow[i], dCol[i]);
          int[] blue = move(curr.bx, curr.by, dRow[i], dCol[i]);
          
          //만약 파란 공이 탈출했으면 다른 방향을 확인 (이번 방향은 실패)
          if(map[blue[0]][blue[1]] == 'O') continue;
          
          //만약 빨간 공이 탈출했을 경우 
          if(map[red[0]][red[1]] == 'O') {
            //파란 공도 탈출했으면 다른 방향을 확인 
            if(map[blue[0]][blue[1]] == 'O') continue;
            return 1; //파란 공은 탈출못했으면 성공 
          }
          
          //둘의 위치가 같으면 더 늦게 온 공을 뒤로 
          if(red[0] == blue[0] && red[1] == blue[1]) {
            if(red[2] < blue[2]) {
              blue[0] -= dRow[i]; blue[1] -= dCol[i];
            } else {
              red[0] -= dRow[i]; red[1] -= dCol[i];
            }
          }
          
          //방문한 적 없으면 이동 확정 후 예약 
          if(!visited[red[0]][red[1]][blue[0]][blue[1]]) {
            visited[red[0]][red[1]][blue[0]][blue[1]] = true;
            q.offer(new Balls(red[0], red[1], blue[0], blue[1], curr.moves+1));
          }
        }
      }
      
      //반복문이 끝날 때까지 탈출 못했으면 실패
      return 0;
    }
    
    public static int[] move(int r, int c, int dr, int dc) {
      int steps = 0; //공이 움직인 칸 개수 
      
      while(isValid(r+dr, c+dc)) {
        r += dr;
        c += dc;
        steps++;
        //만약 움직인 위치에 구멍이 있다면 탈출 
        if(map[r][c] == 'O') break;
      }
      
      return new int[] {r, c, steps};
    }
    
    public static boolean isValid(int r, int c) {
      return r>=0 && r<n && c>=0 && c<m && map[r][c] != '#';
    }
    
    //두 공의 현재 위치와 움직임 횟수 
    private static class Balls {
      int rx, ry, bx, by, moves;
      
      public Balls(int rx, int ry, int bx, int by, int moves) {
        this.rx = rx; this.ry = ry;
        this.bx = bx; this.by = by;
        this.moves = moves;
      }
    }
}
