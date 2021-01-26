package com.cjsanon.thrillio.managers;

import com.cjsanon.thrillio.constants.BookGenre;
import com.cjsanon.thrillio.constants.KidFriendlyStatus;
import com.cjsanon.thrillio.constants.MovieGenre;
import com.cjsanon.thrillio.dao.BookmarkDao;
import com.cjsanon.thrillio.entities.*;

import java.util.Collection;
import java.util.List;

public class BookmarkManager {
    private static BookmarkManager instance = new BookmarkManager();
    private BookmarkDao dao = new BookmarkDao();

    private BookmarkManager(){}
    public static BookmarkManager getInstance(){
        return instance;
    }

    public Movie createMovie(long id, String title, int releaseYear, String[] cast, String[] directors, MovieGenre genre, double imdbRating){
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setReleaseYear(releaseYear);
        movie.setCast(cast);
        movie.setDirectors(directors);
        movie.setGenre(genre);
        movie.setImdbRating(imdbRating);

        return movie;
    }

    public Book createBook(long id, String title, String imageUrl, int publicationYear, String publisher, String[] authors, BookGenre genre, double amazonRating){
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setImageUrl(imageUrl);
        book.setPublicationYear(publicationYear);
        book.setPublisher(publisher);
        book.setAuthors(authors);
        book.setGenre(genre);
        book.setAmazonRating(amazonRating);

        return book;
    }

    public WebLink createWebLink(long id, String title, String url, String host){
        WebLink weblink = new WebLink();
        weblink.setId(id);
        weblink.setTitle(title);
        weblink.setUrl(url);
        weblink.setHost(host);

        return weblink;
    }

    //Relays call to getBookmarks from the DataStore, returns a 2d array
    public List<List<Bookmark>> getBookmarks() {
        return dao.getBookmarks();
    }

    //Performs business logic on user and bookmark
    public void saveUserBookmark(User user, Bookmark bookmark) {
        UserBookmark userBookmark = new UserBookmark(); //userBookmark class important for connecting a user with their bookmark selection
        userBookmark.setUser(user);
        userBookmark.setBookmark(bookmark);

        dao.saveUserBookmark(userBookmark); //passes the user and bookmarks to the dao
    }

    public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
        bookmark.setKidFriendlyStatus(kidFriendlyStatus);
        bookmark.setKidFriendlyMarkedBy(user);

        dao.updateKidFriendlyStatus(bookmark);

        System.out.println("Kid-friendly status: " + kidFriendlyStatus + ", Marked by: " + user.getEmail() + ", " + bookmark);
    }

    public void share(User user, Bookmark bookmark) {
        bookmark.setSharedBy(user);
        System.out.println("Data to be shared: " );
        if (bookmark instanceof Book) {
            System.out.println(((Book)bookmark).getItemData());
        } else if (bookmark instanceof WebLink) {
            System.out.println(((WebLink)bookmark).getItemData());
        }
        dao.shareByInfo(bookmark);
    }

    public Collection<Bookmark> getBooks(boolean isBookmarked, long id) {
        return dao.getBooks(isBookmarked, id);
    }

    public Bookmark getBook(long bid) {
        return dao.getBook(bid);
    }
}
