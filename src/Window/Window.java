package Window;

import Entities.Bird;
import Framework.*;
import Framework.Input.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Window extends Canvas
{
	private final String TITLE = "Flappy Bird";
	public static final int WIDTH = 640;
	public static final int HEIGHT = 450;

	private JFrame frame;
	private BufferStrategy bs;
	private Graphics2D g;

	private Keyboard keyboard;

	private World world;
	private Camera camera;

	public static boolean started = false;

	public Window()
	{
		setSize(new Dimension(WIDTH, HEIGHT));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		frame = new JFrame(TITLE);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.pack();

		keyboard = new Keyboard();

		createBufferStrategy(3);
		addKeyListener(keyboard);
		requestFocus();

		camera = new Camera(0, 0);
		world = new World();
		world.addEntity(new Bird(WIDTH / 2 - 25, HEIGHT / 2 - 20, 50, 35, EntityID.Bird, keyboard));
	}

	private void update()
	{
		world.update();

		if (keyboard.isKeyDown())
		{
			if (keyboard.getKeyCode() == KeyEvent.VK_ESCAPE)
			{
				System.exit(0);
			}

			if (keyboard.getKeyCode() == KeyEvent.VK_ENTER && !started)
			{
				started = true;
				world.createWorld(g);
				world.startGame();
			}
		}

		for (Entity entity : world.entities)
		{
			if (entity.getID() == EntityID.Bird)
			{
				camera.update(entity);
			}
		}
	}

	private void render()
	{
		bs = getBufferStrategy();
		g = (Graphics2D) bs.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.translate(camera.getX(), camera.getY());

		world.render(g, camera);

		if (!started)
		{
			g.setFont(new Font("Arial", Font.PLAIN, 40));

			g.setColor(new Color(50, 50, 50));
			g.drawString("Press enter to start ...", -camera.getX() + 140 - 2, 150 + 2);
			g.setColor(Color.WHITE);
			g.drawString("Press enter to start ...", -camera.getX() + 140, 150);
		}

		g.translate(camera.getX(), camera.getY());

		g.dispose();
		bs.show();
	}

	public void start()
	{
		final double nanoSecond = 1000000000 / 60;
		double delta = 0;

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();

		int frames = 0;
		int updates = 0;

		while (true)
		{
			long nowTime = System.nanoTime();
			delta += (nowTime - lastTime) / nanoSecond;
			lastTime = nowTime;

			while (delta >= 1)
			{
				update();
				updates += 1;
				delta -= 1;
			}

			render();
			frames += 1;

			if ((System.currentTimeMillis() - timer) > 1000)
			{
				System.out.println("Updates: " + updates + " FPS: " + frames);

				timer += 1000;
				frames = 0;
				updates = 0;
			}
		}
	}

	public static void main(String[] args)
	{
		new Window().start();
	}
}
