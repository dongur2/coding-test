import java.util.*;
import java.io.*;

//탈출할 수 있으면 Escaped in x minute(s).
//탈출할 수 없으면 Trapped!
public class Main {
  //동서남북상하 - 정육면체 
  static int[] dRow = new int[] {0, 1, 0, -1};
  static int[] dCol = new int[] {1, 0, -1, 0};
  
  static int l, r, c;
  static char[][][] map;
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    while(true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      l = Integer.parseInt(st.nextToken()); //층 수
      r = Integer.parseInt(st.nextToken()); //행
      c = Integer.parseInt(st.nextToken()); //열
      
      //000이면 종료
      if(l==0 && r==0 && c==0) break;
      
      map = new char[l][r][c];
      
      //맵 생성(#벽, .빈칸, E출구, S출발)
      int[] start = new int[3];
      
      for(int f=0; f<l; f++) {
        for(int x=0; x<r; x++) {
          char[] line = br.readLine().toCharArray();
          for(int y=0; y<c; y++) {
            map[f][x][y] = line[y];
            
            if(line[y] == 'S') {
              start[0] = f; start[1] = x; start[2] = y;
            }
          }
        }
        br.readLine();
      }
      
      //출발지에서 최단 시간으로 탈출 
      int time = bfs(start);
      
      if(time == -1) System.out.println("Trapped!");
      else System.out.printf("Escaped in %d minute(s).\n", time);
    } 
  }
  
  public static int bfs(int[] start) {
    boolean[][][] visited = new boolean[l][r][c];
    Deque<int[]> q = new ArrayDeque<>();
    q.offer(new int[]{start[0], start[1], start[2], 0});
    visited[start[0]][start[1]][start[2]] = true;
    
    while(!q.isEmpty()) {
      int[] curr = q.poll();
      int cl = curr[0];
      int cr = curr[1];
      int cc = curr[2];
      int moves = curr[3];
      
      //출구라면 탈출
      if(map[cl][cr][cc] == 'E') return moves;
      
      //상하
      for(int i:new int[]{1, -1}) {
        int nl = cl+i;
        
        if(isValid(nl, cr, cc, visited)) {
          visited[nl][cr][cc] = true;
          q.offer(new int[]{nl, cr, cc, moves+1});
        }
      }
      //동서남북
      for(int i=0; i<4; i++) {
        int nr = cr + dRow[i];
        int nc = cc + dCol[i];
        
        if(isValid(cl, nr, nc, visited)) {
          visited[cl][nr][nc] = true;
          q.offer(new int[]{cl, nr, nc, moves+1});
        }
      }
    }
    
    return -1;
  }
  
  public static boolean isValid(int f, int x, int y, boolean[][][] visited) {
    return f>=0 && f<l && x>=0 && x<r && y>=0 && y<c && !visited[f][x][y] && map[f][x][y] != '#';
  }
}