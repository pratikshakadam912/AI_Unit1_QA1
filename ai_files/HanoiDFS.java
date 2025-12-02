package ai_files;

class HanoiDFS {
    static void dfs(int n, char from, char aux, char to) {
        if (n == 1) {
            System.out.println(from + " -> " + to);
            return;
        }
        dfs(n - 1, from, to, aux);
        System.out.println(from + " -> " + to);
        dfs(n - 1, aux, from, to);
    }

    public static void main(String[] args) {
        int disks = 3;
        System.out.println("DFS Solution:");
        dfs(disks, 'A', 'B', 'C');
    }
}
