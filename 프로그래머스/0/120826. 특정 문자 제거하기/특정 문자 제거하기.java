class Solution {
    private String myCode(String my_string, String letter) {
        StringBuilder answer = new StringBuilder(my_string);
        
        int index = -2;
        while(index != -1) {
            index = answer.indexOf(letter);
            
            if(index != -1) {
                answer.deleteCharAt(index);
            }
        }
        
        return answer.toString();
    }
    
    private String useString(String my_string, String letter) {
        return my_string.replace(letter, "");
    }
    
    public String solution(String my_string, String letter) {
        return useString(my_string, letter);
    }
}