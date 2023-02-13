package ballgame_frame;

//public class Ballgame_frame extends JFrame //繼承父類Jframe
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;

public class Ballgame_frame extends JFrame //繼承父類Jframe
{
	Ball ball=new Ball(this); //這裡建立一個Ball物件,this作為引數讓Ball類可以獲取Game的成員資訊
	Racquet racquet=new Racquet(this);
	static int score;
	public Ballgame_frame()
{
	this.setTitle("彈跳球遊戲");
	this.setSize(600,600);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	KeyListener listener=new KeyListener()
	{
	 
		@Override
		public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
			racquet.KeyPressed(e);
		}
		 
		@Override
		public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
			racquet.KeyReleased(e);
		}
		 
		@Override
		public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		}
	};
	addKeyListener(listener);
	setFocusable(true);
}
	private void move() //這裡是move方法用來呼叫Ball類中的moveBall
	{ 
		ball.moveBall();
		racquet.move();
	}
	public static void main(String[] args) throws InterruptedException //這個是丟擲異常用來防止執行緒異常的，需要加
	{
		Ballgame_frame tennis=new Ballgame_frame(); //建立game物件
		while (true) {
 
			tennis.move(); //因為訪問限制所以通過move方法來呼叫Ball類中的moveBall方法
			tennis.repaint(); //重新繪製
			Thread.sleep(2); //延遲5毫秒再繪製,不然小球會移動很快一閃而過
			}
	}
	public void gameOver() {
		JOptionPane.showMessageDialog(this, "遊戲結束", "Game Over", JOptionPane.YES_NO_OPTION);//球碰到底跳出遊戲結束視窗
		System.exit(ABORT);
	}
	public void paint(Graphics g) {
 
		super.paint(g);  //不新增這個的話舊的小球不會被擦除
		Graphics2D g2d = (Graphics2D) g; 
		ball.paint(g2d); //呼叫ball類中的paint方法
		racquet.paint(g2d);
		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 50));
		g2d.drawString(String.valueOf(score), 20, 120);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	}
}