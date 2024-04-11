class Solution {
    public int solution(String myString, String pat) {
        String st = "";
        for(int i=0; i<myString.length(); i++) {
            st += myString.charAt(i) == 'A'? "B":"A";
        }        
        
        return st.contains(pat)? 1:0;
    }
}