package image_editor;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class changer extends JApplet{
	
	static boolean deletePoint = false;
	JMenuBar menuBar;
	JMenuItem submit;
	JCheckBoxMenuItem deleteBox;
	JLabel picture;
	JLabel picture2;
    static BufferedImage img;
    static int pointSelected;
    static Graphics2D g2d;
    static ArrayList<Rectangle2D> points = new ArrayList<Rectangle2D>();
    static ArrayList<Rectangle2D> initialPoints = new ArrayList<Rectangle2D>();
    static ArrayList<Integer> pointNumbers = new ArrayList<Integer>();
    
	public void init(){
		picture = new JLabel();
		menuBar = new JMenuBar();
		submit = new JMenuItem("Submit");
		deleteBox = new JCheckBoxMenuItem("Delete");
		deleteBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				deletePoint = !deletePoint;
			}
		});
		submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				System.out.println(deletePoint);
				
			}
			
		});
		menuBar.add(deleteBox);
		menuBar.add(submit);
		setJMenuBar(menuBar);
		loadimage("finger.jpg");
		picture.setIcon(new ImageIcon(img));
		setContentPane(picture);
		setSize(img.getWidth(),img.getHeight());
		getContentPane().addMouseListener(new MouseListener(){
	
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			
			public void mousePressed(MouseEvent e) {
				double x=(double)e.getX();
                double y=(double)e.getY();
                if (!deletePoint) {
                	points.add(new Rectangle2D.Double(x - 10 , y + 40 , 20, 20));
                	pointSelected = points.size() - 1;
                }
                else {
                	for (int i = 0; i < points.size(); i++) {
                		if (points.get(i).contains(x - 10, y + 40)) {
                			points.remove(i);
                			System.out.println("removed");
                		}
                	}
                }
                paint();
			}

			
			public void mouseReleased(MouseEvent e) {
				int x=e.getX();
                int y=e.getY();
                if (!deletePoint) {
                	for (int i = 0; i < points.size(); i++) {
                		if (pointSelected == i) {
                			points.set(pointSelected, new Rectangle2D.Double(x -10 , y + 40, 20, 20));
                		}
                	}
                }
				paint();
				
			}
			
		});
		loader("finger.txt");
		paint();
	}

	public void paint(){
		picture.setIcon(new ImageIcon(img));
		getContentPane().removeAll();
		g2d = img.createGraphics();
		picture.setIcon(new ImageIcon(img));
		loadimage("finger.jpg");
    	g2d.setColor(Color.red);
    	for (int i = 0; i < points.size(); i++) {
    		g2d.setStroke(new BasicStroke(3.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f));
    		g2d.drawRect((int)points.get(i).getX(), (int)points.get(i).getY(), (int)points.get(i).getWidth(), (int)points.get(i).getHeight());
    		
    	}
    	g2d.setColor(Color.blue);
    	for (int i = 0; i < initialPoints.size(); i++) {		
    		g2d.setStroke(new BasicStroke(3.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f));
    		g2d.drawRect((int)initialPoints.get(i).getX(), (int)initialPoints.get(i).getY(), (int)initialPoints.get(i).getWidth(), (int)initialPoints.get(i).getHeight());
    		g2d.drawString(String.valueOf(pointNumbers.get(i)),(int)initialPoints.get(i).getX() - 20, (int)initialPoints.get(i).getY() - 20);
    	}
    	repaint();
	}
	
	public void loadimage(String file){
		try {
	           img = ImageIO.read(new File("/home/mathew/workspace/Minutae Changer/src/images/" + file));
	       } catch (IOException e) {
	    	   System.out.println("couldnt load file");
	       }	
		}
	public static void loader(String file) {
        // The name of the file to open.
        String fileName = "/home/mathew/workspace/Minutae Changer/src/images/" + file;

        double x;
        double y;
        int count = 1;
        
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            

            while((line = bufferedReader.readLine()) != null) {
            	String[] parts = line.split(",");
                x = (double)Integer.parseInt(parts[0]);
                y = (double)Integer.parseInt(parts[1]);
                initialPoints.add(new Rectangle2D.Double(x - 10, y - 10, 20, 20));
                pointNumbers.add(count);
                count++;
            }    

            // Always close files.
            bufferedReader.close();            
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                   
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
	
}
