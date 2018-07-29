import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestShape {
	static Scanner sc = new Scanner(System.in);
	 static Connection con=null;
     static Statement stmt =null;
     static StringBuffer st2;
   
	public int input() {
			System.out.print("Enter the choice for shape 1--> Circle  2--> Rectangle/Square 3-->Cone ::");
	        return  sc.nextInt();
	}
	public void input1(int input) throws SQLException {
		int  radius, length, width,m,n, recChoice,radius1;
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

                //  Class.forName("com.mysql.jdbc.Driver"); 
                   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shapes?useSSL=false","root","admin"); 
                  //here shapes is database name, root is username and password 
                  stmt=con.createStatement(); 
                  ResultSet rs=stmt.executeQuery("select radius, dots from circle");
                  while(rs.next()){
                                  radius1 = rs.getInt("radius");
                                  if (radius1 == radius){
                                                  System.out.println("Already shape exist");
                                                  flag=true;
                                                  break;
                                                  }
                  }
                  if(!flag){
                      addToDB(radius,cir,m,n);
                      s.displayMethod( cir,m,n);
      System.out.println("Do you want to redo this action (y/n) :");
                      ch=sc.next().charAt(0);
                      if(ch=='y' || ch=='Y'){
                                      stmt.executeUpdate("Delete from circle where radius="+radius);
                                      System.out.println("Redo Operation is done");
                      }
                      else{
                    	  input = input();
    						input1(input);
                      }
      }
                  else{
                      s.displayMethod( cir,m,n);
                      System.out.println("Do you want to delete this shape (y/n) :");
                      ch=sc.next().charAt(0);
                      if(ch=='y' || ch=='Y'){
                                      stmt.executeUpdate("Delete from circle where radius="+radius);
                                      System.out.println("deleted");
                                      System.out.println("Do you want to undo this action (y/n) :");
                                      ch=sc.next().charAt(0);
                                      if(ch=='y' || ch=='Y'){
                                      cir=s.inputMethod(m, n);
                                      addToDB(radius,cir,m,n);
                                      s.displayMethod(cir,m,n);
                                      System.out.println("Undo Operation is done");
                                      }
                                      else{
                                    	  input = input();
                  						input1(input);
                                      }
                      }
                      else{
                    	  input = input();
    						input1(input);
                      }
      }
			}  catch(Exception e){
                System.out.println(e);
                } 
finally{
                stmt.close();
                con.close();
}
		//	s.displayMethod(cir, m, n);
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
				
				try{ 

	                //  Class.forName("com.mysql.jdbc.Driver"); 
	                   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shapes?useSSL=false","root","admin"); 
	                  //here shapes is database name, root is username and password 
	                  stmt=con.createStatement(); 
	                  ResultSet rs=stmt.executeQuery("select length, width from rectangle");
	                  while(rs.next()){
	                                 int length2 = rs.getInt("length");
	                                int  length1 = rs.getInt("width");
	                                  if (length1 == length && length2 ==length){
	                                                  System.out.println("Already shape exist");
	                                                  flag=true;
	                                                  break;
	                                                  }
	                                  if(!flag){
	                                      addToDBRect(rect,length,length);
	                                      rec.displayMethod( rect,length,length);
	                      System.out.println("Do you want to redo this action (y/n) :");
	                                      ch=sc.next().charAt(0);
	                                      if(ch=='y' || ch=='Y'){
	                                                      stmt.executeUpdate("Delete from rectangle where length="+length+"and width="+length);
	                                                      System.out.println("Redo Operation is done");
	                                      }
	                                      else{
	                                    	  input = input();
	                    						input1(input);
	                                      }
	                      }
	                  }
	                  rec.displayMethod(rect, length, length);
				System.out.print("Do you want any other shape (y/n) ::");
				ch = sc.next().charAt(0);
				if(ch=='y' || ch == 'Y') {
						input = input();
						input1(input);
				}
				else { System.exit(0);}
			}finally{
                stmt.close();
                con.close();
}
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
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		TestShape spc = new TestShape();
		int input = spc.input();
		spc.input1(input);
		sc.close();
	}
	  public static void addToDB(int radius, String[][] sprlArr,int m ,int n) throws SQLException{
          for (int k=0; k<n;k++){
                                          //System.out.print(radius+"\t");
                                          st2= new StringBuffer();
                                          for (int l=0; l<m;l++){
                                                          st2 = st2.append(sprlArr[k][l]);
                                          }
                                          stmt.executeUpdate("insert into circle values ("+radius+",\'"+st2+"\')");
                          }
	  }
          public static void addToDBRect(String[][] sprlArr,int length, int width) throws SQLException{
              for (int k=0; k<width;k++){
                                              //System.out.print(radius+"\t");
                                              st2= new StringBuffer();
                                              for (int l=0; l<length;l++){
                                                              st2 = st2.append(sprlArr[k][l]);
                                              }
                                              stmt.executeUpdate("insert into rectangle values ("+length+","+width+",\'"+st2+"\')");
                              }
          }
}
