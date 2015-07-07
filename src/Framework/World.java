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

	private BufferedImage background;

	private Random random;

	public World()
	{
		random = new Random();
		background = new BufferedImageLoader().loadImage("/background.png");

		addEntity(new Bottom(xPos, Window.HEIGHT, 350, 112, EntityID.Bottom));

		addEntity(new Pipe(random.nextInt(700 - 350) + 350, Window.HEIGHT, 60, 345, EntityID.Pipe));
		addEntity(new Pipe(random.nextInt(1200 - 1000) + 1000, Window.HEIGHT, 60, 345, EntityID.Pipe));
	}

	public void render(Graphics2D g, Camera camera)
	{
		createWorld(g);

		for (int i = 0; i < entities.size(); i++)
		{
			entities.get(i).render(g);
		}

		if (xPos < Math.abs(camera.getX()))
		{
			xPos += 350;
		}
	}

	public void update()
	{
		for (int i = 0; i < entities.size(); i++)
		{
			entities.get(i).update(entities);
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

		for (int i = 0; i < entities.size(); i++)
		{
			if (entities.get(i).getID() == EntityID.Bottom)
			{
				entities.get(i).x = xPos;
			}
		}
	}
}
