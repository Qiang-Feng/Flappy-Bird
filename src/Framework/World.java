package Framework;

import Entities.Bottom;
import Entities.Checkpoint;
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
    private int xPosLive = 0;

    private BufferedImageLoader loader;
    private BufferedImage background;
    private BufferedImage ground;

    private Random random;

    public World()
    {
        random = new Random();
        loader = new BufferedImageLoader();

        background = loader.loadImage("/background.png");
        ground = loader.loadImage("/ground.png");

        addEntity(new Bottom(xPosLive, Window.HEIGHT, 350, 112, EntityID.Bottom));
    }

    public void addPipe(int position)
    {
        int variance = random.nextInt(101);

        addEntity(new Pipe(position, Window.HEIGHT + variance - 25, 60, 345, EntityID.Pipe, false));
        addEntity(new Pipe(position, variance - 75, 60, 345, EntityID.Pipe, true));
        addEntity(new Checkpoint(position + 55, Window.HEIGHT + variance - 352, 4, 154, EntityID.Checkpoint));
    }

    public void render(Graphics2D g, Camera camera)
    {
        createWorld(g);

        for (Entity entity : entities)
        {
            entity.render(g);
        }

        xPosLive = (int) Math.abs(camera.getX());

        if (xPos < Math.abs(camera.getX()))
        {
            xPos += 350;

            if (Window.started)
            {
                addPipe(xPosLive + Window.WIDTH);
            }
        }
    }

    public void update()
    {
        for (Entity entity : entities)
        {
            entity.update(entities);
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

        g.drawImage(ground, null, xPos - 700, Window.HEIGHT - (ground.getHeight() / 2));
        g.drawImage(ground, null, xPos - 350, Window.HEIGHT - (ground.getHeight() / 2));
        g.drawImage(ground, null, xPos, Window.HEIGHT - (ground.getHeight() / 2));
        g.drawImage(ground, null, xPos + 350, Window.HEIGHT - (ground.getHeight() / 2));
        g.drawImage(ground, null, xPos + 700, Window.HEIGHT - (ground.getHeight() / 2));

        for (Entity entity : entities)
        {
            if (entity.getID() == EntityID.Bottom)
            {
                entity.x = xPosLive;
            }
        }
    }
}
