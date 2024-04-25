class Solution {
    public int solution(String t, String p) {
        int size = p.length();
        
        int cnt = 0;
        for(int i=0; i <= t.length()-size; i++) {
            long num = Long.parseLong(t.substring(i, i+size));
            long pNum = Long.parseLong(p);
            
            if(num <= pNum) cnt++;
        }
        return cnt;
    }
}