import java.util.*;

public class xiecheng {
    public static void main(String[] args) {
    }

    public static void xc01() {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        String str = sc.nextLine();
        String target = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        int left = 0;
        for (int i=0;i<str.length();i++){
            if (str.charAt(i)<'a' || str.charAt(i)>'z'){
                if(same(str.substring(left,i),word))
                    sb.append(target);
                else
                    sb.append(str.substring(left,i));
                sb.append(str.charAt(i));
                left = i+1;
            }
        }
        if (left < str.length()-1){
            if(same(str.substring(left),word))
                sb.append(target);
            else
                sb.append(str.substring(left));
        }
        System.out.println(sb.toString());
    }

    public static boolean same(String s,String word){
        if(s.length() != word.length())
            return false;
        int[] nums = new int[26];
        for (int i=0;i<s.length();i++)
            nums[s.charAt(i)-'a']++;
        for (int i=0;i<word.length();i++)
            nums[word.charAt(i)-'a']--;
        for (int n : nums){
            if (n!=0)
                return false;
        }
        return true;
    }

    public static void xc02() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] strs = str.split(" ");
        List<Character> list = new ArrayList<>();
        Map<String,Boolean> map = new HashMap<>();
        hui(strs,0,list,map);
        List<String> res = new ArrayList<>(map.keySet());
        res.sort(Comparator.naturalOrder());
        for (String r : res){
            System.out.print(r);
            if (map.get(r))
                System.out.print("--circular dependency");
            System.out.println();
        }
    }

    public static void hui(String[] strs,int num,List<Character> list,Map<String,Boolean> map){
        if (num == strs.length){
            StringBuilder sb = new StringBuilder();
            Set<Character> set = new HashSet<>();
            boolean b = false;
            for (char c : list){
                if (set.contains(c))
                    b = true;
                else
                    set.add(c);
                sb.append(c);
            }
            if (!map.containsKey(sb.toString()))
                map.put(sb.toString(),b);
        }
        else{
            for (int i=0;i<strs[num].length();i++){
                list.add(strs[num].charAt(i));
                hui(strs,num+1,list,map);
                list.remove(list.size()-1);
            }
        }
    }

    public static void xc03() {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int e = sc.nextInt();
        int x = sc.nextInt();
        int l = sc.nextInt();
        int[][] martix = new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                martix[i][j] = sc.nextInt();
            }
        }

    }

//    public static int bfs(int[][] martix,int m,int n,int i,int j,int e,int x,int l){
//        if(i<0||i>=m||j<0||j>=n||e<martix[i][j])
//            return 0;
//        e -= martix[i][j];
//
//    }

}
