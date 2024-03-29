package com.cjsanon.thrillio.controllers;

import com.cjsanon.thrillio.constants.KidFriendlyStatus;
import com.cjsanon.thrillio.entities.Bookmark;
import com.cjsanon.thrillio.entities.User;
import com.cjsanon.thrillio.managers.BookmarkManager;
import com.cjsanon.thrillio.managers.UserManager;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(urlPatterns = {"/bookmark", "/bookmark/save", "/bookmark/mybooks"})
public class BookmarkController extends HttpServlet {
    /*private static final BookmarkController instance = new BookmarkController();
    private BookmarkController(){}

    public static BookmarkController getInstance(){
        return instance;
    }*/

    public BookmarkController() {
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        System.out.println("Servlet path: " + request.getServletPath());

        if(request.getSession().getAttribute("userId") != null) {
            long userId = (long)request.getSession().getAttribute("userId"); //getAttribute returns an object so it must be cast

            if(request.getServletPath().contains("save")) {
                //save
                dispatcher = request.getRequestDispatcher("/mybooks.jsp");

                String bid = request.getParameter("bid");

                User user = UserManager.getInstance().getUser(userId);
                Bookmark bookmark = BookmarkManager.getInstance().getBook(Long.parseLong(bid));
                BookmarkManager.getInstance().saveUserBookmark(user, bookmark);

                Collection<Bookmark> list = BookmarkManager.getInstance().getBooks(true, userId); //fetch books
                request.setAttribute("books", list);

            } else if (request.getServletPath().contains("mybooks")) {
                //mybooks
                dispatcher = request.getRequestDispatcher("/mybooks.jsp");

                Collection<Bookmark> list = BookmarkManager.getInstance().getBooks(true, userId); //only get books that are saved by the user
                request.setAttribute("books", list);
            } else {
                dispatcher = request.getRequestDispatcher("/browse.jsp");

                Collection<Bookmark> list = BookmarkManager.getInstance().getBooks(false, userId); //fetch books
                request.setAttribute("books", list);
            }
        } else {
            dispatcher = request.getRequestDispatcher("/login.jsp");
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }



    public static void saveUserBookmark(User user, Bookmark bookmark) {
        BookmarkManager.getInstance().saveUserBookmark(user, bookmark); //passes new bookmark information to the bookmark manager
    }

    public static void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
        BookmarkManager.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
    }

    public static void share(User user, Bookmark bookmark) {
        BookmarkManager.getInstance().share(user, bookmark);
    }
}
