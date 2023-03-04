package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
   hello("world");
   hello("user");
   hello("Alexei");

   Square s = new Square(5);
   System.out.println("Площадь квадрата со сторой " + s.l + " = " + s.area());

   Rectangle r = new Rectangle(4, 6);
   System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

   Point p1 = new Point(2, 4);
   Point p2 = new Point(4, 4);
   System.out.println("Расстояние между точками p1 и p2 по функции = " + distance(p1, p2));
   System.out.println("Пример 1. Расстояние между точками p1 и p2 по методу = " + p2.distance(p1));

   Point p3 = new Point(4, 4);
   Point p4 = new Point(4, 8);
   System.out.println("Пример 2. Расстояние между точками p3 и p4 по методу = " + p4.distance(p3));

   Point p5 = new Point(8, 3);
   Point p6 = new Point(8, 3);
   System.out.println("Пример 3. Расстояние между точками p5 и p6 по методу = " + p5.distance(p6));


  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow((p2.x - p1.x), 2) + (Math.pow((p2.y - p1.y), 2)));
  }

}