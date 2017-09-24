package endomondo;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Endomondo");

        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setPadding(new Insets(10,10,10,10));
        grid.getColumnConstraints().add(new ColumnConstraints(230));
        grid.getColumnConstraints().add(new ColumnConstraints(230));

        grid.getRowConstraints().add(new RowConstraints(60));
        grid.getRowConstraints().add(new RowConstraints(60));
        grid.getRowConstraints().add(new RowConstraints(60));
        grid.getRowConstraints().add(new RowConstraints(60));


        Pane bg_r2 = new Pane();
        bg_r2.setStyle("-fx-background-color: #F3F4EF;");
        grid.add(bg_r2,0,1);
        Pane bg_r3 = new Pane();
        bg_r3.setStyle("-fx-background-color: #F3F4EF;");
        grid.add(bg_r3,1,1);

        Pane bg_r4 = new Pane();
        bg_r4.setStyle("-fx-background-color: #F3F4EF;");
        grid.add(bg_r4,0,2);
        Pane bg_r5 = new Pane();
        bg_r5.setStyle("-fx-background-color: #F3F4EF;");
        grid.add(bg_r5,1,2);

        Pane bg_r6 = new Pane();
        bg_r6.setStyle("-fx-background-color: #F3F4EF;");
        grid.add(bg_r6,0,3);
        Pane bg_r7 = new Pane();
        bg_r7.setStyle("-fx-background-color: #F3F4EF;");
        grid.add(bg_r7,1,3);





        //COLUMN = 0, ROW = 0
        Image img = new Image("endomondo/running_man.png");
        ImageView iv2 = new ImageView();
        iv2.setImage(img);
        iv2.setFitWidth(50);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);
        grid.add(iv2,0,0);

        //COLUMN = 1, ROW = 0
        Text run_title = new Text("RUNNING");
        run_title.setFont(Font.font("Helvetica", FontWeight.NORMAL, 28));
        GridPane.setHalignment(run_title, HPos.RIGHT);
        GridPane.setValignment(run_title, VPos.TOP);
        grid.add(run_title,1,0);

        //COLUMN = 0, ROW = 1
        Text dur_title = new Text("DURATION");
        dur_title.setFont(Font.font("Helvetica", FontWeight.NORMAL, 20));
        GridPane.setHalignment(dur_title, HPos.LEFT);
        GridPane.setValignment(dur_title, VPos.TOP);

        grid.add(dur_title, 0,1);

        //COLUMN = 1, ROW = 1
        Text dur_time = new Text("0:57:24");
        dur_time.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
        GridPane.setHalignment(dur_time, HPos.RIGHT);
        grid.add(dur_time,1,1);

        //COLUMN = 0, ROW = 2
        GridPane dis_subGrid = new GridPane();
        Text dis_title = new Text("DISTANCE");
        dis_title.setFont(Font.font("Helvetica", FontWeight.NORMAL, 20));
        dis_subGrid.add(dis_title,0,0);
        Text dis_measure = new Text("km");
        dis_measure.setFont(Font.font("Helvetica", FontWeight.NORMAL, 20));
        dis_subGrid.add(dis_measure, 0,1);
        grid.add(dis_subGrid,0,2);

        //COLUMN = 1, ROW = 2
        Text dis_time = new Text("12.30");
        dis_time.setFont(Font.font("Helvetica", FontWeight.BOLD, 50));
        GridPane.setHalignment(dis_time, HPos.RIGHT);

        grid.add(dis_time, 1,2);

        GridPane hr_subGrid = new GridPane();
        GridPane hr_bmp_subGRID = new GridPane();
        hr_subGrid.setPadding(new Insets(0,5,0,0));

        //COLUMN = 0, ROW = 3
        Text hr_title = new Text("HEART RATE");
        hr_title.setFont(Font.font("Helvetica", FontWeight.NORMAL, 20));
        hr_bmp_subGRID.add(hr_title,0,0);

        Text hr_measure = new Text("bpm");
        hr_measure.setFont(Font.font("Helvetica", FontWeight.NORMAL, 20));
        hr_bmp_subGRID.add(hr_measure, 0,1);

        hr_subGrid.add(hr_bmp_subGRID,0,0);

        Text hr_value = new Text("163");
        hr_value.setFont(Font.font("Helvetica", FontWeight.BOLD, 40));

        hr_subGrid.add(hr_value,1,0);

        grid.add(hr_subGrid,0,3);

        //COLUMN = 1, ROW = 3
        GridPane speed_subGrid = new GridPane();
        GridPane speed_km_subGrid = new GridPane();

        speed_subGrid.setPadding(new Insets(0,0,0,5));
        Text speed_title = new Text("AVG. SPEED");
        speed_title.setFont(Font.font("Helvetica", FontWeight.NORMAL, 20));
        speed_km_subGrid.add(speed_title,0,0);

        Text speed_measure = new Text("km/h");
        speed_measure.setFont(Font.font("Helvetica", FontWeight.NORMAL, 20));
        speed_km_subGrid.add(speed_measure, 0,1);

        Text speed_value = new Text("4:40");
        speed_value.setFont(Font.font("Helvetica", FontWeight.BOLD, 40));
        speed_subGrid.add(speed_value,1,0);
        speed_subGrid.add(speed_km_subGrid,0,0);
        grid.add(speed_subGrid,1,3);


        //grid.setGridLinesVisible(true);
        //hr_subGrid.setGridLinesVisible(true);
        //hr_bmp_subGRID.setGridLinesVisible(true);
        Scene scene = new Scene(grid, 480, 290);
        primaryStage.setScene(scene);
        primaryStage.show();


    }



    public static void main(String[] args) {
        launch(args);
    }
}
