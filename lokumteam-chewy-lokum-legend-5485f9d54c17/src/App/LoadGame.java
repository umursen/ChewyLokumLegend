package App;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import test.BoardLogicTest;

import java.io.File;
import java.io.IOException;

public class LoadGame {
	
	public static boolean loadGameFromXML(){
		
		
		
		try {
			LogicField[][] loadedBoard = new LogicField[Constants.BOARD_HEIGHT][Constants.BOARD_WIDTH];
			
			File saveFile = new File("save.xml");
			String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		    SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
		    Schema schema = schemaFactory.newSchema(new File("game.xsd"));
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(saveFile);
			
			/* Validation */
			if(!validateFromXsd(schema,document)){
				return false;
			}
			
			
			document.getDocumentElement().normalize();
			
			NodeList inGameNodes = document.getDocumentElement().getChildNodes();
			/* Player */
			/* Nothing to do with player yet */
			
			
			/* Level */
			Node level = inGameNodes.item(1);
			NodeList levelFeatures = level.getChildNodes();
			int level_id = Integer.parseInt(levelFeatures.item(0).getTextContent());
			String level_type = levelFeatures.item(1).getTextContent();
			int left = Integer.parseInt(levelFeatures.item(2).getTextContent());
			int special_moves = Integer.parseInt(levelFeatures.item(3).getTextContent());
			int goalScore = Integer.parseInt(levelFeatures.item(4).getTextContent());
			int currentScore = Integer.parseInt(levelFeatures.item(5).getTextContent());
			
			System.out.println(level_id);
			Options.getInstance().setLevel(level_id);
			Options.getInstance().targetScore = goalScore;
			Score.getInstance().setScore(currentScore);
			
			Level levelObject = Level.getInstance();
			
			levelObject.setSpecialMoves(special_moves);
			if (level_type.equals("move")){
				((MoveLevel)levelObject).setMovesLeft(left); 
			}else if(level_type.equals("time")){
				((TimeLevel)levelObject).updateTime(left);
			}
			
			
			/* Board */
			Node board = inGameNodes.item(2);
			
			/* Lokums */
			NodeList lokums = board.getChildNodes().item(0).getChildNodes();
			for(int i=0; i<lokums.getLength();i++){
				Node lokumNode = lokums.item(i);
				NodeList lokumFeatures = lokumNode.getChildNodes();
				String color = lokumFeatures.item(0).getTextContent();
				int x = Integer.parseInt(lokumFeatures.item(1).getFirstChild().getTextContent());
				int y = Integer.parseInt(lokumFeatures.item(1).getLastChild().getTextContent());
				String type = lokumFeatures.item(2).getTextContent();
				LogicField lokum = Factory.createLogicField(y, x, type, color);
				loadedBoard[y][x] = lokum;
			}
			
			/* Obstacles */
			NodeList obstacles = board.getChildNodes().item(1).getChildNodes();
			for(int i=0; i<obstacles.getLength();i++){
				Node obstacleNode = obstacles.item(i);
				NodeList obstacleFeatures = obstacleNode.getChildNodes();
				String color = obstacleFeatures.item(0).getTextContent();
				int x = Integer.parseInt(obstacleFeatures.item(1).getFirstChild().getTextContent());
				int y = Integer.parseInt(obstacleFeatures.item(1).getLastChild().getTextContent());
				LogicField obstacle = Factory.createLogicField(y, x, null, color);
				loadedBoard[y][x] = obstacle;
			}	
			
			BoardLogic.loadBoard(loadedBoard);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The save.xml format is incorrect!");
			return false;
		}
		return true;
	}
	
	private static boolean validateFromXsd(Schema schema, Document document){
		Validator validator = schema.newValidator();
		try {
			validator.validate(new DOMSource(document));
			return true;
		} catch (SAXException e) {
			System.out.println("XML validation unsuccesful!");
			return false;
		} catch (IOException e) {
			System.out.println("IO error occured.");
			return false;
		}
	}
}
