class Solution {
    // 주어진 brown, yellow 격자 개수 == 카펫의 넓이
    // 카펫 넓이의 약수 곱 쌍 == 카펫의 가로 길이, 세로 길이 :: 그 중 가로 >= 세로
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int carpet = brown + yellow; // 카펫의 넓이
        
        for(int i=3; i<=carpet; i++) { // 최소 3
            if(carpet % i > 0 && carpet / i <= 3) continue;
            
            int j = carpet / i;
            
            int width = Math.max(i,j); // 가로가 세로보다 길어야 한다
            int height = Math.min(i,j);
            int yellowArea = (width-2) * (height-2);

            if(yellowArea == yellow) {
                answer[0] = width;
                answer[1] = height;
                break;
            }
        }
        
        return answer;
    }
}