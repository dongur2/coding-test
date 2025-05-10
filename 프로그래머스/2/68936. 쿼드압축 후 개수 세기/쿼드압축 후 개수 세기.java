/*
2^n x 2^n 배열 -> 특정 영역을 압축 
- 영역 내 모든 수가 같은 값: 해당 수로 압축
- 그렇지 않으면 영역을 4개 정사각형 영역으로 분할 -> 또 확인 (반복)
-- 최종적으로 남는 0과 1의 개수?
*/
class Solution {
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        int n = arr.length; //한 변의 길이
        
        check(arr, n, answer, 0, 0, n, n);
        
        return answer;
    }
    
    public void check(int[][] arr, int n, int[] answer, int sr, int sc, int er, int ec) {
        //모든 값이 같은지 확인 - 같으면 값 바인딩하고 종료
        int num = arr[sr][sc]; //그리드 첫번째 값과 모든 값이 같은지 확인
        if(isSame(arr, sr, sc, er, ec, num)) {
            answer[num]++; return;
        }
        
        //더이상 분할할 수 없으면 개수만 카운트
        //n이 2이면 분할 불가
        if(n == 2) {
            count(arr, n, answer, sr, sc, er, ec);
            return;
        }
        
        //아니라면 분할하고 재확인
        //4개로 분할
        for(int r=sr; r<er; r+=n/2) {
            for(int c=sc; c<ec; c+=n/2) {
                int split = n/2;
                check(arr, split, answer, r, c, r+split, c+split);
            }
        }
    }
    
    public static boolean isSame(int[][] arr, int sr, int sc, int er, int ec, int num) {
        for(int i=sr; i<er; i++) {
            for(int j=sc; j<ec; j++) {
                if(arr[i][j] != num) return false;
            }
        }
        return true;
    }
    
    public static void count(int[][] arr, int n, int[] answer, int sr, int sc, int er, int ec) {
        for(int i=sr; i<er; i++) {
            for(int j=sc; j<ec; j++) {
                int num = arr[i][j];
                answer[num]++;
            }
        }
    }
}