

public class SmartHome {
	private SmartPlug[] smartPlugs;
	private int PcurrentIndex;
	private int RcurrentIndex;
	private Room[] rooms;
	private int roomID=0;
	int userPlugs=0;
	int ch=4;
	
	int userDefinePlugID=0;
	
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
		SmartPlug object = new SmartPlug(value, name,ID,-1);
		smartPlugs[PcurrentIndex] = object;
		PcurrentIndex++;
	}
	
	public void addUserPlugs(String name,int ID,Room r){
		increasePlugs();
		if(PcurrentIndex >= plugsSize()) { return;}
		SmartPlug object = new SmartPlug(false, name,-2,ID);
		r.addtoPluglist(object);
		smartPlugs[PcurrentIndex] = object;
		PcurrentIndex++;
	}
	
	public void preDefine(String[] names){
		smartPlugs = new SmartPlug[names.length];
		for(int i = 0;i<names.length;i++){		
			add(false,names[i],i);
		}
	}
	
	public void DefinePlugs(String names,Room r){
			
		addUserPlugs(names,userDefinePlugID,r);
			userDefinePlugID++;
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


	public SmartPlug[] serachPlug(Room room) {
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
			if(smartPlugs[i].getID()==-2)
				smartPlugs[i].setStatus(k);
		}
	}
	
	
	public void updateRoomplugs(int option,Room r,int o) {
		SmartPlug [] plugs=r.getPluglist();

		
		
		if(option==1) {
			for(int k=0;k<plugs.length;k++) {
				plugs[k].setStatus(false);
			}
		}
		if(option==2) {
			for(int k=0;k<plugs.length;k++) {
				plugs[k].setStatus(true);
			}
		}
		if(option==3) {
			
			
			
			for(int k=0;k<plugs.length;k++) {
				if(o==plugs[k].getUserPlugID())
					plugs[k].toggle();
			}
		}
		
	}
	
	public int getRoom(int plugID){
		for(Room r:rooms ) {
			SmartPlug [] plugs=r.getPluglist();
			for (int i=0;i<plugs.length;i++) {
				if(plugID==plugs[i].getUserPlugID()) {
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
					if(plugID==p.getUserPlugID()) {
						r.remove(p);
					}
					
				}
				
			}
		}
		
		
		
		
		
		for(Room r:rooms ) {
			
			if(r.getRoomID()==roomID) {
				
				
				for(SmartPlug p: smartPlugs) {
					if(plugID==p.getUserPlugID()) {
						r.add(p);
					}
					
				}
				
			}
		}

		
	}
	

	
	public void changePlug(int plugID,int option) {
		for(SmartPlug object : smartPlugs){	
			if(object.getUserPlugID()==plugID) {
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
			if(object.getUserPlugID()==plugID) {
				object.setName(getNamewithId(change));
				object.setStatus(false);
			}
			
		}
	}
	public void moveplug(int change,int roomID) {
		for(SmartPlug object : smartPlugs){	
			if(object.getUserPlugID()==change) {
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
	
	
	public void addPlug(String name) {
		increasePlugs();
		if(PcurrentIndex >= plugsSize()) { return;}
		
		

		SmartPlug object = new SmartPlug(false, name,ch+1,-1);
		smartPlugs[PcurrentIndex] = object;
		PcurrentIndex++;
		ch++;
		
	}
	
	public void increasePlugs() {
		 SmartPlug[] copy=new SmartPlug[plugsSize()+1];
		 System.arraycopy(smartPlugs, 0, copy, 0, smartPlugs.length);
		 smartPlugs = copy;
	}
	
	public void addSmartPlug(int plugID,int rmID) {
			
		String name="";
		for(int i=0;i<plugsSize();i++) {
			if(smartPlugs[i].getID()==plugID)
				 name=smartPlugs[i].getName();
		}
		
		addUserPlugs(name,userDefinePlugID,rooms[rmID]);
		userDefinePlugID++;
		userPlugs++;						
	}
	
	
	public void addRooms(String name) {
		increaseRooms();
		if(RcurrentIndex >= roomsSize()) { return;}
		
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

}
