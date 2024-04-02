class Solution {
    public int solution(int n, int k) {
        int food = n * 12000;
        int drink = (n/10 > 0)? (k-(n/10))*2000 : k*2000;
        
        return food+drink;
    }
}