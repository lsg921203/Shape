package coding0421;

import java.io.Serializable;
import java.util.ArrayList;

class Point implements Serializable{
	private int x;
	private int y;
	public Point(){
		
	}
	public Point(int x,int y){
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Point [" + x + ", " + y + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}

class PointDouble implements Serializable{
	private double x;
	private double y;
	
	public PointDouble() {
		
		// TODO Auto-generated constructor stub
	}
	public PointDouble(double x, double y) {
		
		this.x = x;
		this.y = y;
	}
	public PointDouble(Point p){
		x= (double)p.getX();
		y= (double)p.getY();
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointDouble other = (PointDouble) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
}


//고양이야 왤케 귀엽니
public class Shape {
	//protected Point[] point ;
	protected ArrayList<Point> point;
	protected String name;
	private boolean check;
	
	protected boolean IsInShape(Point p){
		
		for (int i = 0 ; i < point.size() ; i++)//for (int i = 0 ; i < point.length ; i++)
		{
			if(point.get(i).equals(p)){//point[i]
				return true;
			}
			
		}
		return false;
	}
	public void Draw(){
		System.out.println(name + " draw");
		for(int x = 0; x <20; x++)
		{
			for(int y = 0; y<20; y++)
			{
				check = IsInShape(new Point(x,y));
				
				if(check){
					System.out.print("o ");
				}
				else{
					System.out.print("_ ");
				}
				
				
			}
			System.out.println();
		}
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shape other = (Shape) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}
	
	

}

//circle, triangle, rectangle

class Circle extends Shape implements Serializable{
	private int radius;
	
	public Circle(Point center, int rad){
		name = "Circle";
		point = new ArrayList<Point>();
		point.add(center);//point = new Point[1];
						  //point[0]=center;
		radius = rad;
	}
	private double DistanceSquare(Point a, Point b){
		
		double X = (double)(a.getX() - b.getX());
		double Y = (double)(a.getY() - b.getY());
		X=X*X;
		Y=Y*Y;
		return X+Y;
	}
	@Override
	protected boolean IsInShape(Point p){
		
		if(DistanceSquare(p,point.get(0)) < (double)(radius*radius)){//if(DistanceSquare(p,point[0]) < (double)(radius*radius))
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
}

class line implements Serializable{
	private double a;
	private double b;
	private double c;// ax + by + c = 0 
	public line(PointDouble p1,PointDouble p2){
		Getline(p1,p2);
	}
	public void Getline(PointDouble p1,PointDouble p2 ){
		a = p1.getY() - p2.getY();
		b = p2.getX() - p1.getX();
		c = -(a*p1.getX() + b*p1.getY());
	}
	
	public double func(int x , int y){
		return a*x + b*y + c;
	}
	public double func(Point p){
		return a*p.getX() + b*p.getY() + c;
	}
	public double func(PointDouble p){
		return a*p.getX() + b*p.getY() + c;
	}
	public boolean sideCheck(int x, int y){
		
		if(func(x,y) > 0){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean sideCheck(Point p){
		
		if(func(p) > 0){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean sideCheck(PointDouble p){
	
	if(func(p) > 0){
		return true;
	}
	else{
		return false;
	}
}
}




class Polygon extends Shape {//n각형
	private PointDouble center;
	private ArrayList<line> l;//line[]
	private ArrayList<Boolean> check;//boolean[] check
	private ArrayList<Double> angle;//double[] angle///////////////////////////////////////////////////////////////////
	
	
	public Polygon(){}
	public Polygon(ArrayList<Point> p){//Point[] p
		Initialize(p);
	}
	
	public void Initialize(ArrayList<Point> p){//Point[] p
		int length = p.size();
		point = new ArrayList<Point>();						//point = new Point[length];
		l = new ArrayList<line>();					//l = new line[length];
		check = new ArrayList<Boolean>();			//check = new boolean[length];
		angle = new ArrayList<Double>();			//angle = new double[length];
		double sumX=0;
		double sumY=0;
		
		for(int i = 0 ; i< length; i ++){
			point.add(p.get(i));			//point[i] = p[i];
			
			sumX += (double)point.get(i).getX();//(double)point[i].getX();
			sumY += (double)point.get(i).getY();//point[i].getY();
		}
		sumX/=(double)length;
		sumY/=(double)length;
		
		
		center = new PointDouble(sumX,sumY);
		
		for(int i = 0 ; i< length; i ++){
			angle.add(Math.atan2(p.get(i).getY() - center.getY(), p.get(i).getX()-center.getX())) ;//angle[i] = Math.atan2(p[i].getY() - center.getY(), p[i].getX()-center.getX());
		}
		
		sortByAngle();
		for(int i = 0 ; i< length; i ++){
			int j = i+1;
			if(j==length){
				j=0;
			}
			l.add(new line(new PointDouble(point.get(i)),new PointDouble(point.get(j))));//l[i] = new line(new PointDouble(point[i]),new PointDouble(point[j]));
			check.add(l.get(i).sideCheck(center));//check[i] = l[i].sideCheck(center);
			// all true 로 바꿔야지
		}
	}
	protected void sortByAngle(){
		Point tmpP;
		double tmpD;
		
		for(int i = 0 ; i< point.size(); i++)// point.length
		{
			for(int j = 0 ; j< point.size() - i - 1 ; j ++){// point.length   //문제 발생 ㅅㅂ 해결한듯: angle.get(j)->angle.get(j+1)
				if(angle.get(j) < angle.get(j+1))//angle[j] < angle[j+1]
				{
					tmpD = angle.get(j);//angle[j]
					tmpP = point.get(j);//point[j]
					
					angle.set(j, angle.get(j+1));//angle[j] = angle[j+1];
					point.set(j, point.get(j+1));//point[j] = point[j+1];
					
					angle.set(j+1, tmpD);//angle[j+1] = tmpD;
					point.set(j+1, tmpP);//point[j+1] = tmpP;
				}
			}
		}
		
	}
	
	@Override
	protected boolean IsInShape(Point p){
		
		
		for(int i =0 ; i< check.size(); i++){//check.length
			if(check.get(i).booleanValue() != l.get(i).sideCheck(p)){//check[i] != l[i].sideCheck(p)
				return false;
			}
		}
		
		return true;
	}
}



class Rectangle extends Polygon implements Serializable{
	
	
	public Rectangle(Point a,Point b,Point c,Point d){//시계방향으로 입력
		ArrayList<Point> tmpPoint;
		tmpPoint = new ArrayList<Point>();		//point = new Point[4];
		tmpPoint.add(a);	//point[0] = a;
		tmpPoint.add(b);	//point[1] = b;
		tmpPoint.add(c);	//point[2] = c;
		tmpPoint.add(d);	//point[3] = d;
		name = "Rectangle";
		Initialize(tmpPoint);
		
	}

	@Override
	public String toString() {
		return "Rectangle [point=" + point + ", name=" + name + "]";
	}
	
}



class Triangle extends Polygon implements Serializable{
	public Triangle(Point a,Point b,Point c){//시계방향으로 입력
		
		ArrayList<Point> tmpPoint; 
		tmpPoint = new ArrayList<Point>();	//point = new Point[3];
		tmpPoint.add(a);	//point[0] = a;
		tmpPoint.add(b);	//point[1] = b;
		tmpPoint.add(c);	//point[2] = c;
		
		name = "Triangle";
		Initialize(tmpPoint);
		
	}
}



