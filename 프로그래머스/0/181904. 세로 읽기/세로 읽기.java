class Solution {
    public String solution(String my_string, int m, int c) {
        StringBuilder answer = new StringBuilder();
        
        int k = 0;
        String[][] table = new String[my_string.length()/m][m];
        for(int i=0; i<table.length; i++) {
            for(int j=0; j<m; j++) {
                table[i][j] = my_string.charAt(k++)+"";
                if(j==c-1) answer.append(table[i][j]);
            }
        }
        
        return answer.toString();
    }
}