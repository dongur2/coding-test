class Solution {
    static int[] answer = new int[] {1, 1, 1, 1, 1}; //거리두기를 지키지 않으면 0
    
    static int[] dRow = new int[] {0, 1, 0, -1};
    static int[] dCol = new int[] {1, 0, -1, 0};

    public int[] solution(String[][] places) {
        //대기실 5개
        for(int i=0; i<5; i++) {
            check(places, i);
        }
        
        return answer;
    }
    
    public void check(String[][] places, int i) {
        //대기실의 모든 자리를 탐색
        for(int r=0; r<5; r++) {
            if(answer[i] == 0) break;

            for(int c=0; c<5; c++) {
                if(answer[i] == 0) break;
                
                //사람이 앉아있으면 주변 맨해튼 거리 탐색 - 규칙을 지키지 않았을 경우에 0으로 대체
                if(places[i][r].charAt(c) == 'P' && !isGood(places[i], new boolean[places.length][places[i].length], r, c, 1)) {
                    answer[i] = 0;
                }
            }
        }
    }
    
    //P - human, O - table, X - Partition
    public boolean isGood(String[] place, boolean[][] visited, int r, int c, int cnt) {
        if(cnt == 3) return true;
        
        visited[r][c] = true;
        for(int i=0; i<4; i++) {
            int nxtR = r + dRow[i];
            int nxtC = c + dCol[i];
            
            //영역 내이면서 파티션이 아닌 자리라면 진행
            if(isValid(place, visited, nxtR, nxtC)) {
                if(place[nxtR].charAt(nxtC) == 'P') return false;
                if(!isGood(place, visited, nxtR, nxtC, cnt+1)) return false;
            }
        }
        
        return true;
    }
    
    public boolean isValid(String[] place, boolean[][] visited, int r, int c) {
        return r>=0 && r<5 && c>=0 && c<5 && place[r].charAt(c) != 'X' && !visited[r][c];
    }
}