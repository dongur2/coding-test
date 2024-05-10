class Solution {
    public int[] solution(int N, int[] stages) {
        int[] challengers = new int[N+1]; // N+1(모두 완료)도 껴있으니까 N+1
        
        // 스테이지별 도전하고 있는 사람 수 카운트
        for(int i=0; i<stages.length; i++) challengers[stages[i]-1]++;
        // 스테이지별 총 도전자 수 = 해당 스테이지에 도전중 사람 수 + 바로 다음 스테이지로 넘어가있는 사람 수 
        for(int i=N-1; i>=0; i--) challengers[i] += challengers[i+1];

        // 기본 스테이지 오름차순 배열
        int[] answer = new int[N];
        for(int i=0; i<N; i++) {
            answer[i] = i+1;
        }
        
        // 실패율 = 도전중 / 총 도전자 수 
        for(int i=0; i<N-1; i++) {
            for(int j=0; j<N-1; j++) {
                // 비교할 스테이지 번호 
                int left = answer[j];
                int right = answer[j+1];
                
                // 비교할 스테이지 번호의 실패율
                double l = (challengers[left-1]>0)? (double)challengers[left]/challengers[left-1]: 1;
                double r = (challengers[right-1]>0)? (double)challengers[right]/challengers[right-1]: 1;
                
                if(l>r) {
                    int temp = answer[j];
                    answer[j] = answer[j+1];
                    answer[j+1] = temp;
                }
            }
        }
        
        return answer;
    }
}