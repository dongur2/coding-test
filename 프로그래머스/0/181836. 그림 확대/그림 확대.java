class Solution {
    public String[] solution(String[] picture, int k) {
        StringBuilder larger = new StringBuilder();
        
        for(int idx=0; idx<picture.length; idx++) {

            StringBuilder temp = new StringBuilder();
            for(int ch=0; ch<picture[idx].length(); ch++) {
                temp.append((picture[idx].charAt(ch)+"").repeat(k));
            }
            temp.append(",");
            
            larger.append(temp.toString().repeat(k));
        }
        
        return larger.toString().split(",");
    }
}