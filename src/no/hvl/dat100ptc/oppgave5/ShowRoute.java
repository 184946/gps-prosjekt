package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;

	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;

	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));

		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);

		showStatistics();
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);

	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

		// Sett fargen til blå for ruten
		setColor(0, 0, 255);

		// Tegn linjer mellom GPS-punktene
		for (int i = 0; i < gpspoints.length - 1; i++) {
			int x1 = (int) (MARGIN + (gpspoints[i].getLongitude() - minlon) * xstep);
			int y1 = (int) (ybase - (gpspoints[i].getLatitude() - minlat) * ystep);
			int x2 = (int) (MARGIN + (gpspoints[i + 1].getLongitude() - minlon) * xstep);
			int y2 = (int) (ybase - (gpspoints[i + 1].getLatitude() - minlat) * ystep);
			drawLine(x1, y1, x2, y2);
		}
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0, 0, 0); // Sett fargen til svart
		setFont("Courier", 12);

		// TODO
		drawString("Total Time: " + GPSUtils.formatTime(gpscomputer.totalTime()), 10, TEXTDISTANCE);
		drawString("Total Distance: " + String.format("%.2f km", gpscomputer.totalDistance() / 1000), 10,
				TEXTDISTANCE + 20);
		drawString("Total Elevation: " + String.format("%.2f m", gpscomputer.totalElevation()), 10, TEXTDISTANCE + 40);
		drawString("Max Speed: " + String.format("%.2f km/t", gpscomputer.maxSpeed() * 3.6), 10, TEXTDISTANCE + 60);
		drawString("Average Speed: " + String.format("%.2f km/t", gpscomputer.averageSpeed() * 3.6), 10,
				TEXTDISTANCE + 80);
		drawString("Energy: " + String.format("%.2f kcal", gpscomputer.totalKcal(80.0)), 10, TEXTDISTANCE + 100);
	}

	public void replayRoute(int ybase) {
		
		// Konvertering til int for fillCircle
        int startX = (int) (MARGIN + (gpspoints[0].getLongitude() - minlon) * xstep);
        int startY = (int) (ybase - (gpspoints[0].getLatitude() - minlat) * ystep);
        int circle = fillCircle(startX, startY, 5);
		setSpeed(5); // Sett bevegelseshastigheten

		for (int i = 1; i < gpspoints.length; i++) {
			int x = (int) (MARGIN + (gpspoints[i].getLongitude() - minlon) * xstep);
			int y = (int) (ybase - (gpspoints[i].getLatitude() - minlat) * ystep);
			moveCircle(circle, x, y); // Flytt sirkelen til den nye posisjonen
			pause(100); // Pause for å gjøre bevegelsen synlig
		}
	}

}
