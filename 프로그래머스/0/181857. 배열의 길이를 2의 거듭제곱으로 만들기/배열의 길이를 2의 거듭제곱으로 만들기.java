class Solution {
    public int[] solution(int[] arr) {
        int lengthOfArr = arr.length;
        
        int t = 0;
        int length = 1;
        while(true) {
            length = (int)Math.pow(2, t++);
            if(length >= lengthOfArr) break;
        }
        
        int[] answer = new int[length];
        for(int i=0; i<lengthOfArr; i++) answer[i] = arr[i];
        
        return answer;
    }
}