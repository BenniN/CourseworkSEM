package com.napier.sem.ui;

import com.napier.sem.ApplicationInitializer;
import com.napier.sem.reports.SimplePopulationReport;
import com.napier.sem.service.AppServices;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Starting class for our JavaFxApplication.
 */
public class JavaFxApplication extends Application {

    private static JavaFxApplication application;

    private AppServices appServices;
    private EventHandler eventHandler;

    public JavaFxApplication() {
        this.eventHandler = new EventHandler();
        application = this;
    }

    /**
     * Use this method to get an instance of this class.
     * This method might be called from other screen controller classes for example.
     * @return the newest created instance of this class
     */
    public static JavaFxApplication getInstance() {
        return application;
    }

    @Override
    public void start(Stage stage) {
        this.appServices = ApplicationInitializer.getInstance().getAppServices();

        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");


        // call example service
        Button button = new Button("Click me");
        button.setOnAction((event) -> execute((appServices1 -> {
            SimplePopulationReport report = appServices1.getThePopulationOfACity("Edinburgh");
            runOnUI(() -> l.setText("Name: " + report.getName() + ", Population: " + report.getPopulation()));
        })));

        Scene scene = new Scene(new VBox(l, button), 640, 480);
        stage.setScene(scene);
        stage.show();

        // add close event handler
        stage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    /**
     * When the user wants to close the main window, we want to properly close our event handler thread first.
     * @param event the event to be processed
     */
    private void closeWindowEvent(WindowEvent event) {
        if (event.getEventType().equals(WindowEvent.WINDOW_CLOSE_REQUEST)) {
            this.eventHandler.active = false;
            Platform.exit();
        }
    }

    /**
     * This event handler class makes it easy to set up
     * service calls on a separate thread to not freeze the
     * UI on demanding computing operations or database calls.
     */
    private class EventHandler extends Thread {
        private boolean active = true;
        private Queue<ServiceCall> serviceCalls = new ConcurrentLinkedQueue<>();

        private EventHandler() {
            setName("EventHandler-Thread");
            setDaemon(true);
            start();
        }

        private synchronized void addServiceCall(ServiceCall serviceCall) {
            this.serviceCalls.add(serviceCall);
        }

        @Override
        public void run() {
            while (active) {
                if (!serviceCalls.isEmpty()) {
                    ServiceCall serviceCall = serviceCalls.poll();
                    try {
                        serviceCall.perform(appServices);
                    } catch (RuntimeException e) {
                        showExceptionDialog(e);
                    }
                }
            }
        }
    }

    /**
     * Adds the specified service call in a queue
     * so that it will be executed on a separate thread.
     * @param serviceCall the call the be added
     */
    public void execute(ServiceCall serviceCall) {
        this.eventHandler.addServiceCall(serviceCall);
    }

    /**
     * Will execute the specified runnable object
     * on the UI thread.
     * @param runnable the code to be executed on the ui thread
     */
    public void runOnUI(Runnable runnable) {
        Platform.runLater(runnable);
    }

    private void showExceptionDialog(RuntimeException e) {
        Platform.runLater(() -> {
            // show general message to user
            // code from: https://code.makery.ch/blog/javafx-dialogs-official/
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("An error occurred in the application. Please contact the support team.");
            alert.setContentText(e.getMessage());

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);
            alert.showAndWait();
        });
    }
}
