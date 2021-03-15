package org.paintFX;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.paintFX.ShapeFactory.*;

public class MainSceneController {

    private Model model;

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker cpFill;

    @FXML
    private ColorPicker cpBorder;

    @FXML
    private TextField brushSize;


    //Labels
    @FXML
    private Label lblTool;

    @FXML
    private Label mouseCoordinates;


    //Tools
    @FXML
    private Button btnUndo;

    @FXML
    private Button btnRedo;

    @FXML
    private Button btnPen;

    @FXML
    private Button btnEraser;

    @FXML
    private Button btnCircle;

    @FXML
    private Button btnEllipse;

    @FXML
    private Button btnLine;

    @FXML
    private Button btnPolygon;

    @FXML
    private Button btnRectangle;


    public void initialize() {
        //Init model
        model = new Model(canvas);

        //Icons for tools
        btnUndo.setGraphic(new ImageView(Loader.loadImage("icons/undo.png")));
        btnRedo.setGraphic(new ImageView(Loader.loadImage("icons/redo.png")));

        btnPen.setGraphic(new ImageView(Loader.loadImage("icons/pen.png")));
        btnEraser.setGraphic(new ImageView(Loader.loadImage("icons/eraser.png")));
        btnCircle.setGraphic(new ImageView(Loader.loadImage("icons/circle.png")));
        btnEllipse.setGraphic(new ImageView(Loader.loadImage("icons/ellipse.png")));
        btnLine.setGraphic(new ImageView(Loader.loadImage("icons/line.png")));
        btnPolygon.setGraphic(new ImageView(Loader.loadImage("icons/polygon.png")));
        btnRectangle.setGraphic(new ImageView(Loader.loadImage("icons/rectangle.png")));

        setFillColor();
        setBorderColor();
        setBorderSize();

        model.setPaintMode(PaintMode.FILLED);
        canvas.setOnMouseMoved(e -> showMouseCoordinates(e));
    }

    public void setFillColor() {
        model.setFillColor(cpFill.getValue());
    }

    public void setBorderColor() {
        model.setBorderColor(cpBorder.getValue());
    }

    public void setBorderSize() {
        model.setBorderSize(Double.parseDouble(brushSize.getText()));
    }

    public void showMouseCoordinates(MouseEvent e) {
        mouseCoordinates.setText(model.showMouseCoordinates(e.getX(), e.getY()));
    }

    public void setPenTool() {
        lblTool.setText("Tool : 'Pen' ");

        model.resetMouseEvents();
        model.setPenTool();
    }

    public void setEraserTool() {
        lblTool.setText("Tool : 'Eraser' ");

        model.resetMouseEvents();
        model.setEraserTool();
    }

    public void setShapeFactoryToRectangle() {
        lblTool.setText("Tool : 'Rectangle' ");

        model.resetMouseEvents();
        model.bindMouseForDrawingRegularShapes();
        model.setShapeFactory(new RectangleFactory());
    }

    public void setShapeFactoryToCircle() {
        lblTool.setText("Tool : 'Circle' ");

        model.resetMouseEvents();
        model.bindMouseForDrawingRegularShapes();
        model.setShapeFactory(new CircleFactory());
    }

    public void setShapeFactoryToEllipse() {
        lblTool.setText("Tool : 'Ellipse' ");

        model.resetMouseEvents();
        model.bindMouseForDrawingRegularShapes();
        model.setShapeFactory(new EllipseFactory());
    }

    public void setShapeFactoryToLine() {
        lblTool.setText("Tool : 'Line' ");

        model.resetMouseEvents();
        model.bindMouseForDrawingRegularShapes();
        model.setShapeFactory(new LineFactory());
    }

    public void setShapeFactoryToPolygon() {
        lblTool.setText("Tool : 'Polygon' ");

        model.resetMouseEvents();
        model.bindMouseDrawingDifficultShape();
        model.setShapeFactory(new PolygonFactory());
    }

    public void onSwap() {
        Color temp = cpFill.getValue();
        cpFill.setValue(cpBorder.getValue());
        cpBorder.setValue(temp);

        setFillColor();
        setBorderColor();
    }

    public void onUndo() {
        model.onUndo();
    }

    public void onRedo() {
        model.onRedo();
    }

    public void clearCanvas() {
        model.clearCanvas();
        model.removeComponents();
    }

    public void setPaintModeToFilled() {
        model.setPaintMode(PaintMode.FILLED);
    }

    public void setPaintModeToBordered() { model.setPaintMode(PaintMode.BORDERED); }

    public void setPaintModeToFilledWithBorder() {
        model.setPaintMode(PaintMode.FILLED_WITH_BORDER);
    }

    public void onSave() {
        model.onSave(canvas);
    }

    public void onExit() {
        model.onExit();
    }

}