package GUI.Snakes;
import java.util.Random;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
public class snakes {
	JButton b[][] = new JButton[48][48];
	long n[][] = new long[48][48];
	Color bl = Color.BLACK,r = Color.RED,gr = Color.GREEN;
	int hxd = 1,hyd = 0,mx = 0,my = 0,co = 0,sc = 3,sco = 3,nf = 0,speed = 500;
	boolean g = true,wa = false;
	Thread thread1 = null;
	public void food() {
		Random ra = new Random();
		int c = 0;
		while(c == 0) {
			int x = ra.nextInt(48);
			int y = ra.nextInt(48);
			if(b[x][y].getBackground() == bl) {
				b[x][y].setBackground(r);
				c++;
			}
		}
	}
	public void min(long n[][]) {
		int x = 0,y = 0;
		long min = System.currentTimeMillis();
		while(x < 48 && y < 48) {
			if(n[x][y] != 0 && n[x][y] != 1 && n[x][y] < min) {
				mx = x;
				my = y;
				min = n[x][y];
			}
			x++;
			if(x > 47) {
				x = 0;
				y++;
			}
		}
	}
	public void movewithwalls() {
		int x = 0,y = 0;
		co = 1;
		while(x < 48 && y < 48) {
			if(b[x][y].getText().equals("h")) {
				if(x+hxd > 47 || x+hxd < 0 || y+hyd > 47 || y+hyd < 0 || b[x+hxd][y+hyd].getBackground() == gr) {
					g = false;
					return;
				}
				else if(b[x+hxd][y+hyd].getBackground() == r) {
					sc++;
					nf = 0;
				}
				b[x+hxd][y+hyd].setBackground(gr);
				b[x][y].setText("");
				n[x][y] = System.currentTimeMillis();
				b[x+hxd][y+hyd].setText("h");
				break;
			}
			x++;
			if(x > 47) {
				x = 0;
				y++;
			}
		}
		if(!(sc > sco)) {
			min(n);
			b[mx][my].setBackground(bl);
			n[mx][my] = 0;
		}
		else
			sco = sc;
	}
	public void movenowalls() {
		int x = 0,y = 0;
		co = 1;
		while(x < 48 && y < 48) {
			if(b[x][y].getText().equals("h")) {
				if(x+hxd > 47) {
					if(b[0][y+hyd].getBackground() == gr) {
						g = false;
						return;
					}
					else if(b[0][y+hyd].getBackground() == r) {
						sc++;
						nf = 0;
					}
					b[0][y+hyd].setBackground(gr);
					b[x][y].setText("");
					n[x][y] = System.currentTimeMillis();
					b[0][y+hyd].setText("h");
					break;
				}
				else if(x+hxd < 0) {
					if(b[47][y+hyd].getBackground() == gr) {
						g = false;
						return;
					}
					else if(b[47][y+hyd].getBackground() == r) {
						sc++;
						nf = 0;
					}
					b[47][y+hyd].setBackground(gr);
					b[x][y].setText("");
					n[x][y] = System.currentTimeMillis();
					b[47][y+hyd].setText("h");
					break;
				}
				else if(y+hyd < 0) {
					if(b[x+hxd][47].getBackground() == gr) {
						g = false;
						return;
					}
					else if(b[x+hxd][47].getBackground() == r) {
						sc++;
						nf = 0;
					}
					b[x+hxd][47].setBackground(gr);
					b[x][y].setText("");
					n[x][y] = System.currentTimeMillis();
					b[x+hxd][47].setText("h");
					break;
				}
				else if(y+hyd > 47) {
					if(b[x+hxd][0].getBackground() == gr) {
						g = false;
						return;
					}
					else if(b[x+hxd][0].getBackground() == r) {
						sc++;
						nf = 0;
					}
					b[x+hxd][0].setBackground(gr);
					b[x][y].setText("");
					n[x][y] = System.currentTimeMillis();
					b[x+hxd][0].setText("h");
					break;
				}
				else {
					if(b[x+hxd][y+hyd].getBackground() == gr) {
						g = false;
						return;
					}
					else if(b[x+hxd][y+hyd].getBackground() == r) {
						sc++;
						nf = 0;
					}
					b[x+hxd][y+hyd].setBackground(gr);
					b[x][y].setText("");
					n[x][y] = System.currentTimeMillis();
					b[x+hxd][y+hyd].setText("h");
					break;
				}
			}
			x++;
			if(x > 47) {
				x = 0;
				y++;
			}
		}
		if(!(sc > sco)) {
			min(n);
			b[mx][my].setBackground(bl);
			n[mx][my] = 0;
		}
		else
			sco = sc;
	}
	public static void main(String[] ar) {
		new snakes();
	}
	public snakes() {
		JFrame f = new JFrame("Snakes");
		f.setSize(966, 1000);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		JPanel p = new JPanel();
		p.setLayout(null);
		int x = 0, y = 0,x2 = 0,y2 = 0;
		while(y2 < 48 && x2 < 48) {
			b[x2][y2] = new JButton("");
			b[x2][y2].setBackground(bl);
			b[x2][y2].setBounds(x, y,20,20);
			b[x2][y2].setOpaque(true);
			b[x2][y2].setBorderPainted(false);
			b[x2][y2].setEnabled(false);
			b[x2][y2].setFont(new Font("Arial",Font.PLAIN,0));
			p.add(b[x2][y2]);
			x+=20;
			x2++;
			if(x2 > 47) {
				x = 0;
				y+=20;
				x2 = 0;
				y2++;
			}
		}
		b[22][24].setBackground(gr);
		n[22][24] = System.currentTimeMillis();
		b[23][24].setBackground(gr);
		n[23][24] = System.currentTimeMillis();
		b[24][24].setBackground(gr);
		b[24][24].setText("h");
		f.add(p);
		f.setVisible(true);
		f.addKeyListener(new Keylistener());
		String o[] = {"Easiest","Easy","Medium","Hard","Hardest","Insanity"};
		UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial",Font.PLAIN,40)));
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial",Font.PLAIN,40)));
		int s = -1;
		while(s == -1) {
			s = JOptionPane.showOptionDialog(null, "Choose Difficulty", "Difficulty Selector", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,o,-1);
			if(s == 0)
				speed = 500;
			else if(s == 1)
				speed = 300;
			else if(s == 2)
				speed = 200;
			else if(s == 3)
				speed = 100;
			else if(s == 4)
				speed = 50;
			else if(s == 5)
				speed = 10;
		}
		s = -1;
		String op[] = {"Walls","No Walls"};
		while(s == -1) {
			s = JOptionPane.showOptionDialog(null, "Walls?", "Walls?", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,op,-1);
			if(s == 0)
				wa = true;
			else if(s == 1)
				wa = false;
		}
		thread1 = new Thread(new Runnable() {
			public void run(){
				while(true){
					if(wa)
						movewithwalls();
					else
						movenowalls();
					f.setTitle("Score = "+sc);
					if(!g) {
						JOptionPane.showMessageDialog(null, "Game Over, Score = "+sc);
						System.exit(0);
					}
					if(nf == 0) {
						food();
						nf++;
					}
					co = 0;
					try {
						Thread.sleep(speed);
					}
					catch(InterruptedException e) {
						break;
					}
				}
			}
	    });
	thread1.start();
	}
	private class Keylistener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == 37) { //37 is the code for left key
				while(co == 0 && hxd == 0) {
					hxd = -1;
					hyd = 0;
					co = 1;
				}
			}
			else if(e.getKeyCode() == 38) { //38 is the code for up key
				while(co == 0 && hyd == 0) {
					hxd = 0;
					hyd = -1;
					co = 1;
				}
			}
			else if(e.getKeyCode() == 39) { //39 is the code for right key
				while(co == 0 && hxd == 0) {
					hxd = 1;
					hyd = 0;
					co = 1;
				}
			}
			else if(e.getKeyCode() == 40) { //40 is the code for down key
				while(co == 0 && hyd == 0) {
					hxd = 0;
					hyd = 1;
					co = 1;
				}
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
	}
}