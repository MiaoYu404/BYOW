import org.junit.jupiter.api.Test;
import tileengine.Coordinate;
import tileengine.DungeonGenerator;
import tileengine.TETile;

import static com.google.common.truth.Truth.assertThat;

public class WorldGenerationTest {
    @Test
    public void testCoordinates() {
        Coordinate c1 = new Coordinate(0, 3);
        Coordinate c2 = new Coordinate(0, 3);
        assertThat(c1.equals(c2)).isTrue();
        assertThat(c1.hashCode() == c2.hashCode()).isFalse();
    }

//    @Test
//    public void testHallwayGeneration() {
//        DungeonGenerator dg = new DungeonGenerator(11, 20250301);
//        boolean[] gates1 = {true, true, true, true};
//        TETile[][] res1 = Chunk.generateHallwayChunk(gates1);
//        System.out.println(TETile.toString(res1));
//
//        boolean[] gates2 = {true, false, true, false};
//        TETile[][] res2 = Chunk.generateHallwayChunk(gates2);
//        System.out.println(TETile.toString(res2));
//
//        boolean[] gates3 = {true, false, false, true};
//        TETile[][] res3 = Chunk.generateHallwayChunk(gates3);
//        System.out.println(TETile.toString(res3));
//
//        boolean[] gates4 = {false, true, true, true};
//        TETile[][] res4 = Chunk.generateHallwayChunk(gates4);
//        System.out.println(TETile.toString(res4));
//    }
}
