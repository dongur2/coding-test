import java.util.*;

class Solution {
    /* 
    h 개의 논문이 각각 최소 h개의 인용을 갖는 가장 큰 숫자 h 
    # h-인덱스의 최대값: 출판물의 수
    
    1. 논문 인용 횟수를 정렬
    2. 기본 h-idx를 최대값 (논문 수)으로 설정
    3. 배열을 앞에서부터 순회하는데, 값이 h-idx보다 작을 경우 h-idx 마이너스하면서 진행
        4. h-idx 이상인 값을 만나면 리턴
    */
    public int solution(int[] citations) {
        int h = citations.length;
        
        int[] arr = Arrays.copyOf(citations, citations.length);
        Arrays.sort(arr);
        
        for(int a:arr) {
            if(a < h) h--;
            else break;
        }
        
        return h;
    }
}