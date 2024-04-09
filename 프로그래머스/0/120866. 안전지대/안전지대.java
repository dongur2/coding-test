import java.util.*;

class Solution {
    public int solution(int[][] board) {
        List<Integer> dx = new ArrayList<>();
        List<Integer> dy = new ArrayList<>();
        
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j] == 1) {
                    dx.add(i); 
                    dy.add(j);
                }
            }
        }
        
        for(int k = 0; k < dx.size(); k++) {
            for(int i= dx.get(k)-1; i<= dx.get(k)+1; i++) {
                for(int j= dy.get(k)-1; j<= dy.get(k)+1; j++) {
                    if(i<0 || i>=board.length || j<0 || j>=board[0].length) continue;
                    if(board[i][j] != 1) board[i][j]=1;
                }
            }
        }
        
        int answer = 0;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j] == 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}