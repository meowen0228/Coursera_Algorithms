public class SuccessorWithDelete {

  private int[] id;
  private int[] successorId;

  public SuccessorWithDelete(int N) {
    id = new int[N];
    successorId = new int[N];

    for (int i = 0; i < N; i++) {
      id[i] = i;
      successorId[i] = i;
    }
    ;
  }

  public void remove(int i) {
    successorId[i] = i + 1;
  }

  public int getSuccessor(int i) {
    while (i != successorId[i] && i < id.length - 1)
      i = successorId[i];

    return successorId[i];
  }

  public static void main(String[] args) {
    SuccessorWithDelete uf = new SuccessorWithDelete(6);
    uf.remove(2);
    System.out.println(uf.getSuccessor(2));
    uf.remove(3);
    System.out.println(uf.getSuccessor(2));
    uf.remove(4);
    System.out.println(uf.getSuccessor(2));
    uf.remove(5);
    System.out.println(uf.getSuccessor(2));
    System.out.println(uf.getSuccessor(1));
    System.out.println(uf.getSuccessor(3));
  }
}
