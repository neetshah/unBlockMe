// Neet Shah
// started on Dec 10th, 2016
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
public class unBlockMetry extends Applet implements ActionListener
{
    Panel p_card; //hold all the screens
    Panel card1, card2, card3; //the screens
    CardLayout cdLayout = new CardLayout ();
    int x, y, num;
    int row = 6;
    int row1 = 3;
    JLabel counter;
    JButton a[] = new JButton [row * row];
    JButton b[] = new JButton [row1 * row1];
    //int p[] [] = {{0, 0, 0, 7, 2, 0}, {0, 0, 0, 7, 2, 0}, {1, 1, 4, 7, 2, 0 /* 8 */}, {0, 5, 4, 0, 3, 3}, {0, 5, 0, 0, 0, 0}, {0, 6, 6, 0, 0, 0}};
    int p[] [] = {{1031, 1032, 1033, 0, 0, 2031}, {0, 0, 2031, 0, 0, 2032}, {1021, 1022, 2032, 0, 0, 2033}, {2021, 0, 2033, 0, 1121, 1122}, {2022, 0, 0, 0, 2021, 0}, {1031, 1032, 1033, 0, 2022, 0}};
    int check[] [] = new int [6] [6];
    int whichcar = 0;
    int count, current;
    public void init ()
    {
	JLabel title;
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	// screen1 ();
	screen2 ();
	//screen3 ();

	setLayout (new BorderLayout ());
	add ("Center", p_card);

	resize (405, 520);
    }


    public void screen1 ()
    {
	card1 = new Panel ();
	card1.setBackground (Color.white);
	JButton title = new JButton (createImageIcon ("menu.png"));
	title.addActionListener (this);
	title.setActionCommand ("123");
	title.setBorder (null);
	card1.add (title);
	p_card.add ("1", card1);
    }


    public void screen2 ()
    {
	card2 = new Panel ();
	card2.setBackground (new Color (140, 140, 140));
	JLabel title = new JLabel ("Unblock Me");
	title.setFont (new Font ("Times New Roman", Font.BOLD, 80));
	title.setForeground (Color.blue);

	for (int i = 0 ; i < a.length ; i++) // Unblock Me pieces
	{
	    a [i] = new JButton ("");
	    a [i].addActionListener (this);
	    a [i].setActionCommand ("a" + i);
	    a [i].setPreferredSize (new Dimension (50, 50));
	}

	Panel g = new Panel (new GridLayout (row, row));
	//add the buttons here, one by one
	for (int i = 0 ; i < a.length ; i++)
	{
	    a [i].setBackground (Color.black);
	    a [i].setBorder (null);
	    g.add (a [i]);
	}
	print ();
	for (int i = 0 ; i < 4 ; i++)
	{
	    //c[i] = new JButton (createImageIcon

	    for (int z = 0 ; z < 9 ; z++) // arrow keys
	    {
		b [z] = new JButton ("");
		b [z].addActionListener (this);
		b [z].setActionCommand ("b" + z);
		b [z].setPreferredSize (new Dimension (50, 50));
	    }
	}

	//creating arrow keys
	Panel g1 = new Panel (new GridLayout (row1, row1));
	for (int z = 0 ; z < b.length ; z++)
	{
	    b [z].setBorder (null);
	    g1.add (b [z]);
	}
	b [1].setIcon (createImageIcon ("up.jpg"));
	b [3].setIcon (createImageIcon ("left.jpg"));
	b [5].setIcon (createImageIcon ("right.jpg"));
	b [7].setIcon (createImageIcon ("down.jpg"));
	b [0].setVisible (false);
	b [2].setVisible (false);
	b [4].setVisible (false);
	b [6].setVisible (false);
	b [8].setVisible (false);

	counter = new JLabel ("Moves: " + count);

	JButton reset = new JButton ("Reset");
	reset.addActionListener (this);
	reset.setActionCommand ("reset");

	card2.add (title);
	card2.add (g);
	card2.add (g1);
	card2.add (counter);
	card2.add (reset);
	p_card.add ("2", card2);
    }


    public void print ()
    {
	int m = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < row ; j++)
	    {
		String change = p [i] [j] + ".jpg";
		String change1 = "\"" + change + "\"";
		a [m].setIcon (createImageIcon (change));
		m++;
	    }
	}
    }



    public void actionPerformed (ActionEvent e)
    {
	char which = e.getActionCommand ().charAt (0);
	for (int i = 0 ; i < 6 ; i++)
	{
	    for (int j = 0 ; j < 6 ; j++)
		check [i] [j] = p [i] [j];
	}


	if (e.getActionCommand ().equals ("123"))
	{
	    resize (500, 450);
	    cdLayout.show (p_card, "2");
	}

	if (which == 'a')
	{
	    num = Integer.parseInt (e.getActionCommand ().substring (1, e.getActionCommand ().length ()));
	    x = num / row;
	    y = num % row;
	    showStatus ("Which colour: " + p [x] [y]);
	    whichcar = p [x] [y];
	}

	else if (which == 'b')
	{
	    int num1 = Integer.parseInt (e.getActionCommand ().substring (1, e.getActionCommand ().length ()));
	    move (num1);
	}

	else if (e.getActionCommand ().equals ("reset"))
	{
	    reset ();
	}

	int pos = -1;
	for (int i = 0 ; i < 6 ; i++)
	{
	    for (int j = 0 ; j < 6 ; j++)
	    {
		if (p [i] [j] != check [i] [j])
		    pos = 1;
	    }
	}

	if (pos == 1)
	    count += 1;
	counter.setText ("Moves: " + count);
	if (p [2] [5] == 1022)
	{
	    resize (500, 100);
	    win (count);
	    cdLayout.show (p_card, "3");
	}


	print ();
    }


    public void move (int b)
    {
	String a = Integer.toString (whichcar);
	String a2 = a.substring (0, 1);
	int a3 = Integer.parseInt (a2);

	if ((a3 == 1) && (b == 3 || b == 5))
	    move1 (b);
	else if ((a3 == 2) && (b == 1 || b == 7))
	    move2 (b);
    }


    public void move1 (int b)
    {
	String a = Integer.toString (whichcar);
	String a2 = a.substring (a.length () - 1, a.length ());
	int a3 = Integer.parseInt (a2);
	current = a3;
	while (current != 1)
	{
	    y--;
	    //showStatus ("Which colour: " + p [x] [y]);
	    whichcar = p [x] [y];

	    a = Integer.toString (whichcar);
	    a2 = a.substring (a.length () - 1, a.length ());
	    current = Integer.parseInt (a2);
	}
	String c = Integer.toString (whichcar);
	String c2 = a.substring (c.length () - 2, c.length () - 1);
	int c3 = Integer.parseInt (c2);
	//left for block length of 2
	if ((y % 6 != 0) && (p [x] [y - 1] == 0) && (c3 == 2) && (b == 3))
	{
	    p [x] [y - 1] = p [x] [y];
	    p [x] [y] = p [x] [y + 1];
	    p [x] [y + 1] = 0;
	}

	//right for block length of 2
	else if ((y % 6 != 4) && (p [x] [y + 2] == 0) && (c3 == 2) && (b == 5))
	{
	    p [x] [y + 2] = p [x] [y + 1];
	    p [x] [y + 1] = p [x] [y];
	    p [x] [y] = 0;
	}

	//left for block length of 3
	else if ((y % 6 != 0) && (p [x] [y - 1] == 0) && (c3 == 3) && (b == 3))
	{
	    p [x] [y - 1] = p [x] [y];
	    p [x] [y] = p [x] [y + 1];
	    p [x] [y + 1] = p [x] [y + 2];
	    p [x] [y + 2] = 0;
	}

	//right for block length of 3
	else if ((y % 6 != 3) && (p [x] [y + 3] == 0) && (c3 == 3) && (b == 5))
	{
	    p [x] [y + 3] = p [x] [y + 2];
	    p [x] [y + 2] = p [x] [y + 1];
	    p [x] [y + 1] = p [x] [y];
	    p [x] [y] = 0;
	}
	print ();
    }


    public void move2 (int b)
    {
	String a = Integer.toString (whichcar);
	String a2 = a.substring (a.length () - 1, a.length ());
	int a3 = Integer.parseInt (a2);
	current = a3;
	while (current != 1)
	{
	    x--;
	    //showStatus ("Which colour: " + p [x] [y]);
	    whichcar = p [x] [y];

	    a = Integer.toString (whichcar);
	    a2 = a.substring (a.length () - 1, a.length ());
	    current = Integer.parseInt (a2);
	}
	String c = Integer.toString (whichcar);
	String c2 = a.substring (c.length () - 2, c.length () - 1);
	int c3 = Integer.parseInt (c2);

	//up for block of length 2
	if ((x != 0) && (p [x - 1] [y] == 0) && (c3 == 2) && (b == 1))
	{
	    p [x - 1] [y] = p [x] [y];
	    p [x] [y] = p [x + 1] [y];
	    p [x + 1] [y] = 0;
	}
	//down for block of length 2
	else if ((x / 6 != 4) && (p [x + 2] [y] == 0) && (c3 == 2) && (b == 7))
	{
	    p [x + 2] [y] = p [x + 1] [y];
	    p [x + 1] [y] = p [x] [y];
	    p [x] [y] = 0;
	}
	//up for block of length 3
	else if ((x != 0) && (p [x - 1] [y] == 0) && (c3 == 3) && (b == 1))
	{
	    p [x - 1] [y] = p [x] [y];
	    p [x] [y] = p [x + 1] [y];
	    p [x + 1] [y] = p [x + 2] [y];
	    p [x + 2] [y] = 0;
	}
	//down for block of length 3
	else if ((x / 6 != 4) && (p [x + 3] [y] == 0) && (c3 == 3) && (b == 7))
	{
	    p [x + 3] [y] = p [x + 2] [y];
	    p [x + 2] [y] = p [x + 1] [y];
	    p [x + 1] [y] = p [x] [y];
	    p [x] [y] = 0;
	}
	print ();
    }


    public void reset ()  //reset
    {
	int a[] [] = {{1031, 1032, 1033, 0, 0, 2031}, {0, 0, 2031, 0, 0, 2032}, {1021, 1022, 2032, 0, 0, 2033}, {2021, 0, 2033, 0, 1121, 1122}, {2022, 0, 0, 0, 2021, 0}, {1031, 1032, 1033, 0, 2022, 0}};
	showStatus ("Reset");
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < row ; j++)
		p [i] [j] = a [i] [j];
	}
	count = -1;
	counter.setText ("Moves: " + count);
	print ();
    }


    public void win (int d)
    {
	int p = 0;
	if (d <= 26)
	    p = 3;
	else if (d <= 30)
	    p = 2;
	else if (d > 30)
	    p = 1;
	JOptionPane.showMessageDialog (null, "You completed level 1 in " + d + " moves! You received " + p + " stars!", "You Won!",
		JOptionPane.ERROR_MESSAGE);


	//Show a dialog asking the user to select from a combobox:
	String[] possibleValues = {"Reset", "Next Level", "Main Menu"};
	String selectedValue = (String) JOptionPane.showInputDialog (null,
		"What do you want to do next?", "Choose one", JOptionPane.INFORMATION_MESSAGE, null,
		possibleValues, possibleValues [0]);
	if (possibleValues.equals ("Reset"))
	    reset ();
	cdLayout.show (p_card, "3");
    }



    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = unBlockMetry.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}


	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }
}


