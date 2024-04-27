import java.util.*;

class Solution {
    private String[] useComparator(String[] strings, int n) {
       Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if(s1.charAt(n) == s2.charAt(n)) return s1.compareTo(s2);
                else return s1.charAt(n) - s2.charAt(n);
            }
        });
        return strings; 
    }
    
    private String[] refer(String[] strings, int n) {
        String[] arr = new String[strings.length];
        
        for(int i=0; i<strings.length; i++) {
            arr[i] = strings[i].charAt(n) + strings[i];
        }
        
        Arrays.sort(arr);
        
        for(int i=0; i<strings.length; i++) {
            arr[i] = arr[i].substring(1);
        }
        
        return arr;
    }
    
    public String[] solution(String[] strings, int n) {
        return refer(strings, n);
    }
}