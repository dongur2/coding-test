class Solution {
    private void useLength(char[] arr) {
        if(arr[0] >= 65) arr[0] = Character.toUpperCase(arr[0]);
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == ' ' && i+1 < arr.length) arr[i+1] = Character.toUpperCase(arr[i+1]);
        }
    }
    
    private void useBoolean(char[] arr) {
        boolean isBlank = true;
        for(int i=0; i<arr.length; i++) {
            arr[i] = (isBlank)? Character.toUpperCase(arr[i]):arr[i];
            isBlank = (arr[i] == ' ')? true:false;
        }
    }
    
    public String solution(String s) {
        char[] arr = s.toLowerCase().toCharArray();
        useBoolean(arr);        
        return String.valueOf(arr);
    }
}