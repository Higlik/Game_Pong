package pong;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy; 
import java.awt.image.BufferedImage;
import java.awt.Color;


import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends Canvas implements Runnable,KeyListener{
	
	private static final long serialVersionUID = 1L;
	public final static int WIDTH =   130; //largura
	public final static int HEIGHT = 170; //Altura
	public final int SCALE = 3; //Mutiplica ambos(identico a dar zoom em uma tela)
	
	public BufferedImage layer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addKeyListener(this);
		player = new Player(70,HEIGHT-10);
		enemy = new Enemy(70,0);
		ball = new Ball(70,HEIGHT/2 - 1);
	}
	

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false); // A tela não pode ser redimensionada
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Encarra o programa quando a janela for fechada
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
	
		
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0,0, WIDTH,HEIGHT);	
		player.render(g);
		enemy.render(g);
		ball.render(g);
	
		g = bs.getDrawGraphics();
		g.drawImage(layer,0,0, WIDTH*SCALE,HEIGHT*SCALE,null);
		
		bs.show();
	}
	
	@Override
	public void run() {
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
	
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
	}

	
	
}
