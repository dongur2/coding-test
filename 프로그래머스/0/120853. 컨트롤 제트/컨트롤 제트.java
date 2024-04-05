class Solution {
    public int solution(String s) {
        String[] arr = s.split(" ");
        
        int answer = 0;
        
        int i = 0;
        while(i < arr.length) {
            if(i+1 < arr.length && arr[i+1].equals("Z")) {
                i+=2;
            } else {
                answer += Integer.parseInt(arr[i]);
                i++;
            }
        }    
        
        return answer;
    }
}