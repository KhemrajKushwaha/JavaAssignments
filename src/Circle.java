import java.util.Scanner;

public class Circle extends Shapes{
	int radius;
	Circle(int radius1){
		this.radius = radius1;
	}
	@Override
	public String[][] inputMethod(int m, int n){
		int back, front, r;
		String cir[][] = new String[m][n];
		r=radius;
		back = radius +1;
		front = radius -1;		
		for(int i = 0;i<m; i++) {
			if(i<radius) {
			for(int j = 0;j<n; j++) {
			   if(j==back || j==front) {
					  cir[i][j]= "0";
				  }
				  else if (j==r) {
					  cir[i][j]= "0";
				  }
				  else {
					  cir[i][j]= " ";
				  }
			  }
			front--;
			 back++;
			r=front;
			} 
		 if(i == radius) {
			 front++;
				back--;
		 for(int j = 0;j<m; j++) {
				 if(j==back || j==front) {
					  cir[i][j]= "0";
			 }
				 else {
					 cir[i][j]=" ";
				 }
		 }
			}
		if(i>radius) {
			r=radius;
				 for(int j = 0;j<n; j++) {
			
			     if(j==back || j==front) {
					  cir[i][j]= "0";
				  }
				 				  else if(i==(radius*2) && j==radius) {
					  cir[i][j]="0";
				  }
				 				  else  {
									  cir[i][j]= " ";
								  }
			  } front++;
				 back--;
			}
		}
		return cir;
	}


	@Override
	public void displayMethod(String cir[][], int m, int n) {
		//String cir1[][]=cir[m][n];
		for(int k=0;k<m;k++) {
			for(int l=0;l<n;l++) {
				System.out.print(cir[k][l]);
			}	System.out.println();
		}
	}
	public static void main(String[] args) {
		int radius, m,n;
		Scanner sc= new Scanner(System.in);
		System.out.print("Enter radius for the circle :");
		radius=sc.nextInt();
		Circle cir=new Circle(radius);
		m=radius*2+1;
		n=radius*2+1;
		String cir1[][];
		cir1=cir.inputMethod(m, n);
		cir.displayMethod(cir1, m, n);
		sc.close();

	}


}
