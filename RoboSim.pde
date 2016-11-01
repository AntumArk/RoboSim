boolean debug=false;
In I=new In();
car Car=new car();
void setup()
{size(400,800);
background(0);
I.createButtons();
frameRate(30);
}
void draw()
{ background(0);
  I.getinput();
  I.display();
 
  Car.UpdateLocation();
  Car.display();
   I.reset();
}