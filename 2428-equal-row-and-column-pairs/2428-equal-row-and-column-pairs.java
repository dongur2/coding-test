import java.util.List;
import java.util.ArrayList;
/*
    서로 같은 (i번째 행, j번째 열)의 개수 
 */
class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length; //n*n

        int cnt = 0;

        //행을 기준으로 열 차례로 확인 
        for(int r=0; r<n; r++) {
            //현재 행 
            List<Integer> row = new ArrayList<>();
            for(int c=0; c<n; c++) {
                row.add(grid[r][c]);
            }

            //열 차례로 확인 
            for(int c=0; c<n; c++) {
                List<Integer> col = new ArrayList<>();
                for(int cr=0; cr<n; cr++) {
                    col.add(grid[cr][c]);
                }

                //행과 같으면 카운트 
                if(row.equals(col)) cnt++;
            }
        }

        return cnt;
    }
}