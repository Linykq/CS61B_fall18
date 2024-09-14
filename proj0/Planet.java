public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	};

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
	}

	public double calcForceExertedBy(Planet p){
		return Planet.G * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
	}

	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - this.xxPos;
		return dx * this.calcForceExertedBy(p) / this.calcDistance(p);
	}

	public double calcForceExertedByY(Planet p){
		double dy = p.yyPos - this.yyPos;
		return dy * this.calcForceExertedBy(p) / this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] Planets){
		double sumForceX = 0;
		for(Planet p : Planets){
			if(this.equals(p)){
				continue;
			}
			sumForceX += calcForceExertedByX(p);
		}
		return sumForceX;
	}

	public double calcNetForceExertedByY(Planet[] Planets){
		double sumForceY = 0;
		for(Planet p : Planets){
			if(this.equals(p)){
				continue;
			}
			sumForceY += calcForceExertedByY(p);
		}
		return sumForceY;
	}

	public void update(double dt,double fx,double fy){
		double ax = fx / mass;
		double ay = fy / mass;
		xxVel += ax * dt;
		yyVel += ay * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}

}
