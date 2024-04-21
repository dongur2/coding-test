class Solution {
    public boolean solution(String s) {
        if(s.length()==4 || s.length()==6) return s.equals(s.replaceAll("[^0-9]", ""));
        return false;
    }
}