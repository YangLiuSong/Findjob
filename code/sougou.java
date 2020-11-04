import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sougou {
    public static void main(String[] args) {
//        trim(10,14,new Interval[]{[[0,1],[0,2],[0,3],[1,4],[3,4],[2,6],[4,5],[5,6],[6,7],[7,8],[6,9],[9,10],[8,-1],[10,-1]]})
    }

    public static class Interval {
        int start;
        int end;
    }

    public static Interval solve (int n, int k, String str1, String str2) {
        // write code here
        res.start = Integer.MAX_VALUE;
        res.end = Integer.MIN_VALUE;
        boolean[] ans = new boolean[n];
        huisu(n,k,0,str1,str2,ans);
        return res;
    }

    public static Interval res = new Interval();
    public static void huisu(int n,int k,int index,String str1,String str2,boolean[] ans){
        if (k>n-index)
            return;
        if (index == n){
            if(k!=0)
                return;
            int min=0,max=0;
            for(int i=0;i<n;i++){
                if(ans[i]){
                    if(str1.charAt(i)==str2.charAt(i)){
                        max++;
                        min++;
                    }
                }
                else{
                    if(str1.charAt(i)!=str2.charAt(i)) {
                        max++;
                    }
                }
            }
            res.start = Math.min(res.start,min);
            res.end = Math.max(res.end,max);
        }
        else {
            for(int i=index;i<n;i++){
                if(k>0){
                    k--;
                    ans[i] = true;
                    huisu(n,k,i+1,str1,str2,ans);
                    ans[i] = false;
                    k++;
                }
//                huisu(n,k,i+1,str1,str2,ans);
            }
        }
    }

    public static String rotatePassword (String[] s1, String[] s2) {
        int n = s1.length;
        List<String> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(s1[i].charAt(j)=='0'){
                    list.add(i+","+j);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int c=0;c<4;c++){
            List<String> tempList = new ArrayList<>();
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(list.contains(i+","+j)){
                        sb.append(s2[i].charAt(j));
                        tempList.add(change(i,j,n));
                    }
                }
            }
            list = new ArrayList<>(tempList);
        }
        return sb.toString();
    }

    public static String change(int i,int j,int n){
        int temp = n-i-1;
        return j+","+temp;
    }

    public static Interval trim (int N, int M, Interval[] conn) {
        // write code here
        int[] inArray = new int[N+2];
        boolean[] visit = new boolean[N];
        Map<Integer,StringBuilder> road = new HashMap<>();
        for(int i=0;i<M;i++){
            if(!road.containsKey(conn[i].start)){
                road.put(conn[i].start,new StringBuilder(conn[i].end));
            }
            else{
                road.put(conn[i].start,road.get(conn[i].start).append(",").append(conn[i].end));
            }
            if ((conn[i].end==-1))
                inArray[N+1]++;
            else
                inArray[conn[i].end]++;
        }

        return new Interval();
    }
}
