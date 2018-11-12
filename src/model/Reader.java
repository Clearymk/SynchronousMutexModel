package model;


public class Reader extends Thread {
    private int id;
    private DataFile df;

    public Reader(int readerNum, DataFile df) {
        this.id = readerNum;
        this.df = df;
    }
    /**
     * @Author yangmingke
     * @Description run方法模拟读者进行读操作，当当前线程没有被终止时，调用DataFile中的方法进行读操作
     * @Date 10:51 2018/11/3
     * @Param []
     * @return void
     **/
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("reader " + id + " comes here");
                df.naps();
                df.startRead(id);
                df.naps();
                df.endRead(id);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
