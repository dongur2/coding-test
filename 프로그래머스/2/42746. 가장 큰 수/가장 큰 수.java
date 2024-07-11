import java.util.*;

class Solution {
    /*
    문제: 정수를 이어 붙여 만들 수 있는 가장 큰 수(문자열)를 리턴
    - 0 또는 양의 정수가 있는 배열이 주어지는데, 순서를 재배치하여 만들 수 있는 가장 큰 수
    - numbers.length <= 10^5
    - 0 <= number <= 1000
    
    1. 각 배열 원소를 문자열로 변환: 숫자 자체 크기 비교가 아니라, 문자열로 연결했을 때 더 큰 값을 가지는 조합을 찾기 위함
    2. 배열을 앞뒤원소를 더했을 때 더 큰 원소를 앞으로 정렬: 비교는 사전순
    3. 가장 큰 값이 0이면 0 리턴: 모든 값이 0
    4. 배열의 모든 원소를 연결하여 하나의 문자열로 리턴
    */
    public String solution(int[] numbers) {
        //1. 각 배열 원소를 문자열로 변환
        String[] converted = Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new);
        
        //2. 앞뒤원소를 더했을 때 더 큰 원소를 앞으로 정렬
        Arrays.sort(converted, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
 
        //3. 가장 큰 값이면 "0"을, 아니라면 정렬한 배열을 하나의 문자열로 연결하여 리턴
        if (converted[0].equals("0")) return "0";
        return String.join("", converted);
    }
}