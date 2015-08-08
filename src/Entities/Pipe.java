package Entities;

import Framework.BufferedImageLoader;
import Framework.Entity;
import Framework.EntityID;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Pipe extends Entity
{
	private BufferedImage tubeImage;
	private static float e = 0.5F;

	public Pipe(int x, int y, int width, int height, EntityID id, boolean top)
	{
		super(x, y - (height / 2), width, height, id, e);
		tubeImage = new BufferedImageLoader().loadImage("/pipe.png");

		if (top)
		{
			AffineTransform transform = AffineTransform.getScaleInstance(-1, -1);
			transform.translate(-tubeImage.getWidth(), -tubeImage.getHeight());
			AffineTransformOp image = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

			tubeImage = image.filter(tubeImage, null);
		}
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
