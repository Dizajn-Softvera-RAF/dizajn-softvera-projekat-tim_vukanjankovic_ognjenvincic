package dsw.view.resolver;

import dsw.view.painters.InterclassPainter;

import java.awt.*;

public class ConnectionPointsResolver {
    private InterclassPainter startObject;
    private InterclassPainter endObject;

    public ConnectionPointsResolver(InterclassPainter startObject, InterclassPainter endObject) {
        this.startObject = startObject;
        this.endObject = endObject;
    }

    public Point calculateStart() {
        return new Point((int) Math.floor(startObject.getDevice().getPosition().getX()), (int) Math.floor(startObject.getDevice().getPosition().getY()));
    }

    public Point calculateEnd() {
        if (endObject.getDevice().getPosition().getX() > (startObject.getDevice().getPosition().getX() - startObject.getDevice().getSize().getWidth() * 2)
                && endObject.getDevice().getPosition().getX() < (startObject.getDevice().getPosition().getX() + startObject.getDevice().getSize().getWidth() * 2)
                && endObject.getDevice().getPosition().getY() > startObject.getDevice().getPosition().getY()) {
            return new Point((int) Math.floor(endObject.getDevice().getPosition().getX()), (int) Math.floor(endObject.getDevice().getPosition().getY() - (endObject.getDevice().getSize().getHeight() / 2)));
        }
        if (endObject.getDevice().getPosition().getX() > (startObject.getDevice().getPosition().getX() - startObject.getDevice().getSize().getWidth() * 2)
                && endObject.getDevice().getPosition().getX() < (startObject.getDevice().getPosition().getX() + startObject.getDevice().getSize().getWidth() * 2)
                && endObject.getDevice().getPosition().getY() < startObject.getDevice().getPosition().getY()) {
            return new Point((int) Math.floor(endObject.getDevice().getPosition().getX()), (int) Math.floor(endObject.getDevice().getPosition().getY() + (endObject.getDevice().getSize().getHeight() / 2)));
        }
        if (endObject.getDevice().getPosition().getX() > startObject.getDevice().getPosition().getX()) {
            return new Point((int) Math.floor(endObject.getDevice().getPosition().getX() - (endObject.getDevice().getSize().getWidth() / 2)), (int) Math.floor(endObject.getDevice().getPosition().getY()));
        } else {
            return new Point((int) Math.floor(endObject.getDevice().getPosition().getX() + (endObject.getDevice().getSize().getWidth() / 2)), (int) Math.floor(endObject.getDevice().getPosition().getY()));
        }
    }
}
