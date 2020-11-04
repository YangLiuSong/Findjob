import javafx.scene.transform.Scale;

import java.util.Scanner;

public class redBook {
    public static void main(String[] args) {
        red01();
    }

    public static void red01() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0;i<n;i++) {
            nums[i] = sc.nextInt();
        }
        int left = 0;
        while (left < n-1) {
            if (nums[left]>nums[left+1])
                break;
            left++;
        }
        if (left == n-1) {
            System.out.println(-1);
            System.out.println(-1);
        }
        int right = n-1;
        while (right > left+1) {
            if (nums[right] < nums[left])
                break;
            right--;
        }
        System.out.println(left);
        System.out.println(right);
    }

    public static void red02() {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        boolean[] has = new boolean[m*n];
        while (sc.hasNext()) {
            int x = sc.nextInt();
            if (x <= has.length)
                has[sc.nextInt()-1] = true;
        }
        boolean flag = false;
        for (int i = 0;i<has.length;i++){
            if (!has[i]){
                System.out.println(i+1);
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println(m*n+1);
        }
    }

    public static void red03() {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int l = sc.nextInt();
        int t = sc.nextInt();
        int n = sc.nextInt();
        boolean[] roads = new boolean[x];
        for (int i = 0;i<n;i++)
            roads[sc.nextInt()] = true;
        int[] dp = new int[x+1];
    }
}