package pong;
import java.awt.Graphics;
import java.awt.Color;

public class Player {

	public boolean right,left;

	public int x,y;
	
	public int height,width;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 20;
		this.height = 10;
	}
	
	
	
	public void tick() {
		if(right) {
			x++;
		}else if(left) {
			x--;
		}
		
		if(x+width > Game.WIDTH) {
			x = Game.WIDTH - width;
		}else if(x < 0) {
			x = 0;
		}
		
	}
	
	
	
	
	public void render() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	
}
