import java.util.*;
import java.util.concurrent.Future;

public class dy {

    public static void main(String[] args) {

    }

    /**
     *
     */
    public static void mt01(){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char[] ch = str.toCharArray();
        int[] t = new int[9];
        for(int i=0;i<9;i++){
            t[i] = sc.nextInt();
        }
        StringBuffer r = new StringBuffer();
        for(int i=0;i<ch.length;i++){
            if(ch[i]!='-'){
                int index = ch[i]-'0';
                if(t[index-1]!=ch[i])
                    r.append(t[index-1]);
                else
                    r.append(ch[i]);
            }
            else
                r.append(ch[i]);
        }
        System.out.println(r);
    }

    /**
     *
     */
    public static void mt02(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[] rs = new double[n];
        for(int i=0;i<n;i++){
            rs[i] = sc.nextInt();
        }

        Arrays.sort(rs);
        double d = 0.0;
        boolean color = true;
        for(int i=n-1;i>=0;i--){
            if(color){
                d += Math.pow(rs[i],2);
                color = false;
            }
            else{
                d -= Math.pow(rs[i],2);
                color = true;
            }
        }
        double result = d*Math.PI;
        System.out.println(String.format("%.5f",result));
    }

    /**
     *
     */
    public static void mt03() {
        Scanner sc = new Scanner(System.in);
    }

    /**
     *
     */
    public static void mt04(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double p = sc.nextDouble();
        double q = sc.nextDouble();
        double[] scores = new double[n];
        for(int i=0;i<n;i++){
            scores[i] = sc.nextDouble();
        }

    }

    /**
     *
     */
    public static void mt05(){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        double K = sc.nextDouble();
        double[] nums = new double[N];
        double sum = 0;
        for(int i=0;i<N;i++){
            nums[i] = sc.nextDouble();
        }
        for(int i=0;i<N;i++){
            sum = sum + (C(N+K-i-2,K-1)*nums[i])%100000007;
        }
        System.out.println(String.format("%.0f",sum));
    }

    public static double C(double m,double n){
        double a = 1;
        double b = 1;
        for(double i=n;i>0;i--){
            a *= m;
            m--;
            b *= i;
        }
        return (a/b)%100000007;
    }

    /**
     * 给定一组数，输出其中第二大的数字（不使用排序）
     */
    public static void tx01(){
        Scanner sc = new Scanner(System.in);
        int m=Integer.MIN_VALUE;
        int n=Integer.MIN_VALUE;
        int count=0;
        while (sc.hasNext()){
            if(count==0){
                m=sc.nextInt();
                count++;
            }
            else{
                int a=sc.nextInt();
                if(a>m){
                    n=m;
                    m=a;
                }
                else if(a>n)
                    n=a;
            }
        }
        System.out.println(n);
    }

    /**
     * 给定n与m，找出1~n中所有和为m的子集
     */
    public static void tx02(){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        List<Integer> list = new ArrayList<>();
        getN(n,m,0,list);
    }

    public static void getN(int n, int m, int sum, List<Integer> list){
        if(sum>m||n<0)
            return;
        else if(sum==m){
            for(int i=0;i<list.size()-1;i++){
                System.out.print(list.get(i) + ",");
            }
            System.out.println(list.get(list.size()-1));
            return;
        }
        else{
            for(int i=n;i>0;i--){
                sum+=i;
                list.add(i);
                getN(i-1,m,sum,list);
                list.remove(list.size()-1);
                sum-=i;
            }
        }
    }
}
