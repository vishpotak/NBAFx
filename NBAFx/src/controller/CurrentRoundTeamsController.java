package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import model.Game;
import model.Season;


public class CurrentRoundTeamsController extends Controller<Season>{
    
    @FXML private Label currentRoundNo;
    @FXML private TableView<Game>  currentRoundTv;
    
    @FXML private TableColumn<Game, String> team1Clm;
    @FXML private TableColumn<Game, String> versus;
    @FXML private TableColumn<Game, String> team2Clm;

    @FXML public void initialize(){
        currentRoundTv.setItems(getSeason().getCurrentSchedule());
        currentRoundTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        int roundNumber = getSeason().round()+1;
        String roundTxt = "Round: " + roundNumber;
        
        currentRoundNo.setText(roundTxt);
        team1Clm.setCellValueFactory(cellData -> cellData.getValue().team1());
        versus.setCellValueFactory(cellData -> new SimpleStringProperty("VS"));
        team2Clm.setCellValueFactory(cellData -> cellData.getValue().team2());
    }

    @FXML public void close(){
            stage.close();
    }
    
    public Season getSeason(){
        return this.model;
    }

}







