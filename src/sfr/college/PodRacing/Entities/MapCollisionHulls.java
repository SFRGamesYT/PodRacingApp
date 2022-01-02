package sfr.college.PodRacing.Entities;

import java.awt.*;

public class MapCollisionHulls extends Entity {
    //store data for each hit box
    private final HitBox[] hitBoxes =

            {
                    //horizomtal hitboxes
                    new HitBox(4, 7, 246, 8),
                    new HitBox(26, 21, 228, 22),
                    new HitBox(48, 35, 204, 36),
                    new HitBox(72, 58, 183, 59),
                    new HitBox(72, 99, 163, 100),
                    new HitBox(205, 101, 228, 102),
                    new HitBox(48, 119, 141, 120),
                    new HitBox(184, 119, 246, 120),
                    new HitBox(49, 146, 118, 147),
                    new HitBox(164, 153, 245, 154),
                    new HitBox(70, 167, 92, 168),
                    new HitBox(142, 171, 224, 172),
                    new HitBox(119, 188, 224, 189),
                    new HitBox(93, 217, 245, 218),
                    new HitBox(26, 232, 48, 233),
                    new HitBox(8, 248, 69, 249),
                    //vertical hitboxes
                    new HitBox(7, 3, 8, 248),
                    new HitBox(26, 21, 27, 229),
                    new HitBox(50, 33, 51, 119),
                    new HitBox(50, 146, 51, 229),
                    new HitBox(72, 61, 73, 96),
                    new HitBox(70, 170, 71, 248),
                    new HitBox(91, 170, 92, 217),
                    new HitBox(119, 146, 120, 185),
                    new HitBox(140, 122, 141, 170),
                    new HitBox(164, 99, 165, 150),
                    new HitBox(182, 61, 183, 119),
                    new HitBox(205, 33, 206, 98),
                    new HitBox(227, 23, 228, 98),
                    new HitBox(247, 7, 248, 119),
                    new HitBox(223, 173, 224, 185),
                    new HitBox(246, 153, 247, 217)
            };



    @Override
    public void render(Graphics g) {
        for(HitBox i:hitBoxes){
            i.draw((Graphics2D) g);
        }
    }

    @Override
    public void tick() {

    }

    public HitBox[] getHitBoxes() {
        return hitBoxes;
    }
}
