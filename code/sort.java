import sun.dc.path.FastPathProducer;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Queue;

public class sort {
    public static void main(String[] args) {
        int[] n = new int[]{2,1,2,5,6,1,23,571,21,54,68,12,0,54,28,64,3};
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<n.length;i++){
            list.add(n[i]);
        }
//        quick_sort(n,0, n.length-1);
//        guibing_sort(n,0,n.length-1);
//        heap_sort(n);
        for(int i=0;i<n.length;i++){
            System.out.print(n[i]+" ");
        }
        System.out.println();
//        pob_sort(list);
//        for(int i=0;i<list.size();i++){
//            System.out.print(list.get(i)+" ");
//        }
//        System.out.println();
    }

    /**
     * 选择排序，每次遍历全部数组，将最小或者最大的数移至数组的一边
     */
    public static void select_sort(int[] nums){
        // 从左开始遍历数组直至倒数第二位
        for(int i=0;i<nums.length-1;i++){
            // 记录最小元素的下标
            int min_index=i;
            // 从i+1位开始遍历后续全部元素，找的最小元素的下标
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]<=nums[min_index]){
                    min_index=j;
                }
            }
            // 交换最小下标与i位置的值
            int temp = nums[i];
            nums[i] = nums[min_index];
            nums[min_index] = temp;
        }
    }
    /**
     * 选择排序，每次遍历全部列表，将最小或者最大的数移至数组的一边
     */
    public static void select_sort(List<Integer> nums){
        for(int i=0;i<nums.size()-1;i++){
            int min_index=i;
            for(int j=i+1;j<nums.size();j++){
                if(nums.get(j)<=nums.get(min_index)){
                    min_index=j;
                }
            }
            int temp = nums.get(i);
            nums.set(i,nums.get(min_index));
            nums.set(min_index,temp);
        }
    }

    /**
     * 插入排序，从左开始遍历数组，向左侧遍历对比，如果遇到比它小的元素，就将其插入在其右侧
     */
    public static void insert_sort(int[] nums){
        for (int i=0;i<nums.length;i++){
            // 开始比较的下标
            int j = i-1;
            // 记录当前位置的值
            int temp = nums[i];
            // 找到数组中第一个比记录值小的元素下标j，插入的位置即为j+1
            while (j>=0 && nums[j]>temp){
                // 在寻找过程中，完成元素的后移操作
                nums[j+1] = nums[j];
                j--;
            }
            // 插入记录值
            nums[j+1] = temp;
        }
    }
    /**
     * 插入排序，从左开始遍历列表，向左侧遍历对比，如果遇到比它小的元素，就将其插入在其右侧
     */
    public static void insert_sort(List<Integer> nums){
        for(int i=1;i<nums.size();i++){
            // 开始比较的下标
            int k = i-1;
            // 记录当前位置的值
            int temp = nums.get(i);
            // 找到数组中第一个比记录值小的元素下标k，插入的位置即为k+1
            while (k>=0&&nums.get(k)>temp){
                k--;
            }
            // 将插入位置后的元素全部往后移一位
            for(int j=i;j>k+1;j--){
                nums.set(j,nums.get(j-1));
            }
            // 在位置k+1插入记录值
            nums.set(k+1,temp);
        }
    }

    /**
     * 冒泡排序，遍历数组，两两比较
     */
    public static void pob_sort(int[] nums){
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums.length-1-i;j++){
                if(nums[j]>nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序，遍历列表，两两比较
     */
    public static void pob_sort(List<Integer> nums){
        for(int i=0;i<nums.size();i++){
            for(int j=0;j<nums.size()-1-i;j++){
                if(nums.get(j)>nums.get(j+1)){
                    int temp = nums.get(j);
                    nums.set(j,nums.get(j+1));
                    nums.set(j+1,temp);
                }
            }
        }
    }

    /**
     * 快速排序，选定中轴元素，左右划分(数组)
     */
    public static void quick_sort(int[] nums,int left,int right){
        if(left<right){
            // 获取中轴元素的下标
            int m = parition(nums,left,right);
            // 分左右两组排序
            quick_sort(nums,left,m-1);
            quick_sort(nums,m+1,right);
        }
    }

    // 用于获取数组中位元素的下标
    public static int parition(int[] nums,int left,int right){
        // 将数组最左侧元素作为基准元素
        int l = left+1;
        int r = right;
        int m = nums[left];
        // 在范围内遍历全数组
        while(true){
            // 从左侧开始遍历，直至遇到第一个大于基准元素的为止
            while(r>=l&&nums[l]<=m){
                l++;
            }
            // 从右侧开始遍历，直至遇到第一个小于基准元素的为止
            while(r>=l&&nums[r]>=m){
                r--;
            }
            // 退出循环条件
            if(l>=r)
                break;
            // 交换前后元素的位置
            int temp = nums[r];
            nums[r] = nums[l];
            nums[l] = temp;
        }
        // 将基准元素换到最终的位置上
        nums[left]=nums[r];
        nums[r]=m;
        // 返回基准元素的下标，即中轴下标
        return r;
    }
    /**
     * 快速排序，选定中轴元素，左右划分（列表）
     */
    public static void quick_sort(List<Integer> nums,int left,int right){
        if(left<right){
            // 获取中轴元素的下标
            int m = parition(nums,left,right);
            // 分左右两组排序
            quick_sort(nums,left,m-1);
            quick_sort(nums,m+1,right);
        }
    }

    // 用于获取列表中位元素的下标
    public static int parition(List<Integer> nums,int left,int right){
        // 将数组最左侧元素作为基准元素
        int l = left+1;
        int r = right;
        int m = nums.get(left);
        // 在范围内遍历全数组
        while(true){
            // 从左侧开始遍历，直至遇到第一个大于基准元素的为止
            while(r>=l&&nums.get(l)<=m){
                l++;
            }
            // 从右侧开始遍历，直至遇到第一个小于基准元素的为止
            while(r>=l&&nums.get(r)>=m){
                r--;
            }
            // 退出循环条件
            if(l>=r)
                break;
            // 交换前后元素的位置
            int temp = nums.get(r);
            nums.set(r,nums.get(l));
            nums.set(l,temp);
        }
        // 将基准元素换到最终的位置上
        nums.set(left,nums.get(r));
        nums.set(r,m);
        // 返回基准元素的下标，即中轴下标
        return r;
    }

    /**
     * 堆排序
     */
    public static void heap_sort(int[] nums){
        // 将数组转换为最大堆
        // 因为最大堆为完全二叉树结构，因此A[n/2+1],……,A[n]是最大堆的叶子节点
        // 每个叶子节点本身就是一个最大堆，所以从A[n/2]~A[1]逐步维护这个最底层的最大堆
        for(int i=(nums.length/2)-1;i>=0;i--){
            adjustTree(nums, i, nums.length - 1);
        }
        for(int i=nums.length-1;i>0;i--){
            // 将根节点移至数组末尾
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            // 对交换后的堆进行下沉操作，重新整理为最大（最小）堆
            adjustTree(nums,0,i-1);
        }
    }

    // 最大（最小）堆的下沉操作
    // 参数：待排序的数组，起始节点下标，带判断的数组长度
    public static void adjustTree(int[] nums,int parent,int n){
        // 创建临时节点存储父节点的值
        int temp = nums[parent];
        // 父节点左子树的下标
        int child = 2*parent+1;
        // 如果存在左子树，则进入下沉判断
        while (child<=n){
            // 如存在右子树且右子树的值大于左子树，则对右子树进行后续判断
            if(child+1<=n&&nums[child+1]>nums[child])
                child++;
            // 如果父节点的值大于等于子节点的值，则退出下沉操作
            if(temp>=nums[child])
                break;
            // 进行交换
            nums[parent]=nums[child];
            // 继续向下进行下沉判断
            parent=child;
            child=parent*2+1;
        }
        // 将临时的值赋给当前的父节点（如未进行下沉交换，即父节点不变）
        nums[parent]=temp;
    }

    /**
     * 归并排序，分治的思想
     */
    public static void guibing_sort(int[] nums,int left,int right){
        if(left<right){
            int mid = (left+right)/2;
            guibing_sort(nums,left,mid);
            guibing_sort(nums,mid+1,right);
            merge(nums,left,mid,right);
        }
    }

    public static void merge(int[] nums,int left,int mid,int right){
        int[] temps = new int[right-left+1];
        int l = left;
        int r = mid+1;
        int i = 0;
        while(l<=mid&&r<=right){
            if(nums[l]<nums[r])
                temps[i++] = nums[l++];
            else
                temps[i++] = nums[r++];
        }
        while(l<=mid)
            temps[i++] = nums[l++];
        while(r<=right)
            temps[i++] = nums[r++];
        for(int j=0;j<i;)
            nums[left++] = temps[j++];
    }
}
