package Entities;

import Framework.BufferedImageLoader;
import Framework.Entity;
import Framework.EntityID;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Pipe extends Entity
{
	private BufferedImage tubeImage;

	public Pipe(int x, int y, int width, int height, EntityID id)
	{
		super(x, y - (height / 2), width, height, id);
		tubeImage = new BufferedImageLoader().loadImage("/pipe.png");
	}

	@Override
	public void update(ArrayList<Entity> entities)
	{

	}

	@Override
	public void render(Graphics2D g)
	{
		g.drawImage(tubeImage, null, x, y);
	}
}
