/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Question :https://www.hackerearth.com/practice/algorithms/graphs/shortest-path-algorithms/practice-problems/algorithm/metro-6db2ba1b/
package metro;

/**
 *
 * @author akanksha singh
 */
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Comparator;

public class Metro {
	public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Metro1 solver = new Metro1();
        solver.solve(1, in, out);
        out.close();
    }
 
    static class Metro1 {
        ArrayList<Edge>[] adj;
        long[] min_time;
 
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n1 = in.ni();
            int m1 = in.ni();
            adj = new ArrayList[n1];
            min_time = new long[n1];
            for (int i = 0; i < n1; ++i) adj[i] = new ArrayList<>();
            for (int i = 0; i < m1; ++i) {
                int k = in.ni();
                int t = in.ni();
                int[] ar1 = new int[k];
                for (int j = 0; j < k; ++j) {
                    ar1[j] = in.ni() - 1;
                }
                long max = t;
                for (int j = 1; j < k; ++j) {
                    long cur = in.nl();
                    adj[ar1[j - 1]].add(new Edge(max, ar1[j], cur));
                    max += cur;
                }
            }
            int start = in.ni() - 1, end = in.ni() - 1;
            PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
 
                public int compare(Integer o1, Integer o2) {
                    return Long.compare(min_time[o1], min_time[o2]);
                }
            });
            pq.add(start);
            Arrays.fill(min_time, Long.MAX_VALUE);
            min_time[start] = 0;
            while (!pq.isEmpty()) {
                int pop = pq.poll();
                for (Edge g : adj[pop]) {
                    int go = g.to;
                    if (min_time[pop] + g.time < min_time[go] && min_time[pop] <= g.max_time) {
                        min_time[go] = g.time + min_time[pop];
                        pq.add(go);
                    }
                }
            }
            if (min_time[end] != Long.MAX_VALUE) out.print(min_time[end]);
            else out.print(-1);
        }
 
        public class Edge {
            long time;
            long max_time;
            int to;
 
            public Edge(long max_time, int to, long time) {
                this.max_time = max_time;
                this.to = to;
                this.time = time;
            }
 
        }
 
    }
 
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int ni() {
            return Integer.parseInt(next());
        }
 
        public long nl() {
            return Long.parseLong(next());
        }
 
    }
}
