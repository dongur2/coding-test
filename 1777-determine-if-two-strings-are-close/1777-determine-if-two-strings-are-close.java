import java.util.*;
/*
    다음 작업을 수행했을 때 문자열1 -> 문자열2로 변환할 수 있으면 '가깝다'고 간주
    1. 글자 두개의 위치를 교환한다. 
    2. 문자열에 존재하는 모든 해당 글자열을 -> 다른 글자열로 변환한다.
    위 작업은 필요한 만큼 수행할 수 있다.

    word1와 word2가 가까운지 여부를 리턴 
 */
class Solution {
    public boolean closeStrings(String word1, String word2) {
        //문자열 길이 다르면 불가능 
        if (word1.length() != word2.length()) return false;

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        //글자별 개수 카운트 
        for (char c : word1.toCharArray()) map1.put(c, map1.getOrDefault(c, 0) + 1);
        for (char c : word2.toCharArray()) map2.put(c, map2.getOrDefault(c, 0) + 1);

        //문자 종류 구성이 다르면 불가능
        if (!map1.keySet().equals(map2.keySet())) return false;

        //문자 빈도 목록 정렬해서 비교
        List<Integer> freq1 = new ArrayList<>(map1.values());
        List<Integer> freq2 = new ArrayList<>(map2.values());
        Collections.sort(freq1);
        Collections.sort(freq2);

        //빈도 같으면 가능 
        return freq1.equals(freq2);
    }
}