/*
    바나나 n더미 - piles[i] = i번째 더미에 포함된 바나나 개수 
    가드는 h 시간 안에 돌아올 것

    코코는 1시간마다 더미 중 하나를 선택해서 k개를 먹음
    만약 그 더미의 바나나가 k개보다 적다면, 그 시간 동안은 더 이상 바나나 안먹음

    근데 천천히 먹는 걸 좋아하지만.. 다 먹고 싶음 
    가드가 돌아오기 전에 코코가 "최소한"으로 바나나를 모두 먹을 수 있는 "시간당 바나나 개수 k"를 리턴할 것
 */
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int k = Arrays.stream(piles).max().getAsInt(); //최악의 경우: 가장 많은 개수가 포함된 더미를 한번에 먹기

        int left = 1, right = k;
        while(left <= right) {
            int mid = left + (right-left)/2;

            //돌아오기 전에 이미 다 먹었으면, 더 천천히 먹을 수 있는지 확인
            if(getHour(piles, mid) <= h) {
                k = mid; right = mid - 1;
            }
            //돌아오기 전에 다 못 먹었으면, 더 빨리 먹을 수 있는지 확인
            else left = mid + 1;
        }

        return k;
    }

    int getHour(int[] piles, int b) {
        int times = 0;

        for(int p:piles) {
            times += Math.ceil((double)p / b);
        }

        return times;
    }
}