package mx.edu.ittepic.dadm_u4_ejercicio8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    int dados[] = {R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};
    Thread[] jugadores;
    Random pc;
    Button inicar;
    boolean jugar = false;
    boolean[] jugadas;
    int[][] puntos;
    int[] totales;
    ImageView[][] dadosImagenes;
    TextView[] txtTotales;
    int turno = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicar = (Button) findViewById(R.id.btn_iniciar);
        inicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!jugar) {
                    jugar = true;
                    jugadas[0] = true;
                    jugadas[1] = false;
                    jugadas[2] = false;
                    jugadas[3] = false;
                }
            }
        });
        pc = new Random();
        totales = new int[4];
        jugadas = new boolean[4];
        txtTotales = new TextView[4];
        dadosImagenes = new ImageView[4][2];
        /* declaracion por id */
        dadosImagenes[0][0] = (ImageView) findViewById(R.id.j1_1);
        dadosImagenes[0][1] = (ImageView) findViewById(R.id.j1_2);
        txtTotales[0] = (TextView) findViewById(R.id.j1_p);
        dadosImagenes[1][0] = (ImageView) findViewById(R.id.j2_1);
        dadosImagenes[1][1] = (ImageView) findViewById(R.id.j2_2);
        txtTotales[1] = (TextView) findViewById(R.id.j2_p);
        dadosImagenes[2][0] = (ImageView) findViewById(R.id.j3_1);
        dadosImagenes[2][1] = (ImageView) findViewById(R.id.j3_2);
        txtTotales[2] = (TextView) findViewById(R.id.j3_p);
        dadosImagenes[3][0] = (ImageView) findViewById(R.id.j4_1);
        dadosImagenes[3][1] = (ImageView) findViewById(R.id.j4_2);
        txtTotales[3] = (TextView) findViewById(R.id.j4_p);
        puntos = new int[4][2];
        jugadores = new Thread[4];
        jugadores[0] = new Thread() {
            public void run() {
                try {
                    while (true) {
                        if (jugadas[0]) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (turno < 3) {
                                        if (jugar) {

                                                puntos[0][0] = pc.nextInt(6) + 1;
                                                puntos[0][1] = pc.nextInt(6) + 1;
                                                dadosImagenes[0][0].setImageDrawable(getResources().getDrawable(dados[puntos[0][0] - 1]));
                                                dadosImagenes[0][1].setImageDrawable(getResources().getDrawable(dados[puntos[0][1] - 1]));
                                                totales[0] += puntos[0][0] + puntos[0][1];
                                                txtTotales[0].setText("" + totales[0]);
                                                jugadas[0] = false;
                                                jugadas[1] = true;
                                        }
                                    } else {
                                        if (totales[0] > totales[1] && totales[0] > totales[2] && totales[0] > totales[3]) {
                                            Toast.makeText(MainActivity.this, "El ganador es jugador 1", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (totales[1] > totales[0] && totales[1] > totales[2] && totales[1] > totales[3]) {
                                                Toast.makeText(MainActivity.this, "El ganador es jugador 2", Toast.LENGTH_SHORT).show();
                                            } else {
                                                if (totales[2] > totales[0] && totales[2] > totales[1] && totales[2] > totales[3]) {
                                                    Toast.makeText(MainActivity.this, "El ganador es jugador 3", Toast.LENGTH_SHORT).show();
                                                } else
                                                    Toast.makeText(MainActivity.this, "El ganador es jugador 4", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        jugar = false;
                                        turno = 0;
                                        for (int i = 0; i < totales.length; i++) {
                                            totales[i] = 0;
                                        }
                                    }
                                }
                            });
                        }
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        jugadores[1] = new Thread() {
            public void run() {
                try {
                    while (true) {
                        if (jugadas[1]) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (turno < 3) {
                                        if (jugar) {
                                                puntos[1][0] = pc.nextInt(6) + 1;
                                                dadosImagenes[1][0].setImageDrawable(getResources().getDrawable(dados[puntos[1][0] - 1]));
                                                puntos[1][1] = pc.nextInt(6) + 1;
                                                dadosImagenes[1][1].setImageDrawable(getResources().getDrawable(dados[puntos[1][1] - 1]));
                                                totales[1] += puntos[1][0] + puntos[1][1];
                                                txtTotales[1].setText("" + totales[1]);
                                                jugadas[1] = false;
                                                jugadas[2] = true;
                                        }
                                    }
                                }
                            });

                        }
                        sleep(2000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        jugadores[2] = new Thread() {
            public void run() {
                try {
                    while (true) {
                        if (jugadas[2]) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (turno < 3) {
                                        if (jugar) {
                                                puntos[2][0] = pc.nextInt(6) + 1;
                                                dadosImagenes[2][0].setImageDrawable(getResources().getDrawable(dados[puntos[2][0] - 1]));
                                                puntos[2][1] = pc.nextInt(6) + 1;
                                                dadosImagenes[2][1].setImageDrawable(getResources().getDrawable(dados[puntos[2][1] - 1]));
                                                totales[2] += puntos[2][0] + puntos[2][1];
                                                txtTotales[2].setText("" + totales[2]);
                                                jugadas[2] = false;
                                                jugadas[3] = true;
                                        }
                                    }
                                }
                            });
                        }
                        sleep(3000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        jugadores[3] = new Thread() {
            public void run() {
                try {
                    while (true) {
                        if (jugadas[3]) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (turno < 3) {
                                        if (jugar) {
                                            puntos[3][0] = pc.nextInt(6) + 1;
                                            dadosImagenes[3][0].setImageDrawable(getResources().getDrawable(dados[puntos[3][0] - 1]));
                                            puntos[3][1] = pc.nextInt(6) + 1;
                                            dadosImagenes[3][1].setImageDrawable(getResources().getDrawable(dados[puntos[3][1] - 1]));
                                            totales[3] += puntos[3][0] + puntos[3][1];
                                            txtTotales[3].setText("" + totales[3]);
                                            jugadas[3] = false;
                                            jugadas[0] = true;
                                            turno++;
                                        }
                                    }
                                }
                            });
                        }
                        sleep(4000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        jugadores[0].start();
        jugadores[1].start();
        jugadores[2].start();
        jugadores[3].start();
    }
}
