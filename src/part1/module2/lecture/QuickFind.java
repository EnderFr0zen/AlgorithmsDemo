package part1.module2.lecture;

public class QuickFind {

    private int[] id;

    public QuickFind(int N) {
        // Create an array of size N
        id = new int[N];
        for (int i = 0; i < N; i++) {
            // Initialize each element with its own index
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        // Elements are connected if they have the same id
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        // Get the component id of p
        int pid = id[p];
        // Get the component id of q
        int qid = id[q];
        // Change all entries with id[p] to id[q]
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                // Change component id to match q's component
                id[i] = qid;
            }
        }
    }
}
