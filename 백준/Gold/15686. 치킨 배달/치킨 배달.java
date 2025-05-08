import java.util.*;
import java.io.*;


//치킨 거리의 최솟값 
public class Main {
  static int n = 0, m = 0;
  static int[][] map;
  static boolean[][] visited;
  static int answer = 0;
  
  static int[] dRow = new int[] {0, 1, 0, -1};
  static int[] dCol = new int[] {1, 0, -1, 0};
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());  
    m = Integer.parseInt(st.nextToken());
    
    //지도 생성
    List<Position> houses = new ArrayList<>();
    List<Position> chickens = new ArrayList<>(); //치킨집 위치
    map = new int[n][n];
    visited = new boolean[n][n];
    for(int r=0; r<n; r++) {
      st = new StringTokenizer(br.readLine());
      for(int c=0; c<n; c++) {
        map[r][c] = Integer.parseInt(st.nextToken());
        
        //집, 치킨집 위치 저장
        if(map[r][c] == 1) houses.add(new Position(r, c));
        if(map[r][c] == 2) chickens.add(new Position(r, c));
      }
    }
    
    //기존 치킨집 중 m개 고른 조합 
    List<List<Position>> comb = new ArrayList<>();
    for(int i=0; i<chickens.size(); i++) {
      makeCombination(comb, chickens, i, new ArrayList<>());    
    }
    
    //각 조합에 대한 치킨거리 계산 
    List<Integer> dists = new ArrayList<>(); //구한 치킨 거리를 리스트로 저장
    for(List<Position> c:comb) {
      dists.add(computeChickenDistance(c, houses));  
    }
    
    //모든 치킨 거리 경우의 수를 오름차순 정렬하고 첫번째 값을 리턴 
    dists.stream().sorted().limit(1).forEach(d->System.out.println(d));
  }
  
  //m개 고른 치킨집 조합 생성
  public static void makeCombination(List<List<Position>> comb, List<Position> chickens, int idx, List<Position> list) {
    if (list.size() == m) {
      comb.add(new ArrayList<>(list));
      return;
    }
  
    for(int i=idx; i<chickens.size(); i++) {
      list.add(chickens.get(i));
      makeCombination(comb, chickens, i+1, list);
      list.remove(list.size() - 1); // 복구  
    }
  }
  
  public static int computeChickenDistance(List<Position> chickens, List<Position> houses) {
    //현재 조합 치킨 거리의 합 
    int sum = 0;
    
    for(Position h:houses) {
      //집
      int hr = h.r;
      int hc = h.c;
      
      int len = Integer.MAX_VALUE;
      for(Position c:chickens) {
        len = Math.min((Math.abs(c.r-hr)+Math.abs(c.c-hc)), len);          
      }
      
      sum += len;
    }
    
    return sum;
  }
  
  public static int getDistanceFromHouse(int[][] chickenMap, int row, int col, int d) {
    Deque<int[]> q = new ArrayDeque<>();
    q.offer(new int[]{row, col, d});
    visited[row][col] = true;
    
    while(!q.isEmpty()) {
      int[] curr = q.poll();
      
      //치킨집을 발견했을 경우 그 집으로부터의 치킨 거리 리턴  
      if(chickenMap[curr[0]][curr[1]] == 2) return curr[2];
      
      for(int i=0; i<4; i++) {
        int nr = curr[0] + dRow[i];
        int nc = curr[1] + dCol[i];
        
        if(isValid(nr, nc)) {
          visited[nr][nc] = true;
          q.offer(new int[]{nr, nc, curr[2]+1});
        }
      }
    }
    
    return -1;
  }
  
  public static boolean isValid(int r, int c) {
    return r>=0 && r<n && c>=0 && c<n && !visited[r][c];
  }
  
  private static class Position {
    int r, c;
    
    public Position(int r, int c) {
      this.r=r; this.c=c;
    }
    
    @Override
    public boolean equals(Object obj) {
      if(!(obj instanceof Position)) return false;
      Position o = (Position) obj;
      return this.r == o.r && this.c == o.c;
    }
    
    @Override
    public int hashCode() {
      return Objects.hash(r, c); 
    }
  }
}