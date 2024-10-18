package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 100; 

	private GPSComputer gpscomputer;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Speed profile", 
				2 * MARGIN + 
				2 * gpscomputer.speeds().length, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT);
	}
	
	public void showSpeedProfile(int ybase) {
		
		double [] speeds = gpscomputer.speeds();
		double avgSpeed = gpscomputer.averageSpeed();
		
		int N = speeds.length;
		
		double maxSpeed = GPSUtils.findMax(speeds);
		double yscale = (double) BARHEIGHT / maxSpeed;
		
		int x = MARGIN;
		
		for (int i = 0; i < N; i++) {
			
			int speedHeight = (int) (speeds[i] * yscale);
			int y = ybase - speedHeight;
			
			setColor(0,0,255);
			drawLine(x,ybase,x,y);
			
			x += 3;
			
		}
		
		int avgSpeedHeight = (int) (avgSpeed * yscale);
		int avgY = ybase - avgSpeedHeight;
		
		setColor(0,255,0);
		drawLine(MARGIN,avgY,MARGIN+N*2,avgY);
	
		// TODO
		//throw new UnsupportedOperationException(TODO.method());
		
	}
}
