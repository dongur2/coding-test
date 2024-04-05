class Solution {
    public String[] solution(String my_str, int n) {
        int idx = (my_str.length()%n==0)? my_str.length()/n:my_str.length()/n+1;
        String[] answer = new String[idx];
        
        for(int i=0, j=0; j<answer.length; i+=n, j++) {
            if(i+n > my_str.length()) {
                answer[j]=my_str.substring(i);
            } else {
                answer[j]=my_str.substring(i, i+n);
            }
        }
        
        return answer;
    }
}