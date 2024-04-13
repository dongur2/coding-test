class Solution {
    private String useRegex(String myString) {
        return myString.replaceAll("[a-l]", "l");
    }
    
    private String useChar(String myString) {
        StringBuilder answer = new StringBuilder();
        
        for(int i=0; i<myString.length(); i++) {
            answer.append((myString.charAt(i) > 'l')? myString.charAt(i):"l");
        }
        
        return answer.toString();
    }
    
    public String solution(String myString) {
        return useChar(myString);
    }
}