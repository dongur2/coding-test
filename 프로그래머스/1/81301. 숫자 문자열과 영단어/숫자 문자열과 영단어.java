class Solution {
    String[] arr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    
    public int solution(String s) {
        StringBuilder answer = new StringBuilder();
        StringBuilder res = new StringBuilder();
        
        for(char c:s.toCharArray()) {
            //숫자일 경우 추가
            if(c <= 97) answer.append(c+"");
            //글자일 경우 보관 후 변환해서 추가
            else {
                res.append(c+"");
                
                for(int i=0; i<arr.length; i++) {
                    if(arr[i].equals(res.toString())) {
                        answer.append(i+"");
                        res = new StringBuilder();
                    }
                }
            }
        }   
        
        return Integer.parseInt(answer.toString());
    }
    
    private void checkAndRevertInt(StringBuilder res) {
        
    }
}