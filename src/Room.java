


//Room Class
public class Room {

	private int roomID;
	private String roomName;
	private int PcurrentIndex;
	
	SmartPlug [] pluglist;

	public int plugsSize(){ return pluglist.length; }
	
	
	public Room(int roomID, String roomName) {
		this.roomID = roomID;
		this.roomName = roomName;
		pluglist=new SmartPlug[0];
		PcurrentIndex=0;
	}
	

	



	public void setPluglist(SmartPlug[] pluglist) {
		this.pluglist = pluglist;
	}

	public void add(SmartPlug sp) {
		

		increasePlugs();
		if(PcurrentIndex >= plugsSize()) { return;}
		pluglist[PcurrentIndex] = sp;
		PcurrentIndex++;
		
	}
		public void remove(SmartPlug sp) {
		
		int index=find(pluglist,sp);
		
		if(index>=PcurrentIndex || index<0){
            
        }else{
            for(int i=index;i<PcurrentIndex-1;i++){
            	pluglist[i] = pluglist[i+1];
            }
            pluglist[PcurrentIndex-1]=null;
            PcurrentIndex--;
            decreasePlugs();
          
        }
		
	}
		public int find(SmartPlug[] a, SmartPlug target)
		{
		    for (int i = 0; i < a.length; i++)
		    {
		        if (a[i] == target) {
		            return i;
		        }
		    }
		 
		    return -1;
		}
		
	public void addtoPluglist(SmartPlug sp) {
		add(sp);
	}



	public void increasePlugs() {
		 SmartPlug[] copy=new SmartPlug[plugsSize()+1];
		 System.arraycopy(pluglist, 0, copy, 0, pluglist.length);
		 pluglist = copy;
	}


	public void decreasePlugs() {
		SmartPlug [] temp = new SmartPlug [plugsSize()-1];
		if(temp.length!=0 && temp.length<plugsSize() && plugsSize()!=0)
	    for (int j = 0; j < temp.length; j++) {
	      temp[j] = pluglist[j];
	    }
	    pluglist = temp;
	}

	
	
	public SmartPlug[] getPluglist() {
		if(plugsSize()<1 ) {
			SmartPlug [] empty = new SmartPlug[0];
			
			return empty;
		}
		else {
			
			return pluglist;
		}
			
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
