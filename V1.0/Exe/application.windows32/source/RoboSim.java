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

public class RoboSim extends PApplet {

boolean debug=false;
In I=new In();
car Car=new car();
public void setup()
{
background(0);
I.createButtons();
frameRate(30);
}
public void draw()
{ background(0);
  I.getinput();
  I.display();
 
  Car.UpdateLocation();
  Car.display();
   I.reset();
}
class car {
  private PVector loc=new PVector(150, 200);
  float dimx=40,dimy=40;
  private void UpdateLocation()
  {////////
    if (debug)
      println(I.rMloc('x'));
    ////////


    loc.set(I.rMloc('x'), I.rMloc('y'));
  }

  private void display()
  {
    fill(200);
    pushMatrix();
    translate(loc.x+dimx/4, loc.y+dimy/4);
    rect(-dimx/2, -dimy/2, dimx/2, dimy/2);
    popMatrix();
  }
}


public class In { //<>// //<>// //<>// //<>// //<>// //<>//
  /////Button class/////
   class button 
  {  
    private float x, y, s; 
    private String name;
    public void setInfo(String aname, float ax, float ay, float as)
    { 
      x=ax;
      y=ay;
      s=as;
      name=aname;
    }
    public void dispButton()
    {
      textSize(15);
      if (pressed())
        fill(255, 255, 0, 255);
      else 
        fill(255, 255);
      ellipse(x, y, s, s);
      fill(255,0,0);
      text(name, x-s, y+s*1.5f);
    }
    public void dispNameWithState(float ax,float ay)
    {textSize(10);
    fill(255);
      text(name,ax,ay);
    if(pressed())
     text("true",ax+40,ay);
    else
    text("false",ax+40,ay);
    }
    public String returnName()
    {
    return(name);
    }
    public boolean pressed()
    {
      boolean c=false;
      if (mousePressed)
        if ((abs(x-mouseX)<s)&&(abs(x-mouseX)>0)&&(abs(y-mouseY)<s)&&(abs(y-mouseY)>0))
        { ////////
          if (debug)
            println("pressed");
          ////////
          c= true;
        } else 
          c= false;

      return c;
    }
  }
  /////End/////

  private float mx, my, ik, mb;
  private int nButtons; 
  public button[] Buttons=new button[20];
  

  private void getinput() {
    mx=mouseX;
    my=mouseY;
    ik=key;
    mb=mouseButton;
  }
  
  private void reset()
  { mb=0;
    mx=0;
    my=0;
    ik=0;
  }
  
  public float rMloc(char w)
  {float val=-1;
    if(w=='x')
    val=(mx);
   if(w=='y') 
   val=(my);
   return(val);
  }
  private void createButtons()
  {    
    /////Load File/////
    String[] lines;
    int index = 1;
    lines = loadStrings("Buttons.txt");
   
    /////End/////
    /////Find number of buttons/////
    String[] piece=split(lines[0], ' ');
    nButtons=Integer.parseInt(piece[0]);
    /////End/////

    ////////
    if (debug)
      {print("Number of buttons");
      println(nButtons);}
    ///////


    /////Init Buttons/////
    // 
    
   for (int i=0; i<nButtons; i++)
      Buttons[i]=new button();
    /////End/////  
    /////Set Button information/////  
    for (int n=0; index < nButtons*4-1; index+=4, n++) {

      ////////
      if (debug)
       { print("Index ");
        println(index);
        print("N ");
        println(n);}
      ////////   

      String[] pieces = split(lines[0], ' ');

      ////////
      if (debug)
      {
        print("Button name ");
        println(pieces[index]);
        print("Button X ");
        println(Float.parseFloat(pieces[index+1]));
        print("Button Y ") ;
        println(pieces[index+2]);
        print("Button size ");
        println(pieces[index+3]);
      }
      ///////
      Buttons[n].setInfo(pieces[index], Float.parseFloat(pieces[index+1]), Float.parseFloat(pieces[index+2]), Float.parseFloat(pieces[index+3]));
    } 
    /////End/////
  }

  public void display()
  {
    for (int i=0; i<nButtons; i++)
   { Buttons[i].dispButton();
     Buttons[i].dispNameWithState(20,100+i*20);
  }
    textSize(10);
    fill(255);
    text("Mouse - ",20,20);
    text((int)mb,60,20);
    text("MouseX - ",20,40);
    text(mx,60,40);
    text("MouseY - ",20,60);
    text(my,60,60);
    text("Key int,char- ",20,80);
    text((int)ik,90,80);
    text((char)ik,120,80); 
     
  }
}
  public void settings() { size(400,800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "RoboSim" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
