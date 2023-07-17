package academiadecodigo.org;

import org.academiadecodigo.simplegraphics.graphics.Canvas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {

    private Window window;
    private Player player;
    private Player enemy;

    private Ball ball;

    private Thread tBall;
    private Thread tEnemy;
    private Thread tPlayer;
    public Game() {
        window = new Window();

        player = new Player(Window.PADDING + 20,Window.PADDING + 20);
        enemy = new Player(Window.WIDTH-(20),Window.PADDING + 20);

        ball = new Ball();

        tBall = new Thread(ball);
        tPlayer = new Thread(player);
        tEnemy = new Thread(enemy);
        Canvas.getInstance().addKeyListener(this);
    }

    public void start() {
        window.init();
        player.init();
        enemy.init();
        ball.init();
        tPlayer.start();
        tEnemy.start();
        tBall.start();
        while (true)
        {
            gameLogic();
            try {
                Thread.sleep(2);
            }catch (InterruptedException e)
            {
                System.out.println("Main thread interrupted");
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            player.setDirection(Direction.UP);
        }
        if(e.getKeyCode() == KeyEvent.VK_S)
        {
            player.setDirection(Direction.DOWN);
        }if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            enemy.setDirection(Direction.UP);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            enemy.setDirection(Direction.DOWN);
        }
    }
    public void gameLogic()
    {
        ball.checkCollision(player,enemy);
    }

    @Override
    public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_W)
            {
                player.setDirection(Direction.STOPPED);
            }
            if(e.getKeyCode() == KeyEvent.VK_S)
            {
                player.setDirection(Direction.STOPPED);
            }if(e.getKeyCode() == KeyEvent.VK_UP)
            {
                enemy.setDirection(Direction.STOPPED);
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                enemy.setDirection(Direction.STOPPED);
            }

    }
}