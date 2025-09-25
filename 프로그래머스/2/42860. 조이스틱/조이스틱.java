class Solution {
    public int solution(String name) {
        int answer = 0;
        
        //커서 이동의 최댓값 (한 방향으로만 끝까지 가는 경우) 
        int minMoves = name.length() - 1; 

        //알파벳 바꾸는 데 필요한 최소 조작 횟수
        for (int i = 0; i < name.length(); i++) {
            //A에서 목표 알파벳까지 위/아래로 움직이는 최소 횟수
            answer += Math.min(name.charAt(i)-'A', 'Z'-name.charAt(i)+1);
        }

        //커서를 이동하는 데 필요한 최소 조작 횟수
        for (int i = 0; i < name.length(); i++) {
            int next = i + 1;

            //A가 연속으로 나오는 부분 찾기
            while (next < name.length() && name.charAt(next) == 'A') {
                next++;
            }

            //앞으로 쭉 이동하는 경우
            int moveForward = i;
            //뒤로 돌아가는 경우
            int moveBackward = name.length() - next;
            //앞으로 갔다가 뒤로 돌아오는 경우의 최소 이동 횟수
            int moves = moveForward + moveBackward + Math.min(moveForward, moveBackward);
            
            //모든 경우의 최소 이동 횟수 갱신
            minMoves = Math.min(minMoves, moves);
        }

        return answer + minMoves;
    }
}