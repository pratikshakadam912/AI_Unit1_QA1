package ai_files;

import java.util.*;

class HanoiAStar {
    static class State implements Comparable<State> {
        int[] peg;
        State parent;
        String move;
        int g, h;

        State(int[] p, State par, String m, int g) {
            peg = p.clone();
            parent = par;
            move = m;
            this.g = g;
            this.h = heuristic();
        }

        int heuristic() {
            int count = 0;
            for (int v : peg)
                if (v != 2) count++;
            return count;
        }

        boolean isGoal() {
            return h == 0;
        }

        public int compareTo(State o) {
            return (this.g + this.h) - (o.g + o.h);
        }

        public boolean equals(Object o) {
            return Arrays.equals(peg, ((State) o).peg);
        }

        public int hashCode() {
            return Arrays.hashCode(peg);
        }
    }

    static void aStar() {
        PriorityQueue<State> pq = new PriorityQueue<>();
        Set<State> visited = new HashSet<>();

        State start = new State(new int[]{0, 0, 0}, null, "", 0);
        pq.add(start);

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.isGoal()) {
                printPath(cur);
                return;
            }

            visited.add(cur);

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (cur.peg[i] == j) continue;

                    int[] next = cur.peg.clone();
                    next[i] = j;

                    State nxt = new State(next, cur,
                            "Disk " + (i + 1) + ": " + cur.peg[i] + " -> " + j, cur.g + 1);

                    if (!visited.contains(nxt))
                        pq.add(nxt);
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
        System.out.println("A* Solution:");
        while (!st.isEmpty())
            System.out.println(st.pop());
    }

    public static void main(String[] args) {
        aStar();
    }
}
