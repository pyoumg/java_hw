package cse3040hw10;


class Points{
	private double sum=0;
	private double[] nPoints=null;
	
	Points(double[] arr){
		nPoints=arr;
		for(int i=0;i<arr.length;i++)
			sum+=arr[i];
	}
	public double getSum()
	{
		return sum;
	}
	
	public boolean equals(Points a) {
		if(a==null&&nPoints!=null || a!=null&&nPoints==null)//수정
			return false;
		else if(sum==a.getSum()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		if(nPoints==null)
			return "null";
		else
			return "[sum of points: "+sum+ "]";
		
	}
}



public class Problem10 {//수정금지
	public static void main(String[] args) {
		Points p1 = new Points(new double[] { 1.0, 2.0, 3.0 });
		Points p2 = new Points(new double[] { 4.0, 5.0, 6.0 });
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p1.equals(p2));
		Points p3 = new Points(new double[] { 1.0, 4.0, 7.0 });
		Points p4 = new Points(new double[] { 3.0, 9.0 });
		System.out.println(p3);
		System.out.println(p4);
		System.out.println(p3.equals(p4));
		Points p5 = new Points(new double[] { 1.0, 2.0 });
		Points p6 = null;
		System.out.println(p5);
		System.out.println(p6);
		System.out.println(p5.equals(p6));
	}
}
