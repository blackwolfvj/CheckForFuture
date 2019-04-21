package HumaineGroup.StatusResultNew;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Hello world!
 *
 */
public class App {
	// JDBC driver name and database URL 
	   static final String JDBC_DRIVER = "org.h2.Driver";   
	   static final String DB_URL = "jdbc:h2:file:C:/Users/lenovo/Documents/1.4.197/STATUSDETAILS;AUTO_SERVER=TRUE";  
//	   static final String DB_URL = "jdbc:h2:~/test";  
	   
	   //  Database credentials 
	   static final String USER = "sa"; 
	   static final String PASS = ""; 
	  
	public static void main(String[] args) {
	      Connection conn = null; 
	      Statement stmt = null; 
	      try { 
	         // STEP 1: Register JDBC driver 
	         Class.forName(JDBC_DRIVER); 
	             
	         //STEP 2: Open a connection 
	         System.out.println("Connecting to database..."); 
	         conn = DriverManager.getConnection(DB_URL,USER,PASS);  
	         
	         //STEP 3: Execute a query 
	         System.out.println("Creating table in given database..."); 
	         stmt = conn.createStatement();
	         //------------------CREATE----------------------------
	         String sql =  "CREATE TABLE   STATUSDETAILS " + 
	            "(empid INTEGER not NULL , " + 
	            " screentype VARCHAR(255) not NULL , " +  
	            " storyid VARCHAR(255) not NULL , " +  
	            " subsys VARCHAR(255), " +  
	            " module VARCHAR(255), " +  
	            " fnname VARCHAR(255) not NULL, " +  
	            " startdate VARCHAR(255), " +  
	            " enddate VARCHAR(255), " +  
	            " status VARCHAR(255), " +  
	            " inittable VARCHAR(255), " +  
	            " initscreen VARCHAR(255), " +  
	            " currtable VARCHAR(255), " +  
	            " currscreen VARCHAR(255), " +  
	            " percentdone VARCHAR(255), " +  
	            " appgen VARCHAR(255), " +  
	            " changereq VARCHAR(255), " +  
	            " table VARCHAR(255), " +  
	            " remarks VARCHAR(255), " +  
	            " PRIMARY KEY ( fnname,screenType ))";
	         stmt.executeUpdate(sql); 
	         //-------------------------INSERT---------------------------------
//	         String sql =  "CREATE TABLE   STORYDETAILS " + 
//	 	            " (storyid INTEGER not NULL , " +  
//	 	            " empid INTEGER not NULL , " + 
//	 	            " subsys VARCHAR(255), " +  
//	 	            " module VARCHAR(255), " +  
//	 	            " fnname VARCHAR(255), " +  
//	 	            " PRIMARY KEY ( storyid ))";
//	 	         stmt.executeUpdate(sql); 
//	 	        System.out.println("Created given table in database..."); 
	         //----------------------------------------------------------------
	 	        
//	         String sql1 = "SELECT * FROM STATUSDETAILS"; 
//	         ResultSet rs = stmt.executeQuery(sql1); 
//	         System.out.println(rs);
//	         String sql =  "Drop table REGISTRATION";
//	         String sql = "INSERT INTO Registration " + "VALUES (100, 'Zara', 'Ali', 18)"; 
//	         sql = "INSERT INTO Registration " + "VALUES (101, 'Mahnaz', 'Fatma', 25)";  
//	         
//	         stmt.executeUpdate(sql); 
//	         sql = "INSERT INTO Registration " + "VALUES (102, 'Zaid', 'Khan', 30)"; 
//	         
//	         stmt.executeUpdate(sql); 
//	         sql = "INSERT INTO Registration " + "VALUES(103, 'Sumit', 'Mittal', 28)"; 
//	         
//	         stmt.executeUpdate(sql); 
//	         System.out.println("Records Updated into the table...");
	         
	         // STEP 4: Clean-up environment 
	         
	         // STEP 4: Extract data from result set 
//	         while(rs.next()) { 
	            // Retrieve by column name 
//	            int id  = rs.getInt("id"); 
//	            int age = rs.getInt("age"); 
//	            String first = rs.getString("first"); 
//	            String last = rs.getString("last");  
	            
	            // Display values 
//	            System.out.print("ID: " + id); 
//	            System.out.print(", Age: " + age); 
//	            System.out.print(", First: " + first); 
//	            System.out.println(", Last: " + last); 
//	         } 
	         // STEP 5: Clean-up environment 
//	         rs.close();
	         stmt.close(); 
	         conn.close(); 
	      } catch(SQLException se) { 
	         //Handle errors for JDBC 
	         se.printStackTrace(); 
	      } catch(Exception e) { 
	         //Handle errors for Class.forName 
	         e.printStackTrace(); 
	      } finally { 
	         //finally block used to close resources 
	         try{ 
	            if(stmt!=null) stmt.close(); 
	         } catch(SQLException se2) { 
	         } // nothing we can do 
	         try { 
	            if(conn!=null) conn.close(); 
	         } catch(SQLException se){ 
	            se.printStackTrace(); 
	         } //end finally try 
	      } //end try 
	      System.out.println("Goodbye!");
	   }

	public void insertData(String screenType, String storyId, String subsys, String module, String fnName, 
			String startDate, String endDate, String status, String initTable, 
			String initScreen, String currTable, String currScreen, String done, 
			String appGen, String changeReq, String tableIssue, String remarks) {
		
		 Connection conn = null; 
	      Statement stmt = null; 
	      try { 
	         // STEP 1: Register JDBC driver 
	         Class.forName(JDBC_DRIVER); 
	             
	         //STEP 2: Open a connection 
	         System.out.println("Connecting to database..."); 
	         conn = DriverManager.getConnection(DB_URL,USER,PASS);  
	         
	         //STEP 3: Execute a query 
	         System.out.println("Creating table in given database..."); 
	         stmt = conn.createStatement();
	         
	         String sql = "INSERT INTO STATUSDETAILS " + "VALUES(1487,'"+screenType+"','"+storyId+"','"+subsys+"','"
	        		 +module+"','"+fnName+"','"+startDate+"','"+endDate+"','"+status+"','"+initTable+"','"
	        		 +initScreen+"','"+currTable+"','"+currScreen+"','"+done+"','"+appGen+"','"+changeReq+"','"
	        		 +tableIssue+"','"+remarks+"')";
	         
	         stmt.executeUpdate(sql); 
	         
//	         String sql2 = "INSERT INTO STORYDETAILS " + "VALUES('"+storyId+"','1487','"+subsys+"','"
//	        		 +module+"','"+fnName+"')";
//	         
//	         stmt.executeUpdate(sql2);
//	         String sql3 = "SELECT * FROM STORYDETAILS where subsys = 'finance'"; 
//	         ResultSet rs3 = stmt.executeQuery(sql3); 
//	         while(rs3.next()) { 
//		            // Display values 
//		            System.out.println("ID: " + rs3.getInt("empid")); 
//		            System.out.println(", story: " + rs3.getInt("storyid")); 
//		            System.out.println(", subsys: " + rs3.getString("subsys")); 
//		            System.out.println(", subsys: " + rs3.getString("module")); 
//		            System.out.println(", subsys: " + rs3.getString("fnname")); 
//		         } 

	         String sql1 = "SELECT * FROM STATUSDETAILS"; 
	         ResultSet rs = stmt.executeQuery(sql1); 
	         while(rs.next()) { 
		            // Display values 
		            System.out.println("ID: " + rs.getInt("empid")); 
		            System.out.println(", screenType: " + rs.getString("screentype")); 
		            System.out.println(", story: " + rs.getInt("storyid")); 
		            System.out.println(", subsys: " + rs.getString("subsys")); 
		            System.out.println(", subsys: " + rs.getString("module")); 
		            System.out.println(", subsys: " + rs.getString("fnname")); 
		            System.out.println(", subsys: " + rs.getString("startdate")); 
		            System.out.println(", subsys: " + rs.getString("enddate")); 
		            System.out.println(", subsys: " + rs.getString("status")); 
		            System.out.println(", subsys: " + rs.getString("inittable")); 
		            System.out.println(", subsys: " + rs.getString("initscreen")); 
		            System.out.println(", subsys: " + rs.getString("currtable")); 
		            System.out.println(", subsys: " + rs.getString("currscreen")); 
		            System.out.println(", subsys: " + rs.getString("percentdone")); 
		            System.out.println(", subsys: " + rs.getString("appgen")); 
		            System.out.println(", subsys: " + rs.getString("changereq")); 
		            System.out.println(", subsys: " + rs.getString("table")); 
		            System.out.println(", subsys: " + rs.getString("remarks")); 
		         } 
	         rs.close();
	         stmt.close(); 
	         conn.close(); 
	         JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
	      }catch(SQLException se) { 
		         //Handle errors for JDBC 
		         se.printStackTrace(); 
		  } catch(Exception e) { 
		         //Handle errors for Class.forName 
		         e.printStackTrace(); 
		  } finally { 
		         //finally block used to close resources 
		      try{ 
		            if(stmt!=null) stmt.close(); 
		      } catch(SQLException se2) { 
		      } // nothing we can do 
		      try { 
		            if(conn!=null) conn.close(); 
		      } catch(SQLException se){ 
		            se.printStackTrace(); 
		      } //end finally try 
		  } //end try 
	}

	public List<String> loadFunctionNameCmb() {
		Connection conn = null; 
	      Statement stmt = null; 
	      List<String> fnNameList = new ArrayList<String>();
	      try { 
	         // STEP 1: Register JDBC driver 
	         Class.forName(JDBC_DRIVER); 
	             
	         //STEP 2: Open a connection 
	         System.out.println("Connecting to database..."); 
	         conn = DriverManager.getConnection(DB_URL,USER,PASS);  
	         
	         //STEP 3: Execute a query 
	         System.out.println("Creating table in given database..."); 
	         stmt = conn.createStatement();
	         
	         String sql1 = "select * FROM STATUSDETAILS";
	         
	         ResultSet rs = stmt.executeQuery(sql1);
	         while(rs.next()) {
	        	    fnNameList.add(rs.getString("fnname"));
		         } 
	         rs.close();
	         stmt.close(); 
	         conn.close(); 
	      }catch(SQLException se) { 
		         //Handle errors for JDBC 
		         se.printStackTrace(); 
		  } catch(Exception e) { 
		         //Handle errors for Class.forName 
		         e.printStackTrace(); 
		  } finally { 
		         //finally block used to close resources 
		      try{ 
		            if(stmt!=null) stmt.close(); 
		      } catch(SQLException se2) { 
		      } // nothing we can do 
		      try { 
		            if(conn!=null) conn.close(); 
		      } catch(SQLException se){ 
		            se.printStackTrace(); 
		      } //end finally try 
		  } //end try 
		
		return fnNameList;
	}

	public Map<String, String> loadDetails(Object fnName, String screenType2) {
		Connection conn = null;
	      Statement stmt = null;
	      Map<String,String> detail2Map = new HashMap<String, String>();
	      try { 
	         // STEP 1: Register JDBC driver 
	         Class.forName(JDBC_DRIVER); 
	             
	         //STEP 2: Open a connection 
	         System.out.println("Connecting to database..."); 
	         conn = DriverManager.getConnection(DB_URL,USER,PASS);  
	         
	         //STEP 3: Execute a query 
	         System.out.println("Creating table in given database..."); 
	         stmt = conn.createStatement();
	         
	         String sql1 = "select * FROM STATUSDETAILS where fnname = '"+fnName.toString()+"' and screentype = '"+screenType2+"'";
	         
//	         stmt.executeUpdate(sql1); 
	         
//	         String sql1 = "SELECT * FROM STATUSDETAILS"; 
	         ResultSet rs = stmt.executeQuery(sql1);
	         while(rs.next()) {
		            // Retrieve by column name 
//		            int id  = rs.getInt("empid"); 
//		            int age = rs.getInt("storyid"); 
//		            String first = rs.getString("first"); 
//		            String last = rs.getString("last");  
		            
		            // Display values 
	        	 System.out.print("*****************************************************");
	        	 detail2Map.put("screenType", rs.getString("screentype"));
	        	 detail2Map.put("storyIdtxt2", String.valueOf(rs.getInt("storyid")));
	        	 detail2Map.put("subSystxt2", rs.getString("subsys"));
	        	 detail2Map.put("moduleTxt2", rs.getString("module"));
	        	 detail2Map.put("fnNameCmb2", rs.getString("fnname"));
	        	 detail2Map.put("startDate2", rs.getString("startdate"));
	        	 detail2Map.put("endDate2", rs.getString("enddate"));
	        	 detail2Map.put("statusCmb2", rs.getString("status"));
	        	 detail2Map.put("initTableCmb2", rs.getString("inittable"));
	        	 detail2Map.put("initScreenCmb2", rs.getString("initscreen"));
	        	 detail2Map.put("currTableCmb2", rs.getString("currtable"));
	        	 detail2Map.put("currScreenCmb2", rs.getString("currscreen"));
	        	 detail2Map.put("doneCmb2", rs.getString("percentdone"));
	        	 detail2Map.put("appGenArea2", rs.getString("appgen"));
	        	 detail2Map.put("changeReqArea2", rs.getString("changereq"));
	        	 detail2Map.put("tableArea2", rs.getString("table"));
	        	 detail2Map.put("remarksArea2", rs.getString("remarks"));
		            System.out.print("ID: " + rs.getInt("empid")); 
		            System.out.print(", story: " + rs.getInt("storyid")); 
		            System.out.print(", subsys: " + rs.getString("subsys")); 
		            System.out.print(", subsys: " + rs.getString("module")); 
		            System.out.print(", subsys: " + rs.getString("fnname")); 
		            System.out.print(", subsys: " + rs.getString("startdate")); 
		            System.out.print(", subsys: " + rs.getString("enddate")); 
		            System.out.print(", subsys: " + rs.getString("status")); 
		            System.out.print(", subsys: " + rs.getString("inittable")); 
		            System.out.print(", subsys: " + rs.getString("initscreen")); 
		            System.out.print(", subsys: " + rs.getString("currtable")); 
		            System.out.print(", subsys: " + rs.getString("currscreen")); 
		            System.out.print(", subsys: " + rs.getString("percentdone")); 
		            System.out.print(", subsys: " + rs.getString("appgen")); 
		            System.out.print(", subsys: " + rs.getString("changereq")); 
		            System.out.print(", subsys: " + rs.getString("table")); 
		            System.out.print(", subsys: " + rs.getString("remarks")); 
		         }
	         StatusDetails at = new StatusDetails();
	         rs.close();
	         stmt.close(); 
	         conn.close(); 
	      }catch(SQLException se) { 
		         //Handle errors for JDBC 
		         se.printStackTrace(); 
		  } catch(Exception e) { 
		         //Handle errors for Class.forName 
		         e.printStackTrace(); 
		  } finally { 
		         //finally block used to close resources 
		      try{ 
		            if(stmt!=null) stmt.close(); 
		      } catch(SQLException se2) { 
		      } // nothing we can do 
		      try { 
		            if(conn!=null) conn.close(); 
		      } catch(SQLException se){ 
		            se.printStackTrace(); 
		      } //end finally try 
		  } //end try 
	      return detail2Map;
		
	}

	public void downloadDetails() {
		Connection conn = null; 
	      Statement stmt = null; 
	      try { 
	         // STEP 1: Register JDBC driver 
	         Class.forName(JDBC_DRIVER); 
	             
	         //STEP 2: Open a connection 
	         System.out.println("Connecting to database..."); 
	         conn = DriverManager.getConnection(DB_URL,USER,PASS);  
	         
	         //STEP 3: Execute a query 
	         System.out.println("Creating table in given database..."); 
	         stmt = conn.createStatement();
	         
	         String sql1 = "select * FROM STATUSDETAILS";
	         
	         ResultSet rs = stmt.executeQuery(sql1);
	         JSONArray json = new JSONArray();
	         while(rs.next()) {
	        	    JSONObject obj = new JSONObject();
		            obj.put("ID: " , rs.getInt("empid")); 
		            obj.put("story: " , rs.getInt("storyid")); 
		            obj.put("screentype: " , rs.getString("screentype")); 
		            obj.put("subsys: " , rs.getString("subsys")); 
		            obj.put("module: " , rs.getString("module")); 
		            obj.put("fnname: " , rs.getString("fnname")); 
		            obj.put("startdate: " , rs.getString("startdate")); 
		            obj.put("enddate: " , rs.getString("enddate")); 
		            obj.put("status: " , rs.getString("status")); 
		            obj.put("inittable: " , rs.getString("inittable")); 
		            obj.put("initscreen: " , rs.getString("initscreen")); 
		            obj.put("currtable: " , rs.getString("currtable")); 
		            obj.put("currscreen: " , rs.getString("currscreen")); 
		            obj.put("percentdone: " , rs.getString("percentdone")); 
		            obj.put("appgen: " , rs.getString("appgen")); 
		            obj.put("changereq: " , rs.getString("changereq")); 
		            obj.put("table: " , rs.getString("table")); 
		            obj.put("remarks: " , rs.getString("remarks")); 
		            json.add(obj);
		         } 
	         try {
	        		 FileWriter file = new FileWriter("C:\\Users\\lenovo\\Documents\\1.4.197\\newfile.json");
	 			file.write(json.toJSONString());

	 			file.flush();
	 			JOptionPane.showMessageDialog(null, "File Downloaded Successfully");
	 		}
	 		catch (IOException e) {
	 			e.printStackTrace();
	 		}
	         rs.close();
	         stmt.close(); 
	         conn.close(); 
	      }catch(SQLException se) { 
		         //Handle errors for JDBC 
		         se.printStackTrace(); 
		  } catch(Exception e) { 
		         //Handle errors for Class.forName 
		         e.printStackTrace(); 
		  } finally { 
		         //finally block used to close resources 
		      try{ 
		            if(stmt!=null) stmt.close(); 
		      } catch(SQLException se2) { 
		      } // nothing we can do 
		      try { 
		            if(conn!=null) conn.close(); 
		      } catch(SQLException se){ 
		            se.printStackTrace(); 
		      } //end finally try 
		  } //end try 
		
	}
}
