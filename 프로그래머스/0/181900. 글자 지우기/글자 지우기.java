class Solution {
    public String solution(String my_string, int[] indices) {
        char[] answer = my_string.toCharArray();
        for(int i : indices) {
            answer[i] = ' ';
        }
        return String.valueOf(answer).replace(" ", "");
    }
}