import java.util.Arrays;

class Solution {
    public int solution(int[] d, int budget) {
        // 최대한 많은 부서를 지원했을 때 그 부서 개수 리턴
        // -> 신청 금액이 적은 순서대로 정렬 후 예산이 그 금액 합에 가까울 때까지 지원

        //1. 신청 금액 배열을 오름차순 정렬
        Arrays.sort(d);

        int summary = 0;
        //2. 인덱스를 하나씩 증가: 지원 부서 하나씩 증가 & 신청 금액 합과 예산 비교
        for(int i=0; i<d.length; i++) {
            summary += d[i];

            if(summary > budget) return i; // 신청 금액 합 > 예산: 바로 직전 지원 부서 개수 리턴
            else if(summary == budget) return i+1; // 신청 금액 합 == 예산: 현재 지원 부서 개수 리턴
        }
        return d.length; //for문 종료까지 빠져나가지 않았다면 신청 부서 모두 지원 가능
    }
}