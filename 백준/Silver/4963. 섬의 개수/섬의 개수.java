import java.util.*;
import java.io.*;

//섬 개수
public class Main {
    static int w = -1;
    static int h = -1;
    
    static int[][] map;
    static boolean[][] visited;
    
    static List<Integer> answer = new ArrayList<>();
    
    //상하좌우, 대각선 
    static int[] dRow = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dCol = {1, 1, 0, -1, -1, -1, 0, 1};
    
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
      while(true) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        
        //중지
        if(w == 0 && h == 0) break;
        
        //지도 생성
        map = new int[h][w];
        visited = new boolean[h][w];
        
        for(int r=0; r<h; r++) {
          String input = br.readLine();
          String[] arr = input.split(" ");
          for(int c=0; c<w; c++) {
            map[r][c] = Integer.parseInt(arr[c]);
          }
        }
        
        //섬 개수 카운트 
        int islands = findIsland();
        answer.add(islands); //정답 배열에 개수 추가
      }
      
      //테스트별 섬 개수 출력
      answer.forEach(l->System.out.println(l));
    }
    
    public static int findIsland() {
      int cnt = 0;
      
      //육지를 탐색
      for(int r=0; r<h; r++) {
        for(int c=0; c<w; c++) {
          if(!visited[r][c] && map[r][c] == 1) {
            cnt++;
            visited[r][c] = true;
            dfs(r, c);
          }
        }
      }
      return cnt;
    }
    
    public static void dfs(int row, int col) {
      //인접지역 육지 탐색 
      for(int i=0; i<8; i++) {
        int nxtR = row + dRow[i];
        int nxtC = col + dCol[i];
        
        if(isValid(nxtR, nxtC)) {
          visited[nxtR][nxtC] = true;
          dfs(nxtR, nxtC);
        }
      }
    }
    
    public static boolean isValid(int r, int c) {
      return r >= 0 && r < h && c >= 0 && c < w && !visited[r][c] && map[r][c] == 1;
    }
}