import java.util.List;
import java.util.ArrayList;

//n*n 체스판에서 퀸 n개를 서로 공격하지 않도록 하는 배치
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> answer = new ArrayList<>();
    
        //체스판 생성 
        List<String> board = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            sb.append(".");
        }
        for(int i=0; i<n; i++) {
            board.add(sb.toString());
        }

        place(n, answer, board, 0);

        return answer;
    }

    public void place(int n, List<List<String>> answer, List<String> board, int row) {
        //퀸을 모두 배치했을 경우 정답 리스트에 추가
        if(row == n) {
            answer.add(new ArrayList<>(board));
            return;
        }

        //각 열마다 놓을 수 있는 자리인지 탐색
        for(int c=0; c<n; c++) {
            //공격받는 자리면 무시
            if(!isValid(n, board, row, c)) continue;

            //퀸 배치 
            StringBuilder sb = new StringBuilder(board.get(row));
            sb.replace(c, c+1, "Q");
            board.set(row, sb.toString());

            //재탐색
            place(n, answer, board, row+1);

            //퀸 복구 
            sb = new StringBuilder(board.get(row));
            sb.replace(c, c+1, ".");
            board.set(row, sb.toString());
        }
    }

    public boolean isValid(int n, List<String> board, int r, int c) {
        //같은 열(위)에 퀸 있는지 확인
        for(int i=1; r-i>=0; i++) {
            if(board.get(r-i).charAt(c) == 'Q') return false;
        }

        //왼쪽 대각선
        for(int i=1; r-i>=0 && c-i>=0; i++) {
            if(board.get(r-i).charAt(c-i) == 'Q') return false;
        }

        //오른쪽 대각선
        for(int i=1; r-i>=0 && c+i<n; i++) {
            if(board.get(r-i).charAt(c+i) == 'Q') return false;
        }

        return true;
    }
}