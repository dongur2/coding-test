class Solution {
    public String solution(String s) {
        String[] str = s.split(" ");
        
        int big = Integer.MIN_VALUE;
        int small = Integer.MAX_VALUE;
        
        for(String st:str) {
            big = Math.max(big, Integer.parseInt(st));
            small = Math.min(small, Integer.parseInt(st));
        }
        
        return small + " " + big;
    }
}