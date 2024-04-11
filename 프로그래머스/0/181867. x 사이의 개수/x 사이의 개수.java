class Solution {
    public int[] solution(String myString) {
        String[] arr = myString.split("x");
        
        int count = myString.charAt(myString.length()-1) == 'x'? arr.length+1 : arr.length;
        
        int[] answer = new int[count];
        for(int i=0; i<arr.length; i++) {
            answer[i] = arr[i].length();
        }
        
        return answer;
    }
}