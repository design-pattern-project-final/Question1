package project1;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SwimInfo extends Frame implements ActionListener {
  SwimData sdata, sxdata = null;

  List swList, cloneList;

  Button Clone, Refresh, Quit;

  Swimmer sw;

  public SwimInfo() {
    super("Swimmers");
    String directory = System.getProperty("user.home");  
    String fileName = "swimmers.txt";  
    String absolutePath = directory + File.separator + fileName;
    sdata = new SwimData(absolutePath);

    setGUI();
    loadswList();
  }

  //-
  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
    if (obj == Clone)
      cloneAndLoad();
    if (obj == Refresh)
      loadswList();
    if (obj == Quit)
      System.exit(0);
  }

  //-
  private void cloneAndLoad() {
    //sxdata = (SwimData)sdata.deepClone();
    sxdata = (SwimData) sdata.clone();
    sxdata.sortByTime();

    cloneList.removeAll();
    //now print out sorted values from clone
    for (int i = 0; i < sxdata.size(); i++) {
      sw = sxdata.getSwimmer(i);
      cloneList.addItem(sw.getName() + " " + sw.getTime());
    }
  }

  //-
  private void loadswList() {
    swList.removeAll();
    for (int i = 0; i < sdata.size(); i++) {
      sw = sdata.getSwimmer(i);
      swList.addItem(sw.getName() + " " + sw.getTime());
    }
  }

  //-

  private void setGUI() {
    setLayout(new GridLayout(1, 3));
    setBackground(Color.lightGray);
    swList = new List(15);
    cloneList = new List(15);
    Panel cp = new Panel();
    add(swList);
    add(cp);
    add(cloneList);
    Clone = new Button("Clone >");
    Refresh = new Button("<Refresh");
    Quit = new Button("Quit");
    cp.setLayout(new GridLayout(3, 1));
    Panel p1 = new Panel();
    cp.add(p1);
    p1.add(Clone);
    Panel p2 = new Panel();
    cp.add(p2);
    p2.add(Refresh);
    Panel p3 = new Panel();
    cp.add(p3);
    p3.add(Quit);
    Clone.addActionListener(this);
    Refresh.addActionListener(this);
    Quit.addActionListener(this);
    setBounds(100, 100, 500, 400);
    setVisible(true);
  }

  //-
  static public void main(String argv[]) {
    new SwimInfo();
  }
}