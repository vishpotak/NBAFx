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
import model.Player;
import model.Team;



public class PlayerUpdateController extends Controller<Player> {
   
    @FXML private TextField newNameTf;
    @FXML private TextField newCreditTf;
    @FXML private TextField newAgeTf;
    @FXML private TextField newNoTf;
    
    @FXML private Button updateBtn;
    @FXML private Button addBtn;
    @FXML private Button closeBtn;
    
    @FXML public void initialize(){
//        addBtn.setDisable(getPlayer() != null);
//        updateBtn.setDisable(getPlayer() != null);
        
        addBtn.setDisable(!getPlayer().hasName(""));
        updateBtn.setDisable(getPlayer().hasName(""));
        
        newNameTf.setText(getPlayer().getName());
        newCreditTf.setText(Double.toString(getPlayer().getCredit()));
        newAgeTf.setText(Integer.toString(getPlayer().getAge()));
        newNoTf.setText(Integer.toString(getPlayer().getNo())); 
    }
    
    @FXML public void updatePlayer() throws Exception{
        Validator PlayerValidator = new Validator();
        if (PlayerValidator.isValid(newNameTf.getText(), newCreditTf.getText(), newAgeTf.getText() , newNoTf.getText())){
            if(!getPlayer().getTeam().getPlayers().hasPlayer(getNewName())){
                getPlayer().setName(newNameTf.getText());
                getPlayer().setCredit(Double.parseDouble(newCreditTf.getText()));
                getPlayer().setAge(Integer.parseInt(newAgeTf.getText()));
                getPlayer().setNo(Integer.parseInt(newNoTf.getText()));
                stage.close();
            }
            else{
                PlayerValidator.addError(newNameTf.getText() + " Player name already exists");
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
                ViewLoader.showStage(PlayerValidator, "/view/error.fxml", "Error!", stage);
            }
        }
        else{
            try{
                PlayerValidator.generateErrors(getNewName(), Double.toString(getNewCredit()), Integer.toString(getNewAge()) , Integer.toString(getNewNo()));
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
                ViewLoader.showStage(PlayerValidator, "/view/error.fxml", "Input Errors", stage);
            }
            catch(IOException ex) {
                Logger.getLogger(AddTeamController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML public void addPlayer() throws Exception{
        
        Validator PlayerValidator = new Validator();

        if (PlayerValidator.isValid(getNewName(), Double.toString(getNewCredit()), Integer.toString(getNewAge()) , Integer.toString(getNewNo()))){
            if(!getPlayer().getTeam().getPlayers().hasPlayer(getNewName())){
                getPlayer().getTeam().getPlayers().addPlayer(new Player(getNewName(), getNewCredit(), getNewAge(), getNewNo()));
                stage.close();
            }
            else{
                PlayerValidator.addError(newNameTf.getText() + " Player name already exists");
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
                ViewLoader.showStage(PlayerValidator, "/view/error.fxml", "Error!", stage);
            }
        }
        else{
            try{
                PlayerValidator.generateErrors(getNewName(), Double.toString(getNewCredit()), Integer.toString(getNewAge()) , Integer.toString(getNewNo()));
                Stage stage = new Stage();
                stage.setX(ViewLoader.X + 601);
                stage.setY(ViewLoader.Y);
                stage.getIcons().add(new Image("/view/error.png"));
                ViewLoader.showStage(PlayerValidator, "/view/error.fxml", "Input Errors", stage);
            }
            catch (IOException ex) {
                Logger.getLogger(AddTeamController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    
    @FXML public void close() {
        stage.close();
    }
    
    private Player getPlayer(){
        return this.model;
    }
    
    public String getNewName() {
        return newNameTf.getText();
    }

    public double getNewCredit() {
        return Double.parseDouble(newCreditTf.getText());
    }

    public int getNewAge() {
        return Integer.parseInt(newAgeTf.getText());
    }

    public int getNewNo() {
        return Integer.parseInt(newNoTf.getText());
    }
    
}
    
    
//    public String getNewNameStr() {
//        return newNameTf.getText();
//      }
//
//    public String getNewCreditStr() {
//        return newCreditTf.getText();
//      }
//
//    public String getNewAgeStr() {
//        return newAgeTf.getText();
//      }
//
//    public String getNewNoStr() {
//        return newNoTf.getText();
//    }


    
//    private void setNameTf(String name){
//         newNameTf.setText(name);
//    }
//    
//    private void setCreditTf(String credit){
//         //newCreditTf.setText();
//    }
//    
//    private void setAgeTf(int age){
//         newAgeTf.getText();
//    }
//    
//    private void setNoTf(int no){
//         newNoTf.getText();
//    }
    


//if(getPlayer().getTeam().getPlayers().hasPlayer(getNewName())){
//                     getPlayer().getTeam().getPlayers().addPlayer(new Player(getNewName(), getNewCredit(), getNewAge(), getNewNo()));
//                }
//                else{
//                    PlayerValidator.addError(getPlayer().getName() + " Player already exists");
//                    Stage stage = new Stage();
//                    stage.setX(ViewLoader.X + 601);
//                    stage.setY(ViewLoader.Y);
//                    stage.getIcons().add(new Image("/view/error.png"));
//                    ViewLoader.showStage(PlayerValidator, "/view/error.fxml", "Error!", stage);
//                }
