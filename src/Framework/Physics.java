package Framework;

public class Physics
{
	public static boolean collision(Entity a, Entity b)
	{
		return (a.getBounds().intersects(b.getBounds()) || a.getBounds().contains(b.getBounds()));
	}
}
