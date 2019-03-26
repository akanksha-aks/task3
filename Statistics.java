/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *
 * @author akanksha singh
 Question:https://www.hackerearth.com/practice/algorithms/string-algorithm/z-algorithm/practice-problems/algorithm/statistics-of-strings-3bec3843/
*
 */
public class Statistics implements Runnable {
	static final long MOD = (long) 1e9 + 7;
	static final Reader in = new Reader();
	static final PrintWriter out = new PrintWriter(System.out);
	int m, k;
	long ans = 0;
	public static void main(String[] args) {
		new Thread(null, new Statistics(), "abc", 1 << 28).start();
	}
 
	int maxz(char[] s, int n) {
		int[] z = new int[n];
		int l = 0, r = 0, max = 0;
		for (int i = 1; i < n; i++) {
			if (i > r) {
				l = r = i;
				while (r < n && s[r] == s[r - l])
					r++;
				z[i] = r - l;
				r--;
			} else {
				int k = i - l;
				if (z[k] < r - i + 1)
					z[i] = z[k];
				else {
					l = i;
					while (r < n && s[r] == s[r - l])
						r++;
					z[i] = r - l;
					r--;
				}
			}
			max = Math.max(z[i], max);
		}
		return max;
	}
 
	void solve(char[] ch, int n, int mod) {
		
		if (n < 1) {
			ans = (ans + maxz(ch, m)) % mod;
		} else {
			for (int j = 0; j < k; j++) {
				ch[n - 1] = (char) (j + 65);
				solve(ch, n - 1, mod);
			}
		}
	}
 
	@Override
	public void run() {
		int n = in.nextInt();
		m = n;
		k = in.nextInt();
		int mod = in.nextInt();
		char[] ch = new char[n];
		solve(ch, n, mod);
		out.print(ans);
		out.close();
	}
 
	static class Node {
		int n;
		int f;
		int[] li;
 
		public Node() {
			this.n = in.nextInt();
			this.f = in.nextInt();
			li = in.nextIntArray(n);
		}
	}
 
	static class DisjointSet {
		private int[] idx, isize;
		private int n;
 
		DisjointSet(int n) {
			this.n = n;
			idx = new int[n];
			for (int i = 0; i < n; i++)
				idx[i] = i;
			isize = new int[n];
			Arrays.fill(isize, 1);
		}
 
		int size() {
			return n;
		}
 
		int[] getIdx() {
			return idx;
		}
 
		int[] getIsize() {
			return isize;
		}
 
		int largestCpn() {
			return Arrays.stream(isize).max().getAsInt();
		}
 
		int smallestCpn() {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++)
				if (isize[i] > 1 && find(i) == i)
					min = Math.min(min, isize[i]);
			return min;
		}
 
		int find(int p) {
			while (p != idx[p])
				p = idx[p];
			return p;
		}
 
		boolean isConnected(int p, int q) {
			return find(p) == find(q);
		}
 
		void union(int p, int q) {
			int i = find(p);
			int j = find(q);
			if (i == j)
				return;
			if (isize[i] < isize[j]) {
				idx[i] = j;
				isize[j] += isize[i];
			} else {
				idx[j] = i;
				isize[i] += isize[j];
			}
			n--;
		}
	}
 
	static class Pair {
		int x, y;
 
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
 
		@Override
		public boolean equals(Object o) {
			if (o == null)
				return false;
			if (!(o instanceof Pair))
				return false;
			Pair p = (Pair) o;
			return (x == p.x && y == p.y);
		}
 
		@Override
		public int hashCode() {
			int hash = 31;
			hash = hash * 89 + (x ^ (x >>> 32));
			hash = hash * 89 + (y ^ (y >>> 32));
			return hash;
		}
 
		@Override
		public String toString() {
			return x + " " + y + "\n";
		}
	}
 
	static class Reader {
		BufferedReader br;
		StringTokenizer st;
 
		Reader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
 
		String next() {
			try {
				while (st == null || !st.hasMoreTokens()) {
					st = new StringTokenizer(br.readLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return st.nextToken();
		}
 
		int nextInt() {
			return Integer.parseInt(next());
		}
 
		int[] nextIntArray(int n) {
			int[] arr = new int[n];
			for (int i = 0; i < n; i++)
				arr[i] = nextInt();
			return arr;
		}
 
		long nextLong() {
			return Long.parseLong(next());
		}
 
		String nextLine() {
			String s = "";
			try {
				s = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return s;
		}
	}
}
