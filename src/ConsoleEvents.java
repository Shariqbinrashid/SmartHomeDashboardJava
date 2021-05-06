import java.util.Scanner;
import java.util.ArrayList;

//Console Helper class
public class ConsoleEvents {
	
	public String getString(String prompt){
		Scanner in = new Scanner(System.in);
		String s;
		out(prompt);
		try {
			s = in.next();
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
		
		
		System.out.println("ENTER PLUG INFORMATION BELOW: ");
		
		
		SmartPlug[] availablePlugs=smth.getSmartPlugs();
		int roomID=0;
		int plugID=0;
		int v=0;
		//looping till user define plugs for Home
		for(int k = 0;k<smth.getUserPlugs();k++){
			
			v=k+1;
			System.out.println("Plug no.   "+v);
			
			smth.getAvailableRooms();
			try {
			roomID = getInt("Using the above list, please select the room for this plug (integer only)");
			
			System.out.println("AVAILABLE DEVICE LIST OPTIONS: ");
			System.out.println("These are standard devices that can be attached to the smart plug: ");
			
			
			smth.getAvaialablePlugs();
			
			plugID=getInt("Using the above list, please select the device to attach to the smart plug (integer only)");
			
			//Adding plug to room list
			smth.getRooms()[roomID].addtoPluglist(availablePlugs[plugID]);
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
			ArrayList<SmartPlug> plugs=roomslist[i].getPluglist();	
			for(int k=0;k<plugs.size();k++) {
				out("SMartPlug |attached to: "+plugs.get(k).getName()+"            "+"|room: "+roomslist[i].getRoomName()+" |ID: "+plugs.get(k).getID()+"|"+"status: "+plugs.get(k).isStatus());
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
				smth.getAvailableRooms();
				
				int roomID = getInt("Please select rooms(integer only)");
				ArrayList<SmartPlug> plugs=avaialbeRooms[roomID].getPluglist();

				for(int k=0;k<plugs.size();k++) {
					out("SMartPlug |attached to: "+plugs.get(k).getName()+"            "+"|room: "+roomslist[roomID].getRoomName()+" |ID: "+plugs.get(k).getID()+"|"+"status: "+plugs.get(k).isStatus());
				}
				
				out("ROOM LEVEL OPTIONS");
				out("1- Switch all plugs off in room");
				out("2-  Switch all plugs on in room");
				out("3- Select a plug ID in the room and toggle its on/off status");
				option=getInt("Select an option");
				smth.updateRoomplugs(option,avaialbeRooms[roomID]);		
			break;
			case 3:
				smth.viewAllPlugs();
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

					System.out.println("AVAILABLE DEVICE LIST OPTIONS: ");
					System.out.println("These are standard devices that can be attached to the smart plug: ");
					
					
					smth.getAvaialablePlugs();
					
					
					int plugID=getInt("Enter device to attach to smart plug (integer only");
					smth.updateattach(option, plugID);
					
				}
				if(op==4) {
					smth.getAvailableRooms();
					roomID = getInt("Please select rooms(integer only)");
					smth.moveplug(option, roomID);
					
				}
				
				
				break;
			case 4:
				out("SYSTEM LEVEL OPTIONS");
				out("1 - add plug");
				out("2 - add attached devices");
				out("3 - add rooms");
				
				option=getInt("Please select option (integer only)");

				if(option==1) {
					smth.addSmartPlug();
				}
				if(option==2) {
					smth.addPlug();
				}
				
				if(option==3) {
					smth.addRooms();
				}
				break;
			default:
			out("please enter a valid option");
		}
	}
	
	public void out(String message){ System.out.println(message);}
	public void out(int message){ System.out.println(message);}

}