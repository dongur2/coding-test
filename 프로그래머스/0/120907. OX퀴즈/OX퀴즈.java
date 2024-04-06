class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        
        for(int i=0; i<quiz.length; i++) {
            int num1 = 0;
            int num2 = 0;
            int res = 0;
            
            String[] s = quiz[i].split(" ");
            num1 = Integer.parseInt(s[0]);
            num2 = Integer.parseInt(s[2]);
            res = Integer.parseInt(s[4]);

            if(s[1].equals("+")) {
                answer[i] = num1+num2==res? "O":"X";
            } else {
                answer[i] = num1-num2==res? "O":"X";
            }
        }
        return answer;
    }
}