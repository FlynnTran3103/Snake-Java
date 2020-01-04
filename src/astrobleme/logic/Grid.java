package astrobleme.logic;

public class Grid {
    public void update() {
        if (food.getPoint().equals(snake.getHead())) {
            snake.extend();
            food.setPoint(getRandomPoint());
        } else {
            snake.move();
        }
    }

    public boolean getSnake() {
    }
}
