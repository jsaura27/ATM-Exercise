# ATM-Exercise
 
To run this project you will need to download it and build it. 
Once that is done you will need to go open the ATMExec class located in ATM-Exercise->src->main->java
There you will be able to run the project. 
This project has serialization, so it should be able to pick up the latest serialized object of ATM, but if for some reason you run into a problem where in the command 
it shows that the bills are null, in line 15 of ATMExec.java you will find a commented line, uncomment it and run it again, that will initialize the object with
bills for every currency available. You can proceed to comment it again after the first run since the serialization works. 
![image](https://user-images.githubusercontent.com/52546217/194766120-3f5d098b-d4fa-46e7-b2e7-87e500c973c4.png)

Once you are able to run it, you will face the first task of selecting a currency, go wild and choose any of the 9 available. 
That will lead you to the main menu where you will see the balance of that currency as well as face 6 different options:
![image](https://user-images.githubusercontent.com/52546217/194766196-9f811542-3604-4643-9dbc-c25aafcadb27.png)

1.Deposit money -> Allows you to deposit bills of the current currency, 20 and 50 only

2.Withdraw money -> Allows you to retrieve money from the ATM, only combinations of 20 and 50 bills and inside the amount the atm has inside

3.Change currency -> Change to any of the other 8 currencies

4.Check balance -> shows the amount of each currency currently inside the atm, and of how many bills that amount is comprise of.

5.Receipt -> Prints a receipt of the amount of withdrawals made. The amount of bills and currency of those withdrawals.

6.Exit -> Stops the process.

Once you select any of the options 1 to 5 it will go back to the main menu after you are finished with that action, to allow you to proceed with another one. 
This will go until you click 6 to exit the application.

Inside the project there are also various Unit test to assert that most of the functionality of the project is working according to what is expected.
![image](https://user-images.githubusercontent.com/52546217/194766936-e27936f0-a0d5-419e-9b35-c1ddd559acea.png)


Personal notes:

The main approach to this project was a 4 step approach. It is going to be a bit cartoonis but it is a pretty accurate depiction of my thoughts:
 1.It looks pretty straight forward, a class where I store the bills, and calculate...wait, how do I store the data? OK, time to google how serialization works.
 
 2.I have serialization ready, let's leave the hashmap and currency problems to Saturday me, now let's finish the withdraw and deposit money in the atm.
 
 3.Aaaaand now I have to change all the variables cos of the hashmap with the currencies dammit. Let's add a few other things like receipt, balance to look good, sounds like a plan!
 
 4.Ok, adding more changes to withdraw money again cos I forgot the leaving the options open and some unit tests so I can test all the scenarios in 1 run.
 
 Other feautures:
 There are some feautures I would like to add:
  1.Better unit test for DataManagementTest.class, they were the first one made and they are not my best work.
  
  2.Finish all the remaining unit test, I believe there are a few unit test I have not finished yet for the ATMCommandsTest.class
  
  3.Rechecking the withdraw method, I believe it can be optimized, I have focused on delivering a finished product, but I am certain it can be improved.
  
  4.Integration testing, due to the nature of all the console output as well as keyboard scanner interaction an integration test would have taken me quite a while, so it remains in the todo list.
  
  5.A simple GUI would make it a bit more easy to digest visually than just inputing things in the console and running on the IDE. 
  
