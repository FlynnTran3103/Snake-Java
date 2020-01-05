package astrobleme.logic;

import astrobleme.gui.Painter;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop implements Runnable{
    private final Grid grid;
    private final GraphicsContext context;
    private int frameRate;
    private float interval;
    private boolean running;
    private boolean paused;
    private boolean keyIsPressed;
    public GameLoop(final Grid grid, final GraphicsContext context){
        this.grid=grid;
        this.context=context;
        frameRate = 20;
        interval = 1000 / frameRate;
        running = true;
        paused = false;
        keyIsPressed = false;
    }
    @Override
    public void run(){
        while (running && paused){
            // Time the update and paint calls
            float time = System.currentTimeMillis();
            
            keyIsPressed = false;
            grid.update();
            Painter.paint(grid,context);
            
            if(!grid.getSnake()){
                pause();
                Painter.paintResetMessage(context);
                break;
            }
            time = System.currentTimeMillis() - time;
            //Adjust the timing correctly
            if(time<interval){
                try{
                    Thread.sleep((long)(interval-time));
                }catch (InterruptedException ignore){}
            }
        }
    }
    public void stop(){
        running = false;
    }
    public boolean isKeyIsPressed(){
        return keyIsPressed;
    }

    private void pause() {
         paused = true;
    }
}