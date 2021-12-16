package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;


public class Ball {

	public double x,y;
	public int height,width;
	
	public double dx,dy;
	public double speed = 1.7;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 3;
		this.height = 3;
		
		int angle = new Random().nextInt(120 - 45) + 45 + 1;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		
		//colisões de paredes laterais
		if(x+(dx*speed) + width >= Game.WIDTH) {
			dx*=-1;
		}else if (x+(dx*speed) < 0) {
			dx*=-1;
		}
		
		//pontos
		if(y >= Game.HEIGHT) {
			//ponto da maquina
			System.out.println("ponto da maquina");
			new Game();
			return;
		}else if(y <= 0) {
			//ponto do jogador
			System.out.println("ponto do jogador");
			new Game();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),width, height);
		
		Rectangle boundsPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.width,Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy > 0) {
				dy*=-1;
			}
		}else if(bounds.intersects(boundsEnemy)) {
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dy < 0) {
				dy*=-1;
			}
		}
		
		x+=dx*speed;
		y+=dy*speed;
		
		if(bounds.intersects(boundsPlayer) && bounds.intersects(boundsEnemy) ) {
			for(x = 10; x>1000; x++)
			x+=dx*speed++;
			for(y = 10; y>1000; y++)
			y+=dy*speed++;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
