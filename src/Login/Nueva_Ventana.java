package Login;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Nueva_Ventana extends JDialog{
    private JPanel perfilPanel;
    private JLabel DirUserTXT;
    private JLabel CellUserTXT;
    private JLabel EmailUserTXT;
    private JLabel NameUserTXT;

    public User user;

    public void ModificarCampos (String name, String email, String cell, String dir){
        NameUserTXT.setText(name);
        EmailUserTXT.setText(email);
        CellUserTXT.setText(cell);
        DirUserTXT.setText(dir);
    }

    public Nueva_Ventana (JFrame parent, String name, String email, String cell, String dir) {
        super(parent);
        ModificarCampos(name, email, cell, dir); //Modifica los campos antes de mostrar
        setTitle(("Perfil Usuario"));
        setContentPane(perfilPanel);
        setMinimumSize(new Dimension(300, 220));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setVisible(true);
    }

}


