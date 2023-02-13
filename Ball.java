package ballgame_frame;

import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball extends JPanel{
	private static final int ballsize=60;
	int x=0; //小球的預設位置
	int y=0;
	int incx=1; //這是小球位置要移動的方向
	int incy=1;
	private Ballgame_frame tennis;
	public Ball(Ballgame_frame tennis)
	{
		this.tennis=tennis;
	}
	@SuppressWarnings("static-access")
	void moveBall() //這個方法就是不斷更新小球的位置
	{
	 
		if(x+incx>tennis.getWidth()-ballsize) //如果小球移動後的位置超出窗體範圍的話,移動方向就是一直-1;因為要考慮球的大小所以-60
			incx=-1;
		if(x+incx<0) //同理
			incx=1;
		if(y+incy>tennis.getHeight()-ballsize)
			tennis.gameOver();
		if(y+incy<0)
			incy=1;
		if (collision()) //如果檢測到碰撞就改變方向
		{
			incy = -1;
			y = tennis.racquet.getTopY() - ballsize;//這個是矯正球的位置,為了防止碰撞導致的就球拍和小球重疊
			tennis.score++;
		}
		x+=incx;
		y+=incy;
	}
	public void paint(Graphics2D g)
	{
		g.fillOval(x, y, ballsize, ballsize); 
	}
	public Rectangle getBounds()//返回Rectangle型別的小球
	{
		return new Rectangle(x,y,ballsize,ballsize);
	}
	private boolean collision()  //這個是檢測碰撞
	{
		return tennis.racquet.getBounds().intersects(getBounds()); //用intersects方法判斷小球是否和球拍相交
	}
}