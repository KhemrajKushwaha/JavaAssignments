package com.assignment1;

import java.sql.*;
import java.util.Scanner;


public class Circle extends Shapes{
	static Scanner sc =new Scanner(System.in);
	static int radius;
	static char ch;
	Circle(int radius1){
		this.radius = radius1;
	}
	@Override
	public  String[][] inputMethod(int m, int n){
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
	
	@Override
	 public  void addToDB( String[][] sprlArr,int m ,int n) throws Exception{
		 for (int k=0; k<n;k++){
			 //System.out.print(radius+"\t");
			 StringBuffer st2= new StringBuffer();
			 for (int l=0; l<m;l++){
				 st2 = st2.append(sprlArr[k][l]);
			 }
			 new DBConnect().dbConnect().executeUpdate("insert into circle values ("+radius+",\'"+st2+"\')");
		 }
	 }
	 
	 public void redo() throws SQLException, Exception{
		 System.out.println("Do you want to redo this action (y/n) :");
         ch=sc.next().charAt(0);
         if(ch=='y' || ch=='Y'){
        	 new DBConnect().dbConnect().executeUpdate("Delete from circle where radius="+radius);
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
	 
	 @Override
	 public void undo(String cir[][], int m, int n) throws SQLException, Exception{

          System.out.println("Do you want to delete this shape (y/n) :");
         ch=sc.next().charAt(0);
         if(ch=='y' || ch=='Y'){
                         new DBConnect().dbConnect().executeUpdate("Delete from circle where radius="+radius);
                         System.out.println("deleted");
                         System.out.println("Do you want to undo this action (y/n) :");
                         ch=sc.next().charAt(0);
                         if(ch=='y' || ch=='Y'){
                         cir=new Circle(radius).inputMethod(m, n);
                         new Circle(radius).addToDB(cir,m,n);
                         new Circle(radius).displayMethod(cir,m,n);
                         System.out.println("Undo Operation is done");
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
