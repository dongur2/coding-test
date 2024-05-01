class Solution {
    public String solution(int[] food) {
        StringBuilder answer = new StringBuilder();
        
        for(int i=1; i<food.length; i++) {
            answer.append(String.valueOf(i).repeat(food[i]/2));
        }
        
        StringBuilder reversed = new StringBuilder(answer.toString());
        reversed.reverse();
        answer.append("0").append(reversed.toString());
        
        return answer.toString();
    }
}