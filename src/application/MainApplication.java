package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.ModelUtil;

import java.io.IOException;

public class MainApplication extends Application {
    //为了防止多次载入同一资源，将各个界面抽出来成为单独的属性
    private BorderPane rootLayout;
    private AnchorPane logView;
    private AnchorPane showPCModelView;
    private AnchorPane showRWModelView;
    private AnchorPane showPCDataFlow;
    private AnchorPane showRWDataFlow;

    /**
     * @return void
     * @Author yangmingke
     * @Description 重写Application中的start方法，在JavaFx应用初始化时自动调用，在此方法中加载各类资源，用于初始化界面。
     * @Date 9:27 2018/11/3
     * @Param [primaryStage]
     **/
    @Override
    public void start(Stage primaryStage) {
        //加载主界面
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/view/RootLayout.fxml"));
        try {
            rootLayout = loader.load();
            //加载功能界面
            loader = new FXMLLoader(MainApplication.class.getResource("/view/FunctionView.fxml"));
            rootLayout.setLeft(loader.load());

            Scene scene = new Scene(rootLayout);
            //加载具体功能界面
            initView();
            rootLayout.setCenter(showPCModelView);
            //设置图标，标题等属性
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("/view/image/modelIcon.png"));
            primaryStage.setTitle("SynchronousMutexModel");
            primaryStage.show();
            //当主界面结束时，自动结束所有程序
            primaryStage.setOnCloseRequest(event -> {
                System.exit(0);
            });

            ModelUtil.setApplication(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @Author yangmingke
     * @Description 用于响应功能选择页面的点击时间，传入选择的功能名，载入相应的页面
     * @Date 9:33 2018/11/3
     * @Param [viewName]
     **/
    public void loadSelectView(String viewName) {
        switch (viewName) {
            case "LogView":
                rootLayout.setCenter(logView);
                break;
            case "ShowPCModelView":
                rootLayout.setCenter(showPCModelView);
                break;
            case "ShowRWModelView":
                rootLayout.setCenter(showRWModelView);
                break;
            case "ShowPCDataFlow":
                rootLayout.setCenter(showPCDataFlow);
                break;
            case "ShowRWDataFlow":
                rootLayout.setCenter(showRWDataFlow);
                break;
        }
    }

    /**
     * @return void
     * @Author yangmingke
     * @Description 用于初始化所有的功能界面，只调用一次，防止多重加载功能界面，造成资源浪费
     * @Date 9:35 2018/11/3
     * @Param []
     **/
    public void initView() {
        loadView("LogView");
        loadView("ShowPCModelView");
        loadView("ShowRWModelView");
        loadView("ShowPCDataFlow");
        loadView("ShowRWDataFlow");
    }

    /**
     * @return void
     * @Author yangmingke
     * @Description 传入对应的函数功能名，加载函数功能的布局文件
     * @Date 9:38 2018/11/3
     * @Param [viewName]
     **/
    public void loadView(String viewName) {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/view/" + viewName + ".fxml"));

        try {
            switch (viewName) {
                case "LogView":
                    logView = loader.load();
                    ModelUtil.setLogViewController(loader.getController());
                    break;
                case "ShowPCModelView":
                    showPCModelView = loader.load();
                    ModelUtil.setShowModelViewController(loader.getController());
                    break;
                case "ShowRWModelView":
                    showRWModelView = loader.load();
                    ModelUtil.setShowRWViewController(loader.getController());
                    break;
                case "ShowPCDataFlow":
                    showPCDataFlow = loader.load();
                    break;
                case "ShowRWDataFlow":
                    showRWDataFlow = loader.load();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @return
     * @Author yangmingke
     * @Description 启动应用
     * @Date 9:39 2018/11/3
     * @Param
     **/
    public static void main(String[] args) {
        launch(args);
    }
}
