package com.example.numberbonds;

import java.io.IOException; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context; 
import android.database.Cursor; 
import android.database.SQLException; 
import android.database.sqlite.SQLiteDatabase; 
import android.util.Log; 
public class TestAdapter  
{ 
    protected static final String TAG = "DataAdapter"; 
 
    private final Context mContext; 
    private SQLiteDatabase mDb; 
    private DataBaseHelper mDbHelper; 
    int startIndex, endIndex;

    public TestAdapter(Context context){ 
        this.mContext = context; 
        mDbHelper = new DataBaseHelper(mContext); 
    } 
    public TestAdapter createDatabase() throws SQLException{ 
        try{ 
            mDbHelper.createDataBase(); 
        }catch (IOException mIOException){ 
            Log.e(TAG, mIOException.toString() + "UnableToCreateDatabase"); 
            throw new Error("UnableToCreateDatabase");
        } 
        return this; 
    } 
    public TestAdapter open() throws SQLException{ 
        try{ 
            mDbHelper.openDataBase(); 
            mDbHelper.close(); 
            mDb = mDbHelper.getReadableDatabase(); 
        }catch (SQLException mSQLException){ 
            Log.e(TAG, "open >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
        return this; 
    } 
    public void close(){ 
        mDbHelper.close(); 
    } 
    
    /*********************************************Initiate the database**********************************************/
    public String insertStudent(String name, ArrayList<String> ques) 
    { 
    	String TABLE_NAME="feedback";
    	String id=null;
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
    	try 
        { 
        ContentValues initialValues = new ContentValues();

        String dateString= dateFormat.format(date);

        initialValues.put("name",name);
        initialValues.put("date",dateString);
        initialValues.put("level","");
        initialValues.put("question1",ques.get(0));
        initialValues.put("question2",ques.get(1));
        initialValues.put("question3",ques.get(2));
        initialValues.put("question4",ques.get(3));
        initialValues.put("question5",ques.get(4));
        initialValues.put("question6",ques.get(5));
        initialValues.put("question7",ques.get(6));
        initialValues.put("question8",ques.get(7));
        initialValues.put("question9",ques.get(8));
        initialValues.put("question10",ques.get(9));
        initialValues.put("totalmistakes","");
        long recordID=mDb.insert(TABLE_NAME, null, initialValues);
        id= Long.toString(recordID);
        System.out.println("in insert record, id is :"+id);
        return id;
        } 
    	catch (SQLException mSQLException){ 
            throw mSQLException; 
        } 
     } 

    /*********************************************Fetch all student data**********************************************/
  /*  public ArrayList<Student> browseAllStudent() 
    { 
    	ArrayList<Student> records=new ArrayList<Student>();
        try 
        { 
        	String sql ="select * from score"; 
        	Cursor cursor = mDb.rawQuery(sql,null); 
            if (cursor.moveToFirst())
            {
           	   do
           	   {   
           		  Student record = new Student(); 
           	      String id=cursor.getString(cursor.getColumnIndex("_id"));
           	      String name=cursor.getString(cursor.getColumnIndex("name"));
           	      String date=cursor.getString(cursor.getColumnIndex("date"));
           	      String level1=cursor.getString(cursor.getColumnIndex("level1"));
           	      String level2=cursor.getString(cursor.getColumnIndex("level2"));
           	      String level3=cursor.getString(cursor.getColumnIndex("level3"));
           	      String level4=cursor.getString(cursor.getColumnIndex("level4"));
           	      String level5=cursor.getString(cursor.getColumnIndex("level5"));
           	      String level6=cursor.getString(cursor.getColumnIndex("level6"));
           	      String level7=cursor.getString(cursor.getColumnIndex("level7"));

           	      //adding to ArrayList
           	      record.setId(id);
           	      record.setName(name);
           	      record.setDate(date);
           	      record.setLevel1(level1);
           	      record.setLevel2(level2);
           	      record.setLevel3(level3);
           	      record.setLevel4(level4);
           	      record.setLevel5(level5);
           	      record.setLevel6(level6);
           	      record.setLevel7(level7);
            	      
           	      records.add(record);
           	     
           	    }while(cursor.moveToNext());
             }
            cursor.close();            
            return records;
        } 
        catch (SQLException mSQLException)  
        { 
            throw mSQLException; 
        } 
     } 
    
    /*********************************************Fetch student data by name**********************************************/
   /* public ArrayList<Student> browseStudentByName(String username) 
    { 
    	ArrayList<Student> records=new ArrayList<Student>();
        try 
        { 
        	String sql ="select * from score where name like ?"; 
        	Cursor cursor = mDb.rawQuery(sql,new String[] { username }); 
            if (cursor.moveToFirst())
            {
           	   do
           	   { 
           		  Student record = new Student(); 
          	      String id=cursor.getString(cursor.getColumnIndex("_id"));
          	      String name=cursor.getString(cursor.getColumnIndex("name"));
          	      String date=cursor.getString(cursor.getColumnIndex("date"));
          	      String level1=cursor.getString(cursor.getColumnIndex("level1"));
          	      String level2=cursor.getString(cursor.getColumnIndex("level2"));
          	      String level3=cursor.getString(cursor.getColumnIndex("level3"));
          	      String level4=cursor.getString(cursor.getColumnIndex("level4"));
          	      String level5=cursor.getString(cursor.getColumnIndex("level5"));
          	      String level6=cursor.getString(cursor.getColumnIndex("level6"));
          	      String level7=cursor.getString(cursor.getColumnIndex("level7"));
 
          	      //adding to ArrayList
          	      record.setId(id);
          	      record.setName(name);
          	      record.setDate(date);
          	      record.setLevel1(level1);
          	      record.setLevel2(level2);
          	      record.setLevel3(level3);
          	      record.setLevel4(level4);
          	      record.setLevel5(level5);
          	      record.setLevel6(level6);
          	      record.setLevel7(level7);
       	      
        	      records.add(record);
           	     
           	    }while(cursor.moveToNext());
             }
            cursor.close();            
            return records;
        } 
        catch (SQLException mSQLException)  
        { 
            throw mSQLException; 
        } 
     } 
    /*********************************************Fetch student data by name**********************************************/
    public ArrayList<String> browseStudentByGroup() 
    { 
    	ArrayList<String> records=new ArrayList<String>();
        try 
        { 
        	String sql ="select * from feedback group by name"; 
        	Cursor cursor = mDb.rawQuery(sql,null); 
            if (cursor.moveToFirst())
            {
               records.add("All");
           	   do
           	   {            		   
           		  String username=cursor.getString(cursor.getColumnIndex("name"));
	              String name=username.substring(0, 1).toUpperCase() + username.substring(1);
        	      records.add(name);
           	     
           	    }while(cursor.moveToNext());
             }
            cursor.close();            
            return records;
        } 
        catch (SQLException mSQLException)  
        { 
            throw mSQLException; 
        } 
     } 
    /*********************************************Update Level**********************************************/
    public void update(String level,int id,String response,String questionNo,String mistakes) 
    { 
    	String TABLE_NAME="feedback";
    	String q=new String();
    	try 
        { 
    		
        ContentValues initialValues = new ContentValues();
        System.out.println("In Update: Qno:"+questionNo+"response:"+response);
        
        String sql ="select "+questionNo+"  from feedback where _id="+((Integer)id).toString(); 
    	Cursor cursor = mDb.rawQuery(sql,null); 
        if (cursor.moveToFirst())
        {
          // records.add("All");
       	  // do
       	  // {            		   
       		  q=cursor.getString(cursor.getColumnIndex(questionNo));
              //String name=username.substring(0, 1).toUpperCase() + username.substring(1);
       		  response = q + response;
       	     
       	  //  }while(cursor.moveToNext());
         }
        cursor.close(); 
        System.out.println("Query fetched, Updated Response: "+response);
        
        initialValues.put(questionNo,response);
        System.out.println("Mistakes:"+mistakes);
        initialValues.put("totalmistakes",mistakes);
        System.out.println("Level:"+level);
        initialValues.put("level",level);
        mDb.update(TABLE_NAME, initialValues, "_id=" + id, null);
        
        }
    	catch (SQLException mSQLException)  
        { 
            throw mSQLException; 
        } 
    }
    public void deleteRecord(String id) 
    {
    	mDb.delete("feedback","_id" + "=?",new String[] {id});
    }
    
    
    public ArrayList<Student> extract() 
    { 
    	ArrayList<Student> records=new ArrayList<Student>();
        try 
        { 
        	String sql ="select * from feedback"; 
        	Cursor cursor = mDb.rawQuery(sql,null); 
            if (cursor.moveToFirst())
            {
           	   do
           	   {   
           		  //Student record = new Student(); 
           	      //String id=cursor.getString(cursor.getColumnIndex("_id"));
           	      String name=new String(cursor.getString(cursor.getColumnIndex("name")));
           	      String date= new String(cursor.getString(cursor.getColumnIndex("date")));
           	      //int level = Integer.valueOf(new String(cursor.getString(cursor.getColumnIndex("level"))));
           	      String level = new String(cursor.getString(cursor.getColumnIndex("level")));
           	      System.out.println("Heehahaha"+level);
           	      int id = Integer.valueOf(new String(cursor.getString(cursor.getColumnIndex("_id"))));
           	      String q1=new String(cursor.getString(cursor.getColumnIndex("question1")));
           	      String q2=new String(cursor.getString(cursor.getColumnIndex("question2")));
           	      String q3=new String(cursor.getString(cursor.getColumnIndex("question3")));
           	      String q4=new String(cursor.getString(cursor.getColumnIndex("question4")));
           	      String q5=new String(cursor.getString(cursor.getColumnIndex("question5")));
           	      String q6=new String(cursor.getString(cursor.getColumnIndex("question6")));
        	      String q7=new String(cursor.getString(cursor.getColumnIndex("question7")));
        	      String q8=new String(cursor.getString(cursor.getColumnIndex("question8")));
        	      String q9=new String(cursor.getString(cursor.getColumnIndex("question9")));
        	      String q10=new String(cursor.getString(cursor.getColumnIndex("question10")));
        	      String mistakes =new String(cursor.getString(cursor.getColumnIndex("totalmistakes")));
        	      
           	      //adding to ArrayList
        	      Student record = new Student(name,date,level,id,q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,mistakes);
        	      System.out.println("Level in TestAdapter::"+record.level);    
           	      records.add(record);
           	     
           	    }while(cursor.moveToNext());
             }
            cursor.close();            
            return records;
        } 
        catch (SQLException mSQLException)  
        { 
            throw mSQLException; 
        } 
     } 
    
    
}
