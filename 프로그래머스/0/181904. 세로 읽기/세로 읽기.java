class Solution {
    private String useArr(String my_string, int m, int c) {
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
    
    private String again(String my_string, int m, int c) {
        StringBuilder answer = new StringBuilder();
        
        for(int i=(c-1); i<my_string.length(); i+=m) {
            answer.append(my_string.charAt(i)+"");
        }
        
        return answer.toString();
    }
    
    public String solution(String my_string, int m, int c) {
        return again(my_string, m, c);
    }
}