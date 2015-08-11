package Entities;

import Framework.Entity;
import Framework.EntityID;

import java.awt.*;
import java.util.ArrayList;

public class Checkpoint extends Entity
{
    public Checkpoint(int x, int y, int width, int height, EntityID id)
    {
        super(x, y, width, height, id);
    }

    @Override
    public void render(Graphics2D g)
    {

    }

    @Override
    public void update(ArrayList<Entity> entities)
    {

    }
}
