//Syed Muhammad Hamza Raza
//ERP NO:17887
import java.util.*;
import java.io.*;
import java.awt.Color;
public class MountainPath
{     
  public static int[][] grid;
  
  //Main Function
  
  public static void main(String[] args)
  { 
    StdDraw.enableDoubleBuffering();
    Scanner in = new Scanner(System.in);
    System.out.print("Enter the file name: ");
    String filename = in.nextLine();
    System.out.print("Enter the number of rows: ");
    int rows = in.nextInt();
    System.out.print("Enter the number of columns: ");
    int cols = in.nextInt();
    init(filename,rows,cols);
    Drawmap();
    System.out.println ("The Lowest Index of elevation is: "+indexofLowestElevPath());
    System.out.print("Enter the row for lowest elevation path: ");
    int row=in.nextInt();
    StdDraw.setPenColor(StdDraw.GREEN);
    System.out.println ("The Lowest elevation of row given by you is:"+DrawLowestElevPath(row));
    StdDraw.show();
  }
  
  //Constructor
  public static void init(String Filename,int rows,int columns)
  { 
    Scanner in=null;
    File file = new File(Filename);
    try 
    {
      in = new Scanner(file);
    } 
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
      System.exit(1);   
    }
    grid=new int[rows][columns];
    for (int i=0;i<rows;i++)
    {
      for(int j=0;j<columns;j++)
        grid[i][j]=in.nextInt();
    }
  }
  
  // Minimum 
  
  public static int findmin()
  {
    int min=Integer.MAX_VALUE;
    for (int i=0;i<grid.length;i++)
    {
      for(int j=0;j<grid[0].length;j++)
      {
        if(grid[i][j]<min)
          min=grid[i][j];
      }
    }
    return min;
  }
  
  // Maximum
  
  public static int findmax()
  {
    int max=Integer.MIN_VALUE;
    for (int i=0;i<grid.length;i++)
    {
      for(int j=0;j<grid[0].length;j++)
      {
        if(grid[i][j]>max)
          max=grid[i][j];
      }
    }
    return max;
  }
  
  // Drawing
  
  public static void Drawmap(){
    double rng=(findmax()-findmin());
    
    StdDraw.setCanvasSize(grid[0].length, grid.length);
    StdDraw.setXscale(0.0,(double)grid[0].length);
    StdDraw.setYscale(0.0,(double)grid.length);
    for(int i=0;i<grid.length;i++)
    {
      for(int j=0;j<grid[0].length;j++)
      {
        int  c=(int)(((grid[grid.length-i-1][j]-findmin())/rng)*255);
        StdDraw.setPenColor(new Color(c,c,c));
        StdDraw.filledSquare(j,i,0.5);
      }
    }
  }
  
  //index of min row
  
  public static int indexOfMinRow(int col)
  {
    int min=0;
    int indexmin=0;
    for(int i=0;i<grid.length;i++)
    {
      if(grid[i][col]<min)
      {
        indexmin=i;
        min=grid[i][col];
      }
    }
    return indexmin;}
  
  // Lowest elevation
  
  public static int DrawLowestElevPath(int srow) 
  {
    int elev = 0;int fwd=0;int fwd_up=0;int fwd_down=0;
    for (int col = 0; col < grid[srow].length-1; col++) 
    {
      if (srow == 0)
      {
        fwd = Math.abs(grid[srow][col+1] - grid[srow][col]);
        fwd_up = Math.abs(grid[srow+1][col+1] - grid[srow][col]);
        if ((fwd < fwd_up) || (fwd == fwd_up))
        {
          StdDraw.filledSquare((double)col+1,(double)srow,0.5);
          elev += fwd;
        }
        else
        {
          StdDraw.filledSquare((double)col+1,(double)srow+1,0.5);
          srow++;
          elev += fwd_up;
        }
      }
      
      else if(srow==(grid.length-1))
      {
        fwd = Math.abs(grid[srow][col+1] - grid[srow][col]);
        fwd_down = Math.abs(grid[srow-1][col+1] - grid[srow][col]);
        if((fwd==fwd_down)||(fwd<fwd_down))
        {
          StdDraw.filledSquare((double)col+1,(double)srow,0.5);
          elev+=fwd;
        }
        else 
        {
          StdDraw.filledSquare((double)col+1,(double)srow-1, 0.5);
          elev+=fwd_down;
          srow--;
        } 
      }
      
      else
      {
        fwd = Math.abs(grid[srow][col+1] - grid[srow][col]);
        fwd_up = Math.abs(grid[srow+1][col+1] - grid[srow][col]);
        fwd_down = Math.abs(grid[srow-1][col+1] - grid[srow][col]);
        if ((((fwd==fwd_up)&&(fwd==fwd_down))||((fwd<fwd_down)&&(fwd<fwd_up)))||(((fwd==fwd_up)&&(fwd<fwd_down))||((fwd==fwd_down)&&(fwd<fwd_up))))
        {
          StdDraw.filledSquare((double)col+1,(double)srow,0.5);
          elev += fwd;
        }
        else if ((fwd_up < fwd) && (fwd_up < fwd_down))
        {
          StdDraw.filledSquare((double)col+1,(double)srow+1,0.5);
          srow++;
          elev += fwd_up;
        }
        else if ((fwd_down < fwd) && (fwd_down < fwd_up)) 
        {
          StdDraw.filledSquare((double)col+1,(double)srow-1,0.5);
          srow--;
          elev += fwd_down;
        }
        else if(fwd_up==fwd_down)
        {
          if (Math.random() < 0.5) 
          {
            StdDraw.filledSquare((double)col+1,(double)srow-1,0.5);
            srow--;
            elev += fwd_down;
          }
          else 
          {
            StdDraw.filledSquare((double)col+1,(double)srow+1,0.5);
            srow++;
            elev += fwd_up;
          }
        }
      }
    }
    return elev;
  }
  
  //index of lowest elevation
  
  public static int indexofLowestElevPath()
  {
    int minelev=Integer.MAX_VALUE;
    int index=0;
    StdDraw.setPenColor(StdDraw.RED);
    for(int i=0;i<grid.length;i++)
    {
      int elev=DrawLowestElevPath(i);
      if( elev<minelev)
      {
        minelev=elev;
        index=i;
      }
    }
    return index;
  }
}

