
package mysqljava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MySqljava {

    
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/tienda?user=root&password=mysqladmin";
            Connection connect = DriverManager.getConnection(url);
            Statement statement = connect.createStatement();
            String query = "SELECT * FROM producto";
            ResultSet resultSet = statement.executeQuery(query);
          
            
            
            while(resultSet.next()){
                int idProd       = resultSet.getInt("id_producto");
                String  descProd = resultSet.getString("desc_producto");
                int precio       = resultSet.getInt("precio");
                System.out.println("Id: "+idProd);
                System.out.println("Prod: "+descProd);
                System.out.println("Precio: "+precio);
                System.out.println("====================");
            }
            
            Scanner scan = new Scanner(System.in);
            System.out.println("¿Que deseas hacer: INSERTAR / BORRAR / ACTUALIZAR");
            String accion = scan.nextLine();
            if(accion.equals("INSERTAR")) {
                scan = new Scanner(System.in);
                System.out.println("Ingrese el id_producto");
                String idProd = scan.nextLine();
                
                scan = new Scanner(System.in);
                System.out.println("Ingrese la desc_producto");
                String descProd = scan.nextLine();
                
                scan = new Scanner(System.in);
                System.out.println("Ingrese el precio");
                String precio = scan.nextLine();
                
                query = "INSERT INTO producto VALUES (?, ? ,?)";
                PreparedStatement ps = connect.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(idProd));
                ps.setString(2, descProd);
                ps.setInt(3, Integer.parseInt(precio));
                ps.executeUpdate();
            }else if (accion.equals("BORRAR")){
                scan = new Scanner(System.in);
                System.out.println("Ingrese el id_producto");
                String idProd = scan.nextLine();
                
                query = "DELETE FROM producto WHERE id_producto =?";
                      
                PreparedStatement ps = connect.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(idProd));
                ps.executeUpdate();
                
            }else if (accion.equals("ACTUALIZAR")){
                scan = new Scanner(System.in);
                System.out.println("Ingrese el id_producto");
                String idProd = scan.nextLine();
                
                scan = new Scanner(System.in);
                System.out.println("Ingrese la desc_producto");
                String descProd = scan.nextLine();
                
                scan = new Scanner(System.in);
                System.out.println("Ingrese el precio");
                String precio = scan.nextLine();
                
                query = "UPDATE producto SET desc_producto = ?, precio = ? WHERE id_producto = ?";
                PreparedStatement ps = connect.prepareStatement(query);
                ps.setString(1,(descProd));
                ps.setInt(2, Integer.parseInt(precio));
                ps.setInt(3, Integer.parseInt(idProd));
                ps.executeUpdate();
           
            }
                    
            resultSet.close();
            statement.close();
            connect.close();
        
        } catch (Exception e) {
            System.err.println(e);
        }
        
  
    }
    
}
