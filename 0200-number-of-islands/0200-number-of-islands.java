import java.util.*;

class Solution {
    //상하좌우로 좌표이동
    int[] rowArr = {0, 1, 0, -1};
    int[] colArr = {1, 0, -1, 0};

    int rowLength;
    int colLength;

    char[][] map;
    boolean[][] checked;

    public int numIslands(char[][] grid) {
        rowLength = grid.length;
        colLength = grid[0].length;
        map = grid; //편리하게 사용 위해 맵 복사
        checked = new boolean[rowLength][colLength];

        return findIslands();
    }

    private int findIslands() {
        int cnt = 0;

        //모든 노드를 순차적으로 순회:
        for(int row=0; row < rowLength; row++) {
            for(int col=0; col < colLength; col++) {
                //방문하지 않았던 육지에 도착:
                if(map[row][col] == '1' && !checked[row][col]) {
    	    	    //도착한 "섬"의 면적을 모두 탐색하여 방문 여부 체크 & 섬 개수 카운트
                    checkIslandArea(row, col); cnt++;
                }
            }
        }
        return cnt;
    }

    private void checkIslandArea(int row, int col) {
        //탐색 시작 위치를 대기열에 추가 & 방문 체크
        Queue<int[]> waiting = new ArrayDeque<>();
        waiting.offer(new int[]{row, col});
        checked[row][col] = true;

        //대기열이 소진될 때까지:
        while(!waiting.isEmpty()) {
            //대기열에서 탐색 시작 위치를 획득
            int[] cur = waiting.poll();
            int curR = cur[0];
            int curC = cur[1];

            //현재 위치의 상하좌우 좌표를 탐색:
            for(int i=0; i<4; i++) {
                int nxtR = curR + rowArr[i];
                int nxtC = curC + colArr[i];

                //맵 밖으로 나가지 않고, 방문하지 않은 육지가 있을 경우: 
                if(isValid(nxtR, nxtC)) {
                    //방문 여부 체크 & 그 육지에서 다시 상하좌우를 재탐색
                    checked[nxtR][nxtC] = true;
                    checkIslandArea(nxtR, nxtC);
                }
            }
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < rowLength && col >= 0 && col < colLength && map[row][col] == '1' && !checked[row][col];
    }
}