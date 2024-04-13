class Solution {
    public int solution(String binomial) {
        int answer = 0;
        switch(binomial.split(" ")[1]) {
            case "+": answer = Integer.parseInt(binomial.split(" ")[0]) + Integer.parseInt(binomial.split(" ")[2]); break;
            case "-": answer = Integer.parseInt(binomial.split(" ")[0]) - Integer.parseInt(binomial.split(" ")[2]); break;
            case "*": answer = Integer.parseInt(binomial.split(" ")[0]) * Integer.parseInt(binomial.split(" ")[2]); break;
        }
        return answer;
    }
}