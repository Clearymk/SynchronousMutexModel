package model;

import utils.ModelUtil;

public class DataFile {
    // doreading表示读信号量，当doreading=true时不能进行写操作。
    private boolean doreading;
    // dowriting表示写信号量，当dowriting=ture时不能进行读操作。
    private boolean dowriting;

    public DataFile() {
        doreading = false;
        dowriting = false;
    }

    /**
     * @Author yangmingke
     * @Description 线程睡眠，不消耗CPU资源
     * @Date 10:34 2018/11/3
     * @Param []
     * @return void
     **/
    public static void naps() throws InterruptedException {
            Thread.sleep((long) (ModelUtil.getTime_interval() * Math.random()));
    }

    /**
     * @Author yangmingke
     * @Description 开始读操作，当前没有写者在写的时候开始读，调用ModelUtil中的方法更新UI和读者数量。
     *                如果当前没有在写并且有读者的话就开始进行读操作
     * @Date 10:34 2018/11/3
     * @Param [id]
     * @return int
     **/
    public synchronized int startRead(int id) throws InterruptedException {

        while (dowriting == true) {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                // 等待写者发出notify
                wait();
        }
        ModelUtil.startRead(id);
        System.out.println(Thread.currentThread().getName() + " begin to read!");
        ModelUtil.addReaderCount();
        if (ModelUtil.getReadCount() == 1) {
            doreading = true;
        }
        return ModelUtil.getReadCount();
    }

    /**
     * @Author yangmingke
     * @Description 结束读操作，如果当前读者数为0的话，设置读者的信号量使得写者可以写，并唤醒所有线程
     * @Date 10:38 2018/11/3
     * @Param [id]
     * @return int
     **/
    public synchronized int endRead(int id) {
        // 结束读操作
        ModelUtil.endRead(id);
        if (ModelUtil.getReadCount() == 0) {
            doreading = false;
        }
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " reading done!");
        return ModelUtil.getReadCount();
    }

    /**
     * @Author yangmingke
     * @Description 由于模型中任意时刻只能有一个写者在进行写操作。如果当前有读者或者写者该线程都需要等待。
     *               如果可以读的话设置读者信号量，进行写操作
     * @Date 10:40 2018/11/3
     * @Param [id]
     * @return void
     **/
    public synchronized void startWrite(int id) throws InterruptedException {
        // 开始写操作

        while (doreading == true || dowriting == true) {

                System.out.println(Thread.currentThread().getName() + " is waiting");
                wait();
                // 等待写者或读者发出notify
        }
        ModelUtil.startWrite(id);
        System.out.println(Thread.currentThread().getName() + " begin to write!");
        dowriting = true;
    }

    /**
     * @Author yangmingke
     * @Description 结束写操作，将信号量设为false，唤醒其他线程
     * @Date 10:44 2018/11/3
     * @Param [id]
     * @return void
     **/
    public synchronized void endWrite(int id) {
        ModelUtil.endWrite(id);
        // 结束写操作
        dowriting = false;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " writing done!");
    }
}