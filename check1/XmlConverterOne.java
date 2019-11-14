package XmlConverterOne;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
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
	
	public static String createXml() throws SAXException, IOException{
		String outputXmlSource="";
		try {
			Map<String,Map<String,String>> allTagsMap = new HashMap<>();
			Map<String,String> currencyListMap = new HashMap<>();
			
			currencyListMap.put("MAILINGNAME", "INR");
			currencyListMap.put("ORIGINALNAME", "$");
			currencyListMap.put("EXPANDEDSYMBOL", "INR");
			currencyListMap.put("DECIMALSYMBOL", "paise");
			currencyListMap.put("DECIMALPLACES", "2");
			currencyListMap.put("DECIMALPLACESFORPRINTING", "2");
			
			allTagsMap.put("Currency$", currencyListMap);
			
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
			StringWriter sw = new StringWriter();
			transformer.transform(source, new StreamResult(sw));
			outputXmlSource = sw.toString();
			StreamResult result = new StreamResult(new File("D:\\file123.xml"));
			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		return outputXmlSource;
	}

	private static Document makeReqData(Element reqData, Document doc, Map<String, String> currencyListMap) {
		String[] tagType = {"CURRENCY","GROUP","LEDGER"};
		Element dynamicTag;
		
		for(int i=0;i<tagType.length;i++){
			
			Element tallyMessage = doc.createElement("TALLYMESSAGE");
			tallyMessage.setAttribute("xmlns:UDF","TallyUDF");
			reqData.appendChild(tallyMessage);
			
			dynamicTag = doc.createElement(tagType[i]);
			
			dynamicTag.setAttribute("RESERVEDNAME","");
			dynamicTag.setAttribute("NAME","$");
			dynamicTag.setAttribute("ACTION","Create");
			tallyMessage.appendChild(dynamicTag);
			
			doc = makeDynamicTags(dynamicTag,doc,currencyListMap,tagType[i]);
			
		}
		
		return doc;
	}

	private static Document makeDynamicTags(Element dynamicTag, Document doc, Map<String, String> currencyListMap, String tagType) {
		Element currencyDynamicTag;
		switch(tagType){
		case "CURRENCY":
			for (Map.Entry<String, String> entry : currencyListMap.entrySet()) {
				currencyDynamicTag = doc.createElement(entry.getKey());
				currencyDynamicTag.appendChild(doc.createTextNode(entry.getValue()));
				dynamicTag.appendChild(currencyDynamicTag);
			}
			break;
		case "GROUP":
			Element parentGroup = doc.createElement("PARENT");
			parentGroup.setTextContent("Current Liabilities");//Apppend Dynamic
			Element isCostCentre = doc.createElement("ISCOSTCENTRESON");
			isCostCentre.setTextContent("No");
			Element langName = doc.createElement("LANGUAGENAME.LIST");
			dynamicTag.appendChild(parentGroup);
			dynamicTag.appendChild(isCostCentre);
			dynamicTag.appendChild(langName);
			
			Element nameList = doc.createElement("NAME.LIST");
			nameList.setAttribute("TYPE", "String");
			Element typeName = doc.createElement("NAME");
			typeName.setTextContent("Sundry Creditors"); //Append Dynamic
			nameList.appendChild(typeName);
			Element langId = doc.createElement("LANGUAGEID");
			langId.setTextContent("1033");
			
			langName.appendChild(nameList);
			langName.appendChild(langId);
			
			break;
		case "LEDGER":
			Element parentLedgerGroup = doc.createElement("PARENT");
			parentLedgerGroup.setTextContent("Cash-in-Hand");//Apppend Dynamic
			Element taxType = doc.createElement("TAXTYPE");
			taxType.setTextContent("Others");
			Element ledgerLangName = doc.createElement("LANGUAGENAME.LIST");
			dynamicTag.appendChild(parentLedgerGroup);
			dynamicTag.appendChild(taxType);
			dynamicTag.appendChild(ledgerLangName);
			
			Element ledgerNameList = doc.createElement("NAME.LIST");
			ledgerNameList.setAttribute("TYPE", "String");
			Element ledgerName = doc.createElement("NAME");
			ledgerName.setTextContent("Cash"); //Append Dynamic
			ledgerNameList.appendChild(ledgerName);
			Element ledgerLangId = doc.createElement("LANGUAGEID");
			ledgerLangId.setTextContent("1033");
			
			ledgerLangName.appendChild(ledgerNameList);
			ledgerLangName.appendChild(ledgerLangId);
			break;
		default:
			break;
		}
/*		currencyListMap.forEach((key,value) -> {
			currencyDynamicTag = doc.createElement(key);
			currencyDynamicTag.appendChild(doc.createTextNode(value));
			tallyMessage.appendChild(currencyDynamicTag);
		});*/
		
		
		
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
