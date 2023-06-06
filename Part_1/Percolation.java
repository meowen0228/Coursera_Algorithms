import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private WeightedQuickUnionUF wquOpen;
  private WeightedQuickUnionUF wquFull;
  private boolean[][] grid;
  private int openSite;
  private int gridSize;
  private int gridSquared;
  private int virtualTop;
  private int virtualBottom;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n <= 0) {
      throw new Error("n must bigger than 0.");
    }

    grid = new boolean[n][n];
    gridSize = n;
    openSite = 0;
    gridSquared = n * n;

    wquOpen = new WeightedQuickUnionUF(gridSquared + 2);
    wquFull = new WeightedQuickUnionUF(gridSquared + 2);
    virtualTop = 0;
    virtualBottom = gridSquared + 1;
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {

    row -= 1;
    col -= 1;

    // if already open do nothing
    if (isOpen(row, col)) {
      return;
    }

    grid[row][col] = true;
    int index = getFlatIndex(row, col);

    // first row
    if (row == 0) {
      wquOpen.union(virtualTop, getFlatIndex(row, col));
      wquFull.union(virtualTop, getFlatIndex(row, col));
    }

    // last row
    if (row == gridSize - 1) {
      wquOpen.union(virtualBottom, getFlatIndex(row, col));
    }

    // open up
    if (isOnGrid(row - 1, col) && isOpen(row - 1, col)) {
      wquOpen.union(index, index - gridSize);
      wquFull.union(index, index - gridSize);
    }

    // open down
    if (isOnGrid(row + 1, col) && isOpen(row + 1, col)) {
      wquOpen.union(index, index + gridSize);
      wquFull.union(index, index + gridSize);
    }

    // open right
    if (isOnGrid(row, col + 1) && isOpen(row, col + 1)) {
      wquOpen.union(index, index + 1);
      wquFull.union(index, index + 1);
    }

    // open left
    if (isOnGrid(row, col - 1) && isOpen(row, col - 1)) {
      wquOpen.union(index, index - 1);
      wquFull.union(index, index - 1);
    }

    openSite++;
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    checkIsOnGrid(row, col);
    return grid[row][col];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    checkIsOnGrid(row, col);
    return grid[row][col];
  }

  public void checkIsOnGrid(int row, int col) {
    if (!isOnGrid(row, col)) {
      throw new IllegalArgumentException("Index out of bound.");
    }
  }

  // is the site (row, col) on grid?
  public boolean isOnGrid(int row, int col) {
    return (row >= 0 && col >= 0 && row < this.gridSize && col < this.gridSize);
  }

  // is the site (row, col) on grid?
  public int getFlatIndex(int row, int col) {
    // 1 is for vitualTop
    return row * gridSize + col + 1;
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return openSite;
  }

  // does the system percolate?
  public boolean percolates() {
    return wquOpen.connected(virtualTop, virtualBottom);
  }

  // test client (optional)
  public static void main(String[] args) {
    try {
      Percolation pc = new Percolation(3);
      pc.open(1, 1);
      pc.open(1, 2);
      pc.open(2, 2);
      pc.open(3, 2);
      System.out.println(pc.percolates());
    } catch (Exception e) {
      System.out.println(e);
    }

  }
}