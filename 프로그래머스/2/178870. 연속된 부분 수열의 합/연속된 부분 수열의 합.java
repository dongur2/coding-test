//오름차순으로 정렬된 수열에서 원소의 합이 k가 되는 부분 수열의 시작-끝 인덱스
class Solution {
    public int[] solution(int[] sequence, int k) {
        int len = Integer.MAX_VALUE;
        int[] answer = new int[2];
        
        //앞에서부터 시작
        int left = 0, sum = 0;
        
        for(int right=0; right<sequence.length; right++) {
            sum += sequence[right]; // 1, 3, 6, 10 (9, 7)
            
            //k보다 합이 크면 작거나 같아질 때까지 왼쪽 인덱스 땡기기
            while(sum > k) sum -= sequence[left++];
            
            //현재 부분 수열의 합 = k: 이미 저장된 수열의 길이보다 "짧으면" 업데이트
            //앞에서부터 탐색을 진행했으므로, <길이가 같으면 앞이 우선> 조건은 자동 충족 
            if(sum == k) {
                if(right - left < len) {
                    len = right - left;
                    answer[0] = left; answer[1] = right;
                }
                //더 짧은 길이가 가능한지 확인하기 위해 왼쪽 인덱스 땡기기
                sum -= sequence[left++];
            }
        }
        return answer;
    }
}