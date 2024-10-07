package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {
		
		gpspoints = new GPSPoint[n];
		int antall = 0;
		}

	
	
	
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	
	
	
	
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = true;
		
		if (antall<gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			return inserted;
			
		} else {
			return !inserted;
		}
		
		
		
	}

	
	
	public boolean insert(String time, String latitude, String longitude, String elevation) {

		
		
		GPSPoint gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
		boolean nja = insertGPS(gpspoint);

		return nja;
	}

	
	
	public void print() {
		
		System.out.println("====== GPS Data - START ======");
		for (GPSPoint list: gpspoints) {
			
			String a = list.toString();
			System.out.print(a);
		}
		System.out.println("====== GPS Data - SLUTT ======");

		
	}
}
