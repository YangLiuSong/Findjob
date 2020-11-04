import java.util.Scanner;

// 某次笔试题
public class g {
    public static void main(String[] args) {
        g01();
    }

    public static void g01(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = (int)Math.sqrt(n);
        int count = 0;
        for(int i=2;i<=m && n!=1;){
            if (n%i==0){
                count++;
                n /= i;
            }else{
                i++;
            }
        }
        System.out.println(count==0?1:count);
    }
}
