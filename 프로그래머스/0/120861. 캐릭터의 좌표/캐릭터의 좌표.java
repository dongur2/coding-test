class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int[] answer = new int[2];
        
        int MaxHeight = board[1]/2;
        int MaxWidth = board[0]/2;
        
        for(String key:keyinput) {
            if(key.equals("up") && answer[1] != MaxHeight) {
                answer[1]++;
            } else if(key.equals("down") && answer[1] != -MaxHeight) {
                answer[1]--;
            } else if(key.equals("left") && answer[0] != -MaxWidth) {
                answer[0]--;
            } else if(key.equals("right") && answer[0] != MaxWidth) {
                answer[0]++;
            } 
        }

        return answer;
    }
}