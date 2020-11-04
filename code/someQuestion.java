import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import java.net.BindException;
import java.util.*;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class someQuestion {
    // 功能：输出文字图片
    // 设置str,font的样式以及输出文件目录
    public static void wordImage(String str, Font font, File outFile) throws Exception {
        String[] s = str.split("\n");
        int max_width = 0;
        int row_num = 0;
        int index = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i].length() > max_width) {
                max_width = s[i].length();
                index = i;
            }
            row_num++;
        }
        String max_str = s[index];
        //获取font的样式应用在str上的整个矩形
        Rectangle2D r = font.getStringBounds(max_str, new FontRenderContext(AffineTransform.getScaleInstance(1, 1), false, false));
        int unitHeight = (int) Math.floor(r.getHeight());//获取单个字符的高度
        //获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
        int width = (int) Math.round(r.getWidth()) + 1;
        int height = unitHeight * row_num + 8;//把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
        //创建图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);//先用白色填充整张图片,也就是背景
        g.setColor(Color.black);//在换成黑色
        g.setFont(font);//设置画笔字体
        for (int i = 0; i < s.length; i++) {
            g.drawString(s[i], 0, font.getSize() * (i + 1));
        }
        g.dispose();
        ImageIO.write(image, "png", outFile);//输出png图片
    }

    // 某个笔试题，输出第一个不重复的字符
    public static int FirstNotRepeatingChar(String str) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (!map.containsKey(str.substring(i, i + 1)))
                map.put(str.substring(i, i + 1), i);
            else
                map.put(str.substring(i, i + 1), -1);
        }
        int r = 10001;
        for (Integer i : map.values()) {
            if (i != -1 && i < r)
                r = i;
        }
        if (r != 10001)
            return r;
        else
            return 0;
    }

    // 九皇后问题
    // 判断这个位置是否满足放皇后的条件
    public static boolean isOK(int[] x, int row, int col, int n) {
        int leftCol = col - 1;
        int rightCol = col + 1;
        for (int r = row - 1; r >= 0; r--) {
            if (x[r] == col)
                return false;
            if (leftCol >= 0 && x[r] == leftCol)
                return false;
            if (rightCol < n && x[r] == rightCol)
                return false;
            leftCol--;
            rightCol++;
        }
        return true;
    }
    // 九皇后问题主函数（思路：回溯法）
    public static void setQueen(int[] x, int row, int n) {
        if (row == n) {
            for (int i : x)
                System.out.println(i);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isOK(x, row, col, n)) {
                x[row] = col;
                setQueen(x, row + 1, n);
            }
        }
    }

    // 回溯法，解决某个字符排列的算法问题，不记得了
    public static void ssssetStr(char[] ch, int i) {
        if (i == ch.length - 1) {
            String ss = "";
            for (char c : ch) {
                ss += String.valueOf(c);
            }
            System.out.println(ss);
        } else {
            for (int j = i; j < ch.length; j++) {
                swap(ch, i, j);
                ssssetStr(ch, i + 1);
                swap(ch, i, j);
            }
        }
    }
    // 上面字符排列问题的辅助函数，交换两个位置的字符
    public static void swap(char[] ch, int i, int j) {
        if (i != j) {
            char t = ch[i];
            ch[i] = ch[j];
            ch[j] = t;
        }
    }

    // 字符串转Int
    public static int StrToInt(String str) {
        if (str == null || str == " ")
            return 0;
        int result = 0;
        boolean b = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && (c == '+' || c == '-')) {
                if (c == '+')
                    continue;
                else if (c == '-') {
                    b = false;
                    continue;
                }
            }
            if (c >= '0' && c <= '9') {
                result = result * 10 + (c - '0');
            } else
                return 0;
        }
        if (!b)
            result *= -1;
        return result;
    }

    // 判断字符串是否是数字
    public static boolean isNumeric(String str) {
        boolean sign = false;
        boolean e = false;
        boolean dot = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '+' || c == '-') {
                if (!e && sign)
                    return false;
                if (!(i == 0 || str.charAt(i - 1) == 'e' || str.charAt(i - 1) == 'E'))
                    return false;
                sign = true;
            } else if (c == 'e' || c == 'E') {
                if (e)
                    return false;
                e = true;
            } else if (c == '.') {
                if (e || dot)
                    return false;
                dot = true;
            } else if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    // leetcode：输出一个数组能排列出来的最小数字（存在问题，不是答案）
    public static String PrintMinNumber(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                String s1 = numbers[i] + "";
                String s2 = numbers[j] + "";
                if (s1.concat(s2).compareTo(s2.concat(s1)) > 0) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        String result = "";
        for (int i : numbers) {
            result += i + "";
        }
        return result;
    }

    // leetcode：丑数
    public static int GetUglyNumber_Solution(int index) {
        if (index < 7)
            return index;
        int[] list = new int[index];
        list[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0, num;
        for (int i = 0; i < index; i++) {
            num = Math.min(list[p2] * 2, Math.min(list[p3] * 3, list[p5] * 5));
            if (num == list[p2] * 2) p2++;
            if (num == list[p3] * 3) p3++;
            if (num == list[p5] * 5) p5++;
            list[i] = num;
        }
        return list[list.length - 1];
    }

    // 通过二叉树的后续数组判断该二叉树是否是一个二叉搜索树
    public boolean VerifySquenceOfBST(int[] sequence) {
        // 将数组排序得到中序遍历，重建二叉树
        // 再判断该二叉树是不是搜索二叉树
        int[] in = sequence.clone();
        Arrays.sort(in);
        return reTree(in, 0, in.length - 1, sequence, 0, sequence.length - 1);
    }
    // 根据后序遍历与中序遍历重建二叉树
    public static boolean reTree(int[] in, int s_in, int e_in, int[] beh, int s_beh, int e_beh) {
        for (int i = s_in; i < e_in; i++) {
            if (in[i] == beh[e_beh]) {
                if (s_beh + i - s_in - 1 < 0 && beh[e_beh] < beh[e_beh - 1])
                    return reTree(in, i + 1, e_in, beh, s_beh + i - s_in, e_beh - 1);
                else if (beh[e_beh] > beh[s_beh + i - s_in - 1] && beh[e_beh] < beh[e_beh - 1])
                    return reTree(in, s_in, i - s_in - 1, beh, s_beh, s_beh + i - s_in - 1) || reTree(in, i + 1, e_in, beh, s_beh + i - s_in, e_beh - 1);
                else
                    return false;
            }
        }
        return true;
    }

    // leetcode：将空格字符替换为指定字符
    public static String replaceSpace(String iniString, int length) {
        // write code here
        char[] ch = iniString.toCharArray();
        String s = "";
        for (int i = 0; i < length; i++) {
            if (ch[i] == ' ')
                s += "%20";
            else
                s += ch[i];
        }
        return s;
    }

    // leetcode：压缩字符串
    public static String zipString(String iniString) {
        // write code here
        char[] ch = iniString.toCharArray();
        String s = "";
        int count = 1;
        for (int i = 0; i < ch.length; i++) {
            if (i == ch.length - 1) {
                if (ch[i] == ch[i - 1]) {
                    count++;
                }
                s += String.valueOf(ch[i]) + String.valueOf(count);
            } else {
                if (ch[i] != ch[i + 1]) {
                    s += String.valueOf(ch[i]) + String.valueOf(count);
                    count = 1;
                } else
                    count++;
            }
        }
        System.out.println(s);
        if (s.length() < iniString.length())
            return s;
        else
            return iniString;
    }

    // 某个算法题
    public static int getResult(int n, int m) {
        // write code here
        boolean[] b = new boolean[n];
        int k = -1;
        for (int i = n; i != 1; i--) {
            int c = 0;
            while (c != m) {
                k = (k + 1) % n;
                if (!b[k])
                    c++;
            }
            b[k] = true;
        }
        for (int i = 0; i < n; i++) {
            if (!b[i])
                return i + 1;
        }
        return 0;
    }

    // 某个算法题
    public static ArrayList<Integer> twoStacksSort(int[] numbers) {
        // write code here
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++)
            nums.add(numbers[i]);
        ArrayList<Integer> list = new ArrayList<>();
        while (nums.size() > 0) {
            int temp = nums.remove(0);
            if (list.size() == 0 || temp > list.get(0))
                list.add(0, temp);
            else {
                int l = nums.size();
                while (list.size() != 0 && temp < list.get(0)) {
                    nums.add(0, list.remove(0));
                }
                list.add(0, temp);
                while (nums.size() > l) {
                    list.add(0, nums.remove(0));
                }
            }
        }
        return list;
    }


    /**
     * 2020年网易网上笔试第四题
     * 一个长度为n的数字序列，在其上进行q次操作
     * 每次操作有一个查询的数字x，须将序列中所有大于或等于x的数字全部减一，并输出具体操作的数据数量
     * 输入描述：
     * 第一行n、q，表示数字个数和操作个数
     * 一行n个数，表示初始化的数字序列
     * q行，每行一个数，表示查询的数字x
     */
    // 该解法时间复杂度存在问题
    public static void Wy2020_04() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        int[] nList = new int[n];
        for (int i = 0; i < n; i++)
            nList[i] = scanner.nextInt();
        int[] qList = new int[q];
        for (int i = 0; i < q; i++)
            qList[i] = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (nList[j] >= qList[i]) {
                    nList[j]--;
                    count++;
                }
            }
            qList[i] = count;
        }
        for (int i = 0; i < q; i++)
            System.out.println(qList[i]);
    }

    public static void main(String[] args) {
        // 不记得干啥的了。多线程相关问题
        Map<String, Object> returnMap = new HashMap<>();
        Callable<List> callable = new Callable<List>() {
            @Override
            public List call() throws Exception {
                return  new ArrayList(); //去service 查询数据返回
            }
        };

        Callable<List> callable2 = new Callable<List>() {
            @Override
            public List call() throws Exception {
                return  new ArrayList(); //去service 查询数据返回2
            }
        };

        Callable<List<String>> listCallable = new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return null;
            }
        };

        FutureTask<List> futureTask = new FutureTask<>(callable);
        FutureTask<List> futureTask2= new FutureTask<>(callable2);
        FutureTask<List<String>> listFutureTask = new FutureTask<>(listCallable);
        Thread t1 = new Thread(futureTask); //声明线程
        Thread t2 = new Thread(futureTask2);
        Thread t3 = new Thread(listFutureTask);
        t1.start(); //开始线程
        t2.start();
        t3.start();
        try {
            List list1 = futureTask.get(); //得到结果
            List list2 = futureTask2.get();
            List list3 = listFutureTask.get();
            returnMap.put("list1",list1); //将结果放入map中返回
            returnMap.put("list2",list2);
            returnMap.put("list3",list3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            t1.interrupt(); //关闭线程
            t2.interrupt();
            t3.interrupt();
        }
    }

    // 0914百度笔试 第二题
    public static void baidu02(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        // 存储每个人所在的队列及其在队列中的位置
        int[][] arrays = new int[n+1][2];
        // 初始化
        for(int i=1;i<=n;i++){
            arrays[i][0] = i;
            arrays[i][1] = 0;
        }
        int m = scanner.nextInt();
        Map<Integer,Queue<Integer>> map = new HashMap<>();
        for(int i=0;i<m;i++){
            String operate = scanner.next();
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            // 关闭
            if(operate.equals("C")){
                if(!map.containsKey(b)){
                    if (!map.containsKey(a)){
                        Queue<Integer> queue = new LinkedList<>();
                        queue.offer(b);
                        queue.offer(a);
                        arrays[a][0] = b;
                        arrays[a][1] = 1;
                        map.put(b,queue);
                    }
                    else{
                        Queue<Integer> queueA = map.get(a);
                        Queue<Integer> queueB = new LinkedList<>();
                        queueB.offer(b);
                        while (queueA.size() != 0){
                            int temp = queueA.poll();
                            arrays[temp][0] = b;
                            arrays[temp][1] = queueB.size()-1;
                            queueB.offer(temp);
                        }
                        map.remove(a);
                        map.put(b,queueB);
                    }
                }
                else{
                    if (!map.containsKey(a)){
                        Queue<Integer> queue = map.get(b);
                        arrays[a][0] = b;
                        arrays[a][1] = queue.size()-1;
                        queue.offer(a);
                        map.put(b,queue);
                    }
                    else{
                        Queue<Integer> queueA = map.get(a);
                        Queue<Integer> queueB = map.get(b);
                        while (queueA.size() != 0){
                            int temp = queueA.poll();
                            arrays[temp][0] = b;
                            arrays[temp][1] = queueB.size()-1;
                            queueB.offer(temp);
                        }
                        map.remove(a);
                        map.put(b,queueB);
                    }
                }
            }
            // 查询
            else{
                if (arrays[a][0] == arrays[b][0]){
                    System.out.println(Math.abs(arrays[a][1] - arrays[b][1]));
                }
                else
                    System.out.println(-1);
            }
        }
    }


    // 0913新浪笔试 第一题
    public static void sina01(){
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        while(sc.hasNext()){
            list.add(sc.nextInt());
        }
        TreeNode root = createTree(list,0);
        root = bian(root);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.size()!=0){
            int l = queue.size();
            for(int i=0;i<l;i++){
                TreeNode temp = queue.poll();
                System.out.print(temp.val);
                System.out.print(" ");
                if(temp.left!=null)
                    queue.offer(temp.left);
                if(temp.right!=null)
                    queue.offer(temp.right);
            }
        }
    }

    // 二叉树节点定义
    public static class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) {
            this.val = x;
            this.left = null;
            this.right = null;
        }
    }
    public static TreeNode createTree(List<Integer> list,int index){
        TreeNode root = new TreeNode(list.get(index));
        if (2*index+1<list.size())
            root.left = createTree(list,2*index+1);
        if(2*index+2<list.size())
            root.right = createTree(list,2*index+2);
        return root;
    }
    public static TreeNode bian(TreeNode root){
        // 根据题意，若左为空，右必为空
        if (root.left == null)
            return root;
        TreeNode newRoot = bian(root.left);
        TreeNode newRight = newRoot;
        while(newRight.right != null){
            newRight = newRight.right;
        }
        newRight.left = root.right;
        newRight.right = root;
        newRight.right.left = null;
        newRight.right.right = null;
        return newRoot;
    }
    // 0913新浪笔试 1 End

    // 泛型方法的定义方式
//    public static <T> int compare(T a,T b){
//
//    }

    // 某个算法题
    public static List<List<Integer>> list = new ArrayList<>();
    public static List<List<Integer>> permuteUnique(int[] nums){
        // 创建HashSet用于去重判断
        HashSet<String> set = new HashSet<>();
        all(nums,0,nums.length-1,set);
        return list;
    }
    // 全排列的动态规划
    public static void all(int[] nums,int start,int end,HashSet<String> set){
        // 左指针与右指针重回，循环退出条件
        if(start==end){
            // 用字符串记录，判断是否重复
            String s = "";
            for(int i=0;i<nums.length;i++)
                s+=nums[i]+",";
            if(!set.contains(s)){
                set.add(s);
                List<Integer> l = new ArrayList<>();
                String[] ss = s.split(",");
                for(int i=0;i<nums.length;i++){
                    l.add(Integer.valueOf(ss[i]));
                }
                list.add(l);
            }
            return;
        }
        else{
            // 从第一个元素开始，一次与后面位置的元素进行交换，以达到全排列的效果
            for(int i=start;i<=end;i++){
                swap(nums,start,i);//当前位置元素与后面位置的元素依次替换
                all(nums, start+1,end,set);
                swap(nums,start,i);//将位置换回来,继续做下一个排列
            }
        }
    }
    private static void swap(int [] array,int s,int i){
        int temp=array[s];
        array[s]=array[i];
        array[i]=temp;
    }

    // 某个leetcode算法题
    public static String tictactoe(String[] board) {
        int n = board.length;
        char[][] table = new char[n][n];
        int rowX = 0;
        int rowO = 0;
        int[] colX = new int[n];
        int[] colO = new int[n];
        int nullStr=0;
        boolean end = false;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                table[i][j] = board[i].charAt(j);
                if(table[i][j]=='X'){
                    rowX++;
                    colX[j]++;
                }
                else if(table[i][j]=='O'){
                    rowO++;
                    colO[j]++;
                }
                else
                    nullStr++;
            }
            if(rowO==n)
                return "O";
            else if(rowX==n)
                return "X";
            else{
                rowX = 0;
                rowO = 0;
            }
        }
        for(int i=0;i<n;i++){
            if(colO[i]==n)
                return "O";
            else if(colX[i]==n)
                return "X";
        }
        boolean l=true,r=true;
        for(int i=0,j=n-1;i<n-1&&j>0;){
            if(l&&table[i][i]==table[i+1][i+1])
                l = true;
            else
                l = false;
            if(r&&table[i][j]==table[i+1][j-1])
                r = true;
            else
                r = false;
            i++;
            j--;
        }
        if(l)
            return String.valueOf(table[0][0]);
        if(r)
            return String.valueOf(table[0][n-1]);
        if(nullStr!=0)
            return "Pending";
        else
            return "Draw";
    }

    // 某个算法题
    public static int myAtoi(String str) {
        Pattern pattern = Pattern.compile("\\s*([+|-]?\\d+[\\.\\d+]?)");
        Matcher m = pattern.matcher(str);
        String result;
        if(m.lookingAt()){
            result = m.group();
        }
        else
            return 0;
        int r;
        boolean b = result.charAt(0)=='-';
        try{
            result=result.replace(" ","");
            r = Integer.parseInt(result);
        }catch(Exception e){
            if(b)
                r = Integer.MIN_VALUE;
            else
                r = Integer.MAX_VALUE;
        }
        return r;
    }

    // 牛客网_小米 风口的猪
    public static int calculateMax(int[] prices) {
        if (prices.length < 2)
            return 0;
        int r = 0;
        for (int i = 0; i < prices.length; i++) {
            r = Math.max(r, calIntertalMax(prices, 0, i) + calIntertalMax(prices, i, prices.length));
        }
        return r;
    }
    public static int calIntertalMax(int[] prices, int left, int right) {
        int minPrice = Integer.MAX_VALUE;
        int maxMoney = 0;
        for (int i = left; i < right; i++) {
            if (prices[i] < minPrice)
                minPrice = prices[i];
            else
                maxMoney = Math.max(maxMoney, prices[i] - minPrice);
        }
        return maxMoney;
    }

    // 算法题：循环记录文件的错误信息
    public static void fileRecord() {
        Scanner sc = new Scanner(System.in);
        String path = "E:\\V1R2\\product\\fpgadrive.c";
        String[] paths = path.split("\\\\");
        while (sc.hasNext()) {
            boolean b = true;
            String str = sc.nextLine();
            if (str.length() <= 8)
                b = false;
            int count = 0;
            String[] regexs = new String[]{"[0-9]*", "[a-z]*", "[A-Z]*", "[^a-zA-Z0-9]*"};
            for (int i = 0; i < regexs.length; i++) {
                if (str.matches(regexs[i]))
                    count++;
            }
            if (count < 3)
                b = false;
            for (int i = 0; i < str.length() - 3; i++) {
                if (str.substring(i + 3).contains(str.substring(i, i + 3)))
                    b = false;
            }
            if (b)
                System.out.println("OK");
            else
                System.out.println("NG");
        }
    }

    // leetcode求数组中除自身外所有数乘积
    // 思路：左右遍历累乘积，当前位置除自身外的所有数乘积为左右累乘积之积
    public static int[] s(int[] nums) {
        int l = nums.length;
        int[] left = new int[l];
        int[] right = new int[l];
        int le = 1, ri = 1;
        for (int i = 0; i < l; i++) {
            le = le * nums[i];
            left[i] = le;
            ri = ri * nums[l - i - 1];
            right[l - i - 1] = ri;
        }
        int[] output = new int[l];
        for (int i = 0; i < l; i++) {
            output[i] = (i == 0 ? 1 : left[i - 1]) * (i == l - 1 ? 1 : right[i + 1]);
        }
        return output;
    }

    private static boolean b;
    // leetcode 跳跃游戏递归解法（超出时间限制）
    public static void jumpTo(int[] nums, int start) {
        // 若b为真，直接结束
        if (b)
            return;
            // 若达到数组最后一位，则b为真
        else if (start == nums.length - 1)
            b = true;
            // 若当前可用步数为0，则b为假
        else if (nums[start] == 0)
            b = false;
        else {
            for (int i = 0; i < nums[start]; i++)
                jumpTo(nums, start + i + 1);
        }
    }

    // 数字组合算法（回溯）
    public static void searchCombine(List<List<Integer>> list, List<Integer> l, int[] candidates, int index, int target) {
        // 目标值若小于0，则结束本次循环
        if (target < 0)
            return;
            // 目标值若等于0，则找到一组解，将其存入结果数组中
        else if (target == 0) {
            list.add(new ArrayList<>(l));
        } else {
            // 从当前位置index开始循环回溯
            for (int i = index; i >= 0; i--) {
                // 往列表中增加元素
                l.add(candidates[i]);
                // 目标值减去，回溯
                searchCombine(list, l, candidates, i, target - candidates[i]);
                // 上一次回溯添加的元素无解，移除
                l.remove(l.size() - 1);
            }
        }
    }

    // 某个算法题
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        if (nums.length == 0)
            return list;
        for (int i = 0; i < nums.length; i++) {
            int length = list.size();
            for (int j = 0; j < length; j++) {
                List<Integer> nl = new ArrayList<>();
                for (int x : list.get(j)) {
                    nl.add(x);
                }
                nl.add(nums[i]);
                list.add(nl);
            }
        }
        return list;
    }

    // 返回输入字符串中最长有效括号字串，输入的字符串仅包含(,)两种字符
    public static int LongestBracketMatch(String s) {
        if (s.length() < 2)
            return 0;
        List<Character> list = new ArrayList<>();
        int leftLength = 0;
        int rightLength = 0;
        int l = 0;
        char[] ch = s.toCharArray();
        // 从左至右遍历
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(')
                list.add(ch[i]);
            else {
                if (!list.isEmpty()) {
                    list.remove(list.size() - 1);
                    l += 2;
                } else {
                    leftLength = Math.max(l, leftLength);
                    l = 0;
                }
            }
        }
        leftLength = Math.max(l, leftLength);
        // 清空list并初始化l
        list.clear();
        l = 0;
        // 从右至左遍历
        for (int i = ch.length - 1; i >= 0; i--) {
            if (ch[i] == ')')
                list.add(ch[i]);
            else {
                if (!list.isEmpty()) {
                    list.remove(list.size() - 1);
                    l += 2;
                } else {
                    rightLength = Math.max(l, rightLength);
                    l = 0;
                }
            }
        }
        rightLength = Math.max(l, rightLength);
        return Math.max(leftLength, rightLength);
    }

    // 判断两个字符串是否为异构词
    public static boolean sameString(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        int[] t = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            t[s1.charAt(i) - 'a']++;
            t[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (t[i] != 0)
                return false;
        }
        return true;
    }

    // 罗马数字转阿拉伯数字
    public static int luoma2num(String s) {
        Map<Character, Integer> m = new HashMap<>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);
        char[] ch = s.toCharArray();
        int l = ch.length;
        int r = 0;
        for (int i = 0; i < l; i++) {
            if (ch[i] != 'I' && ch[i] != 'X' && ch[i] != 'C') {
                r += m.get(ch[i]);
            } else {
                if (i + 1 != l) {
                    if ((ch[i] == 'I' && (ch[i + 1] == 'V' || ch[i + 1] == 'X')) || (ch[i] == 'X' && (ch[i + 1] == 'L' || ch[i + 1] == 'C')) || (ch[i] == 'C' && (ch[i + 1] == 'D' || ch[i + 1] == 'M'))) {
                        r += m.get(ch[i + 1]);
                        r -= m.get(ch[i]);
                        i++;
                    } else
                        r += m.get(ch[i]);
                } else
                    r += m.get(ch[i]);
            }
        }
        return r;
    }

    /**
     * 最小栈
     */
    public static void MinStack() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Stack<Integer> stack = new Stack();
        ArrayList<Integer> list = new ArrayList<>();
        int m = 1000001;
        while (n > 0) {
            String s = sc.next();
            if (s.equals("push")) {
                int x = sc.nextInt();
                if (stack.empty()) {
                    list.add(x);
                    m = x;
                } else {
                    if (x <= m) {
                        list.add(x);
                        m = x;
                    }
                }
                stack.push(x);
            } else if (s.equals("getMin"))
                System.out.println(m);
            else {
                int peek = stack.pop();
                if (peek == list.get(list.size() - 1)) {
                    list.remove(list.size() - 1);
                    m = list.get(list.size() - 1);
                }
            }
            n--;
        }
    }

    /**
     * 牛客网 网易2019校招题：牛牛找工作
     */
    public static class work {
        int ability;
        int payment;

        work(int ability, int payment) {
            this.ability = ability;
            this.payment = payment;
        }
    }

    public static class person {
        int id;
        int ability;
        int pay;

        person(int id, int ability, int pay) {
            this.id = id;
            this.ability = ability;
            this.pay = pay;
        }
    }

    public static void wy2019_01() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        work[] works = new work[n];
        for (int i = 0; i < n; i++) {
            works[i] = new work(scanner.nextInt(), scanner.nextInt());
        }
        // 这样的排序用法需要记住
        Arrays.sort(works, Comparator.comparingInt(work -> work.ability));
        // 更改思路，将每份工作对应的报酬，更新为其能力范围内的最大值
        for (int i = 1; i < n; i++) {
            works[i].payment = Math.max(works[i - 1].payment, works[i].payment);
        }
        // 给员工能力值排序
        person[] persons = new person[m];
        for (int i = 0; i < m; i++) {
            persons[i] = new person(i, scanner.nextInt(), 0);
        }
        Arrays.sort(persons, Comparator.comparingInt(person -> person.ability));
        int index = 0;
        int im = 0;
        while (im < m) {
            if (persons[im].ability < works[index].ability) {
                if (index != 0) {
                    persons[im].pay = works[index - 1].payment;
                }
                im++;
            } else {
                if (index < n - 1)
                    index++;
                else {
                    persons[im].pay = works[n - 1].payment;
                    im++;
                }
            }
        }
        // 按编号恢复原始序列以便输出
        Arrays.sort(persons, Comparator.comparingInt(person -> person.id));
        for (int i = 0; i < m; i++)
            System.out.println(persons[i].pay);
    }

    // 斗鱼2020春招（实习生）在线笔试编程题（反转字符串）
    public static String reverse (String str) {
        // write code here
        if(str.length()==0)
            return str;
        char[] ch = str.toCharArray();
        int left = 0,right = str.length()-1;
        while(left<right){
            char c = ch[left];
            ch[left] = ch[right];
            ch[right] = c;
            left++;
            right--;
        }
        return String.valueOf(ch);
    }

}