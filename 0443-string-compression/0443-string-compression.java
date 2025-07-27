/*
    character 배열이 주어졌을 때, 주어진 규칙에 따라 압축할 것
    - chars 배열은 연속으로 반복되는 문자로 구성
    - '연속된 문자' + '연속 개수' 로 구성 (1개일 경우 1은 생략)
    - 10이 넘어갈 경우 '1', '0' 같이 표시
    - 이렇게 압축한 새로운 배열의 길이를 리턴
 */
class Solution {
    public int compress(char[] chars) {
        int index = 0; //수정용
        int i = 0;     //조회용

        while (i < chars.length) {
            char currChar = chars[i];
            int count = 0;

            //연속된 문자 카운트
            while (i < chars.length && chars[i] == currChar) {
                i++;
                count++;
            }

            //연속된 문자 종료되면 기록
            chars[index++] = currChar;

            //숫자 기록 (1은 생략)
            if (count > 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[index++] = c;
                }
            }
        }

        return index;
    }
}