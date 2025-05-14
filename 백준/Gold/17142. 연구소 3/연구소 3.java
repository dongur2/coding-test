import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//시간 제한 0.25초 ;;
//모든 빈 칸에 바이러스를 퍼뜨리는 최소 시간
public class Main {
  //바이러스는 상하좌우 전파 - 1초 (m개)
  static int[] dRow = new int[]{0,1,0,-1};
  static int[] dCol = new int[]{1,0,-1,0};
  
  static int n, m;
  
  static int[][] map;
  
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //n*n 연구소 - 0빈칸, 1벽 2바이러스 
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    
    map = new int[n][n];
    
    List<Virus> viruses = new ArrayList<>(); //각 바이러스 위치 
    for(int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<n; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        
        if(map[i][j] == 2) viruses.add(new Virus(i, j)); //바이러스 위치는 리스트에 저장 
      }
    }
    
    //활성시킬 바이러스 M개 선정
    List<List<Virus>> comb = new ArrayList<>();
    for(int i=0; i<viruses.size(); i++) {
      makeComb(comb, viruses, i+1, new ArrayList<>(List.of(viruses.get(i))));
    }
    
    int answer = Integer.MAX_VALUE;
    
    //선정한 바이러스 활성화 -> 전파 속도 확인 
    for(List<Virus> c:comb) {
      int[][] copied = copyMap(); //전파 위해 카피
      int time = bfs(copied, c);
      answer = Math.min(answer, time);
    }
    
    if(answer == Integer.MAX_VALUE) answer = -1;
    System.out.println(answer);
  }
  
  public static void makeComb(List<List<Virus>> comb, List<Virus> viruses, int idx, List<Virus> selected) {
    //m개 골랐으면 추가하고 리턴
    if(selected.size() == m) {
      comb.add(new ArrayList<>(selected));
      return;
    }
    
    for(int i=idx; i<viruses.size(); i++) {
      selected.add(viruses.get(i));
      makeComb(comb, viruses, i+1, selected);
      selected.remove(selected.size()-1);
    }
  }
  
  public static int[][] copyMap() {
    int[][] m = new int[n][n];
    for(int i=0; i<n; i++) {
      m[i] = Arrays.copyOf(map[i], n);
    }
    return m;
  }
  
  public static int bfs(int[][] copied, List<Virus> virus) {
    Queue<Node> q = new ArrayDeque<>();
    boolean[][] visited = new boolean[n][n];
    for(Virus v:virus) {
      q.offer(new Node(v.x, v.y, 0));
      visited[v.x][v.y] = true;
    }
    
    int time = 0;
    
    while(!q.isEmpty()) {
      Node curr = q.poll();
      int cx = curr.x;
      int cy = curr.y;
      int cs = curr.steps;
      
      //바이러스 전파 
      for(int i=0; i<4; i++) {
        int nx = cx+dRow[i];
        int ny = cy+dCol[i];
        
        if(isValid(copied, visited, nx, ny)) {
          visited[nx][ny] = true;
          
          //빈칸이면 전파, 시간 업데이트 
          if(copied[nx][ny] == 0) {
            copied[nx][ny] = 2;
            time = Math.max(time, cs+1);
          }
          q.offer(new Node(nx, ny, cs+1));
        }
      }
    }
    
    //모든 빈칸이 전파되었는지 확인
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (map[i][j] == 0 && !visited[i][j]) return Integer.MAX_VALUE;
        }
    }
    
    return time;
  }
  
  //연구소 영역 내 + 방문한 적 없는 + 벽이 아닌 구역
  public static boolean isValid(int[][] c, boolean[][] v, int x, int y) {
    return x>=0 && x<n && y>=0 && y<n && c[x][y] != 1 && !v[x][y];
  }
  
  private static class Virus {
    int x, y;
    
    public Virus(int x, int y) {
      this.x=x; this.y=y;
    }
  }
  
  private static class Node {
    int x, y, steps;
    
    public Node(int x, int y, int s) {
      this.x=x; this.y=y; this.steps=s;
    }
  }
}