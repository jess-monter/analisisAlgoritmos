import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;

/**
 *
 * @author Jessica Monter Gallardo
 */


public class Grid extends Canvas {
  int width, height, rows, columns, x, y;
  int[][] plano;
  
  /**
   * @constructor
   * Método constructor del grid a adoquinar.
   * @param w ancho del grid
   * @param h altura del grid
   * @param r número de cuadros a lo largo y ancho del grid.
   * @param emptyX coordenada en el eje X del cuadro especial
   * @param emptyY coordenada en el eje Y del cuadro especial.
   */
  Grid(int w, int h, int r, int emptyX, int emptyY) {
    setSize(width = w, height = h);
    columns = r;
    rows = r;
    x = emptyX;
    y = emptyY;
    creaArregloGrid(r,emptyX,emptyY);
    imprimeArregloGrid();
  }

  /**
   *Método que crea el arreglo que servirá para pintar en el grid.
   *@param tam número de filas y columnas
   *@param x indice primero de la coordenada en que se ubicará el cuadro especial en el arreglo.
   *@param y indice segundo de la coordenada en que se ubicará el cuadro especial en el arreglo
   */
  public void creaArregloGrid(int tam, int x, int y) {
   
    plano = new int[tam][tam];
   
    for (int i=0; i<tam; i++) {
      for (int j=0; j<tam; j++) {
        plano[i][j] = 0;
      }
    }
    
    plano[x][y] = -1;
  }

  /**
   * Método que imprime el arreglo plano en consola e indica la posición de la coordenada especial.
   */
  public void imprimeArregloGrid() {
    for(int i=0; i<plano.length;i++){
      for(int j=0;j<plano.length;j++){
        if(plano[i][j] == -1) {
          System.out.println("******Coordenada Especial******: ("+ i + "," + j + "): " + plano[i][j]);
        }else{
          System.out.println("Coordenada: ("+ i + "," + j + "): " + plano[i][j]);
        }
        
      }
    }
  }


  /**
   * Método que hace la llamada inicial al método recursivo del algoritmo de adoquinamiento.
   * @param g, gráfico en el que se pintarán los pasos del algoritmo.
   **/
  public void adoquina(Graphics g) {
    adoquinaRecursivo(g, plano.length, 0, 0);
  }

  /**
   * Método recursivo que lleva a cabo el algoritmo de adoquinamiento.
   * @param g, gráfico donde se pintarán los pasos del algoritmo.
   * @param tam, tamaño del grid y el plano al que se le aplicará el algoritmo.
   * @param cordX, coordenada X desde la que se aplicará el algoritmo.
   * @param coordY, coordenada Y desde la que se aplicará el algoritmo.
   * @throws InterruptedException e
   *
   **/
  private void adoquinaRecursivo(Graphics g, int tam, int cordX, int coordY) {
    int tmpCoordX, tmpCoordY;
    try {
      if (tam == 2) {
        for (int i=0; i<tam; i++) {
          for (int j=0; j<tam; j++){
            if (plano[cordX+i][coordY+j] == 0) {
              plano[cordX+i][coordY+j] = 1;
              pintaAdoquin(g, cordX+i, coordY+j);
              Thread.sleep(1000);
            }
          }
        }
      }else{

        int tmpX=cordX, tmpY=coordY;
      
        for (int x=cordX; x<cordX+tam; x++) {
          for (int y=coordY; y<coordY+tam; y++) {
            if (plano[x][y] != 0) {
              tmpX = x;
              tmpY = y;
            }
          }
        }
        
        /* Si el cuadrado especial se encuentra en el cuadrante superior izquierdo*/
        if (tmpX < cordX + tam/2 && tmpY < coordY + tam/2) {

          plano[cordX+tam/2][coordY+tam/2-1] = -1;
          pintaAdoquin(g, cordX+tam/2, coordY+tam/2-1);
          Thread.sleep(1000);
          plano[cordX+tam/2][coordY+tam/2] = -1;
          pintaAdoquin(g, cordX+tam/2, coordY+tam/2);
          Thread.sleep(1000);
          plano[cordX+tam/2-1][coordY+tam/2] = -1;
          pintaAdoquin(g, cordX+tam/2-1, coordY+tam/2);
          Thread.sleep(1000);
          adoquinaRecursivo(g, tam/2, cordX, coordY);
          adoquinaRecursivo(g, tam/2, cordX, coordY+tam/2);
          adoquinaRecursivo(g, tam/2, cordX+tam/2, coordY);
          adoquinaRecursivo(g, tam/2, cordX+tam/2, coordY+tam/2);
        
        /* Si el cuadrado especial se encuentra en el cuadrante superior derecho*/
        } else if (tmpX < cordX + tam/2 && tmpY >= coordY + tam/2) {

          plano[cordX+tam/2][coordY+tam/2-1] = -1;
          pintaAdoquin(g, cordX+tam/2, coordY+tam/2-1);
          Thread.sleep(1000);
          plano[cordX+tam/2][coordY+tam/2] = -1;
          pintaAdoquin(g, cordX+tam/2, coordY+tam/2);
          Thread.sleep(1000);
          plano[cordX+tam/2-1][coordY+tam/2-1] = -1;
          pintaAdoquin(g, cordX+tam/2-1, coordY+tam/2-1);
          Thread.sleep(1000);
          adoquinaRecursivo(g, tam/2, cordX, coordY+tam/2);
          adoquinaRecursivo(g, tam/2, cordX, coordY);
          adoquinaRecursivo(g, tam/2, cordX+tam/2, coordY);
          adoquinaRecursivo(g, tam/2, cordX+tam/2, coordY+tam/2);
          
        /* Si el cuadro especial se encuentra en el cuadrante inferior izquierdo*/
        } else if (tmpX >= cordX + tam/2 && tmpY < coordY + tam/2) {
        
          plano[cordX+tam/2-1][coordY+tam/2] = -1;
          pintaAdoquin(g, cordX+tam/2-1, coordY+tam/2);
          Thread.sleep(1000);
          plano[cordX+tam/2][coordY+tam/2] = -1;
          pintaAdoquin(g, cordX+tam/2, coordY+tam/2);
          Thread.sleep(1000);
          plano[cordX+tam/2-1][coordY+tam/2-1] = -1;
          pintaAdoquin(g, cordX+tam/2-1, coordY+tam/2-1);
          Thread.sleep(1000);
          adoquinaRecursivo(g, tam/2, cordX+tam/2, coordY);
          adoquinaRecursivo(g, tam/2, cordX, coordY);
          adoquinaRecursivo(g, tam/2, cordX, coordY+tam/2);
          adoquinaRecursivo(g, tam/2, cordX+tam/2, coordY+tam/2);
        /* Si el cuadro especial se encuentra en el cuadrante inferior derecho */
        } else {
        
          plano[cordX+tam/2-1][coordY+tam/2] = -1;
          pintaAdoquin(g, cordX+tam/2-1, coordY+tam/2);
          Thread.sleep(1000);
          plano[cordX+tam/2-1][coordY+tam/2-1] = -1;
          pintaAdoquin(g, cordX+tam/2-1, coordY+tam/2-1);
          Thread.sleep(1000);
          plano[cordX+tam/2][coordY+tam/2-1] = -1;
          pintaAdoquin(g, cordX+tam/2, coordY+tam/2-1);
          Thread.sleep(1000);
          adoquinaRecursivo(g, tam/2, cordX+tam/2, coordY+tam/2);
          adoquinaRecursivo(g, tam/2, cordX+tam/2, coordY);
          adoquinaRecursivo(g, tam/2, cordX, coordY+tam/2);
          adoquinaRecursivo(g, tam/2, cordX, coordY);
        }
      }
    } catch (InterruptedException e) {
      return;
    }
  }

  /**
   * @param Graphics g, grid a pintar
   * Método que pinta el adoquín en forma de L en el grid
   */
  public void pintaAdoquin(Graphics g, int x, int y) {
    g.setColor(Color.RED);
    g.fillRect(x*(width/rows)+1, y*(width/rows)+1, width/rows-1, height/rows-1);
  }

  /**
   * Método que pintará las lineas verticales y horizontales en el grid.
   * @param g es el gráfico donde se pintarán las líneas.
   *
   */
  public void paint(Graphics g) {
    width = getSize().width;
    height = getSize().height;

    int heightOfRow = height / rows;
    for (int k = 0; k < rows; k++)
    g.drawLine(0, k * heightOfRow , width, k * heightOfRow );

    int widthOfRow = width / columns;
    
    for (int k = 0; k < columns; k++) {
      g.drawLine(k*widthOfRow , 0, k*widthOfRow , height);
    }
    
    g.setColor(Color.BLACK);
    g.fillRect(x*(width/rows)+1, y*(width/rows)+1, width/rows-1, height/rows-1);
    adoquina(g);
  }
}

class Adoquinamiento extends Frame {
  
  /**
  * @constructor
  * Método que crea la ventana y el grid dentro de ella.
  * @param title String que será el título de la ventana
  * @param w int que será el ancho de la ventana
  * @param h int que será el alto de la ventana
  * @param rows int que será el número de cuadros a lo largo y ancho del grid.
  * @param x int que será la coordenada en el eje X del cuadro especial.
  * @param y int que será la coordenada en el eje Y del cuadro especial.
  */
  Adoquinamiento(String title, int w, int h, int rows, int x, int y) {
    setTitle(title);
    setSize(w, h);
    setResizable(false);
    Grid grid = new Grid(w, h, rows, x, y);
    add(grid);
  }


  public static void main(String[] args) {
  

    Scanner in = new Scanner(System.in);
    System.out.println("¿De qué tamaño desea el grid? (Debe ser una potencia de 2)");
    int tam = in.nextInt();
    System.out.println("¿En qué coordenadas desea que se ubique el cuadro especial?");
    System.out.println("Coordenada X (0<= X < tamaño del grid: ");
    int cx = in.nextInt();
    System.out.println("Coordenada Y: 0 <= Y < tamaño del grid: ");
    int cy = in.nextInt();
    
    Adoquinamiento main = new Adoquinamiento("Algoritmo de Adoquinamiento", 512, 512, tam, cx, cy);
    main.setVisible(true);

    System.out.println("Corriendo Algoritmo de Adoquinamiento...");
  }
}