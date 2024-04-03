class Solution {
    public String solution(String my_string) {
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
        
        return answer.toString().replaceAll("[0-9]", "");
    }
}