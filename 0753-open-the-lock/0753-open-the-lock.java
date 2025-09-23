/*
    4개 숫자 다이얼 (0-9)을 위아래로 돌릴 수 있음
    0000에서 시작 
    - deadends 목록: 이 목록에 포함되는 코드로 돌릴 경우 실패
    
    실패하지 않고 타겟 코드를 맞출 수 있는 최소 다이얼 회전 횟수 (불가능하면 -1)
 */
class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>();
        Set<String> deads = new HashSet<>(List.of(deadends));

        if(deads.contains("0000")) return -1;
        
        Queue<String> q = new ArrayDeque<>();
        q.offer("0000"); visited.add("0000");

        int answer = 0;

        while(!q.isEmpty()) {
            int level = q.size();

            //현재 단계에서 만들 수 있는 모든 코드 생성해서 큐에 추가 
            for(int i=0; i<level; i++) {
                String curr = q.poll();

                //목표 코드 완성했으면 리턴 
                if(target.equals(curr)) return answer;

                for(int j=0; j<4; j++) {
                    char c = curr.charAt(j);

                    //+1
                    char up = (char) (((c - '0' + 1) % 10) + '0');
                    String upCode = curr.substring(0, j) + up + curr.substring(j+1);
                    if(!visited.contains(upCode) && !deads.contains(upCode)) {
                        visited.add(upCode); q.offer(upCode);
                    }
                    //-1
                    char down = (char) (((c - '0' + 9) % 10) + '0');
                    String downCode = curr.substring(0, j) + down + curr.substring(j+1);
                    if(!visited.contains(downCode) && !deads.contains(downCode)) {
                        visited.add(downCode); q.offer(downCode);
                    }
                }
            }
            answer++;
        }

        return -1;
    }
}