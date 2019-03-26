/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package array.sort;
import java.io.*;
import java.util.*;

/**
 *
 * @author akanksha singh
 */
    
// Question : https://www.hackerearth.com/practice/algorithms/sorting/bucket-sort/practice-problems/algorithm/sort-the-array-5/

public class ArraySort {

static class Parser{
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;
    public Parser(InputStream in){
        din = new DataInputStream(in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0; }
    public long nextLong() throws Exception {
        long ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = c == '-';
        if (neg) c = read();
        do { ret = ret * 10 + c - '0';
            c = read();
        } while (c > ' ');
        if (neg) return -ret;
        return ret; }
    //reads in the next string
    public String next() throws Exception{
        StringBuilder ret = new StringBuilder();
        byte c = read();
        while (c <= ' ') c = read();
        do {
            ret = ret.append((char)c);
            c = read();
        } while (c > ' ');
        return ret.toString(); }
    public int nextInt() throws Exception {
        int ret = 0;
        byte c = read();
        while (c <= ' ') c = read();
        boolean neg = c == '-';
        if (neg) c = read();
        do {
            ret = ret * 10 + c - '0';
            c = read(); } while (c > ' ');
        if (neg) return -ret;
        return ret; }
    private void fillBuffer() throws Exception{
        bytesRead = din.read(buffer,
                bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1) buffer[0] = -1; }
    private byte read() throws Exception {
        if (bufferPointer == bytesRead)
            fillBuffer();
        return buffer[bufferPointer++]; }} 
public static void main(String args[]) throws Exception {
    Parser S=new Parser(System.in);
    PrintWriter out =new PrintWriter(System.out);
    int n1=S.nextInt();
    int k1=S.nextInt();
    Integer a[][]=new Integer[n1][2];
    for(int i=0;i<n1;i++) {
    	a[i][1]=S.nextInt();
    	a[i][0]=a[i][1]%k1;
    }
    if(n1==1) {
    	out.print(a[0][1]);
    	out.close();
    	return;
    }
    Arrays.sort(a,new Comparator<Integer[]>() {
    	@Override
    	public int compare(Integer a[],Integer b[]) {
    		int x=b[0].compareTo(a[0]);
    		if(x==0)
    			x=a[1].compareTo(b[1]);
    		return x;
    	}
    });
    for(int i=0;i<n1;i++)
    	out.print(a[i][1]+" ");
    out.close();
}
}
