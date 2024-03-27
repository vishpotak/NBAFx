package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Game;
import model.Season;
import model.Team;


public class TeamsRoundController extends Controller<Season>  {
    
    public Season getSeason(){
        return this.model;
    }

    @FXML private ListView teamsLv;
    @FXML private TableView<Game> roundedTeamsTv;
    
    @FXML private Button roundTeamsBtn;
    @FXML private Label roundNo;  //see gameClm in roundTeam
    @FXML private Button arrangeSeasonBtn;
    
    @FXML private TableColumn<Game, String> gameClm;
    @FXML private TableColumn<Game, String> team1Clm;
    @FXML private TableColumn<Game, String> team2Clm;
    
    
    
    public ObservableList<Team> getTeams(){
        return teamsLv.getSelectionModel().getSelectedItems();
    }
    
    @FXML public void initialize(){
        teamsLv.setItems(getSeason().getCurrentTeams());
        teamsLv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        int roundNumber = getSeason().round()+1;
        String roundTxt = "Round: " + roundNumber;
        
        roundNo.setText(roundTxt);
        roundedTeamsTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        teamsLv.getSelectionModel().selectedItemProperty().addListener((observable, oldTeam, newTeam) -> roundTeamsBtn.setDisable((getTeams().size() != 2)));
        //arrangeSeasonBtn.setDisable(getSeason().getCurrentTeams().size() == 0); //fix this
        teamsLv.getSelectionModel().selectedItemProperty().addListener((observable, oldTeam, newTeam) ->
                arrangeSeasonBtn.setDisable(!getSeason().getCurrentTeams().isEmpty()));
    }
    
    @FXML public void roundTeams(){
        getSeason().addTeams(getTeams());
        
        roundedTeamsTv.setItems(getSeason().getCurrentSchedule()); //try putting in in initializeS
        
        gameClm.setCellValueFactory(cellData -> cellData.getValue().termProperty().asString()); 
        team1Clm.setCellValueFactory(cellData -> cellData.getValue().team1());
        team2Clm.setCellValueFactory(cellData -> cellData.getValue().team2());
    }
    
    @FXML public void arrangeSeason(){
        this.stage.close();
    }
}



