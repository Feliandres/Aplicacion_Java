package Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Login extends JDialog{
    private JPanel loginPanel;
    private JTextField emailTF;
    private JPasswordField passwordTF;
    private JButton cancelBTN;
    private JButton okBTN;
    private JLabel paswordTxt;
    private JLabel emailTxt;
    private JLabel BienvenidosTxt;
    private JLabel MiTiendaTxt;

    public User user;

    public Login (JFrame parent) {
        super(parent);
        setTitle(("Login"));
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(640,480));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setVisible(true);

        okBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email=emailTF.getText();
                String password=String.valueOf(passwordTF.getPassword());
                System.out.println("boton ok");
                user=getAuthenticationUser(email,password);

                if (user!=null){
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(
                            Login.this,"email o password incorrectos",
                            "intente nuevamente",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
        });

        cancelBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton cancel");
                dispose();
            }
        });
        setVisible(true);
    }

    private User getAuthenticationUser(String email, String password){
        User user =null;

        final String DB_URL="jdbc:mysql://localhost/mitienda?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM usuarios WHERE email=? AND password=?"; /* cambio user por la entidad BD */            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            System.out.println("conexion OK");
            ResultSet resultSet=preparedStatement.executeQuery();

            if (resultSet.next()){
                user = new User();

                user.nombre=resultSet.getString("nombre");
                user.email=resultSet.getString("email");
                user.celular=resultSet.getString("celular");
                user.direccion=resultSet.getString("direccion");
                user.password=resultSet.getString("password");
            }

            stmt.close();
            conn.close();

        }catch(Exception e){
            System.out.println("error de...");
            e.printStackTrace();
        }
        return user;
    }


    public static void main(String[] args) {
        Login loginForm=new Login(null);
        User user = loginForm.user;

        if (user != null) {

            Nueva_Ventana perfil = new Nueva_Ventana(null, user.nombre, user.email, user.celular, user.direccion);

            System.out.println("Autenticacion correcta "+ user.nombre);
            System.out.println("email: "+user.email);
            System.out.println("celular: "+user.celular);
            System.out.println("direccion: "+user.direccion);
            System.out.println("password: "+user.password);
        }
        else {
            System.out.println("Autenticacion fallida");
        }
    }

}
