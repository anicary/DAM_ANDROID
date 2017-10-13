package mx.edu.ittepic.dadm_u3_retosemana8_ana_carolina_mondragon_rangel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by UsuarioPrueba on 12/10/2017.
 */

        public class Lienzo extends View {
            int x1 = 400, x2 = 600, y1 = 100, y2 = 300;

            public Lienzo(Context context) {
                super(context);
            }

            public void onDraw(Canvas c) {

                Paint p = new Paint(); //configuracion
                //Edificio
                p.setColor(Color.BLUE);
                c.drawRect(100, 800, 300, 1000, p);

                p.setColor(Color.RED);
                RectF circulo = new RectF(x1, y1, x2, y2);
                c.drawOval(circulo, p);
                //c.drawCircle(500,200,130,p);


            }

            public boolean onTouchEvent(MotionEvent e) { //tocar la pantalla siempre y cuando sea un view

                if (e.getAction() == MotionEvent.ACTION_DOWN) {//Esto es presionar
                    float x = e.getX();//coordenada de toque en X
                    float y = e.getY(); //coordenada de toque en Y

                    if (e.getAction() == MotionEvent.ACTION_DOWN) {
                        if (x < x2 && x > x1 && y > y1 && y < y2) {
                            Toast.makeText(getContext(), "Presionaste en el Circulo", Toast.LENGTH_SHORT).show();
                        }
                        if (x < 300 && x > 100 && y > 800 && y < 1000) {
                            Toast.makeText(getContext(), "Presionaste en el Cuadrado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    this.invalidate();
                }

                return true;
            }

        }
