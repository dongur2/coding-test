import java.util.*;
//후보키 개수
/*
후보키: 유일성 + 최소성 만족
*/
class Solution {
    static int len = 0;
    
    public int solution(String[][] relation) {
        len = relation[0].length; //모든 속성 개수
        
        //모든 속성 조합 구하기 (부분집합)
        List<String> answer = new ArrayList<>();
        for(int l=1; l<=len; l++) {
            makeComb(relation, l, answer, -1, new StringBuilder());
        }
        
        return answer.size();
    }
    
    public static void makeComb(String[][] relation, int l, List<String> answer, int i, StringBuilder sb) {
        if(sb.length() == l) {
            //유일성 검사
            if(isUnique(relation, sb)) {
                //처음 값은 바로 저장
                if(answer.isEmpty()) {
                    answer.add(sb.toString()); return;
                }
                
                //최소성 검사
                if(isMinimum(answer, sb)) answer.add(sb.toString()); 
            }
            return;
        }
        
        for(int j=i+1; j<len; j++) {
            sb.append(j);
            makeComb(relation, l, answer, j, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
    
    public static boolean isUnique(String[][] relation, StringBuilder sb) {
        //인덱스 조합으로 값 생성
        Set<String> tuples = new HashSet<>();
        
        for(String[] r:relation) {
            StringBuilder s = new StringBuilder();

            for(int i=0; i<sb.length(); i++) {
                s.append(r[Integer.valueOf(sb.charAt(i)+"")]);
            }

            tuples.add(s.toString());
        }

        return tuples.size() == relation.length;
    }
    
    public static boolean isMinimum(List<String> answer, StringBuilder sb) {
        Set<Character> sc = new HashSet<>();
        for(char c:sb.toString().toCharArray()) {
            sc.add(c);
        }
        
        for(String ans:answer) {
            Set<Character> ac = new HashSet<>();
            for(char a:ans.toCharArray()) {
                ac.add(a);
            }
            
            if(sc.containsAll(ac)) return false;
        }
        return true;
    }
}