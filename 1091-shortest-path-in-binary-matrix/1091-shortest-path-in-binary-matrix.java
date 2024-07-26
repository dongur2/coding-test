import java.util.*;

class Solution {
    //8방향 이동
    int[] rowArr = {-1, -1, 0, 1, 1, 1, 0, -1};
    int[] colArr = {0, 1, 1, 1, 0, -1, -1, -1};

    int rowLen, colLen;
    boolean[][] checked;

    public int shortestPathBinaryMatrix(int[][] grid) {
        rowLen = grid.length; colLen = grid[0].length;
        checked = new boolean[rowLen][colLen];

        //고정된 처음 시작 노드나 마지막 노드가 유효한 노드가 아닐 경우(1) 즉시 불가능(-1) 리턴
        if(grid[0][0] != 0 || grid[rowLen-1][colLen-1] != 0) return -1;
        //주어진 배열이 원소(0) 1개로 이루어진 배열이라면 즉시 1 리턴 
        else if(rowLen == 1 && colLen == 1) return 1;

        return bfs(grid, 0, 0);
    }

    private int bfs(int[][] grid, int row, int col) {
        //시작 노드를 대기열에 추가 & 방문 체크
        Queue<int[]> waiting = new ArrayDeque<>();
        waiting.offer(new int[]{row, col, 1});
        checked[row][col] = true;

        //대기열이 소진될 때까지 반복:
        while(!waiting.isEmpty()) {
            //대기열의 최우선 노드를 꺼내 방문
            int[] cur = waiting.poll();
            int curR = cur[0];
            int curC = cur[1];
            int curCnt = cur[2];

            //현재 노드에서 8방향으로 탐색:
            for(int i=0; i<8; i++) {
                int nxtR = curR + rowArr[i];
                int nxtC = curC + colArr[i];

                //만약 위치가 오른쪽 끝이라면 즉시 카운트+1을 리턴
                if(nxtR == rowLen-1 && nxtC == colLen-1) return curCnt + 1;    

                //방문하지 않은 이동 가능한 노드가 있는 경우:
                else if(isValid(grid, nxtR, nxtC)) {
                    //방문 체크 & 카운트 & 대기열에 추가
                    checked[nxtR][nxtC] = true; 
                    waiting.offer(new int[]{nxtR, nxtC, curCnt+1});
                }
            }
        }
        return -1;
    }

    private boolean isValid(int[][] grid, int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen && grid[row][col] == 0 && !checked[row][col];
    }
}