import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Painter extends JPanel implements Observer{
	private Graphics2D g;
	
	public Painter() {
		
	}
	
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        g = (Graphics2D) graphics;
        
        // Ensures that it will run smoothly on Linux.
        if (System.getProperty("os.name").equals("Linux")) {
            Toolkit.getDefaultToolkit().sync();
        }

        setBackground(Properties.backgroundColor);
    }
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
		paintSnake( Engine.getEngine().getSnake() );
		paintFood(Engine.getEngine().getFoodX(), Engine.getEngine().getFoodY());

        if (Properties.getTheme() == Properties.Theme.Rainbow) Properties.changeColor();
        
		repaint();
	}

    private void paintSnake (Snake snake) {
        int x, y;
        int corner = Properties.SQUARE_SIZE / 3;

        for (Square sq : snake) {

            x = sq.getX() * Properties.SQUARE_SIZE;
            y = sq.getY() * Properties.SQUARE_SIZE;

            g.setColor(Properties.snakeColor);
            g.fillRoundRect(x + 1, y + 1, Properties.SQUARE_SIZE - 2,
                    Properties.SQUARE_SIZE - 2, corner, corner);

        }
    }

    private void paintFood (int _x, int _y) {
        int x = _x * Properties.SQUARE_SIZE;
        int y = _y * Properties.SQUARE_SIZE;
        int corner = Properties.SQUARE_SIZE / 3;

        g.setColor(Properties.foodColor);
        g.fillRoundRect(x + 1, y + 1, Properties.SQUARE_SIZE - 2,
                Properties.SQUARE_SIZE - 2, corner, corner);
    }

}
