import java.util.Scanner;


//Console Helper class
public class ConsoleEvents {
	
	
	public void populateRoom(SmartHome factory){
		for(int i = 0;i<factory.roomsSize();i++){
			String name = getString("Please provide a name for your "+i+" room ");
			factory.addRoom(name);
		}
	}
	
	
	public void populate(SmartHome factory){
		
		String[] initialAttachPlugs= {"Heater","Lamp","TV","Computer","Phone Charger"};
		factory.preDefine(initialAttachPlugs);
		
		out("ENTER PLUG INFORMATION BELOW: ");
		
		
		AttachDevice[] availablePlugs=factory.getDevices();
		int roomID=0;
		int plugID=0;
		int v=0;
		
		for(int k = 0;k<factory.getUserPlugs();k++){
			
			v=k+1;
			out("Plug no.   "+v);
			
			
			out("ROOMS AVAIALBLE:  ");
			for(int i = 0;i<factory.roomsSize();i++){
				System.out.print(i+" - "+factory.getRooms()[i].getRoomName()+" | ");		
			}
			
			try {
			roomID = getInt("Using the above list, please select the room for this plug (integer only)");
			
			out("AVAILABLE DEVICE LIST OPTIONS: ");
			out("These are standard devices that can be attached to the smart plug: ");
			
			
			for(int j = 0;j<factory.attachSize();j++){
				
				out(factory.getDevices()[j].getID()+" "+factory.getDevices()[j].getName());
			}
			
			plugID=getInt("Using the above list, please select the device to attach to the smart plug (integer only)");
			
			factory.DefinePlugs(availablePlugs[plugID].getName(),factory.getRooms()[roomID]);
			
			}
			catch(Exception e) {
				out("Invalid input/Exception");
			}		
		}
		
	}
	
	
	
	public void dashboard(SmartHome factory) {
				out("------------DASHBOARD-----------");
		
		Room[] roomslist=factory.getRooms();
		for(int i=0;i<factory.roomsSize();i++) {		

			SmartPlug[] plugs=roomslist[i].getPluglist();
			out("Room: "+roomslist[i].getRoomName()+" ID:"+i);
			for(int k=0;k<plugs.length;k++) {
				
				out("SMartPlug |attached to: "+plugs[k].getName()+"          "+"|room: "+roomslist[i].getRoomName()+" |ID: "+plugs[k].getID()+"|"+"status: "+plugs[k].isStatus());
			}				
			
		}
		out("");
	}
	
	public void menuOptions(SmartHome factory) {
		Room[] roomslist=factory.getRooms();
		
		out("----------MENU OPTIONS-----------");
		out("--------please select option:----");
		out("1 - house level options");
		out("2 - room level options");
		out("3 - plug level options");
		out("4 - system options");
		int optionLevel = getInt("");
		switch (optionLevel) {
			case 1:
			case1(factory);
			break;
			case 2:
			case2(factory);
				
			break;
			case 3:
				case3(factory);
				
				break;
			case 4:
				case4(factory);
				break;
			default:
			out("please enter a valid option");
		}
	}
	
	
	public void case1(SmartHome factory) {
		out("House Level Options ");
		out("1- Switch all plugs off");
		out("2- Switch all plugs on");
		out("Select an option");
		int option=getInt("");
		if(option==1)
			factory.updatePlugs(false);
		else if(option==2)
			factory.updatePlugs(true);
	}
public void case2(SmartHome factory) {
	Room[] roomslist=factory.getRooms();
	Room[] avaialbeRooms=factory.getRooms();		
	
	out("ROOMS AVAIALBLE:  ");
	for(int i = 0;i<factory.roomsSize();i++){
		System.out.print(i+" - "+factory.getRooms()[i].getRoomName()+" | ");		
	}
	
	int roomID = getInt("Please select rooms(integer only)");
	SmartPlug[] plugs=avaialbeRooms[roomID].getPluglist();
	
	for(int k=0;k<plugs.length;k++) {
		
		
		out("SMartPlug |attached to: "+plugs[k].getName()+"            "+"|room: "+roomslist[roomID].getRoomName()+" |ID: "+plugs[k].getID()+"|"+"status: "+plugs[k].isStatus());
	}
	
	out("ROOM LEVEL OPTIONS");
	out("1- Switch all plugs off in room");
	out("2-  Switch all plugs on in room");
	out("3- Select a plug ID in the room and toggle its on/off status");
	int option=getInt("Select an option");
	int o=0;
	if(option==3) {
		o=getInt("Select Plug from above");
	}
	factory.updateRoomplugs(option,avaialbeRooms[roomID],o);
	}
public void case3(SmartHome factory) {
	for(SmartPlug object : factory.getSmartPlugs()){		
		
		out("SMartPlug |attached to: "+object.getName()+"            "+"|room: "+factory.getRooms()[factory.getRoom(object.getID())].getRoomName()+" |ID: "+object.getID()+"|"+"status: "+object.isStatus());		
	}	
		int option=getInt("Please select plug (integer only)");
		out("PLUG LEVEL OPTIONS");
		out("1 - Switch plug off");
		out("2 - Switch plug on");
		out("3 - Change attached device");
		out("4 - Move plug to different room");
		
		int op=getInt("Select an option (integer only)");
		if(op==1 || op==2) {
			factory.changePlug(option,op);
		}
		
		if(op==3) {


			out("AVAILABLE DEVICE LIST OPTIONS: ");
			out("These are standard devices that can be attached to the smart plug: ");
			
			
			for(int j = 0;j<factory.attachSize();j++){
				
				out(factory.getDevices()[j].getID()+" "+factory.getDevices()[j].getName());
			}
			
			
			int plugID=getInt("Enter device to attach to smart plug (integer only");
			factory.updateattach(option, plugID);
			
		}
		if(op==4) {
			out("ROOMS AVAIALBLE:  ");
			for(int i = 0;i<factory.roomsSize();i++){
				System.out.print(i+" - "+factory.getRooms()[i].getRoomName()+" | ");		
			}
			int roomID = getInt("Please select rooms(integer only)");
			factory.moveplug(option, roomID);
			
		}
		
}
public void case4(SmartHome factory) {
	out("SYSTEM LEVEL OPTIONS");
	out("1 -Add new smart Plug to room");
	out("2 -Add new attached device");
	out("3 -Add new room");
	
	int option=getInt("Please select option (integer only)");

	if(option==1) {
	
		
		out("ROOMS AVAIALBLE:  ");
		for(int i = 0;i<factory.roomsSize();i++){
			System.out.print(i+" - "+factory.getRooms()[i].getRoomName()+" | ");		
		}
		
		int rmID = getInt("Using the above list, please select the room for this plug (integer only)");
		

		out("AVAILABLE DEVICE LIST OPTIONS: ");
		out("These are standard devices that can be attached to the smart plug: ");
		
		
		for(int j = 0;j<factory.attachSize();j++){
			
			out(factory.getDevices()[j].getID()+" "+factory.getDevices()[j].getName());
		}
		
		int plugID=getInt("Using the above list, please select the device to attach to the smart plug (integer only)");
		factory.addSmartPlug(plugID,rmID);
		
	}
	if(option==2) {
		
		String name=getString("Enter name of Attach device");
		factory.addPlug(name);
		
		
	}
	
	if(option==3) {
		String name=getString("Enter name of new room");
		factory.addRooms(name);
	}
}

	public String getString(String prompt){
		Scanner in = new Scanner(System.in);
		String s;
		out(prompt);
		try {
			s = in.nextLine();
		} catch (Exception e){
			return getString(prompt);
		}
		return s;
	}
	public int getInt(String prompt){
		Scanner in = new Scanner(System.in);
		int i;
		out(prompt);
		try {
			i = in.nextInt();
		} catch (Exception e){
			
			return getInt(prompt);
		}
		return i;
	}
	
	public double getDouble(String prompt){
			Scanner in = new Scanner(System.in);
			double i;
			out(prompt);
			try {
				i = in.nextDouble();
			} catch (Exception e){
				out("Invalid input");
				return getDouble(prompt);
			}
			return i;
		}
	public boolean getBoolean(String prompt){
		Scanner in = new Scanner(System.in);
		int i;
		out(prompt);
		try {
			i = in.nextInt();
		} catch (Exception e){
			out("Invalid input");
			return getBoolean(prompt);
		}
		return convertInteger(i);
	}
	
	private boolean convertInteger(int value){
		if(value==0){ return true;}
			return false;
	}
	public void out(String message){ System.out.println(message);}
	public void out(int message){ System.out.println(message);}
	
	
	

}