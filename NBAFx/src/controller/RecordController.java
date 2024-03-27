package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Record;
import model.Season;


public class RecordController extends Controller<Season>{
    
    @FXML private TableView<Record> recordTv;
    
    @FXML private TableColumn <Record, String> round;
    @FXML private TableColumn <Record, String> game;
    @FXML private TableColumn <Record, String> wonTeam;
    @FXML private TableColumn <Record, String> lostTeam;
    
    public void initialize(){
        recordTv.setItems(getSeason().record());
        recordTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        round.setCellValueFactory(cellData -> cellData.getValue().roundProperty().asString());
        game.setCellValueFactory(cellData -> cellData.getValue().gameNumberProperty().asString());
        wonTeam.setCellValueFactory(cellData -> cellData.getValue().winTeamProperty());
        lostTeam.setCellValueFactory(cellData -> cellData.getValue().loseTeamProperty());
    }
    
    @FXML public void close(){
        stage.close();
    }

    private Season getSeason() {
        return this.model;
    }
    
}







