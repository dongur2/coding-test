import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/*
    서로 같은 (i번째 행, j번째 열)의 개수 
 */
class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length; //n*n
        int cnt = 0;
        
        //행:등장 횟수 
        Map<String, Integer> map = new HashMap<>();
        for(int r=0; r<n; r++) {
            StringBuilder row = new StringBuilder();

            for(int c=0; c<n; c++) {
                row.append(grid[r][c]+",");
            }

            map.put(row.toString(), map.getOrDefault(row.toString(), 0)+1);
        }

        //열: 맵에 저장된 행과 일치하는 값이 있다면 - 값을 카운트에 더함
        for(int c=0; c<n; c++) {
            StringBuilder col = new StringBuilder();

            for(int r=0; r<n; r++) {
                col.append(grid[r][c]+",");
            }

            if(map.containsKey(col.toString())) cnt += map.get(col.toString());
        }
 
        return cnt;
    }

    private int sol1(int[][] grid) {
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