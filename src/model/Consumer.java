package model;

import utils.ModelUtil;

public class Consumer extends Thread{
    /**
     * @Author yangmingke
     * @Description 消费者方法，当线程没被中断时并且当产品队列不为空时，准备消费产品。
     *               获得锁之后进行消费，调用ModelUtil.subProductCount()，消费产品并更
     *               新UI，最后释放锁并唤醒其他阻塞的线程。若产品队列为空则释放锁，等
     *               待下一次调用。
     * @Date 10:22 2018/11/3
     * @Param []
     * @return void
     **/
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                synchronized (ModelUtil.LOCK) {
                    while (ModelUtil.getProductCount() == 0) {
                        ModelUtil.LOCK.wait();
                    }

                    ModelUtil.subProductCount();

                    System.out.println(Thread.currentThread().getName() + "消费者消费，产品队列目前总共有" + ModelUtil.getProductCount() + "个产品");

                    ModelUtil.LOCK.notifyAll();
                }

                Thread.sleep((long) (ModelUtil.getTime_interval() * Math.random()));
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}

