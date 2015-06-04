package latentfingerprints;

import java.applet.Applet;
import java.awt.Graphics; 
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.imageio.*;
import javax.swing.*;

public class imageapp extends Applet implements MouseListener,ItemListener,ActionListener,WindowListener{
	
	static StringBuffer strBuffer;
	static BufferedImage img;
    static Graphics2D g2d;
    static JFrame f;
    static JFrame btnFrame;
    static ArrayList<Rectangle2D> points = new ArrayList<Rectangle2D>();
    static boolean deletePoint = false;
    static int pointSelected;
    static String filename;
    static ArrayList<Rectangle2D> initialPoints = new ArrayList<Rectangle2D>();
    static ArrayList<Integer> pointNumbers = new ArrayList<Integer>();

	public void init() {
		addMouseListener(this);
		JPanel pnlButton = new JPanel();
		JCheckBox btn = new JCheckBox("Delete");
		JButton done = new JButton("Submit");
		add(done);	
		btn.addItemListener(this);
		done.addActionListener(this);
		addWindowListener(this);
		strBuffer = new StringBuffer();
		addItem("initializing the applet ");
    }

    public void start() {
        addItem("starting the applet ");
    }

    public void stop() {
        addItem("stopping the applet ");
    }

    public void destroy() {
        addItem("unloading the applet");
    }

	
	public imageapp(){		
	}
	
	public imageapp(String file){
		try{
			img = ImageIO.read(new File("/home/mathew/workspace/Minutae Changer/src/images/"+file));
		} catch (IOException e){			
		}
	}
	
	public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
	
	void addItem(String word) {
        System.out.println(word);
        strBuffer.append(word);
        repaint();
    }
 
	public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
	
	public static void initialRefresh() {
		f.add(new LoadImageApp("finger.jpg"));
		f.repaint();
    	g2d = img.createGraphics();
    	g2d.setColor(Color.blue);
    	for (int i = 0; i < initialPoints.size(); i++) {
    		g2d.setStroke(new BasicStroke(3.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f));
    		g2d.drawRect((int)initialPoints.get(i).getX(), (int)initialPoints.get(i).getY(), (int)initialPoints.get(i).getWidth(), (int)initialPoints.get(i).getHeight());
    		g2d.drawString(String.valueOf(pointNumbers.get(i)),(int)initialPoints.get(i).getX() - 20, (int)initialPoints.get(i).getY() - 20);
    	}
	}

	public void mouseClicked(MouseEvent e) {
    }

	public void mousePressed(MouseEvent e) {
		double x=(double)e.getX();
        double y=(double)e.getY();
        if (!deletePoint) {
            points.add(new Rectangle2D.Double(x - 10, y - 10, 20, 20));
            pointSelected = points.size() - 1;
        }
        else {
            for (int i = 0; i < points.size(); i++) {
                if (points.get(i).contains(x, y)) {
                	points.remove(i);
                }
            }
        }
        refreshFrame();

	}

	public void mouseReleased(MouseEvent e) {
		int x=e.getX();
        int y=e.getY();
        if (!deletePoint) {
        for (int i = 0; i < points.size(); i++) {
            if (pointSelected == i) {
                points.set(pointSelected, new Rectangle2D.Double(x - 10, y - 10, 20, 20));
            }
        }
        refreshFrame();
        }
	}

	public void refreshFrame() {
		f.add(new LoadImageApp("finger.jpg"));
		f.repaint();
        g2d = img.createGraphics();
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
	}
			
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
				
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
				
	}
	
	public void itemStateChanged(ItemEvent e) {
        deletePoint = !deletePoint;
    }
	
	public void actionPerformed(ActionEvent e) {
        PrintWriter writer;
		try {
			writer = new PrintWriter("/home/mathew/workspace/Minutae Changer/src/images/" + filename + ".txt" , "UTF-8");
			for (int i = 0; i < points.size(); i++) {
				writer.println((int)points.get(i).getX() + "," + (int)points.get(i).getY());
	        }
	        writer.close();
	        System.exit(0);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

	public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
	
	public static void main(String[] args) {    	
    	
        f = new JFrame("Load Image Sample");
        btnFrame = new JFrame("Button Frame");
        filename = "finger";
        f.add(new LoadImageApp(filename + ".jpg"));
        g2d = img.createGraphics();

        f.pack();
        btnFrame.pack();
        f.setVisible(true);
        btnFrame.setVisible(true);
        btnFrame.setSize(200, 200);

        g2d.setColor(Color.red);

        // Adding to JFrame
        pnlButton.add(btn);
        pnlButton.add(done);
        btnFrame.add(pnlButton);
        
        loader(filename + ".txt");
        initialRefresh();     
    
        
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
