package coding0421;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

//import java.util.Scanner;
public class ShapeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Scanner sc = new Scanner(System.in);
		
		FileOutputStream fo;
		ObjectOutputStream oo;
		FileInputStream fi;
		ObjectInputStream oi;
		
		File file;
		
		Shape[] shapes = 
			{new Rectangle(new Point (1,1), new Point (1,11), new Point (11,1) ,  new Point (11,11))
			, new Circle(new Point(10,10), 4) 
			, new Triangle(new Point (1,1), new Point (1,11), new Point (11,1) ) 
			, new Rectangle(new Point (2,2), new Point (1,11), new Point (11,11) ,  new Point (11,1))
			, new Circle(new Point(10,10), 7) 
			, new Triangle(new Point (3,3), new Point (1,11), new Point (11,1) ) 
			, new Rectangle(new Point (1,1), new Point (1,11), new Point (11,11) ,  new Point (11,1)) //문제발생 
			, new Circle(new Point(15,10), 8) 
			, new Triangle(new Point (1,1), new Point (1,7), new Point (11,11) ) 
			, new Rectangle(new Point (10,10), new Point (10,11), new Point (11,11) ,  new Point (11,10))};	
		Shape tmp;
		try {
			
			file = new File("src/coding0421/files/shape.dat");
			
			if(!file.exists()){
				file.createNewFile();
			}
			
			fo = new FileOutputStream("src/coding0421/files/shape.dat");
			oo = new ObjectOutputStream(fo);
			fi = new FileInputStream("src/coding0421/files/shape.dat");
			oi = new ObjectInputStream(fi);
			
			/*for(int i = 0 ; i < shapes.length ; i++){
				oo.writeObject(shapes[i]);
			}*/
			int i =0 ;
			Shape rec = shapes[i];
			Shape rec2;
			oo.writeObject(rec);
			System.out.println(i+"번 도형");
			//arraylist 로 포인트 저장및 로드
			/*ArrayList<Point> tmpP ;
			tmpP = (ArrayList<Point>)oi.readObject();*/
			rec2 = (Shape)oi.readObject();
			
			
			
			//rec2 = new Rectangle(tmpP.get(0),tmpP.get(1),tmpP.get(2),tmpP.get(3));
			System.out.println(rec);
			System.out.println(rec2);
			//rec2.Initialize(rec2.point);
			//rec.Draw();
			//rec2.Draw();
			/*for(int i = 0 ; i< shapes.length ; i++){
				
				System.out.println(i+"번 도형");
				tmp = (Shape)oi.readObject();
				tmp.Draw();
				System.out.println();
				
				//sc.next();
			}*/
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
		
		
		
		
	}
}
