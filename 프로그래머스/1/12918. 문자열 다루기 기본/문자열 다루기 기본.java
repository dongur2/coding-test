class Solution {
    public boolean solution(String s) {
        String replaced = s.replaceAll("[^0-9]", "");
        if(!s.equals(replaced)) return false;
        return replaced.length()==4 || replaced.length()==6;
    }
}