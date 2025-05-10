import java.util.Arrays;
/*
주어진 쿼리 (그리드 영역 시작점, 끝점) 테두리만 시계방향으로 회전 - 그 중 가장 작은 수 추출
*/
class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        //보드판 생성
        int[][] origin = new int[rows+1][columns+1]; //원래 데이터
        int[][] board = new int[rows+1][columns+1]; //회전에 실사용
        
        int num = 0;
        for(int i=1; i<rows+1; i++) {
            for(int j=1; j<columns+1; j++) {
                num++;
                origin[i][j] = num; board[i][j] = num;
            }
        }
        
        //총 그리드 수 10^4
        int n = queries.length;
        
        int[] answer = new int[n];
        
        //주어진 그리드별로 회전
        //회전은 각각 따로가 아니고, 순서대로 이어져 데이터 영향 있음
        for(int q=0; q<n; q++) {
            int[] query = queries[q];
            
            int sr = query[0]; int sc = query[1]; //시작
            int er = query[2]; int ec = query[3]; //끝
            
            int minimum = Integer.MAX_VALUE;
            
            //위쪽 테두리 - 시작행 고정, 시작열에서 끝열까지 
            for(int c=sc; c<ec; c++) {
                board[sr][c+1] = origin[sr][c];
                minimum = Math.min(minimum, origin[sr][c]);
            }
            //오른쪽 테두리 - 끝열 고정
            for(int r=sr; r<er; r++) {
                board[r+1][ec] = origin[r][ec];
                minimum = Math.min(minimum, origin[r][ec]);
            }
            //아래쪽 테두리 - 끝행 고정
            for(int c=ec; c>sc; c--) {
                board[er][c-1] = origin[er][c];
                minimum = Math.min(minimum, origin[er][c]);
            }
            //왼쪽 테두리 - 시작열 고정
            for(int r=er; r>sr; r--) {
                board[r-1][sc] = origin[r][sc];
                minimum = Math.min(minimum, origin[r][sc]);
            }
        
            //가장 작은 수를 저장
            answer[q] = minimum;
            
            //회전 끝났으면 회전된 배열을 origin에 저장해서 업데이트
            //회전한 부분만 업데이트
            for(int i=sr; i<=er; i++) {
                origin[i] = Arrays.copyOf(board[i], board[i].length);
            }
        }
        
        return answer;
    }
}