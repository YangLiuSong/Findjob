import com.sun.org.apache.xml.internal.security.utils.resolver.implementations.ResolverXPointer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class train {

    public static void main(String[] args) throws Exception{
        while(true){
            new Thread().start();
        }
    }

    /**
     * 手写死锁
     */
    public static String obj1 = "obj1";
    public static String obj2 = "obj2";
    public static void deadLock(){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("Lock1 running!");
                    while (true){
                        synchronized (obj1){
                            System.out.println("Lock1 lock obj1");
                            Thread.sleep(3000);
                            synchronized (obj2){
                                System.out.println("Lock1 lock obj2");
                            }
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("Lock2 running!");
                    while (true){
                        synchronized (obj2){
                            System.out.println("Lock2 lock obj2");
                            Thread.sleep(3000);
                            synchronized (obj1){
                                System.out.println("Lock2 lock obj1");
                            }
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        a.start();
        b.start();
    }

    /**
     * 几种多线程顺序输出ABC的实现
     */
    // 使用信号量Semaphore
    public static Semaphore A = new Semaphore(1);
    public static Semaphore B = new Semaphore(1);
    public static Semaphore C = new Semaphore(1);
    public static void orderPrint() throws Exception{
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0;i<10;i++){
                        A.acquire();
                        System.out.println("A");
                        B.release();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0;i<10;i++){
                        B.acquire();
                        System.out.println("B");
                        C.release();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0;i<10;i++){
                        C.acquire();
                        System.out.println("C");
                        A.release();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        B.acquire();
        C.acquire();
        a.start();
        b.start();
        c.start();
    }

    // ReentrantLock配合全局变量，保证每次只一个线程能访问修改state，以控制顺序
    public static Lock lock = new ReentrantLock();
    public static int state = 0;
    public static void oP() throws Exception{
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;){
                    lock.lock();
                    if(state%3==0){
                        System.out.println("A");
                        state++;
                        i++;
                    }
                    lock.unlock();
                }
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;){
                    lock.lock();
                    if(state%3==1){
                        System.out.println("B");
                        state++;
                        i++;
                    }
                    lock.unlock();
                }
            }
        });
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;){
                    lock.lock();
                    if(state%3==2){
                        System.out.println("C");
                        state++;
                        i++;
                    }
                    lock.unlock();
                }
            }
        });
        a.start();
        b.start();
        c.start();
    }


    // synchronized配合Object的wait()与notify()
    public final static Object oLock = new Object();
    public static String flag = "A";
    public static void s_oP(){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    synchronized (oLock){
                        if (flag.equals("A")){
                            System.out.println("A");
                            flag="B";
                            oLock.notifyAll();
                        }
                        else{
                            try {
                                oLock.wait();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (oLock){
                        if (flag.equals("B")){
                            System.out.println("B");
                            flag="C";
                            oLock.notifyAll();
                        }
                        else{
                            try {
                                oLock.wait();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (oLock){
                        if (flag.equals("C")){
                            System.out.println("C");
                            flag="A";
                            oLock.notifyAll();
                        }
                        else{
                            try {
                                oLock.wait();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        a.start();
        b.start();
        c.start();
    }

    // ReentrantLock与Condition配合（类似于Object的wait()与notify()）
    public static Lock rLock = new ReentrantLock();
    public static Condition cA = rLock.newCondition();
    public static Condition cB = rLock.newCondition();
    public static Condition cC = rLock.newCondition();
    public static String rFlag = "A";
    public static void r_oP(){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                rLock.lock();
                try {
                    while (true){
                        while (!rFlag.equals("A"))
                            cA.await();
                        System.out.println(rFlag);
                        rFlag="B";
                        cB.signal();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    rLock.unlock();
                }
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                rLock.lock();
                try {
                    while (true){
                        while (!rFlag.equals("B"))
                            cB.await();
                        System.out.println(rFlag);
                        rFlag="C";
                        cC.signal();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    rLock.unlock();
                }
            }
        });
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                rLock.lock();
                try {
                    while (true){
                        while (!rFlag.equals("C"))
                            cC.await();
                        System.out.println(rFlag);
                        rFlag="A";
                        cA.signal();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    rLock.unlock();
                }
            }
        });
        a.start();
        b.start();
        c.start();
    }

    /**
     * 手写堆溢出
     */
    public static void heapOut(){
        List<byte[]> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(new byte[5 * 1024 * 1024]);
            System.out.println("分配次数：" + (++i));
        }
    }

    /**
     * 手写栈溢出：StackOverflowError
     */
    public static void stackOut(){
        stackOut();
    }

    /**
     * 手写栈溢出：OutOfMemberError
     */
    public static void stackOut01(){
        train test = null;
        try {
            test = new train();
            test.sofMethod();
        } finally {
            System.out.println("递归次数："+test.depth);
        }
    }
    int depth = 0;

    public void sofMethod(){
        depth ++ ;
        sofMethod();
    }

    // 手写排序练习
//    // 冒泡
//    public static void maopao(int[] n){
//        for(int i=0;i<n.length;i++){
//            for(int j=0;j<n.length-i-1;j++){
//                if(n[j]>n[j+1]){
//                    int temp = n[j];
//                    n[j] = n[j+1];
//                    n[j+1] = temp;
//                }
//            }
//        }
//    }
//
//    // 选择
//    public static void xuanze(int[] n){
//        for(int i=0;i<n.length-1;i++){
//            int index = i;
//            for(int j=i+1;j<n.length;j++){
//                if(n[j]<n[index])
//                    index = j;
//            }
//            int temp = n[index];
//            n[index] = n[i];
//            n[i] = temp;
//        }
//    }
//
//    // 插入
//    public static void charu(int[] n){
//        for(int i=0;i<n.length-1;i++){
//            int k=i+1;
//            int temp = n[k];
//            while (k>0&&temp<n[k-1]){
//                k--;
//            }
//            for(int j=i+1;j>k;j--)
//                n[j]=n[j-1];
//            n[k] = temp;
//        }
//    }
//
//    // 快速
//    public static void kuaisu(int[] n,int left,int right){
//        if(left<right){
//            int m = p(n,left,right);
//            kuaisu(n,left,m-1);
//            kuaisu(n,m+1,right);
//        }
//    }
//
//    public static int p(int[] n,int left,int right){
//        int temp = n[left];
//        int l = left+1;
//        int r = right;
//        while (true){
//            while(l<=r&&n[l]<=temp){
//                l++;
//            }
//            while(l<=r&&n[r]>=temp){
//                r--;
//            }
//            if(l>=r)
//                break;
//            int t = n[l];
//            n[l]=n[r];
//            n[r]=t;
//        }
//        n[left]=n[r];
//        n[r]=temp;
//        return r;
//    }
//
//    // 堆
//    public static void dui(int[] n){
//        for(int i=n.length/2+1;i>=0;i--)
//            dodui(n,i,n.length-1);
//        for(int i=n.length-1;i>0;i--){
//            int temp = n[i];
//            n[i] = n[0];
//            n[0] = temp;
//            dodui(n,0,i-1);
//        }
//    }
//
//    public static void dodui(int[] n,int parent,int length){
//        int temp = n[parent];
//        int child = 2*parent+1;
//        while (child<=length){
//            if(child+1<=length&&n[child+1]>n[child])
//                child++;
//            if(temp>=n[child])
//                break;
//            n[parent]=n[child];
//            parent = child;
//            child = 2*parent+1;
//        }
//        n[parent]=temp;
//    }
}
