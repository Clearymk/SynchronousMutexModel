package controller;

import utils.AlertUtil;
import utils.ModelUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import utils.ThreadUtil;


public class ShowPCModelViewController {
    @FXML
    private GridPane showGridPane;
    @FXML
    private Text consumerCountText;
    @FXML
    private Text producerCountText;
    @FXML
    private TextField timeIntervalTextField;
    @FXML
    private ImageView inImageView;
    @FXML
    private ImageView outImageView;
    @FXML
    private Text hintText;

    private ThreadUtil util = ThreadUtil.getInstance();

    /**
     * @Author yangmingke
     * @Description 当点击开始按钮时，调用此方法，设置线程初始化参数，启动生产者消费者线程
     * @Date 9:56 2018/11/3
     * @Param []
     * @return void
     **/
    @FXML
    private void clickToStartThread() {
        //先结束之前的线程，再
        util.endThread();
        //得到用户选择的参数
        ModelUtil.setcCount(Integer.valueOf(consumerCountText.getText()));
        ModelUtil.setpCount(Integer.valueOf(producerCountText.getText()));

        try {
            ModelUtil.setTime_interval(Integer.valueOf(timeIntervalTextField.getText()));
        } catch (NumberFormatException e) {
            //对于错误的输入报错
            AlertUtil au = AlertUtil.getAlertUtil();
            au.setHanderMessage("延迟时间输入错误");
            au.setMessage("请输入正确的整数");
            au.showMessage(Alert.AlertType.ERROR);
        }
        //初始化并开始线程
        util.initPCThread();
        util.startThread();
    }
    //之后的方法都是根据用户设置的参数来更改程序默认参数
    @FXML
    private void clickToAddConsumerCount() {
        int cCount = Integer.valueOf(consumerCountText.getText());

        if (cCount < 10) {
            cCount++;
            ModelUtil.setcCount(cCount);
        }

        consumerCountText.setText(String.valueOf(cCount));
    }

    @FXML
    private void clickToSubConsumerCount() {
        int cCount = Integer.valueOf(consumerCountText.getText());
        if (cCount > 1) {
            cCount--;
            ModelUtil.setpCount(cCount);
        }
        consumerCountText.setText(String.valueOf(cCount));
    }

    @FXML
    private void clickToAddProducerCount() {
        int pCount = Integer.valueOf(producerCountText.getText());

        if (pCount < 10) {
            pCount++;
            ModelUtil.setpCount(pCount);
        }

        producerCountText.setText(String.valueOf(pCount));
    }

    @FXML
    private void clickToSubProducerCount() {
        int pCount = Integer.valueOf(producerCountText.getText());

        if (pCount > 1) {
            pCount--;
            ModelUtil.setpCount(pCount);
        }
        producerCountText.setText(String.valueOf(pCount));
    }

    /**
     * @Author yangmingke
     * @Description 结束线程，并将功能页面初始化
     * @Date 10:00 2018/11/3
     * @Param []
     * @return void
     **/
    @FXML
    private void clickToEndThread() {
        util.endThread();
        ModelUtil.clearProductCount();
        updateLabel();
        setHintText("当前队列为空");
    }
    /**
     * @Author yangmingke
     * @Description 当产品队列更新时，调用此方法，展示当前的产品队列的情况
     * @Date 10:00 2018/11/3
     * @Param []
     * @return void
     **/
    public void updateLabel() {
        //再非JavaFx线程中更新UI需要使用Platform.runLater方法
        Platform.runLater(() -> {
            //清除当前队列
            showGridPane.getChildren().clear();
            //得到现在队列的总数，更新UI
            for (int i = 1; i <= ModelUtil.getProductCount(); i++) {
                Label l = new Label();

                l.setPrefWidth(30);
                l.setPrefHeight(30);
                l.setStyle("-fx-background-color: Gray;");

                showGridPane.getChildren().add(l);

                GridPane.setConstraints(l, i, 0);
            }
        });
        //防止数据更新过快，线程睡眠
        try {
            Thread.sleep(ModelUtil.getTime_interval());
        } catch (InterruptedException e) {
            Platform.runLater(() ->
                showGridPane.getChildren().clear()
            );
            Thread.currentThread().interrupt();
        }
    }
    /**
     * @Author yangmingke
     * @Description 设置产品加入队列的图片
     * @Date 10:06 2018/11/3
     * @Param []
     * @return void
     **/
    public void setInImageToImageView() {
        inImageView.setImage(new Image("view/image/inArrow.png"));
        //图片持续0.2s
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        inImageView.setImage(null);
    }

    public void setOutImageView() {
        outImageView.setImage(new Image("view/image/outArrow.png"));

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        outImageView.setImage(null);
    }

    public void setHintText(String hintText) {
        this.hintText.setText(hintText);
    }
}
