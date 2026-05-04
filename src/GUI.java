import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;

public class GUI implements ActionListener{
    JFrame frame;

    JButton modeButton;
    JButton passFreqButton;
    JButton confirmButton;
    JButton simulateButton;
    JButton advSettingsButton;

    JTextField maxFloorField;
    JTextField defaultFloorField;

    JTextField passTimeField;
    JTextField passChanceField;
    JTextField simTimeField;
    JTextField accelTimeField;
    JTextField doorTimeField;
    JTextField regularTimeField;

    JLabel passTimeLabel;
    JLabel passChanceLabel;
    JLabel simTimeLabel;
    JLabel accelTimeLabel;
    JLabel doorTimeLabel;
    JLabel regularTimeLabel;

    JLabel FloorNumLabel;
    JLabel FloorDefaultLabel;

    Color backgroundColor = new Color(114, 114, 114);
    Color foregroundColor = new Color(177, 177, 177);

    JLayeredPane layers;

    SimParams parameters = new SimParams();

    // GUI variables
    int passFreqMode = 0;
    boolean advSettings = false;

    // Sizing:
    int yConfirm = 110;
    int yResults = yConfirm + 100;
    int yAdvSettings = yResults + 100;

    public GUI(){
        frame = new JFrame();
        layers = new JLayeredPane();
        {
            // Passenger Spawn Mode Button
            modeButton = new JButton("Tryb: Losowy");
            modeButton.addActionListener(this);
            modeButton.setBounds(345, 35, 250, 30);
            layers.add(modeButton, 10);

            // Passenger Frequency Button
            passFreqButton = new JButton("Liczba pasażerów: Umiarkowana");
            passFreqButton.addActionListener(this);
            passFreqButton.setBounds(345, 70, 250, 30);
            layers.add(passFreqButton, 10);

            // Confirm Button
            confirmButton = new JButton("Zatwierdź");
            confirmButton.addActionListener(this);
            confirmButton.setBounds(265,yConfirm+10,100,30);
            layers.add(confirmButton, 10);

            simulateButton = new JButton("Symulacja");
            simulateButton.addActionListener(this);
            simulateButton.setBounds(240, yConfirm + 50, 150, 50);
            frame.add(simulateButton);

            advSettingsButton = new JButton("Zaawansowane ustawienia >");
            advSettingsButton.addActionListener(this);
            advSettingsButton.setBounds(10, yAdvSettings+10, 200, 30);
            frame.add(advSettingsButton);
        } // Buttons
        {
            // Maximum Floor Text Field
            maxFloorField = new JTextField();
            maxFloorField.setBounds(130, 35, 30, 25);
            maxFloorField.setText("5");
            layers.add(maxFloorField, 10);

            // Default Floor Text Field, which floor the elevator will go to when there are no calls
            defaultFloorField = new JTextField();
            defaultFloorField.setBounds(130, 70, 30, 25);
            defaultFloorField.setText("0");
            layers.add(defaultFloorField, 10);

            // Advanced settings:
            passTimeField = new JTextField();
            passTimeField.setBounds(460, yAdvSettings+80, 30, 25);
            passTimeField.setText(""+parameters.passTime);
            frame.add(passTimeField);

            passChanceField = new JTextField();
            passChanceField.setBounds(460, yAdvSettings+115, 30, 25);
            passChanceField.setText(""+parameters.passChance);
            frame.add(passChanceField);

            simTimeField = new JTextField();
            simTimeField.setBounds(160, yAdvSettings+45, 30, 25);
            simTimeField.setText(""+parameters.simTime);
            frame.add(simTimeField);

            accelTimeField = new JTextField();
            accelTimeField.setBounds(160, yAdvSettings+115, 30, 25);
            accelTimeField.setText(""+parameters.accelTime);
            frame.add(accelTimeField);

            doorTimeField = new JTextField();
            doorTimeField.setBounds(460, yAdvSettings+45, 30, 25);
            doorTimeField.setText(""+parameters.doorTime);
            frame.add(doorTimeField);

            regularTimeField = new JTextField();
            regularTimeField.setBounds(160, yAdvSettings+80, 30, 25);
            regularTimeField.setText(""+parameters.regularTime);
            frame.add(regularTimeField);

        } // Text Fields
        {
            // Max Floor Number Label
            FloorNumLabel = new JLabel("Zapis: " + parameters.maxFloor, SwingConstants.LEFT);
            FloorNumLabel.setBounds(170, 35, 300, 25);
            frame.add(FloorNumLabel);

            // Max Floor Number Label
            FloorDefaultLabel = new JLabel("Zapis: " + parameters.defaultFloor, SwingConstants.LEFT);
            FloorDefaultLabel.setBounds(170, 70, 300, 25);
            frame.add(FloorDefaultLabel);

            // Advanced Settings Labels
            passTimeLabel = new JLabel("Zapis: "+parameters.passTime, SwingConstants.LEFT);
            passTimeLabel.setBounds(500, yAdvSettings+80, 300, 25);
            frame.add(passTimeLabel);

            passChanceLabel = new JLabel("Zapis: "+parameters.passChance, SwingConstants.LEFT);
            passChanceLabel.setBounds(500, yAdvSettings+115, 300, 25);
            frame.add(passChanceLabel);

            simTimeLabel = new JLabel("Zapis: "+parameters.simTime, SwingConstants.LEFT);
            simTimeLabel.setBounds(200, yAdvSettings+45, 300, 25);
            frame.add(simTimeLabel);

            accelTimeLabel = new JLabel("Zapis: "+parameters.accelTime, SwingConstants.LEFT);
            accelTimeLabel.setBounds(200, yAdvSettings+115, 300, 25);
            frame.add(accelTimeLabel);

            doorTimeLabel = new JLabel("Zapis: "+parameters.doorTime, SwingConstants.LEFT);
            doorTimeLabel.setBounds(500, yAdvSettings+45, 300, 25);
            frame.add(doorTimeLabel);

            regularTimeLabel = new JLabel("Zapis: "+parameters.regularTime, SwingConstants.LEFT);
            regularTimeLabel.setBounds(200, yAdvSettings+80, 300, 25);
            frame.add(regularTimeLabel);

        } // Labels

        { // Static Labels
            JLabel elevatorMainLabel = new JLabel("Parametry Budynku", SwingConstants.CENTER);
            elevatorMainLabel.setBounds(10, 10, 300, 25);
            layers.add(elevatorMainLabel, 18);

            JLabel passengerMainLabel = new JLabel("Parametry Pasażerów", SwingConstants.CENTER);
            passengerMainLabel.setBounds(320, 10, 300, 25);
            layers.add(passengerMainLabel, 18);

            JLabel maxFloorLabel = new JLabel("Najwyższe piętro:", SwingConstants.LEFT);
            maxFloorLabel.setBounds(20, 35, 300, 25);
            layers.add(maxFloorLabel, 18);

            JLabel defaultFloorLabel = new JLabel("Domyślne piętro:", SwingConstants.LEFT);
            defaultFloorLabel.setBounds(20, 70, 300, 25);
            layers.add(defaultFloorLabel, 18);

            JLabel advSimTimeLabel = new JLabel("Czas symulacji:", SwingConstants.LEFT);
            advSimTimeLabel.setBounds(20, yAdvSettings+45, 300, 25);
            frame.add(advSimTimeLabel);

            JLabel advTravelTimeLabel = new JLabel("Czas przejazdu:", SwingConstants.LEFT);
            advTravelTimeLabel.setBounds(20, yAdvSettings+80, 300, 25);
            frame.add(advTravelTimeLabel);

            JLabel advAccelTimeLabel = new JLabel("Czas przyspieszenia:", SwingConstants.LEFT);
            advAccelTimeLabel.setBounds(20, yAdvSettings+115, 300, 25);
            frame.add(advAccelTimeLabel);

            JLabel advDoorTimeLabel = new JLabel("Czas otworzenia drzwi:", SwingConstants.LEFT);
            advDoorTimeLabel.setBounds(320, yAdvSettings+45, 300, 25);
            frame.add(advDoorTimeLabel);

            JLabel advSpawnTimeLabel = new JLabel("Czas pasażera:", SwingConstants.LEFT);
            advSpawnTimeLabel.setBounds(320, yAdvSettings+80, 300, 25);
            frame.add(advSpawnTimeLabel);

            JLabel advChanceLabel = new JLabel("Szansa pasażera:", SwingConstants.LEFT);
            advChanceLabel.setBounds(320, yAdvSettings+115, 300, 25);
            frame.add(advChanceLabel);
        } // Static Labels
        { // Static Rectangles
            JPanel elevatorParamsRectMain = new JPanel();
            elevatorParamsRectMain.setBackground(foregroundColor);
            elevatorParamsRectMain.setBounds(10, 10, 300, 100);
            layers.add(elevatorParamsRectMain, 10);

            JPanel passengerParamsRectMain = new JPanel();
            passengerParamsRectMain.setBackground(foregroundColor);
            passengerParamsRectMain.setBounds(320, 10, 300, 100);
            layers.add(passengerParamsRectMain, 20);

            JPanel resultsRect = new JPanel();
            resultsRect.setBackground(foregroundColor);
            resultsRect.setBounds(10, yResults+10,610,90);
            layers.add(resultsRect, 20);

            JPanel advSettingsRect = new JPanel();
            advSettingsRect.setBackground(foregroundColor);
            advSettingsRect.setBounds(10, yAdvSettings+40,610,110);
            frame.add(advSettingsRect);

            JPanel backgroundRect = new JPanel();
            backgroundRect.setBackground(backgroundColor);
            backgroundRect.setBounds(0, 0, 630, 800);
            layers.add(backgroundRect, 12);
        } // Static Rectangles

        frame.add(layers);
        frame.setSize(644,387);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Symulator windy");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent action) {
        if (action.getSource() == modeButton) {
            if (parameters.mode == 0){
                parameters.mode = 1;
                modeButton.setText("Tryb: Wyjście do pracy");
            } else if (parameters.mode == 1) {
                parameters.mode = 2;
                modeButton.setText("Tryb: Powrót do domu");
            } else {
                parameters.mode = 0;
                modeButton.setText("Tryb: Losowy");
            }
        } else if (action.getSource() == confirmButton) {
            confirmChanges();

        } else if (action.getSource() == passFreqButton) {
            if (passFreqMode == 0){
                passFreqMode = 1;
                passFreqButton.setText("Liczba pasażerów: Duża");
            } else if (passFreqMode == 1) {
                passFreqMode = 2;
                passFreqButton.setText("Liczba pasażerów: Mała");
            } else {
                passFreqMode = 0;
                passFreqButton.setText("Liczba pasażerów: Umiarkowana");
            }

        } else if (action.getSource() == simulateButton) {
            Simulation sim = new Simulation(parameters);
            sim.SimStart();
            ShowResults(sim.SimEnd());

        } else if (action.getSource() == advSettingsButton) {
            if (advSettings)
            { // already on
                advSettings = false;
                frame.setSize(644,387);
                advSettingsButton.setText(("Zaawansowane ustawienia >"));
            } else
            { // already off
                advSettings = true;
                frame.setSize(644,496);
                advSettingsButton.setText(("Zaawansowane ustawienia v"));
            }
        }
    }

    private void confirmChanges() {
        parameters.maxFloor = stringToInt(maxFloorField.getText());
        parameters.defaultFloor = stringToInt(defaultFloorField.getText());
        if (parameters.maxFloor == -1 || parameters.defaultFloor == -1 || parameters.defaultFloor > parameters.maxFloor)
            InvalidData();

        parameters.passTime = stringToInt(passTimeField.getText());
        parameters.passChance = stringToInt(passChanceField.getText());
        parameters.simTime = stringToInt(simTimeField.getText());
        parameters.accelTime = stringToInt(accelTimeField.getText());
        parameters.doorTime = stringToInt(doorTimeField.getText());
        parameters.regularTime = stringToInt(regularTimeField.getText());
        if (parameters.passTime == -1 || parameters.passChance == -1 || parameters.simTime == -1 || parameters.accelTime == -1 || parameters.doorTime == -1 || parameters.regularTime == -1)
            InvalidData();
        RefreshText();
    }

    private int stringToInt(String text) {
        int k = 0;
        double temp=0;
        for (int i = text.length() - 1; i >= 0; i--) {
            if (text.toCharArray()[i]>47 && text.toCharArray()[i]<58) {
                temp += (((double) text.toCharArray()[i] - 48) * Math.pow(10, (double) k));
                k++;
            }else{
                temp = -1;
                break;
            }
        }
        return (int) temp;
    }

    private void InvalidData() {
        parameters.maxFloor = 5;
        maxFloorField.setText("5");
        parameters.defaultFloor = 0;
        defaultFloorField.setText("0");

        parameters.passTime = 4;
        passTimeField.setText(""+parameters.passTime);
        parameters.passChance = 10;
        passChanceField.setText(""+parameters.passChance);
        parameters.simTime = 10000;
        simTimeField.setText(""+parameters.simTime);
        parameters.accelTime = 3;
        accelTimeField.setText(""+parameters.accelTime);
        parameters.doorTime = 10;
        doorTimeField.setText(""+parameters.doorTime);
        parameters.regularTime = 10;
        regularTimeField.setText(""+parameters.regularTime);
    }

    private void RefreshText() {
        FloorNumLabel.setText("Zapis: " + parameters.maxFloor);
        FloorDefaultLabel.setText("Zapis: " + parameters.defaultFloor);

        passTimeLabel.setText("Zapis: " + parameters.passTime);
        passChanceLabel.setText("Zapis: " + parameters.passChance);
        simTimeLabel.setText("Zapis: " + parameters.simTime);
        accelTimeLabel.setText("Zapis: " + parameters.accelTime);
        doorTimeLabel.setText("Zapis: " + parameters.doorTime);
        regularTimeLabel.setText("Zapis: " + parameters.regularTime);
    }

    private void ShowResults(SimResults res){
        JLabel resultLabel = new JLabel("Wyniki symulacji", SwingConstants.CENTER);
        resultLabel.setBounds(10, yResults+10, 610, 25);
        layers.add(resultLabel,10);

        JLabel result1 = new JLabel("Liczba obsłużonych pasażerów: "+res.passengersCount, SwingConstants.CENTER);
        result1.setBounds(10, yResults + 35, 300, 25);
        layers.add(result1,10);

        JLabel result2 = new JLabel("Liczba pokonanych pięter: "+res.floorCount, SwingConstants.CENTER);
        result2.setBounds(10, yResults + 70, 300, 25);
        layers.add(result2,10);

        JLabel result3 = new JLabel("Średni czas obsłużenia pasażera: "+res.passengerTime, SwingConstants.CENTER);
        result3.setBounds(310, yResults + 35, 300, 25);
        layers.add(result3,10);

        JLabel result4 = new JLabel("Liczba zatrzymań: "+res.stopCount, SwingConstants.CENTER);
        result4.setBounds(310, yResults + 70, 300, 25);
        layers.add(result4,10);
    }

}

// kolory: button.setBackground(new Color(120, 200, 150));
//Color.RED
//Color.BLUE
//Color.GREEN
//Color.YELLOW
//Color.BLACK
//Color.WHITE
//Color.ORANGE
//Color.PINK
//Color.GRAY