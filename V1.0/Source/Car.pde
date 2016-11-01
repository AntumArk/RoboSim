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
