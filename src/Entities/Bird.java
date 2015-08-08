package Entities;

import Framework.*;
import Framework.Input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bird extends Entity
{
	private float gravity = 0.7F;
	public float xVel = 2;
	public float yVel = 0;
	private float yTerminalVelocity = 15;

	private boolean started = false;
	private boolean ended = false;
	private boolean continueUp = true;

	private int currentImg = 0;

	private Keyboard keyboard;
	private BufferedImage[] images;
	private AffineTransform transform;

	private long lastTime;

	public Bird(int x, int y, int width, int height, EntityID id, Keyboard keyboard)
	{
		super(x, y, width, height, id);

		this.keyboard = keyboard;

		images = new BufferedImage[3];
		images[0] = new BufferedImageLoader().loadImage("/birdFirst.png");
		images[1] = new BufferedImageLoader().loadImage("/birdSecond.png");
		images[2] = new BufferedImageLoader().loadImage("/birdThird.png");

		lastTime = System.currentTimeMillis();
	}

	@Override
	public void update(ArrayList<Entity> entities)
	{
		if ((yVel < yTerminalVelocity) && !ended && started)
		{
			yVel += gravity;
		}

		x += xVel;
		y += yVel;

		if (keyboard.isKeyDown() && !ended)
		{
			if (keyboard.getKeyCode() == KeyEvent.VK_ENTER)
			{
				started = true;
				if (continueUp)
				{
					continueUp = false;
					jump();
				}
			}
		}

		if (keyboard.isKeyDown() && (keyboard.getKeyCode() == KeyEvent.VK_UP || keyboard.getKeyCode() == KeyEvent.VK_SPACE) && !ended && started)
		{
			if (continueUp)
			{
				continueUp = false;
				jump();
			}
		}
		else if (keyboard.isKeyDown() && !ended)
		{
			if (keyboard.getKeyCode() == KeyEvent.VK_ENTER)
			{
				started = true;
				if (continueUp)
				{
					continueUp = false;
					jump();
				}
			}
		}
		else
		{
			continueUp = true;
		}

		for (Entity entity : entities)
		{
			if (entity.getID() == EntityID.Bottom)
			{
				if (Physics.collision(this, entity))
				{
					y = (int) entity.getBounds().getY() - this.height;

					if (Math.abs(yVel) < 2.5)
					{
						ended = true;

						xVel = 0;
						yVel = 0;
					}
					else
					{
						yVel = Physics.calculateReboundVelocity(this, entity, false);
					}
				}
			}

			if (entity.getID() == EntityID.Pipe)
			{
				if (Physics.collision(this, entity))
				{
					ended = true;

					yVel = 10;
					xVel = 0;
				}
			}
		}
	}

	private void jump()
	{
		yVel = -10;
	}

	@Override
	public void render(Graphics2D g)
	{
		final int delay = 150;

		if (!ended)
		{
			if ((System.currentTimeMillis() - lastTime) > delay)
			{
				if (currentImg < 2)
				{
					currentImg += 1;
				}
				else
				{
					currentImg = 0;
				}

				lastTime += delay;
			}
		}
		else
		{
			currentImg = 0;
		}

		g.drawImage(images[currentImg], null, x, y);
	}
}
