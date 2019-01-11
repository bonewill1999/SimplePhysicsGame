package com.example.johnnybahama.physicsgame;


import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Parameter;

/**
 * Created by rushd on 7/5/2017.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    public PlayableObject mainCharacter;
    private final Context dummyContext = this.getContext();
    private int testX = 250;





    public GameView(Context context) {
        super(context);


        getHolder().addCallback(this);


        thread = new MainThread(getHolder(), this);
        this.setBackgroundResource(R.drawable.andriod);




        setFocusable(true);


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        return super.onTouchEvent(event);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Bitmap placeholder = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
        //  mainCharacter = new PlayableObject(BitmapFactory.decodeResource(getResources(),R.drawable.test), 3, 3, 3 );
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(placeholder, 200, 200, false);





        mainCharacter = new PlayableObject(resizedBitmap, 250, 250, 1);



        //  System.out.print("REEEEEEEEEEEEEEEEEEEE");








        thread.setRunning(true);
        thread.start();
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


//                Toast notLongError = Toast.makeText(dummyContext, String.valueOf(motionEvent.getX()), Toast.LENGTH_SHORT);
//                notLongError.show();
               // mainCharacter.checkDragged((int)Math.round(motionEvent.getX()),(int)Math.round(motionEvent.getY()));
                mainCharacter.move(12,12,12);
                testX = testX + 40;

              //  System.out.print(String.valueOf(mainCharacter.getX()));
                return false;


            }
        });

        this.setOnDragListener(new OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {

                //System.out.print("BALL CLICKED");


                if(mainCharacter.isBeingDragged()){
                 //   mainCharacter.setX((int)Math.round(dragEvent.getX()));
                 //   mainCharacter.setY((int)Math.round(dragEvent.getY()));


                }
                return false;
            }
        });

        this.setOnHoverListener(new OnHoverListener() {
            @Override
            public boolean onHover(View view, MotionEvent motionEvent) {
                System.out.print("BALL CLICKED");
                return false;
            }
        });

    }



    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        mainCharacter.update();


    }



    @Override
    public void draw(Canvas canvas)
    {
      //  mainCharacter.move(mainCharacter.getX()+20, mainCharacter.getY()+20, 1);
    //    System.out.print("REEEEEEEEEEEEEEEEEEEE");
   //     canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.test), 50, 50, null);



        super.draw(canvas);
        if(canvas!=null) {
            //canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.andriod), 0, 0, null);

           // mainCharacter.move(mainCharacter.getX()+20, mainCharacter.getY()+20, 1);
            System.out.print(String.valueOf(testX));
            mainCharacter.draw(canvas);
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.test), testX, 50, null);




            //System.out.print("REEEEEEEEEEEEEEEEEEEE");
            //canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.test), 50, 50, null);





        }
    }



}


