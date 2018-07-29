package com.assignment1;

import java.sql.SQLException;

public abstract class Shapes {
public abstract String[][] inputMethod(int m, int n);
public abstract void displayMethod(String[][] arr, int m, int n);
public abstract void addToDB( String[][] Arr,int m ,int n) throws Exception ;
public abstract void undo(String [][]arr, int  m, int n)throws Exception;
public abstract void redo() throws Exception ;


}
