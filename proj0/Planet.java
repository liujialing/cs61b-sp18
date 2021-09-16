public class Planet {
	private static double G = 6.67 * Math.pow(10, -11);
	private static String IMAGE_PATH = "images/";

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV,
		double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet planet) {
		this.xxPos = planet.xxPos;
		this.yyPos = planet.yyPos;
		this.xxVel = planet.xxVel;
		this.yyVel = planet.yyVel;
		this.mass = planet.mass;
		this.imgFileName = planet.imgFileName;
	}

	public double calcDistance(Planet planet) {
		return Math.sqrt(Math.pow(this.xxPos - planet.xxPos,2) + Math.pow(this.yyPos - planet.yyPos,2));
	}

	public double calcForceExertedBy(Planet planet) {
		return G * this.mass * planet.mass / Math.pow(calcDistance(planet), 2);
	}

	public double calcForceExertedByX(Planet planet) {
		return calcForceExertedBy(planet) * (planet.xxPos - this.xxPos) / calcDistance(planet);
	}

	public double calcForceExertedByY(Planet planet) {
		return calcForceExertedBy(planet) * (planet.yyPos - this.yyPos) / calcDistance(planet);
	}

	public double calcNetForceExertedByX(Planet[] planets) {
		double forceX = 0d;
		for (Planet planet : planets) {
			if (planet != this) {
				forceX += calcForceExertedByX(planet);
			}
		}
		return forceX;
	}

	public double calcNetForceExertedByY(Planet[] planets) {
		double forceY = 0d;
		for (Planet planet : planets) {
			if (planet != this) {
				forceY += calcForceExertedByY(planet);
			}	
		}
		return forceY;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;

		this.xxVel += dt * aX;
		this.yyVel += dt * aY;

		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, IMAGE_PATH + imgFileName);
	}
}