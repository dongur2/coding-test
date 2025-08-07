/*
    숫자 배열 spells, potions
    spells[i] = i번째 spell의 강도
    potions[j] = j번째 potion 강도

    숫자 success
    spell, potion 쌍 중에 결과물이 최소 success가 되는 경우를 고려할 것
    product = spell * potion

    숫자 배열 pairs를 리턴할 것
    pairs[i] = i번째 spell에 대해 성공적인 쌍이 되는 경우의 길이
 */
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] res = new int[spells.length];

        //potions 정렬 O(nlogn)
        Arrays.sort(potions);

        //이진 탐색
        for(int i=0; i<spells.length; i++) {
            res[i] = potions.length - search(potions, spells[i], success);
        }

        return res;
    }

    //success를 불만족하는 끝 인덱스 
    int search(int[] p, int s, long success) {
       int left = 0, right = p.length;

       while(left < right) {
        int mid = left + ((right - left) / 2);

        if((long)s * p[mid] >= success) right = mid;
        else left = mid + 1;
       }

       return left;
    }
}