package Framework;

import java.awt.*;
import java.util.ArrayList;

public abstract class Entity
{
	protected EntityID id;

	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public Entity(int x, int y, int width, int height, EntityID id)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
	}

	public EntityID getID()
	{
		return id;
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x, y, width, height);
	}

	public abstract void update(ArrayList<Entity> entities);
	public abstract void render(Graphics2D g);
}
