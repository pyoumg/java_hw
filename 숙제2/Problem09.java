package cse3040hw9;

class Point{
	private double[] dotPoint;//private variable
	public Point(double[] arr) {
		dotPoint=arr;
	}
	public double[] getPoint()
	{
		return dotPoint;
	}
	
}

class EuclideanDistance {
	private static double ans;
	private static double[] aArr,bArr;
	
	public static double getDist(Point a,Point b) {
		ans=0;//초기화
		if(a.getPoint().length!=b.getPoint().length)//차원이 다를 때
			return -1;
		else {
			aArr=a.getPoint();
			bArr=b.getPoint();
			for(int i=0;i<aArr.length;i++) {
				ans+=(aArr[i]-bArr[i])*(aArr[i]-bArr[i]);
			}
			ans=Math.sqrt(ans);
			return ans;
		}
		
	}
}

class ManhattanDistance {
	private static double ans;
	private static double[] aArr,bArr;
	
	public static double getDist(Point a,Point b) {
		ans=0;//초기화
		if(a.getPoint().length!=b.getPoint().length)//차원이 다를 때
			return -1;
		else {
			aArr=a.getPoint();
			bArr=b.getPoint();
			for(int i=0;i<aArr.length;i++) {
				ans+=Math.abs(aArr[i]-bArr[i]);
			}
			return ans;
		}
		
	}
}

public class Problem09 {//변경불가
	public static void main(String[] args) {
		Point p1 = new Point(new double[] { 1.0, 2.0, 3.0 });
		Point p2 = new Point(new double[] { 4.0, 5.0, 6.0 });
		System.out.println("Euclidean Distance: " + EuclideanDistance.getDist(p1, p2));
		System.out.println("Manhattan Distance: " + ManhattanDistance.getDist(p1, p2));
		Point p3 = new Point(new double[] { 1.0, 2.0, 3.0 });
		Point p4 = new Point(new double[] { 4.0, 5.0 });
		System.out.println("Euclidean Distance: " + EuclideanDistance.getDist(p3, p4));
		System.out.println("Manhattan Distance: " + ManhattanDistance.getDist(p3, p4));
	}
}
