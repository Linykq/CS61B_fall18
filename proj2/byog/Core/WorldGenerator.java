package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;


public class WorldGenerator {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;
    private long seed;
    private Random random;
    private List<Room> rooms;
    private Queue<Room> bfs;
    public TETile[][] world;


    private class Room {
        private int x, y, width, height;

        public Room(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public boolean intersects(Room another) {
            return ! (x + width < another.x || another.x + another.width < x || y + height < another.y || another.y + another.height < y);
        }

        public boolean inEdge(int x, int y) {
            return (x == this.x || x == this.x + this.width - 1) && (y == this.y || y == this.y + this.height - 1);
        }
    }

    public WorldGenerator(long seed) {
        this.seed = seed;
        this.random = new Random(seed + new Random().nextInt(211));
        this.rooms = new ArrayList<>();
        this.bfs = new LinkedList<>();

        world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public void generate() {
        generateRooms();
        connectRooms();
    }

    private void generateRooms() {
        Room baseRoom = generateBaseRoom();
        bfs.add(baseRoom);

        while (!bfs.isEmpty()) {
            Room currRoom = bfs.poll();
            int numNeighbours = random.nextInt(3) + 1;
            for (int i = 0; i < numNeighbours; i++) {
                generateNeighbours(currRoom);
            }
        }
    }

    private Room generateBaseRoom() {
        int width = random.nextInt(10) + 3;
        int height = random.nextInt(10) + 3;
        int x = random.nextInt(WIDTH - width);
        int y = random.nextInt(HEIGHT - height);
        Room baseRoom = new Room(x, y, width, height);
        fillTile(baseRoom);
        int k = random.nextInt(baseRoom.width - 2) + baseRoom.x + 1;
        world[k][baseRoom.y] = Tileset.LOCKED_DOOR;
        return baseRoom;
    }

    private void generateNeighbours(Room room) {

    }

    private void fillTile(Room room) {
        for (int y = room.y; y < room.y + room.height; y++) {
             for (int x = room.x; x < room.x + room.width; x++) {
                if(y == room.y || y == room.y + room.height - 1|| x == room.x || x == room.x + room.width - 1) {
                    world[x][y] = Tileset.WALL;
                } else {
                    world[x][y] = Tileset.FLOOR;
                }
             }
        }
    }

    private void connectRooms() {

    }

    private void createCorridor(Room room1, Room room2) {

    }


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        WorldGenerator worldGenerator = new WorldGenerator(208315);
        worldGenerator.generate();
        ter.renderFrame(worldGenerator.world);
    }
}
