package Game;

import Map.Simulation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Visualization {
    JFrame frame;
    Timer timer;
    JTextArea textArea;
    JButton startStopButton;
    JButton stopSimulationButton;
    Simulation simulation;


    public Visualization(Simulation simulation){
        this.init();
        this.simulation = simulation;
    }

    private void init(){
        this.frame = new JFrame("Animals simulation");
        frame.setSize(1200,800);
        frame.setLayout(null);
        frame.setVisible(true);

        this.textArea=new JTextArea("");
        textArea.setBounds(50,100, 1000,600);
        textArea.setEditable(false);

        this.startStopButton = new JButton("");
        startStopButton.setBounds(50,20,80,20);
        frame.add(startStopButton);

        startStopButton.addActionListener( new ActionListener() {
            // The action listener that responds to the
            // button starts or stops the animation.  It
            // checks the value of timer to find out which
            // to do.  Timer is non-null when the animation
            // is running, so if timer is null, the
            // animation needs to be started.
            public void actionPerformed(ActionEvent evt) {
                if (timer == null)
                    startAnimation();
                else
                    stopAnimation();
            }
        });

        this.stopSimulationButton = new JButton("Zakończ");
        stopSimulationButton.setBounds(50,60,80,20);
        stopSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        frame.add(startStopButton);
        frame.add(stopSimulationButton);

    }

    ActionListener timerListener = new ActionListener() {
        // Define an action listener to respond to events
        // from the timer.
        public void actionPerformed(ActionEvent evt) {
            if(!simulation.areAnimalsAlive()){
                textArea.setText(simulation.toString());
                startStopButton.setVisible(false);
                timer.stop();
            }
            textArea.setText(simulation.toString());
            frame.add(textArea);
            SwingUtilities.updateComponentTreeUI(frame);
            simulation.day();
        }
    };

    public void startAnimation(){
        if (timer == null) {
            // Start the animation by creating a Timer that
            // will fire an event every 50 milliseconds, and
            // will send those events to timerListener.
            timer = new Timer(200, timerListener);
            timer.start();  // Make the time start running.
            startStopButton.setText("Pauza");
        }
    }

    private void stopAnimation() {
        // Stop the animation by stopping the timer, unless the
        // animation is not running.
        if (timer != null) {
            timer.stop();   // Stop the timer.
            timer = null;   // Set timer variable to null, so that we
            //   can tell that the animation isn't running.
            startStopButton.setText("Start");
        }
    }












}
