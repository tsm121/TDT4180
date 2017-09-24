package appointment;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class Controller implements Initializable {

    @FXML //  fx:id="myButton"
    private Button save_appointment; // Value injected by FXMLLoader

    @FXML
    private TextField purpose;

    @FXML
    private ComboBox<String> building;

    @FXML
    private ComboBox<Integer> room;

    @FXML
    private DatePicker pick_date;

    @FXML
    private ComboBox<String> from_h;

    @FXML
    private ComboBox<String> from_m;

    @FXML
    private ComboBox<String> to_h;

    @FXML
    private ComboBox<String> to_m;

    @FXML
    private DatePicker rep_date;

    @FXML
    private TextField rep_days;

    @FXML
    private CheckBox rep;

    @FXML
    private Button cancel;

    @FXML
    private Label wrong_time;

    @FXML
    private Label rep_tdays;

    @FXML
    private Label rep_tdate;

    @FXML
    private Label wrong_purpose;

    @FXML
    private Label wrong_date;

    @FXML
    private Label wrong_rep_date;

    @FXML
    private Label wrong_purpose_num;

    private Label from = new Label("");
    private Label to = new Label("");

    //Check if everything is filled in correctly
    private boolean checkInputs(){
        try{

            if (rep.isSelected()) {

                if (wrong_purpose.isVisible() || wrong_time.isVisible() || wrong_rep_date.isVisible() || wrong_date.isVisible()
                        || building.getSelectionModel().getSelectedItem() == null || room.getValue() == null || pick_date.getValue() == null
                        || rep_date.getValue() == null || rep_days.getLength() <= 0 || wrong_purpose_num.isVisible()) {

                    return false;
                }

            }

            else if (!rep.isSelected()){
                if (wrong_purpose.isVisible() || wrong_time.isVisible() || wrong_date.isVisible()
                        || building.getValue().length() <= 0 || room.getValue() == null || pick_date.getValue() == null || wrong_purpose_num.isVisible()) {
                    return false;
                }
            }
        }catch (Exception e){return false;}

        return true;
    }


    //Get int value of Label
    private int getLabelValue(Label x){
        if (x.getText().equals("")) return 0;
        else return Integer.valueOf(x.getText());
    }

    //Check if end time is after start time
    private void checkValidTime(){
        if(getLabelValue(to) <= getLabelValue(from)){
            wrong_time.setVisible(true);
        }
        else{
            wrong_time.setVisible(false);
        }
    }

    //Get time as int and in minutes
    private int getValueTime(ComboBox<String> hour, ComboBox<String> min){

        if (hour.getSelectionModel().getSelectedItem() == null || min.getSelectionModel().getSelectedItem() == null){
            return 0;
        }
        return Integer.valueOf(hour.getSelectionModel().getSelectedItem()) * 60 + Integer.valueOf(min.getSelectionModel().getSelectedItem());
    }

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert save_appointment != null : "fx:id=\"save_appointment\" was not injected: check your FXML file 'simple.fxml'.";
        assert purpose != null : "fx:id=\"purpose\" was not injected: check your FXML file 'appointment.fxml'.";
        assert building != null : "fx:id=\"building\" was not injected: check your FXML file 'appointment.fxml'.";
        assert room != null : "fx:id=\"room\" was not injected: check your FXML file 'appointment.fxml'.";
        assert pick_date != null : "fx:id=\"pick_date\" was not injected: check your FXML file 'appointment.fxml'.";
        assert from_h != null : "fx:id=\"from_h\" was not injected: check your FXML file 'appointment.fxml'.";
        assert to_h != null : "fx:id=\"to_h\" was not injected: check your FXML file 'appointment.fxml'.";
        assert from_m != null : "fx:id=\"from_m\" was not injected: check your FXML file 'appointment.fxml'.";
        assert to_m != null : "fx:id=\"to_m\" was not injected: check your FXML file 'appointment.fxml'.";
        assert rep_date != null : "fx:id=\"rep_date\" was not injected: check your FXML file 'appointment.fxml'.";
        assert rep_days != null : "fx:id=\"rep_days\" was not injected: check your FXML file 'appointment.fxml'.";
        assert rep != null : "fx:id=\"rep\" was not injected: check your FXML file 'appointment.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'appointment.fxml'.";
        assert wrong_time != null : "fx:id=\"wrong_time\" was not injected: check your FXML file 'appointment.fxml'.";
        assert rep_tdate != null : "fx:id=\"rep_tdate\" was not injected: check your FXML file 'appointment.fxml'.";
        assert rep_tdays != null : "fx:id=\"rep_tdays\" was not injected: check your FXML file 'appointment.fxml'.";
        assert wrong_purpose != null : "fx:id=\"wrong_purpose\" was not injected: check your FXML file 'appointment.fxml'.";
        assert wrong_date != null : "fx:id=\"wrong_date\" was not injected: check your FXML file 'appointment.fxml'.";
        assert wrong_rep_date != null : "fx:id=\"wrong_rep_date\" was not injected: check your FXML file 'appointment.fxml'.";
        assert wrong_purpose_num != null : "fx:id=\"wrong_purpose_num\" was not injected: check your FXML file 'appointment.fxml'.";


        //Check if input has numbers
        purpose.focusedProperty().addListener(observable -> {

            if(!purpose.isFocused()){
                String text = purpose.getText();
                if(text.matches(".*\\d+.*")){
                    wrong_purpose_num.setVisible(true);
                }
                else {
                    wrong_purpose_num.setVisible(false);
                }
            }

        });


        //Add choices for "minute" and "hour"
        from_h.getItems().addAll("08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        to_h.getItems().addAll("08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        from_m.getItems().addAll("00","15","30","45");
        to_m.getItems().addAll("00","15","30","45");

        //From hour - Check if end and start time is valid when pressed
        from_h.valueProperty().addListener(observable -> {
            from.setText(Integer.toString(getValueTime(from_h,from_m)));
            checkValidTime();
        });

        //From minute - Check if end and start time is valid when pressed
        from_m.valueProperty().addListener(observable -> {
            from.setText(Integer.toString(getValueTime(from_h,from_m)));
            checkValidTime();
        });

        //To hour - Check if end and start time is valid when pressed
        to_h.valueProperty().addListener(observable -> {
            to.setText(Integer.toString(getValueTime(to_h,to_m)));
            checkValidTime();
        });

        //To minute - Check if end and start time is valid when pressed
        to_m.valueProperty().addListener(observable -> {
            to.setText(Integer.toString(getValueTime(to_h,to_m)));
            checkValidTime();
        });

        //Disable and enable choices for repetition
        rep.selectedProperty().addListener(observable -> {

            if(rep.isSelected()){
                rep_date.setDisable(false);
                rep_days.setDisable(false);
                rep_tdate.setDisable(false);
                rep_tdays.setDisable(false);
            }

            else {
                rep_date.setDisable(true);
                rep_days.setDisable(true);
                rep_tdate.setDisable(true);
                rep_tdays.setDisable(true);
            }

        });

        //Check "days" input
        rep_days.textProperty().addListener(observable -> {

            if (rep_days.getLength() > 0){

                try {
                    if (Integer.valueOf(rep_days.getText()) < 0){
                        rep_days.setText("0");
                    }
                }
                catch (Exception e){
                    rep_days.setText("");
                }
            }
        });

        //Checks if start date is after today
        pick_date.valueProperty().addListener(observable -> {
            if(LocalDate.now().isAfter(pick_date.getValue())){
                wrong_date.setVisible(true);
            }
            else wrong_date.setVisible(false);
        });

        //Checks if end date on rep is after start date
        rep_date.valueProperty().addListener(observable -> {
            if(pick_date.getValue().isAfter(rep_date.getValue())){
                wrong_rep_date.setVisible(true);
            }
            else wrong_rep_date.setVisible(false);
        });

        //Add items to "building"
        building.getItems().addAll("Hovedbygget","IT-Bygget", "P15", "Realfagsbygget");

        //Add items to "room" depending on "building"
        building.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selected_item = building.getSelectionModel().getSelectedItem();
                if (selected_item.equals("Hovedbygget")) {
                    room.getItems().removeAll(room.getItems());
                    room.getItems().addAll(100,101,102);
                }

                else if (selected_item.equals("IT-Bygget")){
                    room.getItems().removeAll(room.getItems());
                    room.getItems().addAll(200,201,202);
                }

                else if (selected_item.equals("P15")){
                    room.getItems().removeAll(room.getItems());
                    room.getItems().addAll(300,301,302);
                }

                else if (selected_item.equals("Realfagsbygget")){
                    room.getItems().removeAll(room.getItems());
                    room.getItems().addAll(400,401,402);
                }
            }
        });

        //Save form
        save_appointment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(purpose.getLength() <= 0){
                    wrong_purpose.setVisible(true);
                }
                else wrong_purpose.setVisible(false);

                if(!checkInputs()){

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Noob");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid fields");
                    alert.showAndWait();

                }

                else {

                    System.out.println("Title: " + purpose.getText() + "\n");
                    System.out.println("Building: " + building.getSelectionModel().getSelectedItem());
                    System.out.println("Room: " + room.getSelectionModel().getSelectedItem());
                    System.out.println("Date: " +  pick_date.getValue());
                    System.out.println("From: " + from_h.getSelectionModel().getSelectedItem()
                            + ":" + from_m.getSelectionModel().getSelectedItem()
                            + " To: " + to_h.getSelectionModel().getSelectedItem()
                            + ":" + to_m.getSelectionModel().getSelectedItem());

                    if (rep.isSelected()){
                        System.out.println("Repeat every " + rep_days.getText() + " days. Stop on " + rep_date.getValue());
                    }

                }
            }
        });

        //Close button
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Platform.exit();

            }
        });

    }

}