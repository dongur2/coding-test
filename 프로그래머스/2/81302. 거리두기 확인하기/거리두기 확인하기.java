import java.util.*;

/*
Q. 각 대기실별 거리두기 준수 여부 확인

- 맨해튼 거리 확인
1. 사람 주변 상하좌우 확인
1-1. 확인한 자리에 파티션(X)이 있으면 그 위치에서의 탐색 중지 (준수)
1-2. 확인한 자리에 사람(P)이 있으면 거리두기 규칙 준수 탐색을 중지하고 false 리턴 (미준수)
1-3. 확인한 자리에 빈 테이블(O)이 있으면 그 위치에서 탐색 시작 (2차 탐색)

2. 빈 테이블이 있는 위치에서 상하좌우 탐색 -> 사람이 있으면 false 리턴
*/
class Solution {
    int[] rowArr = {0, 1, 0, -1};
    int[] colArr = {1, 0, -1, 0};
    
    int[] answer;
    
    public int[] solution(String[][] places) {
        answer = new int[places.length];
        checkPlacesWithRule(places);
        return answer;
    }
    
    void checkPlacesWithRule(String[][] places) {
        for(int i=0; i<places.length; i++) {
            answer[i] = doesFllowRule(places[i]) ? 1 : 0;
        }
    }
    
    boolean doesFllowRule(String[] place) {
        for(int r=0; r<place.length; r++) {
            for(int c=0; c<place[0].length(); c++) {
                if(place[r].charAt(c) == 'P' && !isValidDistance(place, r, c, new boolean[place.length][place[0].length()], 1)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    //dfs
    boolean isValidDistance(String[] place, int row, int col, boolean[][] visited, int phase) {
        visited[row][col] = true;
        
        //base case: 3번째 루프면 반복 중지
        if(phase == 3) return true;
        
        //loop: 빈테이블일 경우 재탐색
        for(int i=0; i<4; i++) {
            int r = row + rowArr[i];
            int c = col + colArr[i];
            
            if(r < 0 || r >= 5 || c < 0 || c >= 5 || visited[r][c] || place[r].charAt(c) == 'X') continue;
            
            if(place[r].charAt(c) == 'P') return false;
            if(!isValidDistance(place, r, c, visited, phase+1)) return false;
        }
        return true;
    }
}