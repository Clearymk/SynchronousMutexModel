package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utils.ModelUtil;

public class FunctionViewController {
    /**
     * @Author yangmingke
     * @Description 响应事件，当功能选择页面中具体功能被点击，调用此方法，得到该功能的名字。
     *               再通过MainApplication中的loadSelectView方法加载对应的功能界面
     * @Date 9:40 2018/11/3
     * @Param [event]
     * @return void
     **/
    @FXML
    private void selectView(Event event) {
        HBox selectHBox = (HBox) event.getSource();

        selectHBox.requestFocus();

        Label selectLabel = (Label) selectHBox.getChildren().get(0);

        ModelUtil.getApplication().loadSelectView(selectLabel.getId());
    }
}
