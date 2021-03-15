package org.paintFX.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import org.paintFX.PaintMode;

public class Polygon implements Shape {

    private final double borderSize;
    private final Paint fillColor;
    private final Paint borderColor;
    private final PaintMode paintMode;

    private final double[] points;

    public Polygon(double[] points, double borderSize, Paint fillColor, Paint borderColor, PaintMode paintMode) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.paintMode = paintMode;
        this.borderSize = borderSize;

        this.points = points;
    }

    @Override
    public void draw(GraphicsContext g) {

        g.setLineWidth(borderSize);
        g.setStroke(borderColor);
        g.setFill(fillColor);

        double[] pointsX = new double[points.length / 2];
        double[] pointsY = new double[points.length / 2];

        for (int i = 0; i < points.length; i++) {
            if (i % 2 == 0) {
                pointsX[i / 2] = points[i];
            } else {
                pointsY[i / 2] = points[i];
            }
        }

        switch (paintMode) {
            case FILLED:
                g.fillPolygon(pointsX, pointsY, points.length / 2);
                break;
            case BORDERED:
                g.strokePolygon(pointsX, pointsY, points.length / 2);
                break;
            case FILLED_WITH_BORDER:
                g.fillPolygon(pointsX, pointsY, points.length / 2);
                g.strokePolygon(pointsX, pointsY, points.length / 2);
                break;
            default:
                System.out.println("Unknown paint mode");
                break;
        }

    }
}