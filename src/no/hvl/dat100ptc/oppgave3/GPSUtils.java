package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;
import java.util.Locale;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min = da[0];
		
		for (double d : da) {
			if (d<min) {
				min = d;
			}
		}
		return min;

		
		
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] latitudeTab = new double[gpspoints.length];
		for (int i = 0; i<latitudeTab.length; i++) {
			latitudeTab[i] = gpspoints[i].getLatitude();
		} return latitudeTab;
	} 

	

	
	
	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] longitudeTab = new double[gpspoints.length];
		
		for (int i = 0; i<gpspoints.length; i++) {
			longitudeTab[i] = gpspoints[i].getLongitude();
		}
		return longitudeTab;
	
	
		
		

	}

	private static final int R = 6371000; // jordens radius

	
	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		
		latitude1 = gpspoint1.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		
		latitude2 = gpspoint2.getLatitude();
		longitude2 = gpspoint2.getLongitude();
		
		
		double phi1 = latitude1 * (PI/180);
		double phi2 = latitude2 * (PI/180);
		double deltaphi = phi2-phi1;
		double deltadelta = (longitude2 * (PI/180)) - (longitude1 * PI/180);
		
		double a = compute_a(phi1, phi2, deltaphi, deltadelta);
		
		double c = compute_c(a);
		
		d = R*c;
		return d;
		
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
	
		double a = (Math.pow(Math.sin(deltaphi/2), 2)) + Math.cos(phi1) * Math.cos(phi2) * (Math.pow(Math.sin(deltadelta/2),2));
		return a;
		
		
		

	}

	private static double compute_c(double a) {
		
		double c  = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return c;

		
		
		
		
		

	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
		
		int tid1 = gpspoint1.getTime();
		int tid2 = gpspoint2.getTime();
		
		secs = tid2-tid1;
		
		double distance = distance(gpspoint1, gpspoint2);
		
		speed = distance/secs;
		return speed;
		
		
		
		
		
		
		
	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		
		int hour,min,rmin,sec;
		
		hour = secs/3600;
		rmin = secs%3600;
		min = rmin/60;
		sec = rmin%60;
		
		timestr = String.format("  %02d" + TIMESEP + "%02d" + TIMESEP + "%02d", hour, min, sec);
		return timestr;
		
		
		
		
		
		
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		
		d = Math.round(d * 100)/100.0;
		String txt = String.format(Locale.ENGLISH,"%.2f", d); //Locale.ENGLISH slik at det blir . istedenfor ,
		
		int mellomrom = TEXTWIDTH - txt.length();
		
		
		String str = "";
		for (int i = 0; i < mellomrom; i++) {
			str += " ";
		}
		str += txt;
		
		return str;
		
		
		
		
	}
}
