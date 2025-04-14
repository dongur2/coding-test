import java.util.Scanner;

//주어진 자연수의 각 자리를 곱 -> 한자리까지 
public class Main {
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      
      //입력
      while(true) {
        int num = sc.nextInt();
        if(num == 0) break;

        //9*5 45 4*5 20 2*0 0

        int newNum = num;
        while(newNum >= 0) {  
          System.out.print(newNum+" ");
          if(newNum / 10 == 0) break; //한자리 수가 되면 중지
          
          int result=1;
          while(newNum > 0) { //9
            result *= newNum % 10; //5*9=45
            newNum /= 10; //9..0
          }
          newNum = result;
        }
        System.out.println();
      }
  }
}