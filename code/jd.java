import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class jd {
    public static void main(String[] args) {
        jd01();
    }

    /**
     * 提取年份
     * 时间限制： 3000MS
     * 内存限制： 589824KB
     * 题目描述：
     * 小明想从一段英文短文中提取潜在的年份信息，待匹配的年份的范围为1000年至3999年，包含1000和3999。
     *
     * 输入一段英文短文，按出现次序输出所提取到的所有可能的年份字符串。
     *
     *
     *
     * 输入描述
     * 单组输入，输入一段英文短文，可能包含字母大小写，标点符号及空格。（不超过2000个字符）
     *
     * 输出描述
     * 输出所提取到的所有可能的年份字符串，两两之间用一个空格隔开。
     *
     *
     * 样例输入
     * And millionaires will hold 46% of total wealth by 2019, the report says. This ratio is likely to increase in 2020.
     * 样例输出
     * 2019 2020
     */
    public static void jd01(){
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>();
        while (sc.hasNext()){
            String s = sc.next();
            String[] strs = s.split("[\\D\\W]");
            for (String str : strs){
                if (str.length()==4 && str.matches("[1-3][0-9][0-9][0-9]")){
                    if (!set.contains(str)){
                        sb.append(str).append(" ");
                        set.add(str);
                    }
                }
            }
        }
        System.out.print(sb.length()==0?"":sb.toString().substring(0,sb.length()-1));
    }

    /**
     * 王子与公主
     * 时间限制： 3000MS
     * 内存限制： 589824KB
     * 题目描述：
     * 在一个n行m列的二维地图中，王子的位置为(x1,y1)，公主的位置为(x2,y2)。
     *
     * 在地图中设有一些障碍物，王子只能朝上、下、左、右四个方向行走，且不允许走出地图也不允许穿越障碍物。
     *
     * 请编写一个程序判断王子是否可以顺利走到公主所在的位置。
     *
     *
     *
     * 输入描述
     * 多组输入，第1行输入一个正整数T表示输入数据的组数。
     *
     * 对于每一组输入数据：输入n+1行。
     *
     * 其中，第1行输入两个正整数n和m表示地图的大小，n为行数，m为列数。（n<=100,m<=100）
     *
     * 接下来n行表示地图，每一行都有m个字符，其中S表示王子的位置，E表示公主的位置，'.'表示可以通行，'#'表示障碍物（不能通行）。
     *
     * 输出描述
     * 针对每一组输入数据，判断王子是否能够到达公主所在位置？如果可以输出“YES”，否则输出“NO”。
     *
     *
     * 样例输入
     * 2
     * 2 2
     * .E
     * S.
     * 2 2
     * #E
     * S#
     * 样例输出
     * YES
     * NO
     */
    private static boolean flag = false;
    public static void jd02(){
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        for (int c=0;c<count;c++){
            int n = sc.nextInt();
            int m = sc.nextInt();
            char[][] matrix = new char[n][m];
            int startI = 0;
            int startJ = 0;
            for (int i=0;i<n;i++){
                String s = sc.next();
                for (int j=0;j<m;j++){
                    matrix[i][j] = s.charAt(j);
                    if (matrix[i][j] == 'S'){
                        startI = i;
                        startJ = j;
                    }
                }
            }
            boolean[][] visited = new boolean[n][m];
            dfs(matrix,n,m,startI,startJ,visited);
            System.out.println(flag ? "YES" : "NO");
            flag = false;
        }
    }

    public static void dfs(char[][] matrix,int n,int m,int i,int j,boolean[][] visited){
        if (flag || i<0 || i>=n || j<0 || j>=m || visited[i][j] || matrix[i][j] == '#')
            return;
        if (matrix[i][j] == 'E')
            flag = true;
        else{
            visited[i][j] = true;
            dfs(matrix,n,m,i+1,j,visited);
            dfs(matrix,n,m,i,j+1,visited);
            dfs(matrix,n,m,i-1,j,visited);
            dfs(matrix,n,m,i,j-1,visited);
            visited[i][j] = false;
        }
    }
}
