import java.util.*;
import java.io.*;


//안전 영역의 최대 크기 
public class Main {
  //바이러스는 상하좌우로 전파
  static int[] dRow = new int[] {0, 1, 0, -1};
  static int[] dCol = new int[] {1, 0, -1, 0};
  
  //연구소 n*m (0: 빈칸, 1: 벽, 2:바이러스)  
  static int n=0, m=0;
  static int[][] map;
  
  static int answer = Integer.MIN_VALUE;
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    
    //맵 생성
    map = new int[n][m];
    for(int r=0; r<n; r++) {
      st = new StringTokenizer(br.readLine());
      for(int c=0; c<m; c++) {
        map[r][c] = Integer.parseInt(st.nextToken());
      }
    }
    
    //벽 3개를 세운 모든 경우의 수에서의 안전 영역 구하기
    makeWallCombinations(0, 0, 1);
    
    System.out.println(answer);
  }
  
  public static void makeWallCombinations(int row, int col, int walls) {
    //벽 3개 다 세웠으면 전파 후 안전영역 체크
    if(walls > 3) {
      //안전영역 카운트 
      int safeArea = countSafeArea();
      //더 큰 값으로 업데이트
      answer = Math.max(answer, safeArea);
      
      return; //복귀
    }
    
    //차례로 탐색하면서 빈 칸에 벽 세우기
    for(int r=0; r<n; r++) {
      for(int c=0; c<m; c++) {
        if(map[r][c] == 0) {
          map[r][c] = 1; //벽 세우고 
          makeWallCombinations(r, c, walls+1); //다음 벽 세우러 재탐색 
          map[r][c] = 0; //벽 없애기 (복구) 
        }
      }
    }
  }
  
  public static int countSafeArea() {
    //기존 맵 복사 
    int[][] temp = new int[n][m];
    for(int i=0; i<n; i++) {
      temp[i] = Arrays.copyOf(map[i], m);
    }
    
    //바이러스 전파 
    boolean[][] visited = new boolean[n][m];
    for(int r=0; r<n; r++) {
      for(int c=0; c<m; c++) {
        if(temp[r][c] == 2 && !visited[r][c]) {
          pollute(temp, visited, r, c);     
        }
      }
    }
    
    //안전영역 카운트 
    int cnt = 0;
    for(int[] line:temp) {
      for(int l:line) {
        if(l == 0) cnt++;
      }
    }
    
    return cnt;
  }
  
  public static void pollute(int[][] temp, boolean[][] visited, int row, int col) {
    visited[row][col] = true;
    
    for(int i=0; i<4; i++) {
      int nr = row + dRow[i];
      int nc = col + dCol[i];
      
      if(isValid(temp, visited, nr, nc)) {
        temp[nr][nc] = 2;
        pollute(temp, visited, nr, nc);
      }
    }
  }
  
  public static boolean isValid(int[][] temp, boolean[][] visited, int r, int c) {
    return r>=0 && r<n && c>=0 && c<m && temp[r][c] != 1 && !visited[r][c];
  }
}