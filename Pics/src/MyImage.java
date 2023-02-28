import java.util.LinkedList;

public class MyImage {
    private LinkedList<String> nameOfImages;
    private int indexOfImage;
    
	public MyImage() {
		this.nameOfImages = new LinkedList<>();
		this.indexOfImage = 0;
	}	
	
	public LinkedList<String> getNameOfImages() {
		return nameOfImages;
	}
	public void setNameOfImages(LinkedList<String> nameOfImages) {
		this.nameOfImages = nameOfImages;
	}
	public int getIndexOfImage() {
		return indexOfImage;
	}
	public void setIndexOfImage(int indexOfImage) {
		this.indexOfImage = indexOfImage;
	}
    
    


}
