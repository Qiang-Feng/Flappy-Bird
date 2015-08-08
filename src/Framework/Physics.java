package Framework;

import Entities.Bird;

public class Physics
{
	public static boolean collision(Entity a, Entity b)
	{
		return (a.getBounds().intersects(b.getBounds()) || a.getBounds().contains(b.getBounds()));
	}

	public static float calculateReboundVelocity(Bird bird, Entity surface, boolean horizontal)
	{
		if (horizontal)
		{
			return (-surface.e * bird.xVel);
		}

		return (-surface.e * bird.yVel);
	}
}
