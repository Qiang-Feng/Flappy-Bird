package Framework;

import Entities.Bottom;
import Entities.Pipe;
import Window.Window;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class World
{
	public ArrayList<Entity> entities = new ArrayList<Entity>();

	private int xPos = 0;
	private int xPosLive = 0;

	private BufferedImageLoader loader;
	private BufferedImage background;
	private BufferedImage ground;

	private Random random;

	public World()
	{
		random = new Random();
		loader = new BufferedImageLoader();
		background = loader.loadImage("/background.png");
		ground = loader.loadImage("/ground.png");

		addEntity(new Bottom(xPosLive, Window.HEIGHT, 350, 112, EntityID.Bottom));
	}

	public void startGame()
	{
		addEntity(new Pipe(xPosLive + Window.WIDTH, Window.HEIGHT, 60, 345, EntityID.Pipe, false));
		addEntity(new Pipe(xPosLive + Window.WIDTH, -50, 60, 345, EntityID.Pipe, true));
	}

	public void addPipe(int position)
	{
		addEntity(new Pipe(position, Window.HEIGHT, 60, 345, EntityID.Pipe, false));
		addEntity(new Pipe(position, -50, 60, 345, EntityID.Pipe, true));
	}

	public void render(Graphics2D g, Camera camera)
	{
		createWorld(g);

		for (Entity entity : entities)
		{
			entity.render(g);
		}

		if (xPos < Math.abs(camera.getX()))
		{
			xPos += 350;
		}

		xPosLive = (int) Math.abs(camera.getX());
	}

	public void update()
	{
		for (Entity entity : entities)
		{
			entity.update(entities);
		}
	}

	public void addEntity(Entity entity)
	{
		entities.add(entity);
	}

	public void removeEntity(Entity entity)
	{
		entities.remove(entity);
	}

	public void createWorld(Graphics2D g)
	{
		g.drawImage(background, null, xPos - 700, 0);
		g.drawImage(background, null, xPos - 350, 0);
		g.drawImage(background, null, xPos, 0);
		g.drawImage(background, null, xPos + 350, 0);
		g.drawImage(background, null, xPos + 700, 0);

		g.drawImage(ground, null, xPos - 700, Window.HEIGHT - (ground.getHeight() / 2));
		g.drawImage(ground, null, xPos - 350, Window.HEIGHT - (ground.getHeight() / 2));
		g.drawImage(ground, null, xPos, Window.HEIGHT - (ground.getHeight() / 2));
		g.drawImage(ground, null, xPos + 350, Window.HEIGHT - (ground.getHeight() / 2));
		g.drawImage(ground, null, xPos + 700, Window.HEIGHT - (ground.getHeight() / 2));

		for (Entity entity : entities)
		{
			if (entity.getID() == EntityID.Bottom)
			{
				entity.x = xPosLive;
			}
		}
	}
}
