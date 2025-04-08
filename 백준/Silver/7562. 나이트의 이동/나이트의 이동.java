import java.util.*;
import java.io.*;

//나이트가 목적지로 이동할 수 있는 최소 이동 수 
public class Main {
    //나이트가 한 번에 이동할 수 있는 좌표 
    static int[] dRow = {2, 1, -1, -2, -2, -1, 1, 2};
    static int[] dCol = {1, 2, 2, 1, -1, -2, -2, -1}; 
    
    static int[][] map;
    static boolean[][] visited;
    
    static int startRow = -1, startCol = -1;
    static int endRow = -1, endCol = -1;
    
    static List<Integer> answer = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      int t = Integer.parseInt(br.readLine());
      
      for(int i=0; i<t; i++) {
        int l = Integer.parseInt(br.readLine()); //한 변의 길이
        
        //맵 생성 
        map = new int[l][l]; //0번~l-1번
        visited = new boolean[l][l];
        
        //현재 좌표 
        String[] input = br.readLine().split(" ");
        startRow = Integer.parseInt(input[0]);
        startCol = Integer.parseInt(input[1]);
        
        //목적지
        input = br.readLine().split(" ");
        endRow = Integer.parseInt(input[0]);
        endCol = Integer.parseInt(input[1]);
        
        //최소 이동 수를 구해야하므로 bfs
        int moves = bfs();
        answer.add(moves);
      }
      
      //출력
      answer.forEach(a->System.out.println(a));
    }
    
    public static int bfs() {
      //세팅
      Deque<int[]> q = new ArrayDeque<>();
      visited[startRow][startCol] = true;
      q.offer(new int[]{startRow, startCol, 0});
      
      //큐
      while(!q.isEmpty()) {
        int[] curr = q.poll();
        int row = curr[0];
        int col = curr[1];
        int moves = curr[2];
        
        //목적지에 도착했으면 리턴
        if(row == endRow && col == endCol) return moves;
        
        for(int i=0; i<8; i++) {
          int nxtR = row + dRow[i];
          int nxtC = col + dCol[i];
          
          if(isValid(nxtR, nxtC)) {
            visited[nxtR][nxtC] = true;
            q.offer(new int[]{nxtR, nxtC, moves+1});
          }
        }
      }
      return -1;
    }
    
    public static boolean isValid(int r, int c) {
      return r >= 0 && r < map.length && c >= 0 && c < map[0].length && !visited[r][c];
    }
}