package model;


public class Writer extends Thread {
    private DataFile df;
    private int id;

    public Writer(int id, DataFile df) {
        this.df = df;
        this.id = id;

    }

    /**
     * @Author yangmingke
     * @Description run方法模拟写者进行写操作，当当前线程没有被终止时，调用DataFile中的方法进行写操作
     * @Date 10:53 2018/11/3
     * @Param []
     * @return void
     **/

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                df.naps();
                System.out.println("Writer " + id + " comes here");
                df.startWrite(id);
                df.naps();
                df.endWrite(id);
            } catch (InterruptedException e) {
                Thread.currentThread().isInterrupted();
            }
        }
    }
}
