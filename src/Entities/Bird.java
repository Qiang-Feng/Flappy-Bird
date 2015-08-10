package Entities;

import Framework.*;
import Window.Window;
import Framework.Input.Keyboard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Bird extends Entity
{
	private float gravity = 0.7F;
	public float xVel = 2;
	public float yVel = 0;
	private float yTerminalVelocity = 15;

	private boolean noJump = false;
	private boolean ended = false;
	private boolean pause = false;
	private boolean continueUp = true;

	private int currentImg = 0;

	private Keyboard keyboard;
	private BufferedImage[] images;

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
		if (!pause)
		{
			if ((yVel < yTerminalVelocity) && !ended && Window.started)
			{
				yVel += gravity;
			}

			x += xVel;
			y += yVel;

			if (keyboard.isKeyDown() && !ended && !noJump)
			{
				if (keyboard.getKeyCode() == KeyEvent.VK_ENTER)
				{
					if (continueUp)
					{
						continueUp = false;
						jump();
					}
				}
			}

			if (keyboard.isKeyDown() && (keyboard.getKeyCode() == KeyEvent.VK_UP || keyboard.getKeyCode() == KeyEvent.VK_SPACE) && !ended && !noJump)
			{
				if (continueUp)
				{
					continueUp = false;
					jump();
				}
			}
			else if (keyboard.isKeyDown() && !ended && !noJump)
			{
				if (keyboard.getKeyCode() == KeyEvent.VK_ENTER)
				{
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
						noJump = true;

						y = (int) entity.getBounds().getY() - this.height;

						if (Math.abs(yVel) < 4)
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
						noJump = true;

						if (Math.abs(yVel) < 4)
						{
							ended = true;

							xVel = 0;
							yVel = 0;
						}

						switch (Physics.collisionType(this, entity))
						{
							case Physics.BOTTOM:
								y = (int) entity.getBounds().getY() - this.height;
								yVel = Physics.calculateReboundVelocity(this, entity, false);
								break;

							case Physics.TOP:
								y = (int) entity.getBounds().getY() + Window.HEIGHT - this.height * 3;
								yVel = Physics.calculateReboundVelocity(this, entity, false);
								break;

							case Physics.LEFT:
								xVel = Physics.calculateReboundVelocity(this, entity, true);
								break;

							default:
								System.out.println("Error!");
						}
					}
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

		if (!ended && !noJump)
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
