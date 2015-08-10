package Entities;

import Framework.Entity;
import Framework.EntityID;

import java.awt.*;
import java.util.ArrayList;

public class Bottom extends Entity
{
	private static float e = 0.5F;

	public Bottom(int x, int y, int width, int height, EntityID id)
	{
		super(x, y - (height / 2), width, height, id, e);
	}

	@Override
	public void update(ArrayList<Entity> entities)
	{

	}

	@Override
	public void render(Graphics2D g)
	{

	}
}
