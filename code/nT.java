import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class nT {
    public static void main(String[] args) {
        net1();
    }

    public static void net1(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int[] d_ns = new int[n-1];
        for (int i=0;i<n;i++){
            nums[i]=sc.nextInt();
        }
        for (int i=0;i<n-1;i++){
            d_ns[i]=nums[i+1]-nums[i];
        }
        Arrays.sort(d_ns);
        int m = d_ns[0];
        boolean b = false;
        for(int i=1;i<n-1;i++){
            try {
                m = maxValue(m,d_ns[i]);
            }catch (ArithmeticException e){
                b=true;
            }
        }
        if (b)
            System.out.println(-1);
        else
            System.out.println(m);
    }

    public static int maxValue(int a,int b) throws ArithmeticException{
        if(a<b){
            int t = a;
            a = b;
            b = t;
        }
        int temp = a%b;
        while (temp!=0){
            a=b;
            b=temp;
            temp=a%b;
        }
        return b;
    }

    public static int lost =  Integer.MAX_VALUE;
    public static void net2(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int D = sc.nextInt();
        int[][] nums = new int[n][2];
        for (int i=0;i<n;i++){
            nums[i][0]=sc.nextInt();
        }
        for (int i=0;i<n;i++){
            nums[i][1]=sc.nextInt();
        }
        all(nums,D,0,n-1);
        System.out.println(lost);
    }

    public static void all(int[][] nums,int D,int start,int end){
        if(start==end){
            int d = D;
            int l = 0;
            for(int i=0;i<nums.length;i++){
                if(d<=nums[i][0])
                    l+=nums[i][1];
                if(l>lost)
                    return;
                d++;
            }
            lost = Math.min(lost,l);
        }
        else {
            for (int i=start;i<=end;i++){
                swap(nums,start,i);
                all(nums,D,i+1,end);
                swap(nums,start,i);
            }
        }
    }
    public static void swap(int[][] nums,int a,int b){
        int[] temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void net3(){
        Scanner sc = new Scanner(System.in);
        // 城市人口
        int n = sc.nextInt();
        // 聚会数量
        int m = sc.nextInt();
        // 初始病毒编号
        int f = sc.nextInt();
        boolean[] b = new boolean[n];
        b[f] = true;
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<m;i++){
            int q = sc.nextInt();
            boolean has = false;
            for(int j=0;j<q;j++){
                int num = sc.nextInt();
                list.add(num);
                if(!has&&b[num]){
                    has = true;
                }
            }
            if (has){
                for(int k=0;k<q;k++){
                    b[list.get(k)]=true;
                }
            }
            list.clear();
        }
        for(boolean h:b){
            if(h)
                count++;
        }
        System.out.println(count);
    }
}
