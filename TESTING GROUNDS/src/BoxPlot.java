import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;



/*
 * BoxPlot JPanel. Currently hard coded for line drawing only. May introduce drawing
 * rectangles to represent part of the image. Only accepts a double[] with 5 entries. 
 * No checks are done to ensure the correct order is given. 
 */


/**
 * Creates a JPanel that accepts a Double array and draws
 * a visual representation as a statistics BoxPlot.
 * 
 * @author Kirsten Grace
 * @version 11.15.13
 *
 */


@SuppressWarnings("serial")
public class BoxPlot extends JPanel {
    
    /** Represents percentage. */
    private static final double FORTY_PERCENT = .40;
    /** Represents percentage. */
    private static final double FORTY_FIVE_PERCENT = .45;
    /** Represents percentage. */
    private static final double FIFTY_PERCENT = .50;
    /** Represents percentage. */
    private static final double FIFTY_FIVE_PERCENT = .55;
    /** Represents percentage. */
    private static final double SIXTY_PERCENT = .60;
    /** Represents percentage. */
    private static final double SIXTY_FIVE_PERCENT = .65;
    /** Represents percentage. */
    private static final double EIGHTY_PERCENT = .80;
    /** JPanel Size. */
    private static final Dimension DEFAULT_SIZE = new Dimension(500, 400);
    
    /** Map. */
    private static final Map<String, Color> DEFAULT_COLORS = new HashMap<String, Color>() {
     {
    	 put("BACKGROUND", Color.white);
    }};
    
    
    /** This is a comment. */
    private static final double[] TEST_ONE = new double[] {0.0, 16.0, 3.0, 6.0, 8.0};
    
    /** This is a comment. */
    private static final double[] TEST_TWO = new double[] {4.0, 50.0, 23.0, 27.0, 41.0}; 
    
    /** This is a comment. */
    private static final double[] TEST_THREE = new double[] {0.0, 16.0, 3.0, 6.0, 8.0}; 
    
    /** This is a comment. */
    private double myMin;
    
    /** This is a comment. */
    private double myMax;
    
    /** This is a comment. */
    private double myFirstQ;
    
    /** This is a comment. */
    private double mySecondQ;
    
    /** This is a comment. */
    private double myThirdQ;
    
    /** This is a comment. */
	private Color myLineColor;
    

    /**
     * Accepts an array of type double in the following order:
     * min, max, firstQ, secondQ, thirdQ.
     * 
     * @param theDoubles the array used to create the boxplot
     */
    public BoxPlot(final double[] theDoubles) {

        myMin = theDoubles[0];
        myMax = theDoubles[1];
        myFirstQ = theDoubles[2];
        mySecondQ = theDoubles[3];
        myThirdQ = theDoubles[4];
        this.setName("BoxPlot");
        
        super.setBackground(DEFAULT_COLORS.get("BACKGROUND"));
    }
    
    
    /**
     * Accepts 5 doubles in the following order:
     * min, max, firstQ, secondQ, thirdQ.
     * 
     * @param theDoubles the array used to create the boxplot
     */
    public BoxPlot(final double theMin, final double theMax,
    		final double theFirstQ, final double theSecondQ,
    		final double theThirdQ) {

        myMin = theMin;
        myMax = theMax;
        myFirstQ = theFirstQ;
        mySecondQ = theSecondQ;
        myThirdQ = theThirdQ;
        this.setName("BoxPlot");
        
        
    }
    

    @Override    
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        
        final Graphics2D g2d =  (Graphics2D) theGraphics;
        if(myLineColor != null) {
        	g2d.setColor(myLineColor);        
        }
        
        
        final double offset = 5;
        final double xMin = getWidth() * .10; 
        final double xMax = getWidth() * .90;
        final double xFirstQ = xMin + (getWidth() * EIGHTY_PERCENT 
                * (myFirstQ / (myMax - myMin)));
        final double xSecondQ = xMin + (getWidth() * EIGHTY_PERCENT 
                * (mySecondQ / (myMax - myMin)));
        final double xThirdQ = xMin + (getWidth() * EIGHTY_PERCENT 
                * (myThirdQ / (myMax - myMin)));
        
        g2d.setStroke(new BasicStroke(2)); 

        // Line that connects between Min and FirstQ
        g2d.drawLine((int) xMin,
                     (int) (getHeight() * FIFTY_PERCENT),
                     (int) xFirstQ,
                     (int) (getHeight() * FIFTY_PERCENT));

        // Line that connects between ThirdQ and Max
        g2d.drawLine((int) xThirdQ,
                     (int) (getHeight() * FIFTY_PERCENT),
                     (int) xMax,
                     (int) (getHeight() * FIFTY_PERCENT));
        
        // Lines that connects between FirstQ and ThirdQ
        g2d.drawLine((int) xFirstQ,
                     (int) (getHeight() * FORTY_FIVE_PERCENT),
                     (int) xThirdQ,
                     (int) (getHeight() * FORTY_FIVE_PERCENT));
        
        g2d.drawLine((int) xFirstQ,
                     (int) (getHeight() * FIFTY_FIVE_PERCENT),
                     (int) xThirdQ,
                     (int) (getHeight() * FIFTY_FIVE_PERCENT));
 
        // Minimum Line and Label
        g2d.drawLine((int) xMin, 
                     (int) (getHeight() * FORTY_PERCENT), 
                     (int) xMin, 
                     (int) (getHeight() * SIXTY_PERCENT));
        g2d.drawString(String.valueOf((int) myMin), 
                       (int) (xMin - offset), 
                       (int) (getHeight() * SIXTY_FIVE_PERCENT));

        // FirstQ Line and Label
        g2d.drawLine((int) xFirstQ, 
                     (int) (getHeight() * FORTY_FIVE_PERCENT), 
                     (int) xFirstQ, 
                     (int) (getHeight() * FIFTY_FIVE_PERCENT));
        g2d.drawString(String.valueOf((int) myFirstQ), 
                       (int) (xFirstQ - offset), 
                       (int) (getHeight() * SIXTY_FIVE_PERCENT));        

        // SecondQ Line and Label
        g2d.drawLine((int) xSecondQ, 
                     (int) (getHeight() * FORTY_FIVE_PERCENT), 
                     (int) xSecondQ, 
                     (int) (getHeight() * FIFTY_FIVE_PERCENT));
        g2d.drawString(String.valueOf((int) mySecondQ), 
                       (int) (xSecondQ - offset), 
                       (int) (getHeight() * SIXTY_FIVE_PERCENT));    

        // ThirdQ Line and Label
        g2d.drawLine((int) xThirdQ, 
                     (int) (getHeight() * FORTY_FIVE_PERCENT), 
                     (int) xThirdQ, 
                     (int) (getHeight() * FIFTY_FIVE_PERCENT));
        g2d.drawString(String.valueOf((int) myThirdQ), 
                       (int) (xThirdQ - offset), 
                       (int) (getHeight() * SIXTY_FIVE_PERCENT));      
        
        // Maximum Line and Label
        g2d.drawLine((int) xMax, 
                     (int) (getHeight() * FORTY_PERCENT), 
                     (int) xMax, 
                     (int) (getHeight() * SIXTY_PERCENT));
        g2d.drawString(String.valueOf((int) myMax), 
                       (int) (xMax - offset), 
                       (int) (getHeight() * SIXTY_FIVE_PERCENT));
        
//        
//        g2d.setStroke(new BasicStroke(1));
//        g2d.setColor(Color.GRAY);
//        //vertical grid every 10%
//        for (double x = 0; x < getWidth(); x += getWidth() * .10) {
//            g2d.drawLine((int) x, 0, (int) x, getHeight());            
//        }
//        //horizontal grid every 10%
//        for (double y = 0; y < getHeight(); y += getHeight() * .10) {
//            g2d.drawLine(0, (int) y, getWidth(), (int) y);            
//        }
//        


    }
    
    
    public void setLineColor(final Color theLineColor) {
    	myLineColor = theLineColor;
    }
    
    
    /** Starts the program. @param theArgs the command line arguments*/
    public static void main(final String[] theArgs) {
        final JFrame frame = new JFrame("This is a tester!");
        frame.setSize(DEFAULT_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BoxPlot oneBoxPlot = new BoxPlot(TEST_ONE);
        frame.add(oneBoxPlot); 
        oneBoxPlot.setLineColor(Color.GREEN);
        oneBoxPlot.setBackground(Color.BLACK);
 //       frame.setContentPane(new BoxPlot(TEST_TWO));
        frame.setVisible(true);
        
        
    }
    
    
}
