import java.util.ArrayList;


//Room Class
public class Room {

	private int roomID;
	private String roomName;
	ArrayList<SmartPlug>  pluglist= new ArrayList<SmartPlug>();

	
	
	
	public Room(int roomID, String roomName) {
		this.roomID = roomID;
		this.roomName = roomName;
	}
	

	public ArrayList<SmartPlug> getPluglist() {
		return pluglist;
	}

	public void setPluglist(ArrayList<SmartPlug> pluglist) {
		this.pluglist = pluglist;
	}


	public void addtoPluglist(SmartPlug sp) {
		this.pluglist.add(sp);
	}



	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	
	
}
