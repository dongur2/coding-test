class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for(int i=0; i<n; i++) {
            StringBuilder res = new StringBuilder();
            
            StringBuilder t1 = new StringBuilder(convertTo2(arr1[i]));
            StringBuilder t2 = new StringBuilder(convertTo2(arr2[i]));
            
            if(t1.length() < n) t1.insert(0, "0".repeat(n-t1.length()));
            if(t2.length() < n) t2.insert(0, "0".repeat(n-t2.length()));
            
            for(int j=0; j<n; j++) {
                res.append((t1.charAt(j) > '0' || t2.charAt(j) > '0')? "#":" ");
            }
            answer[i] = res.toString();
        }
        
        return answer;
    }
    
    private String convertTo2(int origin) {
            StringBuilder t = new StringBuilder();
            while(origin > 1) {
                t.append(origin % 2);
                origin /= 2;
            }
            t.append(origin);
            return t.reverse().toString();
    }
}