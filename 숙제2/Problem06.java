package cse3040hw6;


interface IntSequence {
	boolean hasNext();

	int next();
}

class FibonacciSequence implements IntSequence {
	private int a=0,b=1,c=-1;

	public boolean hasNext() {
		return true;
	}

	public int next() {
		if(c==-1) {//초기값
			c=0;
			return 0;
		}
		else if(c==0)
		{	
			c=a+b;
			return 1;
		}
		else {
			c=a+b;
			a=b;
			b=c;
			return c;
		}
	}
}

public class Problem06 {
	public static void main(String[] args) {
		IntSequence seq = new FibonacciSequence();
		for (int i = 0; i < 20; i++) {
			if (seq.hasNext() == false)
				break;
			System.out.print(seq.next() + " ");
		}
		System.out.println(" ");
	}
}
