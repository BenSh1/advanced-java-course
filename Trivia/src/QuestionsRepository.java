import java.util.ArrayList;

public class QuestionsRepository {
	
    private ArrayList<Question> scienceQuestion; 
    private ArrayList<Question> geographyQuestion;     
    private ArrayList<Question> historyQuestion;
    
    public QuestionsRepository()
    {
    	this.scienceQuestion = new ArrayList<Question>();
    	this.geographyQuestion = new ArrayList<Question>();
    	this.historyQuestion = new ArrayList<Question>();

    }

    
	public ArrayList<Question> getScienceQuestion() {
		return scienceQuestion;
	}
	public void setScienceQuestion(ArrayList<Question> scienceQuestion) {
		this.scienceQuestion = scienceQuestion;
	}
	public ArrayList<Question> getGeographyQuestion() {
		return geographyQuestion;
	}
	public void setGeographyQuestion(ArrayList<Question> geographyQuestion) {
		this.geographyQuestion = geographyQuestion;
	}
	public ArrayList<Question> getHistoryQuestion() {
		return historyQuestion;
	}
	public void setHistoryQuestion(ArrayList<Question> historyQuestion) {
		this.historyQuestion = historyQuestion;
	} 


    
	

}
