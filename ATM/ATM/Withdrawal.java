// Withdrawal.java
// Represents a withdrawal ATM transaction
import java.util.Scanner;

public class Withdrawal extends Transaction
{
    private int amount; // amount to withdraw
    private Keypad keypad; // reference to keypad
    private CashDispenser cashDispenser; // reference to cash dispenser

    // constant corresponding to menu option to cancel
    private final static int CANCELED = 0;

    private int cashQuantity; // withdrawal cash quantity

    // Withdrawal constructor
    public Withdrawal( int userAccountNumber, Screen atmScreen,
                       BankDatabase atmBankDatabase, Keypad atmKeypad,
                       CashDispenser atmCashDispenser )
    {
        // initialize superclass variables
        super( userAccountNumber, atmScreen, atmBankDatabase );

        // initialize references to keypad and cash dispenser
        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    } // end Withdrawal constructor

    // perform transaction
    public void execute()
    {
        boolean cashDispensed = false; // cash was not dispensed yet
        double availableBalance; // amount available for withdrawal

        // get references to bank database and screen
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        // loop until cash is dispensed or the user cancels
        do
        {
            // obtain a chosen withdrawal amount from the user
            keypad.enableButton();
            keypad.disableButton(12);
            keypad.disableButton(15);
            amount = displayMenuOfAmounts();

            // check whether user chose a withdrawal amount or canceled
            if ( amount != CANCELED )
            {
                screen.displayMessageLine("\nAre you sure you want to withdraw: $" + amount + "?");
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
                // get available balance of account involved
                availableBalance =
                        bankDatabase.getAvailableBalance( getAccountNumber() );

                if(confirm == 1)
                {
                // check whether the user has enough money in the account
                    if ( amount <= availableBalance )
                    {
                        // check whether the cash dispenser has enough money
                        if ( cashDispenser.isSufficientCashAvailable( amount ) )
                        {
                            // update the account involved to reflect withdrawal
                            bankDatabase.debit( getAccountNumber(), amount );

                            cashDispenser.dispenseCash( amount ); // dispense cash
                            cashDispensed = true; // cash was dispensed

                            screen.restTextBox();
                            // check whether the user withdraw HKD$100 in the account
                            if(cashQuantity * 100 == amount)
                            {
                                screen.displayMessageLine("\nYou get HK$100 x " + cashQuantity );
                                screen.displayMessageLine("Total amount: HKD$" + amount);
                            }

                            // check whether the user withdraw HKD$500 in the account
                            if(cashQuantity * 500 == amount)
                            {
                                screen.displayMessageLine("\nYou get HK$500 x " + cashQuantity );
                                screen.displayMessageLine("Total amount: HKD$" + amount);
                            }

                            // check whether the user withdraw HKD$1000 in the account
                            if(cashQuantity * 1000 == amount)
                            {
                                screen.displayMessageLine("\nYou get HK$1000 x " + cashQuantity );
                                screen.displayMessageLine("Total amount: HKD$" + amount);
                            }

                            // instruct user to take cash
                            screen.displayMessageLine(
                                    "\nDispensing money......Please wait." );
                            screen.displayMessageLine(" ");
                            screen.displayMessageLine(" ");
                            screen.displayMessageLine("Press enter to continue....");
                        } // end if
                        else{ // cash dispenser does not have enough cash
                            screen.restTextBox();
                            screen.displayMessageLine(
                                    "\nInsufficient cash available in the ATM." +
                                            "\n\nPlease choose a smaller amount." );
                        }
                    } // end if
                    else // not enough money available in user's account
                    {
                        screen.restTextBox();
                        screen.displayMessageLine(
                                "\nInsufficient funds in your account." +
                                        "\n\nPlease choose a smaller amount." );
                    } // end else
                }
                else if(confirm == 2)
                {
                screen.displayMessageLine( "\nCanceling transaction..." );
                screen.displayMessageLine("Press enter to continue....");
                return; // return to main menu because user canceled
                }
            } // end if
            else // user chose cancel menu option
            {
                screen.displayMessageLine( "\nCanceling transaction..." );
                return; // return to main menu because user canceled
            } // end else
        } while ( !cashDispensed );

    } // end method execute

    // display a menu of withdrawal amounts and the option to cancel;
    // return the chosen amount or 0 if the user chooses to cancel
    private int displayMenuOfAmounts()
    {
        int userChoice = -1; // local variable to store return value


        BankDatabase bankDatabase = getBankDatabase();
        bankDatabase.getAvailableBalance( getAccountNumber() );

        Screen screen = getScreen(); // get screen reference

        // array of amounts to correspond to menu numbers
        int amounts[] = { 0, 100, 500, 1000 };

        // loop while no valid choice has been made
        while ( userChoice == -1 )
        {
            // display the menu
            screen.displayMessageLine( "\nWithdrawal Menu:" );
            screen.displayMessageLine( "1 - $100 x number of $100" );
            screen.displayMessageLine( "2 - $500 x number of $500" );
            screen.displayMessageLine( "3 - $1000 x number of $1000" );
            screen.displayMessageLine( "0 - Cancel transaction" );
            screen.displayMessage( "\nType your withdraw amount or choose a withdraw amount(1-3): " );

            keypad.userInput();
            screen.displayMessage(keypad.getstr_input());
            int input = keypad.getInput(); // get user input through keypad

            // determine how to proceed based on the input value
            switch ( input )
            {

                case 1: // if the user chose a withdrawal amount
                case 2: // (i.e., chose option 1, 2, 3), return the
                case 3: // corresponding amount from amounts array
                    keypad.disableButton(12);
                    keypad.disableButton(15);
                    userChoice = amounts[ input ]; // save user's choice
                    screen.displayMessage("\nHow many HKD$ " + userChoice + " do you want to withdraw? ");
                    keypad.userInput();
                    screen.displayMessage(keypad.getstr_input());
                    input = keypad.getInput(); // get user input through keypad
                    cashQuantity = input; // update withdrawal cash quantity
                    userChoice *= cashQuantity; // update withdrawal amount
                    break;
                case CANCELED: // the user chose to cancel
                    userChoice = CANCELED; // save user's choice
                    break;
                default: // the user did not enter a value from 1-4
                    if (input <= bankDatabase.getAvailableBalance( getAccountNumber() ) && input%100==0)
                    {
                        userChoice=input;
                    }
                    else if (input > bankDatabase.getAvailableBalance( getAccountNumber() ))
                    {
                        screen.restTextBox();
                        screen.displayMessageLine(
                                "\nNot enough balance !" );
                        continue;
                    }
                    else
                    {
                        screen.restTextBox();
                        screen.displayMessageLine(
                                "\n Please input the amount of multiple of 100. Try again." );
                        continue;
                    }
            } // end switch
        } // end while

        return userChoice; // return withdrawal amount or CANCELED
    } // end method displayMenuOfAmounts
} // end class Withdrawal
