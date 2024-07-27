import java.util.*;

class Solution {
    int[] rowArr = {0, 1, 0, -1};
    int[] colArr = {1, 0, -1, 0};
    
    int rowLen, colLen;
    int[] start, lever, exit;
    String[] mapCopied;
    boolean[][] checked;
    
    public int solution(String[] maps) {
        mapCopied = maps;
        rowLen = maps.length; 
        colLen = maps[0].length();
        checked = new boolean[rowLen][colLen];
        
        return findTheShortestEscape();
    }
    
    private int findTheShortestEscape() {
        //시작점/레버/출구 위치를 찾아서 변수에 저장
        findPoints();
        
        //탈출에 필요한 최단 시간 탐색
        //BFS: 시작점에서 출발해 레버에 도착 & 레버에서 출발해 출구에 도착하는 최소 거리(시간) 탐색 - 레버에 도착했을 경우에만 진행
        //-> 시작위치(start, lever), 도착위치(lever, exit) 
        return findRouteTo();
    }
    
    private void findPoints() { //O(NM)?
        for(int i=0; i<rowLen; i++) {
            if(mapCopied[i].contains("S")) start = new int[]{i, mapCopied[i].indexOf("S")};
            if(mapCopied[i].contains("E")) exit = new int[]{i, mapCopied[i].indexOf("E")};
            if(mapCopied[i].contains("L")) lever = new int[]{i, mapCopied[i].indexOf("L")};
        }
    }
    
    private int findRouteTo() {
        Queue<int[]> waiting = new ArrayDeque<>();
        checked[start[0]][start[1]] = true;
        waiting.offer(new int[]{start[0], start[1], 0});
        
        boolean isLeverOn = false;
        
        while(!waiting.isEmpty()) {
            int[] cur = waiting.poll(); //현재 방문 노드
            int curR = cur[0];
            int curC = cur[1];
            int curCnt = cur[2];
            
            for(int i=0; i<4; i++) { //방문한 노드의 상하좌우를 탐색
                int nxtR = curR + rowArr[i];
                int nxtC = curC + colArr[i];
                
                //이동할 수 있는 인접 노드 중에 레버가 존재하면 레버 외 예약 노드 비움
                if(nxtR == lever[0] && nxtC == lever[1] && !checked[nxtR][nxtC]) {
                    isLeverOn = true;
                    
                    checked = new boolean[rowLen][colLen]; //왔던 길도 갈 수 있도록 방문 체크 초기화
                    waiting.clear(); //레버에서 시작하도록 나머지 대기열 비움
                    waiting.offer(new int[]{nxtR, nxtC, curCnt+1});
                    checked[nxtR][nxtC] = true; 
                    break;
                    
                } else if(nxtR == exit[0] && nxtC == exit[1] && isLeverOn) return curCnt + 1;
                
                if(isValid(nxtR, nxtC)) {
                    waiting.offer(new int[]{nxtR, nxtC, curCnt+1});
                    checked[nxtR][nxtC] = true;
                }
            }
        }
        return -1;
    }
    
    private boolean isValid(int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen && mapCopied[row].charAt(col) != 'X' && !checked[row][col];
    }
    
}