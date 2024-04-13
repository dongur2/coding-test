class Solution {
    public int[] solution(String my_string) {
        int[] answer = new int[52];
        
        for(int i=0; i<my_string.length(); i++) {
            char ch = my_string.charAt(i);
            int idx = ch < 'a'? (ch - 'A') : (ch - 71);
            answer[idx]++;
        }
        
        return answer;
    }
}