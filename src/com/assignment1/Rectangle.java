package com.assignment1;
import java.sql.SQLException;
import java.util.*;

public class Rectangle extends Shapes{
	static Scanner sc =new Scanner(System.in);
	static StringBuffer st2;
	static char ch;
	static int length, width;
	Rectangle(int length, int width){
		this.length = length;
		this.width=width;
	}
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
	
	public  void addToDB(String[][] sprlArr,int length, int width) throws Exception{
		for (int k=0; k<width;k++){
			//System.out.print(radius+"\t");
			st2= new StringBuffer();
			for (int l=0; l<length;l++){
				st2 = st2.append(sprlArr[k][l]);
			}
			new DBConnect().dbConnect().executeUpdate("insert into rectangle values ("+length+","+width+",\'"+st2+"\')");
		}
	}
	// Redo Function
	@Override
	 public void redo() throws SQLException, Exception{
		 System.out.println("Do you want to redo this action (y/n) :");
         ch=sc.next().charAt(0);
         if(ch=='y' || ch=='Y'){
        	 new DBConnect().dbConnect().executeUpdate("Delete from rectangle where length="+length+" and width="+width);
                         System.out.println("Redo Operation is done");
                         System.out.print("Do you want any other shape (y/n) ::");
         				ch = sc.next().charAt(0);
         				if(ch=='y' || ch == 'Y') {
         					new TestShape().input1(new TestShape().input());
         				}
         				else { System.exit(0);}
         }
         else{
        	 System.out.print("Do you want any other shape (y/n) ::");
				ch = sc.next().charAt(0);
				if(ch=='y' || ch == 'Y') {
					new TestShape().input1(new TestShape().input());
				}
				else { System.exit(0);}
         }
	 }
	 
	 //Undo Function
	 @Override
	 public void undo(String rec[][], int length, int width) throws SQLException, Exception{

         System.out.println("Do you want to delete this shape (y/n) :");
        ch=sc.next().charAt(0);
        if(ch=='y' || ch=='Y'){
        	new DBConnect().dbConnect().executeUpdate("Delete from rectangle where length="+length+" and width="+width);
                        System.out.println("deleted");
                        System.out.println("Do you want to undo this action (y/n) :");
                        ch=sc.next().charAt(0);
                        if(ch=='y' || ch=='Y'){
                        rec=new Rectangle(length,width).inputMethod(length,width);
                        new Rectangle(length, width).addToDB(rec,length,width);
                        new Rectangle(length,width).displayMethod(rec,length,width);
                        System.out.println("Undo Operation is done");
                        System.out.print("Do you want any other shape (y/n) ::");
         				ch = sc.next().charAt(0);
         				if(ch=='y' || ch == 'Y') {
         					new TestShape().input1(new TestShape().input());
         				}
         				else { System.exit(0);}
                        }
                        else{
                        	 System.out.println("Do you want to undo this action (y/n) :");
                        	 ch=sc.next().charAt(0);
                             if(ch=='y' || ch=='Y'){
                        	 new TestShape().input1(new TestShape().input());
                             }
                        }
        }
        else{
       	 System.out.print("Do you want any other shape (y/n) ::");
				ch = sc.next().charAt(0);
				if(ch=='y' || ch == 'Y') {
					new TestShape().input1(new TestShape().input());
				}
				else { System.exit(0);}
        }
	 }
	 
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Scanner sc=new Scanner(System.in);
	
int length , width, shape;

System.out.print("Please select shape 1--> Square, 2--> Rectangle :");
shape =sc.nextInt();

if(shape==1) {
	System.out.print("Enter Length of the side:");
	length = sc.nextInt();
	Rectangle rect = new Rectangle(length,length);
	String rec[][]=rect.inputMethod(length, length);
	rect.displayMethod(rec, length, length);
}
else if (shape == 2)
{
	System.out.print("Enter Length :");
	length = sc.nextInt();

	System.out.print("Enter Width :");
	width = sc.nextInt();
	Rectangle rect = new Rectangle(length,width);
	String rec[][]=rect.inputMethod(length, width);
	rect.displayMethod(rec, length, width);
}
else {
	System.out.println("Please enter appropriate no.");
}
sc.close();
}

}
