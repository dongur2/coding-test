class Solution {
    private String myCode(String my_string) {
       StringBuilder answer = new StringBuilder(my_string);
        
        int i=0;
        while(true) {
            char ch = answer.charAt(i);
            if(answer.indexOf(ch+"") != answer.lastIndexOf(ch+"")) {
                answer.deleteCharAt(answer.lastIndexOf(ch+""));
                
            } else if (i == answer.length() - 1) {
                break;
                
            } else {
                i++;
            }
        }
        
        return answer.toString();
    }
    
    private String referTo(String my_string) {
        StringBuilder answer = new StringBuilder();
        
        for(int i=0; i<my_string.length(); i++) {
            if(answer.indexOf(my_string.charAt(i)+"") == -1) answer.append(my_string.charAt(i));
        }
        
        return answer.toString();
    }
    
    public String solution(String my_string) {
        return referTo(my_string);
    }
}