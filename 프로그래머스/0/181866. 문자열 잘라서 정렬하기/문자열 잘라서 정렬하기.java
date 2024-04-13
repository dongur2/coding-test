import java.util.*;

class Solution {
    public String[] solution(String myString) {
        List<String> list = new ArrayList<>();
        
        for(String s : myString.split("x")) {
            if(s.length() > 0) list.add(s);
        }
        
        list.sort(Comparator.naturalOrder());
        return list.toArray(new String[list.size()]);
    }
}