class Solution {
    public int solution(String[] babbling) {
        int count = 0;
        
        String[] table = {"aya", "ye", "woo", "ma"};
        for(String t:table) {
            for(int i=0; i<babbling.length; i++) {
                if(babbling[i].contains(t)) {
                    babbling[i] = babbling[i].replace(t, "0");
                }
            }
        }
        
        for(String b:babbling) {
            if(b.replace("0", "").length() == 0) count++;
        }
        
        return count;
    }
}