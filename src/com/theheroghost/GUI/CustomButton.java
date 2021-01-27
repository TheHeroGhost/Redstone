package com.theheroghost.GUI;

import com.theheroghost.main.main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public abstract class CustomButton implements MouseMotionListener, MouseListener {

    private int x, y, SizeX, SizeY;

    private boolean Hover;
    private boolean Visible = true;
    private boolean Framed = true;
    private boolean Active = true;
    private boolean Background;
    private boolean Pressed;

    private String Text;

    private float HoverTransparent = 0.3f;
    private float BackgroundTransparent = 0.7f;
    private float PressedTransparent = 0.3f;

    private Color BackgroundColor = Color.CYAN;
    private Color TextColor = Color.BLACK;
    private Color FrameColor = Color.BLACK;
    private Color HoverColor = Color.GRAY;

    private Font font = new Font("Tahoma", Font.PLAIN, 12);

    public CustomButton(String Text, int x, int y, int sizeX, int sizeY) {
        this.Text = Text;
        this.x = x;
        this.y = y;
        SizeX = sizeX;
        SizeY = sizeY;
    }

    public CustomButton(String text, int x, int y, int sizeX, int sizeY, boolean visible) {
        this.x = x;
        this.y = y;
        SizeX = sizeX;
        SizeY = sizeY;
        Visible = visible;
        Text = text;
    }

    public CustomButton(String text, int x, int y, int sizeX, int sizeY, boolean visible, boolean framed) {
        this.x = x;
        this.y = y;
        SizeX = sizeX;
        SizeY = sizeY;
        Visible = visible;
        Text = text;
        Framed = framed;
    }

    public void Draw(Graphics g) {
        if(isVisible() && isActive()) {
            //Background
            if(isBackground()) {
                ((Graphics2D) g).setComposite(AlphaComposite.SrcOver.derive(getBackgroundTransparent()));
                g.setColor(getBackgroundColor());
                g.fillRect(getX(), getY(), getSizeX(), getSizeY());
            }
            //Frame
            if (isFramed()) {
                ((Graphics2D) g).setComposite(AlphaComposite.SrcOver.derive(1f));
                g.setColor(getFrameColor());
                g.drawLine(getX(), getY(), getX() + getSizeX(), getY());
                g.drawLine(getX(), getY() + getSizeY(), getX() + getSizeX(), getY() + getSizeY());
                g.drawLine(getX(), getY(), getX(), getY() + getSizeY());
                g.drawLine(getX() + getSizeX(), getY(), getX() + getSizeX(), getY() + getSizeY());
            }

            //Hover
            if(isHover()) {
                ((Graphics2D) g).setComposite(AlphaComposite.SrcOver.derive(getHoverTransparent()));
                if(isPressed()) {
                    ((Graphics2D) g).setComposite(AlphaComposite.SrcOver.derive(getHoverTransparent() + getPressedTransparent()));
                }
                g.setColor(getHoverColor());
                g.fillRect(getX(), getY(), getSizeX(), getSizeY());
            }

            //Text
            ((Graphics2D) g).setComposite(AlphaComposite.SrcOver.derive(1f));
            g.setColor(getTextColor());
            AffineTransform affinetransform = new AffineTransform();
            FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
            int textwidth = (int)(getFont().getStringBounds(getText(), frc).getWidth());
            int textheight = (int)(getFont().getStringBounds(getText(), frc).getHeight());
            g.drawString(getText(), getX() + ((getSizeX() - textwidth) / 2), (getY() + (textheight/2)) + (getSizeY()/2));
        }
    }

    public abstract void onButtonPressEvent();

    @Override
    public void mouseMoved(MouseEvent e) {
        if(isActive()) {
            int MouseX = e.getX() - 8;
            int MouseY = e.getY() - 31;
            if (MouseX > getX() && MouseX < getX() + getSizeX() && MouseY > getY() && MouseY < getY() + getSizeY()) {
                setHover(true);
            } else {
                setHover(false);
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if(isActive()) {
            int MouseX = e.getX() - 8;
            int MouseY = e.getY() - 31;
            if (MouseX > getX() && MouseX < getX() + getSizeX() && MouseY > getY() && MouseY < getY() + getSizeY()) {
                setPressed(true);
                onButtonPressEvent();
            }
        }
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

    public int getSizeX() {
        return SizeX;
    }

    public void setSizeX(int sizeX) {
        SizeX = sizeX;
    }

    public int getSizeY() {
        return SizeY;
    }

    public void setSizeY(int sizeY) {
        SizeY = sizeY;
    }

    public boolean isHover() {
        return Hover;
    }

    public void setHover(boolean hover) {
        Hover = hover;
    }

    public boolean isVisible() {
        return Visible;
    }

    public void setVisible(boolean visible) {
        Visible = visible;
    }

    public boolean isFramed() {
        return Framed;
    }

    public void setFramed(boolean framed) {
        this.Framed = framed;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Color getTextColor() {
        return TextColor;
    }

    public void setTextColor(Color textColor) {
        TextColor = textColor;
    }

    public Color getFrameColor() {
        return FrameColor;
    }

    public void setFrameColor(Color frameColor) {
        FrameColor = frameColor;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public float getHoverTransparent() {
        return HoverTransparent;
    }

    public void setHoverTransparent(float HoverTransparent) {
        this.HoverTransparent = HoverTransparent;
    }

    public Color getHoverColor() {
        return HoverColor;
    }

    public void setHoverColor(Color hoverColo) {
        HoverColor = hoverColo;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public boolean isBackground() {
        return Background;
    }

    public void setBackground(boolean background) {
        Background = background;
    }

    public Color getBackgroundColor() {
        return BackgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        BackgroundColor = backgroundColor;
    }

    public float getBackgroundTransparent() {
        return BackgroundTransparent;
    }

    public void setBackgroundTransparent(float backgroundTransparent) {
        BackgroundTransparent = backgroundTransparent;
    }

    public boolean isPressed() {
        return Pressed;
    }

    public void setPressed(boolean pressed) {
        Pressed = pressed;
    }

    public float getPressedTransparent() {
        return PressedTransparent;
    }

    public void setPressedTransparent(float pressedTransparent) {
        PressedTransparent = pressedTransparent;
    }

    //MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setPressed(false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //MouseMotionListener
    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
