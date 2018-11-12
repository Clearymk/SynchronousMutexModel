package utils;

import model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yangmingke
 * @Description ThreadUtil集成了模型中的多线程中的初始化，开始，销毁的方法。
 * @Date 10:58 2018/11/3
 **/
public class ThreadUtil {
    private static ThreadUtil ourInstance = new ThreadUtil();
    private List<Thread> threads = new ArrayList<>();
    private DataFile df = new DataFile();

    public static ThreadUtil getInstance() {
        return ourInstance;
    }

    private ThreadUtil() {
    }

    public void initPCThread() {
        for (int i = 0; i < ModelUtil.getcCount(); i++) {
            threads.add(new Consumer());
        }

        for (int i = 0; i < ModelUtil.getpCount(); i++) {
            threads.add(new Producer());
        }
    }

    public void startThread() {
        for (Thread t : threads) {
            t.start();
        }
    }

    public void endThread() {
        for (Thread t : threads) {
            t.interrupt();
        }

        System.out.println("---------");

        threads.clear();
    }

    public void initRWThread() {
/*        Reader.total = 0;
        Writer.total = 0;*/

        for (int i = 0; i < ModelUtil.getRwCount(); i++) {
            threads.add(new Reader(i, df));
        }

        for (int i = 0; i < ModelUtil.getRwCount(); i++) {
            threads.add(new Writer(i, df));
        }
    }
}
