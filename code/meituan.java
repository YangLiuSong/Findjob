import java.util.*;

public class meituan {
    public static void main(String[] args) {

    }

    /**
     * 照镜子
     * 时间限制： 3000MS
     * 内存限制： 589824KB
     * 题目描述：
     * 小团有一个n×m的矩阵A， 他知道这是小美用一种特殊的方法生成的，具体规则如下:
     *
     * 小美首先写下一个n'×m的矩阵，然后小美每一次将这个矩阵上下翻转后接到原矩阵的下方。小美重复这个过程若干次（甚至可能是0次，也就是没有进行过这一操作），然后将操作后的矩阵交给小团。
     *
     * 小团想知道，小美一开始写下的矩阵是什么。因为小美可能有多种一开始的矩阵，小团想得到最小的矩阵（这里的最小指矩阵即n'×m的面积最小）。
     *
     *
     *
     * 输入描述
     * 输入包含两个整数n,m，表示小团矩阵的大小。
     *
     * 接下来n行，每行m个正整数，第 i 行第 j 列表示矩阵第 i 行第 j 列的数。
     *
     * 1≤n≤100000,1≤m≤5,矩阵内的数小于等于10
     *
     * 输出描述
     * 输出包含一个矩阵，一共n'行m列，表示小美一开始最小的矩阵。
     *
     *
     * 样例输入
     * 8 3
     * 1 0 1
     * 0 1 0
     * 0 1 0
     * 1 0 1
     * 1 0 1
     * 0 1 0
     * 0 1 0
     * 1 0 1
     * 样例输出
     * 1 0 1
     * 0 1 0
     *
     * 提示
     * 样例解释
     * 小美一开始的矩阵可能有以下3种：
     * 1.
     * 1 0 1
     * 0 1 0
     *
     * 2.
     * 1 0 1
     * 0 1 0
     * 0 1 0
     * 1 0 1
     *
     * 3.
     * 1 0 1
     * 0 1 0
     * 0 1 0
     * 1 0 1
     * 1 0 1
     * 0 1 0
     * 0 1 0
     * 1 0 1
     * 其中最小的矩阵为第一种。
     */
    public static void meituan01(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<String> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            StringBuilder sb = new StringBuilder();
            for (int j=0;j<m;j++){
                sb.append(sc.next()).append(" ");
            }
            list.add(sb.toString());
        }
        // 判断是否还有更小的
        while (hasMin(list)){
            // 去掉后面重复翻转的部分（一半）
            int l = list.size();
            for (int i=0;i<l/2;i++){
                list.remove(l/2);
            }
        }
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }

    public static boolean hasMin(List<String> list){
        int l = list.size();
        // 若长度为奇数，必然不存在更小的
        if (l%2==1)
            return false;
        // 若为偶数，判断是否是上下翻转的
        for (int i=0;i<l/2;i++){
            if(!list.get(i).equals(list.get(l-i-1)))
                return false;
        }
        return true;
    }

    /**
     * 小团的装饰物2
     * 时间限制： 3000MS
     * 内存限制： 589824KB
     * 题目描述：
     * 小团需要购买m样装饰物。商店出售n种装饰物，按照从小到大的顺序从左到右摆了一排。对于每一个装饰物，小团都给予了一个美丽值ai。
     *
     * 小团希望购买的装饰物有着相似的大小，所以他要求购买的装饰物在商店中摆放的位置是连续的一段。小团还认为，一个装饰物的美丽值不能低于k，否则会不好看。
     *
     * 现在，请你计算小团有多少种不同的购买方案。
     *
     *
     *
     * 输入描述
     * 输入第一行包含三个数n,m,k
     *
     * 接下来一行n个整数ai ( 1≤ i ≤n )，空格隔开，表示商店从左到右摆放的每个装饰物的美丽值。
     *
     * n , m≤100000
     *
     * 1≤ai ,k≤10^9
     *
     * 输出描述
     * 输出一个数，表示小团购买的方案数。
     *
     *
     * 样例输入
     * 8 2 5
     * 5 5 5 4 5 5 5 5
     * 样例输出
     * 5
     *
     * 提示
     * 有[1,2][2,3][5,6][6,7][7,8] 共5段
     */
    public static void meituan02(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = sc.nextInt();
        }
        if (m>n)
            System.out.println(0);
        else {
            Queue<Integer> queue = new LinkedList<>();
            List<Integer> list = new ArrayList<>();
            for (int i=0;i<m;i++){
                while (list.size()>0 && nums[i]<list.get(list.size()-1)){
                    list.remove(list.size()-1);
                }
                list.add(nums[i]);
                queue.offer(nums[i]);
            }
            int count = list.get(0)>=k?1:0;
            for (int i=m;i<n;i++){
                int temp = queue.poll();
                if (temp==list.get(0))
                    list.remove(0);
                while (list.size()>0 && nums[i]<list.get(list.size()-1)){
                    list.remove(list.size()-1);
                }
                list.add(nums[i]);
                queue.offer(nums[i]);
                count += list.get(0)>=k?1:0;
            }
            System.out.println(count);
        }
    }

    /**
     * 填数游戏
     * 时间限制： 3000MS
     * 内存限制： 589824KB
     * 题目描述：
     * 小团和小美正在玩一个填数游戏，这个游戏是给一个等式，其中有一些数被挖掉了，你需要向其中填数字，使得等式成立。
     *
     * 比如 ___+12=34，那么横线填的一定是22
     *
     * 现在，这个游戏到了最后一关，这一关的等式很奇特：_+_+_+...+_=n
     *
     * 这里可以填任意多个正整数（甚至可能是1个），只要这些数的和等于n即可。
     *
     * 但是，有一个额外的限制，填入的所有数必须小于等于k，大于等于1，填入的数的最大值必须大于等于d。
     *
     * 请你计算，有多少个不同的等式满足这些限制。由于答案可能很大，请将答案mod(998244353)后输出。
     *
     *
     *
     * 输入描述
     * 输入包含三个数n,k,d（1≤d≤k≤n≤1000）
     *
     * 输出描述
     * 输出包含一行，即方案数。
     *
     *
     * 样例输入
     * 5 3 2
     * 样例输出
     * 12
     *
     * 提示
     * 样例解释
     * 2+3=5
     * 3+2=5
     * 1+1+3=5
     * 1+3+1=5
     * 3+1+1=5
     * 1+2+2=5
     * 2+1+2=5
     * 2+2+1=5
     * 1+1+1+2=5
     * 1+1+2+1=5
     * 1+2+1+1=5
     * 2+1+1+1=5
     * 共12种填法
     */
    private static int count = 0;
    private static Set<String> set = new HashSet<>();
    public static void meituan03(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int d = sc.nextInt();
        int[] nums = new int[k];
        for (int i=0;i<k;i++){
            nums[i] = i+1;
        }
        for (int i=d;i<=k;i++){
            List<Integer> list = new ArrayList<>();
            huisu(nums,n-i,i,list);
        }
        System.out.println(count);
    }

    public static void huisu(int[] nums,int n,int d,List<Integer> list){
        if(n<0)
            return;
        else if(n==0){
            List<Integer> temp = new ArrayList<>(list);
            temp.add(d);
            Collections.sort(temp);
            StringBuilder sb = new StringBuilder();
            for(int i : temp){
                sb.append(i);
            }
            if (!set.contains(sb.toString())){
                count = count + ((list.size()+1)%998244353)%998244353;
                set.add(sb.toString());
            }
            temp.clear();
        }
        else{
            for (int i=0;i<nums.length;i++){
                list.add(nums[i]);
                huisu(nums,n-nums[i],d,list);
                list.remove(list.size()-1);
            }
        }
    }

    public static void meituan05(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] things = new int[n][3];
        for (int i=0;i<n;i++){
            // 优先
            things[i][0] = sc.nextInt();
            // 必要
            things[i][1] = sc.nextInt();
            // 序号
            things[i][2] = i+1;
        }
        Arrays.sort(things, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1])
                    return o2[1]-o1[1];
                else{
                    return o2[0]-o1[0];
                }
            }
        });
        for (int[] t : things){
            System.out.print(t[2]);
            System.out.print(" ");
        }
    }
}
