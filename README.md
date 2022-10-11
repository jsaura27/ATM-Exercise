# ATM-Exercise
 
To run this project you will need to download it and build it. 
Once that is done you will need to go open the ATMExec class located in ATM-Exercise->src->main->java
There you will be able to run the project. 
This project has serialization, so it should be able to pick up the latest serialized object of ATM, but if for some reason it doesn't it will initialize all currencies at 10 of each.  

![image](https://user-images.githubusercontent.com/52546217/194766120-3f5d098b-d4fa-46e7-b2e7-87e500c973c4.png)

Once you are able to run it, you will face the first task of selecting a currency, go wild and choose any of the 9 available.

That will lead you to the main menu where you will see the balance of that currency as well as face 6 different options:

   1.Deposit money -> Allows you to deposit bills of the current currency, 20 and 50 only
   
   ![image](https://user-images.githubusercontent.com/52546217/195113479-ba91b119-39fd-492b-bd95-b06c4308e413.png)


   2.Withdraw money -> Allows you to retrieve money from the ATM, only combinations of 20 and 50 bills and inside the amount the atm has inside
   
   ![image](https://user-images.githubusercontent.com/52546217/195113709-9b777573-4664-4d9b-9681-18f6f34590c2.png)


   3.Change currency -> Change to any of the other 8 currencies
   
   ![image](https://user-images.githubusercontent.com/52546217/195113826-9b288c7e-a834-44e0-b489-42b9c056a109.png)


   4.Check balance -> shows the amount of each currency currently inside the atm, and of how many bills that amount is comprise of.
   
   ![image](https://user-images.githubusercontent.com/52546217/195113917-b4c4735d-042b-456c-9831-eb1ec85a905b.png)


   5.Receipt -> Prints a receipt of the amount of withdrawals made. The amount of bills and currency of those withdrawals.
   
   ![image](https://user-images.githubusercontent.com/52546217/195114095-497d7555-c10b-461d-b6c1-b3d184c2b994.png)

   6.Exit -> Stops the process.


Once you select any of the options 1 to 5 it will go back to the main menu after you are finished with that action, to allow you to proceed with another one. 
This will go until you click 6 to exit the application.

Inside the project there are also various Unit test to assert that most of the functionality of the project is working according to what is expected.
![image](https://user-images.githubusercontent.com/52546217/195114915-36cd6442-c99e-448d-b15f-cc9c827b65a0.png)

You can right click on the Java folder and run all the tests in the project, and see the coverage of the project, it should be exactly like this one:
![image](https://user-images.githubusercontent.com/52546217/195114510-68d23aa5-c1d0-405f-9971-c098e9f2d476.png)
![image](https://user-images.githubusercontent.com/52546217/195114754-cf3b5dc2-3b6c-4b4a-bc6f-a7a20819184d.png)

Personal notes:

 The main approach to this project was a 4 step approach:

   1.Design of the approach to the project, starting with a basic ATM.class, serialization and the main menu.
 
   2.Tackle the main methods: withdraw, deposit and currency change.
 
   3.Retweaking those methods, dividing the withdrawal method and adding the other options to the menu such as receipt and check balance.
 
   4.Cleaning and commenting the code, as well as creating some unit testing for extra safety. 
 
 Other feautures to improve the project:
   
    1.Rechecking the withdraw method, I believe it can be optimized, I have focused on delivering a finished product, but I am certain it can be improved.
  
    2.Integration testing, there is one Integration test, but I believe another one would help to feel safer.
  
    3.A simple GUI would make it a bit more easy to digest visually than just inputing things in the console and running on the IDE. 
  
