import java.util.*;

class Solution {
    /*
    ** 미로를 빠져나가기 위해 필요한 '최소' 시간 반환
    - 빠져나갈 수 없으면 -1 반환
    - 규칙: 레버를 당긴 후에 출구로 탈출할 수 있음
    - X인 칸은 이동할 수 없음
    
    최소 시간을 구해야 하므로 BFS로 접근
    
    1. 시작/레버/출구 위치를 찾기
    2. 시작 위치에서 탐색 -> 레버로 가는 최소 루트 탐색
    3. 레버에 도착 후 출구로 가는 최소 루트 탐색
    */
    
    int[] rowArr = {0, 1, 0, -1};
    int[] colArr = {1, 0, -1, 0};
    
    int rowLen, colLen;
    Map<Character, int[]> checkpoints; //위치 저장
    boolean[][] visited; // 시작->레버, 레버->출구 방문 체크 별개
    
    public int solution(String[] maps) {
        rowLen = maps.length;
        colLen = maps[0].length();
        checkpoints = new HashMap<>();
        visited = new boolean[rowLen][colLen];
        
        return countMinimum(maps);
    }
    
    int countMinimum(String[] maps) {
        boolean isLeverOn = false;
        
        //시작-레버-출구 위치 찾기
        findCheckpoints(maps);
        
        //시작->레버->출구까지 탐색
        Queue<int[]> q = new ArrayDeque<>();
        
        //start
        int[] startPoint = checkpoints.get('S');
        q.offer(new int[]{startPoint[0], startPoint[1], 0}); //[row, col, count]
        visited[startPoint[0]][startPoint[1]] = true;
        
        while(!q.isEmpty()) {
            //cur
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curCnt = cur[2];
            
            //next node
            for(int i=0; i<4; i++) {
                int nextR = curR + rowArr[i];
                int nextC = curC + colArr[i];
                
                if(isValid(maps, nextR, nextC)) {
                    q.offer(new int[]{nextR, nextC, curCnt+1});
                    visited[nextR][nextC] = true;
                
                    //레버에 도착하면 대기열, 방문 체크 초기화 & 레버만 체크하고 출구까지 탐색 시작
                    if(nextR == checkpoints.get('L')[0] && nextC == checkpoints.get('L')[1]) {
                        q.clear(); visited = new boolean[rowLen][colLen]; //initialize
                        isLeverOn = true; //lever on
                        visited[nextR][nextC] = true; q.offer(new int[]{nextR, nextC, curCnt+1}); //add q
                        break;
                    }
                    
                    //레버를 당긴 후 출구 도착
                    if(isLeverOn && nextR == checkpoints.get('E')[0] && nextC == checkpoints.get('E')[1]) return curCnt + 1;
                }
            }
        }
        return -1;
    }
    
    void findCheckpoints(String[] maps) {
        for(int r=0; r<rowLen; r++){
            if(maps[r].indexOf('S') > -1) checkpoints.put('S', new int[]{r, maps[r].indexOf('S')});
            if(maps[r].indexOf('L') > -1) checkpoints.put('L', new int[]{r, maps[r].indexOf('L')});
            if(maps[r].indexOf('E') > -1) checkpoints.put('E', new int[]{r, maps[r].indexOf('E')});
        }
    }
    
    boolean isValid(String[] maps, int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen && !visited[row][col] && maps[row].charAt(col) != 'X';
    }
}