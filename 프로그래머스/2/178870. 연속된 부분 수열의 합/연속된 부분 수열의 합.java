class Solution {
    public int[] solution(int[] sequence, int k) {
        int end = 0;
        int sum = 0;
        int len = -1;

        int[] res = new int[2];

        //1. 시작인덱스++
        for(int start = 0; start<sequence.length; start++) {
            //2. 끝인덱스가 배열 길이를 넘지 않고 부분 수열의 합이 k보다 작으면, 끝인덱스++ & 합에 추가
            while (end<sequence.length && sum < k) {
                sum += sequence[end++];
            }

            //3. 합이 k와 같을 경우, 이전 sum==k인 배열의 길이와 현재 배열 길이를 비교한 후,
            //현재 배열 길이가 더 짧다면, 리턴할 인덱스와 전역 변수로 저장해 둔 배열 길이를 교체
            if (sum == k) {
                if(len == -1 || end - start < len) {
                    res[0] = start;
                    res[1] = end - 1;
                    len = end - start;
                }
            }

            //4. 시작인덱스가 오른쪽으로 이동할 것이므로, 배열에서 벗어나게 되는 원소를 합에서 빼기
            sum -= sequence[start];
        }

        return res;
    }
}