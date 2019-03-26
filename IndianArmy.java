/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indian.army;
import java.util.*;

/**
 *
 * @author akanksha singh
 */


  
//Indain Army question

//Question:https://www.hackerearth.com/practice/algorithms/greedy/basics-of-greedy-algorithms/practice-problems/algorithm/help-out-the-indian-army/
 
 
public class IndianArmy {

	static class pair implements Comparable<pair>
	{
		Long x, y;
		pair(long x,long y)
		{
			this.x=x;
			this.y=y;
		}
		public int compareTo(pair o) {
			return x.compareTo(o.x);
		}
	}
 
	public static void main(String[] args)
	{
 
		Scanner sc = new Scanner(System.in);
 
		int numCheckPoints =sc.nextInt();
		long startPos = sc.nextLong();
		long endPos = sc.nextLong();
 
		pair[] areaDefended = new pair[numCheckPoints];
		for(int i=0;i<numCheckPoints;i++)
		{
			long x = sc.nextLong();
			long p = sc.nextLong();
			areaDefended[i] = new pair(x-p,x+p);
		}
 
		Arrays.sort(areaDefended);
		ArrayList<pair> b = new ArrayList<pair>();
 
		long c = areaDefended[0].x;
		long d = areaDefended[0].y;
 
		//merging the defended segments if any
		for(int i=1;i<numCheckPoints;i++)
		{
			if(areaDefended[i].x<=d)
				d = Math.max(d, areaDefended[i].y);
			else
			{
				b.add(new pair(c,d));
				c = areaDefended[i].x;
				d = areaDefended[i].y;
			}
		}
		b.add(new pair(c,d));
		int i;
		long ans = 0;
		for(i=0;i<b.size();i++)
		{
			if(startPos>=endPos)
			{
				startPos = endPos;
				break;
			}
			//if starting position is in between current segment
			if(b.get(i).x<=startPos && startPos<=b.get(i).y)
				startPos = b.get(i).y;
			//if current segment is in between starting position & ending position
			else if(startPos<=b.get(i).x && endPos>=b.get(i).y)
			{
				ans+=b.get(i).x-startPos;
				startPos = b.get(i).y;
 
			}//if ending position lies between current segment
			else if(startPos<=b.get(i).x && endPos>=b.get(i).x &&  endPos<=b.get(i).y)
			{
				ans+=b.get(i).x-startPos;
				startPos = endPos;
			}
			//if starting position & ending position are before the current segment
			else if(startPos<=b.get(i).x && endPos<=b.get(i).x)
			{
				ans+=endPos-startPos;
				startPos = endPos;
			}
		}
		//add the distance which is not covered in above segments
		if(startPos<endPos)
			ans+=endPos-startPos;
		System.out.println(ans);
		sc.close();
	}
}


