package image_editor;


/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
 
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.*;
import javax.swing.*;

import com.sun.javafx.font.directwrite.RECT;
 
/**
 * This class demonstrates how to load an Image from an external file
 */
public class LoadImageApp extends Component {
           
    static BufferedImage img;
    static Graphics2D g2d;
    static JFrame f;
    static ArrayList<Rectangle2D> points = new ArrayList<Rectangle2D>();
    static boolean addPoint = false;
    static boolean newPoint = false;
    static int pointSelected;
 
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
 
    public LoadImageApp(String file) {
       try {
           img = ImageIO.read(new File("/home/mathew/workspace/Minutae Changer/src/images/"+file));
       } catch (IOException e) {
       }
 
    }
 
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
 
    public static void main(String[] args) {
    	
        f = new JFrame("Load Image Sample");
        f.add(new LoadImageApp("finger.jpg"));
        g2d = img.createGraphics();

        f.pack();
        f.setVisible(true);

        g2d.setColor(Color.green);
//        g2d.fill(new Rectangle2D.Double(100, 100, 20, 20));
//
//        f.add(new LoadImageApp("finger.jpg"));
        
        
        
        
        // Listeners
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
        f.addMouseListener(new MouseListener() {
        	public void mouseClicked(MouseEvent e) {
            }

			public void mousePressed(MouseEvent e) {
				double x=(double)e.getX();
                double y=(double)e.getY();
                if (addPoint == false && newPoint == false) {
					for (int i = 0; i < points.size(); i++) {
	                	if (points.get(i).contains(x, y)) {
	                		addPoint = true;
	                		pointSelected = i;
	                		break;
	                	}
	                }
                }
                if (addPoint == true || newPoint == true) {
                	//f.add(new LoadImageApp("finger.jpg"));
	                points.set(pointSelected, new Rectangle2D.Double(x - 10, y - 10, 20, 20));
	                for (int i = 0; i < points.size(); i++) {
                		g2d.fill(points.get(i));
                	}
	                f.repaint();
                }
                else if (addPoint == false) {
                	points.add(new Rectangle2D.Double(x - 10, y - 10, 20, 20));
                	newPoint = true;
                	for (int i = 0; i < points.size(); i++) {
	                	if (points.get(i).contains(x, y)) {
	                		addPoint = true;
	                		pointSelected = i;
	                		break;
	                	}
	                }
                }
			}

			public void mouseReleased(MouseEvent e) {
				int x=e.getX();
                int y=e.getY();
                if (addPoint == true || newPoint == true) {
                	//f.add(new LoadImageApp("finger.jpg"));
	                points.set(pointSelected, new Rectangle2D.Double(x - 10, y - 10, 20, 20));
	                for (int i = 0; i < points.size(); i++) {
                		g2d.fill(points.get(i));
                	}
	                
	                f.repaint();
	                
	                addPoint = false;
	                newPoint = false;
                }
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
    }

}
