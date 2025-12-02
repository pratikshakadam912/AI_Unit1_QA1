package ai_files;

import java.util.*;

class HanoiBFS {
    static class State {
        int[] peg;
        State parent;
        String move;

        State(int[] p, State par, String m) {
            peg = p.clone();
            parent = par;
            move = m;
        }

        boolean isGoal() {
            for (int v : peg)
                if (v != 2) return false;
            return true;
        }

        public int hashCode() {
            return Arrays.hashCode(peg);
        }

        public boolean equals(Object o) {
            return Arrays.equals(peg, ((State) o).peg);
        }
    }

    static void bfs() {
        Queue<State> q = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        State start = new State(new int[]{0, 0, 0}, null, "");
        q.add(start);
        visited.add(start);

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.isGoal()) {
                printPath(cur);
                return;
            }

            for (int i = 0; i < 3; i++) {        // disk
                for (int j = 0; j < 3; j++) {    // target peg
                    if (cur.peg[i] == j) continue;
                    int[] next = cur.peg.clone();
                    next[i] = j;
                    State nxt = new State(next, cur, "Disk " + (i + 1) + ": " + cur.peg[i] + " -> " + j);
                    if (!visited.contains(nxt)) {
                        visited.add(nxt);
                        q.add(nxt);
                    }
                }
            }
        }
    }

    static void printPath(State s) {
        Stack<String> st = new Stack<>();
        while (s.parent != null) {
            st.push(s.move);
            s = s.parent;
        }
        System.out.println("BFS Solution:");
        while (!st.isEmpty())
            System.out.println(st.pop());
    }

    public static void main(String[] args) {
        bfs();
    }
}
