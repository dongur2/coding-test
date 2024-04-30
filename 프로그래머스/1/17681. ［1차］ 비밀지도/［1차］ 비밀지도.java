class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for(int i=0; i<n; i++) {
            StringBuilder res = new StringBuilder();
            
            StringBuilder t = new StringBuilder(Integer.toBinaryString(arr1[i] | arr2[i]));
            if(t.length() < n) t.insert(0, "0".repeat(n-t.length()));
            
            for(int j=0; j<n; j++) {
                res.append((t.charAt(j) > '0')? "#":" ");
            }
            answer[i] = res.toString();
        }
        return answer;  
    }
}