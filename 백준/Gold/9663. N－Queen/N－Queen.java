import java.util.Scanner;

class Main {
    static int answer = 0;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] board = new int[n][n];
        
        dfs(n, board, 0);
        
        System.out.println(answer);    
    }

    static void dfs(int n, int[][] board, int row) {
        //퀸 모두 배치 완료
        if(row == n) {
            answer++; return;
        }

        for(int col=0; col<n; col++) {
            //열마다 퀸 배치해보기
            //위, 왼쪽 대각선, 오른쪽 대각선에 배치된 퀸이 없으면 배치
            if(!isValid(board, row, col)) continue;
            
            board[row][col] = 1;
            dfs(n, board, row+1);
            board[row][col] = 0;
        }
    }

    static boolean isValid(int[][] board, int row, int col) {
        //위
        for(int x=row-1; x>=0; x--) {
            if(board[x][col] == 1) return false;
        }

        //왼쪽 대각선 위
        for(int x=row-1, y=col-1; x>=0 && y>=0; x--, y--) {
            if(board[x][y] == 1) return false;
        }

        //오른쪽 대각선 위
        for(int x=row-1, y=col+1; x>=0 && y<board.length; x--, y++) {
            if(board[x][y] == 1) return false;
        }

        return true;
    }
}