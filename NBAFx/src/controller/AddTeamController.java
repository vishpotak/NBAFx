package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Team;
import model.Teams;



public class AddTeamController extends Controller<Teams>{
    
    @FXML private TextField teamNameTf;
    @FXML private Button teamAddedBtn;

    @FXML public void addNewTeam() throws IOException{
        try{
            Validator teamValidator = new Validator();
     
            if (teamValidator.isValid(getTeamName())){
                if(!getTeams().hasTeam(getTeamName())){
                    getTeams().addTeam(new Team(getTeamName()));
                    stage.close();
                }
                else{
                    teamValidator.addError(getTeamName() + " Team already exists");
                    Stage stage = new Stage();
                    stage.setX(ViewLoader.X + 601);
                    stage.setY(ViewLoader.Y);
                    stage.getIcons().add(new Image("/view/error.png"));
                    ViewLoader.showStage(teamValidator, "/view/error.fxml", "Error!", stage);
                }
            }
            else{
                teamValidator.generateErrors(getTeamName());

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
    
    public Teams getTeams(){
        return this.model;
    }
    
    private String getTeamName(){
        return teamNameTf.getText();
    }
    
    private void setTeamName(String newTeamName){
        teamNameTf.setText(newTeamName);
    }
    
}
