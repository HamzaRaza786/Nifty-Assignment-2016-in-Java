import java.io.*;
import java.util.Scanner;
public class MovieReviews
{
  public static Scanner keyboard = new Scanner(System.in);
    public static String filename;
  
  public static double[] readfile(String word)throws FileNotFoundException 
  {
    File reviewFile = new File(filename);
    Scanner reviewScanner=null;
    reviewScanner = new Scanner(reviewFile);
    int reviewScore=0;
    String reviewText;
    int Score=0;
    int contains=0;
    double[] arr=new double[2];
    while(reviewScanner.hasNext())
    {
      reviewScore = reviewScanner.nextInt();
      reviewText = reviewScanner.nextLine();
      
      if(reviewText.contains(word))
      {Score+=reviewScore;
        contains++;
      }
    }
    arr[0]=1.0*contains;
    arr[1]=(double)Score/contains;
    return arr;
  }
  public static String readword()
  {
    String word=keyboard.nextLine();
    return word; 
  }
  public static void main(String[] args)throws FileNotFoundException
  {
     System.out.print("Input Filename which you want to use in this program");
    filename=keyboard.nextLine();
    int choice=menu();String[] arr1; String word=readword();double sort=0.0;
    switch(choice)
    {
      case 1:System.out.print("Enter Word: ");
      word=readword();
      double[] ans=readfile(word);
      System.out.println(word+" appears "+(int)ans[0]+" times");
      System.out.printf("The average score for reviews containing the word '" +word+"' is "+"%.3f\n",ans[1]);
      break;
      case 2:
        System.out.print("Enter the name of the file with words you want to find the average score for: ");
        arr1= Userfile();
        sort=Double.parseDouble(arr1[0]);
        System.out.printf("The average score of words in "+arr1[1]+" is "+"%.3f\n",sort);
        if(sort<=1.99)
          System.out.println("The overall sentiment of "+arr1[1]+"  is negative");
        else if(sort>=2.01)
          System.out.println( "The overall sentiment of "+arr1[1]+"  is positive");
        break;
      case 3:
        System.out.print("Enter the name of the file with words you want to score: ");
        arr1=Sortfile();
        System.out.printf("The most positive word, with a score of  "+"%.3f" +" is ’"+arr1[1]+"’",Double.parseDouble(arr1[0]));
        System.out.println();
        System.out.printf("The most negative word, with a score of  "+"%.3f" +" is ’"+arr1[3]+"’",Double.parseDouble(arr1[2]));
        System.out.println();break;
      case 4:
        System.out.print("Enter the name of the file with words, you want to create new files: ");
        Createfile();
        break;
      case 5:break;
      default: break;
    }
  }
  
  
  
  public static String[] Userfile()throws FileNotFoundException
  {
    String filename=keyboard.nextLine();
    double sum=0;
    Scanner reviewchckfile=null;
    File checkfile = new File(filename);
    reviewchckfile = new Scanner(checkfile);
    double[] arr;
    String[] arr1=new String[2];
    String fileword;
    int cnt=0;
    while(reviewchckfile.hasNext())
    {
      fileword=reviewchckfile.nextLine();
      arr=readfile(fileword);
      sum+= arr[1];
      cnt++;
    }
    double ans=sum/cnt;
    arr1[0]=Double.toString(ans);
    arr1[1]=filename;
    return arr1;
  }
  public static String[] Sortfile()throws FileNotFoundException
  {
    String filename=keyboard.nextLine();
    Scanner reviewchckfile=null;
    File checkfile = new File(filename);
    reviewchckfile = new Scanner(checkfile);
    double[] arr;
    String[] arr1=new String[4];
    String fileword;
    int cnt=0;
    String ans="";
    String ans1="";
    double min=Integer.MAX_VALUE;
    double max=Integer.MIN_VALUE;
    while(reviewchckfile.hasNext())
    {
      fileword=reviewchckfile.nextLine();
      arr=readfile(fileword);
      if(arr[1]<min){
        min=arr[1];
        ans=fileword;}
      if(arr[1]>max){
        max=arr[1];
        ans1=fileword;
      }
    }
    arr1[0]=Double.toString(max); 
    arr1[1]=ans1;
    arr1[2]=Double.toString(min);
    arr1[3]=ans;
    return arr1;}
  public static void Createfile()throws FileNotFoundException
  {
    String userf=keyboard.nextLine();
    double[] arr;
    PrintWriter out=new PrintWriter("positive.txt");
    PrintWriter in=new PrintWriter("negative.txt");
    Scanner reviewfile=null;
    File create=new File(userf);
    reviewfile = new Scanner(create);
    while (reviewfile.hasNext())
    {
      String word=reviewfile.nextLine();
      arr=readfile(word);
      if(arr[1]<1.9)
        in.println(word);
      else if(arr[1]>2.1)
        out.println(word);
    }
    in.close();
    out.close();
  }
  public static int  menu()
  {
    System.out.println("What would you like to do?");
    System.out.println("1: Get the score of a word");
    System.out.println("2: Get the overall score of words in a file one word per line");
    System.out.println("3: Find the highest/lowest scoring words in a file");
    System.out.println("4: Sort words from a file into positive.txt and negative.txt");
    System.out.println("5: Exit the program");
    System.out.print("Enter a number 1-5:");
    int num=keyboard.nextInt();
    return num;
  }
}

