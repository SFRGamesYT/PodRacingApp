package sfr.college.PodRacing.Entities;

import java.awt.*;

public class MapCollisionHulls extends Entity {
    //store data for each hit box
    private final HitBox[] hitBoxes =

            {
                    new HitBox(8, 5, 246, 6),
                    new HitBox(26, 21, 228, 22),
                    new HitBox(48, 31, 204, 32),
                    new HitBox(72, 59, 183, 60),
                    new HitBox(72, 97, 163, 98),
                    new HitBox(205, 99, 228, 100),
                    new HitBox(48, 120, 141, 121),
                    new HitBox(184, 120, 246, 121),
                    new HitBox(49, 144, 118, 145),
                    new HitBox(164, 151, 245, 152),
                    new HitBox(48, 31, 204, 32),
                    new HitBox(72, 59, 183, 60),
                    new HitBox(72, 97, 163, 98),
                    new HitBox(205, 99, 228, 100),
                    new HitBox(48, 120, 141, 121),
                    new HitBox(184, 120, 246, 121),
                    new HitBox(70, 168, 92, 169),
                    new HitBox(142, 171, 224, 172),
                    new HitBox(119, 186, 224, 187),
                    new HitBox(93, 218, 245, 219),
                    new HitBox(26, 230, 48, 231),
                    new HitBox(8, 249, 69, 250),
                    new HitBox(6, 7, 7, 248),
                    new HitBox(26, 23, 27, 229),
                    new HitBox(46, 33, 47, 119),
                    new HitBox(47, 146, 48, 229),
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
