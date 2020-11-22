package gameMain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.util.Optional;

public class Controller {
    @FXML
    private BorderPane mainBorderpane;
    @FXML
    private Label correctGuesses;
    @FXML
    private Label totalGuesses;
    @FXML
    private Button matchButton;
    @FXML
    private Label scoreLabel;
    @FXML
    private TextField scoreTextField;
    @FXML
    private Label resultsofMatch;

    private int corrrectGuessed=0;
    private int noOfGuesses=0;


    public void clickMatchButton(ActionEvent actionEvent) {
        //Way of knowing that the user entered text other that a numeric value...
        resultsofMatch.setText("");
        if (!scoreTextField.getText().isEmpty()){
            int guess=Integer.parseInt(scoreTextField.getText());
            int correctNo= (int) (Math.random() * 5+1);
            if (guess==correctNo){
                corrrectGuessed++;
                noOfGuesses++;

                resultsofMatch.setText("Congratulations!!! You guessed correctly");

            }else{
                noOfGuesses++;
                resultsofMatch.setText("Ooops!!! Your guess is incorrect,try again. The correct guess was "+correctNo);
            }
        }else{
            Alert noInput = new Alert(Alert.AlertType.WARNING);
            noInput.setContentText("Please Enter a valid guess");
            noInput.showAndWait();
        }

        totalGuesses.setText(noOfGuesses+"");
        correctGuesses.setText(corrrectGuessed+"");
        scoreTextField.setText("");


    }

    public void exitGame(ActionEvent actionEvent) {
        Alert confirmQuit=new Alert(Alert.AlertType.CONFIRMATION);
        confirmQuit.setContentText("Do you wanna really quit?");
        Optional<ButtonType> result=confirmQuit.showAndWait();
        if (result.isPresent()&&result.get()==ButtonType.OK){
            Alert finalResults=new Alert(Alert.AlertType.INFORMATION);
            double grade=(double)(Integer.parseInt(correctGuesses.getText()))/(Integer.parseInt(totalGuesses.getText()));
            finalResults.setTitle("Results");
            finalResults.setHeaderText("Your form : "+rating(grade*100));
            finalResults.setContentText("Your final results: "+(grade*100)+" %");
            finalResults.showAndWait();
           // clear everything so as to start the game
            scoreTextField.clear();
            correctGuesses.setText("");
            totalGuesses.setText("");
            corrrectGuessed=0;
            noOfGuesses=0;
            resultsofMatch.setText("");



        }
    }
    private String rating(double score){
        String myRating="good";
        if (score<40.0){
            myRating="poor";
        }else if (score <60.0){
            myRating="average";
        }else if (score<80){
            myRating="good";
        }else{
            myRating="Excellent";
        }
        return myRating;
    }
}
