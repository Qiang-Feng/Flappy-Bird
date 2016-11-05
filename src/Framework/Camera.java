package Framework;

import Window.Window;

public class Camera
{
    private int x;
    private int y;

    private boolean stopped = false;

    public Camera(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void update(Entity bird)
    {
        if (!stopped)
        {
            x = (Window.WIDTH / 2) - (bird.width / 2) - bird.x;
        }
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public float getX()
    {
        return x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public float getY()
    {
        return y;
    }

    public void stop()
    {
        stopped = true;
    }
}
