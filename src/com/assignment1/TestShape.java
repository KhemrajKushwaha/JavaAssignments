package com.assignment1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TestShape {
	static Scanner sc = new Scanner(System.in);
	static Connection con=null;
	static Statement stmt =null;
	static StringBuffer st2;

	public int input() {
		System.out.print("Enter the choice for shape 1--> Circle  2--> Rectangle/Square 3-->Cone  ::");
		return  sc.nextInt();
	}
	public void input1(int input) throws Exception {
		int  radius, length, width,m,n, recChoice,radius1,height,height1;
		boolean flag=false;
		char ch;
		if (input == 1) {
			//Circle
			System.out.print("Enter radius for circle ::");
			radius = sc.nextInt();
			Shapes s=new Circle(radius);
			m=radius*2+1;
			n=radius*2+1;
			String cir[][]=s.inputMethod(m, n);
			try{ 
				ResultSet rs=new DBConnect().dbConnect().executeQuery("select radius, dots from circle");
				while(rs.next()){
					radius1 = rs.getInt("radius");
					if (radius1 == radius){
						System.out.println("Already shape exist");
						flag=true;
						break;
					}
				}
				if(!flag){
					 s.addToDB(cir,m,n);
					s.displayMethod( cir,m,n);
					// Perform Redo Operation
					s.redo();
				}
				else{
					s.displayMethod( cir,m,n);
					//Performs Undo Operation
					((Circle) s).undo( cir, m,n);
				}
			}  catch(Exception e){
				System.out.println(e);
			} 
			finally{
				new DBConnect().dbClose();
			}
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
			System.out.print("Please select shape 1--> Square, 2--> Rectangle ::");
			recChoice =sc.nextInt();

			if(recChoice==1) {
				
				System.out.print("Enter length of the side:");
				length = sc.nextInt();
				Shapes rec=new Rectangle(length,length);
				String rect[][]=rec.inputMethod(length, length);
				try{ 
					ResultSet rs=new DBConnect().dbConnect().executeQuery("select length, width from rectangle");
					while(rs.next()){
						int length2 = rs.getInt("length");
						int  length1 = rs.getInt("width");
						if (length1 == length && length2 ==length){
							System.out.println("Already shape exist");
							flag=true;
							break;
						}
					}
						if(!flag){
							rec.addToDB(rect,length,length);
							rec.displayMethod(rect,length,length);
							//Perform Redo operation
							rec.redo();
						}
						else {
							rec.displayMethod(rect, length, length);
							((Rectangle) rec).undo(rect, length, length);
						} 
					}catch(Exception e){
							System.out.println(e);
						} 
						finally{
							new DBConnect().dbClose();
						}
					
				
					/*	System.out.print("Do you want any other shape (y/n) ::");
						ch = sc.next().charAt(0);
						if(ch=='y' || ch == 'Y') {
							input = input();
							input1(input);
						}
						else { System.exit(0);}*/
					}
			else if (recChoice == 2)
			{
				System.out.print("Enter Length :");
				length = sc.nextInt();
				System.out.print("Enter Width :");
				width = sc.nextInt();
				Shapes rec=new Rectangle(length,length);
				String rect[][]=rec.inputMethod(length, width);
				try{ 
					ResultSet rs=new DBConnect().dbConnect().executeQuery("select length, width from rectangle");
					while(rs.next()){
						int length2 = rs.getInt("length");
						int  length1 = rs.getInt("width");
						if ((length2 == length )&& (length1 ==width)){
							System.out.println("Already shape exist");
							flag=true;
							break;
						}
					}
						if(!flag){
							rec.addToDB(rect,length,width);
							rec.displayMethod(rect,length,width);
							//Perform Redo operation
							rec.redo();
						}
						else {
							rec.displayMethod(rect, length, width);
							rec.undo(rect, length, width);
						} 
					}catch(Exception e){
							System.out.println(e);
						} 
						finally{
							new DBConnect().dbClose();
						}
				
			}
			
			else {
				System.out.println("Wrong input");
			}
			System.out.print("Do you want any other shape (y/n) ::");
			ch = sc.next().charAt(0);
			if(ch=='y' || ch == 'Y') {
				input = input();
				input1(input);
			}
			else { System.exit(0);}
		}
		else if (input == 3) {
			
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter Height for cone ::");
            height=sc.nextInt();
			Shapes cone = new Cone(height);
			String coneArr[][];
			m=height+(height/2)+1;
			n=height*2;
			coneArr=cone.inputMethod(m, n);
		//	cone.displayMethod(coneArr, m, n);
			try{ 
				ResultSet rs=new DBConnect().dbConnect().executeQuery("select height, dots from cone");
				while(rs.next()){
					height1 = rs.getInt("height");
					if (height1 == height){
						System.out.println("Already shape exist");
						flag=true;
						break;
					}
				}
				if(!flag){
					 cone.addToDB(coneArr,m,n);
					cone.displayMethod(coneArr,m,n);
					// Perform Redo Operation
					cone.redo();
				}
				else{
					cone.displayMethod(coneArr,m,n);
					//Performs Undo Operation
					cone.undo(coneArr, m,n);
				}
			}  catch(Exception e){
				System.out.println(e);
			} 
			finally{
				new DBConnect().dbClose();
			}
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
				input = input();
				input1(input);
		}

	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TestShape spc = new TestShape();
		int input = spc.input();
		spc.input1(input);
		sc.close();
	}
}
