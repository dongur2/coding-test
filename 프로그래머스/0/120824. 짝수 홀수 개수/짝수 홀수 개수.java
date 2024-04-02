class Solution {
    private void myCode(int[] num_list, int[] answer) {
        for(int number : num_list) {
            if(number % 2 == 0) {
                answer[0]++;
            } else {
                answer[1]++;
            }
        }
    }
    
    private void referTo(int[] num_list, int[] answer) {
        for(int number : num_list) {
            answer[number % 2]++;
        }
    }
    
    public int[] solution(int[] num_list) {
        int[] answer = new int[2];
        referTo(num_list, answer);
        return answer;
    }
}