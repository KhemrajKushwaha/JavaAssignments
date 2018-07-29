import java.util.*;

public class Rectangle extends Shapes{
	@Override
	public String[][] inputMethod(int length, int width) {
		int i=0, j=0, back, front;
		back = length-1;
		front =0;
		String rec[][]=new String[width][length];
		for(i=0; i<width;i++) {
			for(j=0; j<length; j++) {
				if(j==front || j==back) {
				rec[i][j]=".";
				}
				else if(i==0 || i==width-1) {
					rec[i][j]=".";
				}
				else {
					rec[i][j]=" ";
				}
			}
		}
		return rec;
	}
	@Override
	public void displayMethod(String rec[][],int length, int width) {
		for(int i=0;i<width;i++) {
			for(int j=0;j<length;j++) {
				System.out.print(rec[i][j]);
			}
			System.out.println();
		}


}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Scanner sc=new Scanner(System.in);
	Rectangle rect = new Rectangle();
	
int length , width, shape;

System.out.print("Please select shape 1--> Square, 2--> Rectangle :");
shape =sc.nextInt();

if(shape==1) {
	System.out.print("Enter Length of the side:");
	length = sc.nextInt();
	String rec[][]=rect.inputMethod(length, length);
	rect.displayMethod(rec, length, length);
}
else if (shape == 2)
{
	System.out.print("Enter Length :");
	length = sc.nextInt();

	System.out.print("Enter Width :");
	width = sc.nextInt();
	String rec[][]=rect.inputMethod(length, width);
	rect.displayMethod(rec, length, width);
}
else {
	System.out.println("Please enter appropriate no.");
}
sc.close();
}

}
