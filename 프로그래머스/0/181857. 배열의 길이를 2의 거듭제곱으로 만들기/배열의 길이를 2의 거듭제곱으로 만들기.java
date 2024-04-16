class Solution {
    public int[] solution(int[] arr) {
        int lengthOfArr = arr.length;
        
        int t = 0;
        int length = 1;
        while(true) {
            length = (int)Math.pow(2, t++);
            if(length >= lengthOfArr) break;
        }
        
        if(lengthOfArr == length) return arr;
        
        int[] answer = new int[length];
        for(int i=0; i<lengthOfArr; i++) answer[i] = arr[i];
        for(int i=lengthOfArr; i<length; i++) answer[i] = 0;
        
        return answer;
    }
}