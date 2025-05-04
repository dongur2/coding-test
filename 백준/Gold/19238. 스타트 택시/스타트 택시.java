import java.util.*;
import java.io.*;
/*
모든 승객을 목적지로 데려다주고 연료 충전 후 남은 연료 양 리턴 (불가하면 -1)

- 승객 m명 
- n*n 맵
- 상하좌우 이동 가능 -- 최단경로 
- 현재 위치에서 가장 가까운 승객을 태워서 이동
- 거리가 같으면 행 번호 작은 쪽, 행 번호도 같으면 열 번호 작은 쪽
- 택시랑 승객이 같은 위치라면 거리는 0 
- 연료는 1칸 이동마다 1 소모
- 승객 목적지에 도착하면 이동하면서 소모한 연료 양의 두 배 충전
- 목적지에 도착했을 때 연료가 바닥나면 성공 - 중간에 바닥나면 실패 
*/
public class Main {
    static int n = 0, m = 0;
    static int[][] map;
    
    static int[] dRow = new int[] {0, 1, 0, -1};
    static int[] dCol = new int[] {1, 0, -1, 0};
    
    static int[] destination = new int[3];
    
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      long fuel = Long.parseLong(st.nextToken());
      
      //맵 생성
      map = new int[n+1][n+1];
      for(int r=1; r<=n; r++) {
        st = new StringTokenizer(br.readLine());
        for(int c=1; c<=n; c++) {
          map[r][c] = Integer.parseInt(st.nextToken());
        }
      }
      
      //택시 시작점 
      st = new StringTokenizer(br.readLine());
      int taxiRow = Integer.parseInt(st.nextToken());
      int taxiCol = Integer.parseInt(st.nextToken());
      
      //승객 출발지, 목적지
      List<Passenger> passengers = new ArrayList<>();
      for(int i=0; i<m; i++) {
        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        int dr = Integer.parseInt(st.nextToken());
        int dc = Integer.parseInt(st.nextToken());
        
        passengers.add(new Passenger(sr, sc, dr, dc, 0));
      }
      
      //택시(현재 위치와 연료)
      Taxi taxi = new Taxi(taxiRow, taxiCol, fuel);
      
      //m번 가까운 승객을 찾아 이동
      for(int i=0; i<m; i++) {
        //지금 위치에서 가장 가까운 승객
        Passenger nearest = findNearestPassenger(taxi, passengers);
        
        //가장 가까운 승객이 없으면 실패
        if(nearest == null) {
          taxi.fuel = -1; break;
        }
        
        //택시를 가까운 승객 위치로 이동, 연료 업데이트 
        taxi.row = nearest.sr;
        taxi.col = nearest.sc;
        taxi.fuel -= nearest.dist;
        
        //연료가 마이너스라면 실패 
        if(taxi.fuel < 0) {
          taxi.fuel = -1; break;
        }
      
        //이제 승객의 목적지로 이동
        //목적지 이동에 실패했다면 중지 
        if(!moveTo(taxi, nearest)) break;
        //성공했다면 리스트에서 해당 승객 지우기 (완료)
        else passengers.remove(nearest);
      }
      
      System.out.println(taxi.fuel);
  }
  
  //bfs로 가까운 승객을 찾기 
  public static Passenger findNearestPassenger(Taxi taxi, List<Passenger> ps) {
    boolean[][] visited = new boolean[n+1][n+1];
    int[][] dists = new int[n+1][n+1];
    Deque<Position> q = new ArrayDeque<>();
    q.offer(new Position(taxi.row, taxi.col, 0)); //시작 위치와 거리
    visited[taxi.row][taxi.col] = true;
    
    while(!q.isEmpty()) {
      Position curr = q.poll();
      
      //상하좌우 탐색 
      for(int i=0; i<4; i++) {
        int nr = curr.row + dRow[i];
        int nc = curr.col + dCol[i];
        
        //이동
        if(nr>0 && nr<=n && nc>0 && nc<=n && !visited[nr][nc] && map[nr][nc] != 1) {
          visited[nr][nc] = true;
          dists[nr][nc] = curr.dist + 1; //모든 칸에 대한 거리를 저장 
          q.offer(new Position(nr, nc, curr.dist+1));
        }
      }
    }
    
    //모든 거리를 미리 계산한 뒤에 각 승객의 위치를 비교해서 가장 가까운 승객을 리턴
    Passenger nearest = null;
    int minDist = Integer.MAX_VALUE;
    for(Passenger p:ps) {
      //방문할 수 없는 영역이면 실패 
      if(!visited[p.sr][p.sc]) {
        taxi.fuel = -1; return null;
      }
      
      int d = dists[p.sr][p.sc]; //반복문에서 확인하고 있는 승객 출발지로의 거리
      //거리가 같으면 행 번호 작은 쪽, 행 번호도 같으면 열 번호 작은 쪽
      if(minDist > d 
        || (minDist == d && nearest.sr > p.sr)
        || (minDist == d && nearest.sr == p.sr && nearest.sc > p.sc)) {
        nearest = p;
        minDist = d;
      }
    }
    
    return new Passenger(nearest.sr, nearest.sc, nearest.dr, nearest.dc, minDist);
  }
  
  public static boolean moveTo(Taxi taxi, Passenger p) {
    boolean[][] visited = new boolean[n+1][n+1];
    Deque<Position> q = new ArrayDeque<>();
    q.offer(new Position(taxi.row, taxi.col, 0)); //시작 위치와 거리
    visited[taxi.row][taxi.col] = true;
    
    while(!q.isEmpty()) {
      Position curr = q.poll();
      int cr = curr.row;
      int cc = curr.col;
      int cd = curr.dist;
      
      //승객의 위치에 도착했다면 택시 상태 업데이트
      if(cr == p.dr && cc == p.dc) {
        taxi.row = cr;
        taxi.col = cc;
        taxi.fuel -= cd;
        
        //연료가 마이너스면 실패 
        if(taxi.fuel < 0) {
          taxi.fuel = -1; return false;
        
        //연료가 0이상이면 성공 - 2배 충전 
        } else {
          taxi.fuel += cd*2; return true;
        }
      }
      
      //상하좌우 탐색 
      for(int i=0; i<4; i++) {
        int nr = cr + dRow[i];
        int nc = cc + dCol[i];
        
        //이동
        if(nr>0 && nr<=n && nc>0 && nc<=n && !visited[nr][nc] && map[nr][nc] != 1) {
          visited[nr][nc] = true;
          q.offer(new Position(nr, nc, cd+1));
        }
      }
    }
    
    taxi.fuel = -1; return false;
  }
  
  private static class Taxi {
    int row, col; long fuel;
    
    public Taxi(int r, int c, long f) {
      this.row=r; this.col=c; this.fuel=f;
    }
  }
  
  private static class Passenger {
    int sr, sc, dr, dc, dist;
    
    public Passenger(int sr, int sc, int dr, int dc, int dist) {
      this.sr=sr; this.sc=sc; this.dr=dr; this.dc=dc; this.dist=dist;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Passenger)) return false;
        Passenger other = (Passenger) obj;
        return sr == other.sr && sc == other.sc && dr == other.dr && dc == other.dc;
    }
  
    @Override
    public int hashCode() {
        return Objects.hash(sr, sc, dr, dc);
    }
  }
  
  private static class Position {
    int row, col, dist;
    
    public Position(int r, int c, int d) {
      this.row=r; this.col=c; this.dist=d;
    }
  }
}