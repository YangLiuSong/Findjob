import sun.security.krb5.SCDynamicStoreConfig;

import java.util.*;

public class netease_huyu {
    public static void main(String[] args) {
        System.out.println(net_01("10","1.5212"));
//        int[][] b = new int[][]{{5,4,3}, {5,4,5}, {6,6,6}};
//        System.out.println(maxBoxes(b));
//        int[] a = new int[]{2,3,1,4,6,5};
//        int[] b = new int[]{2,3,1,4,6,5};
//        System.out.println(check(a,b,6));
    }

    // 九进制
    public static String net_01(String s1,String s2){
        boolean b1=false,b2=false;
        int l11=0,l12=0,l21=0,l22=0;
        for (int i=0;i<s1.length();i++) {
            if (s1.charAt(i) == '.') {
                b1 = true;
            }
            else{
                if(!b1)
                    l11++;
                else
                    l12++;
            }
        }
        for (int i=0;i<s2.length();i++) {
            if (s2.charAt(i) == '.') {
                b2 = true;
            }
            else {
                if(!b2)
                    l21++;
                else
                    l22++;
            }
        }
        int l_1 = Math.max(l11,l21);
        int l_2 = Math.max(l12,l22);
        List<String > list = new ArrayList<>();
        if(l12>0||l22>0)
            list.add(".");
        boolean move = false;
        while (l_2>0){
            int a=0,b=0;
            if(l12==l_2){
                a = s1.charAt(l11+l12)-'0';
                l12--;
            }
            if(l22==l_2){
                b = s2.charAt(l21+l22)-'0';
                l22--;
            }
            int sum = move?a+b+1:a+b;
            if(sum>=9){
                list.add(1,String.valueOf(sum-9));
                move = true;
            }
            else{
                list.add(1,String.valueOf(sum));
                move = false;
            }
            l_2--;
        }
        boolean Move = move;
        while (l_1>0){
            int a=0,b=0;
            if(l11==l_1){
                a = s1.charAt(l11-1)-'0';
                l11--;
            }
            if(l21==l_1){
                b = s2.charAt(l21-1)-'0';
                l21--;
            }
            int sum = Move?a+b+1:a+b;
            if(sum>=9){
                list.add(0,String.valueOf(sum-9));
                Move = true;
            }
            else{
                list.add(0,String.valueOf(sum));
                Move = false;
            }
            l_1--;
        }
        if (Move) list.add(0,"1");
        StringBuffer s = new StringBuffer();
        for (int i=0;i<list.size();i++){
            s.append(list.get(i));
        }
        return s.toString();
    }

    public static int count=0;
    public static void net_02(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] person = new int[n];
        for (int i=0;i<n;i++){
            person[i] = sc.nextInt();
        }
        int[] works = new int[n];
        for (int i=0;i<n;i++){
            works[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        a(person,0,n-1,works);
        System.out.println(count%m);

    }

    public static void net_03(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[][] names = new String[n][m];
        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                names[i][j] = sc.next();
            }
        }
        List<Character[]> list = new ArrayList<>();
    }

    public static void a(int[] person,int start,int end,int[] works){
        if(start==end){
            if(check(person,works,end))
                count++;
        }
        else{
            for (int i=start;i<=end;i++){
                swap(person,start,i);
                a(person,start+1,end,works);
                swap(person,start,i);
            }
        }
    }

    public static void swap(int[] nums,int a,int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static boolean check(int[] person,int[] works,int n){
        for (int i=0;i<=n;i++){
            if (person[i]<works[i])
                return false;
        }
        return true;
    }


    public static int maxBoxes (int[][] boxes) {
        // write code here
        Arrays.sort(boxes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int l = o1.length;
                for (int i=l-1;i>=0;i--){
                    if(o1[i]!=o2[i])
                        return o1[i]-o2[i];
                }
                return 0;
            }
        });
        int l = boxes.length;
        int count=0;
        for (int i=l-1;i>0;i--){
            if(!checkbox(boxes[i-1],boxes[i]))
                count++;
        }
//        for (int i=0;i<boxes.length;i++){
//            for (int j=0;j<3;j++){
//                System.out.print(boxes[i][j]);
//            }
//            System.out.println("");
//        }
        return count+1;
    }

    public static boolean checkbox(int[] a,int[] b){
        int l = a.length;
        for(int i=0;i<l;i++){
            if(a[i]>=b[i])
                return false;
        }
        return true;
    }
}
