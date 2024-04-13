class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int[] answer = new int[queries.length];
        
        for(int q=0; q<queries.length; q++) {
            int min = 1000001;
            
            // s = queries[q][0], e = queries[q][1], k = queries[q][2]
            for(int i=queries[q][1]; i>=queries[q][0]; i--) {
                min = (arr[i] > queries[q][2] && arr[i] < min)? arr[i] : min;
            }
            answer[q] = (min < 1000001)? min : -1;
        }
        return answer;
    }
}