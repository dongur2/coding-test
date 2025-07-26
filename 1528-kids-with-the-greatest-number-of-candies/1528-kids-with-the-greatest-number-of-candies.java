/*
    n명이 사탕을 가지고 있음
    사탕 배열이 주어지는데, candies[i]는 i번째 애가 있는 사탕 개수
    extraCandies는 내가 가지고 있는 추가 사탕 개수

    길이가 n인 불리언 배열 리턴 
    - result[i]가 true라면: i번째 애한테 내가 가진 사탕을 모두 주고 난 후에 걔가 가장 많은 사탕을 가지고 있는 경우
 */
class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int n = candies.length;

        List<Boolean> result = new ArrayList<>();

        //최대 개수 저장
        int max = Arrays.stream(candies).max().getAsInt();

        //순서대로 내가 가진 사탕 다 주면 i번째 애가 가지게 되는 사탕 개수가 최대 개수인지 확인
        for(int i=0; i<n; i++) {
            int cnt = candies[i] + extraCandies;
            result.add(max <= cnt); //최대 개수와 같거나 최대 개수보다 커야 함
        }

        return result;
    }
}