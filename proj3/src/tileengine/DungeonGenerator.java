package tileengine;

import java.util.*;

public class DungeonGenerator {
    private static int WORLD_WIDTH = 64;
    private static int WORLD_HEIGHT = 64;
    private static final int MIN_ROOM_SIZE = 6;
    private static final int MAX_ROOM_SIZE = 15;
    private static final int MIN_ROOMS = 10;
    private static final int MAX_ROOMS = 25;
    private static final double MIN_COVERAGE = 0.6;

    private Random rand;
    private TETile[][] world;
    private List<Room> rooms;

    public DungeonGenerator(int width, int height, long seed) {
        WORLD_WIDTH = width;
        WORLD_HEIGHT = height;
        rand = new Random(seed);
        world = new TETile[WORLD_WIDTH][WORLD_HEIGHT];
        rooms = new ArrayList<>();

        init();
    }

    private void init() {
        for (int x = 0; x < WORLD_WIDTH; x++) {
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                world[x][y] = Tileset.VOID;
            }
        }
    }

    public TETile[][] generateWorld() {
        do {
            init();
            rooms.clear();
            placeRooms();
            connectRooms();
            addWalls();
        } while (calculateCoverage() < MIN_COVERAGE);
        return world;
    }

    private void placeRooms() {
        int numRooms = rand.nextInt(MAX_ROOMS - MIN_ROOMS + 1) + MIN_ROOMS; // 10到20个房间
        for (int i = 0; i < numRooms; i++) {
            int width = rand.nextInt(MAX_ROOM_SIZE - MIN_ROOM_SIZE + 1) + MIN_ROOM_SIZE;
            int height = rand.nextInt(MAX_ROOM_SIZE - MIN_ROOM_SIZE + 1) + MIN_ROOM_SIZE;
            int x = rand.nextInt(WORLD_WIDTH - width - 1) + 1;
            int y = rand.nextInt(WORLD_HEIGHT - height - 1) + 1;

            Room newRoom = new Room(x, y, width, height);
            if (!doesCollide(newRoom)) {
                rooms.add(newRoom);
                drawRoom(newRoom);
            }
        }
    }

    private boolean doesCollide(Room room) {
        for (Room existing : rooms) {
            if (room.intersects(existing)) {
                return true;
            }
        }
        return false;
    }

    private void drawRoom(Room room) {
        int width = room.width, height = room.height;
        for (int x = room.x; x < room.x + width; x++) {
            for (int y = room.y; y < room.y + height; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }
    }

    private void connectRooms() {
        if (rooms.size() < 2) return;
        // Prim
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                Room a = rooms.get(i);
                Room b = rooms.get(j);
                int dist = Math.abs(a.centerX() - b.centerX()) + Math.abs(a.centerY() - b.centerY());
                edges.add(new Edge(i, j, dist));
            }
        }
        edges.sort(Comparator.comparingInt(e -> e.weight));

        int[] parent = new int[rooms.size()];
        for (int i = 0; i < parent.length; i++) parent[i] = i;
        for (Edge edge : edges) {
            if (find(parent, edge.from) != find(parent, edge.to)) {
                union(parent, edge.from, edge.to);
                connectTwoRooms(rooms.get(edge.from), rooms.get(edge.to));
            }
        }

        // 添加额外随机分支以增加覆盖率和三岔路口
        int extraBranches = rooms.size();
        for (int i = 0; i < extraBranches; i++) {
            int r1 = rand.nextInt(rooms.size());
            int r2 = rand.nextInt(rooms.size());
            if (r1 != r2) {
                connectTwoRooms(rooms.get(r1), rooms.get(r2));
            }
        }
    }

    private void connectTwoRooms(Room roomA, Room roomB) {
        int x1 = roomA.centerX(), y1 = roomA.centerY();
        int x2 = roomB.centerX(), y2 = roomB.centerY();

        if (rand.nextBoolean()) {                                           // possibly
            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                placeRoad(x, y1);
            }
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                placeRoad(x2, y);
            }
        } else {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                placeRoad(x1, y);
            }
            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                placeRoad(x, y2);
            }
        }
    }

    private void placeRoad(int x, int y) {
        if (isValid(x, y)) world[x][y] = Tileset.FLOOR;
        if (isValid(x + 1, y)) world[x + 1][y] = Tileset.FLOOR; // 2格宽
    }

    private void addWalls() {
        boolean[][] visited = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
        for (int x = 0; x < WORLD_WIDTH; x++) {
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                if (world[x][y] == Tileset.FLOOR && !visited[x][y]) {
                    outlineFrom(x, y, visited);
                }
            }
        }
    }

    private void outlineFrom(int x, int y, boolean[][] visited) {
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(x, y));
        visited[x][y] = true;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        while (!queue.isEmpty()) {
            Coordinate curr = queue.poll();
            int cx = curr.x();
            int cy = curr.y();

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (isValid(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if (world[nx][ny] == Tileset.FLOOR) {
                        queue.add(new Coordinate(nx, ny));
                    } else if (world[nx][ny] == Tileset.VOID) {
                        world[nx][ny] = Tileset.WALL;
                    }
                }
            }
        }
    }

    public double calculateCoverage() {
        int used = 0;
        for (int x = 0; x < WORLD_WIDTH; x++) {
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                if (world[x][y] != Tileset.VOID) used++;
            }
        }
        return (double) used / (WORLD_WIDTH * WORLD_HEIGHT);
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < WORLD_WIDTH && y >= 0 && y < WORLD_HEIGHT;
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private void union(int[] parent, int x, int y) {
        parent[find(parent, x)] = find(parent, y);
    }
}

class Room {
    int x, y, width, height;

    public Room(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int centerX() {
        return x + width / 2;
    }

    public int centerY() {
        return y + height / 2;
    }

    public boolean intersects(Room other) {
        return !(other.x > this.x + this.width + 1 ||
                other.x + other.width < this.x - 1 ||
                other.y > this.y + this.height + 1 ||
                other.y + other.height < this.y - 1);
    }
}

class Edge {
    int from, to, weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
