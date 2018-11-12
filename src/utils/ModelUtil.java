package utils;

import application.MainApplication;
import controller.LogViewController;
import controller.ShowPCModelViewController;
import controller.ShowRWViewController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author yangmingke
 * @Description ModelUtil用于模型之间的交互，是模型与视图之间的桥梁。集成的方法共模型和视图调用，保证统一性
 * @Date 10:55 2018/11/3
 **/
public class ModelUtil {
    private static int productCount = 0;
    private static int time_interval = 1000;
    private static int cCount = 5;
    private static int pCount = 5;
    private static int rwCount = 5;
    public static final int FULL = 10;
    public static final String LOCK = "lock";
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    private static int readCount = 0;

    private static ShowPCModelViewController showModelViewController;
    private static LogViewController logViewController;
    private static ShowRWViewController showRWViewController;
    private static MainApplication application;

    public static Integer getProductCount() {
        return productCount;
    }

    public static void addProductCount() {
        productCount++;
        showModelViewController.setHintText("生产者生产，产品队列目前总共有" + ModelUtil.getProductCount() + "个产品");
        logViewController.setLogData(format.format(new Date()) + " " +
                Thread.currentThread().getName() + "生产者生产，目前总共有" + ModelUtil.getProductCount() + "个产品");
        showModelViewController.setInImageToImageView();
        showModelViewController.updateLabel();
    }

    public static void subProductCount() {
        productCount--;
        showModelViewController.setHintText("消费者消费，产品队列目前总共有" + ModelUtil.getProductCount() + "个产品");
        logViewController.setLogData(format.format(new Date()) + " " +
                Thread.currentThread().getName() + "消费者消费，目前总共有" + ModelUtil.getProductCount() + "个产品");
        showModelViewController.setOutImageView();
        showModelViewController.updateLabel();
    }


    public static void startRead(int id) {
        showRWViewController.setHintText(id + " 号读者准备阅读：当前还有 " + ModelUtil.getReadCount() + " 位读者在读");
        logViewController.setLogData(format.format(new Date()) + " "
                + Thread.currentThread().getName() + " " + id + " 号读者准备阅读：当前还有 " + readCount + " 位读者在读");
    }

    public static void endRead(int id) {
        ModelUtil.readCount--;
        showRWViewController.setReaderImage(id);
        showRWViewController.setHintText(id + " 号读者开始阅读：当前还有 " + ModelUtil.getReadCount() + " 位读者在读");
        logViewController.setLogData(format.format(new Date()) + " "
                + Thread.currentThread().getName() + " " + id + " 号读者开始阅读：当前还有 " + readCount + " 位读者在读");
    }

    public static void addReaderCount() {
        ModelUtil.readCount++;
    }

    public static void startWrite(int id) {
        showRWViewController.setHintText(id + " 号写者准备写");
        logViewController.setLogData(format.format(new Date()) + " "
                + Thread.currentThread().getName() + " " + id + " 号写者准备写");
    }

    public static void endWrite(int id) {
        showRWViewController.setWriterImage(id);
        showRWViewController.setHintText(id + " 号写者开始写");
        logViewController.setLogData(format.format(new Date()) + " "
                + Thread.currentThread().getName() + " " + id + " 号写者开始写");
    }

    public static void clearProductCount() {
        productCount = 0;
    }


    public static ShowPCModelViewController getShowModelViewController() {
        return showModelViewController;
    }

    public static void setShowModelViewController(ShowPCModelViewController showModelViewController) {
        ModelUtil.showModelViewController = showModelViewController;
    }

    public static LogViewController getLogViewController() {
        return logViewController;
    }

    public static void setLogViewController(LogViewController logViewController) {
        ModelUtil.logViewController = logViewController;
    }

    public static void setProductCount(int productCount) {
        ModelUtil.productCount = productCount;
    }

    public static int getTime_interval() {
        return time_interval;
    }

    public static void setTime_interval(int time_interval) {
        ModelUtil.time_interval = time_interval;
    }

    public static int getcCount() {
        return cCount;
    }

    public static void setcCount(int cCount) {
        ModelUtil.cCount = cCount;
    }

    public static int getpCount() {
        return pCount;
    }

    public static void setpCount(int pCount) {
        ModelUtil.pCount = pCount;
    }

    public static SimpleDateFormat getFormat() {
        return format;
    }

    public static ShowRWViewController getShowRWViewController() {
        return showRWViewController;
    }

    public static void setShowRWViewController(ShowRWViewController showRWViewController) {
        ModelUtil.showRWViewController = showRWViewController;
    }

    public static MainApplication getApplication() {
        return application;
    }

    public static void setApplication(MainApplication application) {
        ModelUtil.application = application;
    }

    public static int getReadCount() {
        return readCount;
    }

    public static void setReadCount(int readCount) {
        ModelUtil.readCount = readCount;
    }


    public static int getRwCount() {
        return rwCount;
    }

    public static void setRwCount(int rwCount) {
        ModelUtil.rwCount = rwCount;
    }
}
