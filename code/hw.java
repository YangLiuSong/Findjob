import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;

import javax.swing.plaf.IconUIResource;
import java.util.*;

public class hw {
    public static void main(String[] args) {
        hw02();
    }

    public static void hw01(){
        Scanner sc = new Scanner(System.in);
        Map<String,Integer> map = new HashMap<>();
        String[] names = sc.nextLine().split(",");

        for(String s:names){
            // 验证名字是否为空
            if(s==""){
                System.out.println("error.0001");
                return;
            }
            // 验证名字合法性
            for(int i=0;i<s.length();i++){
                // 首字母是否为大写字母
                if(i==0){
                    if(!(s.charAt(i)>='A'&&s.charAt(i)<='Z')){
                        System.out.println("error.0001");
                        return;
                    }
                }
                // 除首字母外是否全部为小写字母
                else{
                    if(!(s.charAt(i)>='a'&&s.charAt(i)<='z')){
                        System.out.println("error.0001");
                        return;
                    }
                }
            }
            if(!map.containsKey(s))
                map.put(s,1);
            else{
                int c = map.get(s)+1;
                map.put(s,c);
            }
        }
        int max = 0;
        List<String> list = new ArrayList<>();
        for (Iterator<String> it = map.keySet().iterator(); it.hasNext(); ) {
            String name = it.next();
            int count = map.get(name);
            if (count>max){
                list.clear();
                list.add(name);
                max = count;
            }
            else if(count==max)
                list.add(name);
        }
        Collections.sort(list);
        System.out.print(list.get(0));
    }

    public static void hw02(){
        Scanner sc = new Scanner(System.in);
        String keyWord = sc.next();
        int keyL = keyWord.length();
        List<String> list = new ArrayList<>();
        String[] text = sc.next().split(",");
        try {
            for(int i=0;i<text.length/3;i++){
                StringBuilder stringBuilder = new StringBuilder("");
                if(!keyWord.equals(text[i*3].substring(0,keyL)))
                    continue;
                else{
                    String[] s01 = text[i*3].split("=");
                    if(s01[0].substring(keyL+1).equals("addr")){
                        if(s01[1].substring(0,2).equals("0x")||s01[1].substring(0,2).equals("0X")){
                            if ((s01[1].length()==2))
                                continue;
                            else{
                                for(int j=2;j<s01[1].length();j++){
                                    if(!((s01[1].charAt(j)>='0'&&s01[1].charAt(j)<='9')||(s01[1].charAt(j)>='a'&&s01[1].charAt(j)<='f')||(s01[1].charAt(j)>='A'&&s01[1].charAt(j)<='F'))) {
                                        System.out.println("FAIL");
                                        return;
                                    }
                                }
                            }
                            stringBuilder.append(s01[1]);
                            stringBuilder.append(" ");
                        }
                        else
                            continue;
                    }
                    else
                        continue;
                    String[] s02 = text[i*3+1].split("=");
                    if(s02[0].equals("mask")){
                        if(s02[1].substring(0,2).equals("0x")||s02[1].substring(0,2).equals("0X")){
                            if ((s02[1].length()==2))
                                continue;
                            else{
                                for(int j=2;j<s02[1].length();j++){
                                    if(!((s02[1].charAt(j)>='0'&&s02[1].charAt(j)<='9')||(s02[1].charAt(j)>='a'&&s02[1].charAt(j)<='f')||(s02[1].charAt(j)>='A'&&s02[1].charAt(j)<='F'))) {
                                        System.out.println("FAIL");
                                        return;
                                    }
                                }
                            }
                            stringBuilder.append(s02[1]);
                            stringBuilder.append(" ");
                        }
                        else
                            continue;
                    }
                    else
                        continue;
                    String[] s03 = text[i*3+2].split("=");
                    if(s03[0].equals("val")){
                        if(s03[1].substring(0,2).equals("0x")||s03[1].substring(0,2).equals("0X")){
                            if ((s03[1].length()==3))
                                continue;
                            else{
                                for(int j=2;j<s03[1].length()-1;j++){
                                    if(!((s03[1].charAt(j)>='0'&&s03[1].charAt(j)<='9')||(s03[1].charAt(j)>='a'&&s03[1].charAt(j)<='f')||(s03[1].charAt(j)>='A'&&s03[1].charAt(j)<='F'))) {
                                        System.out.println("FAIL");
                                        return;
                                    }
                                }
                            }
                            stringBuilder.append(s03[1].substring(0,s03[1].length()-1));
                        }
                        else
                            continue;
                    }
                    else
                        continue;
                    list.add(stringBuilder.toString());
                }
            }
        }catch (Exception e){
            System.out.println("FAIL");
            return;
        }
        if(list.size()==0)
            System.out.println("FAIL");
        else
            for (String s:list){
                System.out.println(s);
            }
    }

    /** 图的算法
     * 5 2 3 1 0 0
     * 1 20 2 3
     * 2 30 3 4 5
     * 3 50 4
     * 4 60
     * 5 80
     */
    public static class G{
        int id;
        int value;
        G[] next;

        public G(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public G[] getNext() {
            return next;
        }

        public void setNext(G[] next) {
            this.next = next;
        }
    }

    public static void hw03(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numOfUse = new int[n];
        for (int i=0;i<n;i++){
            numOfUse[i]=sc.nextInt();
        }
        int[][] idOfSons = new int[n][];
        G[] gs = new G[n];
        for(int i=0;i<n;i++){
            int id = sc.nextInt()-1;
            gs[i] = new G(id);
            int space = sc.nextInt();
            gs[i].setValue(space);
            int[] k = new int[numOfUse[i]];
            for(int j=0;j<numOfUse[i];j++){
                k[j] = sc.nextInt();
            }
            idOfSons[i]=k;
        }
        for(int i=0;i<n;i++){
            if(idOfSons[i].length!=0){
                G[] g = new G[idOfSons[i].length];
                for (int j=0;j<idOfSons[i].length;j++){
                    g[j]=gs[idOfSons[i][j]];
                }
                gs[i].setNext(g);
            }
        }
        /**
         * 后续的操作思路：图的遍历，同时判断是否成环调用
         */
    }

    public static void dfs(G[] gs,int index,int sum,int max){
        if(gs[index].next==null){
            max = Math.max(sum,max);
        }
        else{
            for(int i=0;i<gs[index].next.length;i++){
                sum += gs[index].getValue();
                dfs(gs,gs[index].next[i].getId(),sum,max);
                sum -= gs[index].getValue();
            }
        }
    }
}
