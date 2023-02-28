
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class HangmanController {

	final int LETTERS  = 26;
	final int LIMIT_NUMBER_MISTAKES = 5;
	final int SHOW_HEAD = 1;
	final int SHOW_CHEST = 2;
	final int SHOW_RIGHT_ARM = 3;
	final int SHOW_LEFT_ARM = 4;
	final int SHOW_RIGHT_LEG = 5;
	final int SHOW_LEFT_LEG = 6;

    @FXML
    private Label guesses;

    @FXML
    private ComboBox<Character> letters;

    @FXML
    private Label rightLetters;
    
    @FXML
    private Label feedback;

    @FXML
    private Circle head;

    @FXML
    private Line leftArm;

    @FXML
    private Line leftLeg;
    
    @FXML
    private Line chest;
    
    @FXML
    private Line rightArm;

    @FXML
    private Line rightLeg;
	
	private Game game;

	@FXML
	public void initialize() {

		stateOfAllBodyShapes(false);

		game = new Game();
		
		for(int j = 0; j < game.getRandomWord().length();j++)
    	{
    		game.setRevealWord(game.getRevealWord() + '_');
    	}
		rightLetters.setText(game.getRevealWord());
		
		initCombobox();
	}

    @FXML
    void guessPressed(ActionEvent event) {
    	
    	if(letters.getSelectionModel().isEmpty() == true)
    	{
			feedback.setText("You must choose letter!");
    	}
    	else
    	{
    		if(game.getNumOfGuesses() > LIMIT_NUMBER_MISTAKES)
        	{
    			feedback.setText("You lost!" + "\nThe right word is : " + game.getRandomWord());
        	}
        	else
        	{
        		if( !(game.getRevealWord().equals(game.getRandomWord())))
        		{
        			drawLetterInWord(letters.getValue());

        			rightLetters.setText(game.getRevealWord());

        			if(game.getRevealWord().equals(game.getRandomWord()))
            		{
            			feedback.setText("You Won!");
            		}

                	game.getGuesseArray().add(letters.getValue());
                	String stringOfGuesses = "";

                	for(int i = 0 ; i < game.getGuesseArray().size() ;i++)
                	{
                		stringOfGuesses += " " +   game.getGuesseArray().get(i);
                	}
                		
            		guesses.setText(stringOfGuesses);

        			switch(game.getNumOfGuesses())
        			{
        				case SHOW_HEAD: head.setVisible(true);
        					   break;
        				case SHOW_CHEST: chest.setVisible(true);
    						   break;
        				case SHOW_RIGHT_ARM: rightArm.setVisible(true);
    						   break;
        				case SHOW_LEFT_ARM: leftArm.setVisible(true);
    						   break;
        				case SHOW_RIGHT_LEG: rightLeg.setVisible(true);
    						   break;
        				case SHOW_LEFT_LEG: leftLeg.setVisible(true);
            					feedback.setText("You lost!" + "\nThe right word is : " + game.getRandomWord());
            					break;
        			}

            		updateCombobox(letters.getValue());
        		}
        	}
    	}
    }
    
    private void drawLetterInWord(char letter)
	{
		int foundLetterFlag = 0; // 0 = not found a single letter  , 1 = found at least one letter

		for(int i = 0; i < game.getRandomWord().length();i++)
    	{
    		if(game.getRandomWord().charAt(i) == letter)
    		{
    			foundLetterFlag = 1;
    			game.setRevealWord( game.getRevealWord().substring(0,i) + letter + game.getRevealWord().substring(i+1,game.getRandomWord().length()) );
    		}
    	}
    	if ( foundLetterFlag == 0)
    	{
    		game.setNumOfGuesses(game.getNumOfGuesses() + 1);
    	}
	} 
    
	private void updateCombobox(char letter)
	{
    	for(int i = 0; i< letters.getItems().size();i++)
    	{
    		if( letter == letters.getItems().get(i) )
    			letters.getItems().remove(i);
    	}
	}
    
    @FXML
    void resetPressed(ActionEvent event) {
    	game.setNumOfGuesses(0);

    	game.setRandomWord(game.fetchtWord());
    	
	    game.setRevealWord("");
		for(int j = 0; j < game.getRandomWord().length();j++)
    	{
    		game.setRevealWord(game.getRevealWord() + '_');
    	}
    	
		rightLetters.setText(game.getRevealWord());
    	guesses.setText("");
    	feedback.setText("");
    	
		stateOfAllBodyShapes(false);
    	
    	game.getGuesseArray().clear();
    	initCombobox();
    }

    private void stateOfAllBodyShapes(Boolean visible)
    {
		head.setVisible(visible);
		chest.setVisible(visible);
		leftArm.setVisible(visible);
		rightArm.setVisible(visible);
		leftLeg.setVisible(visible);
		rightLeg.setVisible(visible);
    }
	
	private void initCombobox() 
	{
		letters.getItems().clear();
    	char letter = 'a';
    	for(int i = 0; i< LETTERS;i++)
    	{
    		letters.getItems().add(letter);
    		letter += 1;
    	}
    	letters.setValue('a');
	}
}
