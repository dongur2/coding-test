class Solution {
    public int[] solution(String s) {
        int converted = 0;
        
        int zero = 0;
        int one = 0;
        
        StringBuilder res = new StringBuilder(s);
        while(!res.toString().equals("1")) {
            converted++;
            one = 0;
            
            for(int i=0; i<res.length(); i++) {
                if(res.charAt(i) == '0') zero++;
                else one++;
            }
            
            res = new StringBuilder(Integer.toBinaryString(one));
        }
        
        return new int[] {converted, zero};
    }
}