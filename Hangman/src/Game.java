import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
	private int numOfGuesses;
	private String randomWord;
	private ArrayList<Character> guesseArray;
	private String revealWord;


	public Game()
	{
		this.numOfGuesses = 0;
		this.randomWord = createSecretWord();
		this.guesseArray = new ArrayList<Character>();
		this.revealWord = new String();
	}

    public int getNumOfGuesses() {
		return numOfGuesses;
	}

	public void setNumOfGuesses(int numOfGuesses) {
		this.numOfGuesses = numOfGuesses;
	}



	public String getRandomWord() {
		return randomWord;
	}



	public void setRandomWord(String randomWord) {
		this.randomWord = randomWord;
	}



	public ArrayList<Character> getGuesseArray() {
		return guesseArray;
	}

	public void setGuesseArray(ArrayList<Character> guesseArray) {
		this.guesseArray = guesseArray;
	}
	
	public String getRevealWord() {
		return revealWord;
	}

	public void setRevealWord(String revealWord) {
		this.revealWord = revealWord;
	}
	
	public String fetchtWord()
	{
		return createSecretWord();
	}
	
    private String createSecretWord()
    {
		Random rand = new Random();
    	List<String> words = new ArrayList<String>();
		String[] wordsLine;
		String secretWord = new String();

		try
		{
			FileReader fr = new FileReader("words.txt");
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			while(line != null) 
			{
		        wordsLine = line.split(" ");
		        for(String word : wordsLine) {
		            words.add(word);
		        }
		        line = reader.readLine();
			}
			fr.close();
		}
		catch(IOException e)
		{
			System.out.println("Error");
		}

		secretWord = words.get(rand.nextInt(words.size()));
		return secretWord;
    }
	
	
}
