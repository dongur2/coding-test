class Solution {
    public String[] solution(String[] names) {
        int cnt = ((double)names.length/5) % 1 > 0? (names.length/5)+1 : names.length/5;
        
        String[] answer = new String[cnt];
        for(int i=0; i<cnt; i++) {
            answer[i] = names[0 + (5 * i)];
        }
        
        return answer;
    }
}