package adventofcode.kiranmai.day01;

import adventofcode.kiranmai.day01.NoTimeForATaxicab;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class NoTimeForATaxicabTest {

    @Test
    public void follow_R8_L3_expect_5_blocks() throws Exception {
        String[] compass = {"R8", "R4", "R4", "R8"};

        NoTimeForATaxicab taxicab = new NoTimeForATaxicab();
        taxicab.followInsturctions(compass);
        Set<NoTimeForATaxicab.Position> twiceVisitedPositions = taxicab.getTwiceVisitedPositions();
        System.out.println(twiceVisitedPositions);
        int blocksAway = twiceVisitedPositions.stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Expect twice visited position"))
                .getBlocksAway();
        assertEquals(154, blocksAway);
    }

    @Test
    public void follow_R2_R2_R2_expect_2_blocks() throws Exception {
        // R2, R2, R2 leaves you 2 blocks due South of your starting position, which is 2 blocks away.
        String[] input = {"R1", "L4", "L5", "L5", "R2", "R2", "L1", "L1", "R2", "L3", "R4", "R3", "R2", "L4"
                , "L2", "R5", "L1", "R5", "L5", "L2", "L3", "L1", "R1", "R4", "R5", "L3",
                "R2", "L4", "L5", "R1", "R2", "L3", "R3", "L3", "L1", "L2", "R5", "R4", "R5",
                "L5", "R1", "L190", "L3", "L3", "R3", "R4", "R47", "L3", "R5", "R79", "R5", "R3",
                "R1", "L4", "L3", "L2", "R194", "L2", "R1", "L2", "L2", "R4", "L5", "L5", "R1",
                "R1", "L1", "L3", "L2", "R5", "L3", "L3", "R4", "R1", "R5", "L4",
                "R3", "R1", "L1", "L2", "R4", "R1", "L2", "R4", "R4", "L5", "R3", "L5",
                "L3", "R1", "R1", "L3", "L1", "L1", "L3", "L4", "L1", "L2", "R1", "L5", "L3", "R2",
                "L5", "L3", "R5", "R3", "L4", "L2", "R2", "R4", "R4", "L4", "R5", "L1", "L3", "R3", "R4",
                "R4", "L5", "R4", "R2", "L3", "R4", "R2", "R1", "R2", "L4", "L2", "R2", "L5", "L5", "L3", "R5",
                "L5", "L1", "R4", "L1", "R1", "L1", "R4", "L5", "L3", "R4", "R1", "L3", "R4", "R1", "L3",
                "L1", "R1", "R2", "L4", "L2", "R1", "L5", "L4", "L5"};

        NoTimeForATaxicab taxicab = new NoTimeForATaxicab();
        taxicab.followInsturctions(input);
        int blocksAway = taxicab.getBlocksAway();
        assertEquals(230, blocksAway);
    }


}
