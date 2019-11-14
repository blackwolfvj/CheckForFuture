package XmlConverterOne;

import java.io.*;
import java.net.*;

public class Connector {
	public static void main(String ak[]) throws Exception{
		Connector conn = new Connector();
		conn.sendToTally();
	}

	private void sendToTally() throws Exception {
		String Url = "http://127.0.0.1:9000/";
		String SOAPAction="";
//		String voucher = this.createRequest();
		String voucher = XmlConverterOne.createXml();
		URL urlObj = new URL(Url);
		URLConnection urlConn = urlObj.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) urlConn;
		
		ByteArrayInputStream bin = new ByteArrayInputStream(voucher.getBytes());
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
		copy(bin,bout);
		byte[] b = bout.toByteArray();
		
		httpConn.setRequestProperty("Content-Length", String.valueOf(b.length));
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConn.setRequestProperty("SOAPAction", SOAPAction);
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        
        OutputStream out = httpConn.getOutputStream();
        out.write(b);
        out.close();

        InputStreamReader isr =
                new InputStreamReader(httpConn.getInputStream());
        BufferedReader in = new BufferedReader(isr);

        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }

        in.close();
	}

	private void copy(ByteArrayInputStream bin, ByteArrayOutputStream bout) 
			throws IOException{
		synchronized (bin) {
			synchronized (bout) {
				byte[] buffer = new byte[256];
				while(true){
					int bytesRead = bin.read(buffer);
					if(bytesRead==-1){
						break;
					}
					bout.write(buffer, 0, bytesRead);
				}
			}
		}
		
	}

	private String createRequest() {
		String TXML = "<ENVELOPE>"
                + "<HEADER><TALLYREQUEST>Import Data</TALLYREQUEST></HEADER>"
                + "<BODY>"
                + "<IMPORTDATA>"
                + "<REQUESTDESC><REPORTNAME>Vouchers</REPORTNAME><STATICVARIABLES><SVCURRENTCOMPANY>New Company</SVCURRENTCOMPANY></STATICVARIABLES></REQUESTDESC>"
                + "<REQUESTDATA>"
                + "<TALLYMESSAGE xmlns:UDF=\"TallyUDF\">"
                + "<VOUCHER REMOTEID=\"00000001\" VCHTYPE=\"Receipt\" ACTION=\"Create\" OBJVIEW=\"Accounting Voucher View\">"      
                + "<DATE>20140401</DATE>"
                + "<VOUCHERTYPENAME>Receipt</VOUCHERTYPENAME>"
                + "<VOUCHERNUMBER>1</VOUCHERNUMBER>"
                + "<PARTYLEDGERNAME>Cash</PARTYLEDGERNAME>"
                + "<PERSISTEDVIEW>Accounting Voucher View</PERSISTEDVIEW>"      
                + "<ALLLEDGERENTRIES.LIST>"
                + "<LEDGERNAME>Capital Account</LEDGERNAME>"
                + "<ISDEEMEDPOSITIVE>No</ISDEEMEDPOSITIVE>"
                + "<AMOUNT>50000.00</AMOUNT>"       
                + "</ALLLEDGERENTRIES.LIST>"
                + "<ALLLEDGERENTRIES.LIST>"      
                + "<LEDGERNAME>Cash</LEDGERNAME>"
                + "<ISDEEMEDPOSITIVE>Yes</ISDEEMEDPOSITIVE>"
                + "<AMOUNT>-50000.00</AMOUNT>"          
                + "</ALLLEDGERENTRIES.LIST>"    
                + "</VOUCHER>"
                + "</TALLYMESSAGE>"
                + "</REQUESTDATA>"
                + "</IMPORTDATA>"
                + "</BODY>"
                + "</ENVELOPE>";
		return TXML;
	}
}




/*
public class SOAPClient{
public static void main(String[] args) throws Exception {

String Url = "http://192.168.10.44:9999/";
String xmlFile = "/home/gaurav/Desktop/test.xml";

String SOAPAction = "";

// Create the connection where we're going to send the file.
URL url = new URL(Url);
URLConnection connection = url.openConnection();
HttpURLConnection httpConn = (HttpURLConnection) connection;


FileInputStream fin = new FileInputStream(xmlFile2Send);

ByteArrayOutputStream bout = new ByteArrayOutputStream();

// Copy the SOAP file to the open connection.
copy(fin,bout);
fin.close();

byte[] b = bout.toByteArray();

// Set the appropriate HTTP parameters.
httpConn.setRequestProperty( "Content-Length",
String.valueOf( b.length ) );
httpConn.setRequestProperty("Content-Type","text/xml; charset=utf-8");
httpConn.setRequestProperty("SOAPAction",SOAPAction);
httpConn.setRequestMethod("POST");
httpConn.setDoOutput(true);
httpConn.setDoInput(true);

// Everything's set up; send the XML that was read in to b.
OutputStream out = httpConn.getOutputStream();
out.write( b );
out.close();

// Read the response and write it to standard out.

InputStreamReader isr =
new InputStreamReader(httpConn.getInputStream());
BufferedReader in = new BufferedReader(isr);

String inputLine;

while ((inputLine = in.readLine()) != null)
System.out.println(inputLine);

in.close();
}

public static void copy(InputStream in, OutputStream out)
throws IOException {

// do not allow other threads to read from the
// input or write to the output while copying is
// taking place

synchronized (in) {
synchronized (out) {

byte[] buffer = new byte[256];
while (true) {
int bytesRead = in.read(buffer);
if (bytesRead == -1) break;
out.write(buffer, 0, bytesRead);
}
}
}
}
}
 */