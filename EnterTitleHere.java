import java.util.Scanner; //allows user to input
import javax.swing.*;  //includes methods for GUI (more sophisticated set of GUI components than the earlier awt)
import java.awt.*;       
import java.awt.event.*;
import java.io.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.Color;            
import javax.swing.Timer;          
import java.text.SimpleDateFormat;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;      
import java.util.Iterator;      
import java.util.Random;         
import java.util.Collections;
import javax.swing.ImageIcon;

public class FinalProject extends JFrame implements ActionListener{   
  // Panel 0 variables --------------------------------------------- Start
  
  public static char[][]board = new char [5][5];
  
  public static String[] dice = new String[] {       //creates letters on "dice" to appear on game board
    "AAAFRS", "AAEEEE", "AAFIRS", "ADENNN", "AEEEEM",
      "AEEGMU", "AEGMNN", "AFIRSY", "BJKQXZ", "CCENST",
      "CEIILT", "CEILPT", "CEIPST", "DDHNOT", "DHHLOR",
      "DHLNOR", "DHLNOR", "EIIITT", "EMOTTT", "ENSSSU",
      "FIPRSY", "GORRVW", "IPRRRY", "NOOTUW", "OOOTTU",
  };                                 
  
  public Random random;
  
  // Creates buttons 1-25 an a enter button
  JButton button1 = new JButton ("");
  JButton button2 = new JButton ("");
  JButton button3 = new JButton ("");
  JButton button4 = new JButton ("");
  JButton button5 = new JButton ("");
  JButton button6 = new JButton ("");
  JButton button7 = new JButton ("");
  JButton button8 = new JButton ("");
  JButton button9 = new JButton ("");
  JButton button10 = new JButton ("");
  JButton button11 = new JButton ("");
  JButton button12 = new JButton ("");
  JButton button13 = new JButton ("");
  JButton button14 = new JButton ("");
  JButton button15 = new JButton ("");
  JButton button16 = new JButton ("");
  
  public static ArrayList<JButton> currentPosition = new ArrayList<JButton>();   
  // Panel 0 variables ----------------------------------------------- End
  
  // Panel 1 variables --------------------------------------------- Start
  
  public static String word = "";
  
  // Creates two Array list named dictionary and usedWords
  public static ArrayList<String> dictionary = new ArrayList<String>();
  public static ArrayList<String> usedWords = new ArrayList<String>();
  
  // Creates a wordlabel and wordField
  public static JTextField wordField = new JTextField("", 15);
  JButton enterButton = new JButton ("Enter");
  
  // Panel 1 variables ----------------------------------------------- End
  
  // Panel 2 variables --------------------------------------------- Start
  
  // Creates a text area and a scroll pane
  public static JTextArea textArea = new JTextArea(515, 5);
  public static JScrollPane scrollPane = new JScrollPane(textArea);
  
  // Panel 2 variables ----------------------------------------------- End
  
  // Panel 3 variables --------------------------------------------- Start
  
  // Sets timer as public variable and creates a start button
  public Timer timer;
  JButton startTimer = new JButton("Start"); 
  
  // Panel 3 variables ----------------------------------------------- End
  
  // Panel 4 variables --------------------------------------------- Start
  
  // Creates a scorelabel
  public static int score = 0;
  String phrase = Integer.toString(score);
  JLabel scorelabel = new JLabel(phrase, JLabel.CENTER);
  
  // Panel 4 variables ----------------------------------------------- End
  
  // Panel 5 variables --------------------------------------------- Start
  
  // Creates 4 buttons
  JButton gameButton = new JButton ("Start");
  JButton rulesButton = new JButton("Rules");
  
  // Creates a label to add the rules to
  public static JLabel rulesLabel = new JLabel("", JLabel.CENTER);
  
  // Creates a title and logo for the menu as well as a JLabel
  ImageIcon imageIcon = new ImageIcon("data/Title.png");
  ImageIcon imageIcon2 = new ImageIcon("data/logo.png");
  JLabel title2 = new JLabel(imageIcon);
  JLabel logo = new JLabel(imageIcon2);
  
  // Panel 5 variables ----------------------------------------------- End
  
  // Frame variables ----------------------------------------------- Start
  
  // Creates a border
  TitledBorder title;
  Border blackline;
  
  // Creates a button to go back to the menu from the "Rules" page
  JButton backButton = new JButton ("Menu");
  
//  ImageIcon imageIcon3 = new ImageIcon("logo2.png");
  
  // Frame variables ----------------------------------------------- Start
  
  // Creates panel 0-4 and the frame. Inside includes the board and functions for the game such as scoring
  JFrame frame = new JFrame();
  JPanel boggleBoard = new JPanel();
  JPanel wordInput = new JPanel();
  JPanel displayArea = new JPanel();
  JPanel clock = new JPanel();
  JPanel scoring = new JPanel();
  JPanel menu = new JPanel();
  
  public FinalProject(){ 
//    Frame ------------------------------------------------------------------------------------------------------ Start
    
    // Sets the name and sizd of the frame
    frame.setTitle("[Enter Title Here]");
    frame.setSize (800, 650);
    
    // Set the layout of the frame
    frame.setIconImage(imageIcon2.getImage());
    frame.setLayout(new BorderLayout());
    
    // Adds panels and buttons to the main game frame. Add things such as boggleBoard and clock.
    frame.add(menu, BorderLayout.CENTER);
    frame.add(boggleBoard);
    frame.add(wordInput);
    frame.add(displayArea);
    frame.add(clock);
    frame.add(scoring);
    frame.add(rulesLabel);
    frame.add(backButton, BorderLayout.PAGE_END); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    
    // Adds a listener to the back button from the rules panel
    backButton.addActionListener(this);
    
    // Changes the visible of different panels and button. Makes sure board and game functions are initially not seen when first running the game
    frame.setVisible(true);
    boggleBoard.setVisible(false);
    wordInput.setVisible(false);
    displayArea.setVisible(false);
    clock.setVisible(false);
    scoring.setVisible(false);
    rulesLabel.setVisible(false);
    backButton.setVisible(false);
    
//    Frame -------------------------------------------------------------------------------------------------------- End   
    
//    Letter randomize ------------------------------------------------------------------------------------------- Start
    
    random = new Random(); // seed will be different every time
    
    ArrayList<Character> diceRolls = new ArrayList<Character>(25);
    for (String die : dice) {
      // Choose a random side from the die, and add it to the list to display on theboard
      diceRolls.add(die.charAt(random.nextInt(6)));
    }
    
    // put the rolls into a random order
    Collections.shuffle(diceRolls, random);
    
    // add the result of the roll to the board
    Iterator<Character> diceIter = diceRolls.iterator();         
    for (int r = 0; r < 5; r++) {           //looking through the row position
      for (int c = 0; c < 5; c++) {         //looking through the column position
        board[r][c] = diceIter.next();      //adding the result of the rolled letter onto the board
      }
    }
    
//    Letter randomize --------------------------------------------------------------------------------------------- End
    
//    Panel 0 ---------------------------------------------------------------------------------------------------- Start
    
    // Sets the border of panel 0. 
    blackline = BorderFactory.createLineBorder(Color.black);
    title = BorderFactory.createTitledBorder("Board");
    boggleBoard.setBorder(title);
    title = BorderFactory.createTitledBorder(blackline, "Board");
    title.setTitleJustification(TitledBorder.LEFT);
    boggleBoard.setBorder(title);
    
    // Set the layout of the frame
    boggleBoard.setLayout(new GridLayout(4,4));
    
    // Changes the font and size of the labels on the buttons
    button1.setFont(new Font("Courier", Font.BOLD,45));
    button2.setFont(new Font("Courier", Font.BOLD,45));
    button3.setFont(new Font("Courier", Font.BOLD,45));
    button4.setFont(new Font("Courier", Font.BOLD,45));
    button5.setFont(new Font("Courier", Font.BOLD,45));
    button6.setFont(new Font("Courier", Font.BOLD,45));
    button7.setFont(new Font("Courier", Font.BOLD,45));
    button8.setFont(new Font("Courier", Font.BOLD,45));
    button9.setFont(new Font("Courier", Font.BOLD,45));
    button10.setFont(new Font("Courier", Font.BOLD,45));
    button11.setFont(new Font("Courier", Font.BOLD,45));
    button12.setFont(new Font("Courier", Font.BOLD,45));
    button13.setFont(new Font("Courier", Font.BOLD,45));
    button14.setFont(new Font("Courier", Font.BOLD,45));
    button15.setFont(new Font("Courier", Font.BOLD,45));
    button16.setFont(new Font("Courier", Font.BOLD,45));
    
    // Adding letters to buttons and Adding buttons to panel 0
    // row 1
    button1.setText(Character.toString(board[0][0]));  //row 1 has a position of '0' on the 'x' axis. 
    button2.setText(Character.toString(board[0][1]));  
    button3.setText(Character.toString(board[0][2]));
    button4.setText(Character.toString(board[0][3]));  // the 'y' axis of the column has a position of 3
    boggleBoard.add(button1);
    boggleBoard.add(button2);
    boggleBoard.add(button3);
    boggleBoard.add(button4);
    // row 2
    button5.setText(Character.toString(board[1][0]));  //row 2's 'x' axis has a position of 1
    button6.setText(Character.toString(board[1][1]));
    button7.setText(Character.toString(board[1][2]));
    button8.setText(Character.toString(board[1][3]));
    boggleBoard.add(button5);
    boggleBoard.add(button6);
    boggleBoard.add(button7);
    boggleBoard.add(button8);
    // row 3
    button9.setText(Character.toString(board[2][0]));  //row 3's 'x' axis has a position of 2
    button10.setText(Character.toString(board[2][1]));
    button11.setText(Character.toString(board[2][2]));
    button12.setText(Character.toString(board[2][3]));
    boggleBoard.add(button9);
    boggleBoard.add(button10);
    boggleBoard.add(button11);
    boggleBoard.add(button12);
    // row 4
    button13.setText(Character.toString(board[3][0])); //row 4's 'x' axis has a position of 3
    button14.setText(Character.toString(board[3][1]));
    button15.setText(Character.toString(board[3][2]));
    button16.setText(Character.toString(board[3][3]));
    boggleBoard.add(button13);
    boggleBoard.add(button14);
    boggleBoard.add(button15);
    boggleBoard.add(button16);
    
    // Adds a listener to the buttons so they will respond when clicked
    button1.addActionListener(this); 
    button2.addActionListener(this);
    button3.addActionListener(this);
    button4.addActionListener(this);
    button5.addActionListener(this);
    button6.addActionListener(this);
    button7.addActionListener(this);
    button8.addActionListener(this);
    button9.addActionListener(this);
    button10.addActionListener(this);
    button11.addActionListener(this); 
    button12.addActionListener(this);
    button13.addActionListener(this);
    button14.addActionListener(this);
    button15.addActionListener(this);
    button16.addActionListener(this);
    
    // Remove little box on button when clicked
    button1.setFocusPainted(false);
    button2.setFocusPainted(false);
    button3.setFocusPainted(false);
    button4.setFocusPainted(false);
    button5.setFocusPainted(false);
    button6.setFocusPainted(false);
    button7.setFocusPainted(false);
    button8.setFocusPainted(false);
    button9.setFocusPainted(false);
    button10.setFocusPainted(false);
    button11.setFocusPainted(false);
    button12.setFocusPainted(false);
    button13.setFocusPainted(false);
    button14.setFocusPainted(false);
    button15.setFocusPainted(false);
    button16.setFocusPainted(false);
    
    // Disables all the buttons to make sure they can only be clicked after the 'start' button is pressed 
    button1.setEnabled(false);
    button2.setEnabled(false);
    button3.setEnabled(false);
    button4.setEnabled(false);
    button5.setEnabled(false);
    button6.setEnabled(false);
    button7.setEnabled(false);
    button8.setEnabled(false);
    button9.setEnabled(false);
    button10.setEnabled(false);
    button11.setEnabled(false);
    button12.setEnabled(false);
    button13.setEnabled(false);
    button14.setEnabled(false);
    button15.setEnabled(false);
    button16.setEnabled(false);
    
    // Sets the size and location of panel 0
    boggleBoard.setLocation(5, 5);
    boggleBoard.setSize(500,500);
    
//    Panel 0 ------------------------------------------------------------------------------------------------------ End
    
//    Panel 1 ---------------------------------------------------------------------------------------------------- Start
    
    // Sets the border of panel 1. Words that are inputted are included here.
    blackline = BorderFactory.createLineBorder(Color.black);
    title = BorderFactory.createTitledBorder("Word Input");
    wordInput.setBorder(title);
    title = BorderFactory.createTitledBorder(blackline, "Word Input");
    title.setTitleJustification(TitledBorder.LEFT);
    wordInput.setBorder(title);
    
    // Sets the layout of the frame
    wordInput.setLayout(new GridLayout(1,2));
    
    // Makes the wordField uneditable
    wordField.setEditable(false);
    
    // Adds to panel 1
    wordInput.add(wordField);
    wordInput.add(enterButton);
    
    // Remove little box on button when clicked
    enterButton.setFocusPainted(false);
    
    // Adds a listener to the button
    enterButton.addActionListener(this);
    
    // Disables the enter button
    enterButton.setEnabled(false);
    
    // Set the font size of the text
    wordField.setFont(new Font("Courier", Font.BOLD,45));
    enterButton.setFont(new Font("Courier", Font.BOLD,45));
    
    // Sets the size and location of panel 1
    wordInput.setLocation(5, 515);
    wordInput.setSize(500,80);
    
//    Panel 1 ------------------------------------------------------------------------------------------------------ End
    
//    Panel 2 ---------------------------------------------------------------------------------------------------- Start
    
    // Sets the border of panel 2. This is the display area
    blackline = BorderFactory.createLineBorder(Color.black);
    title = BorderFactory.createTitledBorder("Display Area");
    displayArea.setBorder(title);
    title = BorderFactory.createTitledBorder(blackline, "Display Area");
    title.setTitleJustification(TitledBorder.LEFT);
    displayArea.setBorder(title);
    
    // Adds a Scroll Panel to Panel 2
    textArea.setEditable(false);
    scrollPane.setPreferredSize(new Dimension(250,272));
    displayArea.add(scrollPane);
    
    // Set the layout of the frame
    displayArea.setLayout(new GridLayout());
    
    // Sets the size and location of panel 2
    displayArea.setLocation(515, 5);
    displayArea.setSize(260,300);
    
//    Panel 2 ------------------------------------------------------------------------------------------------------ End
    
//    Panel 3 ---------------------------------------------------------------------------------------------------- Start
    
    // Sets the border of panel 3. This is the panel where the time is displayed
//    Border orangeline;
    blackline = BorderFactory.createLineBorder(Color.black);
    title = BorderFactory.createTitledBorder("Timer");
    clock.setBorder(title);
    title = BorderFactory.createTitledBorder(blackline, "Timer");
    title.setTitleJustification(TitledBorder.LEFT);
    clock.setBorder(title);
    
    // Remove little box on button when clicked
    startTimer.setFocusPainted(false);
    
    // Set the layout of the frame
    clock.setLayout(new BorderLayout());
    
    // Adds the Timer and start button to the panel
    clock.add(new TestPane(), BorderLayout.PAGE_START);
    clock.add(startTimer, BorderLayout.PAGE_END);
    
    startTimer.setFont(new Font("Courier", Font.BOLD,45));
    startTimer.addActionListener(this); 
    
    // Sets the size and location of panel 2
    startTimer.setPreferredSize(new Dimension(90, 90));
    clock.setLocation(515, 315);
    clock.setSize(260,190);
    
//    Panel 3 ------------------------------------------------------------------------------------------------------ End
    
//    Panel 4 ---------------------------------------------------------------------------------------------------- Start
    
    // Sets the border of panel 4. This is where the score is displayed
    blackline = BorderFactory.createLineBorder(Color.black);
    title = BorderFactory.createTitledBorder("Score");
    scoring.setBorder(title);
    title = BorderFactory.createTitledBorder(blackline, "Score");
    title.setTitleJustification(TitledBorder.LEFT);
    scoring.setBorder(title);
    
    scorelabel.setFont(new Font("Courier", Font.BOLD,45));
    scoring.setLayout(new BorderLayout());
    scoring.add(scorelabel, BorderLayout.CENTER);
    
    // Sets the size and location of panel 2
    scoring.setLocation(515, 515);
    scoring.setSize(260,80);
    
//    Panel 4 ------------------------------------------------------------------------------------------------------ End
    
//    Panel 5 ---------------------------------------------------------------------------------------------------- Start
    
    // Sets the border of panel 5
    blackline = BorderFactory.createLineBorder(Color.black);
    menu.setBorder(blackline);
    
    // Puts eveything in a specific location 
    menu.setLayout(null);
    Insets insets = menu.getInsets();
    Dimension size = gameButton.getPreferredSize();
    gameButton.setBounds(120 + insets.left, 170 + insets.top,4 + size.width, size.height);  //this is the position of the start game button on the menu  
    size = rulesButton.getPreferredSize();
    rulesButton.setBounds(120 + insets.left, 200 + insets.top,size.width, size.height);   //this is the position of the rules button 
    size = title2.getPreferredSize();
    title2.setBounds(insets.left, insets.top, 90 + size.width, size.height);         //this is the position of the title of the game
    size = logo.getPreferredSize();
    logo.setBounds(85 + insets.left, 40 + insets.top,size.width, size.height);    //this is the position of the logo 
    
    // Remove little box on button when clicked
    gameButton.setFocusPainted(false);
    rulesButton.setFocusPainted(false);
    
    // Adds ActionListener to gameButton and rulesButton
    gameButton.addActionListener(this); 
    rulesButton.addActionListener(this);
    
    // Adds the buttons to panel 5
    title2.setSize(300,100);  //title size is set
    logo.setSize(135,135);    //logo size is set
    //title, logos, buttons added
    menu.add(title2);       
    menu.add(logo);
    menu.add(gameButton);
    menu.add(rulesButton);
    
    // Sets the size and location of panel 5
    menu.setLocation(240, 190);
    menu.setSize(300,250);
  }
  
//    Panel 5 ------------------------------------------------------------------------------------------------------ End
  
//    Class of Timer --------------------------------------------------------------------------------------------- Start
  
  public class TestPane extends JPanel  {      
    private long startTime = -1;        
    private long duration = 240199;       //this is the duration of the game (4 minutes)
    private JLabel label;
    
    public TestPane() {
      timer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if (startTime < 0) {           //what happens when the game is started
            startTime = System.currentTimeMillis();
          }
          long now = System.currentTimeMillis();     
          long clockTime = now - startTime;      //time starts counting down
          if (clockTime >= duration) {      //what happens when the game ends
            clockTime = duration;
            timer.stop();                  //timer is stopped and all buttons are disabled; you are no longer able to play 
            button1.setEnabled(false);      
            button2.setEnabled(false);
            button3.setEnabled(false);
            button4.setEnabled(false);
            button5.setEnabled(false);
            button6.setEnabled(false);
            button7.setEnabled(false);
            button8.setEnabled(false);
            button9.setEnabled(false);
            button10.setEnabled(false);
            button11.setEnabled(false);
            button12.setEnabled(false);
            button13.setEnabled(false);
            button14.setEnabled(false);
            button15.setEnabled(false);
            button16.setEnabled(false);
            enterButton.setEnabled(false);
            try{     //add score to text file
            File myFile3 = new File ("score.txt");
            PrintWriter printOut = new PrintWriter (myFile3); //printOut is the name of a Scanner object (print in from file)
            printOut.println("Your score is " + score); //print the score to a text file
            printOut.close();   //immediately close the "stream" to the file if no longer printing to it (avoid corrupting it)
            }
            catch(Exception f){}
            wordField.setText("");          //this is to clear the text box when the game is done, again signifying the end of the game
            for(int i = 0;i < currentPosition.size(); i++){
              currentPosition.get(i).setBackground(null);
            }
          }
          
          SimpleDateFormat df = new SimpleDateFormat("mm:ss");   //setup of the timer
          label.setText(df.format(duration - clockTime));
        }
      }
      );
      
      timer.setInitialDelay(0);           //no delays when counting down
      label = new JLabel("...");           // layout for how the timer looks like
      label.setFont(new Font("Courier", Font.BOLD,45));
      add(label);       // timer is added
    }
  }
  
//    Class of Timer ----------------------------------------------------------------------------------------------- End
  
//    Adding a action Listener for game -------------------------------------------------------------------------- Start
  
  public void actionPerformed(ActionEvent event){      //the buttons for letters are enabled when the game is started
    button1.setEnabled(true);
    button2.setEnabled(true);
    button3.setEnabled(true);
    button4.setEnabled(true);
    button5.setEnabled(true);
    button6.setEnabled(true);
    button7.setEnabled(true);
    button8.setEnabled(true);
    button9.setEnabled(true);
    button10.setEnabled(true);
    button11.setEnabled(true);
    button12.setEnabled(true);
    button13.setEnabled(true);
    button14.setEnabled(true);
    button15.setEnabled(true);
    button16.setEnabled(true);    
    if (event.getSource() == button1) {               //what happens when button1 is clicked
      wordField.setText(wordField.getText()+ board[0][0]);
      button3.setEnabled(false);                     //the following are all disabled because they are not in the proximity of button1
      button4.setEnabled(false);
      button7.setEnabled(false);
      button8.setEnabled(false);
      button9.setEnabled(false);
      button10.setEnabled(false);
      button11.setEnabled(false);
      button12.setEnabled(false);
      button13.setEnabled(false);
      button14.setEnabled(false);
      button15.setEnabled(false);
      button16.setEnabled(false);
      currentPosition.add(button1);                 //position of button 1 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    }
    else if (event.getSource() == button2) {            //what happens when button2 is clicked
      wordField.setText(wordField.getText()+ board[0][1]);
      button4.setEnabled(false);                        //the following are all disabled because they are not in the proximity of button2
      button8.setEnabled(false);
      button9.setEnabled(false);
      button10.setEnabled(false);
      button11.setEnabled(false);
      button12.setEnabled(false);
      button13.setEnabled(false);
      button14.setEnabled(false);
      button15.setEnabled(false);
      button16.setEnabled(false);
      currentPosition.add(button2);                   //position of button 2 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    }
    else if (event.getSource() == button3) {             //what happens when button3 is clicked
      wordField.setText(wordField.getText()+ board[0][2]);
      button1.setEnabled(false);                        //the following are all disabled because they are not in the proximity of button3
      button5.setEnabled(false);
      button9.setEnabled(false);
      button10.setEnabled(false);
      button11.setEnabled(false);
      button12.setEnabled(false);
      button13.setEnabled(false);
      button14.setEnabled(false);
      button15.setEnabled(false);
      button16.setEnabled(false);
      currentPosition.add(button3);                   //position of button 3 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    } 
    else if (event.getSource() == button4) {            //what happens when button4 is clicked
      wordField.setText(wordField.getText()+ board[0][3]);
      button1.setEnabled(false);                       //the following are all disabled because they are not in the proximity of button4
      button2.setEnabled(false);
      button5.setEnabled(false);
      button6.setEnabled(false);
      button9.setEnabled(false);
      button10.setEnabled(false);
      button11.setEnabled(false);
      button12.setEnabled(false);
      button13.setEnabled(false);
      button14.setEnabled(false);
      button15.setEnabled(false);
      button16.setEnabled(false);
      currentPosition.add(button4);                     //position of button 4 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    } 
    else if (event.getSource() == button5) {             //what happens when button5 is clicked
      wordField.setText(wordField.getText()+ board[1][0]);
      button3.setEnabled(false);                       //the following are all disabled because they are not in the proximity of button5
      button4.setEnabled(false);
      button7.setEnabled(false);
      button8.setEnabled(false);
      button11.setEnabled(false);
      button12.setEnabled(false);
      button13.setEnabled(false);
      button14.setEnabled(false);
      button15.setEnabled(false);
      button16.setEnabled(false);
      currentPosition.add(button5);                     //position of button 5 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    } 
    else if (event.getSource() == button6) {               //what happens when button6 is clicked
      wordField.setText(wordField.getText()+ board[1][1]);
      button4.setEnabled(false);                          //the following are all disabled because they are not in the proximity of button6
      button8.setEnabled(false);
      button12.setEnabled(false);
      button13.setEnabled(false);
      button14.setEnabled(false);
      button15.setEnabled(false);
      button16.setEnabled(false);
      currentPosition.add(button6);                    //position of button 6 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    } 
    else if (event.getSource() == button7) {             //what happens when button7 is clicked
      wordField.setText(wordField.getText()+ board[1][2]);
      button1.setEnabled(false);                         //the following are all disabled because they are not in the proximity of button7
      button5.setEnabled(false);
      button9.setEnabled(false);
      button13.setEnabled(false);
      button14.setEnabled(false);
      button15.setEnabled(false);
      button16.setEnabled(false);
      currentPosition.add(button7);                    //position of button 7 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    } 
    else if (event.getSource() == button8) {             //what happens when button8 is clicked
      wordField.setText(wordField.getText()+ board[1][3]);
      button1.setEnabled(false);                         //the following are all disabled because they are not in the proximity of button8
      button2.setEnabled(false);
      button5.setEnabled(false);
      button6.setEnabled(false);
      button10.setEnabled(false);
      button9.setEnabled(false);
      button13.setEnabled(false);
      button14.setEnabled(false);
      button15.setEnabled(false);
      button16.setEnabled(false);
      currentPosition.add(button8);                    //position of button 8 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    } 
    else if (event.getSource() == button9) {                //what happens when button9 is clicked
      wordField.setText(wordField.getText()+ board[2][0]);
      button1.setEnabled(false);                         //the following are all disabled because they are not in the proximity of button9
      button2.setEnabled(false);
      button3.setEnabled(false);
      button4.setEnabled(false);
      button7.setEnabled(false);
      button8.setEnabled(false);
      button11.setEnabled(false);
      button12.setEnabled(false);
      button15.setEnabled(false);
      button16.setEnabled(false);
      currentPosition.add(button9);                   //position of button 9 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    } 
    else if (event.getSource() == button10) {              //what happens when button10 is clicked
      wordField.setText(wordField.getText()+ board[2][1]);
      button1.setEnabled(false);                          //the following are all disabled because they are not in the proximity of button10
      button2.setEnabled(false);
      button3.setEnabled(false);
      button4.setEnabled(false);
      button8.setEnabled(false);
      button12.setEnabled(false);
      button16.setEnabled(false);
      currentPosition.add(button10);                    //position of button 10 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    } 
    else if (event.getSource() == button11) {              //what happens when button11 is clicked
      wordField.setText(wordField.getText()+ board[2][2]);
      button1.setEnabled(false);                           //the following are all disabled because they are not in the proximity of button11
      button2.setEnabled(false);
      button3.setEnabled(false);
      button4.setEnabled(false);
      button5.setEnabled(false);
      button9.setEnabled(false);
      button13.setEnabled(false);
      currentPosition.add(button11);                     //position of button 11 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    } 
    else if (event.getSource() == button12) {              //what happens when button12 is clicked
      wordField.setText(wordField.getText()+ board[2][3]);
      button1.setEnabled(false);                           //the following are all disabled because they are not in the proximity of button12
      button2.setEnabled(false);
      button3.setEnabled(false);
      button4.setEnabled(false);
      button5.setEnabled(false);
      button6.setEnabled(false);
      button9.setEnabled(false);
      button10.setEnabled(false);
      button13.setEnabled(false);
      button14.setEnabled(false);
      currentPosition.add(button12);                     //position of button 12 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    } 
    else if (event.getSource() == button13) {              //what happens when button13 is clicked
      wordField.setText(wordField.getText()+ board[3][0]);
      button1.setEnabled(false);                           //the following are all disabled because they are not in the proximity of button13
      button2.setEnabled(false);
      button3.setEnabled(false);
      button4.setEnabled(false);
      button5.setEnabled(false);
      button6.setEnabled(false);
      button7.setEnabled(false);
      button8.setEnabled(false);
      button11.setEnabled(false);
      button12.setEnabled(false);
      button15.setEnabled(false);
      button16.setEnabled(false); 
      currentPosition.add(button13);                       //position of button 13 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    } 
    else if (event.getSource() == button14) {                 //what happens when button14 is clicked
      wordField.setText(wordField.getText()+ board[3][1]);
      button1.setEnabled(false);                             //the following are all disabled because they are not in the proximity of button14
      button2.setEnabled(false);
      button3.setEnabled(false);
      button4.setEnabled(false);
      button5.setEnabled(false);
      button6.setEnabled(false);
      button7.setEnabled(false);
      button8.setEnabled(false);
      button12.setEnabled(false);
      button16.setEnabled(false);
      currentPosition.add(button14);                       //position of button 14 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    } 
    else if (event.getSource() == button15) {              //what happens when button15 is clicked
      wordField.setText(wordField.getText()+ board[3][2]);
      button1.setEnabled(false);                           //the following are all disabled because they are not in the proximity of button15
      button2.setEnabled(false);
      button3.setEnabled(false);
      button4.setEnabled(false);
      button5.setEnabled(false);
      button6.setEnabled(false);
      button7.setEnabled(false);
      button8.setEnabled(false);
      button9.setEnabled(false);
      button13.setEnabled(false);
      currentPosition.add(button15);                       //position of button 15 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    } 
    else if (event.getSource() == button16) {              //what happens when button16 is clicked
      wordField.setText(wordField.getText()+ board[3][3]);
      button1.setEnabled(false);                           //the following are all disabled because they are not in the proximity of button16
      button2.setEnabled(false);
      button3.setEnabled(false);
      button4.setEnabled(false);
      button5.setEnabled(false);
      button6.setEnabled(false);
      button7.setEnabled(false);
      button8.setEnabled(false);
      button9.setEnabled(false);
      button10.setEnabled(false);
      button13.setEnabled(false);
      button14.setEnabled(false);
      currentPosition.add(button16);                      //position of button 16 is added to array which makes sure the same button cannot be clicked again until enter button is pressed
    }
    if (event.getSource() == startTimer) {          //when timer is started the following is all enabled
      timer.start();
      button1.setEnabled(true);
      button2.setEnabled(true);
      button3.setEnabled(true);
      button4.setEnabled(true);
      button5.setEnabled(true);
      button6.setEnabled(true);
      button7.setEnabled(true);
      button8.setEnabled(true);
      button9.setEnabled(true);
      button10.setEnabled(true);
      button11.setEnabled(true);
      button12.setEnabled(true);
      button13.setEnabled(true);
      button14.setEnabled(true);
      button15.setEnabled(true);
      button16.setEnabled(true);
      enterButton.setEnabled(true);
      startTimer.setEnabled(false);                    // start button is no longer enabled
    }
    if (event.getSource() == gameButton) {             //what happens when start button at the menu is clicked
      button1.setEnabled(false);                      //board and other functions are shown but is not enabled until start is clicked
      button2.setEnabled(false);
      button3.setEnabled(false);
      button4.setEnabled(false);
      button5.setEnabled(false);
      button6.setEnabled(false);
      button7.setEnabled(false);
      button8.setEnabled(false);
      button9.setEnabled(false);
      button10.setEnabled(false);
      button11.setEnabled(false);
      button12.setEnabled(false);
      button13.setEnabled(false);
      button14.setEnabled(false);
      button15.setEnabled(false);
      button16.setEnabled(false);
      frame.setLayout(null);                        
      boggleBoard.setVisible(true);
      wordInput.setVisible(true);
      displayArea.setVisible(true);
      clock.setVisible(true);
      scoring.setVisible(true);
      menu.setVisible(false);
      
    }
    if (event.getSource() == rulesButton){       //what happens when rules button is clicked
      menu.setVisible(false);            //menu dissapears
      rulesLabel.setVisible(true);       //rules from text file are displayed
      backButton.setVisible(true);       //going back to menu button is shown
    }
    if (event.getSource() == backButton){   //what happens when the menu butotn is clicked
      rulesLabel.setVisible(false);         //directed back to menu so the stuff on the rules panel is not shown
      backButton.setVisible(false);
      menu.setVisible(true);
    }
    
    if (event.getSource() == enterButton) {      //things that happen when the enter button is clicked 
      for(int i = 0;i < currentPosition.size(); i++){    //text input area is cleared
        currentPosition.get(i).setBackground(null);
      }
      currentPosition = new ArrayList<JButton>();        //old current position array list is cleared and new one is created
      word = wordField.getText();
      // Word checker
      String str = "";
      String newLine = "\n";
      if (dictionary.contains(word) == true && usedWords.contains(word) == false) {         //if word is found
        if(word.length() <= 4){             //points recieved for the length of the word
          score= score+1;
        }
        else if(word.length() == 5){
          score= score+2;
        }
        else if(word.length() == 6){
          score= score+3;
        }
        else if(word.length() == 7){
          score= score+5;
        }
        else if(word.length() >= 8){
          score= score+11;
        }
        String phrase = Integer.toString(score);       //score is schanged
        scorelabel.setText(phrase);
        str += word;
        usedWords.add(str);
        textArea.append(str + newLine);
        wordField.setText("");
      } 
      else if(dictionary.contains(word) == false){    //if word is not found
        wordField.setText("");
        JOptionPane.showMessageDialog(frame,"Word not found\nPress Enter to close","Warning",JOptionPane.WARNING_MESSAGE);   //warnign message displayed
      }
      else{     //if word has been used
        wordField.setText("");
        JOptionPane.showMessageDialog(frame,"Word Used\nPress Enter to close","Warning",JOptionPane.WARNING_MESSAGE);   //warning message displayed
      }
    }
    
    for(int i = 0;i < currentPosition.size(); i++){        //once a button is clicked it turns red to show clearly you have used that letter
      currentPosition.get(i).setEnabled(false);
      currentPosition.get(i).setBackground(Color.red);
    }
  }
  
//    Adding a action Listener for game ---------------------------------------------------------------------------- End    
  
  //reads from the word file used as a dictionary to find all the valid words found in the game
  public static void main(String[] args)throws IOException {
    Scanner input = new Scanner (System.in);
    new FinalProject();
    File myFile = new File ("data/words.txt");
    Scanner readFile = new Scanner (myFile); //readFile is the name of a Scanner object (reads in from file)
    String str = "";
    while(readFile.hasNext()){
      str = readFile.nextLine(); // reads the second line and saves it as str
      str = str.toUpperCase();
      dictionary.add(str);
    }
    readFile.close();   //immediately close the "stream" to the file if not in use (avoid corrupting the file)
    
   //reads from the word file used to display the rules for the player to read
    File myFile2 = new File ("data/gameRules.txt");
    Scanner readFile2 = new Scanner (myFile2); //readFile is the name of a Scanner object (reads in from file)
    String str2 = "";
    while(readFile2.hasNext()){
      str2 += readFile2.nextLine(); // reads the second line and saves it as str
      rulesLabel.setText(str2);
    }
    readFile2.close();  //immediately close the "stream" to the file if not in use (avoid corrupting the file)
    
    input.close(); 
  }   //end of main
}  //end of class