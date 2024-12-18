package byog.lab5;
import org.junit.Test;

import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    /**
    * Computes the width of row i for a size s hexagon.
    * @param s The size of the hex.
    * @param i The row number where i = 0 is the bottom row.
    * @return
    */
    public static int rowWidth(int s, int i) {
        int index = i;
        if(i >= s) {
            index = 2 * s - i - 1;
        }
        return s + 2 * index;
    }

    /**
    * Computes the xPos of the star of a row, assuming the bottom of Hexagon xPos stars at 0
    * @param s The size of the hex.
    * @param i The row number where i = 0 is the bottom row.
    * @return
    */
    public static int xStarPos(int s, int i) {
        int index = i;
        if(i >= s) {
            index = 2 * s - i - 1;
        }
        return -index;
    }

    /**
    * Adds a row of given width with same tile but different colors
    * @param world
    * @param xPos
    * @param yPos
    * @param width
    * @param t
    * @param r
    */
    public static void addRow(TETile[][] world, int xPos, int yPos, int width, TETile t, Random r) {
        for(int xi = 0; xi < width; xi++) {
            world[xPos + xi][yPos] = TETile.colorVariant(t, 32, 32,32, r);
        }
    }

    /**
    * Adds a Hexagon to the world with same tile
    * @param world
    * @param xPos
    * @param yPos
    * @param s
    * @param t
    */
    public static void addHexagon(TETile[][] world, int xPos, int yPos, int s, TETile t) {
        if(s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least 2 tiles");
        }
        for(int yi = 0; yi < 2 * s; yi++) {
            int currY = yPos + yi;
            int currX = xPos + xStarPos(s, yi);
            int currWidth = rowWidth(s, yi);
            Random r = new Random();
            addRow(world, currX, currY, currWidth, t, r);
        }
    }

    /**
    * generate random tile
    * @return
    */
    public static TETile randomTile() {
        TETile tile = null;
        Random r = new Random();
        int x = r.nextInt(6);
        switch (x) {
            case 0:
                tile = Tileset.FLOWER;
                break;
            case 1:
                tile = Tileset.GRASS;
                break;
            case 2:
                tile = Tileset.WATER;
                break;
            case 3:
                tile = Tileset.SAND;
                break;
            case 4:
                tile = Tileset.MOUNTAIN;
                break;
            case 5:
                tile = Tileset.TREE;
                break;
            default:
        }
        return tile;
    }

    public static int bottomHexagon(TETile[][] world, int s) {
        int width = world.length;
        Random r = new Random();
        int x = r.nextInt(width - 2 * s) + (s - 1);
        return x;
    }

    public static boolean inWorld(TETile[][] world, int s, int xPos) {
        if(xPos - (s - 1) < 0 || xPos + 2 *(s - 1) >= world.length) {
            return false;
        }
        return true;
    }

    public static void generateHexagonColum(TETile[][] world, int s, int xPos, int yPos) {
        while (yPos + 2 * s - 1 <  world[0].length) {
            addHexagon(world, xPos, yPos, s, randomTile());
            yPos = yPos + 2 * s;
        }
    }

    public static void HexagonColums(TETile[][] world, int s, int x, int y, int dis) {
        for(int left = x - dis; inWorld(world, s, left); left = left - dis) {
            generateHexagonColum(world, s, left, y);
        }
        for(int right = x; inWorld(world, s, right); right = right + dis) {
            generateHexagonColum(world, s, right, y);
        }
    }

    public static void pieceHexagon(TETile[][] world, int s) {
        if(2 * s > world.length || 3 * s - 2 > world[0].length) {
            throw new IllegalArgumentException("s is too big!");
        }
        int dis = s + 2 * (s - 1) + s;
        int x = bottomHexagon(world, s);
        int y = 0;
        HexagonColums(world, s, x, y, dis);
        if(x + 2 * (s - 1) + 1 < world.length) {
            HexagonColums(world, s, x + 2 * (s - 1) + 1, y + s, dis);
        } else {
            HexagonColums(world, s, x - 2 * (s - 1) - 1, y + s, dis);
        }
    }


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.WALL;
            }
        }
        pieceHexagon(world, 3);
        ter.renderFrame(world);
    }

        @Test
    public void testRowWidth() {
        assertEquals(3, rowWidth(3, 5));
        assertEquals(5, rowWidth(3, 4));
        assertEquals(7, rowWidth(3, 3));
        assertEquals(7, rowWidth(3, 2));
        assertEquals(5, rowWidth(3, 1));
        assertEquals(3, rowWidth(3, 0));
        assertEquals(2, rowWidth(2, 0));
        assertEquals(4, rowWidth(2, 1));
        assertEquals(4, rowWidth(2, 2));
        assertEquals(2, rowWidth(2, 3));
    }

    @Test
    public void testXStarPos() {
        assertEquals(0, xStarPos(3, 5));
        assertEquals(-1, xStarPos(3, 4));
        assertEquals(-2, xStarPos(3, 3));
        assertEquals(-2, xStarPos(3, 2));
        assertEquals(-1, xStarPos(3, 1));
        assertEquals(0, xStarPos(3, 0));
        assertEquals(0, xStarPos(2, 0));
        assertEquals(-1, xStarPos(2, 1));
        assertEquals(-1, xStarPos(2, 2));
        assertEquals(0, xStarPos(2, 3));
    }
}
