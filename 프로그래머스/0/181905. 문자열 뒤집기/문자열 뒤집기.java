class Solution {
    public String solution(String my_string, int s, int e) {
        StringBuilder sent = new StringBuilder(my_string);
        String reversed = new StringBuilder(my_string.substring(s, e+1)).reverse().toString();
        return sent.replace(s, e+1, reversed).toString();
    }
}