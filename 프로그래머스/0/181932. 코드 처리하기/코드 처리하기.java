class Solution {
    public String solution(String code) {
        // 시작할 때 mode=0
        // ret이 빈 문자열이면 EMPTY 리턴
        // mode 0: code[i]!=1 - i가 짝수일 때만 ret += code[i] , code[i]=1 - mode 1
        // mode 1: code[i]!=1 - i가 홀수일 때만 ret += code[i] , code[i]=1 - mode 0
        
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
}