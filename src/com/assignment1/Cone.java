package com.assignment1;
import java.util.Scanner;

public class Cone extends Shapes{
	static int height;
	static char ch;
	Scanner sc=new Scanner(System.in);
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
	
	@Override
	public void addToDB(String[][] coneArr, int m, int n) throws Exception {
		// TODO Auto-generated method stub
		 for (int k=0; k<m;k++){
			 //System.out.print(radius+"\t");
			 StringBuffer st2= new StringBuffer();
			 for (int l=0; l<n;l++){
				 st2 = st2.append(coneArr[k][l]);
			 }
			 new DBConnect().dbConnect().executeUpdate("insert into cone values ("+height+",\'"+st2+"\')");
		 }
		
	}

	@Override
	public void undo(String[][] coneArr, int m, int n) throws Exception {
		// TODO Auto-generated method stub
        System.out.println("Do you want to delete this shape (y/n) :");
       ch=sc.next().charAt(0);
       if(ch=='y' || ch=='Y'){
                       new DBConnect().dbConnect().executeUpdate("Delete from cone where height="+height);
                       System.out.println("deleted");
                       System.out.println("Do you want to undo this action (y/n) :");
                       ch=sc.next().charAt(0);
                       if(ch=='y' || ch=='Y'){
                       coneArr=new Cone(height).inputMethod(m, n);
                       new Cone(height).addToDB(coneArr,m,n);
                       new Cone(height).displayMethod(coneArr,m,n);
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

	@Override
	public void redo() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Do you want to redo this action (y/n) :");
        ch=sc.next().charAt(0);
        if(ch=='y' || ch=='Y'){
       	 new DBConnect().dbConnect().executeUpdate("Delete from cone where height="+height);
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
