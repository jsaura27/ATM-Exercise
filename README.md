# ATM-Exercise
 
To run this project you will need to download it and build it. 
Once that is done you will need to go open the ATMExec class located in ATM-Exercise->src->main->java
There you will be able to run the project. 
This project has serialization, so it should be able to pick up the latest serialized object of ATM, but if for some reason you run into a problem where in the command 
it shows that the bills are null, in line 15 of ATMExec.java you will find a commented line, uncomment it and run it again, that will initialize the object with
bills for every currency available. You can proceed to comment it again after the first run since the serialization works. 
![image](https://user-images.githubusercontent.com/52546217/194766120-3f5d098b-d4fa-46e7-b2e7-87e500c973c4.png)

Once you are able to run it, you will face the first task of selecting a currency, go wild and choose any of the 9 available. 
That will lead you to the main menu where you will face 6 different options:
1.Deposit money -> Allows you to deposit bills of the current currency, 20 and 50 only
2.Withdraw money -> Allows you to retrieve money from the ATM, only combinations of 20 and 50 bills and inside the amount the atm has inside
3.Change currency -> Change to any of the other 8 currencies
4.Check balance -> shows the amount of each currency currently inside the atm, and of how many bills that amount is comprise of.
5.Receipt -> Prints a receipt of the amount of withdrawals made. The amount of bills and currency of those withdrawals.
6.Exit -> Well, it stops the process.

Once you select any of the options 1 to 5 it will go back to the main menu after you are finished with that action, to allow you to proceed with another one. 
This will go until you click 6 to exit the application.

