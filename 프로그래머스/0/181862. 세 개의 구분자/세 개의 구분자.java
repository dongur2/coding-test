import java.util.*;

class Solution {
    public String[] solution(String myStr) {
        List<String> list = new ArrayList<>();
        
        myStr = myStr.replaceAll("[abc]", "-");
        
        for(String s:myStr.split("-")) {
            if(!s.isEmpty()) list.add(s);
        }
        
        return list.isEmpty()? new String[] {"EMPTY"} : list.toArray(String[]::new);
    }
}