import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder a = new StringBuilder(sc.next());
        
        for(int i=0; i<a.length(); i++) {
            if(a.charAt(i) >= 97) {
                a.setCharAt(i, Character.toUpperCase(a.charAt(i)));
            } else {
                a.setCharAt(i, Character.toLowerCase(a.charAt(i)));
            }
        } 
        System.out.println(a);
    }
}