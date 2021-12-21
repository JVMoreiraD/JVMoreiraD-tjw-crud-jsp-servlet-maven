package tjw.crud.contact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tjw.crud.contact.model.ContactModel;
import tjw.crud.contact.util.ContactUtil;

public class ContactDao {
	private static final String SELECT_CONTACT_BY_ID = "select id,name,email,phone from contact where id=?" ;
	private static final String INSERT_CONTACT_SQL = "INSERT INTO contact (name, email, phone) VALUES ( ?, ?, ?)" ;
	private static final String SELECT_ALL_CONTACTS = "select * from contact";
	private static final String DELETE_CONTACT_SQL = "delete from contact where id = ?";
	private static final String UPDATE_CONTACT_SQL = "update contact set name= ?, email= ?, phone=? where id=?";	
	
	public void insertContact(ContactModel contact) {
		try(Connection connection = ContactUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CONTACT_SQL);){
			
			preparedStatement.setString(1, contact.getName());
			preparedStatement.setString(2, contact.getEmail());
			preparedStatement.setString(3, contact.getPhone());
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ContactModel selectContact(int id) {
		ContactModel contact = null;
		try (Connection connection = ContactUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTACT_BY_ID);){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				contact = new ContactModel(id,name,email,phone);
				}
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return contact;
	}
	public List<ContactModel> selectAllContacts() {
		List<ContactModel> contacts = new ArrayList<ContactModel>();
		try (Connection connection = ContactUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CONTACTS);){
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				contacts.add(new ContactModel(id,name,email,phone));
				}
		}catch (SQLException e) {
				e.printStackTrace();
		}
		return contacts;
	}
	public void updateContact(ContactModel contact) throws SQLException{
		try(Connection connection = ContactUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CONTACT_SQL);){
			
			preparedStatement.setString(1, contact.getName());
			preparedStatement.setString(2, contact.getEmail());
			preparedStatement.setString(3, contact.getPhone());
			preparedStatement.setInt(4, contact.getId());
			preparedStatement.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteContact(int id) throws SQLException{
		try (Connection connection = ContactUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CONTACT_SQL);){
			statement.setInt(1, id);
			statement.executeUpdate();
		}
	}
}
