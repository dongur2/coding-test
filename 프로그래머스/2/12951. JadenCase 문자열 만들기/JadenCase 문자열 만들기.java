class Solution {
    public String solution(String s) {
        char[] arr = s.toLowerCase().toCharArray();
        
        if(arr[0] >= 65) arr[0] = Character.toUpperCase(arr[0]);
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == ' ' && i+1 < arr.length) arr[i+1] = Character.toUpperCase(arr[i+1]);
        }
        
        return String.valueOf(arr);
    }
}