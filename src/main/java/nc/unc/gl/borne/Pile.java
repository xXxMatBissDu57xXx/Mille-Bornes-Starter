package nc.unc.gl.borne;

import java.util.Stack;

public class Pile {

    public Stack<Carte> stack;

    public Pile() {
        this.stack = new Stack<Carte>();
    }

    public int getNbCartes() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public Carte piocher() {
        return this.stack.pop();
    }

    public void deposer(Carte carte) {
        stack.push(carte);
        return;
    }

    public Carte peek(){
        return stack.peek();
    }

    public Carte pop(){
        return stack.pop();
    }


    public void push(Carte carte){
        this.stack.push(carte);
    }
}
