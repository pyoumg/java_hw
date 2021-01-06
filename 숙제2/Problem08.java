package cse3040hw8;


class Shape{
		private double area;//private variable
		public void changeArea(double newArea) {
			area=newArea;
		}
		public double getArea()
		{
			return area;
		}
		
		

}

class Circle extends Shape{
	 Circle(double r){
		super.changeArea(r*r*Math.PI);
	}
}

class Square extends Shape{
	 Square(double r){
		super.changeArea(r*r);
	}
}

class Rectangle extends Shape{
	public Rectangle(double a,double b){
		super.changeArea(a*b);
	}
	
}







public class Problem08 {
	static double sumArea(Shape[] arr) {
		double ans=0;
		for(int i=0;i< arr.length;i++) {
			ans+=arr[i].getArea();
		}
		return ans;
	}

	
	public static void main(String[] args) {
		Shape[] arr = { new Circle(5.0), new Square(4.0), 
						new Rectangle(3.0, 4.0), new Square(5.0) };
		System.out.println("Total area of the shapes is: " + sumArea(arr));
	}
}
