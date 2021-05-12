
public class Dashboard {

	
	
	//Dashboard classs main
	
	public static void main(String[] args) {
		ConsoleEvents console = new ConsoleEvents();
				
		int rSize = console.getInt("How many rooms are there in this property?");
		int pSize=console.getInt("How many plugs do you want to place in this property");
		
		SmartHome factory = new SmartHome(pSize,rSize);
		
		console.populateRoom(factory);
		
		
		
		console.populate(factory);				
		while(true) {	
		console.dashboard(factory);
		
		console.menuOptions(factory);
		}			
	
	}
}
