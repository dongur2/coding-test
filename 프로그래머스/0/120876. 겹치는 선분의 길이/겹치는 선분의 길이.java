class Solution {
    public int solution(int[][] lines) {
        int answer = 0;
        
        int[] line = new int[201];
        
        for(int[] i : lines) {
            int start = i[0] + 100;
            int end = i[1] + 100;
            
            for(int j=start; j<end; j++) {
                line[j]++;
            }
        }
        
        for(int i : line) {
            if(i>1) answer++;
        }
        
        return answer;
    }
}