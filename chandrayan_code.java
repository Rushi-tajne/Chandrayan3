import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Chandrayaan 3 Lunar Craft Control!");

        int initialX = getUserInput("Enter the initial X position: ", scanner);
        int initialY = getUserInput("Enter the initial Y position: ", scanner);
        int initialZ = getUserInput("Enter the initial Z position: ", scanner);

        char initialDirection = getInitialDirection(scanner);

        Spacecraft chandrayaan3 = new Chandrayaan3Spacecraft(initialX, initialY, initialZ, initialDirection);

        String inputCommands = getInputString("Enter the commands (e.g., f, r, u, b, l) as a single string: ", scanner);

        List<Character> commands = convertStringToCharacterList(inputCommands);

        // Execute the commands
        chandrayaan3.executeCommands(commands);

        // Print the final position and direction
        chandrayaan3.printPositionAndDirection();

        scanner.close();
    }

    private static int getUserInput(String message, Scanner scanner) {
        System.out.print(message);
        return scanner.nextInt();
    }

    private static char getInitialDirection(Scanner scanner) {
        return getInputString("Enter the initial direction (N, S, E, W, U, D): ", scanner).charAt(0);
    }

    private static String getInputString(String message, Scanner scanner) {
        System.out.print(message);
        return scanner.next();
    }

    private static List<Character> convertStringToCharacterList(String input) {
        List<Character> characterList = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characterList.add(c);
        }
        return characterList;
    }
}

interface Spacecraft {
    void move(char command);
    void turn(char command);
    void rotate(char command);
    void executeCommands(List<Character> commands);
    void printPositionAndDirection();
}

abstract class AbstractSpacecraft implements Spacecraft {
    protected int x, y, z;
    protected char direction;
    protected Stack<Character> st = new Stack<>();

    public AbstractSpacecraft(int initialX, int initialY, int initialZ, char initialDirection) {
        x = initialX;
        y = initialY;
        z = initialZ;
        direction = initialDirection;
    }

    public String getCurrentPosition() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public char getCurrentDirection() {
        return direction;
    }
}

class Chandrayaan3Spacecraft extends AbstractSpacecraft {
    public Chandrayaan3Spacecraft(int initialX, int initialY, int initialZ, char initialDirection) {
        super(initialX, initialY, initialZ, initialDirection);
    }

    @Override
    public void move(char command) {
        switch (command) {
            case 'f':
                moveForward();
                break;
            case 'b':
                moveBackward();
                break;
        }
    }

    @Override
    public void turn(char command) {
        switch (command) {
            case 'l':
                turnLeft();
                break;
            case 'r':
                turnRight();
                break;
        }
    }

    @Override
    public void rotate(char command) {
        switch (command) {
            case 'u':
                rotateUp();
                break;
            case 'd':
                rotateDown();
                break;
        }
    }

    @Override
    public void executeCommands(List<Character> commands) {
        for (char command : commands) {
            if (command == 'f' || command == 'b') {
                move(command);
            } else if (command == 'l' || command == 'r') {
                turn(command);
            } else if (command == 'u' || command == 'd') {
                rotate(command);
            }
        }
    }

    @Override
    public void printPositionAndDirection() {
        System.out.println("Final Position: " + getCurrentPosition());
        System.out.println("Final Direction: " + getCurrentDirection());
    }

    private void moveForward() {
        switch (direction) {
            case 'N':
                y++;
                break;
            case 'S':
                y--;
                break;
            case 'E':
                x++;
                break;
            case 'W':
                x--;
                break;
            case 'U':
                z++;
                break;
            case 'D':
                z--;
                break;
        }
    }

    private void moveBackward() {
        switch (direction) {
            case 'N':
                y--;
                break;
            case 'S':
                y++;
                break;
            case 'E':
                x--;
                break;
            case 'W':
                x++;
                break;
            case 'U':
                z--;
                break;
            case 'D':
                z++;
                break;
        }
    }

    private void turnLeft() {
        switch (direction) {
            case 'N':
                direction = 'W';
                st.push('W');
                break;
            case 'S':
                direction = 'E';
                st.push('E');
                break;
            case 'E':
                direction = 'N';
                st.push('N');
                break;
            case 'W':
                direction = 'S';
                st.push('S');
                break;
            case 'U':
                while (!st.isEmpty() && (st.peek() != 'N' && st.peek() != 'S' && st.peek() != 'W' && st.peek() != 'E')) {
                    st.pop();
                }
                if (!st.isEmpty()) {
                    direction = st.pop();
                }
                switch (direction) {
                    case 'N':
                        direction = 'W';
                        st.push('W');
                        break;
                    case 'S':
                        direction = 'E';
                        st.push('E');
                        break;
                    case 'E':
                        direction = 'N';
                        st.push('N');
                        break;
                    case 'W':
                        direction = 'S';
                        st.push('S');
                        break;
                }
                break;
            case 'D':
                while (!st.isEmpty() && (st.peek() != 'N' && st.peek() != 'S' && st.peek() != 'W' && st.peek() != 'E')) {
                    st.pop();
                }
                if (!st.isEmpty()) {
                    direction = st.pop();
                }
                switch (direction) {
                    case 'N':
                        direction = 'W';
                        st.push('W');
                        break;
                    case 'S':
                        direction = 'E';
                        st.push('E');
                        break;
                    case 'E':
                        direction = 'N';
                        st.push('N');
                        break;
                    case 'W':
                        direction = 'S';
                        st.push('S');
                        break;
                }
                break;
        }
    }

    private void turnRight() {
        switch (direction) {
            case 'N':
                direction = 'E';
                st.push('E');
                break;
            case 'S':
                direction = 'W';
                st.push('W');
                break;
            case 'E':
                direction = 'S';
                st.push('S');
                break;
            case 'W':
                direction = 'N';
                st.push('N');
                break;
            case 'U':
                while (!st.isEmpty() && (st.peek() != 'N' && st.peek() != 'S' && st.peek() != 'W' && st.peek() != 'E')) {
                    st.pop();
                }
                if (!st.isEmpty()) {
                    direction = st.pop();
                }
                switch (direction) {
                    case 'N':
                        direction = 'E';
                        st.push('E');
                        break;
                    case 'S':
                        direction = 'W';
                        st.push('W');
                        break;
                    case 'E':
                        direction = 'S';
                        st.push('S');
                        break;
                    case 'W':
                        direction = 'N';
                        st.push('N');
                        break;
                }
                break;
            case 'D':
                while (!st.isEmpty() && (st.peek() != 'N' && st.peek() != 'S' && st.peek() != 'W' && st.peek() != 'E')) {
                    st.pop();
                }
                if (!st.isEmpty()) {
                    direction = st.pop();
                }
                switch (direction) {
                    case 'N':
                        direction = 'E';
                        st.push('E');
                        break;
                    case 'S':
                        direction = 'W';
                        st.push('W');
                        break;
                    case 'E':
                        direction = 'S';
                        st.push('S');
                        break;
                    case 'W':
                        direction = 'N';
                        st.push('N');
                        break;
                }
                break;
        }
    }

    private void rotateUp() {
        if (direction != 'U') {
            direction = 'U';
        }
    }

    private void rotateDown() {
        if (direction != 'D') {
            direction = 'D';
        }
    }
}
