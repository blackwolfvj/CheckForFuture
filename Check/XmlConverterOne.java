package XmlConverterOne;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlConverterOne {

	public static String[] currencyList = {"MAILINGNAME","ORIGINALNAME","EXPANDEDSYMBOL","DECIMALSYMBOL",
			"DECIMALPLACES","DECIMALPLACESFORPRINTING"};
	
	
	
	public String[] groupList = {"PARENT","ISCOSTCENTRESON","LANGUAGENAME.LIST"};
	public String[] groupChidList = {"NAME.LIST","LANGUAGEID"};
	
	public static void main(String ak[]) throws SAXException, IOException{
		try {
			Map<String,String> currencyListMap = new HashMap<>();
			
			currencyListMap.put("MAILINGNAME", "INR");
			currencyListMap.put("ORIGINALNAME", "$");
			currencyListMap.put("EXPANDEDSYMBOL", "INR");
			currencyListMap.put("DECIMALSYMBOL", "paise");
			currencyListMap.put("DECIMALPLACES", "2");
			currencyListMap.put("DECIMALPLACESFORPRINTING", "2");
			
			String companyName = "Company1";
			
			Map<String,String> currencyMap = new HashMap<>();
			currencyMap.put("$", "dollar");
			
			
			
			String filePath = "LedgerData.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//			Document doc = docBuilder.parse(filePath);
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("ENVELOPE");
			doc.appendChild(rootElement);
			
			Element headerElem = doc.createElement("HEADER");
			rootElement.appendChild(headerElem);
			
			Element headerElem1 = doc.createElement("TALLYREQUEST");
			headerElem1.appendChild(doc.createTextNode("Import Data"));
			headerElem.appendChild(headerElem1);
			
			Element bodyElem = doc.createElement("BODY");
			rootElement.appendChild(bodyElem);
			
			Element importData = doc.createElement("IMPORTDATA");
			bodyElem.appendChild(importData);
			
			Element reqDesc = doc.createElement("REQUESTDESC");
			importData.appendChild(reqDesc);
			
			doc = makeReqDesc(reqDesc,doc,companyName);//Company Name is Mandatory
			
			Element reqData = doc.createElement("REQUESTDATA");
			importData.appendChild(reqData);
			
			doc = makeReqData(reqData,doc,currencyListMap);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("D:\\file123.xml"));
			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		
	}

	private static Document makeReqData(Element reqData, Document doc, Map<String, String> currencyListMap) {
		String[] tagType = {"CURRENCY","GROUP","LEDGER"};
		Element dynamicTag;
		
		for(int i=0;i<tagType.length;i++){
			
			Element tallyMessage = doc.createElement("TALLYMESSAGE");
			tallyMessage.setAttribute("xmlns:UDF","TallyUDF");
			reqData.appendChild(tallyMessage);
			
			dynamicTag = doc.createElement(tagType[i]);
			dynamicTag.setAttribute("NAME","$");
			dynamicTag.setAttribute("RESERVEDNAME","");
			tallyMessage.appendChild(dynamicTag);
			
			doc = makeCurrencyTags(tallyMessage,doc,currencyListMap);
			
		}
		
		return doc;
	}

	private static Document makeCurrencyTags(Element tallyMessage, Document doc, Map<String, String> currencyListMap) {
		Element currencyDynamicTag;
		
/*		currencyListMap.forEach((key,value) -> {
			currencyDynamicTag = doc.createElement(key);
			currencyDynamicTag.appendChild(doc.createTextNode(value));
			tallyMessage.appendChild(currencyDynamicTag);
		});*/
		
		for (Map.Entry<String, String> entry : currencyListMap.entrySet()) {
			currencyDynamicTag = doc.createElement(entry.getKey());
			currencyDynamicTag.appendChild(doc.createTextNode(entry.getValue()));
			tallyMessage.appendChild(currencyDynamicTag);
        }
		
		return doc;
	}

	private static Document makeReqDesc(Element reqDesc, Document doc, String companyName) {
		
		Element reportName = doc.createElement("REPORTNAME");
		reportName.appendChild(doc.createTextNode("All Masters"));
		reqDesc.appendChild(reportName);
		
		Element staticVar = doc.createElement("STATICVARIABLES");
		reqDesc.appendChild(staticVar);
		
		Element svCurrCompany = doc.createElement("SVCURRENTCOMPANY");
		svCurrCompany.appendChild(doc.createTextNode(companyName));
		staticVar.appendChild(svCurrCompany);
		
		return doc;
	}
	
}
