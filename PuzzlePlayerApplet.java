

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;


import javax.swing.Timer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.TextField;
import java.io.File;
import java.io.IOException;

public class PuzzlePlayerApplet extends Applet implements ActionListener,MouseMotionListener,MouseListener
{
	JButton b[][] = new JButton[10][10];
	int x,y;
	Font f = new Font(PuzzleConfig.fontName,0,22);
	PuzzleTracker pt1;
	boolean dragged = false;
	ArrayList<JButton> button;
	JCheckBox[] jcb;
	Puzzle p1;
	Font gidugu_font;
	int hours = 0;
    int minutes = 0;
    long seconds = 0;
    Timer t;
	
	public void init()
	{
		try{
		setSize(900,800);
		setLayout(null);
		//setBackground(Color.blue);
		JPanel jp = new JPanel();
		jp.setBounds(0, 0, 700, 600);
		add(jp);

		jp.setPreferredSize(new Dimension(700,700));
		
		GridLayout lay = new GridLayout(10,10);
		lay.setVgap(10);
		lay.setHgap(10);
		jp.setLayout(lay);

		//Label l[][] = new Label[10][10];
		Font f = new Font(PuzzleConfig.fontName,0,22);

		// TESTS for puzzle 001
		// get 001 puzzle from the puzzlelist
		PuzzlePicker pp1 = new PuzzlePicker("file:///E:/wcl/wordgame/wordgame.html?id=00");


		// populates the puzzle collection
		PuzzleCollection pc1 = new PuzzleCollection(PuzzleConfig.defaultFile);
		// get the puzzle based on the id
		p1 = pc1.getPuzzleByID(pp1.getPuzzleId());
		pt1 = new PuzzleTracker(p1);
		
		setTitle(p1);
		displayTime();
		displayWords(p1);
		Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Gidugu.ttf"));
		Font sizedFont = font.deriveFont(PuzzleConfig.fontSize);

		for(int i = 0; i < p1.getWordList().size();i++)
			p1.getWordList().get(i);
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				String temp = escapeUnicode(p1.getGrid()[i][j]);
				String hex = "";
				if(temp.contains("\\u")){
					StringTokenizer st = new StringTokenizer(temp);
					while(st.hasMoreTokens()) {
						int intValue = Integer.parseInt(st.nextToken("\\u"), 16);
						hex = hex + String.valueOf((char)intValue);
					}
				}
				else
					hex = temp;
				//b[i][j].setFont(sizedFont);
				b[i][j] = new JButton();	
				b[i][j].setFont(sizedFont);
				b[i][j].setText(hex);
				b[i][j].setFont(sizedFont);
				jp.setFont(sizedFont);	
				
				b[i][j].addActionListener(this);
				b[i][j].addMouseMotionListener(this);
				b[i][j].addMouseListener(this);
				b[i][j].setBackground(PuzzleConfig.cellColorUnselected);
				jp.add(b[i][j]);
			}
		}
			button = new ArrayList<JButton>();
		
			jp.setFont(gidugu_font);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//Button b = (Button) e.getSource();
		//b.setBackground(Color.GREEN);
		//b.setForeground(Color.yellow);
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseMoved(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(dragged){
			JButton b = (JButton)e.getSource();
			if(b.getForeground() == PuzzleConfig.cellbgColorSelected)
				b.setForeground(Color.orange);
			else
				b.setForeground(PuzzleConfig.textColorSelected);
			button.add(b);
		}
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent e)
	{
		dragged = true;
		JButton b = (JButton)e.getSource();
		if(b.getForeground() == PuzzleConfig.cellbgColorSelected)
				b.setForeground(Color.orange);
			else
				b.setForeground(PuzzleConfig.textColorSelected);
		button.add(b);	
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		dragged = false;
		checkword();
	}

	@Override
	public void mouseDragged(MouseEvent arg0)
	{
		
	}
	public void setTitle(Puzzle p)
	{
		JPanel Idjp = new JPanel();
		Idjp.setBounds(700, 50, 200, 50);
		Idjp.setPreferredSize(new Dimension(150,50));
		Idjp.setLayout(new FlowLayout());
		add(Idjp);
		Label l = new Label();
		l.setFont(f);
		l.setText(p.getTitle());
		Idjp.add(l);
	}
	public void displayTime()
	{
		
			final TextField tf = new TextField(8);
			JPanel tjp = new JPanel();
			tjp.setBounds(750, 550, 150, 150);
			tjp.setPreferredSize(new Dimension(150,50));
			tjp.setLayout(new FlowLayout());
			add(tjp);
			final int N = 60;
			t = new Timer(1000, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					
					
				    String hour;
				    String minute;
				    String second;
				    
				    
				        NumberFormat formatter = new DecimalFormat("00");
				        if (seconds == N) {
				            seconds = 00;
				            minutes++;
				        }

				        if (minutes == N) {
				            minutes = 00;
				            hours++;
				        }
				        hour = formatter.format(hours);
				        minute = formatter.format(minutes);
				        second = formatter.format(seconds);
				      
				        tf.setEditable(false);
				        tf.setText(String.valueOf(hour + ":" + minute + ":" + second));
				        seconds++;
					// TODO Auto-generated method stub
				        
				        	
				        
				}
				
				
			});
			
			tjp.add(tf);
			
		}
		public void start()
		{
			t.start();
		}
		
		public String toFormat(){
			   
			return String.format("%02d:%02d:%02d", hours, minutes,seconds);
			}


	public void displayWords(Puzzle p) throws FontFormatException, IOException
	{
	
		JPanel dpw = new JPanel(new GridLayout(5,2));
		dpw.setBounds(700, 150, 150, 350);
		dpw.setPreferredSize(new Dimension(150,150));
		dpw.setLayout(new FlowLayout());
		add(dpw);
		
		jcb = new JCheckBox[p.getWordList().size()];
		//JLabel[] jlb = new JLabel[10];
		ArrayList<String> al = p1.getWordList();
		
		Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Gidugu.ttf"));
		Font sizedFont = font.deriveFont(14f);
		for(int i=0;i<p1.getWordList().size();i++)
		{
			String temp = escapeUnicode(al.get(i).replaceAll("\\s+", ""));
			String hex = "";
			if(temp.contains("\\u")){
				StringTokenizer st = new StringTokenizer(temp);
				while(st.hasMoreTokens()) {
					int intValue = Integer.parseInt(st.nextToken("\\u"), 16);
					hex = hex + String.valueOf((char)intValue);
				}
			}
			else
				hex = al.get(i);
			jcb[i] = new JCheckBox();
			//jlb[i] = new JLabel();
			
			
			jcb[i].setText(hex);
			jcb[i].setFont(sizedFont);
			dpw.add(jcb[i]);
			jcb[i].setEnabled(false);
			//dpw.add(jlb[i]);

		}
	}
	
	public void checkword(){
		Iterator<JButton> iterator = button.listIterator();
		String s = "";
		boolean isWord = false;
		
		
		while(iterator.hasNext()){
			JButton tem = iterator.next();
			s = s + tem.getText();
		}

		
		
		for(int i = 0; i < jcb.length; i++ ) {
			String temp = jcb[i].getText().replaceAll("\\s+", "");
			if(s.equalsIgnoreCase(temp)) {
				Iterator<JButton> iterator1 = button.listIterator();
				while(iterator1.hasNext()){
					JButton tem = iterator1.next();
					tem.setForeground(PuzzleConfig.cellbgColorSelected);
					jcb[i].setSelected(true);
					
					isWord = true;
				}
				break;
			}
			else if(s.equalsIgnoreCase(pt1.reverseWord(temp))){
			Iterator<JButton> iterator1 = button.listIterator();
				while(iterator1.hasNext()){
					JButton tem = iterator1.next();
					tem.setForeground(PuzzleConfig.cellbgColorSelected);
					jcb[i].setSelected(true);
					
					isWord = true;
				}
				break;
			}
		}
		if(!isWord){
			Iterator<JButton> iterator1 = button.listIterator();
			while(iterator1.hasNext()){
				JButton tem = iterator1.next();
			if(tem.getForeground() == PuzzleConfig.textColorSelected)
				tem.setForeground(Color.black);
			if(tem.getForeground() == Color.orange)
				tem.setForeground(PuzzleConfig.cellbgColorSelected);
				
			}
		}
		button.removeAll(button);	
	
		
	}

	public String escapeUnicode(String input) {
		  StringBuilder b = new StringBuilder(input.length());
		  Formatter f = new Formatter(b);
		  for (char c : input.toCharArray()) {
		    if (c < 128) {
		      b.append(c);
		    } else {
		      f.format("\\u%04x", (int) c);
		    }
		  }
		 // System.out.println(b.toString());
		 return b.toString();
		
	}
}
	
	
