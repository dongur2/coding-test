class Solution {
    public String solution(String s, int n) {
        char[] arr = s.toCharArray();

        for(int i=0; i<s.length(); i++) {
            if(arr[i] == ' ') continue;
            else if((arr[i] <= 'Z' && arr[i] + n > 'Z') 
                    || (arr[i] <= 'z' && arr[i] + n > 'z')) arr[i] = (char)(arr[i] + n - 26);
            else arr[i] = (char)(arr[i] + n);
        }
        
        return String.valueOf(arr);
    }
}