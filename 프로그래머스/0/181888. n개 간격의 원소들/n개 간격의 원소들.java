class Solution {
    public int[] solution(int[] num_list, int n) {
        int[] answer = new int[(double)num_list.length/n % 1 > 0 ? 
                               (num_list.length/n)+1 : num_list.length/n];
        
        for(int i=0; i<answer.length; i++) {
            answer[i] = num_list[0 + (n * i)];
        }
        
        return answer;
    }
}