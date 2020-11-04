import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

public class kuaishou {

    public static void main(String[] args) {
//        int[] a = new int[]{8,9,7};
//        int[] b = new int[]{5,8,3};
//        WaitInLine(a,b);
        char[][] c = new char[][]{{'*','.','*','*','.'},{'*','.','*','*','*'},{'*','.','*','*','.'}};
        char[][] ca = new char[][]{{'*','*'},{'*','*'}};

        System.out.print(GetMaxStaffs(c));
    }

    public static void k1(){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] ch = s.toCharArray();
        int[] result = new int[3];
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<ch.length;i++){
            if(ch[i]=='('){
                result[1]++;
                stack.push(ch[i]);
            }
            else if(ch[i]==')'){
                if(!stack.empty()){
                    stack.pop();
                    result[0]++;
                    result[1]--;
                }
                else
                    result[2]++;
            }
        }
        for (int i=0;i<3;i++){
            System.out.print(result[i]+" ");
        }
    }

    public static int[] GetPowerFactor (int R, int N) {
        // write code here
        List<Integer> list = new ArrayList<>();
        int l = 0;
        while (R!=0){
            int m = R%N;
            if (m!=1&&m!=0)
                return new int[]{};
            else{
                list.add(m);
                if(m==1)
                    l++;
                R = (R-m)/N;
            }
        }
        int[] r = new int[l];
        int index = 0;
        for (int i=0;i<list.size();i++){
            if (list.get(i)==1){
                r[index] = i;
                System.out.print(r[index]+" ");
                index++;
            }
        }
        return r;
    }

    /**
     * 根据顾客属性计算出顾客排队顺序
     * @param a int整型一维数组 顾客a属性
     * @param b int整型一维数组 顾客b属性
     * @return int整型一维数组
     */
    public static int[] WaitInLine (int[] a, int[] b) {
        // write code here
        int l = a.length;
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for(int i=1;i<l;i++){
            int sum = Integer.MAX_VALUE;
            int index = 0;
            for(int j=0;j<=i;j++){
                list.add(j,i);
                int s = value(list,a,b);
                if(s<sum){
                    index=j;
                    sum=s;
                }
                list.remove(j);
            }
            list.add(index,i);
        }
        int[] r = new int[l];
        for (int i=0;i<l;i++){
            r[i]=list.get(i)+1;
            System.out.print(r[i]+",");
        }
        return r;
    }

    public static int value(List<Integer> list,int[] a,int[] b){
        int sum = 0;
        int l = list.size();
        for(int i=0;i<l;i++){
            int index = list.get(i);
            sum += ((a[index]*i)+(b[index]*(l-i-1)));
        }
        return sum;
    }


    // 工位办公
    public static int count = 0;
    public static int max = 0;
    public static int GetMaxStaffs (char[][] pos) {
        // write code here
        int m = pos.length;
        int n = pos[0].length;
        doit(pos,0,0,m,n);
        return max;
    }

    public static void doit(char[][] pos,int sRow,int sCol,int m,int n){
        if(sRow==m-1&&sCol==n-1){
            if(pos[sRow][sCol]=='.')
                count++;
            max = Math.max(max,count);
        }
        else{
            for (int i=0;i<m;i++){
                for (int j=0;j<n;j++){
                    if(pos[i][j]=='.'){
                        change(pos,i,j);
                        doit(pos,i,j,m,n);
                        re_change(pos,i,j);
                    }
                }
            }
        }
    }

    public static void change(char[][] pos,int i,int j){
        count++;
        pos[i][j]='_';
        if(i-1>=0&&pos[i-1][j]=='.')
            pos[i-1][j]='|';
        if(i+1<pos.length&&pos[i+1][j]=='.')
            pos[i+1][j]='|';
        if(j-1>=0&&pos[i][j-1]=='.')
            pos[i][j-1]='|';
        if(j+1<pos[0].length&&pos[i][j-1]=='.')
            pos[i][j+1]='|';
    }

    public static void re_change(char[][] pos,int i,int j){
        count--;
        pos[i][j]='*';
        if(i-1>=0&&pos[i-1][j]=='|')
            pos[i-1][j]='.';
        if(i+1<pos.length&&pos[i+1][j]=='|')
            pos[i+1][j]='.';
        if(j-1>=0&&pos[i][j-1]=='|')
            pos[i][j-1]='.';
        if(j+1<pos[0].length&&pos[i][j-1]=='|')
            pos[i][j+1]='.';
    }
}
