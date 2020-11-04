public class singleton {
    /**
     * 单例模式：饿汉式
     */
//    private static singleton instance = new singleton(); // 类初始化的时候，立即加载对象
//    private singleton(){
//
//    }
//
//    public static singleton getInstance(){
//        return instance;
//    }
    /**
     * 单例模式：懒汉式
     */
//    private static singleton instance = null;
//    private singleton(){
//
//    }
//
//    public static synchronized singleton getInstance(){
//        if (instance==null)
//            instance = new singleton();
//        return instance;
//    }
    /**
     * 单例模式：双重检测锁
     */
//    private static singleton instance = null;
//    private singleton(){}
//    public static singleton getInstance(){
//        if (instance==null){
//            singleton s;
//            synchronized (singleton.class){
//                s = instance;
//                if(s==null){
//                    synchronized (singleton.class){
//                        if(s==null){
//                            s = new singleton();
//                        }
//                    }
//                    instance = s;
//                }
//            }
//        }
//        return instance;
//    }



//    private volatile static singleton instance;
//    private singleton() {
//    }
//    public static singleton getInstance() {
//        if (instance == null) {
//            //类对象加锁
//            synchronized (singleton.class) {
//                if (instance == null) {
//                    instance = new singleton();
//                }
//            }
//        }
//        return instance;
//    }
    /**
     * 单例模式：静态内部类(懒加载方式)
     */
//    private static class SingletonClassInstance{
//        private static final singleton instance = new singleton();
//    }
//    public static singleton getInstance(){
//        return SingletonClassInstance.instance;
//    }
//    private singleton(){}
    /**
     * 单例模式：枚举
     */
//    public enum SingletonEnum{
//        // 这个枚举元素本身就是单例的
//        INSTANCE;
//    }
}
