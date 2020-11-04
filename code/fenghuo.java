import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class fenghuo {
    public static void main(String[] args) {
        fh02();
    }

    /**
     * 01串
     * 时间限制： 3000MS
     * 内存限制： 589824KB
     * 题目描述：
     * 自从学习了计算机编程，小明就彻底爱上了0和1，很多01串经常在他脑子里面浮现。
     *
     * 但是他从小就害怕警察，虽然他并没有犯过啥大错误，最多就是在小花的新衣服上画朵小花，在小红的白裙子上点上几个小红点......所以他很害怕110。
     *
     * 现在给你一个01串，请你编写一个程序计算在这个01串中不包含110的最长子串的长度。
     *
     *
     *
     * 输入描述
     * 单组输入。
     *
     * 输入一行，包含一个01串S（在S中只包含0和1，有可能输入全为0或者1的串），其长度1<=|S|<=1000000。
     *
     * 输出描述
     * 输出01串S中不包含110的最长子串的长度。
     *
     *
     * 样例输入
     * 1101010110010110
     * 样例输出
     * 8
     *
     * 提示
     * 在样例中，第2个字符到第9个字符组成的子串10101011为不包含110的最长子串，其长度为8。
     */
    public static void fh01(){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        if (s.length()<=2)
            System.out.println(s.length());
        else{
            // 以当前位置结尾满足条件的字串长度
            int[] dp = new int[s.length()];
            dp[0] = 1;
            dp[1] = 2;
            // 记录最长字串的长度
            int max = 2;
            for (int i=2;i<s.length();i++){
                // 若不满足条件，重置dp为最短的字串长度
                if (s.charAt(i) == '0' && (s.charAt(i-1) == '1' && s.charAt(i-2) == '1'))
                    dp[i] = 2;
                else{
                    dp[i] = dp[i-1] + 1;
                    max = Math.max(max,dp[i]);
                }
            }
            System.out.println(max);
        }
    }

    /**
     * 战术遮挡
     * 时间限制： 3000MS
     * 内存限制： 589824KB
     * 题目描述：
     * 人的视力不能看到掩体之后的事物，在一场战争中，我们希望对方尽可能的低估我方的战斗力这样才能出其不意。
     *
     * 某个军事参谋效仿孙膑，把某些小规模部队隐藏在大规模部队中，这样，就使得军队数量看起来变少了。
     *
     * 已知，如果某部队A的人数小于等于另一支部队B人数的1/3，则可以将A藏于B中，且不被人发现。不支持嵌套，例如A小于B的三分之一，可将A藏于B，如果又存在B是C的三分之一，不可再将B藏于C。
     *
     * 现在已知我方共有n支部队，且知道每支部队的人数，请问，在最优方案下，我们暴露给敌人的部队数量有几支。
     *
     *
     *
     * 输入描述
     * 输入第一行包含一个正整数n，表示我方有n支部队。(1<=n<=50000)
     *
     * 第二行有n个整数，表示每支部队的人数，中间用空格隔开。(1<=a_i<=10^8)
     *
     * 输出描述
     * 输出仅包含一个整数，表示最少的游戏局数。
     *
     *
     * 样例输入
     * 5
     * 2 6 7 7 10
     * 样例输出
     * 4
     */
    public static void fh02(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i=0;i<n;i++){
            nums[i] = sc.nextInt();
        }
        // 将部队人数升序排列
        Arrays.sort(nums);
        // 找到人数最多部队三分之一人数部队若存在的下标位置
        int index = Arrays.binarySearch(nums,nums[n-1]/3);
        // 处理不存在的情况
        if (index<0)
            index = Math.abs(index)-1;
        int left = index;
        int right = n-1;
        int count = 0;
        // 由高到低进行判断
        while (left>=0 && right>index){
            // 队伍数量增加
            count++;
            // 若满足隐藏条件，同时左移
            if(nums[left] <= nums[right]/3){
                left--;
                right--;
            }
            else
                left--;
        }
        // 需要加上剩余不可隐藏的队伍数量
        System.out.println(count+left+(right-index+1));
    }

    /**
     * 报数问题
     * 时间限制： 3000MS
     * 内存限制： 589824KB
     * 题目描述：
     * 有n个人排成一条直线，从左到右编号分别为1到n。现在从第1个人开始报数，在报数过程中，如果有人报到m则出列，下一个人将继续从1开始报数。第n个人报数完之后再接着往回报数，即倒数第2个人继续报下一个数；当报到第1个人后，第2个人再接着报数。如此循环，直到只留下一个人为止。
     *
     * 例如当n=2，m=3时，第1个人报1，第2个人报2，接下来第1个人报3，出列，留下第2个人。
     *
     * 当输入n和m时，请问通过（n-1）轮报数后，最后留下的那个人的编号是多少？
     *
     *
     *
     * 输入描述
     * 单组输入。
     *
     * 输入两个正整数n和m，n<=10000，m<=1000。两个正整数之间用空格隔开。
     *
     * 输出描述
     * 输出最后留下的那个人的编号。
     *
     *
     * 样例输入
     * 5 3
     * 样例输出
     * 1
     */
    public static void fh03(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i=1;i<=n;i++){
            list.add(i);
        }
        // 记录报数下标
        int index = 0;
        // 当前报数
        int count = 1;
        // 判断报数方向
        boolean flag = false;
        while (list.size()!=1){
            // 不进行出队操作
            if(count != m){
                // 报数加1
                count++;
                // 判断是往左报数还是往右报数
                if (flag){
                    index--;
                    // 到达队首，改变方向
                    if (index < 0){
                        index = 1;
                        flag = false;
                    }
                }
                else {
                    index++;
                    // 到达队尾，改变方向
                    if (index == list.size()) {
                        index = list.size() - 2;
                        flag = true;
                    }
                }
            }
            // 进行出队操作
            else{
                // 重置报数
                count = 1;
                // 出队
                list.remove(index);
                // 若是往左报数，需将下标减1
                if (flag)
                    index--;
                // 若出队的元素是队尾元素，需将下标减1
                if (index==list.size())
                    index--;
            }
        }
        System.out.println(list.get(0));
    }
}
