import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class smallMi {
    public static void main(String[] args) {
        xiaomi01();
    }

    /**
     * 字符串有效判断
     * 时间限制： 3000MS
     * 内存限制： 589824KB
     * 题目描述：
     * 给定一个只包括'(',')','{','}','[',']' 的字符串,判断字符串是否有效。
     *
     * 有效字符串需满足:
     *
     *    1.左括号必须用相同类型的右括号闭合。
     *
     *    2. 左括号必须以正确的顺序闭合。
     *
     * 注意空字符串可被认为是有效字符串。
     *
     *
     *
     * 输入描述
     * 待判断的字符串，多个字符串需换行输入
     *
     * 输出描述
     * 每个字符串的判断结果，多个结果需换行输出
     *
     *
     * 样例输入
     * ()[]{}
     * ([)]
     * {[]}
     * 样例输出
     * true
     * false
     * true
     */
    public static void xiaomi01(){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String s = sc.next();
            if (s.equals("")) {
                System.out.println(true);
                break;
            }
            Stack<Character> stack = new Stack<>();
            boolean res = true;
            for (int i = 0; res && i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '{' || c == '[') {
                    stack.push(c);
                }
                else if(!stack.isEmpty()) {
                    char temp = stack.pop();
                    if (!((temp == '(' && c == ')') || (temp == '[' && c == ']') || (temp == '{' && c == '}'))) {
                        res = false;
                    }
                }
                else{
                    res = false;
                }
            }
            System.out.println(stack.isEmpty() && res);
        }
    }

    /**
     * 字符串筛选
     * 时间限制： 3000MS
     * 内存限制： 589824KB
     * 题目描述：
     * 给定一个字符串, 需要去除所有之前曾经出现过的字符，只保留第一次出现的字符
     *
     *
     *
     * 输入描述
     * 输入
     *
     * 输出描述
     * 输出
     *
     *
     * 样例输入
     * hello, welcome to xiaomi
     * 样例输出
     * helo, wcmtxia
     */
    public static void xiaomi02(){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if(!set.contains(c)){
                sb.append(c);
                set.add(c);
            }
        }
        System.out.println(sb.toString());
    }

}
