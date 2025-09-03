import java.util.*;
import java.io.*;

/*
  >>> 작동 멈출 때까지 청소 영역 개수 
  방 n x m 
  (0,0) - (n-1,m-1)좌표
  빈칸은 전부 청소되지 않은 상태 
  - 동서남북 이동 
  - 주변 4칸 다 청소됐으면: 
    바라보는 쪽 뒤로 후진(못하면 멈춤)
  - 청소되지 않은 칸이 있으면:
    반시계 90도 회전
    바라보는쪽 앞이 청소되지않았으면 전진 
*/
public class Main {
  static int[] dRow = new int[] {-1, 0, 1, 0};
  static int[] dCol = new int[] {0, 1, 0, -1};
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    
    st = new StringTokenizer(br.readLine());
    //로봇 시작 좌표 (시작점은 항상 빈 칸)
    int r = Integer.parseInt(st.nextToken()); 
    int c = Integer.parseInt(st.nextToken());
    //로봇이 바라보는 방향 - 0:북 1:동 2:남 3:서 
    int d = Integer.parseInt(st.nextToken());
    
    int[][] room = new int[n][m];
    for(int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<m; j++) {
        room[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    //0: 청소X, 1: 벽, 2: 청소 완료
    int res = 0;
    
    //작동 멈출 때까지 청소 
    while(true) {
      //현재 있는 칸이 0이면 청소
      if(room[r][c] == 0) {
        room[r][c] = 2; res++;
      }
      
      //주변 네칸 확인: 빈 칸 있으면
      if(existEmpty(room, n, m, r, c)) {
        d = (d + 3) % 4; //반시계 90도 회전 
        int nr = r + dRow[d], nc = c + dCol[d];
        if(isValid(room, n, m, nr, nc) && room[nr][nc] == 0) { //바라보는 방향을 기준으로 앞이 빈칸이면 전진 
          r += dRow[d];
          c += dCol[d]; //한칸 전진 후 처음으로 돌아감 - 있는 칸 청소 
        }
      }
      
      //빈칸 없으면 
      else {
        int nr = r + dRow[(d+2) % 4], nc = c + dCol[(d+2) % 4];
        if(isValid(room, n, m, nr, nc)) { //바라보는 방향을 유지하고 후진 
          r = nr;
          c = nc;
        } else break; //후진 불가능하면 작동 멈춤 
      }
    }
    
    System.out.println(res);
  }
  
  static boolean existEmpty(int[][] room, int n, int m, int r, int c) {
    for(int i=0; i<4; i++) {
      int nr = r + dRow[i];
      int nc = c + dCol[i];
      
      //빈 칸?
      if(isValid(room, n, m, nr, nc) && room[nr][nc] == 0) return true;
    }
    return false;
  }
  
  static boolean isValid(int[][] room, int n, int m, int r, int c) {
    return r >= 0 && r < n && c >= 0 && c < m && room[r][c] != 1;
  }
  
}