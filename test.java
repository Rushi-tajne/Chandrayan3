import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Chandrayaan3SpacecraftTest {
    private Chandrayaan3Spacecraft chandrayaan3;

    @BeforeEach
    public void setUp() {
        // Initialize Chandrayaan3Spacecraft for testing
        chandrayaan3 = new Chandrayaan3Spacecraft(0, 0, 0, 'N');
    }

    @Test
    public void testMoveForward() {
        chandrayaan3.move('f');
        assertEquals(chandrayaan3.getCurrentPosition(), "(0, 1, 0)");
    }

    @Test
    public void testMoveBackward() {
        chandrayaan3.move('b');
        assertEquals(chandrayaan3.getCurrentPosition(), "(0, -1, 0)");
    }

    @Test
    public void testTurnLeft() {
        chandrayaan3.turn('l');
        assertEquals(chandrayaan3.getCurrentDirection(), 'W');
    }

    @Test
    public void testTurnRight() {
        chandrayaan3.turn('r');
        assertEquals(chandrayaan3.getCurrentDirection(), 'E');
    }

    @Test
    public void testRotateUp() {
        chandrayaan3.rotate('u');
        assertEquals(chandrayaan3.getCurrentDirection(), 'U');
    }

    @Test
    public void testRotateDown() {
        chandrayaan3.rotate('d');
        assertEquals(chandrayaan3.getCurrentDirection(), 'D');
    }

    @Test
    public void testExecuteCommands() {
        List<Character> commands = new ArrayList<>();
        commands.add('f');
        commands.add('l');
        commands.add('r');
        commands.add('u');
        chandrayaan3.executeCommands(commands);

        assertEquals(chandrayaan3.getCurrentPosition(), "(1, 1, 1)");
        assertEquals(chandrayaan3.getCurrentDirection(), 'U');
    }

    @Test
    public void testPrintPositionAndDirection() {
        // Redirect stdout to capture printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        chandrayaan3.printPositionAndDirection();
        assertEquals(outputStream.toString(), "Final Position: (0, 0, 0)\nFinal Direction: N\n");
    }
}
