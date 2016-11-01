public class In { //<>// //<>// //<>// //<>// //<>// //<>//
  /////Button class/////
   class button 
  {  
    private float x, y, s; 
    private String name;
    void setInfo(String aname, float ax, float ay, float as)
    { 
      x=ax;
      y=ay;
      s=as;
      name=aname;
    }
    void dispButton()
    {
      textSize(15);
      if (pressed())
        fill(255, 255, 0, 255);
      else 
        fill(255, 255);
      ellipse(x, y, s, s);
      fill(255,0,0);
      text(name, x-s, y+s*1.5);
    }
    void dispNameWithState(float ax,float ay)
    {textSize(10);
    fill(255);
      text(name,ax,ay);
    if(pressed())
     text("true",ax+40,ay);
    else
    text("false",ax+40,ay);
    }
    String returnName()
    {
    return(name);
    }
    boolean pressed()
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