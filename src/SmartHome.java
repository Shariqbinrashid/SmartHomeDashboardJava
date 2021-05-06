import java.util.ArrayList;

public class SmartHome {
	private SmartPlug[] smartPlugs;
	private int PcurrentIndex;
	private int RcurrentIndex;
	private Room[] rooms;
	private int roomID=0;
	int userPlugs=0;
	public SmartHome(int plugsSize,int roomSize) {
		userPlugs=plugsSize;
		rooms=new Room[roomSize];
		PcurrentIndex = 0;
		RcurrentIndex=0;
		}
	
	
	public int plugsSize(){ return smartPlugs.length; }

	public int roomsSize(){ return rooms.length; }
	
	public void addRoom(String name){
		
		if(RcurrentIndex >= roomsSize()) { return;}
		Room object = new Room(roomID,name);
		rooms[RcurrentIndex] = object;
		RcurrentIndex++;
		roomID++;
	}
	
	
	public void add( boolean value,String name,int ID){
		if(PcurrentIndex >= plugsSize()) { return;}
		SmartPlug object = new SmartPlug(value, name,ID);
		smartPlugs[PcurrentIndex] = object;
		PcurrentIndex++;
	}
	
	public void preDefine(String[] names){
		smartPlugs = new SmartPlug[names.length];
		for(int i = 0;i<names.length;i++){		
			add(false,names[i],i);
		}
	}
	
	
	
	public SmartPlug[] getSmartPlugs() {
		return smartPlugs;
	}


	public void setSmartPlugs(SmartPlug[] smartPlugs) {
		this.smartPlugs = smartPlugs;
	}


	public Room[] getRooms() {
		return rooms;
	}


	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}


	public ArrayList<SmartPlug> serachPlug(Room room) {
		return room.getPluglist();
	}
	
	public String display(){
		String s = "";
		for(SmartPlug object : smartPlugs){
			s += object.toString() + " ";
		}
		return s;
	}


	public int getUserPlugs() {
		return userPlugs;
	}

	public void updatePlugs(boolean k) {
		for (int i=0;i<plugsSize();i++) {
			smartPlugs[i].setStatus(k);
		}
	}
	
	
	public void updateRoomplugs(int option,Room r) {
		ArrayList<SmartPlug> plugs=r.getPluglist();

		
		
		if(option==1) {
			for(int k=0;k<plugs.size();k++) {
				plugs.get(k).setStatus(false);
			}
		}
		if(option==2) {
			for(int k=0;k<plugs.size();k++) {
				plugs.get(k).setStatus(true);
			}
		}
		if(option==3) {
			ConsoleEvents console = new ConsoleEvents();
			int o=console.getInt("Select Plug from above");
			
			for(int k=0;k<plugs.size();k++) {
				if(o==plugs.get(k).getID())
					plugs.get(k).toggle();
			}
		}
		
	}
	
	public int getRoom(int plugID){
		for(Room r:rooms ) {
			ArrayList<SmartPlug> plugs=r.getPluglist();
			for (int i=0;i<plugs.size();i++) {
				if(plugID==plugs.get(i).getID()) {
					return(r.getRoomID());
				}
			}

		}
		return -1;
	}
	
	public void setRoom(int plugID,int roomID){
		int old=getRoom(plugID);
		for(Room r:rooms ) {
			
			if(r.getRoomID()==old) {
				
				
				for(SmartPlug p: smartPlugs) {
					if(plugID==p.getID()) {
						r.pluglist.remove(p);
					}
					
				}
				
			}
		}
		
		
		
		
		
		for(Room r:rooms ) {
			
			if(r.getRoomID()==roomID) {
				
				
				for(SmartPlug p: smartPlugs) {
					if(plugID==p.getID()) {
						r.pluglist.add(p);
					}
					
				}
				
			}
		}

		
	}
	
	public void viewAllPlugs() {
		for(SmartPlug object : smartPlugs){		
				if(getRoom(object.getID())!=-1)
			System.out.println("SMartPlug |attached to: "+object.getName()+"            "+"|room: "+rooms[getRoom(object.getID())].getRoomName()+" |ID: "+object.getID()+"|"+"status: "+object.isStatus());

			
		}
		
	}
	
	public void changePlug(int plugID,int option) {
		for(SmartPlug object : smartPlugs){	
			if(object.getID()==plugID) {
				if (option==1) {
					object.setStatus(false);
				}
				if (option==2) {
					object.setStatus(true);
				}
			}
			
		}
	}
	
	
	public void updateattach(int plugID,int change) {
		for(SmartPlug object : smartPlugs){	
			if(object.getID()==plugID) {
				object.setName(getNamewithId(change));
				object.setID(change);
				
				object.setStatus(false);
			}
			
		}
	}
	public void moveplug(int change,int roomID) {
		for(SmartPlug object : smartPlugs){	
			if(object.getID()==change) {
				setRoom(change,roomID);
			}
			
		}
	}
	
	public String getNamewithId(int ID) {
		for(SmartPlug object : smartPlugs){	
			if(object.getID()==ID) {
				return object.getName();
			}
			
		}
		return null;
	}
	
	
	public void addPlug() {
		increasePlugs();
		ConsoleEvents console = new ConsoleEvents();
		if(PcurrentIndex >= plugsSize()) { return;}
		String name=console.getString("Enter name");
		int ID=plugsSize();

		SmartPlug object = new SmartPlug(false, name,ID-1);
		smartPlugs[PcurrentIndex] = object;
		PcurrentIndex++;
		
		
	}
	
	public void increasePlugs() {
		 SmartPlug[] copy=new SmartPlug[plugsSize()+1];
		 System.arraycopy(smartPlugs, 0, copy, 0, smartPlugs.length);
		 smartPlugs = copy;
	}
	
	public void addSmartPlug() {
		ConsoleEvents console = new ConsoleEvents();


		System.out.println("ROOMS AVAIALBLE:  ");
		for(int i = 0;i<roomsSize();i++){
			System.out.print(i+" - "+rooms[i].getRoomName()+" | ");		
		
		}
		try {
		roomID = console.getInt("Using the above list, please select the room for this plug (integer only)");
		
		System.out.println("AVAILABLE DEVICE LIST OPTIONS: ");
		System.out.println("These are standard devices that can be attached to the smart plug: ");
		
		
		for(int j = 0;j<plugsSize();j++){
			System.out.println(j+" "+smartPlugs[j].getName());
		}
		
		
		int plugID=console.getInt("Using the above list, please select the device to attach to the smart plug (integer only)");
		rooms[roomID].addtoPluglist(smartPlugs[plugID]);
		}
		catch(Exception e) {
			console.out("Invalid input/Exception");
		}
		userPlugs++;
		
	}
	
	
	public void addRooms() {
		increaseRooms();
		ConsoleEvents console = new ConsoleEvents();
		if(RcurrentIndex >= plugsSize()) { return;}
		String name=console.getString("Enter name of room");
		int ID=roomsSize();

		Room object = new Room( ID-1,name);
		rooms[RcurrentIndex] = object;
		RcurrentIndex++;
	}
	
	public void increaseRooms() {
		 Room[] copy=new Room[roomsSize()+1];
		 System.arraycopy(rooms, 0, copy, 0, rooms.length);
		 rooms = copy;
	}
	
	
	
	
	public void setUserPlugs(int userPlugs) {
		this.userPlugs = userPlugs;
	}

	public void getAvaialablePlugs() {
		for(int j = 0;j<plugsSize();j++){
			System.out.println(j+" "+smartPlugs[j].getName());
		}
	}
	
	public void getAvailableRooms() {
		System.out.println("ROOMS AVAIALBLE:  ");
		for(int i = 0;i<roomsSize();i++){
			System.out.print(i+" - "+rooms[i].getRoomName()+" | ");		
		
		}
	}
	
}
