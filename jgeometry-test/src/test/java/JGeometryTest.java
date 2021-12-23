import org.junit.jupiter.api.Test;
import ru.vsu.cs.JGeometry;
import ru.vsu.cs.shape.Point;
import ru.vsu.cs.shape.Polygon;
import ru.vsu.cs.shape.Segment;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JGeometryTest {
    //Квадрат 2x2
    private final Point p1 = new Point(0,0);
    private final Point p2 = new Point(2,0);
    private final Point p3 = new Point(2,2);
    private final Point p4 = new Point(0,2);
    //При лобавлении после p2 получается трапеция
    private final Point p5 = new Point(4,0);
    @Test
    public void testArea(){
        Polygon polygon = new Polygon(List.of(p1,p2,p3,p4));
        assertEquals(polygon.area(),4.0);
        Polygon polygon3 = new Polygon(List.of(p1,p2,p5,p3,p4));
        assertEquals(polygon3.area(),6.0);
    }
    @Test
    public void testArea2(){
        //Трапеция
        Point p1 = new Point(0,0);
        Point p2 = new Point(4,0);
        Point p3 = new Point(3,2);
        Point p4 = new Point(1,2);
        Polygon polygon = new Polygon(List.of(p1,p2,p3,p4));
        assertEquals(polygon.area(),6.0);
    }
    @Test
    public void testPerimeter(){
        Polygon polygon = new Polygon(List.of(p1,p2,p3,p4));
        assertEquals(polygon.perimeter(),8.0);
        Polygon polygon2 = new Polygon(List.of(p2,p4,p1,p3));
        assertEquals(polygon2.perimeter(),9.65685425,0.00001);
    }

    @Test
    public void testContains(){
        Polygon polygon = new Polygon(List.of(p1,p2,p3,p4));
        assertTrue(polygon.contains(1,1));
        assertFalse(polygon.contains(3,3));
    }
    @Test
    public void testIntersectionOfTwoLineSegments(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,2);
        Point p3 = new Point(-1,1);
        Point p4 = new Point(1,1);
        Segment s1 = new Segment(p1,p2);
        Segment s2 = new Segment(p3,p4);
        assertEquals(JGeometry.getTheIntersectionPoint(s1,s2),new Point(0,1));
    }


    @Test
    public void testUnionAndAriaAndPerimeter(){
        Polygon polygon = new Polygon(List.of(p1,p2,p3,p4));
        Point np1 = new Point(1,1);
        Point np2 = new Point(1,-2);
        Point np3 = new Point(4,-2);
        Point np4 = new Point(4,1);
        List<Point> newPoints = List.of(np1,np2,np3,np4);
        Polygon second = new Polygon(newPoints);
        Polygon result = JGeometry.union(polygon, second);
        List<Point> points = result.getListOfPoints();
        assertEquals(8, points.size());
        assertEquals(result.perimeter(),16);
        assertEquals(result.area(),12);
    }

    @Test
    public void testIntersectionAndAriaAndPerimeter(){
        Polygon polygon = new Polygon(List.of(p1,p2,p3,p4));
        Point np1 = new Point(1,1);
        Point np2 = new Point(1,-2);
        Point np3 = new Point(4,-2);
        Point np4 = new Point(4,1);
        List<Point> newPoints = List.of(np1,np2,np3,np4);
        Polygon second = new Polygon(newPoints);
        Polygon result = JGeometry.intersection(polygon, second);
        System.out.println(result.getListOfPoints());
        assertEquals(4, result.getListOfPoints().size());
        assertEquals(result.perimeter(),4);
        System.out.println(result.getListOfPoints());
        assertEquals(result.area(),1);
    }
    @Test
    public void testSubtractionAndAriaAndPerimeter(){
        Polygon polygon = new Polygon(List.of(p1,p2,p3,p4));
        Point np1 = new Point(1,1);
        Point np2 = new Point(1,-2);
        Point np3 = new Point(4,-2);
        Point np4 = new Point(4,1);
        List<Point> newPoints = List.of(np1,np2,np3,np4);
        Polygon second = new Polygon(newPoints);
        Polygon result = JGeometry.subtraction(second, polygon);
        System.out.println(result);
        assertEquals(6, result.getListOfPoints().size());
        assertEquals(result.perimeter(),12);
        assertEquals(result.area(),8);
    }

}
