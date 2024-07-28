import java.util.*;

class Solution {
    int[] rowArr = {0, 1, 0, -1};
    int[] colArr = {1, 0, -1, 0};
    
    int[] answer;
    
    public int[] solution(String[][] places) {
        answer = new int[5];
        
        //거리두기를 충족하는 경우 배열값을 1로 변경
        for(int i=0; i<5; i++) {
            if(isProperDistance(places[i])) answer[i] = 1;
        }
       
        return answer;
    }
    
    private boolean isProperDistance(String[] place) {
        //전체 노드를 순차적으로 순회:
        for(int row=0; row<5; row++) {
            for(int col=0; col<5; col++) {
                //새로운 응시자(P)가 있는 노드를 발견할 경우:
                if(place[row].charAt(col) == 'P') {
                    //맨해튼 거리 탐색 - 충족하지 않으면 즉시 false 리턴
                    if(!isNearEmpty(place, row, col, new boolean[5][5], 0)) return false;
                }
            }
        }
        return true;
    }
    
    private boolean isNearEmpty(String[] place, int row, int col, boolean[][] visited, int cnt) {
        if(cnt == 2) return true; 
        
        //상하좌우 탐색
        visited[row][col] = true;
        for(int i=0; i<4; i++) {
            int nxtR = row + rowArr[i];
            int nxtC = col + colArr[i];

            //다음 노드에 파티션이 있지 않고, 유효한 범위면 탐색:
            if(isValid(nxtR, nxtC) && place[nxtR].charAt(nxtC) != 'X') {
                //방문하지 않았던 노드라면:
                if(!visited[nxtR][nxtC]) {
                    //다른 응시자가 있으면 거리두기 미준수
                    if(place[nxtR].charAt(nxtC) == 'P') return false;
                    //없으면 한 단계 더 탐색
                    if(!isNearEmpty(place, nxtR, nxtC, visited, cnt+1)) return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(int row, int col) {
        return row >= 0 && row < 5 && col >= 0 && col < 5;
    }

}