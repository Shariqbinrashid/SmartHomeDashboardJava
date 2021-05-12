
//SmartPlug Class
public class SmartPlug {
	private boolean status;
	private String name;
	private int ID;
	private int UserPlugID;
	
	public SmartPlug(boolean status, String name,int ID,int UserPlugID) {
		
		this.status = status;
		this.name = name;
		this.ID = ID;
		this.UserPlugID = UserPlugID;
	}

	
	


	public int getUserPlugID() {
		return UserPlugID;
	}





	public void setUserPlugID(int userPlugID) {
		UserPlugID = userPlugID;
	}





	public int getID() {
			return ID;
		}
	public void setID(int ID) {
		this.ID = ID;
	}

	public boolean isStatus() {
	
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	public void toggle(){
		setStatus(!isStatus());
		}


	
	@Override
	public String toString() {
	return "name: " + name +
	"| status: " + status +
	"| ID: " + ID;
	}


}
