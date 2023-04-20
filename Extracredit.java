import java.awt.*;
import java.awt.image.*;
import java.io.BufferedInputStream;
import java.nio.Buffer;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Extracredit {
	static Timer timer1;
	static Timer timer2;
	static JFrame frame;
	JLabel lbIm1;
	JLabel lbIm2;
	BufferedImage img;
	BufferedImage fps_img;
	BufferedImage sc_img;
	int width = 512;
	int height = 512;
	static int var1 = 0;
	static int var2 = 0;

	// Draws a black line on the given buffered image from the pixel defined by (x1, y1) to (x2, y2)
	public void drawLine(BufferedImage image, int x1, int y1, int x2, int y2) {
		Graphics2D g = image.createGraphics();
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(1));
		g.drawLine(x1, y1, x2, y2);
		g.drawImage(image, 0, 0, null);
	}

	public void showIms(String[] args){

		// Read a parameter from command line
		String param0 = args[0];
		String param1 = args[1];
		String param2 = args[2];
		int n = Integer.parseInt(param0);

		double s = Double.parseDouble(param1);
		double fps = Double.parseDouble(param2);

		// Initialize a plain white image
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		fps_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){

				byte r = (byte) 255;
				byte g = (byte) 255;
				byte b = (byte) 255;
				int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);

				img.setRGB(x,y,pix);
				fps_img.setRGB(x, y, pix);
			}
		}

		drawLine(img, 0, 0, width-1, 0);				// top edge
		drawLine(img, 0, 0, 0, height-1);				// left edge
		drawLine(img, 0, height-1, width-1, height-1);	// bottom edge
		drawLine(img, width-1, height-1, width-1, 0); 	// right edge

		drawLine(fps_img, 0, 0, width-1, 0);				// top edge
		drawLine(fps_img, 0, 0, 0, height-1);				// left edge
		drawLine(fps_img, 0, height-1, width-1, height-1);	// bottom edge
		drawLine(fps_img, width-1, height-1, width-1, 0);

		double x = 0;
		double y = 0;
		
		// var1 += (2 * Math.PI / Double.parseDouble(args[2]) * Double.parseDouble(args[1]));
		for(int i = 0; i < n; i++){
			double r = Math.sqrt((255-x)*(255-x) + (255-y)*(255-y));
			double theta = (2 * i * Math.PI / n);
			x = r * Math.cos(theta) + 255;
			y = r * Math.sin(theta) + 255;
			drawLine(img, (int)x, (int)y, 255, 255);
			drawLine(fps_img, (int)x, (int)y, 255, 255);
		}
		// Use labels to display the images

		GridBagLayout gLayout = new GridBagLayout();
		frame.getContentPane().setLayout(gLayout);

		JLabel lbText1 = new JLabel("Original image (Left)");
		lbText1.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lbText2 = new JLabel("Image after modification (Right)");
		lbText2.setHorizontalAlignment(SwingConstants.CENTER);
		lbIm1 = new JLabel(new ImageIcon(img));
		lbIm2 = new JLabel(new ImageIcon(fps_img));
		frame.getContentPane().removeAll();

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		frame.getContentPane().add(lbText1, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		frame.getContentPane().add(lbText2, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		frame.getContentPane().add(lbIm1, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		frame.getContentPane().add(lbIm2, c);

		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		timer1 = new Timer();
		timer2 = new Timer();
		
		timer1.schedule(new TimerTask() {
			@Override
			public void run() {
				//img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				for(int y = 0; y < height; y++){
					for(int x = 0; x < width; x++){
						byte r = (byte) 255;
						byte g = (byte) 255;
						byte b = (byte) 255;
		
						int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
						img.setRGB(x,y,pix);
					}
				}
		
				drawLine(img, 0, 0, width-1, 0);				// top edge
				drawLine(img, 0, 0, 0, height-1);				// left edge
				drawLine(img, 0, height-1, width-1, height-1);	// bottom edge
				drawLine(img, width-1, height-1, width-1, 0); 	// right edge

				double x = 0;
				double y = 0;

				var1 += 360 * Double.parseDouble(args[1]) / 30.0;
				for(int i = 0; i < n; i++){
					// double r = Math.min(height, width) / 2;
					double r = Math.sqrt((255-x)*(255-x) + (255-y)*(255-y));
					double theta = (2 * i * Math.PI / n) + Math.toRadians(var1);
					x = r * Math.cos(theta) + 255;
					y = r * Math.sin(theta) + 255;
					drawLine(img, (int)x, (int)y, 255, 255);
					// drawLine(fps_img, (int)x, (int)y, 255, 255);
				}
				lbIm1.setIcon(new ImageIcon(img));
				lbIm1.repaint();

			}
			}, 0, (int)(1000/30.0));

		timer2.schedule(new TimerTask() {
			@Override
			public void run() {
				double scaled_portion = Double.parseDouble(args[4]);

				fps_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
				sc_img = new BufferedImage((int)(width*scaled_portion), (int)(height*scaled_portion), BufferedImage.TYPE_INT_RGB);
				
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){

					byte r = (byte) 255;
					byte g = (byte) 255;
					byte b = (byte) 255;
					int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);

					fps_img.setRGB(x, y, pix);
				}
			}

			
			for(int y = 0; y < (int)(height * scaled_portion); y++){
				for(int x = 0; x < (int)(width * scaled_portion) ; x++){

					byte r = (byte) 255;
					byte g = (byte) 255;
					byte b = (byte) 255;
					int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);

					sc_img.setRGB(x, y, pix);
				}
			}


			drawLine(fps_img, 0, 0, width-1, 0);				// top edge
			drawLine(fps_img, 0, 0, 0, height-1);				// left edge
			drawLine(fps_img, 0, height-1, width-1, height-1);	// bottom edge
			drawLine(fps_img, width-1, height-1, width-1, 0);

			double x = 0;
			double y = 0;

			// var = s * va/ Double.parseDouble(args[2]);
			var2 += 360 * Double.parseDouble(args[1]) / Double.parseDouble(args[2]);
			for(int i = 0; i < n; i++){
				double r = Math.sqrt((255-x)*(255-x) + (255-y)*(255-y));
				double theta = (2 * i * Math.PI / n) + Math.toRadians(var2);
				x = r * Math.cos(theta) + 255;
				y = r * Math.sin(theta) + 255;
				drawLine(fps_img, (int)x, (int)y, 255, 255);
			}
			
			if (Integer.parseInt(args[3]) == 0){
				for(int i = 0; i < (int)(height * scaled_portion); i++){
					for (int j = 0; j < (int)(width * scaled_portion); j++){
						int scaled_pix = fps_img.getRGB((int)(i/Double.parseDouble(args[4])), (int)(j/Double.parseDouble(args[4])));
						sc_img.setRGB(i, j, scaled_pix);
					}
				}
			}
			else{
				for(int i = 0; i < (int)(height * scaled_portion); i++){

					for (int j = 0; j < (int)(width * scaled_portion); j++){
						int total_red = 0;
						int total_blue = 0;
						int total_green = 0;
						int avg_red = 0;
						int avg_blue = 0;
						int avg_green = 0;
						for (int k = 0; k < 3; k++){
							for (int l = 0; l < 3; l++){
								if ((int)(i/scaled_portion)+k-1 <= -1 || (int)(i/scaled_portion)+k-1 >= 512 || (int)(j/scaled_portion)+l-1 <= -1 || (int)(j/scaled_portion)+l-1 >= 512){
									continue;
								}
								int scaled_pix = fps_img.getRGB((int)(i/scaled_portion)+k-1, (int)(j/scaled_portion)+l-1);
								int red = (scaled_pix >> 16) & 0x000000FF;
								int green = (scaled_pix >>8 ) & 0x000000FF;
								int blue = (scaled_pix) & 0x000000FF;
								total_red += red;
								total_blue += blue;
								total_green += green;
							}
						}
						avg_red = total_red / 9;
						avg_blue = total_blue / 9;
						avg_green = total_green / 9;
						
						byte byte_red = (byte) avg_red;
						byte byte_blue = (byte) avg_blue;
						byte byte_green = (byte) avg_green;
						int avg_pix = 0xff000000 | ((byte_red & 0xff) << 16) | ((byte_green & 0xff) << 8) | (byte_blue & 0xff);
	
						sc_img.setRGB(i, j, avg_pix);
					}
				}
			}

			lbIm2.setIcon(new ImageIcon(sc_img));
			lbIm2.repaint();
			
			}	
			}, 0, (int)(1000/Double.parseDouble(args[2])));
	}


	public static void main(String[] args) {
		frame = new JFrame();
		Extracredit ren = new Extracredit();
		ren.showIms(args);
	}
}
