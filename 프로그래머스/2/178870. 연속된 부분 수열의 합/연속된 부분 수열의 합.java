/*
    부분 수열 찾기:
    - 기존 수열 순서 고정
    - 부분 수열의 합 k
    - 우선 순위: 짧은 길이 > 앞쪽 
    
    부분 수열 시작/끝 인덱스 리턴
*/
class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] res = new int[2];
        res[0] = -1; res[1] = -1; //초기화
        
        int sum = 0, left = 0;
        for(int right = 0; right<sequence.length; right++) {
            sum += sequence[right];
            
            while(sum > k) sum -= sequence[left++];
                        
            if(sum == k) {
                if((res[0] == -1 && res[1] == -1 ) || (res[1]-res[0] > right-left)) {
                    res[0] = left; res[1] = right;
                }
            }
        }
        
        return res;
    }
}