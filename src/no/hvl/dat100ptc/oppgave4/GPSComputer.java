package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	
	
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	
	
	
	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	
	
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	
	
	
	
	public double totalDistance() {

		double distance = 0;
		
		for (int i =0; i<gpspoints.length-1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
			
			
		} return distance;



		
		
	}

	public double totalElevation() {

		double elevation = 0;
		double a=0;
		double b=0;
		
		for (int i = 0; i<gpspoints.length-1; i++) {
			a = gpspoints[i].getElevation();
			b = gpspoints[i+1].getElevation();
			
			if (a<b) {
				elevation += b-a;
			}
		}

		
		return elevation;
	}

	public int totalTime() {
		int totTime =0;
		int a = 0;
		int b = 0;
		
		for (int i =0; i<gpspoints.length-1; i++) {
			a = gpspoints[i].getTime();
			b = gpspoints[i+1].getTime();
			
			totTime = totTime + (b-a);
		}

		
		return totTime;
	}
		

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length-1];
		
		
		for (int i = 0; i<speeds.length; i++) {
			double distance = GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
			int time = gpspoints[i+1].getTime() - gpspoints[i].getTime();
			
			double average = distance/time;
			speeds[i] = average;
		}
		return speeds;
	}
	
	
	
	
	
	
	public double maxSpeed() {
		

		double[] speeds = speeds();
		
		
		double max = 0;
		
		for (double a : speeds) {
			if (max<a) {
				max=a;
			}
		}
		return max;
	
	
	}

	public double averageSpeed() {

		double average = 0;
		
		double totTime = totalTime();
		double totDistance = totalDistance();
		
		average = totDistance / totTime;
		
		return average;
		
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;
		double met;
		double speedmph = speed * MS;

		if (speedmph<10) {
			met = 4.0;
		}
		else if (speedmph>=10 && speedmph<12) {
			met = 6.0;
		}
		else if (speedmph>=12 && speedmph<14) {
			met = 8.0;
		}
		else if (speedmph>=14 && speedmph<16) {
			met = 10.0;
		}
		else if (speedmph>=16 && speedmph<20) {
			met = 12.0;
		}
		else {
			met = 16.0;
	}
		
		
		double t = secs/3600;
		
		kcal = met * weight * t;
		return kcal;
	}

	
	
	
	
	public double totalKcal(double weight) {

		double totalkcal = 0;
		double met = 0;
		
		double totDistance = totalDistance();
		double totTime = totalTime();
		double avrSpeed = averageSpeed();
		
		double speedmph = avrSpeed * MS;

		if (speedmph<10) {
			met = 4.0;
		}
		else if (speedmph>=10 && speedmph<12) {
			met = 6.0;
		}
		else if (speedmph>=12 && speedmph<14) {
			met = 8.0;
		}
		else if (speedmph>=14 && speedmph<16) {
			met = 10.0;
		}
		else if (speedmph>=16 && speedmph<20) {
			met = 12.0;
		}
		else {
			met = 16.0;
	}
		double t = totTime/3600.0;
		
		totalkcal = met * weight * t;
		
		return totalkcal;
	}
	
	
	
	
	
	
	private static double WEIGHT = 80.0;
	private static int TEXTWIDTH = 15;
	private static int VALUEWIDTH = 10;
	
	public void displayStatistics() {
		
		String timeTxt = String.format("%-" + TEXTWIDTH + "s", "Total time");
		String timeValue = String.format("%" + VALUEWIDTH + "s", GPSUtils.formatTime(totalTime()));
		
		String distanceTxt = String.format("%-" + TEXTWIDTH + "s", "Total distance");
		String distanceValue = String.format("%" +VALUEWIDTH + ".2f" , totalDistance()/1000);
		
		String elevationTxt = String.format("%-" + TEXTWIDTH + "s", "Total elevation");
		String elevationValue = String.format("%" +VALUEWIDTH + ".2f" , totalElevation());
		
		String maxSpeedTxt = String.format("%-" + TEXTWIDTH + "s", "Max speed");
		String maxSpeedValue = String.format("%" +VALUEWIDTH + ".2f" , maxSpeed() * 3.6);
		
		String averageSpeedTxt = String.format("%-" + TEXTWIDTH + "s", "Average speed");
		String averageSpeedValue = String.format("%" +VALUEWIDTH + ".2f" , averageSpeed() * 3.6);
		
		String kcalTxt = String.format("%-" + TEXTWIDTH + "s", "Energy");
		String kcalValue = String.format("%" +VALUEWIDTH + ".2f" , totalKcal(WEIGHT));
		
		
		System.out.println("==============================================");
		System.out.println(timeTxt + ":" + timeValue);
		System.out.println(distanceTxt + ":" + distanceValue + " km");
		System.out.println(elevationTxt + ":" + elevationValue + " m");
		System.out.println(maxSpeedTxt + ":" + maxSpeedValue + " km/t");
		System.out.println(averageSpeedTxt + ":" + averageSpeedValue + " km/t");
		System.out.println(kcalTxt + ":" + kcalValue + " kcal");
		System.out.println("==============================================");
		
		
		
		
	}}
		
		

		
		/*System.out.println("==============================================");
		
		String timeTxt = "Total Time";
		int mellomromTxt1 = TEXTWIDTH - timeTxt.length();
		
		String mellomromTxt = "";
		for (int i = 0; i < mellomromTxt1; i++) {
			mellomromTxt += " ";
		}
		
		
		
		
		
		String timeTall = GPSUtils.formatTime(totalTime());
		int mellomromTall1 = VALUEWIDTH - timeTall.length();
		
		String mellomromTall = "";
		for (int i = 0; i < mellomromTall1; i++) {
			mellomromTall += " ";
		}
		
		System.out.println(timeTxt + mellomromTxt + ":" + mellomromTall + timeTall);

		*/
	 


