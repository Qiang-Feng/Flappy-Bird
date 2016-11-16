package Framework;

import Entities.Bird;

public class Physics
{
    public static final int TOP = 0;
    public static final int LEFT = 1;
    public static final int BOTTOM = 2;
    public static final int RIGHT = 3;

    public static boolean collision(Entity a, Entity b)
    {
        return (a.getBounds().intersects(b.getBounds()) || a.getBounds().contains(b.getBounds()));
    }

    public static int collisionType(Entity a, Entity b)
    {
        // Using answer from http://gamedev.stackexchange.com/a/29796
        float wy = (a.width + b.width) * ((a.y + (a.height / 2)) - (b.y + (b.height / 2)));
        float hx = (a.height + b.height) * ((a.x + (a.width / 2)) - (b.x + (b.width / 2)));

        if (wy > hx)
        {
            return (wy > -hx) ? TOP : LEFT;
        }

        return (wy > -hx) ? RIGHT : BOTTOM;
    }

    public static float calculateReboundVelocity(Bird bird, Entity surface, boolean horizontal)
    {
        return (horizontal) ? (-surface.e * bird.xVel) : (-surface.e * bird.yVel);
    }
}
