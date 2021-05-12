# Introduction
## Case Study

Alikoti, A UK-Finnish company, Required a smart Home console base dashboard which can have 
monitoring and controlling system for smart plugs in each room. Following constraints were pre-defined 
by company:
• The project must separate the responsibilities of backend objects and frontend objects.
• You can have zero, one or many smart plugs in a single room
• The application interface must be in the form of a Java console
• Less complexity
• Feasibility of further development.
• Easy Scaling up



# Implementation
To build a separate front end and back end implementation , a client server split architecture is 
used in building this Application. A complete separate split is done for back end implementation and 
frontend. Each module of application is developed keeping in view of company constraints.
For general overview:

#### Front end classes:

![Class Diagram](./img/fcd.JPG)

#### Back end classes:

![Class Diagram](./img/bcd.JPG)


# Dash Board Screen shot
![Dashboard](./img/1.JPG)

# Menu Options

## House Level Options

For the house level actions there are two actions. All the
plugs can either be switched on or off. 
For example:
HOUSE LEVEL OPTIONS

>>1 - Switch all plugs off
2 - Switch all plugs on
Select an option
 2
 
After the specific house level option has been selected the
console  return to the main dashboard with updated
data from the backend objects displayed.

## Room level Options

For the room level options select a room. With a selected room you are given 3 actions for the
plugs in that room. 
For example:
>>ROOMS AVAILABLE: 0 - study 1 | 1 - living room | 2 -
kitchen | 3 - dining room |
Please select room (integer only)
2
SmartPlug |attached to: recharger |room: kitchen|ID:
2|status: on|
SmartPlug |attached to: heater |room: kitchen|ID:
3|status: on|
ROOM LEVEL OPTIONS
1 - Switch all plugs off in room
2 - Switch all plugs on in room
3 - Select a plug in the room and toggle its on/off status
Select an option
 1

After the room level option has been selected the console
should return to the main dashboard with updated data
displayed.


## Smart Plug Level Options
For the plug level options you have given a room
independent listing of the plugs. From this list you canshould be
select a smart plug from its ID. 
For example:
>>SmartPlug |attached to: lamp |room: study 1|ID: 0|status:
on
SmartPlug |attached to: TV |room: living room|ID:
1|status: on
SmartPlug |attached to: recharger |room: kitchen|ID:
2|status: off
SmartPlug |attached to: heater |room: kitchen|ID:
3|status: off
SmartPlug |attached to: computer|room: dining room|ID:
4|status: on
Please select plug (integer only)
1
PLUG LEVEL OPTIONS
1 - Switch plug off
2 - Switch plug on
3 - Change attached device
4 - Move plug to different room
Select an option
3

For the change attached device action you can see
listing of the available devices to allow the user to select a
different attached device. 
For example:
>>AVAILABLE DEVICE LIST OPTIONS
These are standard devices attached to the smart plug,
unless otherwise stated
1-Lamp
2-TV
3-Computer
4-Phone Recharger
5-Heater
Enter device to attach to smart plug (integer only)
1

For the move plug to different room action you have given
a listing of available rooms to allow the user to select a
different room for the plug. 
For example:
>>ROOMS AVAILABLE: 0 - study 1 | 1 - living room | 2 -
kitchen | 3 - dining room |
Please select room for device from list (integer only)

## System level Options

The system options allow (a) more smart plugs to be added,
(b) more attached devices to be listed and (b) more rooms to
be added.

## Logic
### Front-end Logic:
Dashboard is basically a class which contains main of Application which allow us to change values of objects in SmartHome.

ConsoleEvent class act as a mediate between front end and backend , as its responsibility is getting input from user and giving 
output to a user. It sends data to SmartHome class which returns data after processing and used to display results.
### Backend Logic:

#### For SmartHome class:
SmartHome class act as a main class for back end which have array of smart plugs and rooms. It displays 
and update data from smart plug and room class.
#### For Room class:
I have used Room class which have Array of Smart plugs to give a relation between rooms and plugs, as 
rooms can have 1 or more smart plugs and smart plug can be in more than one room.
Once room is selected by user for particular plug , its object is store in list in room,For Smart Plug Class:
Now main Logic of attach devices is built in this class. I have Used two unique IDs to differentiate 
between Pre define Plugs and User define plugs. Pre define plugs are so called Attached devised , when
attach device is added Its store UserPlugID as -1, and for User define plugs for each room I store ID as -2 
for that plug.
so whenever I need only attached devices I just iterate over array and ignore that SmartPlugs object 
which have UserPlugID equal to -1.And for User define Plugs for each room, ignore Plugs which have ID -2.
For updating data, I just match respective ID in array of Smart Plugs in SmartHome, and update 
whatever us required.

# Conclusion:
Keeping in view of company constraints, This Application can be scale up or can be modify as per their new needs. Each constraint or requirement is followed to build this Application so that it can be scale up to any need of company. For implementation, Client server split architecture can be helpful for any error tracing in future