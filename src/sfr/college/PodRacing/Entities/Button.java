package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Handler;

import java.awt.*;


public class Button extends ImageEntity {
    protected final String label;
    protected boolean pressed, flashing;
    protected boolean heavy;
    private Image normal;
    private Image flash;

    public Button(Handler handler, Image[] img, float s, float x, float y, boolean heavy) {
        super(handler, img[0], s, x, y);
        normal = img[0];
        flash = img[1];
        pressed = false;
        label = "";
        this.heavy = heavy;

    }

    public Button(Handler handler, Image[] img, float s, float x, float y, boolean heavy, String lbl) {
        super(handler, img[0], s, x, y);
        normal = img[0];
        flash = img[1];
        pressed = false;
        label = lbl;
        this.heavy = heavy;

    }

    protected boolean isOnButton(int x, int y) {
        return boundsOnScreen.contains(x, y);
    }

    public boolean isMouseOnButton() {
        return isOnButton(handler.getMouseManager().mX, handler.getMouseManager().mY);
    }

    public void flash() {
        flashing = true;
        setImg(flash);
    }

    public void unFlash() {
        flashing = false;
        setImg(normal);
    }

    public void tick() {
        if (!heavy) pressed = false;
        if (isMouseOnButton()) {
            flash();
        } else {
            unFlash();
        }
        if (flashing && handler.getMouseManager().leftPressed) pressed = true;
    }

    public void render(Graphics g) {
        super.render(g);
    }

    public boolean getPressed() {
        return pressed;
    }

    public String getLabel() {
        return label;
    }

    public void setNormal(Image normal) {
        this.normal = normal;
    }

    public void setFlash(Image flash) {
        this.flash = flash;
    }


}
