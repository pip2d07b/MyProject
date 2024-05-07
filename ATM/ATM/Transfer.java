public class Transfer extends Transaction
{
    private Keypad keypad;
    private double amount_transfer;
    private int transfer_account;
    private final static int CANCELED = 0;

    //Create constructor
    public Transfer(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase,Keypad atmKeypad )
    {
        //Obtain and initialize variable from superclass(Transaction)
        super( userAccountNumber, atmScreen, atmBankDatabase );
        keypad = atmKeypad; //Get reference to keypad
    }

    @Override
    public void execute()
    {
        double available_balance;// declare variable "available_balance"
        BankDatabase bankDatabase = getBankDatabase(); //Get reference to bank database
        Screen screen = getScreen(); //Get reference to screen

        //Ask user to input the payee account number by message display
        screen.displayMessageLine("Please input the payee account number:  (Input cancel for return to main menu.)");

        do
        {
            //Input the account number that the user want to transfer to
            keypad.userInput();
            screen.displayMessage(keypad.getstr_input());
            transfer_account = keypad.getInput();

            //If customer enter string, it return to the main menu
            if( transfer_account == CANCELED)
            {
            screen.restTextBox();
            screen.displayMessageLine("You have exit the transfer function. Please press Enter to continue......");
            return;
            }

            //If the account number is the same as the current account number, an error message pops out and ask user to enter again
            if(super.getAccountNumber() == transfer_account){
                screen.displayMessageLine("\nSorry! You cannot transfer money to your own personal account!");
                screen.displayMessageLine("Please enter another account number: ");
                continue;
            }

            //Customer enter the correct payee account number, then the system will ask the amount that user want to transfer
            if(bankDatabase.account_matching(transfer_account) != -1)
            {
                screen.displayMessageLine("\nAccount found!");
                screen.displayMessageLine("How much do you want to transfer to the account($HKD)?");
                screen.displayMessage("\nYour current available balance: ");
                screen.displayMessage(Double.toString(bankDatabase.getAvailableBalance(super.getAccountNumber())));
                screen.displayMessageLine("(Input cancel for return to main menu.)");
                do{
                keypad.enableButton();
                keypad.userInput();
                screen.restTextBox();

                if(keypad.getstr_input().contains("."))
                {
                    amount_transfer = keypad.getDouble();//assign user input of payee account number to amount_transfer
                }
                else
                    amount_transfer = keypad.getInput();


                if(amount_transfer != CANCELED)//If the user input of payee account number is not 0,
                {
                    screen.displayMessageLine("Are you sure you want to transfer: $" + keypad.getstr_input() + "?" );
                    screen.displayMessageLine("\n  1 - confirm         2 - cancel");//confirmation

                    keypad.disableButton(2);


                    for(int i = 2; i <= 6; i++)
                     {
                         keypad.disableButton(i);
                     }

                    for(int i = 8; i<= 10; i++)
                     {
                         keypad.disableButton(i);
                     }

                    for(int i = 12; i<= 15; i++)
                     {
                         keypad.disableButton(i);
                     }

                   keypad.userInput();
                   int confirm = keypad.getInput();




                    available_balance = bankDatabase.getAvailableBalance(super.getAccountNumber());//assign the available balance of user's
                                                                          //account to available_balance

                    if(confirm == 1)
                    {

                        //Show user available balance

                        if(amount_transfer <= available_balance)//if user has enough amount of available_balance to transfer
                        {
                                screen.displayMessageLine("\nTransaction processing........");
                                bankDatabase.debit(super.getAccountNumber(), amount_transfer );
                                bankDatabase.credit(transfer_account,amount_transfer);
                                screen.displayMessageLine("Transaction successful!");
                                screen.displayMessageLine("Please press Enter to continue......");
                                keypad.enterFlag(false);
                                keypad.enterFlag();
                                if(keypad.enterFlagReturn() == true)
                                    amount_transfer = 0;
                        }

                        else
                        {
                            keypad.enterFlag(false);
                            screen.restTextBox();
                            screen.displayMessageLine("Sorry! Your available balance is insufficient.");
                            screen.displayMessageLine("Please enter the amount again or enter 0/cancel to return to main menu.");
                            continue;
                            //User dont have enough money to transfer
                        }
                    }
                    else if(confirm == 2)
                    {
                        screen.displayMessageLine("You have exit the transfer function. Please press Enter to continue......");
                        return;
                    }
                }
                else
                {
                    screen.displayMessageLine("You have exit the transfer function. Please press Enter to continue......");
                    for(int i = 0; i < 3; i++)
                     {
                         keypad.disableButton(i);
                     }

                    for(int i = 4; i <= 6; i++)
                     {
                         keypad.disableButton(i);
                     }

                    for(int i = 8; i<= 10; i++)
                     {
                         keypad.disableButton(i);
                     }

                    for(int i = 12; i<= 15; i++)
                     {
                         keypad.disableButton(i);
                     }
                    return; //Return back to main menu
                }
                }while((amount_transfer > bankDatabase.getAvailableBalance(super.getAccountNumber())));

            }
            else //if the payee account cannot be found, ask user to re-enter again
            {
                screen.displayMessageLine("\nSorry! Could not found this account, please re-enter. (Input 0 for return to main menu.)");
                continue;
            }

        }while((super.getAccountNumber() == transfer_account) || (bankDatabase.account_matching(transfer_account) == -1));

    }
}
