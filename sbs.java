import ij.*;
//import ij.gui.*;
//import java.awt.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
//import java.awt.color.*;


public class sbs implements PlugInFilter {
	public void run(ImageProcessor ip) {
		ImagePlus left = WindowManager.getImage(1);
		
		ImagePlus right = WindowManager.getImage(2);
		
		int width = ip.getWidth();
		int height = ip.getHeight();
		
		ImageProcessor leftIP = left.getProcessor();
		ImageProcessor rightIP = right.getProcessor();
		ImagePlus sbs = IJ.createImage("SBS", "RGB white", width*2, height, 1);
		
		ImageProcessor sbsIP =  sbs.getProcessor();
		
		sbsIP.insert(leftIP,0,0);
		sbsIP.insert(rightIP,width ,0 );
		
		sbs.show();
	}

	public int setup(String arg, ImagePlus imp)
	{
		return DOES_RGB;
	}
}

