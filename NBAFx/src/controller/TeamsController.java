package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Team;
import model.Teams;


public class TeamsController extends Controller<Teams>{
    
    @FXML private TableView<Team> teamsTv;
    
    @FXML private TableColumn<Team, String> teamNameClm;
    @FXML private TableColumn<Team, String> teamPlayersClm;
    @FXML private TableColumn<Team, String> avgCreditClm;
    @FXML private TableColumn<Team, String> avgAgeClm;
    
    @FXML private Button addBtn;
    @FXML private Button manageBtn;
    @FXML private Button deleteBtn;
    @FXML private Button closeBtn;
    
    
    @FXML public void initialize(){
        teamsTv.setItems(getTeams().currentTeams());
        teamsTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                
        teamNameClm.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        teamPlayersClm.setCellValueFactory(cellData -> cellData.getValue().countPlayerProperty().asString());
        avgCreditClm.setCellValueFactory(cellData -> cellData.getValue().countAvgCreditProperty().asString());
        avgAgeClm.setCellValueFactory(cellData -> cellData.getValue().countAvgAgeProperty().asString());
        
        teamsTv.getSelectionModel().selectedItemProperty().addListener((observable, oldTeam, newTeam) -> manageBtn.setDisable(newTeam == null));
        teamsTv.getSelectionModel().selectedItemProperty().addListener((observable, oldTeam, newTeam) -> deleteBtn.setDisable(newTeam == null));
        teamsTv.getSelectionModel().selectedItemProperty().addListener((observable, oldTeam, newTeam) -> addBtn.setDisable(newTeam != null));
        
    }
    
    public Teams getTeams(){
        return this.model;
    }
    
    public Team getSelectedTeam(){
        return teamsTv.getSelectionModel().getSelectedItem();
    }
    
    @FXML 
    public void addTeam(){
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            
            ViewLoader.showStage(getTeams(), "/view/AddTeam.fxml", "Adding New Team", stage);
        } catch (IOException ex) {
            Logger.getLogger(TeamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    @FXML
    public void manageTeam(){
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            
            ViewLoader.showStage(getSelectedTeam(), "/view/ManageTeamView.fxml", "Manage Team: " + getSelectedTeam().getName(), stage);
        } catch (IOException ex) {
            Logger.getLogger(TeamsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void deleteTeam(){
        getTeams().remove(getSelectedTeam());
    }
    
    @FXML
    public void close(){
        stage.close();
    }
}
