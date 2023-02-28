import java.io.Serializable;
import java.util.Objects;

public class Details implements Serializable{
	private String nameOfBook;
	private String nameOfAuthor;
	private String additionalInfo;
	public Details(String nameOfBook, String nameOfAuthor, String additionalInfo) {
		super();
		this.nameOfBook = nameOfBook;
		this.nameOfAuthor = nameOfAuthor;
		this.additionalInfo = additionalInfo;
	}
	public String getNameOfBook() {
		return nameOfBook;
	}
	public void setNameOfBook(String nameOfBook) {
		this.nameOfBook = nameOfBook;
	}
	public String getNameOfAuthor() {
		return nameOfAuthor;
	}
	public void setNameOfAuthor(String nameOfAuthor) {
		this.nameOfAuthor = nameOfAuthor;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nameOfBook, nameOfAuthor, additionalInfo);
	}


}
