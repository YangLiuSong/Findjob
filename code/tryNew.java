import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 手写练习
public class tryNew {
    public static void main(String[] args) {

    }

    public static Semaphore flagA = new Semaphore(1);
    public static Semaphore flagB = new Semaphore(1);
    public static Semaphore flagC = new Semaphore(1);
    public static void test01() throws Exception{
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0;i<10;i++){
                        flagA.acquire();
                        System.out.print("A");
                        flagB.release();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0;i<10;i++){
                        flagB.acquire();
                        System.out.print("B");
                        flagC.release();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0;i<10;i++){
                        flagC.acquire();
                        System.out.print("C");
                        flagA.release();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        flagB.acquire();
        flagC.acquire();
        a.start();
        b.start();
        c.start();
    }

    public static int state = 0;
    public static Lock lock = new ReentrantLock();
    public static void test02(){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;){
                    lock.lock();
                    if(state%3==0) {
                        System.out.print("A");
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
                for (int i=0;i<10;){
                    lock.lock();
                    if(state%3==1) {
                        System.out.print("B");
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
                for (int i=0;i<10;){
                    lock.lock();
                    if(state%3==2) {
                        System.out.print("C");
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

    public static Object obj = new Object();
    public static char flag = 'A';
    public static void test03(){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;){
                    synchronized (obj){
                        if (flag == 'A'){
                            System.out.print(flag);
                            flag = 'B';
                            i++;
                            obj.notifyAll();
                        }
                        else{
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
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
                for(int i=0;i<10;){
                    synchronized (obj){
                        if (flag == 'B'){
                            System.out.print(flag);
                            flag = 'C';
                            i++;
                            obj.notifyAll();
                        }
                        else{
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
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
                for(int i=0;i<10;){
                    synchronized (obj){
                        if (flag == 'C'){
                            System.out.print(flag);
                            flag = 'A';
                            i++;
                            obj.notifyAll();
                        }
                        else{
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
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

    public static Lock cLock = new ReentrantLock();
    public static Condition c1 = cLock.newCondition();
    public static Condition c2 = cLock.newCondition();
    public static Condition c3 = cLock.newCondition();
    public static char fc = 'A';
    public static void test04(){
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                cLock.lock();
                try {
                    for (int i=0;i<10;){
                        while (fc!='A')
                            c1.await();
                        System.out.print(fc);
                        fc = 'B';
                        i++;
                        c2.signal();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    cLock.unlock();
                }
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                cLock.lock();
                try {
                    for (int i=0;i<10;){
                        while (fc!='B')
                            c2.await();
                        System.out.print(fc);
                        fc = 'C';
                        i++;
                        c3.signal();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    cLock.unlock();
                }
            }
        });
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {
                cLock.lock();
                try {
                    for (int i=0;i<10;){
                        while (fc!='C')
                            c3.await();
                        System.out.print(fc);
                        fc = 'A';
                        i++;
                        c1.signal();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    cLock.unlock();
                }
            }
        });
        a.start();
        b.start();
        c.start();
    }
}
