/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfr.college.PodRacing.States;


import sfr.college.PodRacing.Assets;
import sfr.college.PodRacing.Entities.Button;
import sfr.college.PodRacing.Entities.ChoosePodButton;
import sfr.college.PodRacing.Entities.GameText;
import sfr.college.PodRacing.Entities.TitleBackground;
import sfr.college.PodRacing.Handler;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author SR35477
 */
public class ChooseState extends StateExitable {

    private final TitleBackground background;
    private final GameText chooseVehicleText;
    private final ChoosePodButton pod0;
    private final ChoosePodButton pod1;
    private final ChoosePodButton pod2;
    private ChoosePodButton pod3;
    private final Button playButton;
    private final ArrayList<ChoosePodButton> podButtons;
    private String podSelected;
    private final GameText text;
    private final int now;
    private boolean play;

    public ChooseState(Handler handler) {
        super(handler);
        Assets.init(handler);
        forward = false;
        play = false;
        background = new TitleBackground(handler, false);
        chooseVehicleText = new GameText(handler, "CHOOSE VEHICLE", 0.05f, 0.1f);


        pod0 = new ChoosePodButton(handler, Assets.pod0button, 0.20f, 0.2f, 0.4f, "Anakin's Podracer");
        pod1 = new ChoosePodButton(handler, Assets.pod1button, 0.20f, 0.4f, 0.4f, "Sebulba's Podracer");
        pod2 = new ChoosePodButton(handler, Assets.pod2button, 0.20f, 0.6f, 0.4f, "Gasgano's Ord Pedrovia");


        playButton = new Button(handler, Assets.playbutton, 0.2f, 0.85f, 0.4f, false);
        podSelected = "";
        podButtons = new ArrayList<>();


        podButtons.add(pod0);
        podButtons.add(pod1);
        podButtons.add(pod2);

        text = new GameText(handler, "", 0.025f, 0.82f);
        now = handler.getTime();

    }

    @Override
    public void render(Graphics g) {
        background.render(g);
        chooseVehicleText.render(g);
        super.render(g);

        pod0.render(g);
        pod1.render(g);
        pod2.render(g);

        text.render(g);
        if (!"".equals(podSelected)) {
            playButton.render(g);
        }


    }

    @Override
    public void tick() {

        text.setText(podSelected);
        background.tick();
        background.setAlpha(150);
        super.tick();

        int x = 0;
        if (handler.getTime() - now >= 10) {

            pod0.tick();
            pod1.tick();
            pod2.tick();

            while (podButtons.size() > x) {
                if (!"".equals(podButtons.get(x).getSelected())) {
                    podSelected = podButtons.get(x).getSelected();
                }
                if (podButtons.get(x).getLabel().equals(podSelected)) {
                    podButtons.get(x).flash();
                } else {
                    podButtons.get(x).unFlash();
                }

                x++;
            }
            x = 0;
            int count = 0;
            while (podButtons.size() > x) {
                if (handler.getMouseManager().leftPressed) {
                    if (!podButtons.get(x).isMouseOnButton() && !playButton.isMouseOnButton()) {
                        count++;
                    } else {
                        break;
                    }
                }
                x++;
            }
            if (count >= podButtons.size()) podSelected = "";

        }
        if (!"".equals(podSelected)) {
            playButton.tick();
        }
        if (playButton.getPressed()) {
            play = true;
            forward = true;
        }

    }

    public boolean isPlay() {
        return play;
    }

    public String getPodSelected() {
        return podSelected;
    }


}
