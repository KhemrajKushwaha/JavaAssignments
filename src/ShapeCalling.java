import java.util.Scanner;

public class ShapeCalling {
	static Scanner sc = new Scanner(System.in);
	public int input() {
			System.out.print("Enter the choice for shape 1--> Circle  2--> Rectangle/Square 3-->Cone ::");
	        return  sc.nextInt();
	}
	public void input1(int input) {
		int  radius, length, width,m,n, recChoice;
		char ch;
		if (input == 1) {
			//Circle
			System.out.print("Enter radius for circle ::");
			radius = sc.nextInt();
			Shapes s=new Circle(radius);
			m=radius*2+1;
			n=radius*2+1;
			String cir[][]=s.inputMethod(m, n);
			s.displayMethod(cir, m, n);
			System.out.print("Do you want any other shape (y/n) ::");
			ch = sc.next().charAt(0);
			if(ch=='y' || ch == 'Y') {
				input = input();
				input1(input);
			}
			else { System.exit(0);}
		}
		
		else if(input == 2) {
					//Rectangle
			Shapes rec=new Rectangle();
			System.out.print("Please select shape 1--> Square, 2--> Rectangle ::");
			recChoice =sc.nextInt();

			if(recChoice==1) {
				System.out.print("Enter length of the side:");
				length = sc.nextInt();
				String rect[][]=rec.inputMethod(length, length);
				rec.displayMethod(rect, length, length);
				System.out.print("Do you want any other shape (y/n) ::");
				ch = sc.next().charAt(0);
				if(ch=='y' || ch == 'Y') {
						input = input();
						input1(input);
				}
				else { System.exit(0);}
			}
			else if (recChoice == 2)
			{
				System.out.print("Enter Length :");
				length = sc.nextInt();
				System.out.print("Enter Width :");
				width = sc.nextInt();
				String rect[][]=rec.inputMethod(length, width);
				rec.displayMethod(rect, length, width);
				System.out.print("Do you want any other shape (y/n) ::");
				ch = sc.next().charAt(0);
				if(ch=='y' || ch == 'Y') {
					input = input();
					input1(input);
				}
				else { System.exit(0);}
			}
			else {
				System.out.println("Wrong input");
			}
		}
		else if (input == 3) {
			System.out.println("Cone is under developement");
			System.out.print("Do you want any other shape (y/n) ::");
			ch = sc.next().charAt(0);
			if(ch=='y' || ch == 'Y') {
				input = input();
				input1(input);
			}
			else { System.exit(0);}
		}
		else {
			System.out.println("Enter a valid choice");
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShapeCalling spc = new ShapeCalling();
		int input = spc.input();
		spc.input1(input);
		sc.close();
	}

}
