public class QuickUnionWithFind
{
    private int[] id;
    private int[] large;
    
    public QuickUnionWithFind(int N)
    {
        id = new int[N];
        large = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            large[i] = i;
        };
    }
    
    private int root(int i)
    {
        while (i != id[i]) i = id[i];
        return i;
    }
    
    public boolean connected(int p, int q)
    {
        return root(p) == root(q);
    }
    
    public void union(int p, int q)
    {
        int root_p = root(p);
        int root_q = root(q);
        int large_p = large[root_p];
        int large_q = large[root_q];
        id[root_p] = root_q;
        if(large_p < large_q) {
            large[root(p)] = large_q;
        }
    }
    
    public int find(int i)
    {
        return large[root(i)];
    }
    
    public static void main(String[] args) {
        QuickUnionWithFind uf = new QuickUnionWithFind(6);
        uf.union(0, 2);
        System.out.println(uf.find(0));
        uf.union(0, 4);
        System.out.println(uf.find(0));
        uf.union(0, 5);
        System.out.println(uf.find(0));
        System.out.println(uf.id);
    }
};
