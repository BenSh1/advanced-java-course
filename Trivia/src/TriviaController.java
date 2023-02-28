import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;

public class TriviaController {
	
	final int SCORE_CORRECT_ANSWER = 10;
	final int SCORE_NOT_CORRECT_ANSWER = -5;
	final int EMPTY_QUESTION_REPOSITORY = 0;
	final int START_SCORE = 0;

    @FXML
    private Label feedback;
    
    @FXML
    private Button geography;

    @FXML
    private Button history;

    @FXML
    private RadioButton option1;

    @FXML
    private RadioButton option2;

    @FXML
    private RadioButton option3;

    @FXML
    private RadioButton option4;

    @FXML
    private ToggleGroup options;

    @FXML
    private Button science;

    @FXML
    private Label showQuestion;

    @FXML
    private Button submit;
    
    @FXML
    private ToolBar holdCategory;

    @FXML
    private Button finish;

    private Game game;
    
	@FXML
	public void initialize() {
    	
    	game = new Game();

    	game.handleFile();
		
		showQuestion.setText("Choose one option from the category below to get started\nCategory : Science | Geography | History");
		option1.setText("-");
		option2.setText("-");
		option3.setText("-");
		option4.setText("-");	
	}

    private void checkingAnswer(ArrayList<Question> list)
    {
    	if(option1.isSelected())
		{
    		if(option1.getText().equals(list.get(game.getRandomIndexQuestion()).getCorrectAnswer())  )
			{
		    	feedback.setText("Correct!");
		    	game.setScore(game.getScore() + SCORE_CORRECT_ANSWER);
			}
			else
			{
		    	feedback.setText("Not correct! ,The right answer is :" + list.get(game.getRandomIndexQuestion()).getCorrectAnswer());
		    	game.setScore(game.getScore() + SCORE_NOT_CORRECT_ANSWER);
			}

		}
    	else if(option2.isSelected())
		{
    		if(option2.getText().equals(list.get(game.getRandomIndexQuestion()).getCorrectAnswer())  )
			{
		    	feedback.setText("Correct!");
			    game.setScore(game.getScore() + SCORE_CORRECT_ANSWER);
			}
			else
			{
		    	feedback.setText("Not correct! ,The right answer is :" + list.get(game.getRandomIndexQuestion()).getCorrectAnswer());
			    game.setScore(game.getScore() + SCORE_NOT_CORRECT_ANSWER);
			}
		}
    	else if(option3.isSelected())
		{
			if(option3.getText().equals(list.get(game.getRandomIndexQuestion()).getCorrectAnswer())  )
    		{
		    	feedback.setText("Correct!");
			    game.setScore(game.getScore() + SCORE_CORRECT_ANSWER);
			}
			else
			{
		    	feedback.setText("Not correct! ,The right answer is :" + list.get(game.getRandomIndexQuestion()).getCorrectAnswer());
			    game.setScore(game.getScore() + SCORE_NOT_CORRECT_ANSWER);
			}
		}
       	else if(option4.isSelected())
    	{
    		if(option4.getText().equals(list.get(game.getRandomIndexQuestion()).getCorrectAnswer())  )
       		{
		    	feedback.setText("Correct!");
    			game.setScore(game.getScore() + SCORE_CORRECT_ANSWER);
    		}
    		else
    		{
		    	feedback.setText("Not correct! ,The right answer is :" + list.get(game.getRandomIndexQuestion()).getCorrectAnswer());
    		    game.setScore(game.getScore() + SCORE_NOT_CORRECT_ANSWER);
    		}
    	}
    	
    	list.remove(game.getRandomIndexQuestion());
		nextQuestion(list );
    }

    @FXML
    void answerPressed(ActionEvent event) {
    	if( !(option1.isSelected()) && !(option2.isSelected()) && !(option3.isSelected()) && !(option4.isSelected()))
    	{
    		feedback.setText("You must choose one option!");
    	}
    	else
    	{

			if(game.getChosenTopic().equals("Science"))
    		{
    			if(game.getRepositoryQues().getScienceQuestion().size() == EMPTY_QUESTION_REPOSITORY)
    			{
    	    		feedback.setText("No question left!");
    			}
    
    			else
    				checkingAnswer(game.getRepositoryQues().getScienceQuestion());
    			
    		}
    		else if(game.getChosenTopic().equals("Geography"))
    		{
        		if(game.getRepositoryQues().getGeographyQuestion().size() == EMPTY_QUESTION_REPOSITORY)
    			{
    	    		feedback.setText("No question left!");
    			}
    			else
    				checkingAnswer(game.getRepositoryQues().getGeographyQuestion());
    		}
    		else if(game.getChosenTopic().equals("History"))
    		{
        		if(game.getRepositoryQues().getHistoryQuestion().size() == EMPTY_QUESTION_REPOSITORY)
    			{
    	    		feedback.setText("No question left!");
    			}
    			else
    				checkingAnswer(game.getRepositoryQues().getHistoryQuestion());
    		}
    	}
    }
    
    
    private void nextQuestion(ArrayList<Question>  arr) {

    	if(arr.size()> 0)
    	{
        	Random rand = new Random();
        	game.setRandomIndexQuestion(rand.nextInt(arr.size()));
        	
        	showQuestion.setText(arr.get(game.getRandomIndexQuestion()).getQuestion());
    		option1.setText(arr.get(game.getRandomIndexQuestion()).getAnswer1());
    		option2.setText(arr.get(game.getRandomIndexQuestion()).getAnswer2());
    		option3.setText(arr.get(game.getRandomIndexQuestion()).getAnswer3());
    		option4.setText(arr.get(game.getRandomIndexQuestion()).getAnswer4());
    		
    		option1.setSelected(false);
    		option2.setSelected(false);
    		option3.setSelected(false);
    		option4.setSelected(false);
    	}
    	else
    	{
    		showQuestion.setText("Very Well,the quiz is over - no question left!.\nYour score is :" 
    	    		+ game.getScore() + "\nfor statring new game press on the button new-game");
    		
    		option1.setVisible(false);
    		option2.setVisible(false);
    		option3.setVisible(false);
    		option4.setVisible(false);
    		
    		finish.setVisible(false);
    		
        	stateOfAllCategoryButton(false);
    	}
	}
    
    

    @FXML
    void geographyPressed(ActionEvent event) {
    	game.setChosenTopic(geography.getText());
		initQuestion(game.getRepositoryQues().getGeographyQuestion());
		
		stateOfAllCategoryButton(false);
		holdCategory.setVisible(false);
    }
    

    @FXML
    void historyPressed(ActionEvent event) {
    	
    	game.setChosenTopic(history.getText());
    	initQuestion(game.getRepositoryQues().getHistoryQuestion());

    	stateOfAllCategoryButton(false);
		holdCategory.setVisible(false);

    }
    
    private void stateOfAllCategoryButton(Boolean visible)
    {
    	geography.setVisible(visible);
    	history.setVisible(visible);
    	science.setVisible(visible);
    }

    @FXML
    void sciencePressed(ActionEvent event) {
    	game.setChosenTopic(science.getText());
    	initQuestion(game.getRepositoryQues().getScienceQuestion());

    	stateOfAllCategoryButton(false);
		holdCategory.setVisible(false);

    }
    
    
    private void initQuestion(ArrayList<Question>  arr) {
    	Random rand = new Random();
    	game.setRandomIndexQuestion(rand.nextInt(arr.size()));
    	
    	showQuestion.setText(arr.get(game.getRandomIndexQuestion()).getQuestion());
		option1.setText(arr.get(game.getRandomIndexQuestion()).getAnswer1());
		option2.setText(arr.get(game.getRandomIndexQuestion()).getAnswer2());
		option3.setText(arr.get(game.getRandomIndexQuestion()).getAnswer3());
		option4.setText(arr.get(game.getRandomIndexQuestion()).getAnswer4());
	}
    
    @FXML
    void resetPressed(ActionEvent event) {
    	game.handleFile();
    	
    	game.setScore(START_SCORE);
    	game.setChosenTopic("");
    	feedback.setText("");
    	
    	game.getRepositoryQues().getScienceQuestion().clear();
    	game.getRepositoryQues().getGeographyQuestion().clear();
    	game.getRepositoryQues().getHistoryQuestion().clear();

		game.handleFile();

		showQuestion.setText("Choose one option from the category below to get started\nCategory : Science | Geography | History");
		option1.setText("-");
		option2.setText("-");
		option3.setText("-");
		option4.setText("-");	
		
		option1.setVisible(true);
		option2.setVisible(true);
		option3.setVisible(true);
		option4.setVisible(true);
		
		option1.setSelected(false);
		option2.setSelected(false);
		option3.setSelected(false);
		option4.setSelected(false);
    	
		stateOfAllCategoryButton(true);
		holdCategory.setVisible(true);

		submit.setVisible(true);
		
		finish.setVisible(true);


		
    }
    
    @FXML
    void finishPressed(ActionEvent event) {
    	
    	showQuestion.setText("Very Well,the quiz is over on your demand.\nYour score is : " 
				+ 	game.getScore() + "\nfor statring new game press on the button new-game");
    	
    	feedback.setText("");

		option1.setVisible(false);
		option2.setVisible(false);
		option3.setVisible(false);
		option4.setVisible(false);
		
		stateOfAllCategoryButton(false);
		
		finish.setVisible(false);
		holdCategory.setVisible(false);
		
		submit.setVisible(false);
    }

}
