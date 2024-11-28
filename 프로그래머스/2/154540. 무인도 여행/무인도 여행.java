import java.util.*;

class Solution {
    /*
    Q. 각 섬에서 최대 머무를 수 있는 일수를 담은 배열 리턴 (섬이 없으면 -1) :: 오름차순 배열
    - 땅은 상하좌우로 연결
    - X = 바다 
    - 1~9 = 모두 합한 값이 해당 섬에서 머무를 수 있는 일수
    */

    int maxRow = 0, maxCol = 0;
    
    int[] rowArr = new int[] {0, 1, 0, -1};
    int[] colArr = new int[] {1, 0, -1, 0};
    
    boolean[][] visited;
    List<Integer> days = new ArrayList<>();
    
    int cnt = 0;
    
    public int[] solution(String[] maps) {
        maxRow = maps.length;
        maxCol = maps[0].length();
        visited = new boolean[maxRow][maxCol];
        
        //섬 탐색
        for(int r=0; r<maxRow; r++) {
            for(int c=0; c<maxCol; c++) {
                if(maps[r].charAt(c) != 'X' && !visited[r][c]) {
                    cnt = 0;
                    dfs(maps, r, c);
                    days.add(cnt); //인접노드 순회가 끊기고 돌아오면 일수 저장
                }
            }
        }
        
        if(days.isEmpty()) return new int[]{-1};
        
        // Collections.sort(days);
        // int[] answer = new int[days.size()];
        // for(int i=0; i<days.size(); i++) {
        //     answer[i] = days.get(i);
        // }
        // return answer;
        return days.stream().sorted().mapToInt(d -> d).toArray();
    }
    
    void dfs(String[] maps, int row, int col) {
        visited[row][col] = true;
        cnt += Character.getNumericValue(maps[row].charAt(col));
        
        //상하좌우 탐색
        for(int i=0; i<4; i++) {
            int nxtR = row + rowArr[i];
            int nxtC = col + colArr[i];
            
            if(isValid(nxtR, nxtC, maps)) {
                dfs(maps, nxtR, nxtC);
            }
        }
    }
    
    boolean isValid(int r, int c, String[] maps) {
        return r >= 0 && r < maxRow && c >= 0 && c < maxCol && maps[r].charAt(c) != 'X' && !visited[r][c];
    }
}