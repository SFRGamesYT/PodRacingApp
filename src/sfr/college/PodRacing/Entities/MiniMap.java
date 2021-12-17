/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.Entities;

import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;

import java.awt.*;

import static sfr.college.PodRacing.Game.WIN_SIZE;

/**
 * @author Sami
 */
public class MiniMap extends ImageEntity {
    private int pX, pY;

    public MiniMap(Handler handler) {
        super(handler, Assets.minimap, 0.2f, 0.8f, 0.8f, 0, 0);
    }

    @Override
    public void tick() {
        super.tick();
        pX = super.x2 + (int) (handler.getGameState().getMapX() * size) - Game.scaleToWindow(0.0035f);
        pY = super.y2 + (int) (handler.getGameState().getMapY() * size) - Game.scaleToWindow(0.0035f);
    }

    public void render(Graphics g) {
        super.render(g);
        if (handler.getGameState().getMapX() < WIN_SIZE && handler.getGameState().getMapX() > 0) {
            if (handler.getGameState().getMapY() < WIN_SIZE && handler.getGameState().getMapY() > 0) {
                drawPos(g);
            }
        }

    }

    public void drawPos(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(pX, pY, Game.scaleToWindow(0.007f), Game.scaleToWindow(0.007f));
    }

}
