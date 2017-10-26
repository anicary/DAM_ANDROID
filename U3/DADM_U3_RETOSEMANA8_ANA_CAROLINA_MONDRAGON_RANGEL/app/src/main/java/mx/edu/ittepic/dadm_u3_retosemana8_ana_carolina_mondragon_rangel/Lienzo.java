package mx.edu.ittepic.dadm_u3_retosemana8_ana_carolina_mondragon_rangel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by UsuarioPrueba on 12/10/2017.
 */

public class Lienzo extends View {

    CountDownTimer timer;
    boolean movimiento;
            int rx1=100,rx2=300,ry1=800,ry2=600;

            int cx1=400,cx2=600,cy1=100,cy2=300;

            public Lienzo(Context context) {

                super(context);
                movimiento = true;
                timer = new CountDownTimer(5000,1) {
                    @Override
                    public void onTick(long l) {

                        if(movimiento ==true){
                            rx1+=3;
                            rx2+=3;
                            cy1+=5;
                            cy2+=5;
                        }else{
                            rx1-=3;
                            rx2-=3;
                            cy1-=5;
                            cy2-=5;
                        }
                        if(rx2>=1000){
                            movimiento = false;
                        }
                        if(rx2<=300){
                            movimiento = true;
                        }
                        invalidate(); //Vuelve a ejecutar el onDraw
                    }

                    @Override
                    public void onFinish()
                    {
                        timer.start();
                    }
                };
                timer.start();
            }

            public void onDraw(Canvas c) {

                Paint p = new Paint(); //configuracion
                //DIBUJAR RECTANGULO
                p.setColor(Color.rgb(204, 95, 195));
                c.drawRect(rx1, ry1, rx2, ry2, p);
                //DIBBUJAR CIRCULO
                p.setColor(Color.rgb(146, 185, 195));
                RectF circulo = new RectF(cx1, cy1, cx2, cy2);
                c.drawOval(circulo, p);

            }

            public boolean onTouchEvent(MotionEvent e) { //tocar la pantalla siempre y cuando sea un view

                if (e.getAction() == MotionEvent.ACTION_DOWN) {//Esto es presionar
                    float x = e.getX();//coordenada de toque en X
                    float y = e.getY(); //coordenada de toque en Y

                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        if (x < cx2 && x > cx1 && y > cy1 && y < cy2) {
                            Toast.makeText(getContext(), "Presionaste en el Circulo", Toast.LENGTH_SHORT).show();
                        }
                        if (x < rx2 && x > rx1 && y > ry2 && y < ry1) {
                            Toast.makeText(getContext(), "Presionaste en el Cuadrado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    this.invalidate();
                }

                return true;
            }

        }
