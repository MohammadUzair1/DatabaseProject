
package start;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class startApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("/fxml/EmpFXML.fxml"));
      Stage stage = new Stage();
      Scene scene = new Scene(root);
      stage.setTitle("EMPLOYEE MANAGEMENT SYSTEM");
      Image image = new Image("/start/empicon.png");
      stage.getIcons().add(image);
      stage.setScene(scene);
      
      
      stage.show();
      
    }
    public static void main(String []args)
    {
        launch(args);
    }

        
   
    
}
