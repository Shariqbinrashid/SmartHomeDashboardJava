
public class Dashboard {

	
	
	//Dashboard classs main
	
	public static void main(String[] args) {
		ConsoleEvents console = new ConsoleEvents();
				
		int rmSize = console.getInt("How many rooms are there in this property?");
		int pgSize=console.getInt("How many plugs do you want to place in this property");
		
		SmartHome smth = new SmartHome(pgSize,rmSize);
		
		console.populateRoom(smth);
		
		console.preDefinePlugs(smth);
		
		console.populate(smth);				
		while(true) {	
		console.dashboard(smth);
		console.menuOptions(smth);
		console.pressEnter();
		}			
	
	}
}
