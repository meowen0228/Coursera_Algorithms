// https://github.com/vancexu/Algs4/blob/master/JobInterviewQuestions/1.1_union_find/WeightedQuickUnionUF.java
import java.io.FileInputStream;
import java.util.Scanner;

public class SocialNetworkConQuickUnion {
  private FileInputStream ins;
  private int[] id;
  private int count;

  public SocialNetworkConQuickUnion(int N, FileInputStream ins) {
    this.ins = ins;
    id = new int[N];
    count = N;
    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }

  private int root(int i) {
    while (i != id[i])
      i = id[i];
    return i;
  }

  public boolean connected(int p, int q) {
    return root(p) == root(q);
  }

  public void union(int p, int q) {
    id[root(p)] = root(q);
    count = count - 1;
  }

  public String getEarliestConTime() {
    Scanner scanner = new Scanner(ins, "utf-8");
    String earliestConTime = null;
    while (scanner.hasNext()) {
      String line = scanner.nextLine();
      if (line == null) {
        continue;
      }
      String[] lineArray = line.split(" ");
      if (lineArray.length != 3) {
        continue;
      }
      String timestamp = lineArray[0];
      int p = Integer.parseInt(lineArray[1]);
      int q = Integer.parseInt(lineArray[2]);
      if (this.connected(p, q))
        continue;
      this.union(p, q);
      if (this.count == 1) {
        earliestConTime = timestamp;
        break;
      }
    }
    scanner.close();
    return earliestConTime;
  }

  public static void main(String[] args) {
    try {
      FileInputStream ins = new FileInputStream("./SocialNetwork.txt");
      SocialNetworkConQuickUnion uf = new SocialNetworkConQuickUnion(10, ins);
      System.out.println(uf.getEarliestConTime());
    } catch (Exception e) {
      System.out.println(e);
    }
  }
};
