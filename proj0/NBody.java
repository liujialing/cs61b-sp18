public class NBody {

	public static double readRadius(String filename) {
		In in = new In(filename);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[num];
		int i = 0;

		while(!in.isEmpty() && i < num) {
			planets[i] = new Planet(in.readDouble(), 
				in.readDouble(), 
				in.readDouble(), 
				in.readDouble(), 
				in.readDouble(), 
				in.readString()
			);
			i++;
		}
		return planets;
	}

	public static void main(String[] args) {
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];
		String backgroundFilePath = "images/starfield.jpg";
		double times = 0;

		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		StdDraw.setScale(-radius, radius);

		drawUniverse(backgroundFilePath, planets);

		StdDraw.enableDoubleBuffering();
		while(times <= T) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);

				planets[i].update(dt, xForces[i], yForces[i]);
			}
			drawUniverse(backgroundFilePath, planets);
			times += dt;
		}

		printPlanetDetail(radius, planets);

	}

	private static void printPlanetDetail(double radius, Planet[] planets) {
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
					planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}

	private static void drawUniverse(String backgroundFilePath, Planet[] planets) {
		StdDraw.clear();
		StdDraw.picture(0, 0, backgroundFilePath);

		for(Planet planet : planets) {
			planet.draw();
		}
		StdDraw.show();
		StdDraw.pause(10);
	}
}