package model;

import utils.ModelUtil;

public class Producer extends Thread{

    /**
     * @Author yangmingke
     * @Description 生产者方法，当线程没被中断时并且当产品队列不满时，准备生产产品。获
     *               得锁之后进行生产，调用ModelUtil.addProductCount()，生产产品并更新
     *               UI，最后释放锁并唤醒其他阻塞的线程。若产品队列满则释放锁，等待下一
     *               次调用。
     * @Date 10:31 2018/11/3
     * @Param []
     * @return void
     **/
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                synchronized (ModelUtil.LOCK) {
                    while (ModelUtil.getProductCount() == ModelUtil.FULL) {
                        ModelUtil.LOCK.wait();
                    }

                    ModelUtil.addProductCount();

                    System.out.println(Thread.currentThread().getName() + "生产者生产，产品队列目前总共有" + ModelUtil.getProductCount()+ "个产品");

                    ModelUtil.LOCK.notifyAll();
                }

                Thread.sleep((long) (ModelUtil.getTime_interval() * Math.random()));
            } catch (Exception e) {
                this.interrupt();
            }
        }
    }

}