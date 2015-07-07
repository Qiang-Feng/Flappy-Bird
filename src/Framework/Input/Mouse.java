package Framework.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener
{
	private boolean buttonDown = false;

	private int x;
	private int y;

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		buttonDown = true;

		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		buttonDown = false;
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

	public boolean isButtonDown()
	{
		return buttonDown;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
}
