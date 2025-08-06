/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

//1~n 숫자 중에 고른 숫자 하나 리턴 
public class Solution extends GuessGame {
    public int guessNumber(int n) {
        return pick(1, n);
    }

    int pick(int s, int e) {
        int mid = s + (e-s)/2; 
        int dir = guess(mid);      //guess(mid): -1, 1, 0

        if (dir == 0) return mid;
        else if (dir < 0) return pick(s, mid-1); 
        else return pick(mid+1, e);              
    }
}