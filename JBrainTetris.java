import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;


public class JBrainTetris extends JTetris {
private Brain brain;
private JButton button;
private boolean enabled;
private Move move;
private JComboBox box;

public JBrainTetris(int width, int height)
{
    super(width,height);
    brain = null;
    enabled= false;
}
@Override
public Container createControlPanel()
{
        Container panel = super.createControlPanel();
        ArrayList<Brain> brains = BrainFactory.createBrains();
        brain=brains.get(0);    
        this.box = new JComboBox(brains.toArray());
        box.addActionListener(new ComboListener());
        panel.add(this.box);
        button=new JButton("Turn on Brain");
        panel.add(button);
        button.addActionListener(new ButtonListener());
        return panel;
    
}
@Override
public Piece pickNextPiece()
{
    Piece piece = super.pickNextPiece();
    this.move = brain.bestMove(super.board, piece, super.HEIGHT + super.TOP_SPACE);
    return piece;
}
@Override
 public void tick(int verb){
        if(enabled == true){
        if(move.getX() < currentX){
            super.tick(LEFT); 
            }
        else if (move.getX() > currentX){
            super.tick(RIGHT);
        }
        if(move.getPiece() != currentPiece){
            super.tick(ROTATE);
        }
        if(move.getPiece() == currentPiece && move.getX() == currentX){
            super.tick(DOWN);
        }
         }
        super.tick(verb);
    }
public class ComboListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            brain = (Brain)box.getSelectedItem();
        }
    }
public class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
          if (enabled)
            {
                enabled=false;
                button.setText("Brain off");
            }
            else
            {
                enabled = true;
                button.setText("Brain on");
                brain = (Brain)box.getSelectedItem();
            }
    }
}
}
    

     
    


   

