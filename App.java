package HumaineGroup.StatusResultNew;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.h2.tools.Csv;
import org.h2.tools.SimpleResultSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Hello world!
 *
 */
public class App {
	static final String JDBC_DRIVER = "org.h2.Driver";   
	static final String DB_URL = "jdbc:h2:file:C:/Users/lenovo/Documents/1.4.197/STATUSDETAILS;AUTO_SERVER=TRUE";  
	static final String USER = "sa"; 
	static final String PASS = ""; 

	public static void main(String[] args) {
		Connection conn = null; 
		Statement stmt = null; 
		try { 
			Class.forName(JDBC_DRIVER); 
			System.out.println("Connecting to database..."); 
			conn = DriverManager.getConnection(DB_URL,USER,PASS);  
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

			//	         while(rs.next()) { 
			//	            int id  = rs.getInt("id"); 
			//	            int age = rs.getInt("age"); 
			//	            String first = rs.getString("first"); 
			//	            String last = rs.getString("last");  

			//	            System.out.print("ID: " + id); 
			//	            System.out.print(", Age: " + age); 
			//	            System.out.print(", First: " + first); 
			//	            System.out.println(", Last: " + last); 
			//	         } 
			//	         rs.close();
			stmt.close(); 
			conn.close(); 
		} catch(SQLException se) {
			se.printStackTrace(); 
		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally { 
			try{ 
				if(stmt!=null) stmt.close(); 
			} catch(SQLException se2) { 
			}
			try { 
				if(conn!=null) conn.close(); 
			} catch(SQLException se){ 
				se.printStackTrace(); 
			}
		}
		System.out.println("Goodbye!");
	}

	public void insertData(int empId, String screenType, String storyId, String subsys, String module, String fnName, 
			String startDate, String endDate, String status, String initTable, 
			String initScreen, String currTable, String currScreen, String done, 
			String appGen, String changeReq, String tableIssue, String remarks) {

		Connection conn = null; 
		Statement stmt = null; 
		try { 
			Class.forName(JDBC_DRIVER); 

			conn = DriverManager.getConnection(DB_URL,USER,PASS);  

			System.out.println("inserting data in given database..."); 
			stmt = conn.createStatement();

			String sql = "INSERT INTO STATUSDETAILS " + "VALUES("+empId+",'"+screenType+"','"+storyId+"','"+subsys+"','"
					+module+"','"+fnName+"','"+startDate+"','"+endDate+"','"+status+"','"+initTable+"','"
					+initScreen+"','"+currTable+"','"+currScreen+"','"+done+"','"+appGen+"','"+changeReq+"','"
					+tableIssue+"','"+remarks+"')";

			stmt.executeUpdate(sql); 

			System.out.println("Data Inserted Successfully!!!");
			/*String sql1 = "SELECT * FROM STATUSDETAILS"; 
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
	         rs.close();*/
			stmt.close(); 
			conn.close(); 
			JOptionPane.showMessageDialog(null, "Data Inserted Successfully !!!");
		}catch(SQLException se) { 
			se.printStackTrace(); 
		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally { 
			try{ 
				if(stmt!=null) stmt.close(); 
			} catch(SQLException se2) { 
			}
			try { 
				if(conn!=null) conn.close(); 
			} catch(SQLException se){ 
				se.printStackTrace(); 
			} 
		} 
	}

	
	public List<String> loadFunctionNameCmb(int empId) {
		Connection conn = null; 
		Statement stmt = null; 
		List<String> fnNameList = new ArrayList<String>();
		try { 
			Class.forName(JDBC_DRIVER); 

			conn = DriverManager.getConnection(DB_URL,USER,PASS);  

			System.out.println("Loading functionName from given database...");
			stmt = conn.createStatement();

//			String sql1 = "select * FROM STATUSDETAILS";
//			String sql2 = "select DISTINCT empid FROM STATUSDETAILS";
			String sql3 = "select fnname FROM STATUSDETAILS where empid = "+empId;

			ResultSet rs = stmt.executeQuery(sql3);
//			ResultSet rs2 = stmt.executeQuery(sql2);
//			ResultSet rs3 = stmt.executeQuery(sql3);
			
			while(rs.next()) {
				fnNameList.add(rs.getString("fnname"));
			}
//			while(rs3.next()) {
//				fnNameList2.add(rs3.getString("fnname"));
//			}
//			while(rs2.next()) {
//				fnNameList2.add(rs2.getString("empid"));
//			}
			if(!fnNameList.isEmpty()) {
				System.out.println("functionName loaded Successfully !!!");
			}else {
				System.out.println("functionName is Empty !!!");
			}

			rs.close();
			stmt.close(); 
			conn.close(); 
		}catch(SQLException se) { 
			se.printStackTrace(); 
		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally { 
			try{ 
				if(stmt!=null) stmt.close(); 
			} catch(SQLException se2) { 
			}
			try { 
				if(conn!=null) conn.close(); 
			} catch(SQLException se){ 
				se.printStackTrace(); 
			}
		}

		return fnNameList;
	}

	public Map<String, String> loadDetails(Object fnName, String screenType2) {
		Connection conn = null;
		Statement stmt = null;
		Map<String,String> detail2Map = new HashMap<String, String>();
		try { 
			Class.forName(JDBC_DRIVER); 

			conn = DriverManager.getConnection(DB_URL,USER,PASS);  

			stmt = conn.createStatement();

			String sql1 = "select * FROM STATUSDETAILS where fnname = '"+fnName.toString()+"' and screentype = '"+screenType2+"'";

			ResultSet rs = stmt.executeQuery(sql1);
			while(rs.next()) {
				detail2Map.put("screenType", rs.getString("screentype"));
				detail2Map.put("storyIdtxt2", String.valueOf(rs.getInt("storyid"))+"/"+rs.getInt("empid"));
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
			}
			StatusDetails at = new StatusDetails();
			rs.close();
			stmt.close(); 
			conn.close(); 
		}catch(SQLException se) { 
			se.printStackTrace(); 
		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally { 
			try{ 
				if(stmt!=null) stmt.close(); 
			} catch(SQLException se2) { 
			}
			try { 
				if(conn!=null) conn.close(); 
			} catch(SQLException se){ 
				se.printStackTrace(); 
			}
		}
		return detail2Map;

	}

	public void updateData(int empId2,String screenType2, String storyId2, String subsys2, String module2, String fnName2, 
			String startDate2, String endDate2, String status2, String initTable2, 
			String initScreen2, String currTable2, String currScreen2, String done2, 
			String appGen2, String changeReq2, String tableIssue2, String remarks2) {
		Connection conn = null;
		Statement stmt = null;
		Map<String,String> detail2Map = new HashMap<String, String>();
		try { 
			Class.forName(JDBC_DRIVER); 

			conn = DriverManager.getConnection(DB_URL,USER,PASS);  

			stmt = conn.createStatement();

			String sql1 = "update STATUSDETAILS set empid = "+empId2+", storyid = '"+storyId2
					+"', subsys = '" + subsys2
					+"', module = '" + module2
					+"', startdate = '" + startDate2
					+"', enddate = '" + endDate2
					+"', status = '" + status2
					+"', inittable = '" + initTable2
					+"', initscreen = '" + initScreen2
					+"', currtable = '" + currTable2
					+"', currscreen = '" + currScreen2
					+"', percentdone = '" + done2
					+"', appgen = '" + appGen2
					+"', changereq = '" + changeReq2
					+"', table = '" + tableIssue2
					+"', remarks = '" + remarks2 +
					"' where fnname = '" + fnName2 + "' and screentype = '"+screenType2+"'";

			int rs = stmt.executeUpdate(sql1);
			JOptionPane.showMessageDialog(null, "Data Updated Successfully !!!"+rs);
			stmt.close(); 
			conn.close(); 
		}catch(SQLException se) { 
			se.printStackTrace(); 
		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally { 
			try{ 
				if(stmt!=null) stmt.close(); 
			} catch(SQLException se2) { 
			}
			try { 
				if(conn!=null) conn.close(); 
			} catch(SQLException se){ 
				se.printStackTrace(); 
			}
		}

	}

	public void downloadDetails() {
		Connection conn = null; 
		Statement stmt = null; 
		try {
			Class.forName(JDBC_DRIVER); 

			conn = DriverManager.getConnection(DB_URL,USER,PASS);  

			stmt = conn.createStatement();

			String sql1 = "select * FROM STATUSDETAILS";

			SimpleResultSet sr = new SimpleResultSet();
			sr.addColumn("EMPID", Types.INTEGER, 255, 0);
			sr.addColumn("STORYID", Types.VARCHAR, 255, 0);
			sr.addColumn("SCREENTYPE", Types.VARCHAR, 255, 0);
			sr.addColumn("SUBSYS", Types.VARCHAR, 255, 0);
			sr.addColumn("MODULE", Types.VARCHAR, 255, 0);
			sr.addColumn("FNNAME", Types.VARCHAR, 255, 0);
			sr.addColumn("STARTDATE", Types.VARCHAR, 255, 0);
			sr.addColumn("ENDDATE", Types.VARCHAR, 255, 0);
			sr.addColumn("STATUS", Types.VARCHAR, 255, 0);
			sr.addColumn("INITTABLE", Types.VARCHAR, 255, 0);
			sr.addColumn("INITSCREEN", Types.VARCHAR, 255, 0);
			sr.addColumn("CURRTABLE", Types.VARCHAR, 255, 0);
			sr.addColumn("CURRSCREEN", Types.VARCHAR, 255, 0);
			sr.addColumn("PERCENTDONE", Types.VARCHAR, 255, 0);
			sr.addColumn("APPGEN", Types.VARCHAR, 255, 0);
			sr.addColumn("CHANGEREQ", Types.VARCHAR, 255, 0);
			sr.addColumn("TABLE", Types.VARCHAR, 255, 0);
			sr.addColumn("REMARKS", Types.VARCHAR, 255, 0);
			ResultSet rs = stmt.executeQuery(sql1);
			Map<Integer,String> empIdMap = new HashMap<Integer, String>();
			empIdMap.put(1487, "Thamilarasu");
			empIdMap.put(1113, "Arunprasad");
			empIdMap.put(1145, "Logeshwari");
			empIdMap.put(1158, "Kalaimathi");
			empIdMap.put(1158, "Kalaimathi");
//			JSONArray json = new JSONArray();
			while(rs.next()) {
//				JSONObject obj = new JSONObject();
				Object [] rowData1 = new Object [] {empIdMap.get(rs.getInt("empid")), rs.getInt("storyid"),
						rs.getString("screentype")
						,rs.getString("subsys")
						,rs.getString("module")
						,rs.getString("fnname")
						,rs.getString("startdate")
						,rs.getString("enddate")
						,rs.getString("status")
						,rs.getString("inittable")
						,rs.getString("initscreen")
						,rs.getString("currtable")
						,rs.getString("currscreen")
						,rs.getString("percentdone")
						,rs.getString("appgen")
						,rs.getString("changereq")
						,rs.getString("table")
						,rs.getString("remarks")};
				sr.addRow(rowData1);
				/*obj.put("ID: " , rs.getInt("empid"));
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
				json.add(obj);*/
			}
			new Csv().write("C:\\\\Users\\\\lenovo\\\\Documents\\\\1.4.197\\\\Success.csv", sr, null);
			/*FileWriter file = new FileWriter("C:\\Users\\lenovo\\Documents\\1.4.197\\newfile.json");
			file.write(json.toJSONString());

			file.flush();*/
			JOptionPane.showMessageDialog(null, "File Downloaded Successfully");
			rs.close();
			stmt.close(); 
			conn.close(); 
		}catch(SQLException se) {
			se.printStackTrace(); 
		} catch(Exception e) {
			e.printStackTrace(); 
		} finally {
			try{ 
				if(stmt!=null) stmt.close(); 
			} catch(SQLException se2) { 
			}
			try { 
				if(conn!=null) conn.close(); 
			} catch(SQLException se){ 
				se.printStackTrace(); 
			}
		}

	}

	public void deleteRecord(Object fnName, String screenType3) {
		Connection conn = null;
		Statement stmt = null;
		Map<String,String> detail2Map = new HashMap<String, String>();
		try { 
			Class.forName(JDBC_DRIVER); 

			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			stmt = conn.createStatement();

			String sql1 = "delete FROM STATUSDETAILS where fnname = '"+fnName.toString()+"' and screentype = '"+screenType3+"'";

			int rs = stmt.executeUpdate(sql1);
			JOptionPane.showMessageDialog(null, "Record Deleted Successfully!!!");
			stmt.close(); 
			conn.close(); 
		}catch(SQLException se) { 
			se.printStackTrace(); 
		} catch(Exception e) { 
			e.printStackTrace(); 
		} finally { 
			try{ 
				if(stmt!=null) stmt.close(); 
			} catch(SQLException se2) { 
			}
			try { 
				if(conn!=null) conn.close(); 
			} catch(SQLException se){ 
				se.printStackTrace(); 
			}
		}

	}

}
