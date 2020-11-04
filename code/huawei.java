import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
* 华为实现面试-手撕代码-在给定整数数组中，找出所有重复的数字
*
* */
public class huawei {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>();
        int[] nums = new int[]{1,2,3,3,4,4,5,5,78,87,89,99,99,100};
        for(int n:nums){
            // 有就返回
            if(map.containsKey(n))
                System.out.println(n);
            else
                map.put(n,0);
//            map.put(n,map.getOrDefault(n,0)+1);
        }
        // 底下的思路是全部存入map中，计数
//        for (Iterator<Integer> it = map.keySet().iterator(); it.hasNext();){
//            Integer key = it.next();
//            if(map.get(key)>1)
//                System.out.println(key);
//        }
        //
    }
}
