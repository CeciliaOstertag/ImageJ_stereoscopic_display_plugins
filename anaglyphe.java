import ij.*;
//import ij.gui.*;
//import java.awt.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import ij.plugin.ChannelSplitter;
//import java.awt.color.*;


public class anaglyphe implements PlugInFilter{
	public void run(ImageProcessor ip) {
		
		//duplication de l'image
		ImagePlus left = WindowManager.getImage(1);		
		ImagePlus right = left.duplicate();
		
		int width = ip.getWidth();
		int height = ip.getHeight();
		
		ImageProcessor leftIP = left.getProcessor();
		ImageProcessor rightIP = right.getProcessor();
		
		int decalage = 5;
		
		//image de gauche : canal R
		ImagePlus R = IJ.createImage("R", "RGB black", width+decalage, height, 1); 		
		ImageProcessor Rip =  R.getProcessor();		
		Rip.insert(leftIP,0,0);
		ImagePlus[] channelsR = ChannelSplitter.split(R);
		ImagePlus red = channelsR[0];
		red.show();
		
		//image de droite : canaux G et B
		ImagePlus GB = IJ.createImage("GB", "RGB black", width+decalage, height, 1); 
		ImageProcessor GBip =  GB.getProcessor();		
		GBip.insert(rightIP,decalage,0);		
		ImagePlus[] channelsGB = ChannelSplitter.split(GB);
		ImagePlus green = channelsGB[1];
		ImagePlus blue = channelsGB[2];
		green.show();
		blue.show();
		
		IJ.run("Merge Channels...", "c1=[red] c2=[green] c3=[blue]");

	}

	public int setup(String arg, ImagePlus imp)
	{
		return DOES_RGB;
	}
}

