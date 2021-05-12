
//SmartPlug Class
public class SmartPlug {
	private boolean status;
	private String name;
	private int ID;

	
	public SmartPlug(boolean status, String name,int ID) {
		
		this.status = status;
		this.name = name;
		this.ID = ID;
	
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
