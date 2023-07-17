package academiadecodigo.org;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Line;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Window {
    public static final int PADDING = 10;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private Rectangle background;
    private Line middleLine;
    public Window() {
        background = new Rectangle(PADDING,PADDING,WIDTH,HEIGHT);
        background.setColor(Color.BLACK);

        middleLine = new Line(WIDTH/2+PADDING,0,WIDTH/2+PADDING,HEIGHT+PADDING);
        middleLine.setColor(Color.WHITE);

    }

    public void init()
    {
        background.fill();
        middleLine.draw();
    }

}
