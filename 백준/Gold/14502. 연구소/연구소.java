import java.util.*;
import java.io.*;

//안전 영역의 최대값
public class Main {
  static int[][] map;
  
  static int n = -1, m = -1;
  static int answer = Integer.MIN_VALUE;
  
  //바이러스 상하좌우 이동
  static int[] dRow = {0, 1, 0, -1};
  static int[] dCol = {1, 0, -1, 0};
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    
    //맵 생성 0:빈칸, 1:벽, 2:바이러스
    map = new int[n][m];
    
    for(int r=0; r<n; r++) {
      st = new StringTokenizer(br.readLine());
      for(int c=0; c<m; c++) {
        map[r][c] = Integer.parseInt(st.nextToken());
      }
    }
    
    //벽 3개를 세우는 모든 경우의 수에서 안전 영역 확인
    //[조합] 하나씩 밀어가면서 만들어 보기
    makeWalls(0);
  
    System.out.println(answer);
  }
  
  public static void makeWalls(int walls) {
    //벽 3개 다 세웠으면 바이러스 전파 후 안전 영역 탐색
    if(walls == 3) {
      //맵 복사
      int[][] copied = new int[n][m];
      boolean[][] visited = new boolean[n][m];
      for(int i=0; i<n; i++) {
        copied[i] = Arrays.copyOf(map[i], m);
      }
      
      //전파
      for(int r=0; r<n; r++) {
        for(int c=0; c<m; c++) {
          if(copied[r][c] == 2 && !visited[r][c]) {
            visited[r][c] = true;
            pollute(copied, visited, r, c); 
          } 
        }
      }
      
      //안전구역 카운트
      int safe = 0;
      for(int r=0; r<n; r++) {
        for(int c=0; c<m; c++) {
          if(copied[r][c] == 0) safe++;
        }
      }
      
      //최대값 바인딩
      answer = Math.max(answer, safe);
      return;
    }
    
    for(int r=0; r<n; r++) {
      for(int c=0; c<m; c++) {
        if(map[r][c] == 0) {
          map[r][c] = 1; //벽 생성
          makeWalls(walls+1);
          map[r][c] = 0; //복구
        }
      }
    }  
  }
  
  public static void pollute(int[][] copiedMap, boolean[][] visited, int row, int col) {
    //상하좌우
    for(int i=0; i<4; i++) {
      int nxtR = row + dRow[i];
      int nxtC = col + dCol[i];
      
      if(isValid(copiedMap, visited, nxtR, nxtC)) {
        visited[nxtR][nxtC] = true;
        //빈 칸일 경우 감염
        if(copiedMap[nxtR][nxtC] == 0) copiedMap[nxtR][nxtC] = 2;
        pollute(copiedMap, visited, nxtR, nxtC);
      }
    }
  }
  
  public static boolean isValid(int[][] copiedMap, boolean[][] visited, int row, int col) {
    return row>=0 && row<n && col>=0 && col<m && !visited[row][col] && copiedMap[row][col] != 1;
  }
}