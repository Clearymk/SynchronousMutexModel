package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LogViewController {
    @FXML
    VBox logVbox;

    /**
     * @Author yangmingke
     * @Description 用于记录控制台的输出，日志文件输出的功能中展示。传入需要记录的数据，即可将该数据展示在日志文件中
     * @Date 9:50 2018/11/3
     * @Param [data]
     * @return void
     **/
    public void setLogData(String data) {
        Platform.runLater(() -> {
            logVbox.getChildren().add(new Text(data));
        });
    }
}
