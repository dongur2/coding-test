class Solution {
    public int[] solution(long n) {
        String[] num = String.valueOf(n).split("");
        
        int[] answer = new int[num.length];
        for(int i=0, j=num.length-1; i<num.length; i++, j--) {
            answer[i] = Integer.parseInt(num[j]);
        }
        return answer;
    }
}