package FirstProgram.Tally;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, ClassNotFoundException
    {
    	int a = 1;
    	System.out.println(a);
    	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    	Connection con = DriverManager.getConnection("jdbc:ucanaccess:TallyODBC_9000;memory=false", "", "");
    	Statement stmt = (Statement) con.createStatement();
    	ResultSet rs = stmt.executeQuery("select * from Company");


    	ResultSetMetaData md = rs.getMetaData();
    	int columns = md.getColumnCount();
    	List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
    	while (rs.next()){
    		Map<String, Object> row = new HashMap<String, Object>(columns);
    		for(int i = 1; i <= columns; ++i){
    			row.put(md.getColumnName(i), rs.getObject(i));
    		}
    		rows.add(row);
    	}
    	System.out.println(rows);
    }
}
