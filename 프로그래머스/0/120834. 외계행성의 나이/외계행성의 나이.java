class Solution {
    public String solution(int age) {
        StringBuilder answer = new StringBuilder();
        
        while(age > 0) {
            answer.insert(0, (char)(age % 10 + 97));
            age /= 10;
        }
        
        return answer.toString();
    }
}