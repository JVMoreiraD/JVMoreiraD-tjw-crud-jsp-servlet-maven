package tjw.crud.contact.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tjw.crud.contact.dao.ContactDao;
import tjw.crud.contact.model.ContactModel;

/**
 * Servlet implementation class ContactServelet
 * @WebServlet(name= "Contact", urlPatterns ="/ContactServelet")
 */
@WebServlet("/")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDao contactDao;
  
    public void init() {
    	contactDao = new ContactDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertContact(request, response);
				break;
			case "/delete":
				deleteContact(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateContact(request, response);
				break;
			default:
				listContact(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}
	private void listContact(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<ContactModel> listContact = contactDao.selectAllContacts();
		request.setAttribute("listContact", listContact);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listContacts.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/contacts-forms.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ContactModel existingContact = contactDao.selectContact(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/contacts-forms.jsp");
		request.setAttribute("contact", existingContact);
		dispatcher.forward(request, response);

	}

	private void insertContact(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		ContactModel contact = new ContactModel(name, email, phone);
		contactDao.insertContact(contact);
		response.sendRedirect("list");
	}

	private void updateContact(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");

		ContactModel book = new ContactModel(id, name, email, phone);
		contactDao.updateContact(book);
		response.sendRedirect("list");
	}

	private void deleteContact(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		contactDao.deleteContact(id);
		response.sendRedirect("list");

	}

	
}
