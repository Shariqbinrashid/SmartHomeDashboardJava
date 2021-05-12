import java.util.Scanner;


//Console Helper class
public class ConsoleEvents {
	
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
	
	public void populateRoom(SmartHome smth){
		for(int i = 0;i<smth.roomsSize();i++){
			String name = getString("Please provide a name for your "+i+" room ");
			smth.addRoom(name);
		}
	}
	
	
	public void populate(SmartHome smth){
		
		
		out("ENTER PLUG INFORMATION BELOW: ");
		
		
		SmartPlug[] availablePlugs=smth.getSmartPlugs();
		int roomID=0;
		int plugID=0;
		int v=0;
		//looping till user define plugs for Home
		for(int k = 0;k<smth.getUserPlugs();k++){
			
			v=k+1;
			out("Plug no.   "+v);
			
			
			out("ROOMS AVAIALBLE:  ");
			for(int i = 0;i<smth.roomsSize();i++){
				System.out.print(i+" - "+smth.getRooms()[i].getRoomName()+" | ");		
			}
			
			try {
			roomID = getInt("Using the above list, please select the room for this plug (integer only)");
			
			out("AVAILABLE DEVICE LIST OPTIONS: ");
			out("These are standard devices that can be attached to the smart plug: ");
			
			
			for(int j = 0;j<smth.plugsSize();j++){
				if(smth.getSmartPlugs()[j].getUserPlugID()==-1)
				out(smth.getSmartPlugs()[j].getID()+" "+smth.getSmartPlugs()[j].getName());
			}
			
			plugID=getInt("Using the above list, please select the device to attach to the smart plug (integer only)");
			
			smth.DefinePlugs(availablePlugs[plugID].getName(),smth.getRooms()[roomID]);
			
			}
			catch(Exception e) {
				out("Invalid input/Exception");
			}		
		}
		
	}
	
	

	
	public void preDefinePlugs(SmartHome smth){
		String[] initialPlugs= {"Computer","Phone Charger","Heater","Lamp","TV"};
		smth.preDefine(initialPlugs);
	}
	
	
	public void pressEnter(){
		Scanner in = new Scanner(System.in);
		out("Press enter to continue");
		in.nextLine();
	}
	
	
	public void dashboard(SmartHome smth) {
				out("---------------DASHBOARD--------------");
		
		Room[] roomslist=smth.getRooms();
		for(int i=0;i<smth.roomsSize();i++) {		
			out("Room: "+i);
			SmartPlug[] plugs=roomslist[i].getPluglist();	
			for(int k=0;k<plugs.length;k++) {
				if(plugs[k].getID()==-2)
				out("SMartPlug |attached to: "+plugs[k].getName()+"            "+"|room: "+roomslist[i].getRoomName()+" |ID: "+plugs[k].getUserPlugID()+"|"+"status: "+plugs[k].isStatus());
			}				
			
		}
	}
	
	public void menuOptions(SmartHome smth) {
		Room[] roomslist=smth.getRooms();
		
		out("-------------MENU OPTIONS-------------");
		out("-------------please select option:----");
		out("1 - house level options");
		out("2 - room level options");
		out("3 - plug level options");
		out("4 - system options");
		int optionLevel = getInt("");
		switch (optionLevel) {
			case 1:
				out("House Level Options ");
				out("1- Switch all plugs off");
				out("2- Switch all plugs on");
				out("Select an option");
				int option=getInt("");
				if(option==1)
					smth.updatePlugs(false);
				else if(option==2)
					smth.updatePlugs(true);
			break;
			case 2:
				Room[] avaialbeRooms=smth.getRooms();		
				
				out("ROOMS AVAIALBLE:  ");
				for(int i = 0;i<smth.roomsSize();i++){
					System.out.print(i+" - "+smth.getRooms()[i].getRoomName()+" | ");		
				}
				
				int roomID = getInt("Please select rooms(integer only)");
				SmartPlug[] plugs=avaialbeRooms[roomID].getPluglist();
				
				for(int k=0;k<plugs.length;k++) {
					
					if(plugs[k].getID()==-2)
					out("SMartPlug |attached to: "+plugs[k].getName()+"            "+"|room: "+roomslist[roomID].getRoomName()+" |ID: "+plugs[k].getUserPlugID()+"|"+"status: "+plugs[k].isStatus());
				}
				
				out("ROOM LEVEL OPTIONS");
				out("1- Switch all plugs off in room");
				out("2-  Switch all plugs on in room");
				out("3- Select a plug ID in the room and toggle its on/off status");
				option=getInt("Select an option");
				int o=0;
				if(option==3) {
					o=getInt("Select Plug from above");
				}
				smth.updateRoomplugs(option,avaialbeRooms[roomID],o);		
				
			break;
			case 3:
				for(SmartPlug object : smth.getSmartPlugs()){		
					if(object.getID()==-2)
				out("SMartPlug |attached to: "+object.getName()+"            "+"|room: "+smth.getRooms()[smth.getRoom(object.getUserPlugID())].getRoomName()+" |ID: "+object.getUserPlugID()+"|"+"status: "+object.isStatus());		
			}	
				option=getInt("Please select plug (integer only)");
				out("PLUG LEVEL OPTIONS");
				out("1 - Switch plug off");
				out("2 - Switch plug on");
				out("3 - Change attached device");
				out("4 - Move plug to different room");
				
				int op=getInt("Select an option (integer only)");
				if(op==1 || op==2) {
					smth.changePlug(option,op);
				}
				
				if(op==3) {

					out("AVAILABLE DEVICE LIST OPTIONS: ");
					out("These are standard devices that can be attached to the smart plug: ");
					
					
					for(int j = 0;j<smth.plugsSize();j++){
						if(smth.getSmartPlugs()[j].getUserPlugID()==-1)
						out(smth.getSmartPlugs()[j].getID()+" "+smth.getSmartPlugs()[j].getName());
					}
					
					
					int plugID=getInt("Enter device to attach to smart plug (integer only");
					smth.updateattach(option, plugID);
					
				}
				if(op==4) {
					out("ROOMS AVAIALBLE:  ");
					for(int i = 0;i<smth.roomsSize();i++){
						System.out.print(i+" - "+smth.getRooms()[i].getRoomName()+" | ");		
					}
					roomID = getInt("Please select rooms(integer only)");
					smth.moveplug(option, roomID);
					
				}
				
				
				break;
			case 4:
				out("SYSTEM LEVEL OPTIONS");
				out("1 - add new plug to room");
				out("2 - add attached devices");
				out("3 - add rooms");
				
				option=getInt("Please select option (integer only)");

				if(option==1) {
				
					
					out("ROOMS AVAIALBLE:  ");
					for(int i = 0;i<smth.roomsSize();i++){
						System.out.print(i+" - "+smth.getRooms()[i].getRoomName()+" | ");		
					}
					
					int rmID = getInt("Using the above list, please select the room for this plug (integer only)");
					
					out("AVAILABLE DEVICE LIST OPTIONS: ");
					out("These are standard devices that can be attached to the smart plug: ");
					
					for(int j = 0;j<smth.plugsSize();j++){
						if(smth.getSmartPlugs()[j].getUserPlugID()==-1)
						out(smth.getSmartPlugs()[j].getID()+" "+smth.getSmartPlugs()[j].getName());
					}
					int plugID=getInt("Using the above list, please select the device to attach to the smart plug (integer only)");
					smth.addSmartPlug(plugID,rmID);
					
				}
				if(option==2) {
					
					String name=getString("Enter name of attach device you want to add");
					smth.addPlug(name);
					
					
				}
				
				if(option==3) {
					String name=getString("Enter name of room");
					smth.addRooms(name);
				}
				break;
			default:
			out("please enter a valid option");
		}
	}
	
	public void out(String message){ System.out.println(message);}
	public void out(int message){ System.out.println(message);}

}