import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class pongAvecFonctions extends PApplet {

float xBalle,yBalle,decalageEnX,decalageEnY;
float xRaq,yRaq;
float pscal,pscal1; //Produit Scalaire
float norme,normeJeu;
float xAleat,yAleat; //Les nombres aleatoires
int score;

public void avancerBalle(){
    xBalle=xBalle+decalageEnX;
    yBalle=yBalle+decalageEnY;
}

public void bougerRaquette(){
  xRaq=mouseX;
  if(xRaq<20){
    xRaq=20;
  }
  if(xRaq>480){
    xRaq=480;
  }
  yRaq=mouseY;
 if (yRaq<20){
   yRaq=20;
 }
 if (yRaq>480){
   yRaq=480;
 }
 
}

public void effacer(){
  background(0);
}

public void dessiner(){
  pushStyle();// On ouvre \u00ab une parenth\u00e8se \u00bb de style
  
   ellipse(xBalle,yBalle,30,30);//couleur de la balle
   fill(255,0,0);
   
   popStyle();// On ferme notre parenth\u00e8se de style
   
   ellipse(xRaq,yRaq,40,40);//couleur de la raquette
   fill(255,255,255);
   
   pushStyle();// On ouvre \u00ab une parenth\u00e8se \u00bb de style
   
   stroke(0,0,255);
   fill(255,255,255,0);
   ellipse(xAleat,yAleat,50,50);//couleur du panier
   
   popStyle();// On ferme notre parenth\u00e8se de style
}

public void rebondir(){
  if (yBalle>height-15||yBalle<15){
    decalageEnY=-decalageEnY;
  }
   if (xBalle>width-15||xBalle<15){
    decalageEnX=-decalageEnX;
  }
  
  if(xBalle<15){
    xBalle=15;
  }
  if(xBalle>width-15){
    xBalle=width-15;
  }
 if (yBalle<15){
   yBalle=15;
 }
 if (yBalle>width-15){
   yBalle=width-15;
 }
  
  normeJeu=(((xAleat-xBalle)*(xAleat-xBalle))+((yAleat-yBalle)*(yAleat-yBalle)));//norme du panier et de la balle
  
  norme=(((xBalle-xRaq)*(xBalle-xRaq))+((yBalle-yRaq)*(yBalle-yRaq))); // norme de la balle et de la raquette
  
  pscal=((decalageEnX*(xRaq-xBalle))+(decalageEnY*(yRaq-yBalle)));  
  
  if(norme<=1225){ //On utilise le (xB-xA)\u00b2+(yB-yA)\u00b2<=(rayon Raquette+Rayon Balle)\u00b2
     decalageEnX = decalageEnX-(2*pscal)*(xRaq-xBalle)/norme;
     decalageEnY =decalageEnY-(2*pscal)*(yRaq-yBalle)/norme;
     xBalle=xBalle+((xBalle-xRaq)/sqrt(norme))*(35-sqrt(norme));
     yBalle=yBalle+((yBalle-yRaq)/sqrt(norme))*(35-sqrt(norme));    
  }
  
  if(normeJeu<=125){//J'ai mis 125 pour que \u00e7a soit plus "facile"
    score=score+1;////quand on "marque" un panier, on gagne 1 point
    xAleat=random(15,480);//le panier change de coordon\u00e9es al\u00e9atoirement enentre 15 et 480
    yAleat=random(15,480);
  }
  
  if(score==5){
    noLoop();
    text("Vous avez gagn\u00e9 !!",100,200);
  }
  
  text(score,200,50);//Affichage du score

}

public void setup(){
   frameRate(100);
   size(500,500);
   xBalle=15;
   yBalle=height/2;
   xRaq=20;
   yRaq=0;
   decalageEnX=2;
   decalageEnY=2;
   xAleat=random(15,480);
   yAleat=random(15,480);
}

public void draw(){
  effacer();
  rebondir();
  avancerBalle();
  bougerRaquette();
  dessiner();
  println(decalageEnX+"  "+decalageEnY);
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "pongAvecFonctions" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
