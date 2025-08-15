/*
    숫자 2-9로 이루어진 문자열이 주어졌을 때, 만들 수 있는 모든 문자열 리턴
 */

import java.util.List; import java.util.ArrayList;
class Solution {
    //각 숫자로 표현할 수 있는 글자
    char[][] chars = new char[][] {
        {}, {}, {'a','b','c'},
        {'d','e','f'}, {'g', 'h', 'i'}, {'j','k','l'}, {'m','n','o'},
        {'p','q','r','s'}, {'t','u','v'}, {'w','x','y','z'}
    };

    List<String> comb = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        //빈 문자열일 경우
        if(digits.length() == 0) return new ArrayList<String>();

        dfs(digits, 0, new StringBuilder());
        return comb;
    }

    void dfs(String digits, int idx, StringBuilder letter) {
        if(idx > digits.length()) return;

        if(letter.length() == digits.length()) {
            comb.add(letter.toString());
            return;
        }

        for(int i=0; i<chars[Character.getNumericValue(digits.charAt(idx))].length; i++) {
            letter.append(chars[Character.getNumericValue(digits.charAt(idx))][i]);

            //현재 숫자에 해당하는 글자 목록 하나씩 밀면서 들어가기
            dfs(digits, idx+1, letter);
            letter.deleteCharAt(letter.length()-1);
        }
    }
}