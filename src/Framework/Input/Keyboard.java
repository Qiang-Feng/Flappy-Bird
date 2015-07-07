package Framework.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{
	private boolean keyDown = false;

	private char keyChar;
	private int keyCode;

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		keyDown = true;
		keyChar = e.getKeyChar();
		keyCode = e.getKeyCode();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		keyDown = false;
	}

	public boolean isKeyDown()
	{
		return keyDown;
	}

	public char getKeyChar()
	{
		return keyChar;
	}

	public int getKeyCode()
	{
		return keyCode;
	}
}
