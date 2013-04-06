package com.airhacks.control;

import com.airhacks.afterburner.injection.InjectionProvider;
import com.airhacks.control.presentation.airhacks.AirhacksView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author adam-bien.com
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AirhacksView appView = new AirhacksView();
        Scene scene = new Scene(appView.getView());
        stage.setTitle("AirHacks Control");
        final String uri = getClass().getResource("app.css").toExternalForm();
        scene.getStylesheets().add(uri);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        InjectionProvider.forgetAll();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
