import java.util.*;

public class tx {
    public static void main(String[] args) {
        tx05();

    }

    public static void tx01(){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        while (num>0){
            int n = sc.nextInt();
            list.clear();
            while (n>0){
                String s = sc.next();
                if(s.equals("PUSH")){
                    int i = sc.nextInt();
                    list.add(i);
                }
                else if(s.equals("TOP")){
                    if(list.size()==0)
                        System.out.println(-1);
                    else
                        System.out.println(list.get(0));
                }
                else if(s.equals("POP")){
                    if(list.size()==0)
                        System.out.println(-1);
                    else
                        list.remove(0);
                }
                else if(s.equals("SIZE"))
                    System.out.println(list.size());
                else
                    list.clear();
                n--;
            }
            num--;
        }
    }


    /**
     * 2
     * 4
     * 0 0
     * 0 1
     * 1 0
     * 1 1
     * 2 2
     * 2 3
     * 3 2
     * 3 3
     * 4
     * 0 0
     * 0 0
     * 0 0
     * 0 0
     * 0 0
     * 0 0
     * 0 0
     * 0 0
     */
    public static void tx02(){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        while (num>0){
            int n = sc.nextInt();
            int[][] A = new int[n][2];
            int[][] B = new int[n][2];
            for(int i=0;i<n;i++){
                A[i][0] = sc.nextInt();
                A[i][1] = sc.nextInt();
            }
            for(int i=0;i<n;i++){
                B[i][0] = sc.nextInt();
                B[i][1] = sc.nextInt();
            }
            Arrays.sort(A,(a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
            Arrays.sort(B,(a,b)->a[0]==b[0]?a[1]-b[1]:a[0]-b[0]);
            double res = Double.MAX_VALUE;
            for(int i=0;i<n;i++){
                boolean c = false;
                for(int j=0;j<n;j++){
                    double t = Math.sqrt(Math.pow(A[i][0]-B[j][0],2)+Math.pow(A[i][1]-B[j][1],2));
                    res = Math.min(res,t);
                    if(t==0){
                        break;
                    }
                }
            }
            System.out.println(String.format("%.3f", res));
            num--;
        }
    }

    public static void tx03(){
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while(n>0){
            String s = sc.next();
            if(s.equals("add")){
                int i = sc.nextInt();
                s1.push(i);
            }
            else if(s.equals("poll")){
                if(!s2.isEmpty())
                    s2.pop();
                else{
                    while(!s1.isEmpty()){
                        s2.push(s1.pop());
                    }
                    s2.pop();
                }
            }
            else{
                if(!s2.isEmpty())
                    System.out.println(s2.peek());
                else{
                    while(!s1.isEmpty()){
                        s2.push(s1.pop());
                    }
                    System.out.println(s2.peek());
                }
            }
            n--;
        }
    }

    public static void tx05(){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        while(num>0){
            long index = sc.nextLong();
            int k = sc.nextInt();
            int c = getC(index);
            System.out.println(c);
            if(c<=k)
                System.out.println(-1);
            else{
                index=index>>(c-k);
                System.out.println(index);
            }
            num--;
        }
    }

    public static int getC(long index){
        int count = 1;
        while(index!=1){
            index=index>>1;
            count++;
        }
        return count;
    }

    // 同网易笔试
    public static void tx0906_1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        boolean[] b = new boolean[n];
        b[0] = true;
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

    public static void tx0906_2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i=0;i<n;i++) {
            nums[i] = sc.nextInt();
        }
        int midLeft = n/2-1;
        int midRight = n/2;
        for (int i=0;i<n;i++) {
            if (i<=midLeft)
                System.out.println(nums[midRight]);
            else
                System.out.println(nums[midLeft]);
        }
    }
}
