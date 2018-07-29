
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class SpiralPrint {

                static Connection con=null;
                static Statement stmt =null;
                static StringBuffer st2;
                public static void main(String[] args) throws SQLException {
                                int radius, radius1,m,n ;
                                boolean flag=false;
                                
                                String sprlArr[][];
                                Scanner sc=new Scanner(System.in);
                                System.out.print("Enter the radius for the spiral=");
                                radius = sc.nextInt();
                                Circle c= new Circle(radius);
                                n= 2*(radius+1)+1;
                                m= 2*(radius+1);
                                char ch;
                                sprlArr=c.inputMethod(m, n);
                                //c.printSpiral(radius, sprlArr);
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
                                                                addToDB(radius,sprlArr,m,n);
                                                                c.displayMethod( sprlArr,m,n);
                                                System.out.println("Do you want to redo this action (y/n) :");
                                                                ch=sc.next().charAt(0);
                                                                if(ch=='y' || ch=='Y'){
                                                                                stmt.executeUpdate("Delete from circle where radius="+radius);
                                                                                System.out.println("Redo Operation is done");
                                                                }
                                                                else{
                                                                                System.exit(0);
                                                                }
                                                }else{
                                                                c.displayMethod( sprlArr,m,n);
                                                                System.out.println("Do you want to delete this shape (y/n) :");
                                                                ch=sc.next().charAt(0);
                                                                if(ch=='y' || ch=='Y'){
                                                                                stmt.executeUpdate("Delete from circle where radius="+radius);
                                                                                System.out.println("deleted");
                                                                                System.out.println("Do you want to undo this action (y/n) :");
                                                                                ch=sc.next().charAt(0);
                                                                                if(ch=='y' || ch=='Y'){
                                                                                sprlArr=c.inputMethod(m, n);
                                                                                addToDB(radius,sprlArr,m,n);
                                                                                c.displayMethod(sprlArr,m,n);
                                                                                System.out.println("Undo Operation is done");
                                                                                }
                                                                                else{
                                                                                                System.exit(0);
                                                                                }
                                                                }
                                                                else{
                                                                System.exit(0);
                                                                }
                                                }
                                }
                                catch(Exception e){
                                                System.out.println(e);
                                                } 
                                finally{
                                                stmt.close();
                                                con.close();
                                }
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
}

