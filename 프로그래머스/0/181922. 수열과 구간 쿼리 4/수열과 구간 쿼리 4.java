class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        for(int[] query:queries) {
            for(int i=0; i<arr.length; i++) {
                arr[i] += (query[0] <= i && i <= query[1] && i % query[2] == 0) ? 1:0; 
            }
        }
        return arr;
    }
}