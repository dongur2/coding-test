import java.util.*;
import java.io.*;

//(0,0)에서 출발해 지날 수 있는 최대 칸 수 
public class Main {
  //상하좌우 1칸 이동
  static int[] dRow = {0, 1, 0, -1};
  static int[] dCol = {1, 0, -1, 0};
  
  static char[][] map;
  static boolean[][] visited;
  
  static int r = -1, c = -1;
  static int answer = 0;
  
  public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      r = Integer.parseInt(st.nextToken());
      c = Integer.parseInt(st.nextToken());
      
      map = new char[r][c];
      visited = new boolean[r][c];
      
      //맵 생성
      for(int i=0; i<r; i++) {
        char[] input = br.readLine().toCharArray();
        for(int j=0; j<c; j++) {
          map[i][j] = input[j];
        }
      }
      
      //최대한 많은 칸을 지나야 하므로 dfs로 탐색
      visited[0][0] = true; //방문 처리
      Set<Character> chars = new HashSet<>();
      chars.add(map[0][0]);
      dfs(0, 0, 1, chars); //현재 칸 카운트
      
      System.out.println(answer);
  }
  
  public static void dfs(int row, int col, int cnt, Set<Character> chars) {
    //recursive: 인접구역 확인 - 지도 내, 방문 전, 알파벳 중복 여부
    for(int i=0; i<4; i++) {
      int nxtR = row + dRow[i];
      int nxtC = col + dCol[i];
      
      if(isValid(nxtR, nxtC, chars)) {
        visited[nxtR][nxtC] = true;
        chars.add(map[nxtR][nxtC]);
        dfs(nxtR, nxtC, cnt+1, chars);
        
        //복구
        chars.remove(map[nxtR][nxtC]);
        visited[nxtR][nxtC] = false;
      }
    }
    
    //basecase: 더 이상 이동할 수 없으면 중지 후 카운트 비교
    answer = Math.max(answer, cnt);
  }
  
  public static boolean isValid(int row, int col, Set<Character> chars) {
    return row >= 0 && row < r && col >= 0 && col < c && !visited[row][col] && !chars.contains(map[row][col]);
  }
}