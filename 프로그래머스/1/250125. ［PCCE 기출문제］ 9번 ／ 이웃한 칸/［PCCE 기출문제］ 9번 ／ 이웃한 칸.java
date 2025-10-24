//이웃한 칸 중 같은 색으로 색칠된 칸 개수 
class Solution {
    public int solution(String[][] board, int h, int w) {
        int[] dRow = new int[] {0, 1, 0, -1};
        int[] dCol = new int[] {1, 0, -1, 0};
        
        int answer = 0;
        
        //기준 위치에서 상하좌우 확인 
        for(int i=0; i<4; i++) {
            int r = h+dRow[i];
            int c = w+dCol[i];
            
            if(r>=0 && r<board.length && c>=0 && c<board.length) {
                //색 같으면 카운트
                if(board[h][w].equals(board[r][c])) answer++;
            }
        }
        
        return answer;
    }
}