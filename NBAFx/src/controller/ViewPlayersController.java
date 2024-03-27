package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Player;
import model.Team;
import model.Teams;
import model.Players;


public class ViewPlayersController extends Controller<Teams> {

    @FXML public TextField lvlFilterTf;
    @FXML public TextField nmeFilterTf;
    @FXML public TextField fromAgeTf;
    @FXML public TextField toAgeTf;
    
    private ObservableList<Player> storedPlayers;
    
    @FXML private TableView<Player> allPlayersTv;
    
    @FXML private TableColumn<Player, String> teamsNameClm;
    @FXML private TableColumn<Player, String> NameClm;
    @FXML private TableColumn<Player, String> CreditClm;
    @FXML private TableColumn<Player, String> AgeClm;
    @FXML private TableColumn<Player, String> NoClm;
    @FXML private TableColumn<Player, String> LevelClm;
    
    public void initialize(){        
//        for (Team team : getTeams().currentTeams){
//            team.filterPlayersList(nmeFilterTf.getText(), nmeFilterTf.getText(), Integer.parseInt(fromAgeTf.getText()), Integer.parseInt(toAgeTf.getText()));
//            storedPlayers.addAll(team.getPlayers().getFilteredPlayersList());      
//        }
//        
//        allPlayersTv.setItems(storedPlayers);
        allPlayersTv.setItems(getTeams().allPlayersList());
        allPlayersTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        teamsNameClm.setCellValueFactory(cellData -> cellData.getValue().getTeamNameProperty());
        NameClm.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        CreditClm.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asString());
        AgeClm.setCellValueFactory(cellData -> cellData.getValue().getPlayerAgeProperty().asString());
        NoClm.setCellValueFactory(cellData -> cellData.getValue().getPlayerNoProperty().asString());
        LevelClm.setCellValueFactory(cellData -> cellData.getValue().levelProperty());
    }
    
    public void close(){
        stage.close();
    }
    
    private Teams getTeams(){
        return this.model;
    }


}

