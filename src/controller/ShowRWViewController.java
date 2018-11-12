package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.AlertUtil;
import utils.ModelUtil;
import utils.ThreadUtil;

//这个类中的方法跟ShowPCModelController中的方法类似，不写注释了
public class ShowRWViewController {
    @FXML
    private HBox readerHBox;
    @FXML
    private HBox writerHBox;
    @FXML
    private Text hintText;
    @FXML
    private TextField timeIntervalTextField;

    private ThreadUtil util = ThreadUtil.getInstance();

    public void setReaderImage(int id) {
        VBox readVbox = (VBox) readerHBox.getChildren().get(id);
        ImageView imageView = (ImageView) readVbox.getChildren().get(1);

        imageView.setImage(new Image("view/image/readerArrow.png"));

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        imageView.setImage(null);
    }

    public synchronized void setWriterImage(int id) {
        VBox readVbox = (VBox) writerHBox.getChildren().get(id);
        ImageView imageView = (ImageView) readVbox.getChildren().get(0);

        imageView.setImage(new Image("view/image/writerArrow.png"));

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        imageView.setImage(null);
    }

    public void setHintText(String hintText) {
        Platform.runLater(() -> {
            this.hintText.setText(hintText);
        });
    }

    @FXML
    private void clickToStartThread() {
        util.endThread();

        try {
            ModelUtil.setTime_interval(Integer.valueOf(timeIntervalTextField.getText()));
        } catch (NumberFormatException e) {
            AlertUtil au = AlertUtil.getAlertUtil();
            au.setHanderMessage("延迟时间输入错误");
            au.setMessage("请输入正确的整数");
            au.showMessage(Alert.AlertType.ERROR);
        }

        util.initRWThread();
        util.startThread();
    }

    @FXML
    private void clickToEndThread() {
        util.endThread();
    }
}
