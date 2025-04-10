import java.util.*;
import java.io.*;

//분리된 영역의 개수와 각 영역의 넓이 계산
public class Main {
  //연결 
  static int[] dRow = {0, 1, 0, -1};
  static int[] dCol = {1, 0, -1, 0};
  
  static int[][] map;
  static boolean[][] visited;
  
  static int m, n = -1;
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    m = Integer.parseInt(st.nextToken());//세로
    n = Integer.parseInt(st.nextToken());//가로
    int k = Integer.parseInt(st.nextToken());//직사각형 개수
    
    //맵 생성
    map = new int[m][n];
    visited = new boolean[m][n];
    
    //직사각형 배치
    //맵을 위로 뒤집기 - 왼쪽 위가 (0,0)
    for(int i=0; i<k; i++) {
      st = new StringTokenizer(br.readLine());
      //왼쪽 아래 (x,y) - x,y 위치만 교환
      int ly = Integer.parseInt(st.nextToken());
      int lx = Integer.parseInt(st.nextToken());
      //오른쪽 위 (x,y) - x,y 위치 교환 + 각 좌표에서 -1
      int ry = Integer.parseInt(st.nextToken()) - 1;
      int rx = Integer.parseInt(st.nextToken()) - 1;
      
      for(int r=lx; r<=rx; r++) {
        for(int c=ly; c<=ry; c++) {
          map[r][c] = 1; //사각형 표시 
        }
      }
    }
    
    //영역 탐색 
    List<Integer> areaList = new ArrayList<>();
    for(int r=0; r<m; r++) {
      for(int c=0; c<n; c++) {
        //방문한적 없는 영역이면 탐색
        if(map[r][c] == 0 && !visited[r][c]) {
          visited[r][c] = true;
          int area = dfs(r, c, 1); //넓이 카운트 
          areaList.add(area); //저장
        }
      }
    }
    
    //오름차순 정렬 
    System.out.println(areaList.size());
    areaList.stream().sorted().forEach(a->System.out.print(a+" "));
  }
  
  public static int dfs(int row, int col, int area) {
    //상하좌우 탐색
    for(int i=0; i<4; i++) {
      int nxtR = row + dRow[i];
      int nxtC = col + dCol[i];
      
      if(isValid(nxtR, nxtC)) {
        visited[nxtR][nxtC] = true;
        area = dfs(nxtR, nxtC, area+1);
      }
    }
    
    //더 이상 탐색할 곳 없으면 중지 
    return area;
  }
  
  public static boolean isValid(int r, int c) {
    return r >= 0 && r < m && c >= 0 && c < n && !visited[r][c] && map[r][c] == 0;
  }
  
}