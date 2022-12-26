
public class CustomerRequest {
	private String name;
	private int id;
	private String detailsRequest;
	
	public CustomerRequest(String name , int id ,String details)
	{
		this.name = name;
		this.id = id;
		this.detailsRequest = details;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDetailsRequest() {
		return detailsRequest;
	}
	public void setDetailsRequest(String detailsRequest) {
		this.detailsRequest = detailsRequest;
	}
	
	public Boolean equal(CustomerRequest other)
	{
		if(getId() == other.getId() && getDetailsRequest().equals(other.getDetailsRequest()))
		{
			return true;
		}
		else
			return false;

	}

	@Override
	public String toString() {
		return "{name=" + name + ", id=" + id + ", detailsRequest=" + detailsRequest + "}";
	}
	
	
	
}
