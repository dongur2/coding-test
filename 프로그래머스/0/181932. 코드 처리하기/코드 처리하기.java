class Solution {
    public String myCode(String code) {
        int mode = 0;
        String ret = "";
        
        for(int i=0; i<code.length(); i++) {
            switch(mode) {
                case 0: 
                    if(code.charAt(i) != '1') {
                        ret += (i % 2 == 0)? code.charAt(i) : ""; 
                        break;
                    }
                    else mode = 1; break;
                    
                case 1:
                    if(code.charAt(i) != '1') {
                        ret += (i % 2 > 0)? code.charAt(i) : ""; 
                        break;
                    }
                    else mode = 0; break;
            }
        }
        return ret.length() > 0 ? ret : "EMPTY";
    }
    
    public String refer(String code) {
        StringBuilder ret = new StringBuilder();
        int mode = 0;
        
        for(int i=0; i<code.length(); i++) {
            char ch = code.charAt(i);
            
            if(i % 2 == mode) {
                if(ch != '1') ret.append(ch);
            }
            
            if(ch == '1') mode = (mode == 0)? 1 : 0;
        }
        
        return ret.length() > 0 ? ret.toString() : "EMPTY";
    }
    
    public String solution(String code) {
        return refer(code);
    }
}