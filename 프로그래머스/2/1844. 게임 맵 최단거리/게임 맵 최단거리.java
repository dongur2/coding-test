import java.util.*;

class Solution {
    //4방향 이동
    int[] rowArr = {0, 1, 0, -1};
    int[] colArr = {1, 0, -1, 0};
    
    int rowLen, colLen;
    boolean[][] checked;
    
    public int solution(int[][] maps) {
        rowLen = maps.length;
        colLen = maps[0].length;
        checked = new boolean[rowLen][colLen];
        
        return bfs(maps);
    }
    
    private int bfs(int[][] maps) {
        //시작 위치(0, 0)를 방문 체크하고 대기열에 카운트와 함께 추가
        Queue<int[]> waiting = new ArrayDeque<>();
        checked[0][0] = true;
        waiting.offer(new int[]{0, 0, 1});
        
        //대기열이 소진될 때까지 반복:
        while(!waiting.isEmpty()) {
	        //대기열에서 가져온 노드에 방문
            int[] cur = waiting.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curCnt = cur[2];
            
            //인접 노드 4방향을 탐색하여 
            for(int i=0; i<4; i++) {
                int nxtR = curR + rowArr[i];
                int nxtC = curC + colArr[i];
                
                //인접 노드 중에 상대 진영(N-1, M-1)이 있다면: 즉시 카운트를 추가하여 리턴
                if(nxtR == rowLen - 1 && nxtC == colLen - 1) return curCnt + 1;
                
                //아직 방문하지 않은 이동 가능한 노드가 있다면: 방문 체크 & 대기열에 카운트를 추가하여 저장
                if(isValid(maps, nxtR, nxtC)) {
                    checked[nxtR][nxtC] = true;
                    waiting.offer(new int[]{nxtR, nxtC, curCnt + 1});
                }
            }
        }
        return -1;
    }
    
    private boolean isValid(int[][] maps, int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen && maps[row][col] == 1 && !checked[row][col];
    }
}