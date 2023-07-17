package academiadecodigo.org;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Player implements Runnable {


    private int x;
    private int y;
    private final int HEIGHT = 80;
    private final int WIDTH = 15;
    private Rectangle rectangle;
    private final int speed = 10;
    private Direction direction;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        direction = Direction.STOPPED;
        rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        rectangle.setColor(Color.WHITE);
    }
    private void moveUp()
    {
        if(y>Window.PADDING*2) {
            rectangle.translate(0, -speed);
            y -= speed;
        }
    }
    private void moveDown()
    {
        if(y+HEIGHT<Window.HEIGHT) {
            rectangle.translate(0, speed);
            y += speed;
        }
    }

    public int getMiddleY()
    {
        return (y+getFarY())/2;
    }
    public int firstQuarter()
    {
        return getMiddleY()/2;
    }

    public int getFarX()
    {
        return x+15;
    }
    public int getFarY()
    {
        return y+80;
    }
    private void move(Direction direction)
    {
        switch (direction)
        {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case STOPPED:
                break;
            default:
                System.out.println("Something went wrong with movement");
        }
    }
    public void setDirection(Direction newDirection)
    {
        direction = newDirection;
    }
    public void init()
    {
        rectangle.fill();
    }

    @Override
    public void run() {
        while (true) {
            move(direction);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e)
            {
                System.out.println("ThreadPlayerError");
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
