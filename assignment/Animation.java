class Animation extends Thread {
    private Shape[] shapes;
    private AnimatedShapes anim;
    
    public Animation(Shape[] shapes, AnimatedShapes anim) {
        this.shapes = shapes;
        this.anim = anim;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (Shape shape : shapes) {
                    shape.move();
                    bounceBack(shape);
                }
                anim.repaint();
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
            anim.isAnimationRunning = false;
        }
   }
    
    // bounceBack method for making sure that circles goes back if they hit the edges
    private void bounceBack(Shape shape) {
        if (shape.getX() <= 0 || shape.getX() >= anim.getWidth() - shape.getR() * 2) {
            shape.reverseX();
        }

        if (shape.getY() <= 0 || shape.getY() >= anim.getHeight() - shape.getR() * 2) {
            shape.reverseY();
        }
    }

}  