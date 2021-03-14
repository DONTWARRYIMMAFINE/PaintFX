package org.paintFX.ShapeFactory;

import javafx.scene.paint.Paint;
import org.paintFX.PaintMode;
import org.paintFX.Shapes.Rectangle;
import org.paintFX.Shapes.Shape;

public class RectangleFactory implements ShapeFactory {
    @Override
    public Shape createShape(double[] points, double borderSize, Paint fillColor, Paint borderColor, PaintMode paintMode) {
        return new Rectangle(points, borderSize, fillColor, borderColor, paintMode);
    }
}
