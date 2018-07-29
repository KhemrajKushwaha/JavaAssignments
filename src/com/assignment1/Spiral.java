package com.assignment1;
import java.util.Scanner;

public class Spiral {
	public void spiralMethod(int x, int y, int radius) {
		int  iMid, jMid, m, n, back, front;	
		int i=0, j=0;
		n= (2*(radius+1)+1)+y;
		m= (2*(radius+1))+x;
		front = x;
		back=m-1;
		String arr[][] = new String[n][m];
		 iMid = y+radius+1;
		 jMid = x+radius;
		for(int o=0; o<n; o++){
			for(int o1=0; o1<m; o1++){
				arr[o][o1]=" ";
			}     
		}

		for ( i=y;i<n;i++){
			for( j=x;j<m;j++){
				if(i==iMid){                             //1st IF
					if(j<=jMid ){
						arr[i][j]="0";
					}
					else if (j==back){
						arr[i][j]="0";
					}
					else {
						arr[i][j]=" ";
					}
				} //end of 1st IF
				else if(i>y && i<=radius+y){  // Else IF 1 start
					if (j==back){
						arr[i][j]="0";
					}
					else {
						arr[i][j]=" ";
					}
				}  // end of Else IF 1 start
				else if(i>radius+y && i<n-1){  // Else IF 2 start
					if (j==back || j==front){
						arr[i][j]="0";
					}
					else {
						arr[i][j]=" ";
					}
				}  // end of Else IF 2 start
				else{
					arr[i][j]="0";
				}
			} // For J-loop
		}// For i-loop.
		for (int k=0; k<n;k++){
			for (int l=0; l<m;l++){
				System.out.print(arr[k][l]);
			}
			System.out.println();
		}
	}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);

	    int radius, x, y;
		System.out.print("Enter the X Co ordinate :");
		x=sc.nextInt();
		System.out.print("Enter the Y Co ordinate :");
		y=sc.nextInt();
		System.out.print("Enter the radius for spiral :");		
		radius=sc.nextInt();
		Spiral sprl = new Spiral();
		sprl.spiralMethod(x, y, radius);
		sc.close();
			}
}

