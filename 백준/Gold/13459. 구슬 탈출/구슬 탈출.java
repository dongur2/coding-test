import java.util.*;
import java.io.*;

//10번 이하로 빨간 구슬을 움직여 빼낼 수 있는지 여부 (가능:1, 불가능:0)
public class Main {
    /*
      파란 구슬을 구멍으로 빠뜨리지 않고 빨간 구슬을 빼내기
      - 구슬은 상하좌우로 끝까지 이동 
      - 두 공은 동시에 이동
      - 두 공은 같은 칸에 동시에 위치할 수 없음
      - . 빈 칸, # 장애물, O hole, R red ball, B blue ball
    */
    
    static int[] dRow = new int[] {0, 1, 0, -1};
    static int[] dCol = new int[] {1, 0, -1, 0};
    
    static char[][] map;
    static boolean[][][][] visited;
    static int n = 0, m = 0; // height:n, width:m
    
    static int[] hole = new int[2];
    
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    
    map = new char[n][m];
    visited = new boolean[n][m][n][m];

    
    int[] red = new int[2];
    int[] blue = new int[2];
    
    //make map
    for(int r=0; r<n; r++) {
      String line = br.readLine();
      for(int c=0; c<m; c++) {
        map[r][c] = line.charAt(c); 
        
        //find locations of balls and hole
        if(line.charAt(c) == 'B') {
          blue[0] = r; blue[1] = c;
        }
        if(line.charAt(c) == 'R') {
          red[0] = r; red[1] = c;
        }
        if(line.charAt(c) == 'O') {
          hole[0] = r; hole[1] = c;
        }
      }
    }
    
    //bfs
    System.out.print(canEscape(red, blue));
  }
  
  public static int canEscape(int[] startR, int[] startB) {
    Deque<Balls> q = new ArrayDeque<>();
    q.offer(new Balls(startR[0], startR[1], startB[0], startB[1], 1));
    visited[startR[0]][startR[1]][startB[0]][startB[1]] = true;
    
    while(!q.isEmpty()) {
      Balls curr = q.poll();
      
      //10번 초과
      if(curr.moves > 10) break;
      
      for(int i=0; i<4; i++) {
        int[] red = move(curr.rx, curr.ry, dRow[i], dCol[i]);
        int[] blue = move(curr.bx, curr.by, dRow[i], dCol[i]);
        
        //파란 구슬이 탈출했을 경우 다른 방법으로 (파란공이 빠지면 무조건 불가)
        if(blue[0] == hole[0] && blue[1] == hole[1]) continue;
        
        //빨간 구슬이 탈출하고
        if(red[0] == hole[0] && red[1] == hole[1]) {
          //파란 공도 빠졌으면 실패 (동시에 빠지는 경우)
          if(blue[0] == hole[0] && blue[1] == hole[1]) continue;
          //파란공은 안빠졌으면 성공
          else return 1;
        }

        //최종 위치가 같을 경우 더 적게 움직인 구슬을 앞으로 (먼저 도착함)
        if(red[0] == blue[0] && red[1] == blue[1]) {
          if(red[2] < blue[2]) {
            blue[0] -= dRow[i]; blue[1] -= dCol[i];
          } else {
            red[0] -= dRow[i]; red[1] -= dCol[i];
          }
        }
        
        //방문 체크 후 예약
        if(!visited[red[0]][red[1]][blue[0]][blue[1]]) {
          visited[red[0]][red[1]][blue[0]][blue[1]] = true;
          q.offer(new Balls(red[0], red[1], blue[0], blue[1], curr.moves+1));
        }
      }
    }
    
    return 0;
  }
  
  public static int[] move(int r, int c, int dr, int dc) {
    int steps = 0;
    
    //영역 내 + 빈칸일 경우 이동
    while(isValid(r+dr,c+dc) && map[r+dr][c+dc] != '#') {
      r += dr;
      c += dc;
      steps++;
      if(map[r][c] == 'O') break; //구멍에 도착했을 경우 중지
    }
    
    return new int[]{r, c, steps}; //최종 위치와 움직인 횟수 리턴
  }
  
  //in map area, not hurdle
  public static boolean isValid(int r, int c) {
    return r>=0 && r<n && c>=0 && c<m;
  }
  
  private static class Balls {
    private int rx, ry, bx, by, moves;
    
    public Balls(int rx, int ry, int bx, int by, int moves) {
      this.rx=rx;
      this.ry=ry;
      this.bx=bx;
      this.by=by;
      this.moves=moves;
    }
  }
}