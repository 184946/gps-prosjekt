package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

		GPSPoint one = new GPSPoint(2, 3.9, 3.5, 6.0);
		GPSPoint two = new GPSPoint(3, 5.6, 23, 54);
		
		GPSData gpsData = new GPSData(2);
		
		gpsData.insert("2017-08-13T02:52:56.000Z", "3.6", "10.0", "6.4");
		gpsData.insert("2017-08-13T10:52:36.000Z", "5.0", "2.0", "3.0");
		
		gpsData.print();
		
		
		
	}
}
