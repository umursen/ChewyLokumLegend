package App;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;


public class LokumBox {

	private LogicField logicField;
	private Image image;
	private int side=Constants.ANIMATION_WINDOW_WIDTH/Constants.NUMBER_OF_LOKUMS;


	public LokumBox(LogicField logicField){
		this.logicField = logicField;
	}

	public void paintComponent(Graphics g){
		Graphics2D g1 = (Graphics2D) g;
		
		g.setColor(Color.WHITE);
		if(logicField instanceof EmptyLogicField){
			g.fillRect(logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side);
		} else if(logicField instanceof Obstacle){
			g.setColor(Color.darkGray);
			g.fillRect(logicField.getColumnIndex()*side,  Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side);
		} else {
			logicField = (Lokum) logicField;
		}
		
		if(logicField instanceof NormalLokum){
			switch(((Lokum)logicField).getLokumColor()){
				case Constants.LOKUM_COLOR_RED:
					ImageIcon redNormalLokumImage = new ImageIcon("images/normal_red_lokum.png");
					image = redNormalLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;
				case Constants.LOKUM_COLOR_GREEN:
					ImageIcon greenNormalLokumImage = new ImageIcon("images/normal_green_lokum.png");
					image = greenNormalLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;	
				case Constants.LOKUM_COLOR_BROWN:
					ImageIcon brownNormalLokumImage = new ImageIcon("images/normal_brown_lokum.png");
					image = brownNormalLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;
				case Constants.LOKUM_COLOR_WHITE:
					ImageIcon whiteNormalLokumImage = new ImageIcon("images/normal_white_lokum.png");
					image = whiteNormalLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;
				default: g.fillRect(logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side);
			}
		}
		if(logicField instanceof VerticalStripedLokum){
			switch(((Lokum)logicField).getLokumColor()){
				case Constants.LOKUM_COLOR_RED:
					ImageIcon redStrippedLokumImage = new ImageIcon("images/vertical_stripped_red_lokum.png");
					image = redStrippedLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;
				case Constants.LOKUM_COLOR_GREEN:
					ImageIcon yellowStrippedLokumImage = new ImageIcon("images/vertical_stripped_green_lokum.png");
					image = yellowStrippedLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;	
				case Constants.LOKUM_COLOR_BROWN:
					ImageIcon brownStrippedLokumImage = new ImageIcon("images/vertical_stripped_brown_lokum.png");
					image = brownStrippedLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;	
				case Constants.LOKUM_COLOR_WHITE:
					ImageIcon whiteStrippedLokumImage = new ImageIcon("images/vertical_stripped_white_lokum.png");
					image = whiteStrippedLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;						
				default: g.fillRect(logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side);
			}
		}
		
		if(logicField instanceof HorizontalStripedLokum){
			switch(((Lokum)logicField).getLokumColor()){
				case Constants.LOKUM_COLOR_RED:
					ImageIcon redStrippedLokumImage = new ImageIcon("images/horizontal_stripped_red_lokum.png");
					image = redStrippedLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;
				case Constants.LOKUM_COLOR_GREEN:
					ImageIcon yellowStrippedLokumImage = new ImageIcon("images/horizontal_stripped_green_lokum.png");
					image = yellowStrippedLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;	
				case Constants.LOKUM_COLOR_BROWN:
					ImageIcon brownStrippedLokumImage = new ImageIcon("images/horizontal_stripped_brown_lokum.png");
					image = brownStrippedLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;	
				case Constants.LOKUM_COLOR_WHITE:
					ImageIcon whiteStrippedLokumImage = new ImageIcon("images/horizontal_stripped_white_lokum.png");
					image = whiteStrippedLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;						
				default: g.fillRect(logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side);
			}
		}
		
		if(logicField instanceof WrappedLokum){
			switch(((Lokum)logicField).getLokumColor()){
				case Constants.LOKUM_COLOR_RED:
					ImageIcon redWrappedLokumImage = new ImageIcon("images/wrapped_red_lokum.png");
					image = redWrappedLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;
				case Constants.LOKUM_COLOR_GREEN:
					ImageIcon yellowWrappedLokumImage = new ImageIcon("images/wrapped_green_lokum.png");
					image = yellowWrappedLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;	
				case Constants.LOKUM_COLOR_BROWN:
					ImageIcon brownWrappedLokumImage = new ImageIcon("images/wrapped_brown_lokum.png");
					image = brownWrappedLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;	
				case Constants.LOKUM_COLOR_WHITE:
					ImageIcon whiteWrappedLokumImage = new ImageIcon("images/wrapped_white_lokum.png");
					image = whiteWrappedLokumImage.getImage();
					g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;						
				default: g.fillRect(logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side);
			}
		}
		
		if(logicField instanceof BombLokum){
			ImageIcon bombLokumImage = new ImageIcon("images/bomb_lokum.png");
			image = bombLokumImage.getImage();
			g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null);
		}
		
		if(logicField instanceof TimeLokum){
			switch(((TimeLokum)logicField).getLokumColor()){
			case Constants.LOKUM_COLOR_RED:
				ImageIcon redTimeLokumImage = new ImageIcon("images/time_red_lokum.png");
				image = redTimeLokumImage.getImage();
				g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;
			case Constants.LOKUM_COLOR_GREEN:
				ImageIcon greenTimeLokumImage = new ImageIcon("images/time_green_lokum.png");
				image = greenTimeLokumImage.getImage();
				g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;	
			case Constants.LOKUM_COLOR_BROWN:
				ImageIcon brownTimeLokumImage = new ImageIcon("images/time_brown_lokum.png");
				image = brownTimeLokumImage.getImage();
				g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;
			case Constants.LOKUM_COLOR_WHITE:
				ImageIcon whiteTimeLokumImage = new ImageIcon("images/time_white_lokum.png");
				image = whiteTimeLokumImage.getImage();
				g.drawImage(image, logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side, null); break;
			default: g.fillRect(logicField.getColumnIndex()*side, Constants.ANIMATION_WINDOW_HEIGHT - (logicField.getRowIndex() + 1)*side, side, side);
			}
		}
		
	}

	public void changeLogicField(LogicField logicField) {
		this.logicField=logicField;
	}
}
