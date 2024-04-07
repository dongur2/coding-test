class Solution {
    public int solution(String A, String B) {
        StringBuilder sb = new StringBuilder(A);
        
        int cnt = 0;
        while(cnt < A.length()) {
            if(sb.toString().equals(B)) return cnt;
            else {
                sb.insert(0, sb.charAt(A.length()-1));
                sb.deleteCharAt(sb.length()-1);
                cnt++;
            }
        }
        
        return -1;
    }
}