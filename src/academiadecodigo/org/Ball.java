package academiadecodigo.org;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

public class Ball implements Runnable {

    private int x;
    private int y;

    private final int diameter = 20;
    private Ellipse ball;
    private Direction directionX;
    private Direction directionY;
    private Direction biDirection;
    private double speedX;
    private double speedY;


    public Ball() {
        directionX = Direction.RIGHT;
        directionY = Direction.STOPPED;
        biDirection = Direction.STOPPED;
        speedX = 1;
        speedY = 0;
        x = ((Window.WIDTH-10)/2);
        y = ((Window.HEIGHT-10)/2);
        System.out.println(x+" "+y);
        ball = new Ellipse(x,y,diameter,diameter);
        ball.setColor(Color.WHITE);
    }
    public void init()
    {
        ball.fill();
    }
    public int getFarX()
    {
        return x+diameter;
    }
    public int getFarY()
    {
        return y+diameter;
    }

    public void moveRight()
    {
        speedX = 1;
        speedY = 0;
        x+=speedX;
        ball.translate(speedX,speedY);
        directionX = Direction.RIGHT;
    }
    public void moveLeft()
    {
        speedX = 1;
        speedY = 0;
        x-=speedX;
        ball.translate(-speedX,speedY);
        directionX = Direction.LEFT;
    }
    public void moveUp()
    {
        speedX = 0;
        speedY = 1;
        y-=speedY;
        ball.translate(speedX,-speedY);
        directionY = Direction.UP;
    }
    public void moveDown()
    {
        speedX = 0;
        speedY = 1;
        y+=speedY;
        ball.translate(speedX,speedY);
        directionY = Direction.DOWN;
    }
    public void moveUpLeft(double speedX,double speedY)
    {
        y-=0.5;
        x-=0.5;
        ball.translate(-speedX,-speedY);
        directionY = Direction.UP;
        directionX = Direction.LEFT;
    }
    public void moveUpRight(double speedX,double speedY)
    {
        y-=0.5;
        x+=0.5;
        ball.translate(speedX,-speedY);
        directionY = Direction.UP;
        directionX = Direction.RIGHT;
    }
    public void moveDownLeft(double speedX,double speedY)
    {
        y+=0.5;
        x-=0.5;
        ball.translate(-speedX,speedY);
        directionY = Direction.DOWN;
        directionX = Direction.LEFT;
    }
    public void moveDownRight(double speedX,double speedY)
    {
        y+=0.5;
        x+=0.5;
        ball.translate(speedX,speedY);
        directionY = Direction.DOWN;
        directionX = Direction.RIGHT;
    }
    private void move(Direction directionX,Direction directionY,Direction biDirection)
    {
        switch (directionX)
        {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
        }
        switch (directionY)
        {
            case DOWN -> moveDown();
            case UP -> moveUp();
        }
        switch (biDirection)
        {

        }
    }

    @Override
    public void run() {
        while (true)
        {
            move(directionX,directionY,biDirection);
            try {
                Thread.sleep(2);
            }catch (InterruptedException e)
            {
                System.out.println("Ball thread Interrupted");
            }
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void reset()
    {
        ball.delete();
        x = ((Window.WIDTH-10)/2);
        y = ((Window.HEIGHT-10)/2);
        ball = new Ellipse(x,y,diameter,diameter);
        ball.setColor(Color.WHITE);
        ball.fill();
        directionX = Direction.RIGHT;
    }
    public void checkCollision(Player player,Player enemy)
    {
        if((directionX == Direction.RIGHT && getFarX() >= enemy.getX()) && (getFarY()>enemy.getY()&&y<enemy.getFarY())) {
            directionX = Direction.LEFT;

            if((getFarY()>enemy.getY()&&getFarY()<enemy.getMiddleY()))
            {
                directionY = Direction.UP;
                biDirection = Direction.UP_LEFT;
            }

            if(y<enemy.getFarY()&& getFarY()>enemy.getMiddleY())
            {
                directionY = Direction.DOWN;
                biDirection = Direction.UP_RIGHT;
            }

        }
        if(directionX == Direction.LEFT && x<=player.getFarX()&& (getFarY()>player.getY()&&y<player.getFarY()))
        {
            directionX = Direction.RIGHT;

            if((getFarY()>player.getY()&&getFarY()<=player.getMiddleY()))
            {
                directionY = Direction.UP;
            }
            if(y<player.getFarY()&& getFarY()>player.getMiddleY())
            {
                directionY = Direction.DOWN;
            }
        }
        if(directionY == Direction.DOWN && getFarY()>=Window.HEIGHT-1)
    {

        directionY = Direction.UP;
    }
        if(directionY == Direction.UP && y<=1)
        {
            directionY = Direction.DOWN;
        }


        if(x>enemy.getFarX()-5 || getFarX()< player.getX() )
        {
            reset();
        }

    }
}
