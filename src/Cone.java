import java.util.Scanner;

public class Cone extends Shapes{
	static int height;
	public Cone(int height1) {
		this.height=height1;
	}
   
	@Override
	public String[][] inputMethod(int m, int n) {
		// TODO Auto-generated method stub
		int  f , b, rad,r,back,front;
		rad=height/2;
		f=height;
		r=height;
		b=height;
		back = height+1;
		front = height-1;
		String coneArr[][]=new String[m][n];
		for(int i=0;i<=m;i++) {
			if (i<((height)-rad)) { // till h-r print pyramid
				for(int j=0;j<n;j++) {
					if (j==f || j==b) {
						coneArr[i][j]="*";
					}
					else {
						coneArr[i][j]=" ";
					}
				}
				f++;
				b--;
			}
			else if (i>=(height-rad) && i<height) {// till h print  pyramid with circle
				for(int j=0;j<n;j++) {
					if(j==back || j==front || j==f || j==b) {
						coneArr[i][j]= "*";
					}
					else if (j==r) {
						coneArr[i][j]= "*";
					}
					else {
						coneArr[i][j]= " ";
					}
				}
				front--;
				back++;
				r=front;
			}
			else if(i == height) {
				front++;
				back--;
				for(int j = 0;j<n; j++) {
					if(j==back || j==front) {
						coneArr[i][j]= "*";
					}
					else {
						coneArr[i][j]=" ";
					}
				}
			}
			if(i>height && i<=(height+rad)) {
				for(int j=0;j<n; j++) {

					if(j==back || j==front) {
						coneArr[i][j]= "*";
					}
					else if(i==(height+rad) && j==height)  {
						coneArr[i][j]="*";
					}
					else  {
						coneArr[i][j]= " ";
					}
				} front++;
				back--;
			}
		}

		return coneArr;
	}



	@Override
	public void displayMethod(String[][] coneArr, int m, int n) {
		// TODO Auto-generated method stub
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {

				System.out.print(coneArr[i][j]);

			}System.out.println();
		}
		
	}
	
	public static void main(String[] args) {
		int h,m,n;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Heigth for cone");
		h=sc.nextInt();
		Cone cone = new Cone(h);
		String coneArr[][];
		m=h+(h/2)+1;
		n=h*2;
		coneArr=cone.inputMethod(m, n);
		cone.displayMethod(coneArr, m, n);
	}
}
