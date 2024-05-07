// Screen.java
// Represents the screen of the ATM
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

public class Screen extends JFrame
{
    private JTextArea textbox;
    private String text = "";

    public Screen(JPanel Keypanel){
        super("ATM");

        // initialize GUI components
        textbox = new JTextArea();
        textbox.setBackground(Color.BLUE);
        textbox.setForeground(Color.YELLOW);

        // initialize Frame
        setSize(500,550);
        setVisible(true);
        setLayout(new BorderLayout());
        setResizable(false);

        // add components into Frame
        add(Keypanel, BorderLayout.SOUTH);
        add(textbox, BorderLayout.CENTER);
    }

    // displays a message without a carriage return
    public void displayMessage( String message )
    {
        text = text + message;
        showText();
    } // end method displayMessage

    // display a message with a carriage return
    public void displayMessageLine( String message )
    {
        text =  text + message + "\n";
        showText();
    } // end method displayMessageLine

    // display a dollar amount
    public void displayDollarAmount( double amount )
    {
        text = text + String.format("HK$%,.2f", amount);
        showText();
    } // end method displayDollarAmount

    // clear all the text on TextArea
    public void restTextBox(){
        text = "";
        showText();
    }// end method restTextBox

    // show the text on TextArea
    public void showText(){
        textbox.setText(text);
    }// end method showText

} // end class Screen
