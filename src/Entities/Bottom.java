package Entities;

import Framework.BufferedImageLoader;
import Framework.Entity;
import Framework.EntityID;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bottom extends Entity
{
	private BufferedImage ground;

	public Bottom(int x, int y, int width, int height, EntityID id)
	{
		super(x, y - (height / 2), width, height, id);
		ground = new BufferedImageLoader().loadImage("/ground.png");
	}

	@Override
	public void update(ArrayList<Entity> entities)
	{

	}

	@Override
	public void render(Graphics2D g)
	{
		g.drawImage(ground, null, x - 700, y);
		g.drawImage(ground, null, x - 350, y);
		g.drawImage(ground, null, x, y);
		g.drawImage(ground, null, x + 350, y);
		g.drawImage(ground, null, x + 700, y);
	}
}
