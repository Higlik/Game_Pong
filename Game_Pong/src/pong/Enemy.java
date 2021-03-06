package pong;

import java.awt.Color;
import java.awt.Graphics;


public class Enemy {

	public static Enemy enemy;
	public double x,y;
	public int height,width;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 20;
		this.height = 10;
	}
	
	public void tick() {
		x+= (Game.ball.x - x - 6);
		
		if(x+width > Game.WIDTH) {
			x = Game.WIDTH - width;
		}else if(x < 0) {
			x = 0;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
