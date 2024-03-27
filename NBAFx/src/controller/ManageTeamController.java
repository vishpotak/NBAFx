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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Player;
import model.Team;
import model.Teams;



public class ManageTeamController extends Controller<Team> {

    @FXML private TextField teamNameTf;
    
    @FXML private TableView<Player> playersTv;
    
    @FXML private TableColumn<Player, String> playerNameClm;
    @FXML private TableColumn<Player, String> playerCreditClm;
    @FXML private TableColumn<Player, String> playerAgeClm;
    @FXML private TableColumn<Player, String> playerNoClm;
    
    @FXML private Button addBtn;
    @FXML private Button updateBtn;
    @FXML private Button deleteBtn;
    @FXML private Button saveAndCloseBtn;
    
    @FXML public void initialize(){
        playersTv.setItems(getTeam().getCurrentPlayers());
        playersTv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                
        playerNameClm.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        playerCreditClm.setCellValueFactory(cellData -> cellData.getValue().getPlayerCreditProperty().asString());
        playerAgeClm.setCellValueFactory(cellData -> cellData.getValue().getPlayerAgeProperty().asString());
        playerNoClm.setCellValueFactory(cellData -> cellData.getValue().getPlayerNoProperty().asString());
        
        playersTv.getSelectionModel().selectedItemProperty().addListener((observable, oldPlayer, newPlayer) -> updateBtn.setDisable(newPlayer == null));
        playersTv.getSelectionModel().selectedItemProperty().addListener((observable, oldPlayer, newPlayer) -> deleteBtn.setDisable(newPlayer == null));
        playersTv.getSelectionModel().selectedItemProperty().addListener((observable, oldPlayer, newPlayer) -> addBtn.setDisable(newPlayer != null));
        
        teamNameTf.setText(getTeam().getName());
    }
    
    
    @FXML public void addPlayer(){ // see which fxml
        try {
            Player newPlayer = new Player("", -1.0, 1, 1);
            newPlayer.setTeam(getTeam());
            
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            
            ViewLoader.showStage(newPlayer, "/view/PlayerUpdateView.fxml", "Adding New Player", stage);
        } catch (IOException ex) {
            Logger.getLogger(ManageTeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML public void updatePlayer(){
        try {
            Stage stage = new Stage();
            stage.setX(ViewLoader.X + 601);
            stage.setY(ViewLoader.Y);
            stage.getIcons().add(new Image("/view/edit.png"));
            
            ViewLoader.showStage(getSelectedPlayer(), "/view/PlayerUpdateView.fxml", "Updating Player: " + getSelectedPlayer().getName(), stage); //add teamName
        } catch (IOException ex) {
            Logger.getLogger(ManageTeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML public void deletePlayer(){
        getTeam().getPlayers().removePlayer(getSelectedPlayer()); // removeplayer vs removeplayer
    }
    
    @FXML public void saveAndClose(){
        try{
            Validator teamValidator = new Validator();
     
            if (teamValidator.isValid(getTeamNameTf())){
                    getTeam().setName(getTeamNameTf());
                    stage.close(); 
            }
            else{
                teamValidator.generateErrors(getTeamNameTf());

                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
                ViewLoader.showStage(teamValidator, "/view/error.fxml", "Error!", stage);

            }
        }
        catch (IOException ex) {
            Logger.getLogger(AddTeamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    private Team getTeam(){
        return this.model;
    }
    
//    private Teams getTeams(){
//        return getTeam().
//    }

    private Player getSelectedPlayer() {
        return playersTv.getSelectionModel().getSelectedItem();
    }

    private String getTeamNameTf() {
        return teamNameTf.getText();
    }
  
}
