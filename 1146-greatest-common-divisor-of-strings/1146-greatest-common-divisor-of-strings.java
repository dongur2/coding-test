import java.util.*;

//두 문자열이 어떤 문자열 x를 여러 번 이어붙여 만든 것이라면, 가장 긴 x
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        //str1과 str2는 어떤 문자열 x의 반복으로 이루어져있다.
        //str1과 str2에 공통된 문자열 x가 없는 경우도 있다.

        //모든 경우로 조각낸 문자열 저장
        List<String> list = new ArrayList<>();
        for(int i=1; i<=str1.length(); i++) {
            list.add(str1.substring(0, i));
        }

        //리스트 마지막부터 순회 - 긴 문자열부터 확인
        //해당 문자열로 기존 문자열을 나눴을 때 배열이 비었으면 정답 (즉시 리턴)
        for(int i=list.size()-1; i>=0; i--) {
            String[] a1 = str1.split(list.get(i));
            String[] a2 = str2.split(list.get(i));            
            if(a1.length == 0 && a2.length == 0) return list.get(i);
        }

        return "";
    }
}