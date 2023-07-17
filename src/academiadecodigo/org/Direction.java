package academiadecodigo.org;

import java.util.Optional;

public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN,
        STOPPED,
        UP_LEFT,
        UP_RIGHT,
        DOWN_LEFT,
        DOWN_RIGHT;

        public static Direction invertDirection(Direction direction)
        {
                switch (direction)
                {
                        case RIGHT:
                                return LEFT;
                        case LEFT:
                                return RIGHT;
                        case UP:
                                return DOWN;
                        case DOWN:
                                return UP;
                }
                Optional<Direction>notDirection = Optional.ofNullable(direction);
                return notDirection.get();
        }

}
