package sfr.college.PodRacing.States;

import sfr.college.PodRacing.Game;
import sfr.college.PodRacing.Handler;

import java.awt.*;

public class PreGameState extends State{
    public PreGameState(Handler handler) {
        super(handler);
    }

    @Override
    public void render(Graphics g) {
        g.fillOval(Game.WIN_SIZE_QUARTER,Game.WIN_SIZE_QUARTER,Game.WIN_SIZE_HALF,Game.WIN_SIZE_HALF);
    }

    @Override
    public void tick() {
        super.tick();
    }
}
