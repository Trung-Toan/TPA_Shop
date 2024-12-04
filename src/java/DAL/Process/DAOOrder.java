package DAL.Process;

import Models.Orders;
import Models.OrderDetails;
import Models.Products;
import Models.ShipAddresses;
import Models.User;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class DAOOrder extends DAL.DAO {

    public Vector<Orders> getAllOrders() {
        String sql = "select * from [Orders] O inner join [OrderDetails] OD on O.id = OD.orderID";
        Vector<Orders> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Orders order = new Orders(
                        rs.getInt("id"),
                        rs.getInt("userID"),
                        rs.getInt("shipAddressID"),
                        rs.getInt("status"),
                        rs.getDate("createdAt"),
                        rs.getDate("updatedAt")
                );
                list.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void checkout(User account, Vector<Products> listItem) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        DAOOrder daoOrder = new DAOOrder();
        DAOOrderDetails daoOrderDetails = new DAOOrderDetails();
        DAOShipAddress dsa = new DAOShipAddress();

        ShipAddresses shipAddress = dsa.getShipAddressByUserId(account.getId());
        if (shipAddress == null) {
            System.out.println("No shipping address found for user ID: " + account.getId());
            return;
        }

        List<Orders> allOrders = daoOrder.getAllOrders();
        int newOrderId = 1; // Mặc định giá trị newOrderId là 1 nếu danh sách allOrders rỗng

        if (!allOrders.isEmpty()) {
            newOrderId = allOrders.get(allOrders.size() - 1).getId() + 1;
        }

        try {

            String sql1 = "INSERT INTO [Orders] ([userID], [shipAddressID], [status], [createdAt], [updatedAt]) "
                    + "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, account.getId());
            st1.setInt(2, shipAddress.getId());
            st1.setInt(3, 1);
            st1.setTimestamp(4, Timestamp.valueOf(formattedDate));
            st1.setTimestamp(5, Timestamp.valueOf(formattedDate));
            st1.executeUpdate();

            List<OrderDetails> allOrderDetails = daoOrderDetails.getAllOrderDetails();
            int newOrderItemId = 1;
            if (!allOrderDetails.isEmpty()) {
                newOrderItemId = allOrderDetails.get(allOrderDetails.size() - 1).getId() + 1;
            }

            for (Products item : listItem) {
                String sql2 = "INSERT INTO [OrderDetails] "
                        + "([quantity], [totalPrice], [orderID], [productDetailID], [size], [color], [price]) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement st3 = connection.prepareStatement(sql2);
                st3.setInt(1, item.getQuantity());
                st3.setDouble(2, item.getPriceAfterDiscount2() * item.getQuantity());
                st3.setInt(3, newOrderId);
                st3.setInt(4, item.getPDetails().getId());
                st3.setString(5, item.getPDetails().getSize());
                st3.setString(6, item.getPDetails().getColor());
                st3.setDouble(7, item.getPriceAfterDiscount2());
                st3.executeUpdate();
                newOrderItemId++;

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        DAOShipAddress dsa = new DAOShipAddress();
        System.out.println(dsa.getShipAddressByUserId(4));
    }

}

